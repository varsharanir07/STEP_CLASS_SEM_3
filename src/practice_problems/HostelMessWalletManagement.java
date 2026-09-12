package practice_problems;

class MessWallet {
    private double balance;

    public MessWallet(double opening) {
        if (opening < 0) {
            balance = 0;
            System.out.println("Invalid opening balance. Starting at 0.");
        } else {
            balance = opening;
        }
    }

    public void topUp(double amount) {
        if (amount <= 0)
            System.out.println("Top-up rejected");
        else {
            balance += amount;
            System.out.println("Balance after top-up: " + balance);
        }
    }

    public void deduct(double amount) {
        if (amount > balance)
            System.out.println("Deduct rejected: insufficient balance");
        else
            balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}

public class HostelMessWalletManagement {
    public static void main(String[] args) {
        MessWallet wallet = new MessWallet(500);

        wallet.topUp(200);
        wallet.deduct(1000);

        System.out.println("Final balance: " + wallet.getBalance());
    }
}