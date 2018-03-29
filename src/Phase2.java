import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Phase2 {

    public List<List<StudentModel>> listStudentRecords = new ArrayList<List<StudentModel>>();

    public void readAndMergePhase2(int numOfFiles, int totalSize,String phase1FilesPath,String phase2MergedFilePath){

        //calculate no. of buffers
    		

        //read part
        int[] fileStartPosition = new int[numOfFiles];
        
        Constants.initializeMainMemory();
        int noOfBuffers = (Constants.MAINMEMORYTUPLES /(Constants.TUPLESINBLOCK*(numOfFiles+1)));
        int tuplesPerSublist = noOfBuffers * Constants.TUPLESINBLOCK;
        
        for(int i=1;i<=numOfFiles; i++) {
            File file = new File(phase1FilesPath + i + ".txt");
            listStudentRecords.add(ReadWriteOperations.readFromFile(fileStartPosition[i-1], tuplesPerSublist, file));
            fileStartPosition[i-1] += tuplesPerSublist;
        }

        //merge part
        int tempMinStudentId = 0;
        int fileIndex = 0;
        int loopCount = 0;
        int i;
        List<StudentModel> sortedStudentRecords = new ArrayList<StudentModel>();
        while (totalSize >= 1) {

            for (i = 0; i < listStudentRecords.size(); i++) {

                if (listStudentRecords.get(i).size()>0) {
                    System.out.println("in loop");
                    loopCount++;
                    if (loopCount == 1) {
                        tempMinStudentId = 99999999;
                    }
                    if (listStudentRecords.get(i).get(0).getStudentID() <= tempMinStudentId) {
                        tempMinStudentId = listStudentRecords.get(i).get(0).getStudentID();
                        fileIndex = i;
                    }
                }
            }

            System.out.println("index="+fileIndex);
            System.out.println("size of list i"+listStudentRecords.get(fileIndex).size());
            sortedStudentRecords.add(getTupleByPosition(fileIndex));
            listStudentRecords.get(fileIndex).remove(0);
            loopCount = 0;
            totalSize--;

            if(listStudentRecords.get(fileIndex).size()==Constants.TUPLESINBLOCK){
//                thread wala kaam
                File file = new File(phase1FilesPath + (fileIndex + 1) + ".txt");
                DataLoadThread dataLoadThread = new DataLoadThread(listStudentRecords, fileIndex ,fileStartPosition[fileIndex], tuplesPerSublist-Constants.TUPLESINBLOCK, file);
                dataLoadThread.start();
                fileStartPosition[fileIndex] += tuplesPerSublist-Constants.TUPLESINBLOCK;
                try {
                    dataLoadThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if(sortedStudentRecords.size()==tuplesPerSublist){
                //write the output buffer to file
                try {
                    ReadWriteOperations.writeFile(sortedStudentRecords, phase2MergedFilePath);
                    sortedStudentRecords.clear();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            ReadWriteOperations.writeFile(sortedStudentRecords, phase2MergedFilePath);
            sortedStudentRecords.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public StudentModel getTupleByPosition(int position){
        StudentModel studentObj = new StudentModel();
        studentObj.setStudentID(listStudentRecords.get(position).get(0).getStudentID());
        studentObj.setTextString(listStudentRecords.get(position).get(0).getTextString());
        return studentObj;
    }
}