package com.example.marcos.mygridlayoutexample;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class BitmapCombiner {
    private Bitmap backgroundBitmap;
    private Bitmap foregroundBitmap;
    private Canvas canvas;
    public BitmapCombiner(Bitmap backgroundBitmap,Bitmap foregroundBitmap){
        this.backgroundBitmap=backgroundBitmap;
        this.foregroundBitmap=foregroundBitmap;
        this.canvas=new Canvas(backgroundBitmap);
    }

    public Bitmap getCombinedBitmap(int opacity){
        Bitmap combinedBitmap = Bitmap.createBitmap(backgroundBitmap.getWidth(), backgroundBitmap.getHeight(), Bitmap.Config.ARGB_8888);

        for (int i = 0; i < backgroundBitmap.getHeight(); i++) {
            for (int j = 0; j < backgroundBitmap.getWidth(); j++) {
                int bitmapPixel = backgroundBitmap.getPixel(i, j);
                int overlayPixel = foregroundBitmap.getPixel(i, j);

                int R = (bitmapPixel >> 16) & 0xff;
                int G = (bitmapPixel >> 8) & 0xff;
                int B = (bitmapPixel) & 0xff;

                int overlayR = (overlayPixel >> 16) & 0xff;
                int overlayG = (overlayPixel >> 8) & 0xff;
                int overlayB = (overlayPixel) & 0xff;

                int color = (255 & 0xff) << 24 | ((R * (100 - opacity) / 100) + (overlayR * opacity / 100) & 0xff) << 16 | (G * (100 - opacity) / 100 + (overlayG * opacity / 100) & 0xff) << 8 | (B * (100 - opacity) / 100 + (overlayB * opacity / 100) & 0xff);

                combinedBitmap.setPixel(i, j, color);

            }
        }
        canvas.drawBitmap(combinedBitmap, 0, 0, null);

        return combinedBitmap;
    }

}
