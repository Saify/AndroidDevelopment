package ca.fusiondev.rockpaperscissor;

public interface ChooseWeapon   {
	public static final int rock = 1;
	public static final int paper = 2;
	public static final int scissor = 3;
	
	Weapon selectedWeapon = null;
	
	public Weapon getSelectedWeapon();

	public void setSelectedWeapon(Weapon selectedWeapon);

}
