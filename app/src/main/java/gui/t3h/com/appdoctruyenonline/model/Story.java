package gui.t3h.com.appdoctruyenonline.model;

import java.io.Serializable;

/**
 * Created by duyti on 7/6/2016.
 */
public class Story implements Serializable{
    private String title;
    private String content;
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
