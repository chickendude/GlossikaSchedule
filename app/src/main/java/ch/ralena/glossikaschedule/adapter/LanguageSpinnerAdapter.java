package ch.ralena.glossikaschedule.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ch.ralena.glossikaschedule.R;
import ch.ralena.glossikaschedule.data.LanguageType;

public class LanguageSpinnerAdapter extends ArrayAdapter<LanguageType> {
	List<LanguageType> languages;
	LayoutInflater inflater;

	public LanguageSpinnerAdapter(Context context, int resource, List<LanguageType> languages) {
		super(context, resource, languages);
		this.languages = languages;
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
		// language type is an object with the language's name and flag drawable
		LanguageType languageType = languages.get(position);
		View view = inflater.inflate(R.layout.item_language_spinner, parent, false);

		// update language name
		TextView languageName = view.findViewById(R.id.languageLabel);
		languageName.setText(languageType.getName());

		// update flag
		ImageView flag = view.findViewById(R.id.flagImageView);
		flag.setImageResource(languageType.getDrawable());
		return view;
	}
}
