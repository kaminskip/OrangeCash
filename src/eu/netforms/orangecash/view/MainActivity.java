package eu.netforms.orangecash.view;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import eu.netforms.orangecash.R;
import eu.netforms.orangecash.data.ClearData;
import eu.netforms.orangecash.data.PropertiesDataSource;
import eu.netforms.orangecash.data.UpdateData;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener, OnClickListener {

	PagesAdapter pagesAdapter;
	ViewPager viewPager;
	PropertiesDataSource propertiesDataSource;
	public static final int PLEASE_WAIT_DIALOG = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		propertiesDataSource = new PropertiesDataSource(getApplicationContext());
		setContentView(R.layout.main);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		pagesAdapter = new PagesAdapter(getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(pagesAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		viewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < pagesAdapter.getCount(); i++) {
			int codeName = ((PageFragment) pagesAdapter.getItem(i))
					.getFragmentCodeName();
			actionBar.addTab(actionBar.newTab().setText(codeName)
					.setTabListener(this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			Log.v("ACTION",
					"Select settings " + item.getItemId() + " ("
							+ item.getTitle() + ")");
			goToSettings();
			break;
		case R.id.action_update:
			Log.v("ACTION",
					"Select settings " + item.getItemId() + " ("
							+ item.getTitle() + ")");
			updateData();
			break;
		case R.id.action_clear:
			Log.v("ACTION",
					"Select settings " + item.getItemId() + " ("
							+ item.getTitle() + ")");
			clearData();
			break;
		default:
			Log.w("ACTION", "No action handled! " + item.getItemId() + " ("
					+ item.getTitle() + ")");
		}
		return super.onOptionsItemSelected(item);
	}

	private void updateData() {
		new UpdateData(this).execute("");
	}

	private void clearData() {
		new ClearData(this).execute("");
	}

	private void goToSettings() {
		startActivity(new Intent(this, SettingsActivity.class));
	}

	public void refresh() {
		this.finish();
		startActivity(new Intent(this, this.getClass()));
	}
	
	@Override
	public void onClick(View v) {
		System.out.println("ello");
		final int id = v.getId();
		switch (id) {
		case R.id.updateButton:
			new UpdateData(this).execute("");
			break;
		}
	}
}
