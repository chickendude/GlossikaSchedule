package ch.ralena.glossikaschedule;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import ch.ralena.glossikaschedule.fragment.DayFragment;
import ch.ralena.glossikaschedule.fragment.MainFragment;
import ch.ralena.glossikaschedule.object.Day;
import ch.ralena.glossikaschedule.sql.SqlManager;

public class MainActivity extends AppCompatActivity implements DayFragment.OnDialogDismissedListener {

	private static final String MAIN_FRAGMENT_TAG = "main_fragment";

	MainFragment mMainFragment;
	private SqlManager mSqlManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mSqlManager = new SqlManager(this);

		FragmentManager fragmentManager = getSupportFragmentManager();
		mMainFragment = (MainFragment) fragmentManager.findFragmentByTag(MAIN_FRAGMENT_TAG);
		if (mMainFragment == null) {
			mMainFragment = new MainFragment();
			Bundle bundle = new Bundle();
			mMainFragment.setArguments(bundle);
			fragmentManager
					.beginTransaction()
					.add(R.id.fragmentPlaceHolder, mMainFragment, MAIN_FRAGMENT_TAG)
					.commit();
		}
	}

	@Override
	public void dialogDismissed(Day day) {
		mMainFragment.saveDay(day);
	}
}
