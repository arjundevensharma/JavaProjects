package ISC4U;

public class SoccerTeamDriver {

	public static void main(String[] args) {
		SoccerTeam a = new SoccerTeam();
		SoccerTeam b = new SoccerTeam();
		SoccerTeam c = new SoccerTeam();
		
		SoccerTeam.startTournament();
		
		a.played(b, 1, 5);
		b.played(c, 2, 1);
		c.played(a, 11, 5);
		
		System.out.println("Team A's wins: " + a.getPoints() + "\n" + "Team B's wins: " + b.getPoints() + "\n" +"Team C's wins: " + c.getPoints() + "\n");
		
		System.out.println(SoccerTeam.getScores());
	
	}

}
