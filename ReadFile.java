/**
 * Author: Mark Gapasin
 * Discrete Hopfield Neural Network
 * Class read in text files that will be used for training or testing
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class ReadFile {

    private static Scanner input;
    protected int dimensions;
    protected int totalTraining;

    /**
     * Method checks if files are able to be opened or not
     * @param fileName is the name of the file being opened
     * @return is a File class object of the file being opened
     */
    public Scanner openFile(String fileName){
        try{
            input = new Scanner(new File(fileName));
        }
        catch (Exception e){
            System.out.println("Error! Could not open file: " + fileName);

        }
        return input;
    }

    /**
     * Reads a text file where the first two lines help set up the structure of the net training
     * example: 100	(dimension of the image vectors)
     *          5	(number of the image vectors)
     * Remaining integers represent the image(s) to recognize
     * @return ArrayList of the first 2 lines of the file
     */
    public ArrayList<Integer> readStructure() {
        ArrayList<Integer> contents;
        contents = new ArrayList<Integer>();
        int structureSize;

        // get structure of training
        for (int i = 0; i < 2; i++) {
            if (input.hasNextInt()) {
                structureSize = input.nextInt();
                contents.add(structureSize);
            } else {
                input.nextLine();
                structureSize = input.nextInt();
                contents.add(structureSize);
            }
        }
        input.nextLine();
        return contents;
    }

    /**
     * Reads in a file, parses its data, and creates Hopfield obects for training
     * @return An ArrayList of Hopfield objects
     */
    public ArrayList<Hopfield> readfile(){
        ArrayList<Integer> contents = new ArrayList<Integer>();
        ArrayList<Integer> img_input = new ArrayList<Integer>();
        ArrayList<Integer> target = new ArrayList<Integer>();
        Hopfield hopfield = new Hopfield();
        ArrayList<Hopfield> hopfieldList = new ArrayList<Hopfield>();

        //set up structure
        ArrayList<Integer> structure = this.readStructure();
        input.nextLine();

        dimensions = structure.get(0);
        totalTraining = structure.get(1);


        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (line.equals("")) {
                hopfield.setDimensions(dimensions);
                hopfield.setNumOfImageVectors(totalTraining);
                hopfield.setColHeight(hopfield.getRowLength(), dimensions);
                hopfieldList.add(hopfield);
                hopfield = new Hopfield();
                continue;
            } else {
                String[] x;
                x = line.split("\\s+");
                hopfield.setRowLength(x.length);
                for (int z = 0; z < x.length; z++) {
                    int in = Integer.parseInt(x[z]);
                    // set inputs
                    hopfield.setInputs(in);
                    target.add(in);

                }
                hopfield.setTarget(target);
                target = new ArrayList<Integer>();
            }
        }
        hopfield.setDimensions(dimensions);
        hopfield.setNumOfImageVectors(totalTraining);
        hopfield.setColHeight(hopfield.getRowLength(), dimensions);
        hopfieldList.add(hopfield);

        // Alert user if dimensions are not equal
        for (Hopfield h : hopfieldList){
            if(h.getColHeight()*h.getRowLength() != dimensions){
                System.out.println("Error: Dimensions are uneven. Can not train with different sizes.");
                System.exit(0);
            }
        }
        return hopfieldList;
    }

    /**
     * Reads in a file, parses its data, and creates Hopfield obects for testing
     * @return An ArrayList of Hopfield objects
     */
    public ArrayList<Hopfield> readTestFile(){
        Hopfield hopfield = new Hopfield();
        ArrayList<Hopfield> hopfieldList = new ArrayList<Hopfield>();

        //set up structure
        ArrayList<Integer> structure = this.readStructure();
        input.nextLine();

        dimensions = structure.get(0);
        totalTraining = structure.get(1);


        while (input.hasNextLine()) {
            String line = input.nextLine();
            hopfield.setDimensions(dimensions);
            hopfield.setNumOfImageVectors(totalTraining);
            if(!line.equals("")){
                String[] x;
                x = line.split("\\s+");
                hopfield.setRowLength(x.length);
                hopfield.setColHeight(hopfield.getRowLength(), dimensions);
                for (int z = 0; z < x.length; z++) {
                    int in = Integer.parseInt(x[z]);
                    // set inputs
                    hopfield.setInputs(in);
                }
            }
            else if(line.equals("")){
                hopfieldList.add(hopfield);
                hopfield = new Hopfield();
            }
        }
        hopfieldList.add(hopfield);

        // Alert user if dimensions are not equal
        for (Hopfield h : hopfieldList){
            if(h.getColHeight()*h.getRowLength() != dimensions){
                System.out.println("Error: Dimensions are uneven. Can not train with different sizes.");
                System.exit(0);
            }
        }
        return hopfieldList;
    }

    /**
     * Reads in a trained file, parses its data, and creates Hopfield obects
     * @return An ArrayList of Hopfield objects
     */
    public ArrayList<Hopfield> readTrainedFile(ArrayList<Hopfield> hopFieldList){

        ArrayList<Integer> w = new ArrayList<Integer>();
        ArrayList<Integer> t = new ArrayList<Integer>();

        //set up structure
        ArrayList<Integer> structure = this.readStructure();
        input.nextLine();

        dimensions = structure.get(0);
        if (dimensions != hopFieldList.get(0).getDimensions()){
            System.out.println("Error: Dimensions are uneven. Can not train with different sizes.");
            System.exit(0);
        }

        int count = 0;
        int hopfieldIndex = 0;
        ArrayList<ArrayList<Integer>> wList = new ArrayList<ArrayList<Integer>>();
        String line = input.nextLine();
        while(input.hasNextLine()) {
            // input
            if (line.equals("Trained Weights: ")) {
                line = input.nextLine();
                while (!line.equals("")) {
                    String[] x;
                    x = line.split("\\s+");
                    for (int col = 0; col < dimensions; col++) {
                        int in = Integer.parseInt(x[col]);
                        w.add(in);
                    }
                    wList.add(w);
                    w = new ArrayList<Integer>();
                    line = input.nextLine();
                }
                line = input.nextLine();
            }
            // target
            else if (line.equals("Image Recognized: ")) {
                line = input.nextLine();
                while (!line.equals("")) {
                    String[] x;
                    x = line.split("\\s+");
                    for (int i = 0; i < x.length; i++) {
                        int in = Integer.parseInt(x[i]);
                        t.add(in);
                    }
                    hopFieldList.get(hopfieldIndex).setTarget(t);
                    t = new ArrayList<Integer>();
                    if (input.hasNextLine()) {
                        line = input.nextLine();
                    } else break;
                    count++;
                }
                hopfieldIndex++;
                count = 0;
            }
            else {
                if(input.hasNextLine()){
                    line = input.nextLine();
                }
                else break;
            }

        }
        for(Hopfield h: hopFieldList){
            for(int row = 0; row < h.getDimensions(); row++){
                h.setTrainedWeights(wList.get(row));
            }
        }
        return hopFieldList;
    }

    /**
     * Closes a text file that has been opened
     */
    public void closeFile(){
        input.close();
    }
}
