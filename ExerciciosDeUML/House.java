public class House {
    private Bath bath;
    private Kitchen kitchen;
    private Mailbox mailbox;
    private Mortgage mortgage;
    private Bedroom[] bedrooms;

    public House() {}

    public void setBath(Bath bath) {
        this.bath = bath;
    }

    public void setKitchen(Kitchen kitchen) {
        this.kitchen = kitchen;
    }

    public void setMailbox(Mailbox mailbox) {
        this.mailbox = mailbox;
    }

    public void setMortgage(Mortgage mortgage) {
        this.mortgage = mortgage;
    }

    public void setBedrooms(Bedroom[] bedrooms) {
        this.bedrooms = bedrooms;
    }

    public void listHouseDetails() {
        System.out.println("House details:");
        System.out.println("Bath: " + (bath != null ? "Available" : "Not set"));
        System.out.println("Kitchen: " + (kitchen != null ? "Available" : "Not set"));
        System.out.println("Mailbox: " + (mailbox != null ? "Available" : "Not set"));
        System.out.println("Mortgage: " + (mortgage != null ? "Available" : "Not set"));
    }

    public void listBedrooms() {
        if (bedrooms != null) {
            for (Bedroom bedroom : bedrooms) {
                System.out.println("Bedroom: " + bedroom.getName() + ", Price: $" + bedroom.getPrice() + ", Rented: " + (bedroom.isRented() ? "Yes" : "No"));
            }
        } else {
            System.out.println("No bedrooms set.");
        }
    }
}
