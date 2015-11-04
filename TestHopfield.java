/**
 * Author: Mark Gapasin
 * Discrete Hopfield Neural Network
 * Class is used to test the network if it can recognize a stored image
 */

import java.util.ArrayList;
import java.util.Collections;

public class TestHopfield {
    /**
     * Calculates the y_in value and uses an activation function to
     * get y output of a Hopfield object
     * @param hopfieldList A list of Hopfield objects
     * @return A list of Hopfield objects with results set in its attributes
     */
    public ArrayList<Hopfield> y_In(ArrayList<Hopfield> hopfieldList){
        int num = 1;
        for(Hopfield hopfield : hopfieldList) {
            ArrayList<Integer> xInput = hopfield.getInputs();
            ArrayList<Integer> yInput = new ArrayList<Integer>(hopfield.getInputs()); // x = y
            ArrayList<Integer> orderList;
            int yIn = 0;
            int epoch = 0;
            boolean converged = false;
            while (!converged) {
                converged = true;
                orderList = shuffleList(xInput.size()); //randomize order for training
                for (int col : orderList) {
                    for (int row = 0; row < xInput.size(); row++) {
                        yIn = yIn + yInput.get(row) * hopfield.getTrainedWeights(row, col);
                    }
                    yIn = yIn + xInput.get(col);
                    int newY = activationFunction(yIn, yInput.get(col));
                    // update y input to broadcast
                    if (yInput.get(col) != newY) {
                        yInput.set(col, newY);
                        converged = false;
                    }
                    yIn = 0; // reset yIn
                }
                epoch++;
            }
            System.out.println("Vector Image " + num + " converged after " + epoch + " epoch(s).");
            hopfield.setResultsList(formatResults(yInput, hopfield.getRowLength(), hopfield.getColHeight()));
            num++;
        }
        return hopfieldList;
    }

    /**
     * Formats the new y output results from a 1D Arraylist to a 2D ArrayList
     * @param results List of y outputs for each Hopfield object
     * @param col Column size to format results to
     * @param row Row to size to format results to
     * @return An ArrayList of Hopfield objects
     */
    public ArrayList<ArrayList<Integer>> formatResults(ArrayList<Integer> results, int col, int row){
        ArrayList<ArrayList<Integer>> formattedResults = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> temp = new ArrayList<Integer>();
        int index = 0;
        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){
                temp.add(results.get(index));
                index++;
            }
            formattedResults.add(temp);
            temp = new ArrayList<Integer>();
        }
        return formattedResults;
    }

    /**
     * Discrete activation function y = f(yin)
     * @param yIn The y_in value to y
     * @param y_i The y_i value from the y inputs
     * @return Either the integer -1, 1, or y_i
     */
    public int activationFunction(int yIn, int y_i){
        int y;
        if (yIn > 0) { y = 1; }
        else if (yIn < 0) { y = -1; }
        else y = y_i;
        return y;
    }

    /**
     * Shuffles an a list from 0 to dimension size.
     * @param dimension of weights needed for every input
     * @return A shuffled ArrayList
     */
    public static ArrayList<Integer> shuffleList(int dimension) {
        ArrayList<Integer> dataList = new ArrayList<Integer>();
        for (int i = 0; i < dimension; i++) {
            dataList.add(i);
        }
        Collections.shuffle(dataList);
        return dataList;
    }

}
