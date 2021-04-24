package knn;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Test
{
    public static void main(String[] args){
        String path = "C:/FreeLance/KNN_Java";
//        Cifar10DataLoader trainData = new Cifar10DataLoader(path,0);
//        MyImage img;
//        int ind = 0;
//        while(trainData.hasNext())
//        {
//            img = trainData.next();
//            ind++;
//            System.out.println(ind);
//        }
        KNN clf = new KNN(5);
        clf.loadData(path, 0);
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
