package de.netalic.myapplication.ui.wallet;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.netalic.myapplication.R;
import de.netalic.myapplication.data.model.Wallet;

public class WalletRecyclerAdapter extends RecyclerView.Adapter<WalletRecyclerAdapter.ViewHolder>{

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<Wallet> mData;
    private TextView textView_name;
    private TextView textView_balance;
    private TextView textView_currency;
    private TextView textView_address;

    public WalletRecyclerAdapter(Context context, List<Wallet> data) {
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mData = data;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mLayoutInflater.inflate(R.layout.wallet_row_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        textView_name.setText("name: " + mData.get(i).getName());
        textView_currency.setText("currency code: " + mData.get(i).getCurrencyCode());
        textView_balance.setText(String.valueOf("balance: " + mData.get(i).getBalance()));
        textView_address.setText(String.valueOf("address: "+ mData.get(i).getAddress()));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View view ){
            super(view);
            textView_name = view.findViewById(R.id.textView_name_wallet);
            textView_balance = view.findViewById(R.id.textView_balance_wallet);
            textView_currency = view.findViewById(R.id.textView_currencyCode_wallet);
            textView_address = view.findViewById(R.id.textView_address_wallet);
        }
    }

}
