package com.example.app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.covid19update.R;


public class HomeFragment extends Fragment {


    private String ticker;
    private TextView tvfhname,tvfhsector,tvfhdescription,tvfhmktcap,tvfhexchange,tvfhlistdate,tvfsymbol;


    public HomeFragment(String ticker) {

        this.ticker = ticker;
        // Required empty public constructor
    }



    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        tvfhname  = view.findViewById(R.id.tv_fh_name);
        tvfhsector =view.findViewById(R.id.tv_fh_sector);
        tvfhdescription =view.findViewById(R.id.tv_fh_description);
        tvfhmktcap =view.findViewById(R.id.tv_fh_mktcap);
        tvfhexchange = view.findViewById(R.id.tv_fh_exchange);
        tvfhlistdate = view.findViewById(R.id.tv_fh_listdate);
        tvfsymbol = view.findViewById(R.id.tv_fh_symbol);


        tvfhname.setText(StockActivity.stockHomeList.get(StockActivity.stockHomeList.size()-1).getName());
        tvfhsector.setText(StockActivity.stockHomeList.get(StockActivity.stockHomeList.size()-1).getSector());
        tvfhdescription.setText(StockActivity.stockHomeList.get(StockActivity.stockHomeList.size()-1).getDescription());
        tvfhexchange.setText(StockActivity.stockHomeList.get(StockActivity.stockHomeList.size()-1).getExchange());
        tvfhlistdate.setText(StockActivity.stockHomeList.get(StockActivity.stockHomeList.size()-1).getListDate());
        tvfsymbol.setText(StockActivity.stockHomeList.get(StockActivity.stockHomeList.size()-1).getSymbol());
        float mktcap = Float.parseFloat(StockActivity.stockHomeList.get(StockActivity.stockHomeList.size()-1).getMktcap());


        mktcap =  mktcap/1000000000;
        int cap = (int) mktcap;
        tvfhmktcap.setText(" "+ cap + " Bn");

        return view;
        //return inflater.inflate(R.layout.fragment_home, container, false);

    }

}

//    private void fetchHomeData(String stock_ticker) {
//
//        String API_KEY = "GSYkwV9gDh3OrwtlDM75szDxvcdAs_bP";
//        String url = "https://api.polygon.io/v1/meta/symbols/" + stock_ticker +
//                "/company?&apiKey=" + API_KEY;
//
//        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//                //we get our data here
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    String description = jsonObject.getString("description");
//                    String symbol = jsonObject.getString("symbol");
//                    String name = jsonObject.getString("name");
//                    String mktcap = jsonObject.getString("marketcap");
//                    String sector = jsonObject.getString("sector");
//                    String exchange = jsonObject.getString("exchange");
//                    String listDate = jsonObject.getString("listdate");
//                    StockModel stockModel = new StockModel(name,symbol,sector,description,mktcap,exchange,listDate);
//                    stockHomeList.add(stockModel);
//                    simpleArcLoader.stop();
//                    simpleArcLoader.setVisibility(View.GONE);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    simpleArcLoader.stop();
//                    simpleArcLoader.setVisibility(View.GONE);
//                }
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                simpleArcLoader.stop();
//                simpleArcLoader.setVisibility(View.GONE);
//                //Toast.makeText((Context) HomeFragment, error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
////        RequestQueue requestQueue = Volley.newRequestQueue((Context) HomeFragment);
////        requestQueue.add(request);
//    }


//    private void fetchFinancialData() {
//        String url = "https://corona.lmao.ninja/v2/countries/";
//        simpleArcLoader.start();
//
//        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//                //we get our data here
//                try {
//                    JSONArray jsonArray = new JSONArray(response);
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonObject = jsonArray.getJSONObject(i);
//                        String countryname = jsonObject.getString("country");
//                        String cases = jsonObject.getString("cases");
//                        String todaycases = jsonObject.getString("todayCases");
//                        String deaths = jsonObject.getString("deaths");
//                        String todaydeaths = jsonObject.getString("todayDeaths");
//                        String recovered = jsonObject.getString("recovered");
//                        String active = jsonObject.getString("active");
//                        String critical = jsonObject.getString("critical");
//
//                        JSONObject object = jsonObject.getJSONObject("countryInfo");
//                        String flagurl = object.getString("flag");
//
//                        countryModel = new CountryModel(flagurl, countryname, cases, todaycases, deaths, todaydeaths, recovered, active, critical);
//                        countryModelList.add(countryModel);
//                    }
//                    myCustomAdapter = new MyCustomAdapter(CountryActivity.this, countryModelList);
//                    listView.setAdapter(myCustomAdapter);
//                    simpleArcLoader.stop();
//                    simpleArcLoader.setVisibility(View.GONE);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    simpleArcLoader.stop();
//                    simpleArcLoader.setVisibility(View.GONE);
//                }
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                simpleArcLoader.stop();
//                simpleArcLoader.setVisibility(View.GONE);
//                Toast.makeText(CountryActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(request);
//    }
