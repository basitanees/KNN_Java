package knn;

// Class to hold the predicted label and confidence level of prediction
public class Decision
{
    public int decision;
    public double confidence;
    public Decision(int decision, double confidence)
    {
        this.decision = decision;
        this.confidence = confidence;
    }
}