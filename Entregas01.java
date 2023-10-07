import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import java.awt.geom.RoundRectangle2D;

class RoundedPanel extends JPanel {
    private Shape shape;

    public RoundedPanel(int radius) {
        super();
        setOpaque(false);
        shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        ((Graphics2D) g).fill(shape);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        ((Graphics2D) g).draw(shape);
    }

    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 30, 30);
        }
        return shape.contains(x, y);
    }
}

public class Entregas01 extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public Entregas01() {
        setTitle("Login");
        setSize(500, 220);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        RoundedPanel panel = new RoundedPanel(30);
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.WHITE);

        JLabel usernameLabel = new JLabel("Seu Login");
        JLabel passwordLabel = new JLabel("Sua Senha");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Entrar");

        loginButton.setForeground(Color.BLACK);
        loginButton.setBackground(new Color(173, 216, 230));

        loginButton.setPreferredSize(new Dimension(80, 30)); 

        loginButton.addActionListener(this);

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(loginButton);

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Entregas01();
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.equals("Universidade São Judas Tadeu") && password.equals("A3")) {
            JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
        } else {
            JOptionPane.showMessageDialog(this, "Login falhou. Tente novamente.");
        }
    }
}