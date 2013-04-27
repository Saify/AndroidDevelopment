package ca.fusiondev.rockpaperscissor;

public class Player{
	
	private String name;
	
	private int currentScore;
	
	private Weapon selectedWeapon;
	
	public Player(String name, int score) {
		setName(name);
		setCurrentScore(score);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCurrentScore() {
		return currentScore;
	}

	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}

	public int getSelectedWeapon() {
		return selectedWeapon.getWeapon();
	}
	
	public Weapon getWeapon() {
		return selectedWeapon;
	}

	public void setSelectedWeapon(int selectedWeapon) {
		if(this.selectedWeapon == null){
			this.selectedWeapon = new Weapon();
		}
		this.selectedWeapon.setWeapon(selectedWeapon);
	}

}
