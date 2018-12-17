import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Timer;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExecutorController {

    private void setPrimaryURL(String primaryURL) {
        this.primaryURL = primaryURL;
    }

    private String getPrimaryURL() {
        return this.primaryURL;
    }

    private void setResource(String resource) {
        this.resource = resource;
    }

    public double getTotalTests() {return totalTests;}
    private double totalTests=0;
    private void increaseTestsAmount() {
        totalTests++;
    }

    public int getPassedTests() {return passedTests;}
    private int passedTests=0;
    private void increasePassedTests(){
        passedTests++;
    }

    public int getFailedTests() {return failedTests;}
    private int failedTests =0;
    private void increaseFailedTests() {
        failedTests++;
    }

    private double getTotalTime() {return totalTime;}
    private double totalTime=0;
    private void addTimeValue(double timeValue) {
        totalTime=totalTime+timeValue;
    }

    private double averageTime=0;
    private double getAverageTime() {
        return (getTotalTime()/getTotalTests());
    }

    private String resource="undefined";
    private String primaryURL="undefined";
    private URL url;

    private ExecutorController() {}

    public ExecutorController(DataContainer container) {
        ReportCreator reportCreator = new ReportCreator(executeList(container));
    }

    //SERVICE METHODS
    private void printInstructionsArray(ArrayList<String[]>arrayList) {
        for (int i=0;i<arrayList.size();++i) {
            for (int j=0;j<arrayList.get(i).length;++j) {
                System.out.print(arrayList.get(i)[j]);
            }
            System.out.println();
        }
    }

    // OPERATIVE METHODS


    private String[] open (String path, String timeout) {

        boolean testParameter = true;
        long startTime = System.currentTimeMillis();
        long timerValue = Long.parseLong(timeout);
        String[] reportArray = {"resultSign","commandsList","executionTime"};
        setPrimaryURL(path);
        StringBuilder response = new StringBuilder();
        ExecutorService service = Executors.newSingleThreadExecutor();

        try {
            Runnable r = new Runnable() {
                @Override
                public void run() {

                    try {
                        url = new URL(getPrimaryURL());
                        URLConnection connect = url.openConnection();
                        BufferedReader br = new BufferedReader(new InputStreamReader(connect.getInputStream()));

                        //Thread.sleep(10000); - for checking timeout function
                        while ((resource = br.readLine()) != null) {
                            response.append(resource);
                        }
                        br.close();

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    /*
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    */
                    setResource(response.toString());
                }
            };
            Future<?> f = service.submit(r);
            f.get(timerValue, TimeUnit.SECONDS);
        }
        catch (final InterruptedException e) {
            e.printStackTrace();
        }
        catch (final TimeoutException e) {
            testParameter = false;
        }
        catch (final ExecutionException e) {
        }
        finally {
            service.shutdown();
        }

        long endTime = System.currentTimeMillis();
        Double fTime = (double)(endTime - startTime);
        addTimeValue(fTime/1000);
        reportArray[2]=String.valueOf(fTime/1000);

        StringBuilder reppForm = new StringBuilder();
        reppForm.append(" [open "+"\""+getPrimaryURL()+"\""+" "+"\""+timeout+"\"]");
        reportArray[1]=reppForm.toString();

        if(testParameter) {
            reportArray[0]="+";
            increasePassedTests();
        } else {
            reportArray[0]="!";
            increaseFailedTests();
        }
        increaseTestsAmount();
        return reportArray;
    }

    private String[] checkLinkPresentByHref (String href, String fakeParam) {

        increaseTestsAmount();
        boolean testParameter = true;
        long startTime = System.currentTimeMillis();
        String[] reportArray = {"resultSign","commandsList","executionTime"};

        final Pattern pattern = Pattern.compile("href=\"(.*?)\"", Pattern.DOTALL);
        final Matcher matcher = pattern.matcher(resource);
        matcher.find();
        String rsl = matcher.group(1);

        if (href.equals(rsl)) {
            testParameter=true;
        } else {
            testParameter = false;
        }

        long endTime = System.currentTimeMillis();
        Double fTime = (double)(endTime - startTime);
        addTimeValue(fTime/1000);
        reportArray[2]=String.valueOf(fTime/1000);

        StringBuilder reppForm = new StringBuilder();
        reppForm.append(" [checkLinkPresentByHref "+"\""+href+"\""+"] ");
        reportArray[1]=reppForm.toString();

        if(testParameter) {
            reportArray[0]="+";
            increasePassedTests();
        } else {
            reportArray[0]="!";
            increaseFailedTests();
        }
        return reportArray;
    }

    private String[] checkLinkPresentByName (String linkName, String fakeParam) {

        increaseTestsAmount();
        boolean testParameter = true;
        long startTime = System.currentTimeMillis();
        String[] reportArray = {"resultSign","commandsList","executionTime"};

        final Pattern pattern = Pattern.compile("name=\"(.*?)\"", Pattern.DOTALL);
        final Matcher matcher = pattern.matcher(resource);
        matcher.find();
        String rsl = matcher.group(1);

        if (linkName.equals(rsl)) {
            testParameter=true;
        } else {
            testParameter = false;
        }

        long endTime = System.currentTimeMillis();
        Double fTime = (double)(endTime - startTime);
        addTimeValue(fTime/1000);
        reportArray[2]=String.valueOf(fTime/1000);

        StringBuilder reppForm = new StringBuilder();
        reppForm.append(" [checkLinkPresentByHref "+"\""+linkName+"\""+"] ");
        reportArray[1]=reppForm.toString();


        if(testParameter) {
            reportArray[0]="+";
            increasePassedTests();
        } else {
            reportArray[0]="!";
            increaseFailedTests();
        }
        return reportArray;
    }

    private String[] checkPageTitle (String pageTitle,String fakeParam) {
        increaseTestsAmount();
        boolean testParameter = true;
        long startTime = System.currentTimeMillis();
        String[] reportArray = {"resultSign","commandsList","executionTime"};

        final Pattern pattern = Pattern.compile("<title>(.+?)</title>", Pattern.DOTALL);
        final Matcher matcher = pattern.matcher(resource);
        matcher.find();
        String rsl = matcher.group(1);

        if (pageTitle.equals(rsl)) {
            testParameter=true;
        } else {
            testParameter = false;
        }

        long endTime = System.currentTimeMillis();
        Double fTime = (double)(endTime - startTime);
        addTimeValue(fTime/1000);
        reportArray[2]=String.valueOf(fTime/1000);

        StringBuilder reppForm = new StringBuilder();
        reppForm.append(" [checkPageTitle "+"\""+pageTitle+"\""+"] ");
        reportArray[1]=reppForm.toString();


        if(testParameter) {
            reportArray[0]="+";
            increasePassedTests();
        } else {
            reportArray[0]="!";
            increaseFailedTests();
        }
        return reportArray;
    }

    private String[] checkPageContains (String text,String fakeParam) {
        increaseTestsAmount();
        boolean testParameter = true;
        long startTime = System.currentTimeMillis();
        String[] reportArray = {"resultSign","commandsList","executionTime"};

         if(!resource.toLowerCase().contains(text.toLowerCase())) {
             testParameter = false;
         }

        long endTime = System.currentTimeMillis();
        Double fTime = (double)(endTime - startTime);
        addTimeValue(fTime/1000);
        reportArray[2]=String.valueOf(fTime/1000);

        StringBuilder reppForm = new StringBuilder();
        reppForm.append(" [checkPageContains "+"\""+text+"\""+"] ");
        reportArray[1]=reppForm.toString();

        if(testParameter) {
            reportArray[0]="+";
            increasePassedTests();
        } else {
            reportArray[0]="!";
            increaseFailedTests();
        }
        return reportArray;
    }

    private String secondReport() {
        StringBuilder sr = new StringBuilder();
        sr.append("Total tests: "+getTotalTests()+System.getProperty("line.separator"));
        sr.append("Passed/Failed: "+getPassedTests()+"/"+getFailedTests()+System.getProperty("line.separator"));
        sr.append("Total time: "+getTotalTime()+System.getProperty("line.separator"));
        sr.append("Average time: "+getAverageTime()+System.getProperty("line.separator"));
        System.out.println(sr.toString());
        return sr.toString();
    }

    private ReportData executeList (DataContainer container) {

        ArrayList <String[]> reportArray = new ArrayList<>();
        ArrayList<String>opArray = new ArrayList<>();
        ArrayList<String>fpArray = new ArrayList<>();
        ArrayList<String>spArray = new ArrayList<>();

        opArray = container.getOperatorsContainer();
        fpArray = container.getFirstParametersContainer();
        spArray = container.getSecondParametersContainer();

        for (int i=0;i<opArray.size();++i) {

            if (opArray.get(i).equals("open")) {
                reportArray.add(open(fpArray.get(i),spArray.get(i)));
            }

            if (opArray.get(i).equals("checkPageTitle")) {
                reportArray.add(checkPageTitle(fpArray.get(i),spArray.get(i)));
            }

            if (opArray.get(i).equals("checkPageContains")) {
                reportArray.add(checkPageContains(fpArray.get(i),spArray.get(i)));
            }

            if (opArray.get(i).equals("checkLinkPresentByHref")) {
                reportArray.add(checkLinkPresentByHref(fpArray.get(i),spArray.get(i)));
            }

            if (opArray.get(i).equals("checkLinkPresentByName")) {
                reportArray.add(checkLinkPresentByName(fpArray.get(i),spArray.get(i)));
            }
        }
        ReportData reportData = new ReportData(reportArray,secondReport());
        return reportData;
    }
}
