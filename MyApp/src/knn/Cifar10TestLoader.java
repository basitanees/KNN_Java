package knn;

import java.util.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import static knn.Cifar10DataLoader.NUM_TRAIN_IMAGES_PER_BATCH;

public class Cifar10TestLoader
{
    // Constants
    private static final String TEST_FILE_FOLDER = "test-images";
    
    // Properties
    private String datasetPath;
    private String[] imagesList;
    int index;
    
    // Constructor to initialize datastream
    public Cifar10TestLoader(String pathToProjectFolder)
    {
        datasetPath = pathToProjectFolder + "/" + TEST_FILE_FOLDER;
        File testFolder = new File(datasetPath);
        imagesList = testFolder.list();
    }
    
    public MyImage[] getImages(String[] imgList)
    {
        MyImage[] images = new MyImage[imgList.length];
        for (int i = 0; i < imgList.length; i++)
        {
            images[i] = new MyImage(datasetPath, imgList[i]);
        }
        return images;
    }
    
    public MyImage[] getTestImages()
    {
        MyImage[] images = new MyImage[imagesList.length];
        for (int i = 0; i < imagesList.length; i++)
        {
            images[i] = new MyImage(datasetPath, imagesList[i]);
        }
        return images;
    }
    
    public MyImage getImg(String imgPath)
    {
        return new MyImage(datasetPath, imgPath);
    }
    
    
}
