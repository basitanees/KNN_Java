package knn;


// Class to store distance from test image to train image and label of the train image.
// Make the class comparable to use the sort function
public class Neighbour implements Comparable<Neighbour>
{
    protected double distance;
    protected int classLabel;

    public Neighbour(double distance, int classlabel)
    {
        this.distance = distance;
        this.classLabel = classlabel;
    }

    // Neighbours are compared based on distance.
    @Override
    public int compareTo(Neighbour n2)
    {
        return Double.compare(this.distance, n2.distance);
    }
}
