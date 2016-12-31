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

/**
 * Created by crater-windoze on 12/31/2016.
 */

public class LanguageSpinnerAdapter extends ArrayAdapter<LanguageType> {
	List<LanguageType> mLanguages;
	LayoutInflater mInflater;

	public LanguageSpinnerAdapter(Context context, int resource, List<LanguageType> languages) {
		super(context, resource, languages);
		mLanguages = languages;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
		LanguageType languageType = mLanguages.get(position);
		View view = mInflater.inflate(R.layout.item_language_spinner, parent, false);
		TextView languageName = (TextView) view.findViewById(R.id.languageLabel);
		languageName.setText(languageType.getName());
		ImageView flag = (ImageView) view.findViewById(R.id.flagImageView);
		flag.setImageResource(languageType.getDrawable());
		return view;
	}
}
