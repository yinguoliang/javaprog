package com.elva.qrcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import com.alibaba.fastjson.JSON;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

public class BasicTest {
    
    public static void createCode() throws Exception{
        BitMatrix bm = null;
        Path path = null;
        //code bar
        bm=new MultiFormatWriter().encode("1233", BarcodeFormat.CODABAR, 400, 40);
        path = FileSystems.getDefault().getPath("d:/", "hello_bar.png");
        MatrixToImageWriter.writeToPath(bm, "png", path);
//        //EAN 13
//        bm=new MultiFormatWriter().encode("1234567890123", BarcodeFormat.EAN_13, 400, 400);
//        path = FileSystems.getDefault().getPath("d:/", "hello_ean13.png");
//        MatrixToImageWriter.writeToPath(bm, "png", path);
//        //PDF 417
//        bm=new MultiFormatWriter().encode("Hello QRCode", BarcodeFormat.PDF_417, 400, 400);
//        path = FileSystems.getDefault().getPath("d:/", "hello_pdf417.pdf");
//        MatrixToImageWriter.writeToPath(bm, "png", path);
    }
    
    public static void createQRCode() throws Exception{
        BitMatrix bm = 
                new MultiFormatWriter().encode("Hello QRCode", BarcodeFormat.QR_CODE, 400, 400);
        Path path = FileSystems.getDefault().getPath("d:/", "hello.png");
        MatrixToImageWriter.writeToPath(bm, "png", path);
    }
    
    public static void readQRCode() throws Exception{
        BufferedImage img = ImageIO.read(new File("d:/hello.png"));
        LuminanceSource src = new BufferedImageLuminanceSource(img);
        Binarizer binarizer = new HybridBinarizer(src);
        BinaryBitmap bm = new BinaryBitmap(binarizer);
        Result rs = new MultiFormatReader().decode(bm);
        System.out.println(JSON.toJSONString(rs));
    }
    
    public static void main(String args[]) throws Exception{
        createCode();
//        createQRCode();
//        readQRCode();
    }
}
