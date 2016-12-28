package ch.ralena.glossikaschedule.fragment;

import android.content.Context;
import android.content.DialogInterface;
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
	public interface OnDialogDismissedListener {
		void dialogDismissed(Day day);
	}

	private TextView mDayLabel;
	private OnDialogDismissedListener mListener;
	private Day mDay;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		mListener = (OnDialogDismissedListener) context;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		getDialog().setCanceledOnTouchOutside(true);
		// get our parameters
		Bundle bundle = getArguments();
		mDay = bundle.getParcelable(MainFragment.CURRENT_DAY);
		// inflate view
		View view = inflater.inflate(R.layout.fragment_day, container, false);
		mDayLabel = (TextView) view.findViewById(R.id.dayLabel);
		mDayLabel.setText("Day " + mDay.getDayNumber());
		// set up recycler view
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
		DayAdapter adapter = new DayAdapter(mDay.getStudyItems());
		recyclerView.setAdapter(adapter);
		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
		recyclerView.setLayoutManager(layoutManager);
		return view;
	}

	@Override
	public void onDismiss(DialogInterface dialog) {
		super.onDismiss(dialog);
		mListener.dialogDismissed(mDay);
	}
}
