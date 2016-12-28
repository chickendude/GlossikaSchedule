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
import ch.ralena.glossikaschedule.object.Schedule;
import ch.ralena.glossikaschedule.object.ScheduleData;
import ch.ralena.glossikaschedule.sql.SqlManager;

/**
 * Created by crater-windoze on 12/27/2016.
 */

public class MainFragment extends Fragment implements ScheduleAdapter.OnItemClickedListener {
	private static final String TAG = MainFragment.class.getSimpleName();
	public static final String CURRENT_DAY = "current_day";

	SqlManager mSqlManager;
	private Schedule mSchedule;
	private int mCurrentDay = -1;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		mSqlManager = new SqlManager(getContext());
		createSchedule();
		findCurrentDay();

		View view = inflater.inflate(R.layout.fragment_main, container, false);
		// set up recycler view
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.scheduleRecyclerView);
		ScheduleAdapter scheduleAdapter = new ScheduleAdapter(mSchedule.getSchedule(), this);
		recyclerView.setAdapter(scheduleAdapter);
		RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 7);
		recyclerView.setLayoutManager(layoutManager);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		showDay(getCurrentDay());
	}

	public void showDay(Day day) {
		DayFragment dayFragment = new DayFragment();
		dayFragment.setStyle(DialogFragment.STYLE_NO_FRAME, R.style.dialog);
		Bundle bundle = new Bundle();
		bundle.putParcelable(CURRENT_DAY, day);
		dayFragment.setArguments(bundle);
		dayFragment.show(getFragmentManager(), null);
	}

	private void findCurrentDay() {
		for (Day day : mSchedule.getSchedule()) {
			if (day.isCompleted()) {
				mCurrentDay = day.getDayNumber();
				return;
			}
		}
	}


	private void createSchedule() {
		ArrayList<Schedule> schedules = mSqlManager.getSchedule();
		if (schedules.size() == 0) {
			Log.d(TAG, "null");
			mSchedule = ScheduleData.createSchedule(ScheduleData.SCHEDULE_5_INTENSIVE);
			mSqlManager.createSchedule(mSchedule);
		} else {
			Log.d(TAG, "not null");
			mSchedule = schedules.get(0);
		}
	}

	private String formatNumber(int num, int numDigits) {
		return String.format("%0" + numDigits + "d", num);
	}

	private Day getCurrentDay() {
		int index = mCurrentDay;
		if(index < 0) {
			index = 0;
		}
		return mSchedule.getSchedule().get(index);
	}

	@Override
	public void onItemClicked(Day day) {
		showDay(day);
	}
}
