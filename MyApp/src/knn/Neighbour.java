package knn;

public class Neighbour implements Comparable<Neighbour>
{
    public double distance;
    public int classLabel;

    public Neighbour(double distance, int classlabel)
    {
        this.distance = distance;
        this.classLabel = classlabel;
    }

    // Neighbours are compared based on distance.
    public int compareTo(Neighbour n2)
    {
        return Double.compare(this.distance, n2.distance);
    }
}
