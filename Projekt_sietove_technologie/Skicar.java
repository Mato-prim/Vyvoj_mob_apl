import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class Skicar extends JFrame implements ActionListener, MouseListener, MouseMotionListener {
    private BufferedImage im;
    private JButton button_farba;
    private JButton button_hrubka;
    private JButton button_zmaz;
    private JButton button_koniec;
    private JTextField tf_hrubka;
    private Graphics gr;
    private Color farba_default;
    private int xpoint;
    private int ypoint;
    private boolean kreslenie;
    public Skicar(){
        setSize(1200,1000);
        kreslenie =false;
        farba_default =Color.BLACK;
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        im = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_RGB);
        gr = im.getGraphics();
        gr.setColor(Color.WHITE);
        gr.fillRect(0, 0, im.getWidth(), im.getHeight());
        JLabel l = new JLabel(new ImageIcon(im));
        add(l);
        button_farba = new JButton("Farba");
        button_hrubka = new JButton("Hrubka");
        button_zmaz = new JButton("Zmaz");
        button_koniec = new JButton("Koniec");
        tf_hrubka = new JTextField(5);
        tf_hrubka.setText("1");
        add(button_farba);
        add(button_hrubka);
        add(tf_hrubka);
        add(button_zmaz);
        add(button_koniec);

        button_farba.addActionListener(this);
        button_hrubka.addActionListener(this);
        button_zmaz.addActionListener(this);
        button_koniec.addActionListener(this);
        l.addMouseListener(this);
        l.addMouseMotionListener(this);
        gr.setColor(farba_default);


        setVisible(true);
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new Skicar();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource()== button_farba){
            farba_default =JColorChooser.showDialog(this, "Farba", Color.BLACK);
            if(farba_default ==null){
                farba_default =Color.BLACK;}
            gr.setColor(farba_default);
        }
        if(e.getSource()==button_hrubka){
            ((Graphics2D) gr).setStroke(new BasicStroke(Float.parseFloat(tf_hrubka.getText())));
        }

        if(e.getSource()== button_zmaz){
            Color temp_color = gr.getColor();
            gr.setColor(Color.WHITE);
            gr.fillRect(0, 0, im.getWidth(), im.getHeight());
            gr.setColor(temp_color);
            repaint();
        }

        if(e.getSource()== button_koniec){
            dispose();
        }

    }



    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        xpoint =e.getX();
        ypoint =e.getY();
        kreslenie =true;
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        kreslenie =false;
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        if(kreslenie =true){
            int new_xpoint= e.getX();
            int new_ypoint= e.getY();
            gr.drawLine(xpoint, ypoint, new_xpoint, new_ypoint);
            xpoint =new_xpoint;
            ypoint =new_ypoint;
            repaint();
        }

    }
    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub


    }

}