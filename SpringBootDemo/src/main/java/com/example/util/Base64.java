package com.example.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import sun.misc.BASE64Encoder;

public class Base64 {
	static BASE64Encoder encoder = new BASE64Encoder();

    public static String image2Base64(String imageFilePath) throws IOException {
        FileInputStream fis = new FileInputStream(new File(imageFilePath));
        byte[] bytes = new byte[fis.available()];
        fis.read(bytes);
        String base64String = encoder.encode(bytes);
        System.out.println("base64String: " + base64String);
        fis.close();
        return base64String;
    }
}
