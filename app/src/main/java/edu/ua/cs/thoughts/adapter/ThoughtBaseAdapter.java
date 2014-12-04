package edu.ua.cs.thoughts.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import edu.ua.cs.thoughts.R;
import edu.ua.cs.thoughts.entities.Thought;

/**
 * Created by vcaciuc on 11/7/2014.
 */
public class ThoughtBaseAdapter extends BaseAdapter {

    Context context;
    List<Thought> thoughts;

    public ThoughtBaseAdapter(Context context, List<Thought> thoughts) {
        this.context = context;
        this.thoughts = thoughts;
    }

    @Override
    public int getCount() {
        return thoughts.size();
    }

    @Override
    public Thought getItem(int i) {
        return thoughts.get(i);
    }

    @Override
    public long getItemId(int position) {
        return thoughts.indexOf(getItem(position));
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_item_thought, null);
        }

        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
        TextView txtDate = (TextView) convertView.findViewById(R.id.tvFeedDate);

        Thought row_pos = thoughts.get(position);
        txtTitle.setText(row_pos.thoughtText);
        txtDate.setText(row_pos.dateTime);

        return convertView;
    }

}
