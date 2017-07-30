package ch.ralena.glossikaschedule.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import ch.ralena.glossikaschedule.R;
import ch.ralena.glossikaschedule.adapter.ScheduleSpinnerAdapter;
import ch.ralena.glossikaschedule.data.LanguageType;
import ch.ralena.glossikaschedule.data.ScheduleData;
import io.realm.Realm;

public class NewScheduleFragment extends Fragment implements AdapterView.OnItemSelectedListener {

	public interface OnScheduleCreatedListener {
		void onScheduleCreated();
	}

	private Spinner scheduleSpinner;
	private Spinner languageSpinner;
	private int scheduleType;
	private LanguageType languageType;
	private OnScheduleCreatedListener listener;
	private Realm realm;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		listener = (OnScheduleCreatedListener) context;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		getActivity().setTitle("New Schedule");

		realm = Realm.getDefaultInstance();

		View view = inflater.inflate(R.layout.fragment_new_schedule, container, false);
		scheduleSpinner = view.findViewById(R.id.scheduleSpinner);
		ScheduleSpinnerAdapter scheduleSpinnerAdapter = new ScheduleSpinnerAdapter(getActivity(), R.layout.item_schedule_spinner, ScheduleData.getScheduleTypes());
		scheduleSpinner.setAdapter(scheduleSpinnerAdapter);
		scheduleSpinner.setOnItemSelectedListener(this);

//		languageSpinner = view.findViewById(R.id.languageSpinner);
//		LanguageSelectAdapter languageSelectAdapter = new LanguageSelectAdapter(getActivity(), R.layout.item_language, LanguageData.languages);
//		languageSpinner.setAdapter(languageSelectAdapter);

		languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
				languageType = (LanguageType) adapterView.getItemAtPosition(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {

			}
		});

		Button button = view.findViewById(R.id.createScheduleButton);
		button.setOnClickListener(v -> {
			ScheduleData.createSchedule(realm, scheduleType, languageType.getName());
			listener.onScheduleCreated();
		});
		return view;
	}

	@Override
	public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
		scheduleType = position;
	}

	@Override
	public void onNothingSelected(AdapterView<?> adapterView) {

	}
}
