package Practice;


import java.util.Random;
import java.util.Scanner;

public class Rock_paper_scissor {

    static String playRound(String player, String computer) {

        if (player.equals(computer))
            return "Draw";

        if ((player.equals("Rock") && computer.equals("Scissors")) ||
                (player.equals("Paper") && computer.equals("Rock")) ||
                (player.equals("Scissors") && computer.equals("Paper")))
            return "Player Wins";

        return "Computer Wins";
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        String[] moves = {"Rock","Paper","Scissors"};

        int win=0, loss=0, draw=0;

        System.out.println("Rock Paper Scissors (5 Rounds)\n");

        for(int i=1;i<=5;i++){

            System.out.print("Round "+i+" Enter move: ");
            String player = sc.next();

            player = player.substring(0,1).toUpperCase()+player.substring(1).toLowerCase();

            String computer = moves[r.nextInt(3)];

            String result = playRound(player,computer);

            System.out.println("Computer : "+computer);
            System.out.println("Result   : "+result+"\n");

            if(result.equals("Player Wins")) win++;
            else if(result.equals("Computer Wins")) loss++;
            else draw++;
        }

        double percentage = (win/5.0)*100;

        System.out.println("------ FINAL SUMMARY ------");
        System.out.println("Wins   : "+win);
        System.out.println("Losses : "+loss);
        System.out.println("Draws  : "+draw);
        System.out.printf("Win %%  : %.2f",percentage);

        sc.close();
    }
}
