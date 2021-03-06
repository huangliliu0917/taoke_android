package com.github.caoyouxin.taoke.util;

import android.util.Base64;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.gnastnosaj.boilerplate.ui.activity.BaseActivity;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import timber.log.Timber;

import static com.github.gnastnosaj.boilerplate.ui.activity.BaseActivity.DYNAMIC_BOX_MK_LINESPINNER;


public class MyWebViewClient extends WebViewClient {

    private static final String HACK_CSS_PATH = "hack.css";
    private BaseActivity context;
    private boolean hacked = false;

    public MyWebViewClient(BaseActivity context) {
        this.context = context;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
//        if (!hacked) {
        hack(view);
//            hacked = true;
//            System.out.println("hacked");
//        }
//        System.out.println(url);
    }

    private void hack(WebView view) {
        try {
            InputStream inputStream = context.getAssets().open(HACK_CSS_PATH);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            String encoded = Base64.encodeToString(buffer, Base64.NO_WRAP);
//            System.out.println(encoded);
            view.loadUrl("javascript:(function() {" +
                    "var parent = document.getElementsByTagName('body').item(0);" +
                    "var hacked = document.getElementById('hackCss');" +
                    "if (hacked) { parent.removeChild(hacked); }" +
                    "var style = document.createElement('style');" +
                    "style.id = 'hackCss';" +
                    "style.type = 'text/css';" +
                    "style.innerHTML = window.atob('" + encoded + "');" +
                    "parent.appendChild(style);" +
//                    "alert(style.innerHTML);" +
                    "})()");
        } catch (Exception e) {
            Timber.e(e, "injectCSS exception");
        }

        context.showDynamicBoxCustomView(DYNAMIC_BOX_MK_LINESPINNER, context);
        Observable.timer(5, TimeUnit.SECONDS).compose(context.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe((delay) -> context.dismissDynamicBox(context));
    }

}
