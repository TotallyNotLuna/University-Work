/*

Player gameCharacter for Adventure Mode of Foe

@author Deven Ronquillo
@timestamp Nov 10 2016


*/
public class gameCharacter{
	
	private String name;
	private int health;
	private int attackPower;
	
	public gameCharacter(String type){
		
		if(type.equals("player")){
			
			this.name = "Player 1";
			this.health = 100;
			this.attackPower = 40;
	
		}//end if
		else if(type.equals("monster")){
			
			int monsterType = randomRange(0, 3);
			
			switch(monsterType){
			
				case 0://radroach
	
					this.name = "Radroach";
					this.health = 75 + randomRange(0, 24);
					this.attackPower = 8 + randomRange(0, 4);
				
					break;
				
				case 1://feral ghoul
			
					this.name = "Feral Ghoul";
					this.health = 100 + randomRange(0, 24);
					this.attackPower = 12 + randomRange(0, 4);
				
					break;
				
				case 2://manticore
			
					this.name = "Manticore";
					this.health = 150 + randomRange(0, 49);
					this.attackPower = 15 + randomRange(0, 4);
				
					break;
				
				case 3://hydra
			
					this.name = "Hydra";
					this.health = 200 + randomRange(0, 50);
					this.attackPower = 20 + randomRange(0, 5);
				
					break;
			
			
			}//end switch statement
			
		}//end else if
			
	}//end gameCharacter Constructor
	
	public void takeDamage(int damage){
		
		this.health = this.health - damage;
		
	}//end take damage method
	
	public String getName(){
		
		return this.name;
	
	}//end getName method
	
	public int getAttackPower(){
		
		return this.attackPower;
		
	}//end getAttackPower method
	
	public int getHealth(){
		
		return this.health;
		
	}//end getHealth method
	
	public String toString(){
		
		String str = " Name: " + this.name + " Health: " + this.health + " Attack Power: " + this.attackPower;
		
		return str;

	}//end toString method
	
	public int randomRange(int min, int max){
		
		return (int)((Math.random() * ((max - min) + 1)) + min);
		
	}//end randomRange method	
	
}//end class