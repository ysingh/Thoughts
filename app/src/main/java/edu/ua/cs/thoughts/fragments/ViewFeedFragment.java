package edu.ua.cs.thoughts.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import edu.ua.cs.thoughts.R;
import edu.ua.cs.thoughts.activities.FeedActivity;
import edu.ua.cs.thoughts.adapter.ThoughtAdapter;
import edu.ua.cs.thoughts.entities.Thought;

/**
 * Created by TaxMac on 10/16/14.
 */


public class ViewFeedFragment extends ListFragment {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    GridView list;
    ThoughtAdapter adapter;
    ArrayList<Thought> mThought;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ViewFeedFragment newInstance() {
        ViewFeedFragment fragment = new ViewFeedFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, 1);
        fragment.setArguments(args);
        return fragment;
    }

    public ViewFeedFragment() {
    }

    public void setThoughtsArrayList(final ArrayList<Thought> thoughts) {
        mThought = thoughts;
    }

    public void updateList() {
        try {
            adapter.clear();
            adapter.addAll(mThought);
            adapter.notifyDataSetChanged();
        }catch (NullPointerException e){

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("key", mThought);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view_feed, container, false);
        list = (GridView) rootView.findViewById(R.id.gridView);

        ArrayList<Thought> testThoughts = new ArrayList<Thought>();
        Thought one = new Thought("Hello");
        Thought two = new Thought("Hello1");
        Thought three = new Thought("Hello2");
        testThoughts.add(one);
        testThoughts.add(two);
        testThoughts.add(three);
        setThoughtsArrayList(testThoughts);
        updateList();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new ThoughtAdapter(getActivity(), mThought);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
//                Intent intent = new Intent(getActivity(), ViewSingleThought.class);
//                Thought m = mThought.get(pos);
//                intent.putExtra("key", m.thoughtText);
//                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.activityfadein, R.anim.abc_fade_out);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.add(ViewSingleThought.newInstance(), null);
                ft.commit();
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((FeedActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
