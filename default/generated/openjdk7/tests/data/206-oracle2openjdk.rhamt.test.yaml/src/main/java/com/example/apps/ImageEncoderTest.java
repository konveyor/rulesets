package com.example.apps;

import java.io.ByteArrayOutputStream;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import com.sun.image.codec.jpeg.MockClass;

public class ImageEncoderTest {

    public static void main(String[] args) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BufferedImage bufferedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);

        ImageIO.write(bufferedImage, "jpg", outputStream);
        MockClass mockClass = new MockClass();
    }

}