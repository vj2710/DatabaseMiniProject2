/*
import java.io.File;

public class SortJoin{

    public void readAndJoin(){


        int[] fileStartPosition = new int[2];

        Constants.initializeMainMemory();
        int noOfBuffers = (Constants.MAINMEMORYTUPLES /(Constants.TUPLESINBLOCK*(2+1)));
        int tuplesPerSublist = noOfBuffers * Constants.TUPLESINBLOCK;

        for(int i=1;i<=2; i++) {
            File file = new File(Constants.PHASE2_MERGED_FILE_PATH + i);
            listStudentRecords.add(ReadWriteOperations.readFromFile(fileStartPosition[i-1], tuplesPerSublist, file));
            fileStartPosition[i-1] += tuplesPerSublist;
        }

    }

}*/
