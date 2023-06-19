package com.xiesu;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

public class RotateImage {

    private static final String INPUT_FILE_NAME = "mountains.jpeg";
    private static final String OUTPUT_FILE_NAME = "mountainsRotated.jpeg";

    private AffineTransform rotateImageClockWise(BufferedImage image) {

        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(Math.PI / 2, imageWidth / 2, imageHeight / 2);

        double offset = (imageWidth - imageHeight) / 2;
        affineTransform.translate(offset, offset);
        return affineTransform;
    }

    private AffineTransform rotateImageCounterClockwise(BufferedImage image) {

        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(-Math.PI / 2, imageWidth / 2, imageHeight / 2);

        double offset = (imageWidth - imageHeight) / 2;
        affineTransform.translate(-offset, -offset);

        return affineTransform;
    }

    private void rotateImage() throws Exception {
        BufferedImage bufferedImage = ImageIO.read(new File(INPUT_FILE_NAME));

        BufferedImage output = new BufferedImage(bufferedImage.getHeight(),
                bufferedImage.getWidth(), bufferedImage.getType());

        AffineTransform affineTransform = rotateImageClockWise(bufferedImage);
        AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform,
                AffineTransformOp.TYPE_BILINEAR);
        affineTransformOp.filter(bufferedImage, output);

        ImageIO.write(output, "jpg", new File(OUTPUT_FILE_NAME));

    }

    public static void main(String[] args) {

        try {

            RotateImage rotateImage = new RotateImage();
            rotateImage.rotateImage();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
