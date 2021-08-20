package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class TextGraphicsConverterImpl implements TextGraphicsConverter {

    public String printNewPicture(char[][] picForPrint) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < picForPrint.length; i++) {
            for (int j = 0; j < picForPrint[i].length; j++){
                sb.append(picForPrint[i][j]);
                sb.append(picForPrint[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private int width;
    private int height;
    private double maxRatio;
    private TextColorSchemaImpl schema;

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        double ratio = 0;
        double widthToChange = 0;
        double heightToChange = 0;

        int width = 200;
        int height = 300;

        BufferedImage img = ImageIO.read(new URL(url));

        int newWidth = Math.max(width, img.getWidth());
        int newHeight = Math.max(height, img.getHeight());

        if (img.getWidth() / img.getHeight() > img.getHeight() / img.getWidth()) {
            ratio = (double) img.getWidth() / (double) img.getHeight();
        } else {
            ratio = (double) img.getHeight() / (double) img.getWidth();
        }

        if (ratio > maxRatio && maxRatio != 0) throw new BadImageSizeException(ratio, maxRatio);

//        if (img.getWidth() > width || img.getHeight() > height) {
//            if (width != 0) {
//                widthToChange = img.getWidth() / width;
//            } else widthToChange = 1;
//            if (height != 0) {
//                heightToChange = img.getHeight() / height;
//            } else heightToChange = 1;
//
//            if (widthToChange > heightToChange) {
//                newWidth = (int) (img.getWidth() / widthToChange);
//                newHeight = (int) (img.getHeight() / widthToChange);
//            } else {
//                newWidth = (int) (img.getWidth() / heightToChange);
//                newHeight = (int) (img.getHeight() / heightToChange);
//            }
//        } else {
//            newWidth = img.getWidth();
//            newHeight = img.getHeight();
//        }

        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);
        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);
//        ImageIO.write((RenderedImage) scaledImage, "png", new File("out.png"));
        var bwRaster = bwImg.getRaster();
        char[][] newpic = new char[newHeight][newWidth];
        for (int i = 0; i < newHeight; i++) {
            for (int j = 0; j < newWidth; j++) {
                int color = bwRaster.getPixel(j, i, new int[3])[0];
                char c = schema.convert(color);
                newpic[i][j] = c;
            }
        }

//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < newpic.length; i++) {
//            for (int j = 0; j < newpic[i].length; j++){
//                sb.append(newpic[i][j]);
//                sb.append(newpic[i][j]);
//            }
//            sb.append("\n");
//        }

        printNewPicture(newpic);
        return newpic.toString();

    }

    public TextGraphicsConverterImpl() {
        schema = new TextColorSchemaImpl();
    }

    @Override
    public void setMaxWidth(int width) {

    }

    @Override
    public void setMaxHeight(int height) {

    }

    @Override
    public void setMaxRatio(double maxRatio) {

    }

    @Override
    public void setTextColorSchema(TextColorSchemaImpl schema) {

    }

}
