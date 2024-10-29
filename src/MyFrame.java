import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class MyFrame extends JFrame {

    public MyFrame myFrame;
    public MyPanel myPanel;
    public JPanel startPanel;


    public MyFrame(){
        this.setVisible(true);
        this.setTitle("Graph Drawer");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        String current = System.getProperty("user.dir");
        Path imagePath = Paths.get(current, "/src/LOGO.png");

        try {
            Image image = ImageIO.read(imagePath.toFile());
            this.setIconImage(image);
        } catch (IOException e){
            e.printStackTrace();
        }
        startPanel = new StartPanel(this);

        myFrame = this;
        myPanel = new MyPanel();

        this.add(startPanel);









        this.pack();
    }


}


class StartPanel extends JPanel {

    private final JButton start_button = new JButton("START");
    private final JButton quit_button = new JButton("QUIT");
    private Image backgroundImage;
    private Image logoImage;
    private JLabel titleLabel = new JLabel("Graph Drawer V2.1");

    private Graphics2D g2d;

    public StartPanel(MyFrame frame){

        this.setPreferredSize(new Dimension(1400,700));


        String current = System.getProperty("user.dir");
        Path backgroundImagePath = Paths.get(current, "/src/START_SCREEN_BACKGROUND.jpg");
        Path logoImagePath = Paths.get(current, "/src/START_SCREEN_LOGO.png");

        try {

            backgroundImage = ImageIO.read(backgroundImagePath.toFile());
            logoImage = ImageIO.read(logoImagePath.toFile());


        } catch (IOException e) {
            System.out.println("EROARE AICI");
        }
        start_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.startPanel.setVisible(false);
                frame.add(frame.myPanel);
                frame.myPanel.setVisible(true);

            }

        });
        quit_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.setLayout(null);

        Font font = new Font("Arial",Font.BOLD, 64);
        titleLabel.setFont(font);
        titleLabel.setBounds(400,20,750, 60);

        start_button.setBorder(new BasicBorders.ButtonBorder(Color.RED, Color.BLUE, Color.BLUE, Color.BLUE));
        //start_button.setPreferredSize(new Dimension(80,50));
        start_button.setBackground(Color.lightGray);
        start_button.setBounds(550,100,100,50);

        quit_button.setBorder(new BasicBorders.ButtonBorder(Color.BLUE, Color.RED, Color.BLUE, Color.blue));
        //quit_button.setPreferredSize(new Dimension(80, 50));
        quit_button.setBackground(Color.lightGray);
        quit_button.setBounds(750, 100, 100,50);

        this.add(titleLabel);
        this.add(start_button);
        this.add(quit_button);


    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;

        if (backgroundImage != null) {
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            g2d.drawImage(logoImage, 450,170, 500,500,this);
        }

    }

}
