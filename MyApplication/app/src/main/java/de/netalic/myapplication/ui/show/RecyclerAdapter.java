package de.netalic.myapplication.ui.show;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import de.netalic.myapplication.R;
import de.netalic.myapplication.data.model.Speciality;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private List<Speciality> mData;
    private ItemClickListener mItemClickListener;
    private Context mContext;

    public RecyclerAdapter(Context context, List<Speciality> data){
        this.mData = data;
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = mInflater.inflate(R.layout.speciality_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.mId.setText(String.valueOf(mData.get(i).getId()));
        viewHolder.mTitle.setText(mData.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mId;
        private TextView mTitle;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mId = itemView.findViewById(R.id.textView_speciality_id);
            mTitle = itemView.findViewById(R.id.textView_speciality_title);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mItemClickListener.onItemClick(view, getAdapterPosition());
            LinearLayout mLayout = view.findViewById(R.id.layout_speciality);
            mLayout.setBackgroundColor(mContext.getResources().getColor(R.color.colorAccent));
            setClickListener(mItemClickListener);
        }
    }
    void setClickListener(ItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }
}
