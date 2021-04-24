package knn;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Test
{
    public static void main(String[] args) throws IOException{
        String path = "C:/FreeLance/KNN_Java";
        Cifar10DataLoader trainData = new Cifar10DataLoader(path,0);
        
//        String[] labels = trainData.getLabelNames();
//        for (int i = 0; i < labels.length; i++)
//            System.out.println(labels[i]);

//        MyImage img;
//        int ind = 0;
//        while(trainData.hasNext())
//        {
//            img = trainData.next();
//            ind++;
//            System.out.println(ind);
//        }
        MyImage img = new MyImage("C:/FreeLance/KNN_Java/MyApp", "out1.png");
        for (int i = 3000; i < 3072; i++)
            System.out.print(img.getImage()[i] + " ");
        System.out.println();
        
        byte[] b = new byte[3073];

        FileInputStream inputStream = new FileInputStream("C:/FreeLance/KNN_Java/Training data-20210318/data_batch_1.bin");

        inputStream.read(b);
  
        // and with 0xFF to convert to unsigned int
        for (int i = 3000; i < 3072; i++)
            System.out.print((b[i+1] & 0xFF) + " ");

//        KNN clf = new KNN(10);
//        clf.loadData(path, 0);
//        System.out.println(clf.getTrainAccuracy());
        
//        int label, index;
//        index = 2;
//        label = clf.classifyImg(trainData.getImageAtIndex(index));
//        System.out.println("Actual Label: " + trainData.getLabelAtIndex(index));
//        System.out.println("Predicted Label: " + label);
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

//    }
//}
