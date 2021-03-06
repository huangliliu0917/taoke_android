package com.github.caoyouxin.taoke.datasource;

import android.content.Context;

import com.github.caoyouxin.taoke.model.CouponItem;
import com.github.caoyouxin.taoke.model.ShareImage;
import com.github.gnastnosaj.boilerplate.mvchelper.RxDataSource;
import com.shizhefei.mvc.IDataCacheLoader;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.internal.operators.observable.ObservableJust;


public class ShareImageDataSource extends RxDataSource<List<ShareImage>> implements IDataCacheLoader<List<ShareImage>> {
    private CouponItem couponItem;

    public ShareImageDataSource(Context context, CouponItem couponItem) {
        super(context);

        this.couponItem = couponItem;
    }

    @Override
    public Observable<List<ShareImage>> refresh() throws Exception {
        List<ShareImage> shareImages = new ArrayList<>();
        List<String> thumbs = new ArrayList<>();
        if (null != this.couponItem.getSmallImages()) {
            thumbs.addAll(this.couponItem.getSmallImages());
        }
        thumbs.add(0, this.couponItem.getPictUrl());
        for (String thumb : thumbs) {
            ShareImage shareImage = new ShareImage();
            shareImage.thumb = thumb;
            shareImages.add(shareImage);
        }
        shareImages.get(0).selected = true;
        return new ObservableJust<>(shareImages);
    }

    @Override
    public Observable<List<ShareImage>> loadMore() throws Exception {
        return null;
    }

    @Override
    public boolean hasMore() {
        return false;
    }

    @Override
    public List<ShareImage> loadCache(boolean isEmpty) {
        return null;
    }
}