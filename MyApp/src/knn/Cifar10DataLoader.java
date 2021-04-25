package knn;

import java.util.*;
import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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
    private byte[] batchData;
    private int index;
    private int size;
    
    // Constructor to read the bytes from bin file
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
        catch(IOException e)
        {
            if (iBatch > 4 || iBatch < 0)
                System.out.println("Invalid batch number");
            e.printStackTrace();
        }
    }
    
    // Get list of all the images in the batch
    public MyImage[] getBatch()
    {
        int ind = 0;
        MyImage[] images = new MyImage[NUM_TRAIN_IMAGES_PER_BATCH];
        while (hasNext())
        {
            images[ind] = next();
            ind++;
        }
        return images;
    }
    
    // Read Labels file and save the names in string
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
    
    // Get image at index
    public MyImage getAtIndex(int index)
    {
        return new MyImage(batchData, index);
    }
    
    public MyImage getNextImage()
    {
        return getAtIndex(index++);
    }
    
    @Override
    public boolean hasNext()
    {
        return index < size;
    }
    
    @Override
    public MyImage next()
    {
        return getNextImage();
    }
    
    // Get image at index image only without label
    public int[] getImageAtIndex(int index)
    {
        return new MyImage(batchData, index).getImage();
    }
    
    // Get label of image at index
    public int getLabelAtIndex(int index)
    {
        return new MyImage(batchData, index).getLabel();
    }
}
