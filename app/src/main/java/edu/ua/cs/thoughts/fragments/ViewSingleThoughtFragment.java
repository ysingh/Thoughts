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

import butterknife.ButterKnife;
import butterknife.InjectView;
import edu.ua.cs.thoughts.R;
import edu.ua.cs.thoughts.entities.Thought;

public class ViewSingleThoughtFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    @InjectView(R.id.tvThought)
    TextView tvThought;
    @InjectView(R.id.bEmotion)
    Button bEmotion;
    @InjectView(R.id.bPolarity)
    Button bPolarity;

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

        ButterKnife.inject(this, rootView);

        FillOutUI(thought.thoughtText);

        return rootView;
    }

    private void FillOutUI(String message){
        tvThought.setText(message);
        bEmotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Do stuff
            }
        });
        bPolarity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Do stuff
            }
        });
    }
}
