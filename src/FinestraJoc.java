import javax.swing.JFrame;

public class FinestraJoc extends JFrame {

    public FinestraJoc() {
        setTitle("Snake");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        add(new PanellJoc());

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new FinestraJoc();
    }
}

