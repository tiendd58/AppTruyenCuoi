package gui.t3h.com.appdoctruyenonline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import gui.t3h.com.appdoctruyenonline.R;
import gui.t3h.com.appdoctruyenonline.model.Story;

/**
 * Created by duyti on 7/6/2016.
 */
public class AdapterListStories extends BaseAdapter {

    ArrayList<Story> list = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private ViewHolder viewHolder;


    public AdapterListStories(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    public void initData(ArrayList<Story> list){
        this.list.addAll(list);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Story getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        if(itemView==null){
            itemView=layoutInflater.inflate(R.layout.item_story,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvStory= (TextView) itemView.findViewById(R.id.tv_title);
        }else{
            viewHolder = (ViewHolder) itemView.getTag();
        }
        viewHolder.tvStory.setText(list.get(position).getTitle()+"");
        itemView.setTag(viewHolder);
        return itemView;
    }

    private class ViewHolder {
        TextView tvStory;
    }

}
