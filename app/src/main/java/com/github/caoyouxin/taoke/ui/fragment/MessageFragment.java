package com.github.caoyouxin.taoke.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.caoyouxin.taoke.R;
import com.github.caoyouxin.taoke.ui.activity.MessageActivity;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jasontsang on 10/24/17.
 */

public class MessageFragment extends Fragment {

    @BindView(R.id.global_msg)
    TextView globalMsg;

    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_message, container, false);
            ButterKnife.bind(this, rootView);

            if (savedInstanceState != null) {
                //restore
            }

            this.initAccountId();

            Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.app_not_release_hint, Snackbar.LENGTH_LONG).show();
        }
        return rootView;
    }

    private void initAccountId() {
        SpannableString span = new SpannableString("动态\n淘宝客新模式");
        span.setSpan(new RelativeSizeSpan(1.36f), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ForegroundColorSpan(Color.DKGRAY), 3, 9, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        globalMsg.setText(span);
    }

    @OnClick(R.id.global_msg)
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), MessageActivity.class);
        String text = this.globalMsg.getText().toString();
        intent.putExtra("title", text.substring(0, text.indexOf('\n')));
        getActivity().startActivity(intent);
    }
}
