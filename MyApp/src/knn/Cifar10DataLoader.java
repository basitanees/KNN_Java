package knn;

import java.util.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

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
    private String datasetPath;
    protected FileInputStream trainInputStream;
    byte[] batchData;
    int index;
    
    // Constructor to initialize datastream
    public Cifar10DataLoader(String pathToProjectFolder, int iBatch)
    {
        try
        {
            trainInputStream = new FileInputStream(pathToProjectFolder + "/" + TRAIN_FILES_FOLDER + "/" + TRAIN_FILES[iBatch]);
            int fileSize = (int)trainInputStream.getChannel().size(); // should be 30730000
            batchData = new byte[fileSize];
            trainInputStream.read(batchData);
            trainInputStream.close();
            index = 0;
        }
        catch(Exception e)
        {
            if (iBatch > 4 || iBatch < 0)
                System.out.println("Invalid batch number");
            e.printStackTrace();
        }
    }
    
    public MyImage getAtIndex(int index)
    {
        return new MyImage(batchData, index);
    }
    
    public int[] getImageAtIndex(int index)
    {
        return new MyImage(batchData, index).getImage();
    }
    
    public int getLabelAtIndex(int index)
    {
        return new MyImage(batchData, index).getLabel();
    }
    
    public MyImage getNextImage()
    {
        return getAtIndex(index++);
    }
    
    public boolean hasNext()
    {
        if (index >= NUM_TRAIN_IMAGES_PER_BATCH)
            return false;
        return true;
    }
    
    public MyImage next()
    {
        return getNextImage();
    }
}
