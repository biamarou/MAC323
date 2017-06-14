import java.awt.Color;
import java.lang.Math;
import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
    private Node[][] myPicture;
    int height;
    int width;
    
    private class Node {
	Color color;
	int distance;
	Node father;

	public Node (Color c) {
	    color = c;
	    distance = double.POSITIVE_INFINITY;
	    father = null;
	}
    }
    
    public SeamCarver(Picture picture) {
	if (picture == null)
	    throw new java.lang.NullPointerException();
	
	height = picture.height();
	width = picture.width();
	
	myPicture = new Node[w][h];

	for (int i = 0; i < width; i++)
	    for (int j = 0; j < height; j++) {
		myPicture[i][j] = new Node(picture.get(i, j));

		if (j == 0) {
		    myPicture[i][j].distance = energy(i, j); 
		}
	    }
	
    } // create a seam carver object based on the given picture

    public Picture picture() {

    } // current picture

    public int width() {
	return width;
    } // width of current picture

    public int height() {
	return height;
    } // height of current picture

    public double energy(int x, int y) {
	if (x < 0 || x > width - 1 || y < 0 || x > height - 1)
	    throw new java.lang.IndexOutOfBoundsException();
	
	double Rx, Gx, Bx, Ry, Gy, By;

	Rx = myPicture[(x + 1)%width][j].getRed() - myPicture[(x - 1 + width)%width][j].getRed();
	Gx = myPicture[(x + 1)%width][j].getGreen() - myPicture[(x - 1 + width)%width][j].getGreen();
	Bx = myPicture[(x + 1)%width][j].getBlue() - myPicture[(x - 1 + width)%width][j].getBlue();

	Ry = myPicture[x][(j + 1)%height].getRed() - myPicture[x][(j - 1 + height)%height].getRed();
	Gy = myPicture[x][(j + 1)%height].getGreen() - myPicture[x][(j - 1 + height)%height].getGreen();
	By = myPicture[x][(j + 1)%height].getBlue() - myPicture[x][(j - 1 + height)%height].getBlue();

	double energy = Math.sqrt(Rx*Rx + Gx*Gx + Bx*Bx + Ry*Ry + Gy*Gy + By*By);

	return energy;
    } // energy of pixel at column x and row y

    public int[] findHorizontalSeam() {

	for (int i = 0; i < width; i++) {
	    for (int j = 0; j < height; j++) {
		if (validate(i + 1, j)) {
		    int newerDistance = energy(i + 1, j) + myPicture[i][j].distance;
		    if (newerDistance < myPicture[i + 1][j].distance) {
			myPicture[i + 1][j].distance = newerDistance;
			myPicture[i + 1][j].father = myPicture[i][j];
		    }
		}

		if (validate(i + 1, j + 1)) {
		    int newerDistance = energy(i + 1, j + 1) + myPicture[i][j].distance;
		    if (newerDistance < myPicture[i + 1][j + 1].distance) {
			myPicture[i + 1][j + 1].distance = newerDistance;
			myPicture[i + 1][j + 1].father = myPicture[i][j];
		    }
		}

		if (validate(i + 1, j - 1)) {
		    int newerDistance = energy(i + 1, j - 1) + myPicture[i][j].distance;
		    if (newerDistance < myPicture[i + 1][j - 1].distance) {
			myPicture[i + 1][j - 1].distance = newerDistance;
			myPicture[i + 1][j - 1].father = myPicture[i][j];
		    }
		}
	    }

	    Node minor = myPicture[widht - 1][0];
	    int[] horizontal = new int[height - 1];
	    
	    for (int j = 1; j < height; j++) {
		if (myPicture[widht - 1][j].distance < minor.distance)
		    minor = myPicture[width - 1][j];
	    } 
	    
	
	
    } // sequence of indices for horizontal seam

    public int[] findVerticalSeam() {
	

    } // sequence of indices for vertical seam
    public void removeHorizontalSeam(int[] seam) {
	if (picture == null)
	    throw new java.lang.NullPointerException();
	if (height == 1)
	    throw new java.lang.IllegalArgumentException();
	
	

    } // remove horizontal seam from current picture

    public void removeVerticalSeam(int[] seam) {
	if (picture == null)
	    throw new java.lang.NullPointerException();
	if (width == 1)
	    throw new java.lang.IllegalArgumentException();

    } // remove vertical seam from current picture

    public static void main(String[] args) {

    } // do unit testing of this class

}
