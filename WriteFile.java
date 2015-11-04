import java.util.ArrayList;
import java.io.*;

public class WriteFile {

    /*
 * Writes the data to a text file
 * @param fName is the file name to write to
 * @param hopfieldList is a list of Hopfield objects
 */
    public void writeFile(String fName, Hopfield trainedHopfield, ArrayList<Hopfield> hopfieldList) {

        String fileName = fName;

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            // write size of dimension of each image vectors
            bufferedWriter.write(String.valueOf((hopfieldList.get(0).getDimensions()) + "\t\t(dimension of the image vectors)\n"));
            // write number of image vectors trained
            bufferedWriter.write(String.valueOf(hopfieldList.get(0).getNumOfImageVectors()) + "\t\t(number of the image vectors)");

            bufferedWriter.write("\n\nTrained Weights: \n");
            for (int row = 0; row < hopfieldList.get(0).getDimensions(); row++) {
                for (int col = 0; col < hopfieldList.get(0).getDimensions(); col++) {
                    bufferedWriter.write(trainedHopfield.getTrainedWeights(row, col) + " ");
                }
                bufferedWriter.write("\n");
            }
            int vectorImageCount = 1;
            for(Hopfield hopfield: hopfieldList) {
                bufferedWriter.write("\nImage Recognized: \n");
                for (int row = 0; row < hopfield.getColHeight(); row++) {
                    for (int col = 0; col < hopfield.getRowLength(); col++) {
                        bufferedWriter.write(hopfield.getTarget(row, col) + " ");
                    }
                    bufferedWriter.write("\n");
                }
                vectorImageCount++;
            }

            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("Error writing to file " + fileName);
            e.printStackTrace();
        }
    }

    public static void writeResultsFile(String fName, ArrayList<Hopfield> hopfieldList){
        String fileName = fName;
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Hopfield hopfield : hopfieldList) {
                bufferedWriter.write(("\n\nActual Image Recognized: \n"));
                for(int row = 0; row < hopfield.getColHeight(); row++) {
                    for (int col = 0; col < hopfield.getRowLength(); col++) {
                        if(hopfield.getResultsList(row, col) == 1) {
                            bufferedWriter.write("O");
                        }
                        else bufferedWriter.write(" ");
                    }
                    bufferedWriter.write("\n");
                }
                bufferedWriter.write("\n");
                bufferedWriter.write("Classified Target Image: \n");
                for (int row = 0; row < hopfield.getColHeight(); row++) {
                    for (int col = 0; col < hopfield.getRowLength(); col++) {
                        if(hopfield.getTarget(row, col) == 1){
                            bufferedWriter.write("O");
                        }
                        else bufferedWriter.write(" ");
                    }
                    bufferedWriter.write("\n");
                }
            }
            bufferedWriter.close();
            fileWriter.close();
        }catch (Exception e){
            System.out.println("Error writing to file " + fileName);
            e.printStackTrace();
        }
    }
}
