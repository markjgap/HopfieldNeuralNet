/**
 * Author: Mark Gapasin
 * Discrete Hopfield Neural Network
 * User interface that will train and test a Hopfield model
 */

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainHopfieldNetwork {

    public static void main (String[] args){

        Scanner input = new Scanner(System.in);
        ArrayList<Hopfield> hopfieldList = new ArrayList<Hopfield>();
        int userInputInt;
        String fileInput;

        System.out.println("Discrete Hopfield Neural Networks");

        while(true) {
            try {
                System.out.println("Enter 1 to train, 2 to test, or 0 to quit the network: ");
                userInputInt = input.nextInt();

                switch (userInputInt){

                    // train network
                    case 1:
                        System.out.println("\nEnter file to train Hopfield network (include extension): ");
                        fileInput = input.next();

                        // set up structure of network
                        // dimension size and number of images to train
                        ReadFile r = new ReadFile();
                        r.openFile(fileInput);
                        hopfieldList = r.readfile();
                        r.closeFile();

                        // initialize file name for trained weights
                        System.out.println("\nEnter file name to save trained Hopfield network weights (include extension): ");
                        String fileOutput = input.next();
                        System.out.println();

                        // train weights
                        TrainHopfield train = new TrainHopfield();
                        Hopfield trainedHopfield = new Hopfield();
                        trainedHopfield = train.trainWeights(hopfieldList);

                        WriteFile out = new WriteFile();
                        out.writeFile(fileOutput, trainedHopfield, hopfieldList);
                        System.out.print("Training complete. Trained weights written to " + fileOutput + "\n\n");
                        break;

                    // test network
                    case 2:
                        System.out.println("\nEnter file to test Hopfield network (include extention): ");
                        fileInput = input.next();

                        // set up structure of network
                        // dimension size and number of images to train
                        ReadFile testFile = new ReadFile();
                        testFile.openFile(fileInput);
                        //set up test vectors
                        ArrayList<Hopfield> hopfieldTestList = testFile.readTestFile();
                        testFile.closeFile();

                        // get trained weights to test with
                        System.out.println("\nEnter file name that contains trained weights to use for testing (include extension: ");
                        String fileTrainedWts = input.next();
                        ReadFile r1 = new ReadFile();
                        r1.openFile(fileTrainedWts);
                        // hoplist initialized with input, target, weights for each vector image
                        ArrayList<Hopfield> trainedHopfieldList = r1.readTrainedFile(hopfieldTestList);
                        TestHopfield testResults = new TestHopfield();
                        ArrayList<Hopfield> results = testResults.y_In(trainedHopfieldList);

                        //initialize file name for test results
                        System.out.println("\nEnter file name to save the tested Hopfield network results (include extension): ");
                        fileOutput = input.next();
                        WriteFile output = new WriteFile();
                        output.writeResultsFile(fileOutput, results);
                        System.out.println();

                        break;

                    // quit program
                    case 0:
                        System.out.print("Exited program.\n");
                        System.exit(0);

                    default:
                        System.out.println("Invalid input.\n");
                }
            } catch (InputMismatchException e){
                System.out.println("Invalid input!");
                throw (e);
            }
        }


    }
}
