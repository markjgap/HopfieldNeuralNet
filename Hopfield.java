/**
 * Author: Mark Gapasin
 * Discrete Hopfield Neural Network
 * Class will have all the information needed to store and recognize/recall an image
 */

import java.util.ArrayList;

public class Hopfield {

    private ArrayList<Integer> inputs;
    private ArrayList<ArrayList<Integer>> target;
    private int dimensions;
    private int numOfImageVectors;
    private int rowLength;
    private int colHeight;
    private ArrayList<ArrayList<Integer>> trainedWeights;
    private ArrayList<ArrayList<Integer>> resultsList;


    Hopfield(){
        inputs = new ArrayList<Integer>();
        target = new ArrayList<ArrayList<Integer>>();
        trainedWeights = new ArrayList<ArrayList<Integer>>();
        resultsList = new ArrayList<ArrayList<Integer>>();
        dimensions = 0;
        numOfImageVectors = 0;
        rowLength = 0;
        colHeight = 0;
    }

    /**
     * Sets a list of trained weights
     * @param x List of weights that have been trained
     */
    public void setTrainedWeights(ArrayList<Integer> x){
        trainedWeights.add(x);
    }

    /**
     * Sets a value of trained weight
     * @param value Trained weight value to set
     * @param row Row to set the trained weight
     * @param col Column to set the trained weight
     */
    public void setTrainedWeightsValue(int value, int row, int col){
        trainedWeights.get(row).set(col, value);
    }

    /**
     * Sets the inputs to the net
     * @param x Value of the input
     */
    public void setInputs(int x){
        inputs.add(x);
    }

    /**
     * Sets the target which is what the net should recognize
     * @param x List of target weights to recognize
     */
    public void setTarget(ArrayList<Integer> x){
        target.add(x);
    }

    /**
     * Sets list of y output results after testing
     * @param x A list of y output
     */
    public void setResultsList(ArrayList<ArrayList<Integer>> x){
        resultsList = x;
    }

    /**
     * Sets the dimension of the image based on size
     * @param x Size of the image
     */
    public void setDimensions(int x){
        dimensions = x;
    }

    /**
     * Sets the row length of the image being recognized
     * @param r Size of the width of the image
     */
    public void setRowLength(int r){
        rowLength = r;
    }

    /**
     * Calculates and sets the column height of the image being recognized
     * @param c Row length of the image
     * @param dimensions Dimension size of the image
     */
    public void setColHeight(int c, int dimensions){
        colHeight = dimensions/c;
    }

    /**
     * Sets the number of images being trained of tested
     * @param x Number of images
     */
    public void setNumOfImageVectors(int x){
        numOfImageVectors = x;
    }

    /**
     * Gets a list of inputs to was stored for training or testing
     * @return The x inputs into the net
     */
    public ArrayList<Integer> getInputs(){
        return inputs;
    }

    /**
     * Gets the dimension size of the image stored for training or testing
     * @return Size of the image or total number of inputs to the net
     */
    public int getDimensions(){ return dimensions; }

    /**
     * Gets the number of images that was trained or tested
     * @return Total number of images
     */
    public int getNumOfImageVectors(){
        return numOfImageVectors;
    }

    /**
     * Gets the row length or column size of the stored image(s)
     * @return The length of the image
     */
    public int getRowLength(){
        return rowLength;
    }

    /**
     * Gets the column height or row size of the stored image(s)
     * @return The height of the image
     */
    public int getColHeight(){
        return colHeight;
    }

    /**
     * Gets a trained weight stored
     * @param row Row coordinates of the trained weight
     * @param col Column coordinates of the trained weight
     * @return The trained weight integer
     */
    public int getTrainedWeights(int row, int col){
        return trainedWeights.get(row).get(col);
    }

    /**
     * Gets a target value stored
     * @param row Row coordinates of the target value
     * @param col Column coordinates of the target value
     * @return The target value
     */
    public int getTarget(int row, int col){
        return target.get(row).get(col);
    }

    /**
     * Gets a value from the results list
     * @param row Row coordinates of a value from the results list
     * @param col Column coordinate of a value from the results list
     * @return The results list value
     */
    public int getResultsList(int row, int col){
        return resultsList.get(row).get(col);
    }
}
