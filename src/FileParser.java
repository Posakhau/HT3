import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FileParser {

    private void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    private String filePath;
    private ArrayList<String>operationsGhost = new ArrayList<>();
    private ArrayList<String>firstParamGhost = new ArrayList<>();
    private ArrayList<String>secondParamGhost = new ArrayList<>();

    private void formParameters(String line) {
        ArrayList<String>tempArr = new ArrayList<>();
        Pattern p = Pattern.compile("\"([^\"]*)\"");
        Matcher m = p.matcher(line);

        //System.out.println("FROM LINE: ");

        while (m.find()) {
            System.out.println("START LINE ==========");
            String cath = m.group(1);
            tempArr.add(cath);
            System.out.println("CATH = "+cath+"-LENGTH="+cath.length());
            System.out.println("END LINE ==========");
        }

        System.out.println("START TEMP ARR ==========");
        printArrayList(tempArr);
        System.out.println("END TEMP ARR ==========");


        if (tempArr.size()>1) {
                firstParamGhost.add(tempArr.get(0));
                secondParamGhost.add(tempArr.get(1));
                //System.out.println("Line "+line+" have 2 parameters");
         }

         if (tempArr.size()==1) {
             firstParamGhost.add(tempArr.get(0));
             secondParamGhost.add("0");
         }
            else {
                //System.out.println("Line "+line+" have 1 parameter");
               //firstParamGhost.add(tempArr.get(0));
            //secondParamGhost.add("0");
            }

    }

    //SERVICE
    private void printArray(String[] arr) {
        for (int i=0;i<arr.length;++i) {
            System.out.println(arr[i]+"-LENGTH:"+arr[i].length());
        }
    }
    private void printArrayList(ArrayList<String>arr) {
        for (int i=0;i<arr.size();++i) {
            System.out.println(arr.get(i)+"-LENGTH:"+arr.get(i).length());
        }
    }

    public DataContainer parseFile(String path) throws Exception {
        setFilePath(path);
        FileReader reader = new FileReader(path);
        BufferedReader buffReader = new BufferedReader(reader);
        String line;
        ArrayList<String> lines = new ArrayList<>();
        while ((line = buffReader.readLine()) != null) {
            formParameters(line);
            lines.add(line);
            String[] splitedLines = line.split("\\s+");
            operationsGhost.add(splitedLines[0]);
        }
        DataContainer container = new DataContainer(operationsGhost,firstParamGhost,secondParamGhost);
        return container;
    }
} // end of class
