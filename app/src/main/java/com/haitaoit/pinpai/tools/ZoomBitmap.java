package com.haitaoit.pinpai.tools;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;

public class ZoomBitmap {

    public static Bitmap zoomImage(Bitmap bgimage, double newWidth, double newHeight) {
        // 获取到bitmap的宽
        float width = bgimage.getWidth();

        float height = bgimage.getHeight();
        //
        Matrix matrix = new Matrix();
        // 设置尺寸
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
                (int) height, matrix, true);
        Log.e("tag", bitmap.getHeight() + bitmap.getWidth() + "d");
        return bitmap;
    }
}
