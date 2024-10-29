import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Screen extends JPanel {
    Point A = new Point(100,100);
    Point B = new Point(50,300);
    Point C = new Point(200,300);
    Point D = new Point(300,500);

    static final int UNIT_SIZE = 35;

    static final int SCREEN_WIDTH = 700;
    static final int SCREEN_HEIGHT = 700;

    private Graphics2D g2d;


    MyPanel panel;




    public boolean GRAPH;

    public Screen(MyPanel panel){
        this.setBackground(Color.lightGray);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.panel = panel;


    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        Graphics2D g2d = (Graphics2D) g;

        this.g2d = g2d;


        float strokeWidth = 0.1f;
        g2d.setPaint(Color.black);
        g2d.setStroke(new BasicStroke(strokeWidth));

        for (int i = 0; i <= SCREEN_WIDTH; i = i + UNIT_SIZE) {
            if (i == 350) {
                g2d.setStroke(new BasicStroke(3.0f));
                g2d.setPaint(Color.red);
                g2d.drawLine(i, 0, i, SCREEN_HEIGHT);
                g2d.setStroke(new BasicStroke(0.1f));
                g2d.setPaint(Color.black);
            } else {
                g2d.drawLine(i, 0, i, SCREEN_HEIGHT);
            }
        }

        for ( int i = 0; i < SCREEN_HEIGHT; i = i + UNIT_SIZE){
            if ( i == 350) {
                g2d.setStroke(new BasicStroke(3.0f));
                g2d.setPaint(Color.red);
                g2d.drawLine(0, i, SCREEN_WIDTH, i);
                g2d.setStroke(new BasicStroke(0.1f));
                g2d.setPaint(Color.black);
            }else {
                g2d.drawLine(0, i, SCREEN_WIDTH, i);
            }
        }

        ArrayList<Point> points = panel.getPoints();
        ArrayList<Function> functions = panel.getFunctions();


        //----------Point plotter-----------------
        for ( int i = 0 ; i < points.size() ; i++ ){
            GRAPH = false;
            drawPoint(points.get(i),i+1);

        }
        //---------Quadratic Eq Plotter-----------
        ArrayList<Point> relQuadraticPoints = new ArrayList<>();

        for (Function function : functions) {

            System.out.println("FUNCTION X:"+function.getA()+" Y:"+function.getB());

            relQuadraticPoints = generateQuadraticPoints(function);

            drawQuadraticFunction(relQuadraticPoints);


        }


    }

    public ArrayList<Point> generateQuadraticPoints(Function f){
        ArrayList<Point> points = new ArrayList<>();

        int relX; int relY;


        for (int i = -20; i < 20; i ++){

            int x = i;
            int y = f.getA()*i + f.getB();

            relX = SCREEN_WIDTH/2 + (x*UNIT_SIZE);
            relY = SCREEN_HEIGHT/2 - (y*UNIT_SIZE);

            points.add(new Point(relX, relY));
        }

        return points;

    }
    public void drawQuadraticFunction(ArrayList<Point> points){

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2.0f));

        for(Point point : points){

            int x = point.getX();
            int y = point.getY();

            int diagSize = 4;

            int[] posDiag = {x - diagSize, y - diagSize, x + diagSize, y + diagSize};
            int[] negDiag = {x + diagSize, y - diagSize, x - diagSize, y + diagSize};

            g2d.drawLine(negDiag[0], negDiag[1], negDiag[2], negDiag[3]);
            g2d.drawLine(posDiag[0], posDiag[1], posDiag[2], posDiag[3]);


        }

        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(1.0f));
        for ( int i = 1 ; i < points.size(); i++ ){
            Point prevPoint = points.get(i-1);
            Point currentPoint = points.get(i);

            g2d.drawLine(prevPoint.getX(), prevPoint.getY(), currentPoint.getX(), currentPoint.getY());
        }
    }

    public void drawPoint(Point p, int nr) {

        g2d.setColor(new Color(1, 151, 0));
        g2d.setStroke(new BasicStroke(4.0f));



        int x = p.getX();
        int y = p.getY();


        int relX = x * UNIT_SIZE;
        int relY = y * UNIT_SIZE;
        relX = relX + SCREEN_WIDTH / 2;
        relY = SCREEN_HEIGHT / 2 - relY;
        System.out.println(relX + "   " + relY);


        int diagSize = 5;

        int[] posDiag = {relX - diagSize, relY - diagSize, relX + diagSize, relY + diagSize};
        int[] negDiag = {relX + diagSize, relY - diagSize, relX - diagSize, relY + diagSize};

        g2d.drawLine(negDiag[0], negDiag[1], negDiag[2], negDiag[3]);
        g2d.drawLine(posDiag[0], posDiag[1], posDiag[2], posDiag[3]);
        g2d.drawString("P." + String.valueOf(nr), relX - 10, relY - 10);

        System.out.println("Point drawn X:" + x + "     Y:" + y);

    }














}
