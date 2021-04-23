package knn;

import java.io.IOException;

public class Test {
    public static void main(String[] args)
    {
        String path = "C:/FreeLance/KNN_Java";
//        try
//        {
//            Cifar10DataLoader trainData = new Cifar10DataLoader(path,0);
//            MyImage img = trainData.next();
//        }
//        catch(IOException e)
//        {
//            System.out.println("Failed to create iterator!");
//        }
        KNN clf = new KNN(5);
        clf.loadData(path, 0);

    }
}
