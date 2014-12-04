package edu.ua.cs.thoughts.activities;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.Legend;
import com.github.mikephil.charting.utils.XLabels;
import com.github.mikephil.charting.utils.YLabels;

import java.util.ArrayList;

import edu.ua.cs.thoughts.R;


public class DisplayLineChartActivity extends Activity {

    private Typeface mTf;

    //Dummy Date Data
    protected String[] mMonths = new String[] {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.fragment_display_line_chart);

      //  mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Bold.ttf");

        //Get Dummy data
        LineData data = getData(36, 100);

        //Instantiate a Line chart
        LineChart chart = (LineChart) findViewById(R.id.chart);

        //Setup the chart
         setUpChart(chart, data, Color.rgb(137,230,81));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_line_chart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setUpChart(LineChart chart, LineData data, int color) {

        // if enabled, the chart will always start at zero on the y-axis
        chart.setStartAtZero(false);

        // Set display properties of values displayed on the chart
        chart.setValueTextColor(Color.BLUE);
        // disable the drawing of values into the chart
        chart.setDrawYValues(true);

        chart.setDrawBorder(false);

        // description text
        chart.setDescription("");
        chart.setNoDataTextDescription("No Thought data. Nothing to display.");

        // enable / disable grid lines
        chart.setDrawVerticalGrid(false);
        chart.setDrawHorizontalGrid(false);
        //
        // enable / disable grid background
        chart.setDrawGridBackground(false);
        chart.setGridColor(Color.WHITE & 0x70FFFFFF);
        chart.setGridWidth(1.25f);

        // enable touch gestures
        chart.setTouchEnabled(true);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(true);

        chart.setBackgroundColor(color);

        //chart.setValueTypeface(mTf);

        // add data
        chart.setData(data);

        // get the legend (only possible after setting data)
        Legend l = chart.getLegend();

        // modify the legend ...
        //l.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
        l.setForm(Legend.LegendForm.CIRCLE);
        l.setFormSize(6f);
        l.setTextColor(Color.WHITE);
        l.setTypeface(mTf);

        YLabels y = chart.getYLabels();
        y.setTextColor(Color.WHITE);
        y.setTypeface(mTf);
        y.setLabelCount(4);

        XLabels x = chart.getXLabels();
        x.setTextColor(Color.WHITE);
        x.setTypeface(mTf);

        // animate calls invalidate()...
        chart.animateX(2500);
    }


    //Dummy Data generator
    private LineData getData(int count, float range) {

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            xVals.add(mMonths[i % 12]);
        }

        ArrayList<Entry> yVals = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {
            float val = (float) ( Math.random() * range ) + .3f;
            yVals.add(new Entry(val, i));
        }

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(yVals, "Polarity Analysis");
        set1.setFillAlpha(110);
        set1.setFillColor(Color.RED);

        set1.setLineWidth(1.75f);
        set1.setCircleSize(5f);
        set1.setColor(Color.WHITE);
        set1.setCircleColor(Color.WHITE);
        set1.setHighLightColor(Color.WHITE);

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);

        return data;
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_display_line_chart, container, false);
            return rootView;
        }
    }
}
