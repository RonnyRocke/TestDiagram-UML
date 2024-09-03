import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        PaymentManager paymentManager = new PaymentManager();
        House house = new House();

        // Add sample users
        userManager.addUser(new User("admin", "admin123", "Admin"));
        userManager.addUser(new User("tenant1", "tenant123", "Tenant"));

        // Sample house setup
        Bath bath = new Bath();
        Kitchen kitchen = new Kitchen();
        Mailbox mailbox = new Mailbox();
        Mortgage mortgage = new Mortgage();
        house.setBath(bath);
        house.setKitchen(kitchen);
        house.setMailbox(mailbox);
        house.setMortgage(mortgage);

        Bedroom bedroom1 = new Bedroom(403, "QUARTO1");
        Bedroom bedroom2 = new Bedroom(387, "QUARTO2");
        Bedroom bedroom3 = new Bedroom(211, "QUARTO3");
        Bedroom[] bedrooms = new Bedroom[]{bedroom1, bedroom2, bedroom3};
        house.setBedrooms(bedrooms);

        // Interface gráfica
        JFrame frame = new JFrame("Room Management System");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel, house, userManager, paymentManager);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel, House house, UserManager userManager, PaymentManager paymentManager) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        JTextArea outputArea = new JTextArea();
        outputArea.setBounds(10, 120, 550, 200);
        panel.add(outputArea);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());
                User user = userManager.authenticate(username, password);

                if (user != null) {
                    outputArea.setText("Welcome, " + username + "!\n");

                    if (user.isAdmin()) {
                        outputArea.append("Admin Access Granted.\n");
                        userManager.listUsers();
                    } else {
                        outputArea.append("Tenant Access Granted.\n");
                        house.listHouseDetails();
                        house.listBedrooms();
                    }

                    paymentManager.addUser(username, 1000); // Set initial balance for demo
                    paymentManager.makePayment(username, 100, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                    outputArea.append("Current balance: $" + paymentManager.getBalance(username) + "\n");
                    paymentManager.listPayments();
                } else {
                    outputArea.setText("Invalid credentials.");
                }
            }
        });
    }
}
