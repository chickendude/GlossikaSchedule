package ch.ralena.glossikaschedule.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ch.ralena.glossikaschedule.R;
import ch.ralena.glossikaschedule.adapter.ScheduleAdapter;
import ch.ralena.glossikaschedule.object.Schedule;
import ch.ralena.glossikaschedule.object.ScheduleData;

/**
 * Created by crater-windoze on 12/27/2016.
 */

public class MainFragment extends Fragment {
	private static final String TAG = MainFragment.class.getSimpleName();

	Schedule mSchedule;


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		createSchedule();

		View view = inflater.inflate(R.layout.fragment_main, container, false);
		// set up recycler view
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.scheduleRecyclerView);
		ScheduleAdapter scheduleAdapter = new ScheduleAdapter(mSchedule.getSchedule());
		recyclerView.setAdapter(scheduleAdapter);
		RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 7);
		recyclerView.setLayoutManager(layoutManager);
		return view;
	}

	private void createSchedule() {
		// 5 month intensive
		mSchedule = ScheduleData.createSchedule(ScheduleData.SCHEDULE_5_INTENSIVE);
	}

	private String formatNumber(int num, int numDigits) {
		return String.format("%0" + numDigits + "d", num);
	}
}
