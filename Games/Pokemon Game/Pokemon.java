public class Pokemon {
	public String name;
	public int type; //1-grass, 2-fire, 3-water, 4-electric, etc
	public int hp;
	public int attack;
	
	/**
	 * Constructor for the Pokemon class.
	 * @param nm - the name of the Pokemon
	 * @param tp - the type of the Pokemon
	 * @param HP - the HP of the Pokemon
	 * @param att - the attack power of the Pokemon
	 */
	public Pokemon(String nm, int tp, int HP, int att) {
		name = nm;
		type = tp;
		hp = HP;
		attack = att;
	}
	
	/**
     * Returns a string representation of the Pokemon object.
     * @return the string representation of the Pokemon object.
     */
	public String toString() {
		//various properties of pokemon are outputted
		return "\nName: " + name + ", Type: " + getType(type) + ", HP: " + hp + ", Attack: " + attack + ".";
	}
	
	/**
     * Returns the name of the Pokemon type for the given index.
     * @param index the index of the Pokemon type.
     * @return the name of the Pokemon type for the given index.
     * @throws IllegalArgumentException if the index is out of range.
     */
	public static String getType(int index) {
		//will output a string pokemon type associated with an index value of 0-17
		String[] types = {"Normal", "Fighting", "Flying", "Poison", "Ground", "Rock", "Bug", "Ghost", "Steel", "Fire", "Water", "Grass", "Electric", "Psychic", "Ice", "Dragon", "Dark", "Fairy"};

	    return types[index];
	}
}