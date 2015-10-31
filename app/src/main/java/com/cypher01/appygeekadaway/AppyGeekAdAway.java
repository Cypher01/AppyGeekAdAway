package com.cypher01.appygeekadaway;

import android.app.Activity;
import android.os.Bundle;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedBridge.log;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;

/**
 * Created by Cypher01 on 5.10.2015.
 */
public class AppyGeekAdAway implements IXposedHookLoadPackage {
	private static final String TAG = AppyGeekAdAway.class.getSimpleName() + ": ";

	@Override
	public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
		if (!lpparam.packageName.equals("com.mobilesrepublic.appygeek")) {
			return;
		}

		log(TAG + "Appy Geek loaded");

		final XSharedPreferences pref = new XSharedPreferences(AppyGeekAdAway.class.getPackage().getName(), MainActivity.PREF);

		if(pref.getBoolean(MainActivity.REMOVEFULLSCREENADS, true)) {
			log(TAG + "Removing Fullscreen Ads");

			final Class<?> interstitialAdActivityClass = findClass("com.facebook.ads.InterstitialAdActivity", lpparam.classLoader);

			findAndHookMethod(interstitialAdActivityClass, "onCreate", Bundle.class, new XC_MethodHook() {
				@Override
				protected void afterHookedMethod(MethodHookParam param) throws Throwable {
					log(TAG + "onCreate(Bundle) in class InterstitialAdActivity hooked");
					Activity interstitialAdActivity = (Activity) param.thisObject;
					interstitialAdActivity.finish();
				}
			});

			findAndHookMethod(interstitialAdActivityClass, "onResume", new XC_MethodHook() {
				@Override
				protected void afterHookedMethod(MethodHookParam param) throws Throwable {
					log(TAG + "onResume() in class InterstitialAdActivity hooked");
					Activity interstitialAdActivity = (Activity) param.thisObject;
					interstitialAdActivity.finish();
				}
			});

			final Class<?> videoAdActivityClass = findClass("com.facebook.ads.VideoAdActivity", lpparam.classLoader);

			findAndHookMethod(videoAdActivityClass, "onCreate", Bundle.class, new XC_MethodHook() {
				@Override
				protected void afterHookedMethod(MethodHookParam param) throws Throwable {
					log(TAG + "onCreate(Bundle) in class VideoAdActivity hooked");
					Activity videoAdActivity = (Activity) param.thisObject;
					videoAdActivity.finish();
				}
			});

			findAndHookMethod(videoAdActivityClass, "onResume", new XC_MethodHook() {
				@Override
				protected void afterHookedMethod(MethodHookParam param) throws Throwable {
					log(TAG + "onResume() in class VideoAdActivity hooked");
					Activity videoAdActivity = (Activity) param.thisObject;
					videoAdActivity.finish();
				}
			});
		}

		if(pref.getBoolean(MainActivity.REMOVELISTADS, true)) {
			log(TAG + "Removing List Ads");

			// TODO: find and hook source in Appy Geek
		}
	}
}
