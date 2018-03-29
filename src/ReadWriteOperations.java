import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class ReadWriteOperations {

    public static void writeFile(List<StudentModel> sortedStudentRecords, String fileName) throws IOException {

        BufferedWriter out = null;
        out = new BufferedWriter(new FileWriter(fileName + ".txt", true));

        String singleTuple;
        Main.ioCount++;
        try {
            for (int i = 0; i < sortedStudentRecords.size(); i++) {

                singleTuple = String.format("%08d", sortedStudentRecords.get(i).getStudentID()) +
                        sortedStudentRecords.get(i).getTextString() +
                        '\n';

                out.write(singleTuple);
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    public static void writeBagDifferenceToFile(String strToBeWritten, String fileName) throws IOException {
    		
    		Main.ioCount++;
        BufferedWriter out = null;
        out = new BufferedWriter(new FileWriter(fileName + ".txt", true));
        try{
            out.write(strToBeWritten);
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    public static List<StudentModel> readFromFile(int startBytePosition, int blockSize, File file) {
    		
    		Main.ioCount++;
        long currenttIME = System.currentTimeMillis();
        List<StudentModel> tupleList = new ArrayList<>();
        int counter = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.skip(startBytePosition * 101);
            for (String line; (line = br.readLine()) != null && counter < blockSize; counter++) {
                StudentModel tuple = new StudentModel();
                tuple.setTuple(line);
                tupleList.add(tuple);
                
            }
            br.close();

        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tupleList;
    }

    public static List<String> readFromFileWithBR(BufferedReader br, int startBytePosition, int blockSize) {
    		
    		Main.ioCount++;
        List<String> tupleList = new ArrayList<>();
        int counter = 0;
        try {
            String line;
            while(counter<blockSize && (line = br.readLine())!= null){
                
                tupleList.add(line);
                counter++;
                
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tupleList;
    }


}
