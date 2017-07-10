package ch.ralena.glossikaschedule.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ch.ralena.glossikaschedule.MainActivity;
import ch.ralena.glossikaschedule.R;
import ch.ralena.glossikaschedule.adapter.ScheduleAdapter;
import ch.ralena.glossikaschedule.object.Day;
import ch.ralena.glossikaschedule.object.Schedule;
import ch.ralena.glossikaschedule.object.StudyItem;
import ch.ralena.glossikaschedule.sql.SqlManager;

public class MainFragment extends Fragment {
	private static final String TAG = MainFragment.class.getSimpleName();
	public static final String TAG_CURRENT_DAY = "tag_current_day";
	//	private static final String TAG_DIALOG_OPEN = "tag_dialog_open";
	private static final String DAY_FRAGMENT_TAG = "day_fragment";
	private static final String TAG_ARE_EMPTY = "tag_are_empty";

	SqlManager sqlManager;
	private Schedule schedule;
	private int currentDayId = -1;
	private Day currentDay;
	private ScheduleAdapter adapter;
	private View rootView;
	private boolean isDialogReady;
	private Snackbar snackbar;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Log.d(TAG, "Create view");
		sqlManager = new SqlManager(getContext());
		isDialogReady = true;

		// get the arguments passed in
		Bundle bundle = getArguments();
		long id = bundle.getLong(MainActivity.TAG_SCHEDULE_ID);
		schedule = sqlManager.getSchedule(id);

		// set up title
		((MainActivity) getActivity()).getSupportActionBar().setTitle(schedule.getLanguage() + " - " + schedule.getTitle());

		// load views
		rootView = inflater.inflate(R.layout.fragment_main, container, false);

		// set up adapter and subscribe to clicks on a day
		adapter = new ScheduleAdapter(schedule.getSchedule(), getContext());
		adapter.asObservable().subscribe(this::showDay);

		// set up recycler view
		RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.scheduleRecyclerView);
		recyclerView.setAdapter(adapter);
		RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 7);
		recyclerView.setLayoutManager(layoutManager);

		// skip over all days that have already been completed
		findNextIncompleteDay();

		return rootView;
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// if it's the first time loading the fragment, show the current day
		if (savedInstanceState == null) {
			showDay(currentDay);
		}
	}

	// loads a dialog fragment with checkboxes for the recordings you need to study for that day
	public void showDay(Day day) {
		currentDay = day;
		if (isDialogReady) {
			if (areEmptyDays()) {
				askToFillInDays();
			} else {
				openDayDialog();
			}
		} else {
			snackbar.setText(String.format(getString(R.string.mark_days_as_complete), currentDay.getDayNumber()));
		}
	}

	private void openDayDialog() {
		DayFragment dayFragment = (DayFragment) getFragmentManager().findFragmentByTag(DAY_FRAGMENT_TAG);
		if (dayFragment == null) {
			dayFragment = new DayFragment();
			dayFragment.setStyle(DialogFragment.STYLE_NO_FRAME, R.style.dialog);
			Bundle bundle = new Bundle();
			bundle.putParcelable(TAG_CURRENT_DAY, currentDay);
			dayFragment.setArguments(bundle);
			dayFragment.show(getFragmentManager(), DAY_FRAGMENT_TAG);
		}
	}

	private void askToFillInDays() {
		isDialogReady = false;
		snackbar = Snackbar.make(rootView,
				String.format(getString(R.string.mark_days_as_complete), currentDay.getDayNumber()),
				Snackbar.LENGTH_INDEFINITE)
				.setAction("Yes", v -> {
					for (Day day: schedule.getSchedule()) {
						if (day.getDayNumber() < currentDay.getDayNumber()) {
							day.setCompleted(true);
							for (StudyItem studyItem : day.getStudyItems()) {
								studyItem.setCompleted(true);
							}
							sqlManager.updateDay(day);
						}
					}
					adapter.notifyDataSetChanged();
				}).addCallback(new Snackbar.Callback() {
					@Override
					public void onDismissed(Snackbar transientBottomBar, int event) {
						super.onDismissed(transientBottomBar, event);
						isDialogReady = true;
						openDayDialog();
					}
				});
		snackbar.show();
	}

	private boolean areEmptyDays() {
		ArrayList<Day> days = schedule.getSchedule();
		int currentDayIndex = currentDay.getDayNumber();
		boolean isEmpty = false;
		for (Day day : days) {
			if (day.getDayNumber() < currentDayIndex && !day.isCompleted())
				isEmpty = true;
		}
		return isEmpty;
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
