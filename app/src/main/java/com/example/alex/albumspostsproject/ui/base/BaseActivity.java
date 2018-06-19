package com.example.alex.albumspostsproject.ui.base;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.alex.albumspostsproject.R;

public abstract class BaseActivity extends AppCompatActivity {
    ProgressDialog mProgressDialog;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    protected void showProgressBar() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
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

    protected void initToolbar(boolean flag) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.text_toolbar);
        if (getSupportActionBar() != null && flag){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }



}
