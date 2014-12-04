package edu.ua.cs.thoughts.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;


import edu.ua.cs.thoughts.R;
import edu.ua.cs.thoughts.asynctasks.RetrieveThoughtPolarityTask;
import edu.ua.cs.thoughts.entities.Thought;
import edu.ua.cs.thoughts.interfaces.DataDownloadListener;

public class ViewSingleThoughtFragment extends Fragment {

    TextView tvThought, tvPolarity, tvEmotion;
    Button btnEmotion;
    Button btnPolarity;

    Thought thought;
    Document document;

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
        tvPolarity = (TextView) rootView.findViewById(R.id.tvPolarity);
        tvEmotion = (TextView) rootView.findViewById(R.id.tvEmotion);

        FillOutUI(thought.thoughtText);

        return rootView;
    }

    private void FillOutUI(String message){
        tvThought.setText(message);

        btnPolarity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                analyzeThoughtForPolarity();
            }
        });

        btnEmotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Do stuff
            }
        });
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

                        tvPolarity.setText("Polarity: " + score + " " + type);

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
