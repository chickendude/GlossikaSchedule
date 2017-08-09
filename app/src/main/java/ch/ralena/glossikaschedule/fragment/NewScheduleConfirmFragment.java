package ch.ralena.glossikaschedule.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ch.ralena.glossikaschedule.R;
import io.realm.Realm;

public class NewScheduleConfirmFragment extends Fragment {
	private Realm realm;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		realm = Realm.getDefaultInstance();

		// load view
		View view = inflater.inflate(R.layout.fragment_new_schedule_confirm, container, false);
		return view;
	}
}
