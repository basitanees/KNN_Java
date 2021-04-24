package knn;

import java.lang.Math;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static knn.Cifar10DataLoader.NUM_IMG_PIXELS;
import static knn.Cifar10DataLoader.NUM_TRAIN_IMAGES_PER_BATCH;

public class KNN
{
    // Properties
    int k;
    int nTrainBatches;
    MyImage[] batch;
    
    // Constructor
    public KNN(int k)
    {
        this.k = k;
    }
    
    // Load Data in the model
    public void loadData(String path, int iBatch)
    {
        Cifar10DataLoader loader = new Cifar10DataLoader(path, iBatch);
        batch = loader.getBatch();
    }
    
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
    
    public Neighbour[] getDistances2TestImg(int[] img2)
    {
        Neighbour[] neighbours = new Neighbour[NUM_TRAIN_IMAGES_PER_BATCH];
        for (int i = 0; i < NUM_TRAIN_IMAGES_PER_BATCH; i++)
        {
            neighbours[i] = new Neighbour(euclideanDistance(batch[i].getImage(), img2), batch[i].getLabel());
        }
        return neighbours;
    }
    
    public int classifyImg(int[] img2)
    {
        Neighbour[] neighbours = getDistances2TestImg(img2);
        Arrays.sort(neighbours);
        LinkedHashMap<Integer, Integer> votes = new LinkedHashMap<Integer, Integer>();
        for (int i = 0; i < this.k; i++)
        {
            int label = neighbours[i].classLabel;
            // Increment vote count if already in the list. 
            if (votes.containsKey(label))
                votes.put(label, votes.get(label) + 1);
            // Add vote if not in the list. 
            else
                votes.put(label, 1);
        }
        // Sets the decision as the label with the greatest number of votes.
        int decision = 0;
        double maxVote = 0;
        for (Map.Entry<Integer, Integer> vote : votes.entrySet())
        {
            if (vote.getValue() > maxVote){
                decision = vote.getKey();
                maxVote = vote.getValue();
            }
        }
        double confidence = votes.get(decision)/this.k; // Report this somewhere!!!
        return decision;
    }
    
    public double getAccuracy(int[][] testImages, int[] testLabels)
    {
        int[] predictedLabels = new int[testImages.length];
        int trues = 0;
        for (int i = 0; i < testImages.length; i++)
        {
            predictedLabels[i] = classifyImg(testImages[i]);
            if (predictedLabels[i] == testLabels[i])
                trues++;
            
            if (i % 100 == 0)
                System.out.println(i);
        }
        double accuracy = 100 * trues / testImages.length;
        return accuracy;
    }
    
    public double getTrainAccuracy()
    {
        int[] predictedLabels = new int[batch.length];
        int trues = 0;
        for (int i = 0; i < batch.length; i++)
        {
            predictedLabels[i] = classifyImg(batch[i].getImage());
            if (predictedLabels[i] == batch[i].getLabel())
                trues++;
            
            if (i % 100 == 0)
                System.out.println(i);
            
        }
        double accuracy = 100 * trues / batch.length;
        return accuracy;
    }
}
