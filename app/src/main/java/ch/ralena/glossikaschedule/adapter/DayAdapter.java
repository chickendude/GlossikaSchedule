package ch.ralena.glossikaschedule.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;

import ch.ralena.glossikaschedule.R;
import ch.ralena.glossikaschedule.object.Day;
import ch.ralena.glossikaschedule.object.StudyItem;

public class DayAdapter extends RecyclerView.Adapter {
	public interface OnItemCheckedListener {
		void onItemChecked(ArrayList<StudyItem> studyItems);
	}

	Day day;
	ArrayList<StudyItem> studyItems;
	OnItemCheckedListener listener;

	public DayAdapter(Day day, OnItemCheckedListener listener) {
		this.day = day;
		this.studyItems = day.getStudyItems();
		this.listener = listener;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_study_item, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		((ViewHolder) holder).bindView(studyItems.get(position));
	}

	@Override
	public int getItemCount() {
		return studyItems.size();
	}

	public void changeAll(final boolean isChecked) {
		for (StudyItem studyItem : studyItems) {
			studyItem.setCompleted(isChecked);
		}
		notifyDataSetChanged();
	}

	private class ViewHolder extends RecyclerView.ViewHolder {
		StudyItem studyItem;
		CheckBox fileCheckBox;

		public ViewHolder(View view) {
			super(view);
			fileCheckBox = (CheckBox) view.findViewById(R.id.fileCheckBox);
			fileCheckBox.setOnCheckedChangeListener(mCheckedChangeListener);
		}

		public void bindView(StudyItem studyItem) {
			this.studyItem = studyItem;
			fileCheckBox.setText(studyItem.getTitle());
			fileCheckBox.setChecked(studyItem.isCompleted());
		}

		CompoundButton.OnCheckedChangeListener mCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
				studyItem.setCompleted(isChecked);
				day.updateDateCompleted();
				listener.onItemChecked(studyItems);
			}
		};
	}
}
