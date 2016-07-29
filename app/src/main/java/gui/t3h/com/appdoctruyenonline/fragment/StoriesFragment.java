package gui.t3h.com.appdoctruyenonline.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import gui.t3h.com.appdoctruyenonline.R;
import gui.t3h.com.appdoctruyenonline.activity.ContentStoryActivity;
import gui.t3h.com.appdoctruyenonline.activity.MainActivity;
import gui.t3h.com.appdoctruyenonline.adapter.AdapterListStories;
import gui.t3h.com.appdoctruyenonline.model.Story;
import gui.t3h.com.appdoctruyenonline.util.HandleStory;

/**
 * Created by duyti on 7/6/2016.
 */
public class StoriesFragment extends android.support.v4.app.Fragment implements AdapterView.OnItemClickListener{
    public static final String KEY_STORY = "KEY_STORY";
    private String linkStory = "";
    private ArrayList<Story> listStories = new ArrayList<>();
    private HandleStory handleStory;
    private ListView listView;
    private AdapterListStories listAdapter;
    private View rootView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_list_story,container,false);
        //xu ly du lieu de de cho vao doi tuong story
        getUrl();
        handleStory = new HandleStory();
        handleStory.setLink(linkStory);
        //do du lieu vao listview
        listView = (ListView) rootView.findViewById(R.id.list_stories);
        listAdapter = new AdapterListStories(getActivity().getApplicationContext());
        MyAsyntask myAsyntask = new MyAsyntask();
        myAsyntask.execute();
        listView.setOnItemClickListener(this);
        return rootView;
    }

    public void getUrl() {
        Bundle bundle = getArguments();
        linkStory = bundle.getString(MainActivity.KEY_LINK);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(getActivity().getApplicationContext(), ContentStoryActivity.class);
        intent.putExtra(KEY_STORY, listStories.get(position));
        startActivity(intent);
    }

    private class MyAsyntask extends AsyncTask<Void, Void, Boolean>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            if(handleStory.parseStory()){
                return true;
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(aBoolean){
                listStories.addAll(handleStory.getListStories());
                listAdapter.initData(listStories);
                listView.setAdapter(listAdapter);
            }
        }
    }
}
