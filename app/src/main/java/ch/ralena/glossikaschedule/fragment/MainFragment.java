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

import java.util.ArrayList;

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

		Bundle bundle = getArguments();

		if (savedInstanceState != null) {
			mIsDialogOpen = savedInstanceState.getBoolean(TAG_DIALOG_OPEN);
		} else {
			mIsDialogOpen = false;
		}

		createSchedule();
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
		mIsDialogOpen = true;
		DayFragment dayFragment = new DayFragment();
		dayFragment.setStyle(DialogFragment.STYLE_NO_FRAME, R.style.dialog);
		Bundle bundle = new Bundle();
		bundle.putParcelable(CURRENT_DAY, day);
		dayFragment.setArguments(bundle);
		dayFragment.show(getFragmentManager(), null);
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

	private void createSchedule() {
		ArrayList<Schedule> schedules = mSqlManager.getSchedule();
		// TODO: check if we really need to test to load the schedule creator fragment
		if (schedules.size() == 0) {
			NewScheduleFragment newScheduleFragment = new NewScheduleFragment();
			getFragmentManager()
					.beginTransaction()
					.replace(R.id.fragmentPlaceHolder, newScheduleFragment, MainActivity.MAIN_FRAGMENT_TAG)
					.commit();
		} else {
			mSchedule = schedules.get(0);
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
		for (StudyItem studyItem : mCurrentDay.getStudyItems()) {
			isCompleted = isCompleted & studyItem.isCompleted();
		}
		mCurrentDay.setCompleted(isCompleted);
		mAdapter.notifyDataSetChanged();
		mSqlManager.updateDay(mCurrentDay);
	}
}
