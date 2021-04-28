package knn;

import java.io.File;

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
        this.datasetPath = pathToProjectFolder + "/" + TEST_FILE_FOLDER;
        File testFolder = new File(this.datasetPath);
        this.imagesList = testFolder.list(); // could add filter for png files
    }
    
    // Load images using a list of image names
    public MyImage[] getImages(String[] imgList)
    {
        MyImage[] images = new MyImage[imgList.length];
        for (int i = 0; i < imgList.length; i++)
        {
            images[i] = getImg(imgList[i]);
        }
        return images;
    }
    
    // Load all images present in the test dataset folder
    public MyImage[] getTestImages()
    {
        return getImages(this.imagesList);
    }
    
    // Get image using an image name
    public MyImage getImg(String imgPath)
    {
        return new MyImage(datasetPath, imgPath);
    }
}
