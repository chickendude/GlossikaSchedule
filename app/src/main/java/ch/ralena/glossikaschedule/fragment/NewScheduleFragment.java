package ch.ralena.glossikaschedule.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import ch.ralena.glossikaschedule.R;
import ch.ralena.glossikaschedule.adapter.ScheduleSpinnerAdapter;
import ch.ralena.glossikaschedule.data.ScheduleData;

/**
 * Created by crater-windoze on 12/30/2016.
 */

public class NewScheduleFragment extends Fragment {
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_new_schedule, container, false);
		Spinner scheduleSpinner = (Spinner) view.findViewById(R.id.scheduleSpinner);
		ScheduleSpinnerAdapter scheduleSpinnerAdapter = new ScheduleSpinnerAdapter(getActivity(), R.layout.item_schedule_spinner, ScheduleData.getScheduleTypes());
		scheduleSpinner.setAdapter(scheduleSpinnerAdapter);
		return view;
	}
}
