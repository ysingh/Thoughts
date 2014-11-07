package edu.ua.cs.thoughts.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.ua.cs.thoughts.R;
import edu.ua.cs.thoughts.activities.FeedActivity;
import edu.ua.cs.thoughts.adapter.ThoughtBaseAdapter;
import edu.ua.cs.thoughts.entities.Thought;

/**
 * Created by TaxMac on 10/16/14.
 */
public class ListFeedFragment extends ListFragment implements AdapterView.OnItemClickListener {


    ThoughtBaseAdapter thoughtBaseAdapter;

    ArrayList<Thought> testThoughts = new ArrayList<Thought>();
    Thought one = new Thought("Hello");
    Thought two = new Thought("Hello1");
    Thought three = new Thought("Hello2");

    String[] numbers_text = new String[] { "one", "two", "three", "four",
            "five", "six", "seven", "eight", "nine", "ten", "eleven",
            "twelve", "thirteen", "fourteen", "fifteen" };

    String[] numbers_digits = new String[] { "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "10", "11", "12", "13", "14", "15" };


    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ListFeedFragment newInstance() {
        ListFeedFragment fragment = new ListFeedFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, 1);
        fragment.setArguments(args);
        return fragment;
    }

    public ListFeedFragment() {
    }

    /* @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Context context = getActivity().getApplicationContext();
        CharSequence text = numbers_digits[(int) id].toString();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    } */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        testThoughts.add(one);
        testThoughts.add(two);
        testThoughts.add(three);

        thoughtBaseAdapter = new ThoughtBaseAdapter(getActivity(), testThoughts);
        setListAdapter(thoughtBaseAdapter);

        /* ArrayAdapter<String> adapter = new ArrayAdapter<String> (
                inflater.getContext(), android.R.layout.simple_list_item_1,
                numbers_text);
        setListAdapter(adapter); */
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((FeedActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getActivity(), (CharSequence) testThoughts.get(i).thoughtText, Toast.LENGTH_SHORT)
                .show();
    }
}
