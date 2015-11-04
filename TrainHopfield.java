/**
 * Author: Mark Gapasin
 * Discrete Hopfield Neural Network
 * Class is used to train the network to stored an image for later recall
 */
import java.util.ArrayList;

public class TrainHopfield {

    /**
     * Calculates weights used for training network.
     * Network is an auto-associative so uses formula:
     *      x-input(transposed) * x-input
     * @param hopfieldList The list of images to recognize and store
     * @return A Hopfield object with trained weights attribute set for training
     */
    public Hopfield trainWeights(ArrayList<Hopfield> hopfieldList){

        for (Hopfield hopfield: hopfieldList){
            ArrayList<Integer> w = new ArrayList<Integer>();
            ArrayList<Integer> s = new ArrayList<Integer>();
            for (int index = 0; index < hopfield.getDimensions(); index++){
                s.add(hopfield.getInputs().get(index));
            }
            // initialize w matrix
            int zeroIndex = 0;
            for (int i = 0; i < s.size(); i++){
                for (int j = 0; j < s.size(); j++){
                    w.add(s.get(i) * s.get(j));
                }
                hopfield.setTrainedWeights(w);
                //zeroIndex++;
                w = new ArrayList<Integer>();
            }
        }
        // add all new weights trained
        Hopfield trainedHopfield = new Hopfield();
        // initialize weight matrix to zero
        trainedHopfield = initializeWeightsMatrix(hopfieldList.get(0).getDimensions(), hopfieldList.get(0).getDimensions());
        ArrayList<Integer> newWeightList = new ArrayList<Integer>();

        for(Hopfield hopfield: hopfieldList){
            int zeroIndex = 0;
            for(int row = 0; row < hopfield.getDimensions(); row++){
                for(int col =0; col <hopfield.getDimensions(); col++){
                    if (col != zeroIndex) {
                        int val = trainedHopfield.getTrainedWeights(row, col) + hopfield.getTrainedWeights(row, col);
                        trainedHopfield.setTrainedWeightsValue(val, row, col);
                    }
                    // initialize zero's diagonal
                    else {
                        trainedHopfield.setTrainedWeightsValue(0, row, col);
                    }
                }
                zeroIndex++;
            }
        }

        return trainedHopfield; // return trained weights
    }

    /**
     * Initializes the weights matrix dimensions and sets each position to zero
     * @param row Row size of the matrix to set to
     * @param col Column size of the matrix to set to
     * @return A Hopfield object with its trained weights attribute set to the matrix specified
     */
    public Hopfield initializeWeightsMatrix(int row, int col){
        ArrayList<Integer> w = new ArrayList<Integer>();
        Hopfield hopfield = new Hopfield();
        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){
                w.add(0);
            }
            hopfield.setTrainedWeights(w);
            w = new ArrayList<Integer>();
        }
        return hopfield;
    }

}
