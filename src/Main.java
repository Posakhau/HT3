public class Main {
    public static void main(String[] args) {
         System.out.println(args[0]);
	     MicroFramework microFramework = new MicroFramework(args[0]);
    }
}


/*

    1. На вход через аргумент командной строки передается текстовый файл instructions.txt, который в силу причин находится на диске Е.
       - настроен через аргумент командной строки в IDEA (E:instructions.txt)

    2. Файл репорта формируется также на диск E и имеет имя report.txt

    Общая концепция решения данной задачи заключается в следующем.

    Объект microFramework вызывает объект FileParser, который получает файл инструкций и формирует из него
    структуру данных - объект DataContainer, который передается в объект ExecutorController, который формирует логику вызова
    тестовых методов и сам же их впоследствии вызывает, формируя структуру данных - объект ReportData,
    который передается в объект ReportCreator, который производит запись в файл репорта report.txt

 */
