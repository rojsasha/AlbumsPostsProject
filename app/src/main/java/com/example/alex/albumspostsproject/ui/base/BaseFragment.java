package com.example.alex.albumspostsproject.ui.base;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;

import com.example.alex.albumspostsproject.R;

public abstract class BaseFragment extends Fragment {
    private ProgressDialog mProgressDialog;

    protected void showProgressBar() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setMessage(getString(R.string.text_progressbar));
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }
    }

    protected void dismissProgressBar() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        mProgressDialog = null;
    }
}
