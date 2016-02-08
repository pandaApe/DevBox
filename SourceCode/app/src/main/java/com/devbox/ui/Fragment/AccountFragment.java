package com.devbox.ui.Fragment;

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
import com.devbox.R;
import com.devbox.ui.Activity.CollectionActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by whailong on 15/1/16.
 */
public class AccountFragment extends Fragment {

    @Bind(R.id.cv_feedback)
    CardView cvFeedback;
    @Bind(R.id.cv_collction)
    CardView cvCollection;
    @Bind(R.id.iv_avatar)
    ImageView ivAvatar;
    @Bind(R.id.tv_nickName)
    TextView tvNickName;
    @Bind(R.id.tv_otherDetail)
    TextView tvOtherDetail;
    @Bind(R.id.iv_collection)
    ImageView ivCollection;

    public static AccountFragment newInstance(int num) {
        AccountFragment f = new AccountFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.cv_collction, R.id.cv_feedback})
    public void viewOnClick(View v) {
        switch (v.getId()) {
            case R.id.cv_collction:
                getActivity().startActivity(new Intent(getContext(), CollectionActivity.class));
                break;

            case R.id.cv_feedback:
                FeedbackAgent agent = new FeedbackAgent(getActivity());
                agent.startDefaultThreadActivity();

                break;

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
