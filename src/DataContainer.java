import java.util.ArrayList;

public class DataContainer {

  private DataContainer() {}

    public ArrayList<String> getOperatorsContainer() {
        return operatorsContainer;
    }

    public void setOperatorsContainer(ArrayList<String> operatorsContainer) {
        this.operatorsContainer = operatorsContainer;
    }

    public ArrayList<String> getFirstParametersContainer() {
        return firstParametersContainer;
    }

    public void setFirstParametersContainer(ArrayList<String> firstParametersContainer) {
        this.firstParametersContainer = firstParametersContainer;
    }

    public ArrayList<String> getSecondParametersContainer() {
        return secondParametersContainer;
    }

    public void setSecondParametersContainer(ArrayList<String> secondParametersContainer) {
        this.secondParametersContainer = secondParametersContainer;
    }

  private ArrayList<String>operatorsContainer = new ArrayList<>();
  private ArrayList<String>firstParametersContainer = new ArrayList<>();
  private ArrayList<String>secondParametersContainer = new ArrayList<>();

  public DataContainer(ArrayList<String>operatorsContainer,ArrayList<String>firstParametersContainer,ArrayList<String>secondParametersContainer) {
      setOperatorsContainer(operatorsContainer);
      setFirstParametersContainer(firstParametersContainer);
      setSecondParametersContainer(secondParametersContainer);
  }

  public void printContainer() {
      System.out.println("OPERATORS: ");
      for (int i=0;i<operatorsContainer.size();++i) {
          System.out.println(operatorsContainer.get(i)+"_LENGTH:"+operatorsContainer.get(i).length());
      }

      System.out.println("FIRST PARAMETERS:  ");
      for (int i=0;i<firstParametersContainer.size();++i) {
          System.out.println(firstParametersContainer.get(i)+"_LENGTH:"+firstParametersContainer.get(i).length());
      }

      System.out.println("SECOND PARAMETER:  ");
      for (int i=0;i<secondParametersContainer.size();++i) {
          System.out.println(secondParametersContainer.get(i)+"_LENGTH:"+secondParametersContainer.get(i).length());
      }
  }

}
