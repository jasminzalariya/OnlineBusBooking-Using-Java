import java.util.*;

interface Bus {
    
    void displayAvailableSeats();

    int bookSeats();

    void cancelBooking(int seatNumber);

    void addBusInformation();

    void addCustomerInformation();

    void processPayment(double amount);

    void generateTicket(String customerName, String customerPhone, double amount, boolean paymentStatus);

    void allTicketBookShow();
}

class Customer implements Bus {
    Scanner sc = new Scanner(System.in);
    public int busSeats;
    public boolean seats[];
    public String busName;
    public String busId;
    public String busTime;
    public String busFrom;
    public String busDestination;
    public String driverName;
    public String conductorName;
    public String customerName;
    public String customerPhone;
    public double amount;

    public HashMap<Integer, String[]> bookedSeats = new HashMap<>();

    public void addBusInformation() {
        System.out.println("Enter bus details:");
        System.out.print("Bus Name: ");
        busName = sc.nextLine();
        System.out.print("Bus Driver Name: ");
        driverName = sc.nextLine();
        System.out.print("Bus Conductor Name: ");
        conductorName = sc.nextLine();
        System.out.print("Bus ID: ");
        busId = sc.nextLine();
        System.out.print("Bus Time: ");
        busTime = sc.nextLine();
        System.out.print("Bus From: ");
        busFrom = sc.nextLine();
        System.out.print("Bus Destination: ");
        busDestination = sc.nextLine();
        System.out.print("Number of Seats in the Bus: ");
        busSeats = sc.nextInt();
        System.out.println("Enter the price of Ticket :");
        double price = sc.nextDouble();
        seats = new boolean[busSeats];
        System.out.println("Bus information added successfully!");
        System.out.println("-----------------------------------");
    }

    public void addCustomerInformation() {
        sc.nextLine();
        System.out.println("Enter customer details:");
        System.out.print("Customer Name: ");
        customerName = sc.nextLine();
        System.out.print("Customer Phone: ");
        customerPhone = sc.nextLine();
        System.out.println("Customer information added successfully!");
        System.out.println("---------------------------------------");
    }

    public void showBusInfo() {
        System.out.println("-------------------");
        System.out.println("Bus Name: " + busName);
        System.out.println("Bus Driver Name: " + driverName);
        System.out.println("Bus Conductor Name: " + conductorName);
        System.out.println("Bus ID: " + busId);
        System.out.println("Bus Time: " + busTime);
        System.out.println("Bus From: " + busFrom);
        System.out.println("Bus Destination: " + busDestination);
        System.out.println("Number of Seats in the Bus: " + busSeats);
        System.out.println("---------------------------------");
    }

    public void displayAvailableSeats() {
        System.out.println("Available Seats:");
        for (int i = 0; i < seats.length; i++) {
            if (!seats[i]) {
                System.out.print("Seat " + (i + 1) + " ");
            }
        }
        System.out.println();
    }

    public int bookSeats() {
        displayAvailableSeats();
        System.out.print("Enter the seat number you want to book: ");
        int seatNumber = sc.nextInt();
        if (seatNumber >= 1 && seatNumber <= busSeats) {
            if (!seats[seatNumber - 1]) {
                seats[seatNumber - 1] = true;
                System.out.println("Booking successful! Seat " + seatNumber + " is reserved.");
                addCustomerInformation(); // Adding customer information after booking
                System.out.println("Enter the price of Ticket :");
                double price = sc.nextDouble();
                processPayment(price); // Assuming a fixed amount for each booking
                // Add booked seat details to the map
                bookedSeats.put(seatNumber, new String[] { customerName, customerPhone, String.valueOf(true) });
                return seatNumber;
            } else {
                System.out.println("Sorry, the seat is already booked. Please choose another seat.");
            }
        } else {
            System.out.println("Invalid seat number. Please enter a number between 1 and " + busSeats + ".");
        }
        return -1; 
    }

