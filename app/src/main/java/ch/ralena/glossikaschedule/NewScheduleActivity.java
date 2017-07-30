package ch.ralena.glossikaschedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import ch.ralena.glossikaschedule.fragment.NewScheduleFragment;
import ch.ralena.glossikaschedule.fragment.NewScheduleLanguageFragment;
import ch.ralena.glossikaschedule.object.Schedule;
import io.realm.Realm;

public class NewScheduleActivity extends AppCompatActivity implements NewScheduleFragment.OnScheduleCreatedListener {

	private static final String TAG = NewScheduleActivity.class.getSimpleName();
	public static final String MAIN_FRAGMENT_TAG = "main_fragment";
	private static final String NEW_SCHEDULE_FRAGMENT_TAG = "new_schedule_fragment";
	public static final String TAG_SCHEDULE_ID = "schedule_id";

//	DrawerLayout drawerLayout;
	private FragmentManager fragmentManager;
//	NavigationAdapter navigationAdapter;
//	ActionBarDrawerToggle drawerToggle;

//	RealmResults<Schedule> schedules;
//	Schedule loadedSchedule;

	private Realm realm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_schedule);

		fragmentManager = getSupportFragmentManager();

		// load schedules from database
		realm = Realm.getDefaultInstance();
//		schedules = realm.where(Schedule.class).findAll();

		// set up viewpager
		ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
		viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
			@Override
			public int getCount() {
				return 3;
			}

			@Override
			public Fragment getItem(int position) {
				switch (position) {
					case 0:
						return new NewScheduleLanguageFragment();
					case 1:
						return new NewScheduleLanguageFragment();
					default:
						return new NewScheduleLanguageFragment();
				}
			}
		});
	}

	@Override
	public void onScheduleCreated() {
		getSupportFragmentManager().popBackStack(NEW_SCHEDULE_FRAGMENT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
		Schedule schedule = realm.where(Schedule.class).findAll().last();

		// todo: go to main activity
//		loadMainFragment(schedule);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.toolbar, menu);
		return true;
	}
}
