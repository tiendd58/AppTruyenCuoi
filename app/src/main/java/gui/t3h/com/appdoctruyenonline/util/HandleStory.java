package gui.t3h.com.appdoctruyenonline.util;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import gui.t3h.com.appdoctruyenonline.model.Story;
import gui.t3h.com.appdoctruyenonline.model.Topic;

/**
 * Created by duyti on 7/6/2016.
 */
public class HandleStory {
    private static final String LINK_SITE = "http://truyencuoi.vn";
    private String link;
    private ArrayList<Story> listStories;
    private ArrayList<Topic> listTopics;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ArrayList<Story> getListStories() {
        return listStories;
    }

    public boolean parseStory(){
        listStories = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(link).get();
            Elements titles = doc.select(".view-content h2");
            for (Element title : titles) {
                Story story = new Story();
                story.setTitle(title.text().toString());
                story.setPath(LINK_SITE+title.select("a").attr("href"));
                listStories.add(story);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Topic> getListTopics() {
        return listTopics;
    }

    public void parseTopic(){
        listTopics = new ArrayList<>();
        try {
            //#block-views-the-loai-truyen-block .field-content
            Document doc = Jsoup.connect(link).get();
            Elements titles = doc.select("#block-views-the-loai-truyen-block .field-content");
            for (Element title : titles) {
                Topic topic = new Topic();
                topic.setTitle(title.text().toString());
                topic.setPath(link+title.select("a").attr("href").toString());
                listTopics.add(topic);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String parseContentStory(){
        try {
            Log.i("Link",link);
            Document doc = Jsoup.connect(link).get();
            Elements content = doc.select(".field-item");
            return content.text().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
