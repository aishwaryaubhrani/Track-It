package com.ibm.androidapp;


import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import android.webkit.WebView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public Timeout globalTimeout= new Timeout(60, TimeUnit.SECONDS);

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    private MainActivity activity = null;

    @Before
    public void setUp() throws Exception {
        activity = activityTestRule.getActivity();
    }


    @Test
    public void testLaunch() throws InterruptedException {
        final List<Boolean> succeededSet = new ArrayList<>(1);
        final WebView view = activity.findViewById(R.id.webView);
        // webView methods like getUrl and getJavaScriptEnabled cannot be called outside UI Thread
        // use view.post to make it run thread.
        view.post(new Runnable() {
            @Override
            public void run() {
                try {
                    assertEquals("file:///android_asset/www/index.html", view.getUrl());
                    succeededSet.add(true);
                } catch (Exception error) {
                    succeededSet.add(false);
                }
            }
        });
        while (succeededSet.size() < 1) {
            Thread.sleep(250);
        }
        assertNotNull(view);
        assertTrue(succeededSet.get(0));
    }

    @After
    public void tearDown() throws Exception {
        activity = null;
    }

}
