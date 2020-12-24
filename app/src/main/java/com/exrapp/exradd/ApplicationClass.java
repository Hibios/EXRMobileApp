package com.exrapp.exradd;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.Color;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * The base class of the entire application,
 * contains some important variables for the application,
 * as well as a method for timely QR code generation.
 *
 * @author  Ivan Minakov, Kravtsov Stefan, Belousov Viktor, Tolstykh Mikhail
 * @version 3.0
 * @since   2020-12-24
 */
public class ApplicationClass extends Application {
    /**
     * The method returns the activity state
     * for understanding whether they are displayed or not
     * @return State variable
     */
    public static boolean isActivityVisible() {
        return activityVisible;
    }

    /**
     * Method changes the activity variable
     */
    public static void activityResumed() {
        activityVisible = true;
    }

    /**
     * Method changes the activity variable
     */
    public static void activityPaused() {
        activityVisible = false;
    }

    private static boolean activityVisible;
    public static boolean isNightModeOn; // Night mode status
    public static Bitmap QR_IMAGE;

    /**
     * The method generates QR code when the application
     * is first launched and changes the state of the variable QR_IMAGE
     * @param barcodeText Link from which the QR code will be generated
     */
    public static void generateQRCodeImage(String barcodeText) throws WriterException {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix =
                barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 1024, 1024);

        int height = bitMatrix.getHeight();
        int width = bitMatrix.getWidth();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                bmp.setPixel(x, y, bitMatrix.get(x,y) ? Color.BLACK : Color.WHITE);
            }
        }

        QR_IMAGE = bmp;
    }
}
