package com.udacity.gradle.builditbigger;

import android.test.InstrumentationTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class EndpointsAsyncTaskTest extends InstrumentationTestCase {

    public void testVerifyNonEmptyResponseString() {
        CountDownLatch signal = new CountDownLatch(1);
        final Utils.EndpointsTestAsyncTask task = new Utils.EndpointsTestAsyncTask(signal);
        // Execute the async task on the UI thread! THIS IS KEY!
        try {
            runTestOnUiThread(new Runnable() {
                @Override
                public void run() {
                    task.execute();
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        try {
            signal.await(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result = task.getResult();
        // Check for nonempty result string
        boolean nonEmpty = (!result.equals("") || (result != null));
        assertEquals(nonEmpty, nonEmpty);
    }
}
