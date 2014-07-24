/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.main.view.control.viewpagertransformer;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

/**
 * RotationPageTransformer.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 09:42:14 24 Jul 2014
 */
public class RotationPageTransformer implements PageTransformer{
    private float minAlpha;
    private int degrees;
    private float distanceToCentreFactor;
 
    /**
     * Creates a RotationPageTransformer
     * @param degrees the inner angle between two edges in the "polygon" that the pages are on.
     * Note, this will only work with an obtuse angle
     */
    public RotationPageTransformer(int degrees){
        this(degrees, 0.7f);
    }
 
    /**
     * Creates a RotationPageTransformer
     * @param degrees the inner angle between two edges in the "polygon" that the pages are on.
     * Note, this will only work with an obtuse angle
     * @param minAlpha the least faded out that the side
     */
    public RotationPageTransformer(int degrees, float minAlpha){
        this.degrees = degrees;
        distanceToCentreFactor = (float) Math.tan(Math.toRadians(degrees / 2))/2;
        this.minAlpha = minAlpha;
    }
 
    public void transformPage(View view, float position){
        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();
        view.setPivotX((float) pageWidth / 2);
        view.setPivotY((float) (pageHeight + pageWidth * distanceToCentreFactor)); 
 
        if(position < -1){ //[-infinity,1)
            //off to the left by a lot
            view.setRotation(0);
            view.setAlpha(0);
        }else if(position <= 1){ //[-1,1]
            view.setTranslationX((-position) * pageWidth); //shift the view over
            view.setRotation(position * (180 - degrees)); //rotate it
            // Fade the page relative to its distance from the center
            view.setAlpha(Math.max(minAlpha, 1 - Math.abs(position)/3));
        }else{ //(1, +infinity]
            //off to the right by a lot
            view.setRotation(0);
            view.setAlpha(0);
        }
    }
}