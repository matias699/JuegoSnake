import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class PanellJoc extends JPanel implements ActionListener {

    private final int TAMANY = 400;
    private final int BLOC = 20;

    private final int[] x = new int[400];
    private final int[] y = new int[400];
    private int cossos;

    private int pomaX;
    private int pomaY;

    private char direccio;
    private boolean enJoc;
    private Timer timer;

    private JButton botoReiniciar;

    public PanellJoc() {
        setPreferredSize(new Dimension(TAMANY, TAMANY));
        setBackground(Color.BLACK);
        setFocusable(true);
        setLayout(null);

        addKeyListener(new AdaptadorTeclat());

        botoReiniciar = new JButton("Tornar a jugar");
        botoReiniciar.setBounds(TAMANY / 4, (TAMANY / 2) + 20, TAMANY / 2, 40);
        botoReiniciar.setVisible(false);

        botoReiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarJoc();
            }
        });

        add(botoReiniciar);

        inicialitzarVariables();
        timer = new Timer(150, this);
        timer.start();
    }

    private void inicialitzarVariables() {
        cossos = 3;
        direccio = 'R';
        enJoc = true;
        botoReiniciar.setVisible(false);

        for (int i = 0; i < cossos; i++) {
            x[i] = 60 - (i * BLOC);
            y[i] = 60;
        }
        generarPoma();

        Main.insertarUsuario("Jugador_Prueba");
    }

    private void reiniciarJoc() {
        inicialitzarVariables();
        timer.start();
        requestFocusInWindow();
        repaint();
    }

    private void generarPoma() {
        Random r = new Random();
        pomaX = r.nextInt(TAMANY / BLOC) * BLOC;
        pomaY = r.nextInt(TAMANY / BLOC) * BLOC;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (enJoc) {

            for (int i = cossos; i > 0; i--) {
                x[i] = x[i - 1];
                y[i] = y[i - 1];
            }

            if (direccio == 'U') y[0] -= BLOC;
            if (direccio == 'D') y[0] += BLOC;
            if (direccio == 'L') x[0] -= BLOC;
            if (direccio == 'R') x[0] += BLOC;

            if (x[0] == pomaX && y[0] == pomaY) {
                cossos++;
                generarPoma();
            }

            if (x[0] < 0 || x[0] >= TAMANY || y[0] < 0 || y[0] >= TAMANY) {
                enJoc = false;
            }

            for (int i = cossos; i > 0; i--) {
                if (x[0] == x[i] && y[0] == y[i]) {
                    enJoc = false;
                }
            }

            if (!enJoc) {
                timer.stop();
                botoReiniciar.setVisible(true);

                int puntosFinales = cossos - 3;
                Main.insertarPuntuacion(puntosFinales, 1);
            }
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (enJoc) {
            g.setColor(Color.RED);
            g.fillRect(pomaX, pomaY, BLOC, BLOC);

            for (int i = 0; i < cossos; i++) {
                g.setColor(Color.GREEN);
                g.fillRect(x[i], y[i], BLOC, BLOC);
            }
        } else {
            g.setColor(Color.RED);
            g.drawString("GAME OVER", TAMANY / 3, TAMANY / 2);
        }
    }

    private class AdaptadorTeclat extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int tecla = e.getKeyCode();

            if (tecla == KeyEvent.VK_LEFT && direccio != 'R') direccio = 'L';
            if (tecla == KeyEvent.VK_RIGHT && direccio != 'L') direccio = 'R';
            if (tecla == KeyEvent.VK_UP && direccio != 'D') direccio = 'U';
            if (tecla == KeyEvent.VK_DOWN && direccio != 'U') direccio = 'D';
        }
    }
}