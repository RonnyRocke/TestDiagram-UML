import javax.swing.SwingUtilities;

public class MainFrameLauncher {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        PaymentManager paymentManager = new PaymentManager();

        // Add sample users
        userManager.addUser(new User("admin", "admin123", "Admin"));
        userManager.addUser(new User("tenant1", "tenant123", "Tenant"));

        // Sample house setup
        House house = new House();
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

        SwingUtilities.invokeLater(() -> new MainFrame(userManager, paymentManager, house).setVisible(true));
    }
}
