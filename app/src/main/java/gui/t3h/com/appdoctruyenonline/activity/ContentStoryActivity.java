package gui.t3h.com.appdoctruyenonline.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import gui.t3h.com.appdoctruyenonline.R;
import gui.t3h.com.appdoctruyenonline.fragment.StoriesFragment;
import gui.t3h.com.appdoctruyenonline.model.Story;
import gui.t3h.com.appdoctruyenonline.util.HandleStory;

/**
 * Created by duyti on 7/8/2016.
 */
public class ContentStoryActivity extends AppCompatActivity{
    private Toolbar mToolbar;

    private TextView title, content;
    private HandleStory handleStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_story);
        Log.i("act 2", "test");
        setUpToolbar();
        initView();
    }

    private void initView() {
        title = (TextView) findViewById(R.id.tv_title_story);
        content = (TextView) findViewById(R.id.tv_content_story);
        Story story = (Story) getIntent().getSerializableExtra(StoriesFragment.KEY_STORY);
        title.setText(story.getTitle()+"");
        handleStory = new HandleStory();
        handleStory.setLink(story.getPath());
        MyAsyntask myAsyntask = new MyAsyntask();
        myAsyntask.execute();
    }

    private void setUpToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
    }

    private class MyAsyntask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            return handleStory.parseContentStory();
        }

        @Override
        protected void onPostExecute(String s) {
            content.setText(s+"");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
