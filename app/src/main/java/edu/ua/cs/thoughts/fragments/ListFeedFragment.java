package edu.ua.cs.thoughts.fragments;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.ua.cs.thoughts.activities.FeedActivity;
import edu.ua.cs.thoughts.adapter.ThoughtBaseAdapter;
import edu.ua.cs.thoughts.entities.Thought;
import edu.ua.cs.thoughts.interfaces.FeedInterface;

/**
 * Created by TaxMac on 10/16/14.
 */
public class ListFeedFragment extends ListFragment implements AdapterView.OnItemClickListener {


    ThoughtBaseAdapter thoughtBaseAdapter;

    ArrayList<Thought> testThoughts = new ArrayList<Thought>();
    Thought one = new Thought("Hello");
    Thought two = new Thought("Hello1");
    Thought three = new Thought("Hello2");

    FeedInterface mCallBack;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        testThoughts.add(one);
        testThoughts.add(two);
        testThoughts.add(three);

        thoughtBaseAdapter = new ThoughtBaseAdapter(getActivity(), testThoughts);
        setListAdapter(thoughtBaseAdapter);


        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setOnItemClickListener(this);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((FeedActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
        mCallBack = (FeedInterface) activity;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getActivity(), testThoughts.get(i).thoughtText, Toast.LENGTH_SHORT).show();
        mCallBack.switchFragment(testThoughts.get(i));
    }
}
