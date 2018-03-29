import java.io.File;
import java.util.List;

public class DataLoadThread extends Thread {

    private List<List<StudentModel>> listStudentRecords;
    private int position;
    private int fileStartPosition;
    private int tuplesPerSublist;
    private File file;

    public int getPosition() {
        return position;
    }

    @Override
    public void run() {
        super.run();
        listStudentRecords.get(position).addAll(ReadWriteOperations.readFromFile(fileStartPosition, tuplesPerSublist, file));
    }

    DataLoadThread(List<List<StudentModel>> listStudentRecords, int position, int fileStartPosition, int tuplesPerSublist, File file){
        this.listStudentRecords = listStudentRecords;
        this.position = position;
        this.fileStartPosition = fileStartPosition;
        this.tuplesPerSublist = tuplesPerSublist;

        this.file = file;
    }
}
