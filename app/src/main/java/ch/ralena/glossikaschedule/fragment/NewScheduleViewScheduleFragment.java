package ch.ralena.glossikaschedule.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ch.ralena.glossikaschedule.R;
import ch.ralena.glossikaschedule.data.ScheduleType;

public class NewScheduleViewScheduleFragment extends Fragment {
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// get schedule from passed in bundle
		ScheduleType schedule = getArguments().getParcelable(NewScheduleScheduleFragment.EXTRA_SCHEDULE);
		// load view
		View view = inflater.inflate(R.layout.fragment_new_schedule_view_schedule, container, false);
		TextView title = view.findViewById(R.id.scheduleTitle);
		title.setText(schedule.getTitle());
		TextView summary = view.findViewById(R.id.scheduleSummary);
		summary.setText(schedule.getSummary());
		return view;
	}
}
