package com.example.cameratestapp;
// NOTE: NOT WORKING //
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


public class activityNext extends AppCompatActivity {

    TextView textViewTimeline;
    EditText editTextTweet, editTextUserName;
    Twitter twitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        editTextTweet = (EditText) findViewById(R.id.editTextTweet);
        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        textViewTimeline = (TextView) findViewById(R.id.textViewTimeline);
        textViewTimeline.setMovementMethod(new ScrollingMovementMethod());
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder
                .setOAuthConsumerKey("0000000000000000000000000")
                .setOAuthConsumerSecret("111111111111111111111111111111111111111111")
                .setOAuthAccessToken("222222222-33333333333333333333333333333333")
                .setOAuthAccessTokenSecret("4444444444444444444444444444444444444");
        TwitterFactory factory = new TwitterFactory(builder.build());
        twitter = factory.getInstance();
    }

    public void tweetButtonClick(View view) {
        new MyAsyncTaskTweet().execute(editTextTweet.getText().toString());
    }

    public void timelineButtonClick(View view) {
        new MyAsyncTaskTimeline().execute(editTextUserName.getText().toString());
    }


    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }


    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }


    public class MyAsyncTaskTweet extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... tweet) {
            String result = "";
            try {
                twitter.updateStatus(tweet[0]);
                result = "Success";
            } catch (TwitterException twitterException) {
                result = "Twitter Failure";
            } catch (Exception e) {
                result = "General Error";
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            editTextTweet.setHint(result);
            editTextTweet.setText("");
        }
    }

    public class MyAsyncTaskTimeline extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... username) {
            String result = new String("");
            List<twitter4j.Status> statuses = null;
            try {
                statuses = twitter.getUserTimeline(username[0]);
            } catch (TwitterException twitterException) {
                result = "Twitter Error";
            } catch (Exception e) {
                result = "General Error";
            }
            for (twitter4j.Status status : statuses) {
                result += status.getText();
                result += "\n";
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            editTextUserName.setText("");
            textViewTimeline.setText("");
        }
    }
}