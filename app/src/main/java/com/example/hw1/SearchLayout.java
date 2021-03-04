package com.example.hw1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class SearchLayout extends LinearLayout {
    private static final String TAG = "SearchLayout";

    private EditText mEditView;
    private TextView mCancel;
    private OnSearchTextChangedListener mListener;

    public SearchLayout(Context context) {
        super(context);
        initView();
    }

    public SearchLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SearchLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.layout_search, this);

        mEditView = findViewById(R.id.edit);
        mCancel = findViewById(R.id.cancel);
        ImageView mImageView = findViewById(R.id.image);

        mImageView.setImageResource(R.drawable.icon_search);

        mEditView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged (CharSequence s,int start, int count, int after){
                Log.i(TAG, "beforeTextChanged: " + s);
            }

            @Override
            public void onTextChanged (CharSequence s,int start, int before, int count){
                Log.i(TAG, "onTextChanged: " + s);
            }

            @Override
            public void afterTextChanged (Editable s){
                Log.i(TAG, "afterTextChanged: " + s);
                if (mListener != null) {
                    mListener.afterChanged(s.toString());
                }
            }
        });

        //接下去的部分有所修改，Demo中是直接finish当前活动，此处改成清空搜索框。

        mCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Demo中语句如下
                // ((Activity) getContext()).finish();
                mEditView.setText("");

            }
        });
    }





    public void setOnSearchTextChangedListener(OnSearchTextChangedListener listener) {
        mListener = listener;
    }

    public interface OnSearchTextChangedListener {

        void afterChanged(String text);


    }


}
