package edu.ua.cs.thoughts.adapter;

import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import edu.ua.cs.thoughts.R;
import edu.ua.cs.thoughts.entities.Thought;

/**
 * Created by vcaciuc on 11/7/2014.
 */
public class ThoughtAdapter extends ArrayAdapter<Thought> {

    Context mcontext;
    private Display display;


    public ThoughtAdapter(Context context, ArrayList<Thought> events) {
        super(context, R.layout.grid_cell_thought, events);
        mcontext = context;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = wm.getDefaultDisplay();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Thought thought = getItem(position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_view_feed, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.gridView.setNumColumns(1);

        return convertView;
    }



    static class ViewHolder {
        @InjectView(R.id.gridView)
        GridView gridView;
        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
