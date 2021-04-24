package knn;

import static knn.Cifar10DataLoader.NUM_IMG_PIXELS;
import static knn.Cifar10DataLoader.N_BYTES_PER_IMAGE;

public class MyImage
    {
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
        
        public int getLabel()
        {
            return classLabel;
        }
        
        public int[] getImage()
        {
            return image1D;
        }
    }
