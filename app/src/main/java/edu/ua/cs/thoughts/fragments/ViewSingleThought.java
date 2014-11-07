package edu.ua.cs.thoughts.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import edu.ua.cs.thoughts.R;

public class ViewSingleThought extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    @InjectView(R.id.tvThought)
    TextView tvThought;
    @InjectView(R.id.bEmotion)
    Button bEmotion;
    @InjectView(R.id.bPolarity)
    Button bPolarity;

    private String message;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        setTheme(PreferenceManager.getDefaultSharedPreferences(this).getInt("theme", android.R.style.Theme_Holo));
//        super.onCreate(savedInstanceState);
//        ActionBarRefresher();
//        setContentView(R.layout.fragment_view_single_thought);
//        ButterKnife.inject(this);
//
//        Intent i =  getIntent();
//        message = i.getStringExtra("key");
//        FillOutUI(message);
//    }

    public static ViewSingleThought newInstance() {
        ViewSingleThought fragment = new ViewSingleThought();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
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
