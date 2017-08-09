package ch.ralena.glossikaschedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

import ch.ralena.glossikaschedule.adapter.LanguageSelectAdapter;
import ch.ralena.glossikaschedule.data.LanguageType;
import ch.ralena.glossikaschedule.data.ScheduleType;
import ch.ralena.glossikaschedule.fragment.NewScheduleConfirmFragment;
import ch.ralena.glossikaschedule.fragment.NewScheduleFragment;
import ch.ralena.glossikaschedule.fragment.NewScheduleLanguageFragment;
import ch.ralena.glossikaschedule.fragment.NewScheduleScheduleFragment;
import ch.ralena.glossikaschedule.object.Schedule;
import io.realm.Realm;

public class NewScheduleActivity extends AppCompatActivity implements LanguageSelectAdapter.OnLanguageSelectedListener, NewScheduleFragment.OnScheduleCreatedListener {

	private static final String TAG = NewScheduleActivity.class.getSimpleName();
	private static final String NEW_SCHEDULE_FRAGMENT_TAG = "new_schedule_fragment";

	// views
	private ViewPager viewPager;
	private ImageView[] circleIndicatorImages = new ImageView[3];
	private Toolbar toolbar;
	// objects
	private Realm realm;
	private int currentPage;

	// results passed back in
	private LanguageType selectedLanguage;
	private ScheduleType selectedSchedule;

	private String[] titles = {"What language are you studying?", "What schedule do you want?", "Confirm your schedule"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_schedule);

		// load toolbar
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		// load schedules from database
		realm = Realm.getDefaultInstance();
//		schedules = realm.where(Schedule.class).findAll();

		// set up viewpager
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			}

			@Override
			public void onPageSelected(int position) {
				currentPage = position;
				invalidateOptionsMenu();
				if (position > 0) {
					getSupportActionBar().setDisplayHomeAsUpEnabled(true);
				} else {
					getSupportActionBar().setDisplayHomeAsUpEnabled(false);
				}
				toolbar.setTitle(titles[position]);
				updateCircleIndicators(position);
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
		viewPager.setOffscreenPageLimit(2);
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
						return new NewScheduleScheduleFragment();
					default:
						return new NewScheduleConfirmFragment();
				}
			}
		});
		currentPage = 0;
		toolbar.setTitle(titles[currentPage]);
		createCircleIndicators();
		updateCircleIndicators(currentPage);
	}

	private void createCircleIndicators() {
		LinearLayout circleLayout = (LinearLayout) findViewById(R.id.viewPagerCircles);
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		layoutParams.setMargins(5, 0, 5, 0);
		for (int i = 0; i < circleIndicatorImages.length; i++) {
			circleIndicatorImages[i] = new ImageView(this);
			circleIndicatorImages[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.circle_unselected));
			circleLayout.addView(circleIndicatorImages[i], layoutParams);
		}
	}

	private void updateCircleIndicators(int position) {
		for (int i = 0; i < circleIndicatorImages.length; i++) {
			circleIndicatorImages[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.circle_unselected));
		}
		circleIndicatorImages[position].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.circle_selected));
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				goBackToPreviousPage();
				break;
			case R.id.action_next:
				advanceToNextPage();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void goBackToPreviousPage() {
		viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
	}

	private void advanceToNextPage() {
		viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
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
		getMenuInflater().inflate(R.menu.schedule_toolbar, menu);
		switch (currentPage) {
			case 0:
				if (selectedLanguage != null)
					menu.findItem(R.id.action_next).setVisible(true);
				break;
			case 1:
				if (selectedSchedule != null)
					menu.findItem(R.id.action_confirm).setVisible(true);
				break;
			case 2:
				menu.findItem(R.id.action_confirm).setVisible(true);
				break;
		}
		return true;
	}

	@Override
	public void onLanguageSelected(LanguageType language) {
		if (selectedLanguage == language)
			advanceToNextPage();
		selectedLanguage = language;
		invalidateOptionsMenu();
//		viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
	}

	public void updateScheduleSelected(ScheduleType schedule) {
		selectedSchedule = schedule;
		invalidateOptionsMenu();
	}
}
