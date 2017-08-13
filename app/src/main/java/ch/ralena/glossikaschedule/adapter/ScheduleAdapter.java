package ch.ralena.glossikaschedule.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import ch.ralena.glossikaschedule.R;
import ch.ralena.glossikaschedule.object.Day;
import io.reactivex.subjects.PublishSubject;
import io.realm.RealmList;

public class ScheduleAdapter extends RecyclerView.Adapter {
	RealmList<Day> days;
	Context context;
	PublishSubject<Day> observable = PublishSubject.create();
	int currentPosition;

	public ScheduleAdapter(int currentPosition, RealmList<Day> days, Context context) {
		this.currentPosition = currentPosition;
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

	public void removeHighlight() {
		int position = currentPosition;
		currentPosition = -1;
		notifyItemChanged(position);
	}

	private class ViewHolder extends RecyclerView.ViewHolder {
		LinearLayout dayLayout;
		TextView dayLabel;
		TextView dateLabel;
		Day day;
		int position;

		public ViewHolder(View view) {
			super(view);
			dayLayout = view.findViewById(R.id.dayLayout);
			dayLabel = view.findViewById(R.id.dayLabel);
			dateLabel = view.findViewById(R.id.dateLabel);
			view.setOnClickListener(onClickListener);
		}

		public void bindView(Day day, int position) {
			this.position = position;
			dayLabel.setText(day.getDayNumber()+"");
			if (day.isCompleted()) {
				dateLabel.setText(day.getFormattedDateShort());
			} else {

			}
			this.day = day;
			if (day.isCompleted()) {
				dayLabel.setTextColor(ContextCompat.getColor(context, android.R.color.white));
				if (day.wasCompletedToday()) {
					dayLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
				} else {
					dayLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLight));
				}
			} else {
				dayLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorBackground));
				dayLabel.setTextColor(ContextCompat.getColor(context, R.color.colorTextLight));
				dateLabel.setTextColor(ContextCompat.getColor(context, R.color.colorTextLight));
			}

			if (position == currentPosition) {
				dayLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
				dayLabel.setTextColor(ContextCompat.getColor(context, android.R.color.white));
				dateLabel.setTextColor(ContextCompat.getColor(context, android.R.color.white));
			}

		}

		View.OnClickListener onClickListener = new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				notifyItemChanged(currentPosition);
				currentPosition = position;
				notifyItemChanged(currentPosition);
				observable.onNext(day);
			}
		};
	}
}
