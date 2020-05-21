/*

Player class for Adventure Mode of Foe

@author Deven Ronquillo
@timestamp Nov 10 2016


*/
public class Player extends gameCharacter{

	private int mana;
	
	public Player(){
		
		super("player");
		
		this.mana = 0;
		
	}// end player constructor
	
	public void attack(Monster monster){
		
		monster.takeDamage(getAttackPower());
		
	}//end attack method
	
	public void castSpell(Monster monster){
		
		monster.takeDamage(monster.getHealth() / 2);
		
	}//end castSpall methods
	
	public void chargeMana(){
		
		this.mana += 1;
		
	}//end chargeMana method
	
	public int getMana(){
		
		return this.mana;
		
	}//end getMana method
	
	public void updateMana(){
		
		this.mana -= 2;
			
	}//end updateMana method
	
	public String toString(){
		
		String str = "Name: " + getName() + " Health: " + getHealth() + " Attack Power: " + getAttackPower() + "Mana: " + this.mana;
		
		return str;
		
	}
	

}//end class
