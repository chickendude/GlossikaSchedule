package ch.ralena.glossikaschedule.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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

/**
 * Created by crater-windoze on 12/27/2016.
 */

public class MainFragment extends Fragment implements ScheduleAdapter.OnItemClickedListener {
	private static final String TAG = MainFragment.class.getSimpleName();
	public static final String CURRENT_DAY = "current_day";
	private static final String TAG_DIALOG_OPEN = "dialog_open";
	private static final String DAY_FRAGMENT_TAG = "day_fragment";

	SqlManager mSqlManager;
	private Schedule mSchedule;
	private int mCurrentDayId = -1;
	private Day mCurrentDay;
	private ScheduleAdapter mAdapter;
	private boolean mIsDialogOpen;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		mSqlManager = new SqlManager(getContext());

		if (savedInstanceState != null) {
			mIsDialogOpen = savedInstanceState.getBoolean(TAG_DIALOG_OPEN);
		} else {
			mIsDialogOpen = false;
		}

		Bundle bundle = getArguments();
		long id = bundle.getLong(MainActivity.TAG_SCHEDULE_ID);
		mSchedule = mSqlManager.getSchedule(id);

		findNextIncompleteDay();

		View view = inflater.inflate(R.layout.fragment_main, container, false);
		// set up recycler view
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.scheduleRecyclerView);
		mAdapter = new ScheduleAdapter(mSchedule.getSchedule(), this, getContext());
		recyclerView.setAdapter(mAdapter);
		RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 7);
		recyclerView.setLayoutManager(layoutManager);
		return view;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putBoolean(TAG_DIALOG_OPEN, mIsDialogOpen);
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (savedInstanceState == null) {
			if (!mIsDialogOpen) {
				showDay(mCurrentDay);
			}
		}
	}

	public void showDay(Day day) {
		DayFragment dayFragment = (DayFragment) getFragmentManager().findFragmentByTag(DAY_FRAGMENT_TAG);
		if (dayFragment == null) {
			mIsDialogOpen = true;
			dayFragment = new DayFragment();
			dayFragment.setStyle(DialogFragment.STYLE_NO_FRAME, R.style.dialog);
			Bundle bundle = new Bundle();
			bundle.putParcelable(CURRENT_DAY, day);
			dayFragment.setArguments(bundle);
			dayFragment.show(getFragmentManager(), DAY_FRAGMENT_TAG);
		}
	}

	private void findNextIncompleteDay() {
		for (Day day : mSchedule.getSchedule()) {
			mCurrentDayId = day.getDayNumber() - 1;
			if (!day.isCompleted()) {
				getCurrentDay();
				return;
			}
		}
	}

	public Day getCurrentDay() {
		int index = mCurrentDayId;
		if (index < 0) {
			index = 0;
		}
		mCurrentDay = mSchedule.getSchedule().get(index);
		return mCurrentDay;
	}

	@Override
	public void onItemClicked(Day day, int position) {
		mCurrentDay = day;
		showDay(day);
	}

	public void saveDay() {
		mIsDialogOpen = false;
		boolean isCompleted = true;
		int numberCompleted = 0;
		int total = mCurrentDay.getStudyItems().size();
		for (StudyItem studyItem : mCurrentDay.getStudyItems()) {
			if (studyItem.isCompleted()) {
				numberCompleted++;
			}
			isCompleted = isCompleted & studyItem.isCompleted();
		}
		mCurrentDay.setCompleted(isCompleted);
		if (numberCompleted == total || numberCompleted == total - 1) {
			mAdapter.notifyDataSetChanged();
		}
		mSqlManager.updateDay(mCurrentDay);
	}
}
