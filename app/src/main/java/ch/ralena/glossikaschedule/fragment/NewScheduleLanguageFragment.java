package ch.ralena.glossikaschedule.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ch.ralena.glossikaschedule.R;
import ch.ralena.glossikaschedule.adapter.LanguageSelectAdapter;
import ch.ralena.glossikaschedule.data.LanguageData;
import io.realm.Realm;

public class NewScheduleLanguageFragment extends Fragment {
	private Realm realm;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		getActivity().setTitle("New Schedule");

		realm = Realm.getDefaultInstance();

		// load view
		View view = inflater.inflate(R.layout.fragment_new_schedule_language, container, false);

		RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
		LanguageSelectAdapter languageSelectAdapter = new LanguageSelectAdapter(LanguageData.languages);
		recyclerView.setAdapter(languageSelectAdapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		return view;
	}
}
