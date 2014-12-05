package edu.ua.cs.thoughts.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

        RelativeLayout layout = (RelativeLayout) convertView.findViewById(R.id.layoutThoughtItem);

        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
        TextView txtDate = (TextView) convertView.findViewById(R.id.tvFeedDate);
        ImageView imgEmotion = (ImageView) convertView.findViewById(R.id.imgFeedIcon);

        Thought row_pos = thoughts.get(position);
        if (position % 2 == 0) {
            layout.setBackgroundColor(convertView.getResources().getColor(R.color.card_pressed));
        } else {
            layout.setBackgroundColor(convertView.getResources().getColor(R.color.card_activated));
        }

        if (row_pos.emotionType.equals("Happy")) {
            imgEmotion.setImageResource(R.drawable.happy);
        } else if (row_pos.emotionType.equals("Sad")) {
            imgEmotion.setImageResource(R.drawable.sad);
        }
        else if (row_pos.emotionType.equals("Contempt")) {
            imgEmotion.setImageResource(R.drawable.contempt);
        }
        else if (row_pos.emotionType.equals("Surprise")) {
            imgEmotion.setImageResource(R.drawable.surprise);
        }
        else if (row_pos.emotionType.equals("Angry")) {
            imgEmotion.setImageResource(R.drawable.angry);
        }
        else if (row_pos.emotionType.equals("Fear")) {
            imgEmotion.setImageResource(R.drawable.fear);
        } else {
            imgEmotion.setImageResource(R.drawable.question);
        }

        int snippetLength = 50;

        String thoughtSnippet = row_pos.thoughtText.substring(0, Math.min(row_pos.thoughtText.length(), snippetLength));

        if (row_pos.thoughtText.length() > snippetLength) {
            txtTitle.setText(thoughtSnippet + "...");
        } else {
            txtTitle.setText(row_pos.thoughtText);
        }

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = null;
        try {
            date = format.parse(row_pos.dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String time = (String) DateUtils.getRelativeDateTimeString(context, date.getTime(), DateUtils.MINUTE_IN_MILLIS, DateUtils.WEEK_IN_MILLIS, 0);

        txtDate.setText(time);

        return convertView;
    }

}
