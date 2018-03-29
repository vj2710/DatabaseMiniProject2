public class Constants {
	
    public static final int STUDENT_ID_S = 0;
    public static final int STUDENT_ID_E = 8;
    public static final int END_POSITION = 100;
    public static long MAINMEMORYSIZE;
    public static final int SIZEPERTUPLE = 100;
    public static int MAINMEMORYTUPLES = (int)(MAINMEMORYSIZE/SIZEPERTUPLE);
    public static final int TUPLESINBLOCK = 40;
    public static final double BLOCKSIZE = Math.pow(2,12);
    public static String FILESIZE="filesSize";
    public static String FILENUMBER="fileNumber";
    public static int FILESIZE1 = 0 ;
    public static final int NO_OF_RELATIONS=2;
    public static final String PHASE1_FILE_PATH="resources/phase1Files_T1/Sorted";
    public static final String BAG_DIFF_FILE_PATH="resources/BagDifference";

    public static final String PHASE2_MERGED_FILE_PATH1="resources/FinalMerged1";
    public static final String PHASE2_MERGED_FILE_PATH2="resources/FinalMerged2";
    public static final String PHASE2_MERGED_FILE_PATH="resources/FinalMerged";
    public static final String PHASE1_FILE_PATH1="resources/phase1Files_T1/Sorted";
    public static final String PHASE1_FILE_PATH2="resources/phase1Files_T2/Sorted";
    public static final String BAG_FILE_PATH= "resources/bag";

    
    
    public static void initializeMainMemory(){
    		MAINMEMORYSIZE = (long) (0.5*Runtime.getRuntime().freeMemory());  /*need to uncomment for 5mb*/
    		MAINMEMORYTUPLES = (int)(MAINMEMORYSIZE/SIZEPERTUPLE);
    }
}
