import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Commands extends JPanel {

    MyPanel panel;

    private int NUMBER_OF_POINTS = 0;
    private int NUMBER_OF_FUNCTIONS = 0;


    private JTextField textField1 = new JTextField("X:");
    private JTextField textField2 = new JTextField("Y:");
    private JButton button1 = new JButton("Draw Point");
    private JButton button2 = new JButton("Delete Points");
    private JButton button3 = new JButton("Plot function");
    private JButton button4 = new JButton("Delete Plotted Functions");

    private JTextArea textArea1 = new JTextArea();
    private JLabel label1 = new JLabel("   Graph Plotter V1 \n");
    private JTextField textField3 = new JTextField("f(x)=");


    private Point point;




    public Commands(MyPanel panel){
        this.setBackground(Color.DARK_GRAY);

        this.panel = panel;

        Font myFont = new Font("Arial", Font.BOLD, 25);


        textField1.setColumns(10);
        textField1.setBackground(Color.gray);
        textField1.setBorder(new BasicBorders.FieldBorder(Color.red,Color.BLUE,Color.BLUE,Color.BLUE));
        textField1.setPreferredSize(new Dimension(128, 30));
        textField1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textField1.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if ( textField1.getText().isEmpty()){
                    textField1.setText("X:");
                }
            }
        });

        textField2.setColumns(10);
        textField2.setBackground(Color.gray);
        textField2.setBorder(new BasicBorders.FieldBorder(Color.red,Color.BLUE,Color.BLUE,Color.BLUE));
        textField2.setPreferredSize(new Dimension(128, 30));
        textField2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textField2.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if ( textField2.getText().isEmpty()){
                    textField2.setText("Y:");
                }
            }
        });

        textField3.setColumns(17);
        textField3.setBackground(Color.gray);
        textField3.setBorder(new BasicBorders.FieldBorder(Color.RED, Color.BLUE, Color.BLUE, Color.BLUE));
        textField3.setPreferredSize(new Dimension(300, 30));
        textField3.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textField3.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField3.getText().isEmpty()){
                    textField3.setText("f(x)=");
                }
            }
        });



        Font titleFont = new Font("Arial", Font.BOLD, 63);
        label1.setPreferredSize(new Dimension(600,70));
        label1.setFont(titleFont);
        label1.setBackground(Color.pink);
        label1.setForeground(Color.gray);

        button1.setBackground(Color.gray);
        button1.setForeground(Color.black);
        button1.setBorder(new BasicBorders.ButtonBorder(Color.RED, Color.BLUE, Color.BLUE, Color.BLUE));
        button1.setPreferredSize(new Dimension(128, 30));

        button2.setBackground(Color.gray);
        button2.setForeground(Color.black);
        button2.setBorder(new BasicBorders.ButtonBorder(Color.RED, Color.BLUE, Color.BLUE, Color.BLUE));
        button2.setPreferredSize(new Dimension(128, 30));

        button3.setBackground(Color.gray);
        button3.setForeground(Color.BLACK);
        button3.setBorder(new BasicBorders.ButtonBorder(Color.RED, Color.BLUE, Color.BLUE, Color.BLUE));
        button3.setPreferredSize(new Dimension(150, 30));

        button4.setBackground(Color.gray);
        button4.setForeground(Color.black);
        button4.setBorder(new BasicBorders.ButtonBorder(Color.RED, Color.BLUE, Color.BLUE, Color.BLUE));
        button4.setPreferredSize(new Dimension(150, 30));


        textArea1.setPreferredSize(new Dimension(500,400));
        textArea1.setEditable(false);
        textArea1.setBackground(Color.gray);
        textArea1.setBorder(new BasicBorders.FieldBorder(Color.RED,Color.BLUE,Color.BLUE,Color.BLUE));
        textArea1.setFont(myFont);
        textArea1.setForeground(new Color(153,0,0));
        textArea1.setText("\n");




        button1.addActionListener(new ActionListener() {//draw point bttn
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Objects.equals(textField1.getText(), "") && !Objects.equals(textField1.getText(), "X")){
                    if (!Objects.equals(textField2.getText(), "") && !Objects.equals(textField2.getText(), "Y")){


                        try {
                            int x = Integer.parseInt(textField1.getText());
                            int y = Integer.parseInt(textField2.getText());

                            point = new Point(x, y);

                            panel.addPoint(point);


                            panel.myScreen.repaint();

                            NUMBER_OF_POINTS ++;

                            textArea1.setText(textArea1.getText() + addPointText(point,NUMBER_OF_POINTS) +"\n");


                        } catch (NumberFormatException ex) {
                            System.out.println("Invalid input. Please enter valid integers.");
                        }
                    }
                }
                textField1.setText("");
                textField2.setText("");



            }
        });

        button2.addActionListener(new ActionListener() {//delete bttn
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.deletePoints();
                textArea1.setText("");
                panel.myScreen.repaint();
            }
        });

        button3.addActionListener(new ActionListener() {//plot function bttn
            @Override
            public void actionPerformed(ActionEvent e) {
                String functionString = textField3.getText();

                textField3.setText("");

                Function function = parseLinearFunction(functionString);

                textArea1.setText(textArea1.getText() + addFunctionText(function, NUMBER_OF_FUNCTIONS+1) + "\n");

                NUMBER_OF_FUNCTIONS++;

                panel.addFunction(function);

                panel.myScreen.repaint();

            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.deleteFunctions();
                textArea1.setText("");
                panel.myScreen.repaint();
            }
        });

        this.add(label1);
        this.add(textField1);
        this.add(textField2);
        this.add(button1);
        this.add(button2);
        this.add(textArea1);
        this.add(textField3);
        this.add(button3);
        this.add(button4);

    }




    public Function parseLinearFunction(String functionString){

        int a = 0;
        int b = 0;

        String pattern = "(-?\\d+)x\\s*([-+]\\s*\\d++)?";
        Pattern regex = Pattern.compile(pattern);

        Matcher matcher = regex.matcher(functionString);

        if (matcher.matches()){
            a = Integer.parseInt(matcher.group(1));

            b = matcher.group(2) != null ?
                    Integer.parseInt(matcher.group(2).replaceAll("\\s+", "")) : 0;

        }

        Function function = new Function(a,b);


        return function;

    }


    public String addFunctionText(Function f, int nr){

        String text = null;

        if ( f.getA() > 0 && f.getB() > 0){

            text = "    Function "+nr+")  "+f.getA()+"x + "+f.getB();
        }else if ( f.getA() > 0 && f.getB() < 0){

            text = "    Function "+nr+")  "+f.getA()+"x - "+Math.abs(f.getB());
        }else if ( f.getA() < 0 && f.getB() > 0){

            text = "    Function "+nr+")  "+f.getA()+"x + "+f.getB();
        }else if ( f.getA() < 0 && f.getB() < 0){

            text = "    Function "+nr+")  "+f.getA()+"x - "+Math.abs(f.getB());//CEVA CU MODUL
        }

        System.out.println(text);

        return text;
    }

    public String addPointText(Point p, int nr){
        String text = "    Point "+nr+")  X:"+p.getX()+"  Y:"+p.getY();
        return text;
    }

}
