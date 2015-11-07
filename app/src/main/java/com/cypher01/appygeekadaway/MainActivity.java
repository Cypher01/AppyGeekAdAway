package com.cypher01.appygeekadaway;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

/**
 * Created by Cypher01 on 5.10.2015.
 */
public class MainActivity extends AppCompatActivity {
	private static final String TAG = MainActivity.class.getSimpleName();

	public static final String PREF = "AGARpref";
	public static final String REMOVEFULLSCREENADS = "AGARremoveFullscreenAds";
	public static final String REMOVELISTADS = "AGARremoveListAds";

	private SharedPreferences pref;

	private CheckBox cb_removeFullscreenAds;
	private CheckBox cb_removeListAds;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setIcon(R.drawable.icon_32);
		getSupportActionBar().setTitle("  " + getString(R.string.app_name));

		pref = getSharedPreferences(PREF, MODE_WORLD_READABLE);

		cb_removeFullscreenAds = (CheckBox) findViewById(R.id.cb_removeFullscreenAds);
		cb_removeListAds = (CheckBox) findViewById(R.id.cb_removeListAds);

		cb_removeFullscreenAds.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
				if (pref.getBoolean(REMOVEFULLSCREENADS, true) != checked) {
					Toast.makeText(MainActivity.this, getString(R.string.toastRestart), Toast.LENGTH_LONG).show();
				}

				pref.edit().putBoolean(REMOVEFULLSCREENADS, checked).apply();
			}
		});

		cb_removeListAds.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
				if (pref.getBoolean(REMOVELISTADS, true) != checked) {
					Toast.makeText(MainActivity.this, getString(R.string.toastRestart), Toast.LENGTH_LONG).show();
				}

				pref.edit().putBoolean(REMOVELISTADS, checked).apply();
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		cb_removeFullscreenAds.setChecked(pref.getBoolean(REMOVEFULLSCREENADS, true));
//		cb_removeListAds.setChecked(pref.getBoolean(REMOVELISTADS, true)); // TODO: uncomment when removing List Ads finished
	}
}
