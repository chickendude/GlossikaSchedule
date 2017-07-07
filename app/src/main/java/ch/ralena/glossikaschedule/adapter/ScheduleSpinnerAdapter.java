package ch.ralena.glossikaschedule.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ch.ralena.glossikaschedule.R;
import ch.ralena.glossikaschedule.data.ScheduleType;

public class ScheduleSpinnerAdapter extends ArrayAdapter<ScheduleType> {
	List<ScheduleType> mScheduleTypes;
	LayoutInflater mInflater;

	public ScheduleSpinnerAdapter(Context context, int resource, List<ScheduleType> scheduleTypes) {
		super(context, resource, scheduleTypes);
		mScheduleTypes = scheduleTypes;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@NonNull
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return createView(position, convertView, parent);
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		return createView(position, convertView, parent);
	}

	private View createView(int position, View convertView, ViewGroup parent) {
		ScheduleType scheduleType = mScheduleTypes.get(position);
		View view = mInflater.inflate(R.layout.item_schedule_spinner, parent, false);
		TextView title = (TextView) view.findViewById(R.id.titleLabel);
		TextView summary = (TextView) view.findViewById(R.id.summaryLabel);
		TextView minutesDay = (TextView) view.findViewById(R.id.minutesDayLabel);
		TextView totalReps = (TextView) view.findViewById(R.id.repsLabel);

		title.setText(scheduleType.getTitle());
		summary.setText(scheduleType.getSummary());
		minutesDay.setText(scheduleType.getMinutesDay() + " min\nday");
		totalReps.setText(String.format("%,d", scheduleType.getTotalReps()) + " reps");
		return view;
	}

}
