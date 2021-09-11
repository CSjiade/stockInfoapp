package com.example.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.example.covid19update.R;

import java.util.ArrayList;
import java.util.List;


public class StockAdapter extends ArrayAdapter<StockModel> {


    private Context context;
    private List<StockModel> stockModelList;
    private List<StockModel> stockModellistFiltered;


    public StockAdapter(Context context, List<StockModel> stockModelList) {
        super(context, R.layout.custom_stockview, stockModelList);
        this.context = context;
        this.stockModelList = stockModelList;
        this.stockModellistFiltered = stockModelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_stockview, null, true);
        TextView companyname = view.findViewById(R.id.txtname);
        ImageView companyimage = view.findViewById(R.id.imagecompany);

        companyname.setText(stockModellistFiltered.get(position).getName());
        Glide.with(context).load(stockModellistFiltered.get(position).getLogo()).into(companyimage);
        return view;
    }

    @Override
    public int getCount() {
        return stockModellistFiltered.size();
    }

    @Nullable
    @Override
    public StockModel getItem(int position) {
        return stockModellistFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                if(constraint == null || constraint.length() == 0){
                    filterResults.count = stockModelList.size();
                    filterResults.values = stockModelList;

                }else{
                    List<StockModel> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for(StockModel itemsModel:stockModelList){
                        if(itemsModel.getName().toLowerCase().contains(searchStr)){
                            resultsModel.add(itemsModel);

                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }


                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                stockModellistFiltered = (List<StockModel>) results.values;
                StockActivity.stockModelList = (List<StockModel>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }
}
