package knn;

import static knn.Cifar10DataLoader.NUM_IMG_PIXELS;

public class MyImage
    {
        // Properties
        int classLabel;
        int[] image1D;
        
        // Constructor
        public MyImage(byte[] image)
        {
            classLabel = image[0] & 0xFF;
            image1D = new int[NUM_IMG_PIXELS];
            for (int i = 0; i < NUM_IMG_PIXELS; i++)
            {
                image1D[i] = image[i+1] & 0xFF;
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
