package com.example.myfirstapp;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {
	
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	public static String STATE_SCORE ="playerScore";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Set the home button as the Up button
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		//Ensure you're running on Honeycomb or higher
		//SDK_INT throws a runtime error for 2.0 or lower
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
			//Make the app icon behave as a button for main activity
			ActionBar actionBar = getActionBar();
//			actionBar.setHomeButtonEnabled(true);
			
		}
		
		//If you dont have any save data. The view was used previous
		//If you implement onRestoreInstantState you dont need to implement this check. As the function will do it for you
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		//Inflate the menu items for use in action bar
		MenuInflater inflater = getMenuInflater();	
		
		//Adds all the items declared in the resoure to menu
		inflater.inflate(R.menu.action_buttons, menu);
		
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch(item.getItemId()){
		case R.id.action_search:
			openSearch();
			return true;
		case R.id.action_settings:
			openSettings();
			return true;
			
			default:				
				return super.onOptionsItemSelected(item);
			
		
		}
		
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	
	public void sendMessage(View view){
		Intent intent = new Intent(this, DisplayMessageActivity.class);
	
		EditText editText = (EditText) findViewById(R.id.edit_message);
		String message = editText.getText().toString();
		
		intent.putExtra(EXTRA_MESSAGE, message);
		
		
		//Send the data
		startActivity(intent);
		
	}
	
	public void openSearch(){
		
		
	}
	public void openSettings(){
		
		
	}
	
	@Override
	protected void onStart(){
		super.onStart();
		Log.w("MyFirstApp", "Solomon: onStart called");
	
	}
	
	@Override
	protected void onStop(){
		super.onStop();
		Log.w("MyFirstApp", "Solomon: onStop called");
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState){	//When an activity gets distroyed you decide what you want to save
		
		
		savedInstanceState.putInt(STATE_SCORE, 1);
		
		//Always used the super.. so it works with view hierarchy
		super.onSaveInstanceState(savedInstanceState);
	}
	
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState){
		
		super.onRestoreInstanceState(savedInstanceState);
		
		int myCurrentScore = savedInstanceState.getInt(STATE_SCORE);
	}
}
