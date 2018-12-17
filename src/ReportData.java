import java.util.ArrayList;

public class ReportData {

    public ArrayList<String[]> getFirstPart() {
        return firstPart;
    }

    public void setFirstPart(ArrayList<String[]> firstPart) {
        this.firstPart = firstPart;
    }

    public String getSecondPart() {
        return secondPart;
    }

    public void setSecondPart(String secondPart) {
        this.secondPart = secondPart;
    }

    private ArrayList<String[]>firstPart = new ArrayList<>();
    private String secondPart="";

    private ReportData() {}
    public ReportData(ArrayList<String[]>firstPart,String secondPart) {
        setFirstPart(firstPart);
        setSecondPart(secondPart);
        //printInstructionsArray(getFirstPart());
    }
}

