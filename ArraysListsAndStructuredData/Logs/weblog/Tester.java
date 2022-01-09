
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer x = new LogAnalyzer();
        x.readFile("short-test_log");
        x.printAll();
        System.out.println("Number of unique logs is " + x.countUniqueIPs()+"\r\n");
        int sc = 400;
        System.out.println("logs with a status code greater than "+ 
        sc+" are :-  ");
        x.printAllHigherThanNum(sc);
        ArrayList<String> ips = x.uniqueIPVisitsOnDay("Sep 24");
        System.out.println("\r\n"+ips.size()+"\r\n");
        int z = x.countUniqueIPsInRange(400, 499);
        System.out.println("No. of IPs with status in range 400:499 is : " + z);
    }
    
    public void testCounts(){
        LogAnalyzer x = new LogAnalyzer();
        x.readFile("short-test_log");
        HashMap<String, Integer> count = x.countVisitPerIP();
        System.out.println("Number of unique IPs = "+count.size()+" and they are:-");
        System.out.println(count);
        System.out.println(x.mostNumberVisitsByIP(count));
        System.out.println(x.iPsMostVisits(count));
        System.out.println(x.iPsForDays());
        System.out.println(x.dayWithMostIPVisits(x.iPsForDays()));
        System.out.println(x.iPsWithMostVisitsOnDay("Sep 30",x.iPsForDays()));
        
    }
    
    //public void testIPVisits(){
      //  LogAnalyzer x = new LogAnalyzer();
      //  x.readFile("short-test_log");
      //  ArrayList<String> ips = x.uniqueIPVisitsOnDay("Sep 14");
      //  System.out.println(ips.size());
    //}
    

}
