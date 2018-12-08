import java.awt.*;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
/**
 *
 * @author kumar_raju
 */
public class q1 {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                MouseFrame mF = new MouseFrame();
                mF.setVisible(true);
                mF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }
        });
    }
}

class MouseFrame extends JFrame {

    public MouseFrame() {
        setTitle("Move the pen");
        setSize(600, 600);

        MousePanel panel1 = new MousePanel();
        add(panel1);

    }
}

class MousePanel extends JPanel {

    private Ellipse2D shape;
    private ArrayList<Ellipse2D> circles;
    private Ellipse2D cur;
    private double speed;
    private JTextField tF;
    private JButton But;

    MousePanel() {
        setSize(600, 600);
        circles = new ArrayList<Ellipse2D>(1000);
        MouseHandler m1 = new MouseHandler();
        MouseMotionHandler m2 = new MouseMotionHandler();
        addMouseListener(m1);
        addMouseMotionListener(m2);
        speed = 0.00001;

        tF = new JTextField("100", 5);
        But = new JButton("Start");
        But.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Thread animationThread = new Thread(new Animation());
                animationThread.start();
            }
        });
        add(tF);
        add(But);

        shape = new Ellipse2D.Double(100 - 5, 100 - 5, 10, 10);
        repaint();
    }

    class Animation implements Runnable {

        double tempX = 0, tempY = 0, xx = 0, yy = 0, dispX, dispY;

        @Override
        public void run() {

            repaint();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MousePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            dispY = circles.get(1).getCenterY() - shape.getCenterY();
            dispX = (circles.get(1).getCenterX() - shape.getCenterX()) / Math.abs(dispY);
            if (circles.get(1).getCenterY() > shape.getCenterY()) {
                dispY = 1;
            } else {
                dispY = -1;
            }

            int i = 0;
            while (true) {
                for (i = 1; i < circles.size(); ++i) {
                    if (Math.abs(circles.get(i).getCenterY() - shape.getCenterY())
                            < Math.abs(circles.get(i).getCenterX() - shape.getCenterX())) {
                        dispY = circles.get(i).getCenterY() - shape.getCenterY();
                        dispX = (circles.get(i).getCenterX() - shape.getCenterX()) / Math.abs(dispY) * 0.01;
                        if (circles.get(i).getCenterY() > shape.getCenterY()) {
                            dispY = 0.01;
                        } else {
                            dispY = (-1) * 0.01;
                        }
                        while (Math.abs(circles.get(i).getCenterY() - shape.getCenterY()) >= 0.1) {

                            shape.setFrame(shape.getCenterX() + dispX - 5, shape.getCenterY() + dispY - 5, 10, 10);
                            try {
                                Thread.sleep(1000 / Integer.parseInt(tF.getText()));
                            } catch (InterruptedException ex) {
                                Logger.getLogger(MousePanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            repaint();
                        }
                    } else {
                        dispX = circles.get(i).getCenterX() - shape.getCenterX();
                        dispY = (circles.get(i).getCenterY() - shape.getCenterY()) / Math.abs(dispX) * 0.01;
                        if (circles.get(i).getCenterX() > shape.getCenterX()) {
                            dispX = 0.01;
                        } else {
                            dispX = (-1) * 0.01;
                        }
                        while (Math.abs(circles.get(i).getCenterX() - shape.getCenterX()) >= 0.1) {

                            shape.setFrame(shape.getCenterX() + dispX - 5, shape.getCenterY() + dispY - 5, 10, 10);
                            try {
                                Thread.sleep(1000 / Integer.parseInt(tF.getText()));
                            } catch (InterruptedException ex) {
                                Logger.getLogger(MousePanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            repaint();
                        }
                    }
                }
                i = 0;
                if (Math.abs(circles.get(i).getCenterY() - shape.getCenterY())
                        < Math.abs(circles.get(i).getCenterX() - shape.getCenterX())) {
                    dispY = circles.get(i).getCenterY() - shape.getCenterY();
                    dispX = (circles.get(i).getCenterX() - shape.getCenterX()) / Math.abs(dispY) * 0.01;
                    if (circles.get(i).getCenterY() > shape.getCenterY()) {
                        dispY = 0.01;
                    } else {
                        dispY = (-1) * 0.01;
                    }
                    while (Math.abs(circles.get(i).getCenterY() - shape.getCenterY()) >= 0.1) {

                        shape.setFrame(shape.getCenterX() + dispX - 5, shape.getCenterY() + dispY - 5, 10, 10);
                        try {
                            Thread.sleep(1000 / Integer.parseInt(tF.getText()));
                        } catch (InterruptedException ex) {
                            Logger.getLogger(MousePanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                } else {
                    dispX = circles.get(i).getCenterX() - shape.getCenterX();
                    dispY = (circles.get(i).getCenterY() - shape.getCenterY()) / Math.abs(dispX) * 0.01;
                    if (circles.get(i).getCenterX() > shape.getCenterX()) {
                        dispX = 0.01;
                    } else {
                        dispX = (-1) * 0.01;
                    }
                    while (Math.abs(circles.get(i).getCenterX() - shape.getCenterX()) >= 0.1) {

                        shape.setFrame(shape.getCenterX() + dispX - 5, shape.getCenterY() + dispY - 5, 10, 10);
                        try {
                            Thread.sleep(1000 / Integer.parseInt(tF.getText()));
                        } catch (InterruptedException ex) {
                            Logger.getLogger(MousePanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setPaint(Color.LIGHT_GRAY);

        for (Ellipse2D c : circles) {
            g2.fill(c);
        }
        g2.setPaint(Color.RED);
        g2.fill(shape);

    }

    public void remove(Ellipse2D s) {
        if (s == null) {
            return;
        }

        circles.remove(s);
        repaint();
    }

    public void add(Point2D p) {

        double x = p.getX();
        double y = p.getY();

        cur = new Ellipse2D.Double(x - 5, y - 5, 10, 10);
        circles.add(cur);
        repaint();
        if (circles.size() == 3) {
            //moveCircle();
        }
        if (circles.size() == 1) {
            shape.setFrame(circles.get(0).getX(), circles.get(0).getY(), 10, 10);
        }
    }

    public Ellipse2D find(Point2D p) {
        for (Ellipse2D c : circles) {
            if (c.contains(p)) {
                return c;
            }
        }
        return null;
    }

    private class MouseHandler extends MouseAdapter {

        public void mousePressed(MouseEvent event) {
            cur = find(event.getPoint());
            if (cur == null) {
                add(event.getPoint());
            }
        }

        public void mouseClicked(MouseEvent event) {
            cur = find(event.getPoint());
            if (cur != null && event.getClickCount() >= 2) {
                remove(cur);
            }
        }
    }

    private class MouseMotionHandler implements MouseMotionListener {

        public void mouseMoved(MouseEvent event) {
        }

        public void mouseDragged(MouseEvent event) {
            if (cur != null) {
                double x = event.getX();
                double y = event.getY();

                cur.setFrame(x - 5, y - 5, 10, 10);
                repaint();
            }
        }
    }
}
