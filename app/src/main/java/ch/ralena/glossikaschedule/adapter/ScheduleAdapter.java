package ch.ralena.glossikaschedule.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ch.ralena.glossikaschedule.R;
import ch.ralena.glossikaschedule.object.Day;

/**
 * Created by crater-windoze on 12/27/2016.
 */

public class ScheduleAdapter extends RecyclerView.Adapter {
	public interface OnItemClickedListener {
		void onItemClicked(Day day);
	}
	ArrayList<Day> mDays;
	OnItemClickedListener mListener;

	public ScheduleAdapter(ArrayList<Day> days, OnItemClickedListener listener) {
		mDays = days;
		mListener = listener;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		((ViewHolder) holder).bindView(mDays.get(position));
	}

	@Override
	public int getItemCount() {
		return mDays.size();
	}

	private class ViewHolder extends RecyclerView.ViewHolder {
		TextView mDayLabel;
		Day mDay;

		public ViewHolder(View view) {
			super(view);
			mDayLabel = (TextView) view.findViewById(R.id.dayLabel);
			mDayLabel.setOnClickListener(mOnClickListener);
		}

		public void bindView(Day day) {
			mDayLabel.setText(day.getDayNumber()+"");
			mDay = day;
		}

		View.OnClickListener mOnClickListener = new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mListener.onItemClicked(mDay);
			}
		};
	}
}
