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

public class MyImage
    {
        public static int N_PIXELS_PER_CHANNEL = HEIGHT * WIDTH;
        // Properties
        int classLabel;
        int[] image1D;
        
        // Constructor
        public MyImage(byte[] batchData, int index)
        {
            int offset = index * N_BYTES_PER_IMAGE;
            classLabel = batchData[offset] & 0xFF;
            image1D = new int[NUM_IMG_PIXELS];
            for (int i = 0; i < NUM_IMG_PIXELS; i++)
            {
                image1D[i] = batchData[offset+1+i] & 0xFF;
            }
        }
        
        public MyImage(String datasetPath, String imageName)
        {
            classLabel = Character.getNumericValue(imageName.charAt(0));
            String imagePath = datasetPath + "/" + imageName;
            File img = new File(imagePath);
            BufferedImage image = null;
            try{
                image = ImageIO.read(img);
            }
            catch(IOException e){
            }
            // should check if null
            byte[] imgByte = ((DataBufferByte)image.getRaster().getDataBuffer()).getData();
            image1D = new int[NUM_IMG_PIXELS];
            for (int i = 0; i < N_PIXELS_PER_CHANNEL; i++)
            {
                image1D[i] = imgByte[3*i+2] & 0xFF;
                image1D[N_PIXELS_PER_CHANNEL + i] = imgByte[3*i+1] & 0xFF;
                image1D[2 * N_PIXELS_PER_CHANNEL + i] = imgByte[3*i] & 0xFF;
            }
        }
        
        public int getLabel()
        {
            return classLabel;
        }
        
        public int[] getImage()
        {
            return image1D;
        }
    }
