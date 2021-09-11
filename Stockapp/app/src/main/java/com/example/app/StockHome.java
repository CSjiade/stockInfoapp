package com.example.app;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StockHome extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private String ticker;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_home);
        bottomNavigationView = findViewById(R.id.bottomNav);
        Intent intent = getIntent();
        ticker = intent.getStringExtra("ticker");
        bottomNavigationView.setBackgroundColor(2);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNav);
        bottomNavigationView.setItemIconTintList(ColorStateList.valueOf(Color.parseColor("#29B6F6")));
        bottomNavigationView.setItemTextColor(ColorStateList.valueOf(Color.parseColor("#29B6F6")));
        getSupportActionBar().setTitle("Overview");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.containerStock,new ChartFragment(ticker)).commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNav = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment = null;
            switch (item.getItemId()) {

                case R.id.financials:
                    fragment = new FinancialsFragment();

                    break;

                case R.id.Home:
                    fragment = new HomeFragment(ticker);
                    break;

                case R.id.news:
                    fragment = new NewsFragment();
                    break;

                case R.id.stockchart:
                    fragment = new ChartFragment(ticker);
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.containerStock,fragment).commit();
            return true;
        }
    };



}