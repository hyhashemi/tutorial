package de.netalic.myapplication.ui.wallet;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import de.netalic.myapplication.R;
import de.netalic.myapplication.data.model.Wallet;

public class WalletRecyclerAdapter extends RecyclerView.Adapter<WalletRecyclerAdapter.ViewHolder>{

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<Wallet> mData;

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
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View view ){
            super(view);

        }
    }

}
