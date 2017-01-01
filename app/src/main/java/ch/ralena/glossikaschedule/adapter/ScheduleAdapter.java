package ch.ralena.glossikaschedule.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ch.ralena.glossikaschedule.R;
import ch.ralena.glossikaschedule.object.Day;

/**
 * Created by crater-windoze on 12/27/2016.
 */

public class ScheduleAdapter extends RecyclerView.Adapter {
	public interface OnItemClickedListener {
		void onItemClicked(Day day, int position);
	}
	ArrayList<Day> mDays;
	OnItemClickedListener mListener;
	Context mContext;

	public ScheduleAdapter(ArrayList<Day> days, OnItemClickedListener listener, Context context) {
		mDays = days;
		mListener = listener;
		mContext = context;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		((ViewHolder) holder).bindView(mDays.get(position), position);
	}

	@Override
	public int getItemCount() {
		return mDays.size();
	}

	private class ViewHolder extends RecyclerView.ViewHolder {
		LinearLayout mLayout;
		TextView mDayLabel;
		Day mDay;
		int mPosition;

		public ViewHolder(View view) {
			super(view);
			mLayout = (LinearLayout) view;
			mDayLabel = (TextView) view.findViewById(R.id.dayLabel);
			mDayLabel.setOnClickListener(mOnClickListener);
		}

		public void bindView(Day day, int position) {
			mPosition = position;
			mDayLabel.setText(day.getDayNumber()+"");
			mDay = day;
			if (day.isCompleted()) {
				mLayout.setBackground(ContextCompat.getDrawable(mContext, R.drawable.schedule_item_complete_background));
			} else {
				mLayout.setBackground(ContextCompat.getDrawable(mContext, R.drawable.schedule_item_background));
			}
		}

		View.OnClickListener mOnClickListener = new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mListener.onItemClicked(mDay, mPosition);
			}
		};
	}
}
