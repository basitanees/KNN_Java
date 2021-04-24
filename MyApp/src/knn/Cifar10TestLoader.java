package knn;

import java.util.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;

public class Cifar10TestLoader
{
    // Constants
    public static final int HEIGHT = 32;
    public static final int WIDTH = 32;
    public static final int NUM_IMG_PIXELS = 3072;
//    public static final int NUM_LABELS = 10;
    public static final int CHANNELS = 3;
    private static final String TEST_FILE_FOLDER = "test-images";
//    private static final String LABEL_NAMES_FILE = "batches.meta.txt";
    
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
}
