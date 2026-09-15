package week5_ap;

import java.util.Arrays;

class Player implements Comparable<Player> {

    String name;
    int matchesPlayed;
    double battingAverage;
    boolean injured;
    double fantasyPoints;

    Player(String name, int matchesPlayed,
           double battingAverage, boolean injured) {

        this.name = name;
        this.matchesPlayed = matchesPlayed;
        this.battingAverage = battingAverage;
        this.injured = injured;

        fantasyPoints = battingAverage;
    }

    static boolean isDraftable(int matchesPlayed) {
        return matchesPlayed >= 10;
    }

    static boolean isDraftable(int matchesPlayed, boolean injured) {
        return matchesPlayed >= 5 && !injured;
    }

    public int compareTo(Player other) {
        return Double.compare(other.fantasyPoints, this.fantasyPoints);
    }
}

public class FantasyLeagueAutoDraftRankingEngine {

    static String draftAndRank(Player[] players) {

        Player[] temp = new Player[players.length];
        int count = 0;

        for (Player p : players) {

            if (Player.isDraftable(p.matchesPlayed) ||
                    Player.isDraftable(p.matchesPlayed, p.injured)) {

                temp[count++] = p;
            }
        }

        Player[] draftable = Arrays.copyOf(temp, count);

        Arrays.sort(draftable);

        String result = "";

        for (int i = 0; i < draftable.length; i++) {

            result += (i + 1) + ". " + draftable[i].name;

            if (i != draftable.length - 1)
                result += " | ";
        }

        return result;
    }

    public static void main(String[] args) {

        Player[] players = {
                new Player("Virat", 15, 48.0, false),
                new Player("Rahul", 7, 55.0, false),
                new Player("Sameer", 3, 60.0, false),
                new Player("Dev", 12, 20.0, true)
        };

        System.out.println(draftAndRank(players));
    }
}
