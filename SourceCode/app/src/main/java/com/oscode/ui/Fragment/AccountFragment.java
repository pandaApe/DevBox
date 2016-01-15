package com.oscode.ui.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by whailong on 15/1/16.
 */
public class AccountFragment extends Fragment {
    int mNum;

    /**
     * Create a new instance of CountingFragment, providing "num"
     * as an argument.
     */
    public static AccountFragment newInstance(int num) {
        AccountFragment f = new AccountFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        f.setArguments(args);

        return f;
    }


}
