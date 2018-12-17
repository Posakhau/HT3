import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ReportCreator {

    private static String REPORT_PATH = "E:report.txt";
    private ReportData reportData;
    private String secondPart;

    private ReportCreator() {}
    public ReportCreator(ReportData reportData) {
        setReportData(reportData);
        printInstructionsArray(reportData.getFirstPart());
        try {
            formReportFile();
        } catch (IOException e) {
            System.out.println("CANNOT WRITE REPORT FILE");
            e.printStackTrace();
        }
    }

    //MAIN FORMER
    private void formReportFile() throws IOException {

            FileWriter reportWriter = new FileWriter(REPORT_PATH);
            StringBuilder mainReportStringBuilder = new StringBuilder();

            ArrayList<String[]>firstPart = new ArrayList<>();
            firstPart = reportData.getFirstPart();

            System.out.println("FROM MET|HOD");
            //printInstructionsArray(firstPart);

            for (int i=0;i<firstPart.size();++i) {
                for (int j=0;j<firstPart.get(i).length;++j) {
                 System.out.print(firstPart.get(i)[j]);
                 mainReportStringBuilder.append(firstPart.get(i)[j].toString());
                }
                mainReportStringBuilder.append(System.getProperty("line.separator"));
            System.out.println();
            }









            secondPart = reportData.getSecondPart();
            mainReportStringBuilder.append(secondPart);

            reportWriter.write(mainReportStringBuilder.toString());
            reportWriter.close();
    }

    //SERVICE
    private void printInstructionsArray(ArrayList<String[]>arrayList) {
        //System.out.println("UNDER REPORT CREATOR:");
        for (int i=0;i<arrayList.size();++i) {
            for (int j=0;j<arrayList.get(i).length;++j) {
                System.out.print(arrayList.get(i)[j]);
            }
            System.out.println();
        }
    }

    //GETTERS AND SETTERS
    private void setReportData(ReportData reportData) {
        this.reportData = reportData;
    }

}
