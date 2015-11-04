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

    public void setTrainedWeights(ArrayList<Integer> x){
        trainedWeights.add(x);
    }

    public void setTrainedWeightsValue(int value, int row, int col){
        trainedWeights.get(row).set(col, value);
    }

    public void setInputs(int x){
        inputs.add(x);
    }

    public void setTarget(ArrayList<Integer> x){
        target.add(x);
    }

    public void setResultsList(ArrayList<ArrayList<Integer>> x){
        resultsList = x;
    }

    public void setDimensions(int x){
        dimensions = x;
    }

    public void setRowLength(int r){
        rowLength = r;
    }

    public void setColHeight(int c, int dimensions){
        colHeight = dimensions/c;
    }

    public void setNumOfImageVectors(int x){
        numOfImageVectors = x;
    }

    public ArrayList<Integer> getInputs(){
        return inputs;
    }

    public int getDimensions(){ return dimensions; }

    public int getNumOfImageVectors(){
        return numOfImageVectors;
    }

    public int getRowLength(){
        return rowLength;
    }

    public int getColHeight(){
        return colHeight;
    }

    public int getTrainedWeights(int row, int col){
        return trainedWeights.get(row).get(col);
    }

    public int getTarget(int row, int col){
        return target.get(row).get(col);
    }

    public int getResultsList(int row, int col){
        return resultsList.get(row).get(col);
    }
}
