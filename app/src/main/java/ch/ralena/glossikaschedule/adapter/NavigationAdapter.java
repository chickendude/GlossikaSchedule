package ch.ralena.glossikaschedule.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ch.ralena.glossikaschedule.R;
import ch.ralena.glossikaschedule.object.Schedule;

/**
 * Created by crater-windoze on 1/3/2017.
 */

public class NavigationAdapter extends RecyclerView.Adapter {
	public interface OnItemClickListener {
		void onNewSchedule();
		void onScheduleClicked(Schedule schedule);
	}

	private static final int TYPE_LANGUAGE = 1;
	private static final int TYPE_ADD_SCHEDULE = 2;
	private Context mContext;
	private OnItemClickListener mListener;
	private ArrayList<Schedule> mSchedules;
	private int mCurrentPosition;

	public NavigationAdapter(Context context, ArrayList<Schedule> schedules, int currentPosition) {
		mContext = context;
		mListener = (OnItemClickListener) context;
		mSchedules = schedules;
		mCurrentPosition = currentPosition;
	}

	public void setCurrentPosition(int currentPosition) {
		mCurrentPosition = currentPosition;
	}

	public int getCurrentPosition() {
		return mCurrentPosition;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view;
		if (viewType == TYPE_LANGUAGE) {
			view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_language, parent, false);
			return new ViewHolder(view);
		} else {
			view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_new_schedule, parent, false);
			return new ViewHolderNew(view);
		}
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		if (position < getItemCount() - 1)
			((ViewHolder) holder).bindView(mSchedules.get(position), position);
	}

	@Override
	public int getItemCount() {
		return mSchedules.size() + 1;
	}

	@Override
	public int getItemViewType(int position) {
		if (position < getItemCount() - 1) {
			return TYPE_LANGUAGE;
		} else {
			return TYPE_ADD_SCHEDULE;
		}
	}

	private class ViewHolder extends RecyclerView.ViewHolder {
		private View mView;
		private TextView mLanguageName;
		private TextView mScheduleType;
		private ImageView mFlagImage;
		private Schedule mSchedule;

		public ViewHolder(View view) {
			super(view);
			mView = view;
			mLanguageName = (TextView) view.findViewById(R.id.languageLabel);
			mScheduleType = (TextView) view.findViewById(R.id.scheduleTypeLabel);
			mFlagImage = (ImageView) view.findViewById(R.id.flagImageView);
			mView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					ViewGroup parent = (ViewGroup) view.getParent();
					int numViews = parent.getChildCount();
					for (int i = 0; i < numViews; i++) {
						parent.getChildAt(i).setBackgroundColor(Color.WHITE);
					}
					mView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimaryLight));
					mListener.onScheduleClicked(mSchedule);
				}
			});
		}

		public void bindView(Schedule schedule, int position) {
			mSchedule = schedule;
			if (mCurrentPosition == position) {
				mView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimaryLight));
			} else {
				mView.setBackgroundColor(Color.WHITE);
			}
			mLanguageName.setText(schedule.getLanguage());
			mScheduleType.setText(schedule.getTitle());
			mFlagImage.setImageResource(schedule.getLanguageType().getDrawable());
		}
	}

	private class ViewHolderNew extends RecyclerView.ViewHolder {
		public ViewHolderNew(View itemView) {
			super(itemView);
			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					mListener.onNewSchedule();
				}
			});
		}
	}
}
