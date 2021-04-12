package nix.alevel.util.dataoperations.service;

import nix.alevel.entity.Date;

import java.util.List;
import java.util.Map;

public interface DataOperations {
    Map<String, Long> findDifference(Date firstDate, Date secondDate);
    void printDifference(Map<String, Long> map);
    Date addDate(Date dateFirst, Date dateSecond);
    List<Date> sortAsc(List<Date> arr);
    List<Date> sortDes(List<Date> arr);
    long countOfMSInDate(Date date);
    Date subtractTwoDate(Date dateFirst, Date dateSecond);
}
