package com.oscode.ui.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.feedback.FeedbackAgent;
import com.oscode.R;
import com.oscode.ui.Activity.CDActivity;


/**
 * Created by whailong on 15/1/16.
 */
public class AccountFragment extends Fragment implements View.OnClickListener {

    private CardView cvFeedback;
    private CardView cvCollection;
    private ImageView ivAvatar;
    private TextView tvNickName;

    public static AccountFragment newInstance(int num) {
        AccountFragment f = new AccountFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvNickName = (TextView) view.findViewById(R.id.tv_nickName);
        cvCollection = (CardView) view.findViewById(R.id.cv_collction);

        cvFeedback = (CardView) view.findViewById(R.id.cv_feedback);
        cvCollection.setOnClickListener(this);
        cvFeedback.setOnClickListener(this);

        ivAvatar = (ImageView) view.findViewById(R.id.iv_avatar);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_collction:
                getActivity().startActivity(new Intent(getContext(), CDActivity.class));
                break;

            case R.id.cv_feedback:
                FeedbackAgent agent = new FeedbackAgent(getActivity());
                agent.startDefaultThreadActivity();

                break;

        }
    }
}