    public void allTicketBookShow() {
        System.out.println("All Booked Tickets:");
        for (Map.Entry<Integer, String[]> entry : bookedSeats.entrySet()) {
            int seatNumber = entry.getKey();
            String customerInfo[] = entry.getValue();
            String customerName = customerInfo[0];
            String customerPhone = customerInfo[1];
            boolean paymentStatus = Boolean.parseBoolean(customerInfo[2]);

            System.out.println("Seat Number: " + seatNumber);
            System.out.println("Customer Name: " + customerName);
            System.out.println("Customer Phone: " + customerPhone);
            System.out.println("bus Name: " + busName);
            System.out.println("driver Name: " + driverName);


            System.out.println("Payment Status: " + ((paymentStatus) ? "Successful" : "Unsuccessful"));
            System.out.println("---------------------------------------");
        }
    }

    public void processPayment(double amount) {
        // Here you can implement your payment processing logic, like integrating with a
        // payment gateway
        System.out.println("Payment processed successfully! Amount: $" + amount);
        // Update ticket with payment status
        if (customerName != null && customerPhone != null) {
            generateTicket(customerName, customerPhone, amount, true);
        } else {
            System.out.println("Cannot generate ticket. Customer information incomplete.");
        }
    }

    public void generateTicket(String customerName, String customerPhone, double amount, boolean paymentStatus) {
        System.out.println("----------- Ticket -----------");
        System.out.println("Customer Name: " + customerName);
        System.out.println("Customer Phone: " + customerPhone);
      
        System.out.println("Payment Status: " + ((paymentStatus) ? "Successful" : "Unsuccessful"));
        System.out.println("------------------------------");
    }

    public void generateticket(String customerName, String customerPhone, boolean paymentStatus) {
        System.out.println("----------- Ticket -----------");
        System.out.println("Customer Name: " + customerName);
        System.out.println("Customer Phone: " + customerPhone);
        System.out.println(": " + customerPhone);
        
        
        System.out.println("Payment Status: " + ((paymentStatus) ? "Successful" : "Unsuccessful"));
        System.out.println("------------------------------");
    }

    public void cancelBooking(int seatNumber) {
        if (seatNumber >= 1 && seatNumber <= busSeats) {
            if (seats[seatNumber - 1]) {
                seats[seatNumber - 1] = false;
                System.out.println("Booking canceled! Seat " + seatNumber + " is now available.");
                bookedSeats.remove(seatNumber);
            } else {
                System.out.println("No booking found for seat " + seatNumber + ". Please check the seat number.");
            }
        } else {
            System.out.println("Invalid seat number. Please enter a number between 1 and " + busSeats + ".");
        }

    }
}

public class OnlineBusBooking {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the online bus booking system:");
        System.out.println("-----------------------------------------");

        Customer customer = new Customer();
        customer.addBusInformation();

        while (true) {
            System.out.println("1. Display Available Seats");
            System.out.println("2. Book a Seat");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Show the Bus Information");
            System.out.println("5. Show Ticket");
            System.out.println("6. Show All Booked Tickets");
            System.out.println("7. Exit");
            System.out.println("Enter your choice: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    customer.displayAvailableSeats();
                    break;
                case 2:
                    int bookedSeat = customer.bookSeats();
                    if (bookedSeat != -1) {
                        System.out.println("Seat booked successfully! Seat number: " + bookedSeat);
                    }
                    break;
                case 3:
                    System.out.print("Enter the seat number you want to cancel the booking for: ");
                    int cancelSeat = sc.nextInt();
                    customer.cancelBooking(cancelSeat);
                    break;
                case 4:
                    customer.showBusInfo();
                    break;
                case 5:
                    System.out.print("Enter the seat number to display the ticket: ");
                    int seatNumber = sc.nextInt();
                    if (seatNumber >= 1 && seatNumber <= customer.busSeats) {
                        if (customer.seats[seatNumber - 1]) {
                            // If the seat is booked, display the ticket
                            customer.generateticket(customer.customerName, customer.customerPhone, true);
                        } else {
                            // If the seat is not booked, display a message indicating no booking found
                            System.out.println(
                                    "No booking found for seat " + seatNumber + ". Please check the seat number.");
                        }
                    } else {
                        // If the entered seat number is invalid, display an error message
                        System.out.println("Invalid seat number. Please enter a number between 1 and " + customer.busSeats + ".");
                    }
                    break;

                case 6:
                    customer.allTicketBookShow();
                    break;
                case 7:
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
