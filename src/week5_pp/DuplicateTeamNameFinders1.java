package week5_pp;

public class DuplicateTeamNameFinders1 {

    static String findDuplicateTeam(String[] teamNames) {

        for (int i = 0; i < teamNames.length; i++) {
            for (int j = i + 1; j < teamNames.length; j++) {

                if (teamNames[i].equals(teamNames[j])) {
                    return "Duplicate Found: " + teamNames[i];
                }
            }
        }
        return "No Duplicates Found";
    }

    public static void main(String[] args) {

        String[] teams = {
                "ByteForce",
                "CodeCrafters",
                "ByteForce"
        };

        System.out.println(findDuplicateTeam(teams));
    }
}
