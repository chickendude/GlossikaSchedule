package ch.ralena.glossikaschedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import ch.ralena.glossikaschedule.data.LanguageType;
import ch.ralena.glossikaschedule.data.ScheduleData;
import ch.ralena.glossikaschedule.data.ScheduleType;
import ch.ralena.glossikaschedule.fragment.NewScheduleConfirmFragment;
import ch.ralena.glossikaschedule.fragment.NewScheduleLanguageFragment;
import io.realm.Realm;

public class NewScheduleActivity extends AppCompatActivity {
	private static final String TAG = NewScheduleActivity.class.getSimpleName();

	// views
	private Toolbar toolbar;
	// objects
	private Realm realm;
	private int currentPage;

	// results passed back in
	public LanguageType selectedLanguage;
	public ScheduleType selectedSchedule;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_schedule);

		// load toolbar
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		// prepare realm object
		realm = Realm.getDefaultInstance();

		// load first fragment
		NewScheduleLanguageFragment fragment = new NewScheduleLanguageFragment();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fragmentContainer, fragment)
				.commit();
		currentPage = 0;
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
				.commit();
	}

	public void updateLanguage(LanguageType language) {
		selectedLanguage = language;
	}

	public void updateSchedule(ScheduleType schedule) {
		selectedSchedule = schedule;
	}

	public void createSchedule(String scheduleTitle) {
		ScheduleData.createSchedule(realm, selectedSchedule, selectedLanguage, scheduleTitle);
		Intent intent = new Intent(this, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivity(intent);
	}

}
