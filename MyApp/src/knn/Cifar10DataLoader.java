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
    private static final int N_BYTES_PER_IMAGE = 3073;
    private static final String TRAIN_FILES_FOLDER = "Training data-20210318";
    private static final String[] TRAIN_FILES = {"data_batch_1.bin", "data_batch_2.bin", "data_batch_3.bin", "data_batch_4.bin", "data_batch5.bin"};
    private static final String TEST_FILE_FOLDER = "test-images";
    private static final String LABEL_NAMES_FILE = "batches.meta.txt";
    
    // Properties
    private String datasetPath;
    protected FileInputStream trainInputStream;
    int index;
    
    // Constructor to initialize datastream
    public Cifar10DataLoader(String pathToProjectFolder, int iBatch) throws IOException
    {
        try
        {
            trainInputStream = new FileInputStream(pathToProjectFolder + "/" + TRAIN_FILES_FOLDER + "/" + TRAIN_FILES[iBatch]);
            index = 0;
        }
        catch(Exception e)
        {
            if (iBatch > 4 || iBatch < 0)
                System.out.println("Invalid batch number");
            e.printStackTrace();
        }
    }
    
    public MyImage getNextImage()
    {
        byte[] img = new byte[3073];
        try
        {
            trainInputStream.read(img);
        }
        catch (IOException e)
        {
            System.out.println("Could not read byte");
            e.printStackTrace();
        }
        MyImage image = new MyImage(img);
        return image;
    }
    
    public boolean hasNext()
   {
      if (index >= NUM_TRAIN_IMAGES_PER_BATCH)
         return false;
      return true;
   }
    
   public MyImage next()
   {
      index++;
      return getNextImage();
   }
}