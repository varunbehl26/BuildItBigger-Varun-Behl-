package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.example.varunbehl.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;


public class Utils {
    public static class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
        private MyApi myApiService = null;

        @Override
        protected String doInBackground(Void... params) {
            if (myApiService == null) {
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/");
                myApiService = builder.build();
            }
            try {
                return myApiService.getJoke().execute().getData();
            } catch (IOException e) {
                return e.getMessage();
            }
        }
    }

    // Async Task for testing the EndpointsAsyncTask, onPostExecute overrridden to obtain the raw result
    public static class EndpointsTestAsyncTask extends EndpointsAsyncTask {
        private CountDownLatch signal;
        private String result;

        public EndpointsTestAsyncTask(CountDownLatch signal) {
            this.signal = signal;
        }

        @Override
        protected void onPostExecute(String result) {
            this.result = result;
            // Alerts that task is finished
            signal.countDown();
        }

        public String getResult() {
            return result;
        }
    }
}
