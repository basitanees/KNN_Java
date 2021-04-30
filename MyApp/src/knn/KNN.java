package knn;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static knn.Cifar10DataLoader.NUM_TRAIN_IMAGES_PER_BATCH;

public class KNN
{
    // Properties
    int k;
    MyImage[] dataTr;
    
    // Constructor
    public KNN(int k)
    {
        this.k = k;
    }
    
    // Get the ith batch out of the 5 batches
    public MyImage[] getBatch(String path, int iBatch)
    {
        Cifar10DataLoader loader = new Cifar10DataLoader(path, iBatch);
        return loader.getBatch();
    }
    
    // Load a single batch in the model. Not used
    public void loadBatch(String path, int iBatch)
    {
        this.dataTr = getBatch(path, iBatch);
    }
    
    // Load selected batches in the model
    public void loadData(String path, int[] iBatches)
    {
        this.dataTr = new MyImage[NUM_TRAIN_IMAGES_PER_BATCH * iBatches.length];
        MyImage[] data;
        for (int i = 0; i < iBatches.length; i++)
        {
            data = getBatch(path, iBatches[i]);
            System.arraycopy(data, 0, this.dataTr, i*NUM_TRAIN_IMAGES_PER_BATCH, data.length);
        }
    }
    
    // Calculate euclidean distance between two images
    public double euclideanDistance(int[] img1, int[] img2)
    {
        // should assert img1.length == img2.length
        double sum = 0;
        for (int i = 0; i < img1.length; i++)
        {
            sum += (img1[i] - img2[i]) * (img1[i] - img2[i]);
        }
        return Math.sqrt(sum);
    }
    
    // Calculate the distance of test image to all training images
    // Neighbour class to store distances
    public Neighbour[] getDistances2TestImg(int[] img2)
    {
        Neighbour[] neighbours = new Neighbour[this.dataTr.length];
        for (int i = 0; i < dataTr.length; i++)
        {
            neighbours[i] = new Neighbour(euclideanDistance(this.dataTr[i].getImage(), img2), this.dataTr[i].getLabel());
        }
        return neighbours;
    }
    
    // Classify a single image using knn
    public Decision classifyImg(int[] img2)
    {
        Neighbour[] neighbours = getDistances2TestImg(img2);
        Arrays.sort(neighbours);
        // Hash map for storing class votes from nearest neighbors
        LinkedHashMap<Integer, Integer> votes = new LinkedHashMap<>();
        for (int i = 0; i < this.k; i++)
        {
            int label = neighbours[i].classLabel; 
            if (votes.containsKey(label))
                votes.put(label, votes.get(label) + 1);
            else
                votes.put(label, 1);
        }
        // Sets the decision as the label with the greatest number of votes.
        int prediction = 0;
        double maxVote = 0;
        for (Map.Entry<Integer, Integer> vote : votes.entrySet())
        {
            if (vote.getValue() > maxVote)
            {
                prediction = vote.getKey();
                maxVote = vote.getValue();
            }
        }
        double confidence = (1.0*votes.get(prediction))/this.k;
        return new Decision(prediction, confidence);
    }
    
    public double getAccuracy(MyImage[] testImages)
    {
        int[] predictedLabels = new int[testImages.length];
        int trues = 0;
        for (int i = 0; i < testImages.length; i++)
        {
            Decision pred = classifyImg(testImages[i].getImage());
            predictedLabels[i] = pred.decision;
            if (predictedLabels[i] == testImages[i].getLabel())
                trues++;
            // progress
//            if (i % 100 == 0)
//                System.out.println(i);
        }
        double accuracy = 100.0 * trues / testImages.length;
        return accuracy;
    }
    
    public double getTrainAccuracy()
    {
        return getAccuracy(this.dataTr);
    }
}
