package knn;

import static knn.Cifar10DataLoader.NUM_IMG_PIXELS;
import static knn.Cifar10DataLoader.N_BYTES_PER_IMAGE;
import static knn.Cifar10DataLoader.HEIGHT;
import static knn.Cifar10DataLoader.WIDTH;

import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;

// Class to hold image and its label
public class MyImage
{
    public static int N_PIXELS_PER_CHANNEL = HEIGHT * WIDTH;
    // Properties
    int classLabel;
    int[] image1D;

    // Constructor: Get image and label from byte array from bin file
    public MyImage(byte[] batchData, int index)
    {
        int offset = index * N_BYTES_PER_IMAGE;
        this.classLabel = batchData[offset] & 0xFF;
        this.image1D = new int[NUM_IMG_PIXELS];
        for (int i = 0; i < NUM_IMG_PIXELS; i++)
        {
           this.image1D[i] = batchData[offset+1+i] & 0xFF;
        }
    }
    
    // Constructor 2: Get image and label from image file
    public MyImage(String datasetPath, String imageName)
    {
        // First letter of img name represents class label
        this.classLabel = Character.getNumericValue(imageName.charAt(0));
        String imagePath = datasetPath + "/" + imageName;
        File img = new File(imagePath);
        BufferedImage image = null;
        try{
            image = ImageIO.read(img);
        }
        catch(IOException e){
        }
        // should check if image is still null
        // Convert Buffered image to byte array
        byte[] imgByte = ((DataBufferByte)image.getRaster().getDataBuffer()).getData();
        // Convert byte array to int array of pixels
        this.image1D = new int[NUM_IMG_PIXELS];
        // Also reorder the pixels in the form RRRR...GGGG...BBBB instead of BGRBGRBGR... in imgByte
        for (int i = 0; i < N_PIXELS_PER_CHANNEL; i++)
        {
            this.image1D[i] = imgByte[3*i+2] & 0xFF;
            this.image1D[N_PIXELS_PER_CHANNEL + i] = imgByte[3*i+1] & 0xFF;
            this.image1D[2 * N_PIXELS_PER_CHANNEL + i] = imgByte[3*i] & 0xFF;
        }
    }

    public int getLabel()
    {
        return this.classLabel;
    }

    public int[] getImage()
    {
        return this.image1D;
    }
}
