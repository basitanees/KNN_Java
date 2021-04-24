package knn;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Test
{
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("C:/FreeLance/KNN_Java/Training data-20210318/data_batch_1.bin");
        System.out.println((int)inputStream.getChannel().size() + " Bytes");
        byte[] b = new byte[30730000];
        System.out.println("Reading Data...");
        inputStream.read(b);
        System.out.println("Data has been read!");
        System.out.println(b[0+3*3073]);
        // and with 0xFF to convert to unsigned int
//        System.out.println(b[0]);
//        BufferedImage image = new BufferedImage(32, 32, BufferedImage.TYPE_INT_RGB);
//        for (int row = 0; row < 32; row++) {
//            for (int col = 0; col < 32; col++) {
//                Color color = new Color(
//                        b[1 + 1024 * 0 + row * 32 + col] & 0xFF,
//                        b[1 + 1024 * 1 + row * 32 + col] & 0xFF,
//                        b[1 + 1024 * 2 + row * 32 + col] & 0xFF);
//                image.setRGB(col, row, color.getRGB());
//            }
//        }
//
//        boolean result = ImageIO.write(image, "jpeg", new FileOutputStream("./out.jpg"));
//        if (!result) {
//            System.err.println("failed");
//        }
    }
}

//import java.io.IOException;

//public class Test {
//    public static void main(String[] args)
//    {
//        String path = "C:/FreeLance/KNN_Java";
////        try
////        {
////            Cifar10DataLoader trainData = new Cifar10DataLoader(path,0);
////            MyImage img = trainData.next();
////        }
////        catch(IOException e)
////        {
////            System.out.println("Failed to create iterator!");
////        }
//        KNN clf = new KNN(5);
//        clf.loadData(path, 0);
//
//    }
//}
