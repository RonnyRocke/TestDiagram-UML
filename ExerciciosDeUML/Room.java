import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Room {
    private String name;
    private double price;
    private boolean isRented;

    public Room(String name, double price) {
        this.name = name;
        this.price = price;
        this.isRented = false;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isRented() {
        return isRented;
    }

    public void rentRoom() {
        if (!isRented) {
            isRented = true;
            saveRoomDetails();
            System.out.println("Room " + name + " has been rented.");
        } else {
            System.out.println("Room " + name + " is already rented.");
        }
    }

    public void setUnderMaintenance(boolean status) {
        System.out.println("Room " + name + " maintenance status: " + (status ? "Under Maintenance" : "Available"));
    }

    private void saveRoomDetails() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(name + "_details.txt"))) {
            writer.write("Room Name: " + name);
            writer.newLine();
            writer.write("Price: $" + price);
            writer.newLine();
            writer.write("Status: " + (isRented ? "Rented" : "Available"));
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
