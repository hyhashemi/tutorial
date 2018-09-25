package de.netalic.myapplication.ui.show;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.netalic.myapplication.R;

public class ShowFragment extends Fragment {

    private View mRootView;
    private ShowPresenter mShowPresenter;
    private TextView mTextView_editText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_show, null);
        mShowPresenter = new ShowPresenter(this);
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTextView_editText = mRootView.findViewById(R.id.textView_show_editText);
        mTextView_editText.setText(getArguments().getString("output"));
    }

    public static ShowFragment newInstance(String mOutput) {

        Bundle args = new Bundle();
        args.putString("output", mOutput);
        ShowFragment fragment = new ShowFragment();
        fragment.setArguments(args);
        return fragment;
    }

}