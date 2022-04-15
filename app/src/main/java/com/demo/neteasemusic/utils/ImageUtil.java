package com.demo.neteasemusic.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;

public class ImageUtil {

    public static Bitmap image2RoundBitMap(Resources res,int resId, /*int outWidth, int outHeight,*/ int radius){
        //获取资源信息
        Bitmap bitmap = BitmapFactory.decodeResource(res,resId);
        Bitmap targetBitmap = null;
        if(bitmap != null){
            //设置缩放比
//            float widthScale = outWidth * 1.0f / bitmap.getWidth();
//            float heightScale = outHeight * 1.0f / bitmap.getHeight();
//            Matrix matrix = new Matrix();
//            matrix.setScale(widthScale,heightScale);

            //初始化缩放比纹理图
            BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            //根据控件大小对纹理图进行拉伸缩放
//            bitmapShader.setLocalMatrix(matrix);
            //初始化目标Bitmap
            targetBitmap = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),Bitmap.Config.ARGB_8888);
//            targetBitmap = Bitmap.createBitmap(outWidth,outHeight,Bitmap.Config.ARGB_8888);
            //初始化Canvas
            Canvas targetCanvas = new Canvas(targetBitmap);
            //初始化画笔
            Paint paint = new Paint();
            //设置锯齿
            paint.setAntiAlias(true);
            //设置纹理图
            paint.setShader(bitmapShader);
            //利用画笔将纹理图绘制到画布上
//            targetCanvas.drawRoundRect(new RectF(0,0,outWidth,outHeight),radius,radius,paint);
            targetCanvas.drawRoundRect(new RectF(0,0,bitmap.getWidth(),bitmap.getHeight()),radius,radius,paint);
        }
        return targetBitmap;
    }

    public static Bitmap image2CircleAddFilter(Resources res,int resId,float r,float g,float b,float a){
        //获取资源信息
        Bitmap bitmap = BitmapFactory.decodeResource(res,resId);
        Bitmap targetBitmap = null;
        if(bitmap != null){
            //重置为黑色
            ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                    0, 0, 0, 0, r,  //red
                    0, 0, 0, 0, g,  //green
                    0, 0, 0, 0, b,  //blue
                    0, 0, 0, 1, a,  //alpha
            });
            //初始化纹理图
            BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            //初始化目标bitmap
            targetBitmap = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),Bitmap.Config.ARGB_8888);
            //初始化Canvas大小
            Canvas targetCanvas = new Canvas(targetBitmap);
            //初始化画笔
            Paint paint = new Paint();
            //设置锯齿
            paint.setAntiAlias(true);
            //设置纹理图
            paint.setShader(bitmapShader);
            //设置颜色矩阵
            paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));

            //绘图
            targetCanvas.drawCircle((float) bitmap.getWidth() / 2,(float) bitmap.getHeight() / 2,(float) bitmap.getWidth() / 2,paint);
        }
        return targetBitmap;
    }

}
