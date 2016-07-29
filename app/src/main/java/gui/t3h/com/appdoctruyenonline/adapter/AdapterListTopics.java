package gui.t3h.com.appdoctruyenonline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import gui.t3h.com.appdoctruyenonline.R;
import gui.t3h.com.appdoctruyenonline.model.Topic;

/**
 * Created by duyti on 7/7/2016.
 */
public class AdapterListTopics extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<Topic> listTopic = new ArrayList<>();
    private ViewHolder viewHolder;

    public AdapterListTopics(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public void initData(ArrayList<Topic> list){
        listTopic.addAll(list);
    }

    @Override
    public int getCount() {
        return listTopic.size();
    }

    @Override
    public Topic getItem(int position) {
        return listTopic.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        if(itemView==null){
            itemView = inflater.inflate(R.layout.item_topic,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvTitle = (TextView) itemView.findViewById(R.id.tv_title_topic);
        }else{
            viewHolder = (ViewHolder) itemView.getTag();
        }
        viewHolder.tvTitle.setText(listTopic.get(position).getTitle()+"");
        itemView.setTag(viewHolder);
        return itemView;
    }

    private class ViewHolder{
        TextView tvTitle;
    }
}
