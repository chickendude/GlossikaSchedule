package ch.ralena.glossikaschedule.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import ch.ralena.glossikaschedule.R;
import ch.ralena.glossikaschedule.data.ScheduleData;
import ch.ralena.glossikaschedule.data.ScheduleType;

public class NewScheduleScheduleFragment extends Fragment {

	private LinearLayout circleContainer;
	private LayoutInflater inflater;
	private List<View> minuteCircles;
	private TreeMap<Integer, List<ScheduleType>> schedules;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		getActivity().setTitle("New Schedule");

		// save inflater
		this.inflater = inflater;

		// organize schedules by how many minutes they take
		schedules = new TreeMap<>();
		for (ScheduleType scheduleType : ScheduleData.scheduleList) {
			if (!schedules.containsKey(scheduleType.getMinutesDay())) {
				schedules.put(scheduleType.getMinutesDay(), new ArrayList<>());
			}
			schedules.get(scheduleType.getMinutesDay()).add(scheduleType);
		}

		// load view
		View view = inflater.inflate(R.layout.fragment_new_schedule_schedule, container, false);

		circleContainer = view.findViewById(R.id.circleContainer);

		loadMinuteCircles();

//		RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
//		ScheduleSelectAdapter scheduleSelectAdapter = new ScheduleSelectAdapter(schedules);
//		recyclerView.setAdapter(scheduleSelectAdapter);
//		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		return view;
	}

	private void loadMinuteCircles() {
		minuteCircles = new ArrayList<>();
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		lp.setMargins(0, 10, 0, 10);
		for (Integer minutes : schedules.keySet()) {
			// inflate view
			View minuteCircleView = inflater.inflate(R.layout.item_schedule_minutes, null, false);
			minuteCircleView.setLayoutParams(lp);
			// update minutes text
			TextView minutesLabel = minuteCircleView.findViewById(R.id.minutesLabel);
			minutesLabel.setText(minutes + "");
			// load descriptions
			FlexboxLayout descriptionContainer = minuteCircleView.findViewById(R.id.descriptionContainer);
			loadScheduleDescriptions(descriptionContainer, minutes);

			// add to arraylist and to layout
			minuteCircles.add(minuteCircleView);
			circleContainer.addView(minuteCircleView);
		}
	}

	private void loadScheduleDescriptions(FlexboxLayout descriptionContainer, int minutes) {
		int index = 0;
		for (ScheduleType scheduleType : schedules.get(minutes)) {
			View descriptionView = inflater.inflate(R.layout.item_schedule_description, null, false);
			// update textviews
			TextView monthsLabel = descriptionView.findViewById(R.id.bigLengthLabel);
			TextView weeksLabel = descriptionView.findViewById(R.id.smallLengthLabel);
			monthsLabel.setText(scheduleType.getCourseLength());
			weeksLabel.setText(scheduleType.getCourseLengthSmall());
			// if it's the last one, don't show the "or"
			if (++index == schedules.get(minutes).size()) {
				TextView orLabel = descriptionView.findViewById(R.id.orLabel);
				orLabel.setVisibility(View.GONE);
			}
			// add view to container
			descriptionContainer.addView(descriptionView);
		}
	}
}
