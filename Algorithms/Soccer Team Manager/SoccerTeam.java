package ISC4U;

public class SoccerTeam {
	
	private int wins;
	private int losses;
	private int ties;
	private static int othersScore;
	private static int gamesPlayed = 0;
	
	public SoccerTeam () {
		wins=0;losses=0;ties=0;othersScore=0;gamesPlayed=0;
	}

	
	public SoccerTeam (int w, int l, int t) {
		if (w >= 0 && l >= 0 && t >= 0 ) {
			wins = w;
			losses = l;
			ties = t;
		}
		else {
			throw new IllegalArgumentException("One of the numbers are less than 0.");
		}
	}

	public void played (SoccerTeam other, int myScore, int otherScore) {
		if (myScore > otherScore) wins++;
		else if (myScore < otherScore) losses++;
		else ties++;
		
		gamesPlayed++;
		othersScore += otherScore;
	}
	
	public static String getScores() {
	    return "Total games played: " + gamesPlayed + "\nTotal score: " + othersScore;
	}

	public int getPoints() {
	    return (wins * 2) + ties;
	}
	
	public void reset() {
	   wins = 0; losses = 0; ties = 0;
	}
	
	public static void startTournament() {
		othersScore =0; gamesPlayed = 0;
	}
}