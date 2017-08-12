package ch.ralena.glossikaschedule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import ch.ralena.glossikaschedule.data.LanguageType;
import ch.ralena.glossikaschedule.data.ScheduleType;
import ch.ralena.glossikaschedule.fragment.NewScheduleConfirmFragment;
import ch.ralena.glossikaschedule.fragment.NewScheduleLanguageFragment;
import io.realm.Realm;

public class NewScheduleActivity extends AppCompatActivity {
	private static final String TAG = NewScheduleActivity.class.getSimpleName();
	private static final String NEW_SCHEDULE_FRAGMENT_TAG = "new_schedule_fragment";

	// views
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
//		realm = Realm.getDefaultInstance();
//		schedules = realm.where(Schedule.class).findAll();

		// load first fragment
		NewScheduleLanguageFragment fragment = new NewScheduleLanguageFragment();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fragmentContainer, fragment)
				.commit();
		currentPage = 0;
		toolbar.setTitle(titles[currentPage]);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				onBackPressed();
				break;
			case R.id.action_confirm:
				loadConfirmFragment();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void loadConfirmFragment() {
		NewScheduleConfirmFragment fragment = new NewScheduleConfirmFragment();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fragmentContainer, fragment)
				.addToBackStack(null)
				.commit();
	}
}
