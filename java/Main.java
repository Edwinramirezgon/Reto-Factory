import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Main extends JFrame {

    // Métodos de pago disponibles (deben coincidir con los cases de PaymentFactory)
    private static final String[] METHODS = {"Credit Card", "PayPal", "Bank Transfer"};

    // Componentes de la interfaz gráfica
    private final JComboBox<String> methodBox = new JComboBox<>(METHODS);
    private final JTextField amountField = new JTextField("100.00", 10);
    private final JTextArea logArea = new JTextArea(8, 35); // historial de pagos procesados

    public Main() {
        super("Payment Processor — Factory Method");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // ── Panel superior: selector de método, monto y botones ──────────────
        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        form.add(new JLabel("Method:"));
        form.add(methodBox);        // dropdown con los métodos disponibles
        form.add(new JLabel("Amount $:"));
        form.add(amountField);      // campo para ingresar el monto

        JButton payBtn = new JButton("Pay");
        payBtn.addActionListener(e -> processPayment()); // dispara el pago al hacer clic
        form.add(payBtn);

        JButton clearBtn = new JButton("Clear");
        clearBtn.addActionListener(e -> logArea.setText("")); // limpia el historial
        form.add(clearBtn);

        // ── Panel central: área de log con scroll ─────────────────────────────
        logArea.setEditable(false);
        logArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));

        add(form, BorderLayout.NORTH);
        add(new JScrollPane(logArea), BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null); // centra la ventana en pantalla
    }

    // Patrón Factory Method en acción: delega la creación del procesador a PaymentFactory.
    // El cliente (Main) no sabe qué clase concreta se instancia.
    private void processPayment() {
        try {
            double amount = Double.parseDouble(amountField.getText().trim());
            String method = (String) methodBox.getSelectedItem();

            // Redirige System.out para capturar el output del procesador y mostrarlo en el log
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            PrintStream old = System.out;
            System.setOut(new PrintStream(buf));

            PaymentFactory.create(method).process(amount); // Factory Method

            System.setOut(old); // restaura la salida estándar
            logArea.append(buf + "\n");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Punto de entrada: lanza la GUI en el hilo de eventos de Swing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
