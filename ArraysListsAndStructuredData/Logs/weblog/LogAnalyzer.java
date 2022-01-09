
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fh = new FileResource();
         for(String line : fh.lines()){
             LogEntry x = WebLogParser.parseEntry(line);
             records.add(x);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
         ArrayList<String> uni = new ArrayList<String>();
         for(LogEntry x : records){
             String ip = x.getIpAddress();
             if( ! uni.contains(ip) ){
                 uni.add(ip);
             }
         }
         return uni.size();
     }
     
     public void printAllHigherThanNum(int num){
         for(LogEntry x : records){
             int cod = x.getStatusCode();
             if(cod > num){System.out.println(x);}             
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> visitIps = new ArrayList<String>();
         for(LogEntry x : records){
             String timeStamp = x.getAccessTime().toString();
             if(timeStamp.contains(someday) && ! visitIps.contains(x.getIpAddress())){
                 visitIps.add(x.getIpAddress());
             }             
         }
         return visitIps;
     }
     
     public int countUniqueIPsInRange(int low, int high){
         int counter = 0;
         ArrayList<String> uniIps = new ArrayList<String>();
         for(LogEntry x : records){
             int cod = x.getStatusCode();
             String ip = x.getIpAddress();
             if((cod <= high && cod >= low) && ! uniIps.contains(ip)){
                 uniIps.add(ip);
                 counter = counter + 1;
             }
         }
         
         return counter;
     }
     
     public HashMap<String, Integer> countVisitPerIP(){
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         for(LogEntry x : records){
             String ip = x.getIpAddress();
             if( ! counts.containsKey(ip)){
                 counts.put(ip, 1);
             }else{
                 counts.put(ip, counts.get(ip) + 1 );
             }
         }
         return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> counter){
         int max = 0;
         for(String k : counter.keySet()){
              int val = counter.get(k);
              if(val > max){
                  max = val;
              }
         }
         return max;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counter){
         ArrayList<String> maxedIPs = new ArrayList<String>();
         int max = mostNumberVisitsByIP(counter);
         for(String k : counter.keySet()){
             int val = counter.get(k);
             if(val == max){
                 maxedIPs.add(k);
             }
         }
         return maxedIPs;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays(){
         HashMap<String, ArrayList<String>> iPsIndays = new HashMap<String, ArrayList<String>>();
         for(LogEntry el : records){
             String timestamp = el.getAccessTime().toString();
             String day = timestamp.substring(timestamp.indexOf(" ")+1,timestamp.indexOf(" ")+7);
             if(iPsIndays.containsKey(day)){
                 //if(! iPsIndays.get(day).contains(el.getIpAddress())){
                     iPsIndays.get(day).add(el.getIpAddress());
                 // }else{continue;}
                 
             }else{
                 iPsIndays.put(day, new ArrayList<String>() );
                 iPsIndays.get(day).add(el.getIpAddress());
             }
         }
         return iPsIndays;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> x){
         String maxedDay = new String();
         int max = 0;
         for(String k : x.keySet()){
             int size = x.get(k).size();
             if(size > max){
                 max = size;
                 maxedDay = k;
             }
         }         
         return maxedDay;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(String day, HashMap<String, ArrayList<String>> x){
         ArrayList<String> dayEntries = x.get(day);
         HashMap<String, Integer> dayIPcounts = new HashMap<String, Integer>();
         for(int k = 0; k < dayEntries.size(); k++){
             if(dayIPcounts.containsKey(dayEntries.get(k))){
                 dayIPcounts.put(dayEntries.get(k), dayIPcounts.get(dayEntries.get(k))+1);
             }else{
                 dayIPcounts.put(dayEntries.get(k), 1);
             }
         }
         ArrayList<String> maxedIPs = iPsMostVisits(dayIPcounts);         
         return maxedIPs;
     }
}
