package de.netalic.myapplication.ui.wallet;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
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
    private int mSelectedItem = -1;

    public int getSelectedItem() {
        return mSelectedItem;
    }

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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.textView_name.setText("name: " + mData.get(i).getName());
        viewHolder.textView_balance.setText(String.valueOf("balance: " + mData.get(i).getBalance()));

        if (mSelectedItem >= 0 && i == mSelectedItem) {
            viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(viewHolder.itemView.getContext(), R.color.colorAccent));
        } else {
            viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(viewHolder.itemView.getContext(), android.R.color.white));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView_name;
        private TextView textView_balance;

        public ViewHolder(View itemView ){
            super(itemView);
            textView_name = itemView.findViewById(R.id.textView_name_wallet);
            textView_balance = itemView.findViewById(R.id.textView_balance_wallet);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mSelectedItem = getAdapterPosition();
                    notifyDataSetChanged();
                    return true;
                }
            });

        }
    }
}
