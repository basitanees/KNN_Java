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
import java.io.*;
import java.util.Base64;

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
            try
            {
                image = ImageIO.read(img);
            }
            catch(Exception e)
            {
            }
            byte[] imgByte = ((DataBufferByte)image.getRaster().getDataBuffer()).getData();
            image1D = new int[NUM_IMG_PIXELS];
            for (int i = 0; i < N_PIXELS_PER_CHANNEL; i++)
            {
                image1D[i] = imgByte[3*i+2] & 0xFF;
                image1D[N_PIXELS_PER_CHANNEL + i] = imgByte[3*i+1] & 0xFF;
                image1D[2 * N_PIXELS_PER_CHANNEL + i] = imgByte[3*i] & 0xFF;
            }
        }
        
        public static byte[] toByteArray(BufferedImage bi, String format)
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try{
                ImageIO.write(bi, format, baos);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            byte[] bytes = baos.toByteArray();
            return bytes;
        }
        
//        // Constructor for test data
//        public MyImage(String datasetPath, String imageName)
//        {
//            classLabel = Character.getNumericValue(imageName.charAt(0));
//            String imagePath = datasetPath + "/" + imageName;
//            File img = new File(imagePath);
//            BufferedImage image;
//            int pixel;
//            try{
//                image = ImageIO.read(img);
//                for(int i = 0; i < WIDTH; i++)
//                {
//                    for(int j = 0; j < HEIGHT; j++)
//                    {    
//                        pixel =  image.getRGB(i,j);
//                        image1D[i*WIDTH+j*HEIGHT]   = (pixel & 0x00ff0000) >> 16;
//                        image1D[i] = (pixel & 0x0000ff00) >> 8;
//                        image1D[i]=  pixel & 0x000000ff;
//                    }
//                }
//            }
//            catch(IOException e){
//                e.printStackTrace();
//            }
//        }
        
        public int getLabel()
        {
            return classLabel;
        }
        
        public int[] getImage()
        {
            return image1D;
        }
    }
