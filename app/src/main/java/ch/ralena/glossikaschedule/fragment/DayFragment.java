package ch.ralena.glossikaschedule.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ch.ralena.glossikaschedule.R;
import ch.ralena.glossikaschedule.adapter.DayAdapter;
import ch.ralena.glossikaschedule.object.Day;

/**
 * Created by crater-windoze on 12/27/2016.
 */

public class DayFragment extends DialogFragment {

	private TextView mDayLabel;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// get our parameters
		Bundle bundle = getArguments();
		Day day = bundle.getParcelable(MainFragment.CURRENT_DAY);
		// inflate view
		View view = inflater.inflate(R.layout.fragment_day, container, false);
		mDayLabel = (TextView) view.findViewById(R.id.dayLabel);
		mDayLabel.setText("Day " + day.getDayNumber());
		// set up recycler view
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
		DayAdapter adapter = new DayAdapter(day.getStudyItems());
		recyclerView.setAdapter(adapter);
		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
		recyclerView.setLayoutManager(layoutManager);
		return view;
	}
}
