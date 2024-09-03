import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private UserManager userManager;
    private PaymentManager paymentManager;
    private House house;
    private User currentUser;

    private JTextArea textArea;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton viewHouseButton;
    private JButton viewBedroomsButton;
    private JButton rentRoomButton;
    private JButton maintenanceButton;
    private JButton viewBathButton;
    private JButton makePaymentButton;
    private JButton listPaymentsButton;
    private JButton listUsersButton;
    private JButton exitButton;

    public MainFrame(UserManager userManager, PaymentManager paymentManager, House house) {
        this.userManager = userManager;
        this.paymentManager = paymentManager;
        this.house = house;
        this.currentUser = null;

        setTitle("House Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(15, 1)); // Aumenta o número de linhas no GridLayout

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        viewHouseButton = new JButton("View House Details");
        viewBedroomsButton = new JButton("View Bedroom Details");
        rentRoomButton = new JButton("Rent Room");
        maintenanceButton = new JButton("Set Room Under Maintenance");
        viewBathButton = new JButton("View Bath Details");
        makePaymentButton = new JButton("Make Payment");
        listPaymentsButton = new JButton("List Payments");
        listUsersButton = new JButton("List Users");
        exitButton = new JButton("Exit");

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(viewHouseButton);
        panel.add(viewBedroomsButton);
        panel.add(rentRoomButton);
        panel.add(maintenanceButton);
        panel.add(viewBathButton);
        panel.add(makePaymentButton);
        panel.add(listPaymentsButton);
        panel.add(listUsersButton);
        panel.add(exitButton);

        add(panel, BorderLayout.WEST);

        // Inicialmente, desabilita todos os botões de opção
        toggleButtons(false);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                currentUser = userManager.authenticate(username, password);
                if (currentUser != null) {
                    textArea.setText("Logged in as " + currentUser.getUsername() + " with role " + currentUser.getRole());
                    // Atualiza a interface para mostrar os botões de opção
                    toggleButtons(true);
                    panel.revalidate(); // Revalida o painel
                    panel.repaint();    // Repaint o painel
                } else {
                    textArea.setText("Invalid credentials. Please try again.");
                }
            }
        });

        viewHouseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("House Details:\n" + house.listHouseDetails());
            }
        });

        viewBedroomsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("Bedroom Details:\n" + house.listBedrooms());
            }
        });

        rentRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentUser != null && currentUser.getRole().equals("Tenant")) {
                    // Implement room rent logic
                }
            }
        });

        maintenanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentUser != null && currentUser.getRole().equals("Admin")) {
                    // Implement maintenance logic
                }
            }
        });

        viewBathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bathDetails = house.getBath() != null ? "Available" : "Not set"; // Assumindo que getBath retorna um objeto
                textArea.setText("Bath Details:\n" + bathDetails);
            }
        });

        makePaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentUser != null && currentUser.getRole().equals("Admin")) {
                    // Implement payment logic
                }
            }
        });

        listPaymentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentUser != null && currentUser.getRole().equals("Admin")) {
                    textArea.setText("Payments:\n" + paymentManager.listPayments());
                }
            }
        });

        listUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentUser != null && currentUser.getRole().equals("Admin")) {
                    textArea.setText("Users:\n" + userManager.listUsers());
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void toggleButtons(boolean enabled) {
        viewHouseButton.setEnabled(enabled);
        viewBedroomsButton.setEnabled(enabled);
        rentRoomButton.setEnabled(enabled && (currentUser != null && currentUser.getRole().equals("Tenant")));
        maintenanceButton.setEnabled(enabled && (currentUser != null && currentUser.getRole().equals("Admin")));
        viewBathButton.setEnabled(enabled);
        makePaymentButton.setEnabled(enabled && (currentUser != null && currentUser.getRole().equals("Admin")));
        listPaymentsButton.setEnabled(enabled && (currentUser != null && currentUser.getRole().equals("Admin")));
        listUsersButton.setEnabled(enabled && (currentUser != null && currentUser.getRole().equals("Admin")));
    }
}
