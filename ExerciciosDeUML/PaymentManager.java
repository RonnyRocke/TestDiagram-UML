import java.util.HashMap;
import java.util.Map;

public class PaymentManager {
    private Map<String, Double> balances = new HashMap<>();
    private Map<String, String> payments = new HashMap<>();

    public void addUser(String username, double initialBalance) {
        balances.put(username, initialBalance);
    }

    public void makePayment(String username, double amount, String date) {
        if (balances.containsKey(username)) {
            double currentBalance = balances.get(username);
            if (currentBalance >= amount) {
                balances.put(username, currentBalance - amount);
                payments.put(username, date + " - Payment of $" + amount);
                System.out.println("Payment successful.");
            } else {
                System.out.println("Insufficient balance.");
            }
        } else {
            System.out.println("User not found.");
        }
    }

    public void listPayments() {
        payments.forEach((user, details) -> System.out.println(user + ": " + details));
    }

    public double getBalance(String username) {
        return balances.getOrDefault(username, 0.0);
    }
}
