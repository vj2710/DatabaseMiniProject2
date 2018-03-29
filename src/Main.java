import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Main {
	
	public static int ioCount=0;
    public static void main(String[] args) throws IOException {
    		
        final long startTime = System.currentTimeMillis();
        ArrayList<Map<String,Integer>> arrMemoryVariables;

        Phase1 phase1 = new Phase1();
        arrMemoryVariables=phase1.beginSorting();
        System.out.println((System.currentTimeMillis() - startTime)+"phase1 milliseconds");
        System.out.println("phase 1 IO"+ioCount);

        for(int i=0;i<Constants.NO_OF_RELATIONS;i++) {
            Phase2 phase2 = new Phase2();
            String phase2MergedFilePath = "";
            String phase1FilesPath = "";
            if (i == 0 ){
                phase2MergedFilePath = Constants.PHASE2_MERGED_FILE_PATH1;
                phase1FilesPath = Constants.PHASE1_FILE_PATH1;
            }else{
                phase2MergedFilePath = Constants.PHASE2_MERGED_FILE_PATH2;
                phase1FilesPath = Constants.PHASE1_FILE_PATH2;
            }
            phase2.readAndMergePhase2(arrMemoryVariables.get(i).get(Constants.FILENUMBER), arrMemoryVariables.get(i).get(Constants.FILESIZE),phase1FilesPath,phase2MergedFilePath);
            int  IOCountT1=0 ;
            if(i == 0) {
            		IOCountT1 = ioCount - 105;
                //System.out.println(IOCountT1 + "T1");

            }else {
            		int IOCountT2 = ioCount;
                 //System.out.println(IOCountT2 + "T2");
            }
        }
        System.out.println((System.currentTimeMillis() - startTime)+"phase2 milliseconds");
        System.out.println("phase 2 IO"+ioCount);
    }
}
