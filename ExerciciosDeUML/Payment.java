public class Payment {
    private String username;
    private double amount;
    private String date;

    public Payment(String username, double amount, String date) {
        this.username = username;
        this.amount = amount;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public void printReceipt() {
        System.out.println("Payment Receipt");
        System.out.println("Username: " + username);
        System.out.println("Amount: $" + amount);
        System.out.println("Date: " + date);
    }
}
