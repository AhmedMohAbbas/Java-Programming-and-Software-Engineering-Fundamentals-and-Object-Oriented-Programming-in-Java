import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int counter = 0;
        for (Point currPt : s.getPoints()){
            counter = counter + 1;
        }
        return counter;
    }

    public double getAverageLength(Shape s) {
        double totalPerim1 = 0.0;
        Point prevPt1 = s.getLastPoint();
        int counter1 = 0;
        for (Point currPt1 : s.getPoints()) {
            counter1 = counter1 + 1;
            double currDist1 = prevPt1.distance(currPt1);
            totalPerim1 = totalPerim1 + currDist1;
            prevPt1 = currPt1;
        }
        double averageDis = totalPerim1 / counter1;
        return averageDis;
    }

    public double getLargestSide(Shape s) {
        double largest = 0.0;
        Point prevPt2 = s.getLastPoint();        
        for (Point currPt2 : s.getPoints()){
            double dist = prevPt2.distance(currPt2);
            prevPt2 = currPt2;
            if (dist > largest){
                largest = dist;
            }      
        }        
        return largest;
    }

    public double getLargestX(Shape s) {
        double X = 0.0;
        for (Point a : s.getPoints()){
            double cor = a.getX();
            if ( cor > X ){
                X = cor;
            }            
        }
        return X;
    }

    public double getLargestPerimeterMultipleFiles(DirectoryResource dr) {
        
        double larPrm = 0.0;
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape sh = new Shape(fr);
            double prm = getPerimeter(sh);
            if( prm > larPrm ){
                larPrm = prm;
            }           
        }
        
        return larPrm;
    }

    public String getFileWithLargestPerimeter(DirectoryResource dr) {
        double larPrm1 = 0.0;
        File temp = null;
        
        for (File f1 : dr.selectedFiles()) {
            FileResource fr1 = new FileResource(f1);
            Shape sh1 = new Shape(fr1);
            double prm1 = getPerimeter(sh1);
            if( prm1 > larPrm1 ){
                larPrm1 = prm1;
                temp = f1;
            }           
        }
 
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int pcount = getNumPoints(s);
        double Averagelen = getAverageLength(s);
        double longestSide = getLargestSide(s);
        double largestX = getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("Number of Points = " + pcount);
        System.out.println("Average Side Length = " + Averagelen);
        System.out.println("Length of the longest Side = " + longestSide);
        System.out.println("Highest X-cordinate Value = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = getLargestPerimeterMultipleFiles(dr);
        System.out.println("The Largest Perimeter = " + largestPerimeter);
        
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        DirectoryResource dr1 = new DirectoryResource();
        String larPrmFile = getFileWithLargestPerimeter(dr1);
        System.out.println("The File with the Largest Perimeter is: " + larPrmFile);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
