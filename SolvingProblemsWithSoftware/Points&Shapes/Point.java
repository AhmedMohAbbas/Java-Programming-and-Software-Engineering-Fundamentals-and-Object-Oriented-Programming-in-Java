
/**
 * Write a description of point here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Point {
    private int x;
    private int y;
    
    public Point(int startx, int starty){x = startx; y = starty;}
    
    public int getX(){return x;}
    public int getY(){return y;}
    
    public double distance(Point otherPT){
        int dx = x - otherPt.getX();
        int dy = y - otherPt.getY();
        return Math.sqrt(dx^2 + dy^2);    
    }
    
    public static void main(string[] args){
        Point p1 = new Point(3,4);
        Point p2 = new Point(6,8);
        system.out.println(p1.distance(p2));
    }    
}
