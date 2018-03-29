public class StudentModel {

    private int studentID;
    private String textString;

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getTextString() {
        return textString;
    }

    public void setTextString(String textString) {
        this.textString = textString;
    }

    public void setTuple(String tupleString){

        this.setStudentID(Integer.parseInt(tupleString.substring(Constants.STUDENT_ID_S, Constants.STUDENT_ID_E)));
        this.setTextString(tupleString.substring(Constants.STUDENT_ID_E, Constants.END_POSITION));

    }

    public String getRecordAsString(){
        return Integer.toString(studentID) + textString;
    }
}
