// Java Program to add a image and string
// to a label with horizontal alignment


import javax.swing.*;

class test extends JFrame {

    // frame
    static JFrame f;

    // label to display text
    static JLabel l;

    // default constructor
    test() {
    }

    // main class
    public static void main(String[] args) {
        // create a new frame to store text field and button
        f = new JFrame("label");

        // create a new image icon
        ImageIcon i = new ImageIcon("f:/image.png");

        // create a label to display text and image
        l = new JLabel("new image text ", i, SwingConstants.HORIZONTAL);

        // create a panel
        JPanel p = new JPanel();

        // add label to panel
        p.add(l);

        // add panel to frame
        f.add(p);

        // set the size of frame
        f.setSize(600, 500);

        f.show();
    }
}
