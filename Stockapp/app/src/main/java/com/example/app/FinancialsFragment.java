package com.example.app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.covid19update.R;


public class FinancialsFragment extends Fragment {


    private TextView name, income, sales, gross_profit,eps,cfo,cfi,ncf,cff,fyy;


    public FinancialsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_financials, container,false);

        name  = view.findViewById(R.id.tv_name_fin);
        sales =view.findViewById(R.id.tv_revenue_fin);
        gross_profit =view.findViewById(R.id.tv_gp_fin);
        income = view.findViewById(R.id.tv_income_fin);
        eps =view.findViewById(R.id.tv_EPS_fin);
        cfo = view.findViewById(R.id.tv_CFO_fin);
        cfi = view.findViewById(R.id.tv_CFI_fin);
        ncf = view.findViewById(R.id.tv_NCF_fin);
        cff = view.findViewById(R.id.tv_CFF_fin);
        fyy = view.findViewById(R.id.fy_title);

        name.setText(StockActivity.stockModelList_fin.get(StockActivity.stockModelList_fin.size()-1).getName());
        sales.setText(billionConvertor(StockActivity.stockModelList_fin.get(StockActivity.stockModelList_fin.size()-1).getSales()) + " Bn");
        gross_profit.setText(billionConvertor(StockActivity.stockModelList_fin.get(StockActivity.stockModelList_fin.size()-1).getGrossProfit()) + " Bn");
        income.setText(billionConvertor(StockActivity.stockModelList_fin.get(StockActivity.stockModelList_fin.size()-1).getIncome()) + " Bn");
        eps.setText(StockActivity.stockModelList_fin.get(StockActivity.stockModelList_fin.size()-1).getEps());
        cfo.setText(billionConvertor(StockActivity.stockModelList_fin.get(StockActivity.stockModelList_fin.size()-1).getCfo()) + " Bn");
        cfi.setText(billionConvertor(StockActivity.stockModelList_fin.get(StockActivity.stockModelList_fin.size()-1).getCfi())+ " Bn");
        cff.setText(billionConvertor(StockActivity.stockModelList_fin.get(StockActivity.stockModelList_fin.size()-1).getCff()) + " Bn");
        ncf.setText(billionConvertor(StockActivity.stockModelList_fin.get(StockActivity.stockModelList_fin.size()-1).getNcf()) + " Bn");
        fyy.setText(StockActivity.stockModelList_fin.get(StockActivity.stockModelList_fin.size()-1).getFyy() + " in $USD");
        return view;
    }



    public int billionConvertor(String value) {
        float temp = Float.parseFloat(value);
        temp = temp / 1000000000;

        int int_temp = (int) temp;
        return int_temp;
    }











}