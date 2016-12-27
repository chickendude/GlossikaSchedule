package ch.ralena.glossikaschedule.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.ArrayList;

import ch.ralena.glossikaschedule.R;
import ch.ralena.glossikaschedule.object.StudyItem;

/**
 * Created by crater-windoze on 12/27/2016.
 */

public class DayAdapter extends RecyclerView.Adapter {
	ArrayList<StudyItem> mStudyItems;

	public DayAdapter(ArrayList<StudyItem> studyItems) {
		mStudyItems = studyItems;
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
		CheckBox fileCheckBox;
		public ViewHolder(View view) {
			super(view);
			fileCheckBox = (CheckBox) view.findViewById(R.id.fileCheckBox);
		}

		public void bindView(StudyItem studyItem) {
			fileCheckBox.setText(studyItem.getTitle());
		}
	}
}
