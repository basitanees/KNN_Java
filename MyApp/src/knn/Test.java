package knn;

import java.io.IOException;

public class Test
{
    public static void main(String[] args) throws IOException{
        String path = "C:/FreeLance/KNN_Java";
//        Cifar10DataLoader trainData = new Cifar10DataLoader(path,0);
        Cifar10TestLoader testData = new Cifar10TestLoader(path);
        
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
//        MyImage img = new MyImage("C:/FreeLance/KNN_Java/MyApp", "out1.png");
//        for (int i = 3000; i < 3072; i++)
//            System.out.print(img.getImage()[i] + " ");
//        System.out.println();

        KNN clf = new KNN(10);
        int[] iBatches = {0,1,2};
        clf.loadData(path, iBatches);
        double accTest;
        accTest = clf.getAccuracy(testData.getTestImages());
        System.out.println(accTest);
        
//        System.out.println(clf.getTrainAccuracy());
        
//        int label, index;
//        index = 2;
//        label = clf.classifyImg(trainData.getImageAtIndex(index));
//        System.out.println("Actual Label: " + trainData.getLabelAtIndex(index));
//        System.out.println("Predicted Label: " + label);

    }
}