package gui.t3h.com.appdoctruyenonline.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import gui.t3h.com.appdoctruyenonline.R;
import gui.t3h.com.appdoctruyenonline.adapter.AdapterListTopics;
import gui.t3h.com.appdoctruyenonline.adapter.AdapterPageTopic;
import gui.t3h.com.appdoctruyenonline.fragment.StoriesFragment;
import gui.t3h.com.appdoctruyenonline.model.Topic;
import gui.t3h.com.appdoctruyenonline.util.HandleStory;



public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    public static final String  KEY_LINK = "KEY_LINK";
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;

    private HandleStory handleStory;
    private AdapterListTopics adapterTopic;
    private ListView lvTopic;
    ArrayList<Topic> listTopic = new ArrayList<>();

    ViewPager viewPager;
    private TabLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setUpToolbar();
        initView();
    }

    private void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.nav_drawer);
        //set topic
        lvTopic = (ListView) findViewById(R.id.lv_list_topic);
        lvTopic.setOnItemClickListener(this);
        handleStory = new HandleStory();
        handleStory.setLink("http://truyencuoi.vn");
        adapterTopic = new AdapterListTopics(this);

        viewPager = (ViewPager) findViewById(R.id.content);
        MyAsyntask myAsyntask = new MyAsyntask();
        myAsyntask.execute();

    }

    private void setUpViewPager(ViewPager viewPager) {
        AdapterPageTopic adapter = new AdapterPageTopic(getSupportFragmentManager()) ;
        ArrayList<String> listTitleTopic = new ArrayList<>();
        for (Topic topic:listTopic ) {
            listTitleTopic.add(topic.getTitle());
            StoriesFragment fragment = new StoriesFragment();
            Bundle bundle = new Bundle();
            bundle.putString(KEY_LINK, topic.getPath());
            fragment.setArguments(bundle);
            adapter.addFrag(fragment, topic.getTitle());
            Log.i("Title", topic.getTitle());
        }
        viewPager.setAdapter(adapter);
        tab = (TabLayout) findViewById(R.id.tabs);
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        tab.setupWithViewPager(viewPager);
    }

    private void setUpToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationIcon(R.drawable.ic_drawer);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        viewPager.setCurrentItem(position);
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    private class MyAsyntask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            handleStory.parseTopic();
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(aBoolean){
                listTopic.addAll(handleStory.getListTopics());
                adapterTopic.initData(listTopic);
                lvTopic.setAdapter(adapterTopic);
                setUpViewPager(viewPager);
            }
        }
    }
}
