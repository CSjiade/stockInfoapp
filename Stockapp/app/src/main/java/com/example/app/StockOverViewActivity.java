package com.example.app;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.covid19update.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StockOverViewActivity extends AppCompatActivity {

    private int positionName;
    TextView tvname,tvdescription,tvprice,tvvolume,tvrevenue,tvgrossprofit,tvincome,
    tveps,tvcfo,tvcff,tvcfi,tvncf,tvmktcap,tvfy;
    ImageView imageviewlogo;

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_view);
        Intent intent = getIntent();
        positionName = intent.getIntExtra("position",0);
        getSupportActionBar().setTitle("Details of "+ StockActivity.stockModelList.get(positionName).getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imageviewlogo = findViewById(R.id.imageviewlogo);
        tvname = findViewById(R.id.tv_name);
        tvdescription = findViewById(R.id.tv_description);
        tvprice = findViewById(R.id.tv_price);
        tvvolume= findViewById(R.id.tv_volume);
        tvmktcap = findViewById(R.id.tv_mktcap);
        tvrevenue = findViewById(R.id.tv_revenue);
        tvgrossprofit= findViewById(R.id.tv_gp);
        tvincome = findViewById(R.id.tv_income);
        tveps = findViewById(R.id.tv_EPS);
        tvcfo = findViewById(R.id.tv_CFO);
        tvcff = findViewById(R.id.tv_CFF);
        tvcfi = findViewById(R.id.tv_CFI);
        tvncf = findViewById(R.id.tv_NCF);
        tvfy = findViewById(R.id.fy_title);

        tvname.setText(StockActivity.stockModelList.get(positionName).getName());
        tvdescription.setText(StockActivity.stockModelList.get(positionName).getDescription());
        tvvolume.setText(StockActivity.stockModelList.get(positionName).getVolume());
        tvrevenue.setText(StockActivity.stockModelList.get(positionName).getSales());
        tvgrossprofit.setText(StockActivity.stockModelList.get(positionName).getGrossProfit());
        tvincome.setText(StockActivity.stockModelList.get(positionName).getIncome());
        tveps.setText(StockActivity.stockModelList.get(positionName).getEps());
        tvfy.setText(StockActivity.stockModelList.get(positionName).getFyy());
        Glide.with(getApplicationContext()).load(StockActivity.stockModelList.get(positionName).getLogo()).into(imageviewlogo);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
        LocalDate localDate = LocalDate.now();
        localDate = localDate.minusDays(1);
        tvprice.setText(dtf.format(localDate) + " " + StockActivity.stockModelList.get(positionName).getPrice());
        tvmktcap.setText(StockActivity.stockModelList.get(positionName).getMktcap());
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}