package com.example.lendbook.objetos;

import android.graphics.Bitmap;

public class ImageLivro {

    private Bitmap bitmap;

    public ImageLivro(){

    }

    public ImageLivro(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
