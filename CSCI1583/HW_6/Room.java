/*

Room class for Adventure Mode of Foe

@author Deven Ronquillo
@timestamp Nov 10 2016


*/

public class Room{
	
	private String desc;
	private Room north;
	private Room east;
	private Room south;
	private Room west;
	
	Room(String desc){
		
		this.desc = desc;
		
	}//end constructor
	
	public void setNorth(Room north){
		
		this.north = north;
	
	}//end method setNorth
	
	public void setEast(Room east){
		
		this.east = east;
	
	}//end method setEast
	
	public void setSouth(Room south){
		
		this.south = south;
	
	}//end method setSouth
	
	public void setWest(Room west){
		
		this.west = west;
	
	}//end method setWest
	
	public void setExits(Room north, Room east, Room south, Room west){
		
		this.north = north;
		this.east = east;
		this.south = south;
		this.west = west;
		
	}//end method setExits
	
	public Room getNorth(){
		
		return this.north;
		
	}//end method getNorth
	
	public Room getEast(){
		
		return this.east;
		
	}//end method getEast
	
	public Room getSouth(){
		
		return this.south;
		
	}//end method getSouth
	
	public Room getWest(){
		
		return this.west;
		
	}//end method getWest
	
	public String getDesc(){
		
		return this.desc;
		
	}//end method getDisc
	
	public String getExits(){
		
		String newString = "";
		
		if(this.north != null){
			
			newString = newString + "Enter W to go North.\n";
			
		}//end if
		
		if(this.east != null){
			
			newString = newString + "Enter D to go East.\n";
			
		}//if end
		
		if(this.south != null){
			
			newString = newString + "Enter S to go South.\n";
			
		}//end if
		
		if(this.west != null){
			
			newString = newString + "Enter A to go West.\n";
			
		}//end if
		
		
		return  newString;
		
	}
	
	public String toString(){
		
		String newString = "Description: " + this.desc + "\nExits: \n";
		
		if(this.north != null){
			
			newString = newString + "North.\n";
			
		}//end if
		
		if(this.east != null){
			
			newString = newString + "East.\n";
			
		}//if end
		
		if(this.south != null){
			
			newString = newString + "South.\n";
			
		}//end if
		
		if(this.west != null){
			
			newString = newString + "West.\n";
			
		}//end if
		
		
		return  newString;
	}
}//end class Room