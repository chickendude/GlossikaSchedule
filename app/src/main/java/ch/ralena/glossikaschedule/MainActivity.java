package ch.ralena.glossikaschedule;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import ch.ralena.glossikaschedule.adapter.DayAdapter;
import ch.ralena.glossikaschedule.adapter.NavigationAdapter;
import ch.ralena.glossikaschedule.fragment.MainFragment;
import ch.ralena.glossikaschedule.fragment.NewScheduleFragment;
import ch.ralena.glossikaschedule.object.Schedule;
import ch.ralena.glossikaschedule.object.StudyItem;
import ch.ralena.glossikaschedule.sql.SqlManager;

// TODO: 12/30/2016 mark currently selected study day
public class MainActivity extends AppCompatActivity implements NewScheduleFragment.OnScheduleCreatedListener, DayAdapter.OnItemCheckedListener, NavigationAdapter.OnItemClickListener {

	public static final String MAIN_FRAGMENT_TAG = "main_fragment";
	private static final String NEW_SCHEDULE_FRAGMENT_TAG = "new_schedule_fragment";
	private static final String TAG = MainActivity.class.getSimpleName();
	private static final String TAG_SCHEDULE_ID = "schedule_id";

	DrawerLayout mDrawerLayout;
	MainFragment mMainFragment;
	NewScheduleFragment mNewScheduleFragment;
	private SqlManager mSqlManager;
	private FragmentManager mFragmentManager;
	NavigationAdapter mNavigationAdapter;

	ArrayList<Schedule> mSchedules;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// set up toolbar
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		// set up sql and fragments
		mSqlManager = new SqlManager(this);
		mFragmentManager = getSupportFragmentManager();

		mSchedules = mSqlManager.getSchedules();

		if (mSchedules.size() == 0) {
			loadNewScheduleFragment();
		} else {
			loadMainFragment(mSchedules.get(0).getId());
		}

		// set up nav drawer
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		setupNavigationDrawer();
	}

	private void setupNavigationDrawer() {
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.navigationRecyclerView);
		mNavigationAdapter = new NavigationAdapter(this, mSchedules, 0);
		recyclerView.setAdapter(mNavigationAdapter);
		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);
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

	@Override
	public void onScheduleCreated(long id) {
		mSchedules = mSqlManager.getSchedules();
		mNavigationAdapter.notifyDataSetChanged();
		loadMainFragment(id);
	}

	@Override
	public void onItemChecked(ArrayList<StudyItem> studyItems) {
		mMainFragment.saveDay();
	}

	@Override
	public void onNewSchedule() {
		loadNewScheduleFragment();
	}

	@Override
	public void onScheduleClicked() {
	}
}
