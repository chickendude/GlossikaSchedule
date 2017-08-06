package ch.ralena.glossikaschedule.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import ch.ralena.glossikaschedule.R;
import ch.ralena.glossikaschedule.adapter.ScheduleSelectAdapter;
import ch.ralena.glossikaschedule.data.ScheduleData;
import ch.ralena.glossikaschedule.data.ScheduleType;

public class NewScheduleScheduleFragment extends Fragment {

	private TreeMap<Integer, List<ScheduleType>> schedules;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		getActivity().setTitle("New Schedule");

		// organize schedules by how many minutes they take
		schedules = new TreeMap<>();
		for (ScheduleType scheduleType : ScheduleData.scheduleList) {
			schedules.putIfAbsent(scheduleType.getMinutesDay(), new ArrayList<>());
			schedules.get(scheduleType.getMinutesDay()).add(scheduleType);
		}

		// load view
		View view = inflater.inflate(R.layout.fragment_new_schedule_language, container, false);

		RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
		ScheduleSelectAdapter scheduleSelectAdapter = new ScheduleSelectAdapter(schedules);
		recyclerView.setAdapter(scheduleSelectAdapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		return view;
	}
}
