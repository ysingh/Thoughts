package edu.ua.cs.thoughts.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import edu.ua.cs.thoughts.R;
import edu.ua.cs.thoughts.activities.FeedActivity;
import edu.ua.cs.thoughts.database.DataSource;
import edu.ua.cs.thoughts.entities.Thought;
import edu.ua.cs.thoughts.interfaces.SingleThoughtInterface;

/**
 * A Fragment that presents a UI for the User
 * to enter a new Thought.
 *
 * Tarif Haque
 */

public class AddThoughtFragment extends Fragment {

    SingleThoughtInterface mCallBack;

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    Button btnAddThought;
    EditText etEnterThought;
    DataSource dataSource;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static AddThoughtFragment newInstance() {
        AddThoughtFragment fragment = new AddThoughtFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, 1);
        fragment.setArguments(args);
        return fragment;
    }

    public AddThoughtFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_add_thought, container, false);

        btnAddThought = (Button) rootView.findViewById(R.id.btnAddThought);
        etEnterThought = (EditText) rootView.findViewById(R.id.etEnterThought);
        dataSource = FeedActivity.dataSource;

        btnAddThought.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredThought = etEnterThought.getText().toString();
                Thought thought = dataSource.createThought(enteredThought, FeedActivity.username);
                mCallBack.launchThoughtFragment(thought);
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((FeedActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
        mCallBack = (SingleThoughtInterface) activity;

    }


 }
