package de.netalic.myapplication.ui.show;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import de.netalic.myapplication.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView mId;
    public TextView mTitle;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        mId = itemView.findViewById(R.id.textView_speciality_id);
        mTitle = itemView.findViewById(R.id.textView_speciality_title);

    }
}
