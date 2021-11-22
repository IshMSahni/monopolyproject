package monopoly.Model;

import java.util.*;

public class Board {
    //propertyholder is a map of the whole board
    public HashMap<Integer, Property> propertyholder;

    //private final Integer[] boardspaces = new Integer[25];
    //might want to use an array later instead of using integers directly in the map
    public Board (){

        propertyholder = new HashMap<>();
        Property go = new Property("Go", 0, false, 0, true, "", false);
        propertyholder.put(0,go);
        Property p2 = new Property("Mediterranean Avenue", 60, false,1, false, "", false, 1);
        propertyholder.put(1,p2);
        Property  p3 = new Property("Baltic Avenue", 60, false,2, false, "", false, 1);
        propertyholder.put(2,p3);
        Property  incTax = new Property("Income Tax", 0, false,3, true, "", false);
        propertyholder.put(3,incTax);
        Property  rr1 = new Property("Reading Railroad", 200, false,4, true, "", true);
        propertyholder.put(4,rr1);
        Property p6 = new Property("Oriental Avenue", 100, false,5, false, "", false, 2);
        propertyholder.put(5,p6);
        Property p7 = new Property("Vermont Avenue", 100, false,6, false, "", false, 2);
        propertyholder.put(6,p7);
        Property p8 = new Property("Conneticut Avenue", 120, false,7, false, "", false, 2);
        propertyholder.put(7,p8);
        Property jail = new Property("Jail", 0, false, 8,true, "", false);
        propertyholder.put(8,jail);
        Property p10 = new Property("St. Charles Place", 140, false,9,false, "", false, 3);
        propertyholder.put(9,p10);
        Property eleccompany = new Property("Electric Company", 150, false,10,true, "", true);
        propertyholder.put(10,eleccompany);
        Property p12 = new Property("States Avenue", 140, false,11, false, "", false, 3);
        propertyholder.put(11,p12);
        Property p13 = new Property("Virginia Avenue", 160, false,12, false, "", false, 3);
        propertyholder.put(12,p13);
        Property  p14 = new Property("Pennsylvania Railroad", 200, false,13, true, "", true);
        propertyholder.put(13,p14);
        Property p15 = new Property("St. James Place", 180, false,14, false, "", false, 4);
        propertyholder.put(14,p15);
        Property p16 = new Property("Tennessee Avenue", 180, false,15, false, "", false, 4);
        propertyholder.put(15,p16);
        Property p17 = new Property("New York Avenue", 200, false,16, false, "", false, 4);
        propertyholder.put(16,p17);
        Property fpark = new Property("Free Parking", 0, false,17, true, "", false);
        propertyholder.put(17,fpark);
        Property p19 = new Property("Kentucky Avenue", 220, false,18, false, "", false, 5);
        propertyholder.put(18,p19);
        Property p20 = new Property("Indiana Avenue", 220, false,19, false, "", false, 5);
        propertyholder.put(19,p20);
        Property p21 = new Property("Illinois Avenue", 240, false,20, false, "", false, 5);
        propertyholder.put(20,p21);
        Property p22 = new Property("B&O Railroad", 200, false,21, true, "", true);
        propertyholder.put(21,p22);
        Property p23 = new Property("Atlantic Avenue", 260, false,22, false, "", false, 6);
        propertyholder.put(22,p23);
        Property p24 = new Property("Ventnor Avenue", 260, false,23, false, "", false, 6);
        propertyholder.put(23,p24);
        Property waterworks = new Property("Water Works", 150, false,24, true, "", true);
        propertyholder.put(24,waterworks);
        Property p26 = new Property("Marvin Gardins", 280, false,25, false, "", false, 6);
        propertyholder.put(25,p26);
        Property goToJail = new Property("Go To Jail", 100, false,26, true, "", false);
        propertyholder.put(26,goToJail);
        Property p28 = new Property("Pacific Avenue", 300, false,27, false, "", false, 7);
        propertyholder.put(27,p28);
        Property p29 = new Property("North Carolina Avenue", 300, false,28, false, "", false, 7);
        propertyholder.put(28,p29);
        Property p30 = new Property("Pennsylvania Avenue", 320, false,29, false, "", false, 7);
        propertyholder.put(29,p30);
        Property  p31 = new Property("Short Line Railroad", 200, false,30, true, "", true);
        propertyholder.put(30,p31);
        Property p32 = new Property("Park Place", 350, false,31, false, "", false, 8);
        propertyholder.put(31,p32);
        Property  p33 = new Property("Luxury Tax", 0, false,32, true, "", false);
        propertyholder.put(32,p33);
        Property p34 = new Property("Boardwalk", 400, false,33, false, "", false, 8);
        propertyholder.put(33,p34);
    }

    public Map<Integer, Property> getProperties(){
        return this.propertyholder;
    }
    public Property getProperty(Integer key){
        return  this.propertyholder.get(key);
    }
    /*

        monopoly.monopoly.Model.Board board = new monopoly.monopoly.Model.Board();
        board.getProperties();
        monopoly.monopoly.Model.Property info1 = board.propertyholder.get(24);
        System.out.println(info1.getName() + "," + info1.getCost() + "," + info1.isOwned() + "," + info1.getPosition() +"," + info1.getRent(info1.getCost()));

    */
}
