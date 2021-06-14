package nix.alevel.finance.util;

import nix.alevel.finance.model.entity.Account;
import nix.alevel.finance.model.entity.ExpenseCategory;
import nix.alevel.finance.model.entity.IncomeCategory;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class ConsoleUtils {
    public void expenseTable(List<ExpenseCategory> categories) {
        List<String[]> table = new ArrayList<>();
        table.add(new String[]{"id", "expense"});
        for (ExpenseCategory c : categories) {
            String[] row = new String[2];
            row[0] = c.getId().toString();
            row[1] = c.getExpenseName();
            table.add(row);
        }
        printTable(table);
    }
    public void incomeTable(List<IncomeCategory> categories) {
        List<String[]> table = new ArrayList<>();
        table.add(new String[]{"id", "income"});
        for (IncomeCategory c : categories) {
            String[] row = new String[2];
            row[0] = c.getId().toString();
            row[1] = c.getIncomeName();
            table.add(row);
        }
        printTable(table);
    }

    public void accountTable(List<Account> accounts) {
        List<String[]> table = new ArrayList<>();
        table.add(new String[]{"id", "account name", "balance"});
        for (Account a : accounts) {
            String[] row = new String[3];
            row[0] = a.getId().toString();
            row[1] = a.getAccountName();
            row[2] = a.getBalance().toString();
            table.add(row);
        }
        printTable(table);
    }
    public void printTable(List<String[]> table){
        boolean leftJustifiedRows = false;
        Map<Integer, Integer> columnLengths = new HashMap<>();
        table.forEach(a -> Stream.iterate(0, (i -> i < a.length), (i -> ++i)).forEach(i -> {
            columnLengths.putIfAbsent(i, 0);
            if (columnLengths.get(i) < a[i].length()) {
                columnLengths.put(i, a[i].length());
            }
        }));
        final StringBuilder formatString = new StringBuilder("");
        String flag = leftJustifiedRows ? "-" : "";
        columnLengths.entrySet().forEach(e -> formatString.append("| %" + flag + e.getValue() + "s "));
        formatString.append("|\n");

        String line = columnLengths.entrySet().stream().reduce("", (ln, b) -> {
            String templn = "+-";
            templn = templn + Stream.iterate(0, (i -> i < b.getValue()), (i -> ++i)).reduce("", (ln1, b1) -> ln1 + "-",
                    (a1, b1) -> a1 + b1);
            templn = templn + "-";
            return ln + templn;
        }, (a, b) -> a + b);
        line = line + "+\n";

        System.out.print(line);
        System.out.printf(formatString.toString(), (Object[]) table.get(0)) ;
        System.out.print(line);

        Stream.iterate(1, (i -> i < table.size()), (i -> ++i))
                .forEach(a -> System.out.printf(formatString.toString(),(Object[])  table.get(a)));
        System.out.print(line);
    }
    public long readLong(BufferedReader reader) throws IOException {
        while (true) {
            String text = reader.readLine();
            try {
                return Integer.parseInt(text.trim());
            } catch (NumberFormatException e) {
                System.out.println("You enter a string.");
            }
        }
    }
    public long chooseTheOwnAccount(BufferedReader reader, List<Long> accountSecurityId) throws IOException {
        while (true){
            long id = readLong(reader);
            if(accountSecurityId.contains(id)){
                return id;
            }else {
                System.out.println("Current user hasn`t such account. Please try again.");}
        }
    }
    public Timestamp readDate(BufferedReader reader) throws IOException {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Timestamp date;
        while (true){
            try {
                Date dateRead = formatter.parse(reader.readLine());
                date = new Timestamp(dateRead.getTime());
                break;
            }catch (ParseException e){
                System.out.println("Unparseable date. Please, try again.");
            }
        }
        return date;
    }
}
