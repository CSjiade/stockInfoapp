package com.example.app;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid19update.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StockActivity extends AppCompatActivity {

    public static List<StockModel> stockHomeList = new ArrayList<>();
    EditText editsearch;
    public static List<StockModel> stockModelList_fin = new ArrayList<>();
    public static List<StockNewsModel> stockModelNews_fin = new ArrayList<>();
    public static List<StockModel> stockModelList = new ArrayList<>();



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
        getSupportActionBar().setTitle("US Stocks Only");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        editsearch = findViewById(R.id.editSearch_stock);

        editsearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    fetchHomeData(editsearch.getText().toString().toUpperCase());
                    fetchFinData(editsearch.getText().toString().toUpperCase());
                    fetchNewsData(editsearch.getText().toString().toUpperCase());
                    startActivity(new Intent(getApplicationContext(), StockHome.class).putExtra("ticker", editsearch.getText().toString().toUpperCase()));
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }



private void fetchHomeData(String stock_ticker) {


    String url = "https://api.polygon.io/v1/meta/symbols/" + stock_ticker  + "/company?&apiKey=" + MainActivity.API_KEY;

    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            //we get our data here
            try {
                JSONObject jsonObject = new JSONObject(response);
                String description = jsonObject.getString("description");
                String symbol = jsonObject.getString("symbol");
                String name = jsonObject.getString("name");
                String mktcap = jsonObject.getString("marketcap");
                String sector = jsonObject.getString("sector");
                String exchange = jsonObject.getString("exchange");
                String listDate = jsonObject.getString("listdate");
                StockModel stockModel = new StockModel(name,symbol,sector,description,mktcap,exchange,listDate);
                stockHomeList.add(stockModel);


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(StockActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });

    RequestQueue requestQueue = Volley.newRequestQueue(StockActivity.this);
    requestQueue.add(request);



}



private void fetchFinData(String stock_ticker) {
        stock_ticker = stock_ticker.toUpperCase();
        String url = "https://api.polygon.io/vX/reference/financials?ticker=" +
                stock_ticker + "&timeframe=annual&limit=2&sort=period_of_report_date&apiKey=" + MainActivity.API_KEY;

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //we get our data here
                try {
                    JSONObject jsonObject3 = new JSONObject(response);
                    JSONArray result = jsonObject3.getJSONArray("results");

                    JSONObject jsonObject_zero = result.getJSONObject(0);

                    JSONObject jsonObject_income = jsonObject_zero.getJSONObject("financials").getJSONObject("income_statement");

                    JSONObject jsonObject_rev = jsonObject_income.getJSONObject("revenues");
                    String sales = jsonObject_rev.getString("value");

                    JSONObject jsonObject_gp = jsonObject_income.getJSONObject("gross_profit");
                    String grossProfit = jsonObject_gp.getString("value");

                    JSONObject jsonObject_inc = jsonObject_income.getJSONObject("net_income_loss_attributable_to_parent");
                    String income = jsonObject_inc.getString("value");


                    JSONObject jsonObject_eps = jsonObject_income.getJSONObject("basic_earnings_per_share");
                    String eps = jsonObject_eps.getString("value");


                    JSONObject jsonObject_cf = jsonObject_zero.getJSONObject("financials").getJSONObject("cash_flow_statement");

                    JSONObject jsonObject_cfo = jsonObject_cf.getJSONObject("net_cash_flow_from_operating_activities");
                    String cfo = jsonObject_cfo.getString("value");


                    JSONObject jsonObject_cfi = jsonObject_cf.getJSONObject("net_cash_flow_from_investing_activities");
                    String cfi = jsonObject_cfi.getString("value");

                    JSONObject jsonObject_cff = jsonObject_cf.getJSONObject("net_cash_flow_from_financing_activities");
                    String cff = jsonObject_cff.getString("value");

                    JSONObject jsonObject_ncf = jsonObject_cf.getJSONObject("net_cash_flow");
                    String ncf = jsonObject_ncf.getString("value");
                    String fy = jsonObject_zero.getString("fiscal_period");
                    String year = jsonObject_zero.getString("fiscal_year");
                    String fyy = fy + " " + year;

                    String name = jsonObject_zero.getString("company_name");

                    StockModel stockModel = new StockModel(name, sales, grossProfit, income, eps, cfo, cfi, cff, ncf, fyy);
                    stockModelList_fin.add(stockModel);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(StockActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(StockActivity.this);
        requestQueue.add(request);
    }


private void fetchNewsData(String stock_ticker) {

        stockModelNews_fin.clear();

        stock_ticker = stock_ticker.toUpperCase();
        String url = "https://api.polygon.io/v2/reference/news?limit=10&order=descending&sort=published_utc&ticker="+ stock_ticker +
                "&apiKey=" + MainActivity.API_KEY;

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject3 = new JSONObject(response);
                    JSONArray result = jsonObject3.getJSONArray("results");
                    System.out.println(result);

                    for(int i =0; i<result.length(); i++) {

                        JSONObject jsonObject = result.getJSONObject(i);
                        String author = jsonObject.getJSONObject("publisher").getString("name");
                        String title = jsonObject.getString("title");
                        String pubDate = jsonObject.getString("published_utc");
                        String url = jsonObject.getString("article_url");
                        String imageUrl;
                        try {
                            imageUrl = jsonObject.getString("image_url");
                        }
                        catch (JSONException e) {
                            imageUrl = "src/main/res/drawable-v24/black.png";
                        }
                        String description;
                        try {
                             description = jsonObject.getString("description");
                        } catch (JSONException e) {
                            description = " ";
                        }

                        StockNewsModel stockNewsModel = new StockNewsModel(author, title, pubDate, url, imageUrl, description);
                        stockModelNews_fin.add(stockNewsModel);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(StockActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(StockActivity.this);
        requestQueue.add(request);
    }



}