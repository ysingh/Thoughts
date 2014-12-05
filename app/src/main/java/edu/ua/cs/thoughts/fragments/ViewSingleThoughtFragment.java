package edu.ua.cs.thoughts.fragments;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import edu.ua.cs.thoughts.R;
import edu.ua.cs.thoughts.activities.FeedActivity;
import edu.ua.cs.thoughts.asynctasks.RetrieveThoughtPolarityTask;
import edu.ua.cs.thoughts.database.DataSource;
import edu.ua.cs.thoughts.entities.Thought;
import edu.ua.cs.thoughts.interfaces.DataDownloadListener;

public class ViewSingleThoughtFragment extends Fragment {

    View rootView;
    TextView tvThought, tvPolarity, tvDateTime;
    ImageView imgEmotion;
    Button btnPolarity;

    Thought thought;
    Document document;
    DataSource dataSource;


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
        rootView = inflater.inflate(R.layout.fragment_view_single_thought, container, false);

        setActionBarTitle("Single Thought View");

        if (getArguments().containsKey("thought")) {
            thought = (Thought) getArguments().getParcelable("thought");
        }

        initializeViews();

        tvPolarity.setText(thought.getPolarityPercentage() + "% " + thought.getPolarityClass());

        setDateTime(thought.dateTime);

        dataSource = FeedActivity.dataSource;

        setEmotionImage(thought.emotionType);

        tvThought.setText(thought.thoughtText);

        btnPolarity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                analyzeThoughtForPolarity();
            }
        });

        return rootView;
    }

    /**
     * Set the correct emotion image in the Emotion ImageView.
     */
    private void setEmotionImage(String emotionType) {

        if (emotionType.equals("Happy")) {
            imgEmotion.setImageResource(R.drawable.happy);
        } else if (emotionType.equals("Sad")) {
            imgEmotion.setImageResource(R.drawable.sad);
        }
        else if (emotionType.equals("Contempt")) {
            imgEmotion.setImageResource(R.drawable.contempt);
        }
        else if (emotionType.equals("Surprise")) {
            imgEmotion.setImageResource(R.drawable.surprise);
        }
        else if (emotionType.equals("Angry")) {
            imgEmotion.setImageResource(R.drawable.angry);
        }
        else if (emotionType.equals("Fear")) {
            imgEmotion.setImageResource(R.drawable.fear);
        } else {
            imgEmotion.setImageResource(R.drawable.question);
        }
    }

    private void initializeViews() {
        tvThought = (TextView) rootView.findViewById(R.id.tvThought);
        tvThought.setMovementMethod(new ScrollingMovementMethod());
        btnPolarity = (Button) rootView.findViewById(R.id.btnPolarity);
        tvPolarity = (TextView) rootView.findViewById(R.id.tvPolarity);
        imgEmotion = (ImageView) rootView.findViewById(R.id.imgEmotion);

    }

    /**
     * Sets the title in the Action Bar.
     */
    private void setActionBarTitle(String title) {
        ActionBar ab = getActivity().getActionBar();
        ab.setTitle(title);
    }

    /**
     * Set date and time in respective Text View.
     */
    private void setDateTime(String dateTime) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = null;
        try {
            date = format.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateFormat newFormat = new SimpleDateFormat("EEE, d MMM yyyy hh:mm aaa", Locale.getDefault());
        String printDate = newFormat.format(date);

        tvDateTime = (TextView) rootView.findViewById(R.id.tvDateTime);
        tvDateTime.setText(printDate);
    }

    private void analyzeThoughtForPolarity() {

        RetrieveThoughtPolarityTask task = new RetrieveThoughtPolarityTask();
        task.setDataDownloadListener(new DataDownloadListener()
        {
            @SuppressWarnings("unchecked")
            @Override
            public void dataDownloadedSuccessfully(Object data) {
                document = (Document) data;


                NodeList list = document.getElementsByTagName("docSentiment");


                for (int i = 0; i < list.getLength(); i++) {
                    Node nNode = list.item(i);

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element eElement = (Element) nNode;
                        String type = eElement.getElementsByTagName("type").item(0).getTextContent();
                        String score = eElement.getElementsByTagName("score").item(0).getTextContent();


                        thought.setPolarity(Float.parseFloat(score));
                        tvPolarity.setText(thought.getPolarityPercentage() + "% " + thought.getPolarityClass());
                        dataSource.addPolarityToThought(thought.thoughtID, Float.parseFloat(score));
                    }
                }

            }

            @Override
            public void dataDownloadFailed() {
                // handler failure (e.g network not available etc.)
            }
        });

        task.execute(thought);


    }


}
