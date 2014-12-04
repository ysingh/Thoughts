package edu.ua.cs.thoughts.activities;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.interfaces.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Legend;
import com.github.mikephil.charting.utils.Legend.LegendPosition;

import java.util.ArrayList;

import edu.ua.cs.thoughts.R;
import edu.ua.cs.thoughts.entities.Thought;

public class PiechartActivity extends FragmentActivity implements OnChartValueSelectedListener {

    protected String[] mParties = new String[] {
            "Sad", "Happy", "Anxious", "Surprised", "Something", "Something1"
    };

    private ArrayList<String> mValues = new ArrayList<String>();

    ArrayList<Thought> thoughts;
    private PieChart mChart;

    // Pass in this key when you send the intent to launch the PieChartActivity
    private String THOUGHTS_KEY = "thoughts";

    ArrayList<Float> s = new ArrayList<Float>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        thoughts = getIntent().getParcelableArrayListExtra(THOUGHTS_KEY);
//        mValues = calculatePercentage(thoughts);

        // Get a string array list of percentages associated with each type of emotion
        s = calculatePercentage();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_piechart);


        mChart = (PieChart) findViewById(R.id.chart1);

        // change the color of the center-hole
        mChart.setHoleColor(Color.rgb(235, 235, 235));

        mChart.setHoleRadius(60f);

        mChart.setDescription("");

        mChart.setDrawYValues(true);
        mChart.setDrawCenterText(true);

        mChart.setDrawHoleEnabled(true);

        mChart.setRotationAngle(0);

        // draws the corresponding description value into the slice
        mChart.setDrawXValues(true);

        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);

        // display percentage values
        mChart.setUsePercentValues(true);

        // add a selection listener
        mChart.setOnChartValueSelectedListener(this);

        mChart.setCenterText("MPAndroidChart\nLibrary");

        setData(mParties.length, s.size());

        mChart.animateXY(1500, 1500);

        Legend l = mChart.getLegend();
        l.setPosition(LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);

    }

    //        private ArrayList<String> calculatePercentage(ArrayList<Thought> thoughts) {
    private ArrayList<Float> calculatePercentage() {
        float sad = 0;
        float happy = 0;
        float anxious = 0;
        float surprised = 0;
        float five = 0;
        float six = 0;
        float total = 0;

        ArrayList<Float>  temp = new ArrayList<Float>();
//        for(int i = 0; i < thoughts.size(); i++){
//            if(thoughts.get(i).emotion.toLowerCase().contains("sad")){sad += 1;}
//            if(thoughts.get(i).emotion.toLowerCase().contains("happy")){happy += 1;}
//            if(thoughts.get(i).emotion.toLowerCase().contains("anxious")){anxious += 1;}
//            if(thoughts.get(i).emotion.toLowerCase().contains("surprised")){surprised += 1;}
//            if(thoughts.get(i).emotion.toLowerCase().contains("something")){something += 1;}
//            if(thoughts.get(i).emotion.toLowerCase().contains("something1")){something1 += 1;}
//        }

        // Fake data
        ArrayList<String> t = new ArrayList<String>();
        t.add(0, "sad");
        t.add(1, "sad");
        t.add(2, "sad");
        t.add(3, "sad");
        t.add(4, "sad");
        t.add(5, "happy");
        t.add(6, "anxious");
        t.add(7, "surprised");
        t.add(8, "five");
        t.add(9, "six");

        for(int i = 0; i < t.size(); i++){
            if(t.get(i).toLowerCase().contains("sad")){sad += 1;}
            if(t.get(i).toLowerCase().contains("happy")){happy += 1;}
            if(t.get(i).toLowerCase().contains("anxious")){anxious += 1;}
            if(t.get(i).toLowerCase().contains("surprised")){surprised += 1;}
            if(t.get(i).toLowerCase().contains("five")){five += 1;}
            if(t.get(i).toLowerCase().contains("six")){six += 1;}
        }
        //




        total = sad + happy + anxious + surprised + five + six;
        temp.add(((sad/total) * 100));
        temp.add(((happy/total) * 100));
        temp.add(((anxious/total) * 100));
        temp.add(((surprised/total) * 100));
        temp.add(((five/total) * 100));
        temp.add(((six/total) * 100));
        return temp;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.actionToggleValues: {
                if (mChart.isDrawYValuesEnabled())
                    mChart.setDrawYValues(false);
                else
                    mChart.setDrawYValues(true);
                mChart.invalidate();
                break;
            }
            case R.id.actionTogglePercent: {
                if (mChart.isUsePercentValuesEnabled())
                    mChart.setUsePercentValues(false);
                else
                    mChart.setUsePercentValues(true);
                mChart.invalidate();
                break;
            }
            case R.id.actionToggleHole: {
                if (mChart.isDrawHoleEnabled())
                    mChart.setDrawHoleEnabled(false);
                else
                    mChart.setDrawHoleEnabled(true);
                mChart.invalidate();
                break;
            }
            case R.id.actionDrawCenter: {
                if (mChart.isDrawCenterTextEnabled())
                    mChart.setDrawCenterText(false);
                else
                    mChart.setDrawCenterText(true);
                mChart.invalidate();
                break;
            }
            case R.id.actionToggleXVals: {
                if (mChart.isDrawXValuesEnabled())
                    mChart.setDrawXValues(false);
                else
                    mChart.setDrawXValues(true);
                mChart.invalidate();
                break;
            }
            case R.id.actionSave: {
                // mChart.saveToGallery("title"+System.currentTimeMillis());
                mChart.saveToPath("title" + System.currentTimeMillis(), "");
                break;
            }
            case R.id.animateX: {
                mChart.animateX(1800);
                break;
            }
            case R.id.animateY: {
                mChart.animateY(1800);
                break;
            }
            case R.id.animateXY: {
                mChart.animateXY(1800, 1800);
                break;
            }
        }
        return true;
    }

    private void setData(int count, float range) {

        float mult = range;

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
        for (int i = 0; i < count; i++) {
            yVals1.add(new Entry(s.get(i), i));
        }

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < count ; i++)
            xVals.add(mParties[i % mParties.length]);

        PieDataSet set1 = new PieDataSet(yVals1, "Election Results");
        set1.setSliceSpace(3f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        set1.setColors(colors);

        PieData data = new PieData(xVals, set1);
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex) {

        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getVal() + ", xIndex: " + e.getXIndex()
                        + ", DataSet index: " + dataSetIndex);
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }
}