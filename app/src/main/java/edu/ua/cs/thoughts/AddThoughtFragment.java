package edu.ua.cs.thoughts;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by TaxMac on 10/16/14.
 */
public class AddThoughtFragment extends Fragment {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    ThoughtsDataSource thoughtsDataSource;

    Button btnAddThought;
    EditText etEnterThought;

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
        thoughtsDataSource = new ThoughtsDataSource(this.getActivity());
        thoughtsDataSource.open();

        initializeViews();
    }

    private void initializeViews() {
        btnAddThought =  (Button) getActivity().findViewById(R.id.btnAddThought);
        etEnterThought = (EditText) getActivity().findViewById(R.id.etEnterThought);

        btnAddThought.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String enteredThought = etEnterThought.getText().toString();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_thought, container, false);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((FeedActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }


 }
