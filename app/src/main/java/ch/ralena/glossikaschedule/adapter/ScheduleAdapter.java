package ch.ralena.glossikaschedule.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ch.ralena.glossikaschedule.R;
import ch.ralena.glossikaschedule.object.Day;
import io.reactivex.subjects.PublishSubject;

public class ScheduleAdapter extends RecyclerView.Adapter {
	ArrayList<Day> days;
	Context context;
	PublishSubject<Day> observable = PublishSubject.create();

	public ScheduleAdapter(ArrayList<Day> days, Context context) {
		this.days = days;
		this.context = context;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		((ViewHolder) holder).bindView(days.get(position), position);
	}

	@Override
	public int getItemCount() {
		return days.size();
	}

	public PublishSubject<Day> asObservable() {
		return observable;
	}

	private class ViewHolder extends RecyclerView.ViewHolder {
		TextView mDayLabel;
		Day mDay;
		int mPosition;

		public ViewHolder(View view) {
			super(view);
			mDayLabel = (TextView) view.findViewById(R.id.dayLabel);
			mDayLabel.setOnClickListener(mOnClickListener);
		}

		public void bindView(Day day, int position) {
			mPosition = position;
			mDayLabel.setText(day.getDayNumber()+"");
			mDay = day;
			if (day.isCompleted()) {
				if (day.wasCompletedToday()) {
					mDayLabel.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
					mDayLabel.setTextColor(ContextCompat.getColor(context, R.color.colorAccentLight));
				} else {
					mDayLabel.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLight));
				}
			} else {
				mDayLabel.setBackgroundColor(ContextCompat.getColor(context, R.color.colorBackground));
			}
		}

		View.OnClickListener mOnClickListener = new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				observable.onNext(mDay);
			}
		};
	}
}
