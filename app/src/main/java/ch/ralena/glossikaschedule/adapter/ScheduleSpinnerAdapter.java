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
	List<ScheduleType> scheduleTypes;
	LayoutInflater inflater;

	public ScheduleSpinnerAdapter(Context context, int resource, List<ScheduleType> scheduleTypes) {
		super(context, resource, scheduleTypes);
		this.scheduleTypes = scheduleTypes;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
		ScheduleType scheduleType = scheduleTypes.get(position);
		View view = inflater.inflate(R.layout.item_schedule_spinner, parent, false);
		TextView title = view.findViewById(R.id.titleLabel);
		TextView summary = view.findViewById(R.id.summaryLabel);
		TextView minutesDay = view.findViewById(R.id.minutesDayLabel);
		TextView totalReps = view.findViewById(R.id.repsLabel);

		title.setText(scheduleType.getTitle());
		summary.setText(scheduleType.getSummary());
		minutesDay.setText(scheduleType.getMinutesDay() + " min\nday");
		totalReps.setText(String.format("%,d", scheduleType.getTotalReps()) + " reps");
		return view;
	}

}
