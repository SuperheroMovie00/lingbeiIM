package com.haitaoit.pinpai.tools;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.haitaoit.pinpai.R;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by Administrator on 2017/3/9.
 */

public class RxGlideLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        /**注意：
         1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
         2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
         传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
         切记不要胡乱强转！*/

        Glide.with(context).
                load(path).
                diskCacheStrategy(DiskCacheStrategy.RESULT).
                thumbnail(0.5f).
                placeholder(R.color.gainsboro).
                priority(Priority.LOW).
                error(R.color.gainsboro).
                fallback(R.color.gainsboro).
                dontAnimate().
                into(imageView);
    }
}
