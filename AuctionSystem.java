package courseWork;
import java.util.Scanner;
public class AuctionSystem {
    public static void main(String[]args){
        Scanner input = new Scanner(System.in);
        
        System.out.println("===== SK. MOTORS AUCTION =====");
        System.out.println("Selling to highest bidder!\n");
        
        // Step 1: Get vehicle details
        System.out.println("--- Vehicle Information ---");
        System.out.print("Enter registration number: ");
        String regNumber = input.nextLine();
        
        System.out.print("Enter vehicle cost: ");
        double cost = input.nextDouble();
        
        System.out.print("Enter balance left to pay: ");
        double balance = input.nextDouble();
        
        // Clear the input buffer
        input.nextLine();
        
        System.out.println("\n--- Auction Bidding ---");
        System.out.println("Enter 3 bids from different bidders:");
        
        // Step 2: Get 3 bidders
        String[] names = new String[3];
        double[] bids = new double[3];
        
        for (int i = 0; i < 3; i++) {
            System.out.print("\nBidder " + (i+1) + " name: ");
            names[i] = input.nextLine();
            
            System.out.print("Bid amount: ");
            bids[i] = input.nextDouble();
            input.nextLine(); // Clear buffer
        }
        
        // Step 3: Find the highest bid
        double highestBid = bids[0];
        String winner = names[0];
        
        if (bids[1] > highestBid) {
            highestBid = bids[1];
            winner = names[1];
        }
        
        if (bids[2] > highestBid) {
            highestBid = bids[2];
            winner = names[2];
        }
        
        // Step 4: Get deposits and expenses
        System.out.println("\n--- Financial Details ---");
        System.out.print("Total deposits received: ");
        double deposits = input.nextDouble();
        
        System.out.print("Total expenses incurred: ");
        double expenses = input.nextDouble();
        
        // Step 5: Calculate profit or loss
        double totalMoney = highestBid + deposits;
        double totalCost = cost + expenses + balance;
        double profitOrLoss = totalMoney - totalCost;
        
        // Step 6: Display results
        System.out.println("\n===== AUCTION RESULTS =====");
        System.out.println("Vehicle: " + regNumber);
        System.out.println("Vehicle Cost: " + cost);
        System.out.println("Balance on Vehicle: " + balance);
        
        System.out.println("\n--- Bids Received ---");
        System.out.println("1. " + names[0] + ": " + bids[0]);
        System.out.println("2. " + names[1] + ": " + bids[1]);
        System.out.println("3. " + names[2] + ": " + bids[2]);
        
        System.out.println("\n--- Winner ---");
        System.out.println(winner + " with bid: " + highestBid);
        
        System.out.println("\n--- Financial Summary ---");
        System.out.println("Deposits: " + deposits);
        System.out.println("Expenses: " + expenses);
        System.out.println("Total Money Received: " + totalMoney);
        System.out.println("Total Cost: " + totalCost);
        
        if (profitOrLoss > 0) {
            System.out.println("Profit Made: " + profitOrLoss);
        } else if (profitOrLoss < 0) {
            System.out.println("Loss Incurred: " + profitOrLoss);
        } else {
            System.out.println("No Profit, No Loss (Break Even)");
        }
        
        System.out.println("\nThank you for using SK. Motors!");
        input.close();
    }
}
