import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;

public class Phase1 {

    static ReadWriteOperations readWriteOperations = new ReadWriteOperations();
    static  ArrayList<Map<String,Integer>> arrMemoryVariables = new ArrayList();
    public static ArrayList<Map<String,Integer>> beginSorting() throws IOException {
        for (int i = 1 ; i <= Constants.NO_OF_RELATIONS; i++) {
            readFileAndPrepareChunks(Constants.BAG_FILE_PATH, i);
        }
        return arrMemoryVariables;
    }

    public  static void readFileAndPrepareChunks(String fileName,int fileNumber){
    		Main.ioCount++;
    	//	Constants.initializeMainMemory();
        List<StudentModel> studentRecords = new ArrayList<StudentModel>();
        File file = new File(fileName + fileNumber +".txt");
        int sortedFileNumber = 0;
        int noOfTuples = 0;
        String prevLine = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int bytesReadTillNow = 0;
            for (String line; (line = br.readLine()) != null; ) {
//                if (prevLine.equals(line)){
//
//                    if (prevLine.substring(0,8).equals(line.substring(0,8)) == true){
//                        if (prevLine.substring(8,18).equals(line.substring(8,18)) == false){
//                            System.out.println("Is not equal");
//                        }
//                    }
//                }

                noOfTuples = noOfTuples + 1;
                bytesReadTillNow += line.getBytes().length;
                Constants.initializeMainMemory();
                if (bytesReadTillNow >= Constants.MAINMEMORYSIZE){
                    sortedFileNumber++;
                    sortAndWriteData(sortedFileNumber,studentRecords,fileNumber);
                    studentRecords.clear();
                    bytesReadTillNow = line.getBytes().length;
                }
                StudentModel tuple = new StudentModel();
                tuple.setTuple(line);
                studentRecords.add(tuple);
                
//                prevLine = line;
            }
            if (studentRecords.size()!=0) {
                sortedFileNumber++;
                sortAndWriteData(sortedFileNumber, studentRecords,fileNumber);
            }

            Map<String,Integer> map = new HashMap<String, Integer>();
            map.put(Constants.FILESIZE,noOfTuples);
            map.put(Constants.FILENUMBER,sortedFileNumber);
            arrMemoryVariables.add(map);

        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void sortAndWriteData(int sortedNumber ,List<StudentModel> arrSublist,int fileNumber){
        List<StudentModel>  arrSortedSublist = sortTuples(arrSublist);
        String path = "";
        if (fileNumber == 1){
            path = Constants.PHASE1_FILE_PATH1;
        }else{
            path = Constants.PHASE1_FILE_PATH2;
        }
        String sortedFileName = path + Integer.toString(sortedNumber);
        try {
            readWriteOperations.writeFile(arrSortedSublist,sortedFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static List<StudentModel> sortTuples(List<StudentModel> arrChunksTuples){
        Collections.sort(arrChunksTuples, new Comparator<StudentModel>() {
            @Override
            public int compare(StudentModel o1, StudentModel o2) {
                String strStudendID1 = Integer.toString(o1.getStudentID()) + o1.getTextString();
                String strStudenID2 = Integer.toString(o2.getStudentID()) + o2.getTextString();
                return strStudendID1.compareTo(strStudenID2);
            }
        });

        return arrChunksTuples;
    }




}