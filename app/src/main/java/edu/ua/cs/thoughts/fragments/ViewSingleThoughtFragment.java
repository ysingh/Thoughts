package edu.ua.cs.thoughts.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import edu.ua.cs.thoughts.R;
import edu.ua.cs.thoughts.entities.Thought;

public class ViewSingleThoughtFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    TextView tvThought;
    Button btnEmotion;
    Button btnPolarity;

    Thought thought;


    public static ViewSingleThoughtFragment newInstance(Thought thought) {
        ViewSingleThoughtFragment fragment = new ViewSingleThoughtFragment();
        Bundle args = new Bundle();
        args.putParcelable("thought", thought);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_view_single_thought, container, false);

        if (getArguments().containsKey("thought")) {
            thought = (Thought) getArguments().getParcelable("thought");
        }

        tvThought = (TextView) rootView.findViewById(R.id.tvThought);
        btnEmotion = (Button) rootView.findViewById(R.id.bEmotion);
        btnPolarity = (Button) rootView.findViewById(R.id.bPolarity);

        FillOutUI(thought.thoughtText);

        return rootView;
    }

    private void FillOutUI(String message){
        tvThought.setText(message);
        btnEmotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Do stuff
            }
        });
        btnPolarity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Do stuff
            }
        });
    }
}
