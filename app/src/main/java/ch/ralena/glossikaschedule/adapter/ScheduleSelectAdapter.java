package ch.ralena.glossikaschedule.adapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ch.ralena.glossikaschedule.R;
import ch.ralena.glossikaschedule.data.LanguageType;

public class ScheduleSelectAdapter extends RecyclerView.Adapter<ScheduleSelectAdapter.LanguageViewHolder> {
	public interface OnLanguageSelectedListener {
		void onLanguageSelected(LanguageType language);
	}

	OnLanguageSelectedListener listener;

	List<LanguageType> languages;
	LanguageType selectedLanguage;

	public ScheduleSelectAdapter(List<LanguageType> languages) {
		this.languages = languages;
	}

	@Override
	public ScheduleSelectAdapter.LanguageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_language, parent, false);
		listener = (OnLanguageSelectedListener) parent.getContext();
		return new LanguageViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ScheduleSelectAdapter.LanguageViewHolder holder, int position) {
		holder.bindView(languages.get(position));
	}

	@Override
	public int getItemCount() {
		return languages.size();
	}

	class LanguageViewHolder extends RecyclerView.ViewHolder {
		TextView languageName;
		ImageView flag;

		LanguageViewHolder(View itemView) {
			super(itemView);
			languageName = itemView.findViewById(R.id.languageLabel);
			flag = itemView.findViewById(R.id.flagImageView);
		}

		private void bindView(LanguageType language) {
			if (language.equals(selectedLanguage)) {
				itemView.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.colorAccent));
			} else {
				itemView.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), android.R.color.transparent));
			}
			itemView.setOnClickListener(view -> {
				selectedLanguage = language;
				notifyDataSetChanged();
				listener.onLanguageSelected(language);
			});
			languageName.setText(language.getName());
			flag.setImageResource(language.getDrawable());
		}
	}
}
