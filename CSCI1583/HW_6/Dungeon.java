/*

Dungeon class for Adventure Mode of Foe

@author Deven Ronquillo
@timestamp oct 27 2016


*/


public class Dungeon{

private Room siteEntrance;
private Room entranceHall;
private Room toolStorage;
private Room livingQuarters;
private Room bunkRoom;
private Room lavatoryHall;
private Room messHall;
private Room throughHall;
private Room showerRoom;
private Room officeHall;
private Room overseersOffice;
private Room chamberEntrance;
private Room chamber;


Dungeon(){

	this.siteEntrance = new Room("Excavation Site Entrance");
	this.entranceHall = new Room("Entrance hall, its covered in dust");
	this.toolStorage = new Room("Tool Storage but find nothing of value");
	this.livingQuarters = new Room("Living Quarters, the place is in disarray");
	this.bunkRoom = new Room("Bunk Room, mattresses lay strewn about");
	this.lavatoryHall = new Room("Lavatory Hall and hear the pater of running water");
	this.messHall = new Room("Mess Hall, old preserved food still lay on the tables");
	this.throughHall = new Room("Through Hall, its empty apart from a few containers");
	this.showerRoom = new Room("Shower Room and find the source of the pater, an old broken pipe");
	this.officeHall = new Room("Office Hall, dusty notes and book lay about the room");
	this.overseersOffice = new Room("Overseers Office and find it in disarray with dried blood plastered to the back wall");
	this.chamberEntrance = new Room("Excavation Chamber Entrance and see light pouring in from its distant end");
	this.chamber = new Room("Excavation Chamber and are greeted by half uncovered relics from days past");
	
	
	this.siteEntrance.setNorth(this.entranceHall);
	
	this.entranceHall.setNorth(this.livingQuarters);
	this.entranceHall.setEast(this.toolStorage);
	this.entranceHall.setSouth(this.siteEntrance);
	
	this.toolStorage.setWest(this.entranceHall);
	
	this.livingQuarters.setExits(this.lavatoryHall, this.messHall, this.entranceHall, this.bunkRoom);
	
	this.bunkRoom.setEast(this.livingQuarters);
	
	this.lavatoryHall.setNorth(this.throughHall);
	this.lavatoryHall.setSouth(this.livingQuarters);
	this.lavatoryHall.setWest(this.showerRoom);
	
	this.messHall.setWest(this.livingQuarters);
	
	this.throughHall.setSouth(this.lavatoryHall);
	this.throughHall.setWest(this.officeHall);
	
	this.showerRoom.setEast(this.lavatoryHall);
	
	this.officeHall.setNorth(this.overseersOffice);
	this.officeHall.setEast(this.throughHall);
	this.officeHall.setWest(this.chamberEntrance);
	
	this.overseersOffice.setSouth(this.officeHall);
	
	this.chamberEntrance.setNorth(this.chamber);
	this.chamberEntrance.setEast(this.officeHall);
	this.chamber.setSouth(this.chamberEntrance);

}//end constructor

public Room getRoom0(){
	
	return this.siteEntrance;
	
}//end method getRoom0

}//end class Dungeon