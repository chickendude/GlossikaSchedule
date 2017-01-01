package ch.ralena.glossikaschedule;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import ch.ralena.glossikaschedule.adapter.DayAdapter;
import ch.ralena.glossikaschedule.fragment.DayFragment;
import ch.ralena.glossikaschedule.fragment.MainFragment;
import ch.ralena.glossikaschedule.fragment.NewScheduleFragment;
import ch.ralena.glossikaschedule.object.Day;
import ch.ralena.glossikaschedule.object.Schedule;
import ch.ralena.glossikaschedule.object.StudyItem;
import ch.ralena.glossikaschedule.sql.SqlManager;

// TODO: 12/30/2016 mark currently selected study day
public class MainActivity extends AppCompatActivity implements DayFragment.OnDialogDismissedListener, NewScheduleFragment.OnScheduleCreatedListener, DayAdapter.OnItemCheckedListener {

	public static final String MAIN_FRAGMENT_TAG = "main_fragment";
	private static final String NEW_SCHEDULE_FRAGMENT_TAG = "new_schedule_fragment";
	private static final String TAG = MainActivity.class.getSimpleName();
	private static final String TAG_SCHEDULE_ID = "schedule_id";

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
			loadMainFragment(mSchedules.get(0).getId());
		}
	}

	private void loadMainFragment(long scheduleId) {
		mMainFragment = (MainFragment) mFragmentManager.findFragmentByTag(MAIN_FRAGMENT_TAG);
		if (mMainFragment == null) {
			mMainFragment = new MainFragment();
			Bundle bundle = new Bundle();
			bundle.putLong(TAG_SCHEDULE_ID, scheduleId);
			mMainFragment.setArguments(bundle);
			mFragmentManager
					.beginTransaction()
					.replace(R.id.fragmentPlaceHolder, mMainFragment, MAIN_FRAGMENT_TAG)
					.commit();
		}
	}

	private void loadNewScheduleFragment() {
		mNewScheduleFragment = (NewScheduleFragment) mFragmentManager.findFragmentByTag(NEW_SCHEDULE_FRAGMENT_TAG);
		if (mNewScheduleFragment == null) {
			NewScheduleFragment newScheduleFragment = new NewScheduleFragment();
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.fragmentPlaceHolder, newScheduleFragment, NEW_SCHEDULE_FRAGMENT_TAG)
					.commit();
		}
	}

	private ArrayList<Schedule> getSchedules() {
		return mSqlManager.getSchedule();
	}

	@Override
	public void dialogDismissed(Day day) {
		mMainFragment.saveDay();
	}

	@Override
	public void onScheduleCreated(long id) {
		loadMainFragment(id);
	}

	@Override
	public void onItemChecked(ArrayList<StudyItem> studyItems) {
		mMainFragment.saveDay();
	}
}
