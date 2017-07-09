package ch.ralena.glossikaschedule.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ch.ralena.glossikaschedule.MainActivity;
import ch.ralena.glossikaschedule.R;
import ch.ralena.glossikaschedule.adapter.ScheduleAdapter;
import ch.ralena.glossikaschedule.object.Day;
import ch.ralena.glossikaschedule.object.Schedule;
import ch.ralena.glossikaschedule.object.StudyItem;
import ch.ralena.glossikaschedule.sql.SqlManager;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class MainFragment extends Fragment {
	private static final String TAG = MainFragment.class.getSimpleName();
	public static final String CURRENT_DAY = "current_day";
	private static final String TAG_DIALOG_OPEN = "dialog_open";
	private static final String DAY_FRAGMENT_TAG = "day_fragment";

	SqlManager sqlManager;
	private Schedule schedule;
	private int currentDayId = -1;
	private Day currentDay;
	private ScheduleAdapter adapter;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Log.d(TAG, "Create view");
		sqlManager = new SqlManager(getContext());

		// get the arguments passed in
		Bundle bundle = getArguments();
		long id = bundle.getLong(MainActivity.TAG_SCHEDULE_ID);
		schedule = sqlManager.getSchedule(id);

		// set up title
		((MainActivity) getActivity()).getSupportActionBar().setTitle(schedule.getLanguage() + " - " + schedule.getTitle());

		// skip over all days that have already been completed
		findNextIncompleteDay();

		// load views
		View view = inflater.inflate(R.layout.fragment_main, container, false);
		// set up adapter and subscribe to clicks on a day
		adapter = new ScheduleAdapter(schedule.getSchedule(), getContext());
		adapter.asObservable().subscribe(new Consumer<Day>() {
			@Override
			public void accept(@NonNull Day day) throws Exception {
				currentDay = day;
				showDay(day);
			}
		});
		// set up recycler view
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.scheduleRecyclerView);
		recyclerView.setAdapter(adapter);
		RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 7);
		recyclerView.setLayoutManager(layoutManager);
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.d(TAG, "Start");
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// if it's the first time loading the fragment, show the current day
		if (savedInstanceState == null) {
			showDay(currentDay);
		}
	}

	public void showDay(Day day) {
		DayFragment dayFragment = (DayFragment) getFragmentManager().findFragmentByTag(DAY_FRAGMENT_TAG);
		if (dayFragment == null) {
			dayFragment = new DayFragment();
			dayFragment.setStyle(DialogFragment.STYLE_NO_FRAME, R.style.dialog);
			Bundle bundle = new Bundle();
			bundle.putParcelable(CURRENT_DAY, day);
			dayFragment.setArguments(bundle);
			dayFragment.show(getFragmentManager(), DAY_FRAGMENT_TAG);
		}
	}

	private void findNextIncompleteDay() {
		for (Day day : schedule.getSchedule()) {
			currentDayId = day.getDayNumber() - 1;
			if (!day.isCompleted()) {
				getCurrentDay();
				return;
			}
		}
	}

	public Day getCurrentDay() {
		int index = currentDayId;
		if (index < 0) {
			index = 0;
		}
		currentDay = schedule.getSchedule().get(index);
		return currentDay;
	}

	public void saveDay() {
		boolean isCompleted = true;
		int numberCompleted = 0;
		int total = currentDay.getStudyItems().size();
		for (StudyItem studyItem : currentDay.getStudyItems()) {
			if (studyItem.isCompleted()) {
				numberCompleted++;
			}
			isCompleted = isCompleted & studyItem.isCompleted();
		}
		currentDay.setCompleted(isCompleted);
		if (numberCompleted == total || numberCompleted == total - 1) {
			int position = schedule.getSchedule().indexOf(currentDay);
			adapter.notifyItemChanged(position);
		}
		sqlManager.updateDay(currentDay);
	}
}
