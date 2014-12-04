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
import edu.ua.cs.thoughts.database.DataSource;
import edu.ua.cs.thoughts.entities.Thought;

public class PiechartActivity extends FragmentActivity implements OnChartValueSelectedListener {

    protected String[] mParties = new String[]{
            "Sad", "Happy", "Fear", "Surprise", "Contempt", "Angry"
    };
    private PieChart mChart;
    DataSource dataSource;
    ArrayList<Thought> thoughtsList;
    protected ArrayList<String> thoughts;

    ArrayList<Float> percentages = new ArrayList<Float>();

    @Override
    protected void onResume() {
        dataSource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        dataSource.close();
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thoughts = new ArrayList<String>();
        dataSource = new DataSource(this);
        dataSource.open();
        thoughtsList = dataSource.getAllThoughts();
        for (int i = 0; i < thoughtsList.size(); i++) {
            thoughts.add(thoughtsList.get(i).emotionType);
        }

        // Get a string array list of percentages associated with each type of emotion
        percentages = calculatePercentage(thoughts);

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
        mChart.setCenterText("Emotional Breakdown");

        setData(thoughts.size(), percentages.size());
        mChart.animateXY(1500, 1500);

        Legend l = mChart.getLegend();
        l.setPosition(LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);

    }

    private ArrayList<Float> calculatePercentage(ArrayList<String> thoughts) {
        float sad = 0;
        float happy = 0;
        float fear = 0;
        float surprised = 0;
        float contempt = 0;
        float angry = 0;
        float total = 0;

        ArrayList<Float> temp = new ArrayList<Float>();
        for (int i = 0; i < thoughts.size(); i++) {
            if (thoughts.get(i).toLowerCase().contains("sad")) {
                sad += 1;
            }
            if (thoughts.get(i).toLowerCase().contains("happy")) {
                happy += 1;
            }
            if (thoughts.get(i).toLowerCase().contains("fear")) {
                fear += 1;
            }
            if (thoughts.get(i).toLowerCase().contains("surprised")) {
                surprised += 1;
            }
            if (thoughts.get(i).toLowerCase().contains("contempt")) {
                contempt += 1;
            }
            if (thoughts.get(i).toLowerCase().contains("angry")) {
                angry += 1;
            }
        }

        total = sad + happy + fear + surprised + contempt + angry;
        temp.add(((sad / total) * 100));
        temp.add(((happy / total) * 100));
        temp.add(((fear / total) * 100));
        temp.add(((surprised / total) * 100));
        temp.add(((contempt / total) * 100));
        temp.add(((angry / total) * 100));
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
        for (int i = 0; i < mParties.length; i++) {
            yVals1.add(new Entry(percentages.get(i), i));
        }

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < mParties.length; i++) {
            xVals.add(mParties[i]);
        }

        ArrayList<Entry> yVals2 = new ArrayList<Entry>();
        ArrayList<String> xVals2 = new ArrayList<String>();
        for (int i = 0; i < yVals1.size(); i++) {
            if (yVals1.get(i).getVal() > (0.0f)) {
                yVals2.add(yVals1.get(i));
                xVals2.add(xVals.get(i));
            }
        }

        PieDataSet set1 = new PieDataSet(yVals2, "Emotions");
        set1.setSliceSpace(3f);

        // add a lot of colors
        ArrayList<Integer> colors = new ArrayList<Integer>();

        colors.add(ColorTemplate.getHoloBlue());
        colors.add(Color.MAGENTA);
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.DKGRAY);
        colors.add(Color.CYAN);
        colors.add(Color.LTGRAY);

        set1.setColors(colors);

        PieData data = new PieData(xVals2, set1);
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