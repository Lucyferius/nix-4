package nix.alevel.firsttask.view;

import nix.alevel.firsttask.util.DateFormatter;

import java.util.List;

public class GenerateComparingTable {
    public static void generateTable(List<String> inputDates, List<String> outputDates){
        System.out.println("+---------------------+---------------------+"); //22 23 45
        System.out.printf("|%10s %11s %12s %8s\n","Input", "|","Output", "|");
        System.out.println("+-------------------------------------------+");
        int count = 0;
        for (int i = 0; i<inputDates.size(); i++){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("|   ").append(inputDates.get(i));
            if(DateFormatter.isValid(inputDates.get(i), DateFormatter.FIRST_PERMITTED_FORMAT) ||
                    DateFormatter.isValid(inputDates.get(i), DateFormatter.SECOND_PERMITTED_FORMAT) ||
                    DateFormatter.isValid(inputDates.get(i), DateFormatter.THIRD_PERMITTED_FORMAT)){
                System.out.printf(stringBuilder +"%9s %13s %7s \n", "|", outputDates.get(count), "|");
                count++;
            }else {
                char[] charArray = inputDates.get(i).toCharArray();
                int chars = charArray.length;
                int a =  19-chars;
                System.out.printf(stringBuilder + "%"+a+"s %15s %5s \n","|", "Doesn`t match", "|");
            }
            System.out.println("+---------------------+---------------------+");
        }

    }
}
