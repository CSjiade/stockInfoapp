package com.example.app;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid19update.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ChartFragment extends Fragment {

    private String ticker;

    public ChartFragment(String ticker) {
        this.ticker = ticker;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        fetchGraphData(ticker, view, container);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void fetchGraphData(String ticker, View view, ViewGroup container) {
        LocalDate date = LocalDate.now();
        date = date.minusDays(1);
        LocalDate startdate  = date.minusYears(1);
        String url = "https://api.polygon.io/v2/aggs/ticker/" + ticker + "/range/1/day/" +
                startdate + "/" + date + "?adjusted=true&sort=asc&limit=300&apiKey=" + MainActivity.API_KEY;

        Context thiscontext = container.getContext();
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //we get our data here
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    List<Entry> entryList = new ArrayList<>();
                    LineChart lineChart = view.findViewById(R.id.chart);
                    float maxy = 0;
                    for(int i = 0; i<jsonArray.length();i++) {
                        String value = jsonArray.getJSONObject(i).getString("c");
                        Float floatValue = Float.parseFloat(value);
                        entryList.add(new Entry(i+1,floatValue));
                        maxy = Math.max(maxy,floatValue);
                    }
                    TextView chartHeading = view.findViewById(R.id.textchart);
                    chartHeading.setText(ticker + " YTD Chart");
                    LineDataSet lineDataSet = new LineDataSet(entryList,"Closing Price");
                    lineDataSet.setDrawValues(false);
                    lineDataSet.setColors(Color.parseColor("#9C27B0"));
                    lineDataSet.setFillAlpha(110);
                    lineDataSet.setDrawCircles(false);
                    LineData lineData = new LineData(lineDataSet);
                    lineChart.setData(lineData);
                    lineChart.setVisibleXRangeMaximum(jsonArray.length());
                    lineChart.setVisibleYRangeMaximum(maxy, YAxis.AxisDependency.LEFT);
                    lineChart.getAxisRight().setEnabled(false);
                    XAxis xAxis = lineChart.getXAxis();
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    lineChart.getXAxis().setEnabled(false);
                    lineChart.getDescription().setEnabled(false);
                    lineChart.invalidate();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(thiscontext , error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(thiscontext);
        requestQueue.add(request);
    }

}



