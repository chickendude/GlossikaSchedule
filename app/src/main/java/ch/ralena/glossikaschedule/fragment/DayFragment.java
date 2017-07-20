package ch.ralena.glossikaschedule.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import ch.ralena.glossikaschedule.R;
import ch.ralena.glossikaschedule.adapter.DayAdapter;
import ch.ralena.glossikaschedule.object.Day;

public class DayFragment extends DialogFragment {

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		getDialog().setCanceledOnTouchOutside(true);

		// get our parameters
		Bundle bundle = getArguments();
		Day day = bundle.getParcelable(MainFragment.TAG_CURRENT_DAY);

		// inflate views
		View view = inflater.inflate(R.layout.fragment_day, container, false);
		TextView dayLabel = (TextView) view.findViewById(R.id.dayLabel);
		final CheckBox checkAll = (CheckBox) view.findViewById(R.id.checkAll);

		// set up day title
		dayLabel.setText("Day " + day.getDayNumber());

		// set up completion date
		LinearLayout completedDateLayout = (LinearLayout) view.findViewById(R.id.completedDateLayout);
		if (day.getDateCompleted().equals("")) {
			// hide layout if it hasn't been completed yet
			completedDateLayout.setVisibility(View.GONE);
		} else {
			// otherwise update text to show the date it was completed
			TextView completedDateText = (TextView) view.findViewById(R.id.completedDateText);
			completedDateText.setText(day.getDateCompleted());
		}

		// set up recycler view and adapter
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
		final DayAdapter adapter = new DayAdapter(day, (DayAdapter.OnItemCheckedListener)getActivity());
		recyclerView.setAdapter(adapter);
		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
		recyclerView.setLayoutManager(layoutManager);

		// set up check all listener
		checkAll.setOnCheckedChangeListener((buttonView, isChecked) -> {
			checkAll.setChecked(false);
			adapter.changeAll(isChecked);
			// if they've all been checked, update the day's completion date
			day.updateDateCompleted();
			// we need to wait a short while, otherwise the changes won't get marked
			new Handler().postDelayed(this::dismiss, 400);
		});

		return view;
	}
}
