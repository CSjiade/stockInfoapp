package com.example.app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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


public class MainActivity extends AppCompatActivity {
    TextView status,status2,status3,status4;
    Button btnstock, btnnews;
    public static List<MarketStatus> marketStatusList = new ArrayList<>();
    public static String API_KEY = "ENTER POLYGON API KEY";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("App");
        fetchFrontData();
        fetchGraphData();
        status = findViewById(R.id.status);
        status2 = findViewById(R.id.status2);
        status3 = findViewById(R.id.status3);
        status4 = findViewById(R.id.status4);
        btnstock = findViewById(R.id.btnstock);
        btnnews = findViewById(R.id.btnnews);


        btnstock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StockActivity.class);
                startActivity(intent);
            }
        });

        btnnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void fetchFrontData() {
        String url = "https://api.polygon.io/v1/marketstatus/now?&apiKey=" + API_KEY;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //we get our data here
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String nyse = jsonObject.getJSONObject("exchanges").getString("nyse");
                    String nas = jsonObject.getJSONObject("exchanges").getString("nasdaq");
                    String otc = jsonObject.getJSONObject("exchanges").getString("otc");
                    String fx = jsonObject.getJSONObject( "currencies").getString("fx");
                    String crypto = jsonObject.getJSONObject( "currencies").getString("crypto");

                    if(nas.startsWith("o")){
                        status.setTextColor(getResources().getColor(R.color.green));
                    }
                    else {
                        status.setTextColor(getResources().getColor(R.color.red));
                    }


                    if(nyse.startsWith("o")){
                        status2.setTextColor(getResources().getColor(R.color.green));
                    }
                    else {
                        status2.setTextColor(getResources().getColor(R.color.red));
                    }


                    if(otc.startsWith("o")){
                        status3.setTextColor(getResources().getColor(R.color.green));
                    }
                    else {
                        status3.setTextColor(getResources().getColor(R.color.red));
                    }

                    if(crypto.startsWith("o")){
                        status4.setTextColor(getResources().getColor(R.color.green));
                    }
                    else {
                        status4.setTextColor(getResources().getColor(R.color.red));
                    }
                    status.setText(nyse);
                    status2.setText(nas);
                    status3.setText(otc);
                    status4.setText(crypto);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    private void fetchGraphData() {
        LocalDate date = LocalDate.now();
        date = date.minusDays(1);
        LocalDate startdate  = date.minusYears(1);
        String tempTicker = "SPY";
        String url = "https://api.polygon.io/v2/aggs/ticker/" + tempTicker + "/range/1/day/" +
                startdate + "/" + date + "?adjusted=true&sort=asc&limit=300&apiKey=" + API_KEY;

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //we get our data here
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    List<Entry> entryList = new ArrayList<>();
                    LineChart lineChart = findViewById(R.id.chart);
                    float maxy = 0;
                    for(int i = 0; i<jsonArray.length();i++) {
                        String value = jsonArray.getJSONObject(i).getString("c");
                        Float floatValue = Float.parseFloat(value);
                        entryList.add(new Entry(i+1,floatValue));
                        maxy = Math.max(maxy,floatValue);
                    }
                    TextView chartHeading = findViewById(R.id.textchart);
                    chartHeading.setText(tempTicker + " YTD Chart");
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
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);
    }




}
