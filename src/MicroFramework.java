public class MicroFramework {

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    //VARIABLES
    private String filePath;

    //CONSTRUCTORS
    private MicroFramework() {}

    public MicroFramework(String path) {
        setFilePath(path);
        FileParser parser = new FileParser();
            try {
               ExecutorController executor = new ExecutorController(parser.parseFile(filePath));
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
    }
}
