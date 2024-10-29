import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyPanel extends JPanel{

    private final int SCREEN_HEIGHT = 700;
    private final int SCREEN_WIDTH = 1400;

    private ArrayList<Point> points = new ArrayList<>();
    private ArrayList<Function> functions = new ArrayList<>();


    Screen myScreen;
    Commands myCommands;
    public MyPanel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.red);
        this.setLayout(new GridLayout(1,2));

        myScreen = new Screen(this);
        myCommands = new Commands(this);

        
        this.add(myScreen);
        this.add(myCommands);






    }

    public void addPoint(Point p){
        points.add(p);
    }
    public void addFunction(Function f){
        functions.add(f);
    }

    public ArrayList<Point> getPoints(){
        return points;
    }

    public ArrayList<Function> getFunctions() {
        return functions;
    }

    public void deletePoints(){
        points.removeAll(points);
    }
    public void deleteFunctions(){
        functions.removeAll(functions);
    }








}
