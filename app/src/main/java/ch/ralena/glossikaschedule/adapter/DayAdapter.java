package ch.ralena.glossikaschedule.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;

import ch.ralena.glossikaschedule.R;
import ch.ralena.glossikaschedule.object.StudyItem;

/**
 * Created by crater-windoze on 12/27/2016.
 */

public class DayAdapter extends RecyclerView.Adapter {
	public interface OnItemCheckedListener {
		void onItemChecked(ArrayList<StudyItem> studyItems);
	}

	ArrayList<StudyItem> mStudyItems;
	OnItemCheckedListener mListener;

	public DayAdapter(ArrayList<StudyItem> studyItems, OnItemCheckedListener listener) {
		mStudyItems = studyItems;
		mListener = listener;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_study_item, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		((ViewHolder) holder).bindView(mStudyItems.get(position));
	}

	@Override
	public int getItemCount() {
		return mStudyItems.size();
	}



	private class ViewHolder extends RecyclerView.ViewHolder {
		StudyItem mStudyItem;
		CheckBox fileCheckBox;

		public ViewHolder(View view) {
			super(view);
			fileCheckBox = (CheckBox) view.findViewById(R.id.fileCheckBox);
			fileCheckBox.setOnCheckedChangeListener(mCheckedChangeListener);
		}

		public void bindView(StudyItem studyItem) {
			mStudyItem = studyItem;
			fileCheckBox.setText(studyItem.getTitle());
			fileCheckBox.setChecked(studyItem.isCompleted());
		}

		CompoundButton.OnCheckedChangeListener mCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
				mStudyItem.setCompleted(isChecked);
				mListener.onItemChecked(mStudyItems);
			}
		};
	}
}
