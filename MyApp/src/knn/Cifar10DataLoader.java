package knn;

import java.util.*;
import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;

public class Cifar10DataLoader implements Iterator
{
    // Constants
    public static final int HEIGHT = 32;
    public static final int WIDTH = 32;
    public static final int NUM_IMG_PIXELS = 3072;
    public static final int NUM_LABELS = 10;
    public static final int NUM_TRAIN_IMAGES = 50000;
    public static final int NUM_TRAIN_IMAGES_PER_BATCH = 10000;
    public static final int CHANNELS = 3;
    public static final int N_BYTES_PER_IMAGE = 3073;
    private static final String TRAIN_FILES_FOLDER = "Training data-20210318";
    private static final String[] TRAIN_FILES = {"data_batch_1.bin", "data_batch_2.bin", "data_batch_3.bin", "data_batch_4.bin", "data_batch5.bin"};
    private static final String TEST_FILE_FOLDER = "test-images";
    private static final String LABEL_NAMES_FILE = "batches.meta.txt";
    
    // Properties
    private String projectPath;
//    protected FileInputStream trainInputStream;
    byte[] batchData;
    int index;
    int size;
    
    // Constructor to initialize datastream
    public Cifar10DataLoader(String pathToProjectFolder, int iBatch)
    {
        try
        {
            projectPath = pathToProjectFolder;
            String batchPath = pathToProjectFolder + "/" + TRAIN_FILES_FOLDER + "/" + TRAIN_FILES[iBatch];
            FileInputStream trainInputStream = new FileInputStream(batchPath);
            int fileSize = (int)trainInputStream.getChannel().size(); // should be 30730000
            batchData = new byte[fileSize];
            trainInputStream.read(batchData);
            trainInputStream.close();
            index = 0;
            size = NUM_TRAIN_IMAGES_PER_BATCH;
        }
        catch(Exception e)
        {
            if (iBatch > 4 || iBatch < 0)
                System.out.println("Invalid batch number");
            e.printStackTrace();
        }
    }
    
    public MyImage[] getBatch()
    {
        int index = 0;
        MyImage[] images = new MyImage[NUM_TRAIN_IMAGES_PER_BATCH];
        while (hasNext())
        {
            images[index] = next();
            index++;
        }
        return images;
    }
    
    public String[] getLabelNames()
    {
        String[] labelNames = new String[NUM_LABELS];
        try
        {
            String labelNamesPath = projectPath + "/" + TRAIN_FILES_FOLDER + "/" + LABEL_NAMES_FILE;
            File myObj = new File(labelNamesPath);
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine())
            {
                if (i == NUM_LABELS)
                    break;
                labelNames[i++] = myReader.nextLine();
            }
            myReader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return labelNames;
    }
    
    public MyImage getAtIndex(int index)
    {
        return new MyImage(batchData, index);
    }
    
    public MyImage getNextImage()
    {
        return getAtIndex(index++);
    }
    
    public boolean hasNext()
    {
        if (index >= size)
            return false;
        return true;
    }
    
    public MyImage next()
    {
        return getNextImage();
    }
    
    public int[] getImageAtIndex(int index)
    {
        return new MyImage(batchData, index).getImage();
    }
    
    public int getLabelAtIndex(int index)
    {
        return new MyImage(batchData, index).getLabel();
    }
}
