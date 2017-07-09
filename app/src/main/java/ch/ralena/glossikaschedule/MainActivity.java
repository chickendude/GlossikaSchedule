package ch.ralena.glossikaschedule;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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
	public static final String TAG_SCHEDULE_ID = "schedule_id";

	DrawerLayout drawerLayout;
	MainFragment mainFragment;
	NewScheduleFragment newScheduleFragment;
	private SqlManager sqlManager;
	private FragmentManager fragmentManager;
	NavigationAdapter navigationAdapter;
	ActionBarDrawerToggle drawerToggle;

	ArrayList<Schedule> schedules;
	Schedule loadedSchedule;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

		// set up toolbar
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		// set up sql and fragments
		sqlManager = new SqlManager(this);
		fragmentManager = getSupportFragmentManager();

		schedules = sqlManager.getSchedules();

		if (schedules.size() == 0) {
			loadNewScheduleFragment();
		} else {
			loadMainFragment(schedules.get(0));
		}

		// set up nav drawer
		drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		setupNavigationDrawer();
	}

	private void setupNavigationDrawer() {
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				invalidateOptionsMenu();
			}
		};
		drawerToggle.syncState();
		drawerToggle.setDrawerIndicatorEnabled(true);
		drawerLayout.setDrawerListener(drawerToggle);
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.navigationRecyclerView);
		navigationAdapter = new NavigationAdapter(this, schedules, 0);
		recyclerView.setAdapter(navigationAdapter);
		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);
	}

	private void loadMainFragment(Schedule schedule) {
		if (navigationAdapter != null) {
			int position = schedules.indexOf(schedule);
			navigationAdapter.setCurrentPosition(position);
			navigationAdapter.notifyDataSetChanged();
		}
		loadedSchedule = schedule;
		drawerLayout.closeDrawers();
		mainFragment = (MainFragment) getSupportFragmentManager().findFragmentByTag(MAIN_FRAGMENT_TAG);
		if (mainFragment != null) {
			fragmentManager
					.beginTransaction()
					.remove(mainFragment)
					.commit();
		}
		mainFragment = new MainFragment();
		Bundle bundle = new Bundle();
		bundle.putLong(TAG_SCHEDULE_ID, schedule.getId());
		mainFragment.setArguments(bundle);
		fragmentManager
				.beginTransaction()
				.replace(R.id.fragmentPlaceHolder, mainFragment, MAIN_FRAGMENT_TAG)
				.commit();
	}

	private void loadNewScheduleFragment() {
		drawerLayout.closeDrawers();
		mainFragment = (MainFragment) fragmentManager.findFragmentByTag(MAIN_FRAGMENT_TAG);
		newScheduleFragment = (NewScheduleFragment) fragmentManager.findFragmentByTag(NEW_SCHEDULE_FRAGMENT_TAG);
		if (newScheduleFragment == null) {
			newScheduleFragment = new NewScheduleFragment();
		}
		FragmentTransaction fragmentTransaction = getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.fragmentPlaceHolder, newScheduleFragment, NEW_SCHEDULE_FRAGMENT_TAG);
		if (mainFragment != null) {
			fragmentTransaction.addToBackStack(NEW_SCHEDULE_FRAGMENT_TAG);
		}
		fragmentTransaction.commit();

	}

	@Override
	public void onScheduleCreated(Schedule schedule) {
		getSupportFragmentManager().popBackStack(NEW_SCHEDULE_FRAGMENT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
		schedules.add(schedule);
		loadMainFragment(schedule);
	}

	@Override
	public void onItemChecked(ArrayList<StudyItem> studyItems) {
		mainFragment.saveDay();
	}

	@Override
	public void onNewSchedule() {
		loadNewScheduleFragment();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.toolbar, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				drawerLayout.openDrawer(GravityCompat.START);  // OPEN DRAWER
				return true;
			case R.id.action_delete:
				deleteSchedule();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void deleteSchedule() {
		final Snackbar snackbar = Snackbar.make(findViewById(R.id.fragmentPlaceHolder), "Delete " + loadedSchedule.getLanguage() + "?\n(Can't be undone!)", Snackbar.LENGTH_INDEFINITE);
		snackbar.setAction("Delete", new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				sqlManager.deleteSchedule(loadedSchedule);
				int position = schedules.indexOf(loadedSchedule);
				schedules.remove(loadedSchedule);
				navigationAdapter.notifyItemRemoved(position);
				if (position > 0) position--;
				loadMainFragment(schedules.get(position));
				snackbar.dismiss();
			}
		});
		snackbar.show();
	}

	@Override
	public void onScheduleClicked(Schedule schedule) {
		loadMainFragment(schedule);
	}
}
