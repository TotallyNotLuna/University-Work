/*

monster class for Adventure Mode of Foe

@author Deven Ronquillo
@timestamp Nov 10 2016


*/
public class Monster extends gameCharacter{
	
	private int xp;
	
	public Monster(){
		
		super("monster");//build superclass object
		
		if(getName().equals("Radroach")){
			
			this.xp = 1;
			
		}//end else if
		else if(getName().equals("Feral Ghoul")){
			
			this.xp = 3;
			
		}//end else if
		else if(getName().equals("Manticore")){
			
			this.xp = 5;
			
		}//end else if
		else if(getName().equals("Hydra")){
			
			this.xp = 15;
			
		}//end else if
		
	}//end monster constructor
	
	public void attack(Player player){
		
		player.takeDamage(getAttackPower());
		
	}// end method attack
	
	public int getXP(){
		
		return this.xp;
		
	}//end getXP method
	
	public String toString(){
		
		String str = "Name: " + getName() + " Health: " + getHealth() + " Attack Power: " + getAttackPower() + "XP: " + this.xp;
		
		return str;
		
	}//end toString method
	
}//end class monster