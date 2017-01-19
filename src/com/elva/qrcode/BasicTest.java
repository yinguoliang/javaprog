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
//        createQRCode();
        readQRCode();
    }
}
