package com.pa.devbox.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pa.devbox.R;
import com.pa.devbox.ui.aty.CollectionActivity;

/**
 * Description:
 *
 * Author: PandaApe.
 * CreatedAt: 15/1/16 11:39.
 * Email: whailong2010@gmail.com
 */
public class AccountFragment extends Fragment {


    CardView cvFeedback;

    CardView cvCollection;

    ImageView ivAvatar;

    TextView tvNickName;

    TextView tvOtherDetail;

    ImageView ivCollection;

    public static AccountFragment newInstance(int num) {
        AccountFragment f = new AccountFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
//        ButterKnife.bind(this, view);
        return view;
    }

    //    @OnClick({R.id.cv_collction, R.id.cv_feedback})
    public void viewOnClick(View v) {
        switch (v.getId()) {
            case R.id.cv_collction:
                getActivity().startActivity(new Intent(getContext(), CollectionActivity.class));
                break;

            case R.id.cv_feedback:

                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        ButterKnife.unbind(this);
    }

    @Override
    public void onResume() {

//        if (User.getCurrentUser() != null) {
//            this.tvNickName.setText(User.getCurrentUser().getString("nickName"));
//
//        } else {
//            this.tvNickName.setText("点击登录");
//        }
        super.onResume();
    }

}
