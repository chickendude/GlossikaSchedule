package ch.ralena.glossikaschedule;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import ch.ralena.glossikaschedule.fragment.DayFragment;
import ch.ralena.glossikaschedule.fragment.MainFragment;
import ch.ralena.glossikaschedule.fragment.NewScheduleFragment;
import ch.ralena.glossikaschedule.object.Day;
import ch.ralena.glossikaschedule.object.Schedule;
import ch.ralena.glossikaschedule.sql.SqlManager;

// TODO: 12/30/2016 mark currently selected study day
public class MainActivity extends AppCompatActivity implements DayFragment.OnDialogDismissedListener {

	public static final String MAIN_FRAGMENT_TAG = "main_fragment";
	private static final String TAG = MainActivity.class.getSimpleName();

	MainFragment mMainFragment;
	NewScheduleFragment mNewScheduleFragment;
	private SqlManager mSqlManager;
	private FragmentManager mFragmentManager;

	ArrayList<Schedule> mSchedules;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mSqlManager = new SqlManager(this);
		mFragmentManager = getSupportFragmentManager();

		mSchedules = getSchedules();

		if (mSchedules.size() == 0) {
			loadNewScheduleFragment();
		} else {
			loadMainFragment();
		}
	}

	private void loadMainFragment() {
		mMainFragment = (MainFragment) mFragmentManager.findFragmentByTag(MAIN_FRAGMENT_TAG);
		if (mMainFragment == null) {
			mMainFragment = new MainFragment();
			Bundle bundle = new Bundle();
			mMainFragment.setArguments(bundle);
			mFragmentManager
					.beginTransaction()
					.add(R.id.fragmentPlaceHolder, mMainFragment, MAIN_FRAGMENT_TAG)
					.commit();
		}
	}

	private void loadNewScheduleFragment() {
		mNewScheduleFragment = (NewScheduleFragment) mFragmentManager.findFragmentByTag(MAIN_FRAGMENT_TAG);
		if (mNewScheduleFragment == null) {
			NewScheduleFragment newScheduleFragment = new NewScheduleFragment();
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.fragmentPlaceHolder, newScheduleFragment, MainActivity.MAIN_FRAGMENT_TAG)
					.commit();
		}
	}

	private ArrayList<Schedule> getSchedules() {
		return mSqlManager.getSchedule();
	}

	@Override
	public void dialogDismissed(Day day) {
		mMainFragment.saveDay(day);
	}
}
