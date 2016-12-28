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

import java.util.ArrayList;

import ch.ralena.glossikaschedule.R;
import ch.ralena.glossikaschedule.adapter.ScheduleAdapter;
import ch.ralena.glossikaschedule.object.Day;
import ch.ralena.glossikaschedule.object.Language;
import ch.ralena.glossikaschedule.object.Schedule;
import ch.ralena.glossikaschedule.object.ScheduleData;
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
	private int mCurrentDay = -1;
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

		createSchedule();
		findCurrentDay();

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
				Log.d(TAG, "Create Dialog");
				showDay(getCurrentDay());
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

	private void findCurrentDay() {
		for (Day day : mSchedule.getSchedule()) {
			mCurrentDay = day.getDayNumber() - 1;
			if (!day.isCompleted()) {
				return;
			}
		}
	}

	private void createSchedule() {
		ArrayList<Schedule> schedules = mSqlManager.getSchedule();
		if (schedules.size() == 0) {
			mSchedule = ScheduleData.createSchedule(ScheduleData.SCHEDULE_5_INTENSIVE, Language.CANTONESE);
			mSqlManager.createSchedule(mSchedule);
		} else {
			mSchedule = schedules.get(0);
		}
	}

	private Day getCurrentDay() {
		int index = mCurrentDay;
		if (index < 0) {
			index = 0;
		}
		return mSchedule.getSchedule().get(index);
	}

	@Override
	public void onItemClicked(Day day) {
		showDay(day);
	}

	public void saveDay(Day day) {
		mIsDialogOpen = false;
		boolean isCompleted = true;
		for (StudyItem studyItem : day.getStudyItems()) {
			isCompleted = isCompleted & studyItem.isCompleted();
		}
		day.setCompleted(isCompleted);
		mAdapter.notifyDataSetChanged();
		mSqlManager.updateDay(day);
	}
}
