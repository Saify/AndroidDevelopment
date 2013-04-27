package ca.fusiondev.rockpaperscissor;

public class Weapon    {
	
	public static final int rock = 1;
	public static final int paper = 2;
	public static final int scissor = 3;
	
	public static final String ROCK_KEY = "Rock";
	
	public static final String PAPER_KEY = "Paper";
	
	public static final String SCISSOR_KEY = "Scissor";
	
	private int weapon;
	
	private String weaponName;

	public Weapon() {
	}
	
	public Weapon(int defaultWeapon) {
		setWeapon(defaultWeapon);
	}
	
	public int getWeapon() {
		return weapon;
	}

	public void setWeapon(int weapon) {
		this.weapon = weapon;
		if(weapon == rock){
			setWeaponName(ROCK_KEY);
		}else if(weapon == paper){
			setWeaponName(PAPER_KEY);
		}else if(weapon == scissor){
			setWeaponName(SCISSOR_KEY);
		}
	}

	public String getWeaponName() {
		return weaponName;
	}

	public void setWeaponName(String weaponName) {
		this.weaponName = weaponName;
	}
}
