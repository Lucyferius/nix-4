package nix.alevel.secondtask.view;


import java.util.List;

public class GenerateNamesTable {
    public static void generateTable(List<String> inputDates, List<String> outputDates, long firstTime,
                                     long secondTime, long thirdTime, String name){

        System.out.println("+------------------------------------------------------------+");//62
        String a = "|  Input = " + inputDates.size()+ " names";
        System.out.printf(a + "%"+(62-a.length()) +"s", "|") ;
        System.out.println("\n+------------------------------------------------------------+");
        a = "|  Output = " + outputDates.size() + " names";
        System.out.printf(a + "%"+(62-a.length()) +"s", "|");
        System.out.println("\n+------------------------------------------------------------+");

        System.out.println("\n+---------------------------------+--------------------------+");
        System.out.printf("%s %12s %10d %2s %11s %s %16s %26s" , "| Time-span of finding", "|", firstTime , " ms", "|",
                "\n| all unique names", "|" , "|");
        System.out.println("\n+---------------------------------+--------------------------+");

        System.out.printf("%s %12s %10d %2s %11s %s %16s %26s %s %5s %26s" , "| Time-span of finding", "|", secondTime , " ms", "|",
                "\n| all unique names", "|" , "|", "\n| and getting name with index", "|" , "|");
        System.out.println("\n+---------------------------------+--------------------------+");

        System.out.printf("%s %12s %10d %2s %11s %s %12s %26s %s %18s %26s" , "| Time-span of getting", "|", thirdTime , " ms", "|",
                "\n| name with index from", "|" , "|", "\n| completed list", "|" , "|");
        System.out.println("\n+---------------------------------+--------------------------+");
        a = "|  Example:     " + name + "    (name with index 2))";
        System.out.printf(a + "%"+(62-a.length()) +"s", "|") ;
        System.out.println("\n+---------------------------------+--------------------------+");

    }
}
