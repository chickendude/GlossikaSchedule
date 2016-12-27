package ch.ralena.glossikaschedule;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ch.ralena.glossikaschedule.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

	private static final String MAIN_FRAGMENT_TAG = "main_fragment";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		MainFragment mainFragment = new MainFragment();
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager
				.beginTransaction()
				.add(R.id.fragmentPlaceHolder, mainFragment, MAIN_FRAGMENT_TAG)
				.commit();
	}
}
