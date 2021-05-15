package nix.alevel.secondtask.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniqueNamesFinder {
    public static List<String> findUniqueNamesInList(List<String> names){

        Map<String, Integer> unique = new HashMap<>();
        // Сложность О(n) - проходи по всем елементам начиная от 0 до names.size()-1
        for (int i = 0; i<names.size(); i++) {
            if (unique.containsKey(names.get(i)))
                unique.replace(names.get(i), unique.get(names.get(i))+1);
            else {
                unique.put(names.get(i), 1);
            }

        }
        List<String> uniqueNames = new ArrayList<>(unique.keySet());
        // Функции f(x) является O(g(x)) тогда и только тогда, когда для очень больших значений x,
        // |f(x)| <= M * |g(x)| , где M -константа и M > 0 .
        // Это определение в основном означает, что O(kn) является подмножеством O(n).
        // Конечно , алгоритм в O(2n) WILL всегда будет быстрее, чем другой в O(3n),
        // но все они принадлежат к большому семейству алгоритмов O(n) .

        // Расчет:
        // Создание Map<String, Integer> unique и запись в него элементов <Имя, Сколько раз встречается> на основе данного списка, данная операция занимает проход по списку с размером N -> O(n)
        // Далее независимым циклом проходим по тем елементам Map, Integer которых равен 1. -> O(k)
        // Таким образом:
        // O(n) + O(k) = O(kn) = O(n)
       for (int i = 0; i < unique.size(); i++) {
            if(unique.get(uniqueNames.get(i))>1)
                unique.remove(uniqueNames.get(i));
        }

        return new ArrayList<>(unique.keySet());
    }
    public static String findUniqueNameById(List<String> names, int id){
        return names.get(id);
    }
}
