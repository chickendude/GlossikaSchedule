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

import ch.ralena.glossikaschedule.MainActivity;
import ch.ralena.glossikaschedule.R;
import ch.ralena.glossikaschedule.adapter.LanguageSpinnerAdapter;
import ch.ralena.glossikaschedule.adapter.ScheduleSpinnerAdapter;
import ch.ralena.glossikaschedule.data.LanguageData;
import ch.ralena.glossikaschedule.data.LanguageType;
import ch.ralena.glossikaschedule.data.ScheduleData;
import ch.ralena.glossikaschedule.object.Schedule;
import ch.ralena.glossikaschedule.sql.SqlManager;

/**
 * Created by crater-windoze on 12/30/2016.
 */

public class NewScheduleFragment extends Fragment implements AdapterView.OnItemSelectedListener {

	public interface OnScheduleCreatedListener {
		void onScheduleCreated(Schedule schedule);
	}

	private Spinner mScheduleSpinner;
	private Spinner mLanguageSpinner;
	private int mScheduleType;
	private LanguageType mLanguageType;
	private OnScheduleCreatedListener mListener;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		mListener = (OnScheduleCreatedListener) context;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		((MainActivity) getActivity()).getSupportActionBar().setTitle("New Schedule");

		View view = inflater.inflate(R.layout.fragment_new_schedule, container, false);
		mScheduleSpinner = (Spinner) view.findViewById(R.id.scheduleSpinner);
		ScheduleSpinnerAdapter scheduleSpinnerAdapter = new ScheduleSpinnerAdapter(getActivity(), R.layout.item_schedule_spinner, ScheduleData.getScheduleTypes());
		mScheduleSpinner.setAdapter(scheduleSpinnerAdapter);
		mScheduleSpinner.setOnItemSelectedListener(this);

		mLanguageSpinner = (Spinner) view.findViewById(R.id.languageSpinner);
		LanguageSpinnerAdapter languageSpinnerAdapter = new LanguageSpinnerAdapter(getActivity(), R.layout.item_language_spinner, LanguageData.Languages);
		mLanguageSpinner.setAdapter(languageSpinnerAdapter);

		mLanguageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
				mLanguageType = (LanguageType) adapterView.getItemAtPosition(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {

			}
		});

		Button button = (Button) view.findViewById(R.id.createScheduleButton);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				SqlManager sqlManager = new SqlManager(getActivity());
				Schedule schedule;
				schedule = ScheduleData.createSchedule(mScheduleType, mLanguageType.getName());
				long id = sqlManager.createSchedule(schedule);
				schedule.setId(id);
				mListener.onScheduleCreated(schedule);
			}
		});
		return view;
	}

	@Override
	public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
		mScheduleType = position;
	}

	@Override
	public void onNothingSelected(AdapterView<?> adapterView) {

	}
}
