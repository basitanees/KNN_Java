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
    
    public MyImage[] getBatch(String path, int iBatch)
    {
        Cifar10DataLoader loader = new Cifar10DataLoader(path, iBatch);
        return loader.getBatch();
    }
    
    // Load Data in the model
    public void loadBatch(String path, int iBatch)
    {
        this.dataTr = getBatch(path, iBatch);
    }
    
    public void loadData(String path, int[] iBatches)
    {
        this.dataTr = new MyImage[NUM_TRAIN_IMAGES_PER_BATCH * iBatches.length];
        MyImage[] data;
        for (int i = 0; i < iBatches.length; i++)
        {
            data = getBatch(path, iBatches[i]);
            for (int j = 0; j < data.length; j++)
            {
                this.dataTr[i*NUM_TRAIN_IMAGES_PER_BATCH + j] = data[j];
            }
        }
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
        Neighbour[] neighbours = new Neighbour[this.dataTr.length];
        for (int i = 0; i < dataTr.length; i++)
        {
            neighbours[i] = new Neighbour(euclideanDistance(this.dataTr[i].getImage(), img2), this.dataTr[i].getLabel());
        }
        return neighbours;
    }
    
    public Decision classifyImg(int[] img2)
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
        return new Decision(decision, confidence);
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
            
            if (i % 100 == 0)
                System.out.println(i);
        }
        double accuracy = 100 * trues / testImages.length;
        return accuracy;
    }
    
    public double getTrainAccuracy()
    {
        return getAccuracy(this.dataTr);
    }
}
