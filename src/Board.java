import java.util.*;

public class Board {
    //propertyholder is a map of the whole board
    public HashMap<Integer, Property> propertyholder;
    //private final Integer[] boardspaces = new Integer[25];
    //might want to use an array later instead of using integers directly in the map
    public Board (){
        propertyholder = new HashMap<>();
        Property go = new Property("Go", 0, false, 0, true, "");
        propertyholder.put(0,go);
        Property p2 = new Property("Mediterranean Avenue", 60, false,1, false, "");
        propertyholder.put(1,p2);
        Property p3 = new Property("Baltic", 60, false,2, false, "");
        propertyholder.put(2,p3);
        Property p4 = new Property("Oriental Avenue", 100, false,3, false, "");
        propertyholder.put(3,p4);
        Property p5 = new Property("Vermont Avenue", 100, false,4, false, "");
        propertyholder.put(4,p5);
        Property p6 = new Property("Conneticut Avenue", 120, false,5, false, "");
        propertyholder.put(5,p6);
        Property jail = new Property("Jail", 0, false, 6,true, "");
        propertyholder.put(6,jail);
        Property p8 = new Property("St. Charles Place", 140, false,7,false, "");
        propertyholder.put(7,p8);
        Property p9 = new Property("States Avenue", 140, false,8, false, "");
        propertyholder.put(8,p9);
        Property p10 = new Property("Virginia Avenue", 160, false,9, false, "");
        propertyholder.put(9,p10);
        Property p11 = new Property("St. James Place", 180, false,10, false, "");
        propertyholder.put(10,p11);
        Property p12 = new Property("Tennessee Avenue", 180, false,11, false, "");
        propertyholder.put(11,p12);
        Property p13 = new Property("New York Avenue", 200, false,12, false, "");
        propertyholder.put(12,p13);
        Property fpark = new Property("Free Parking", 0, false,13, true, "");
        propertyholder.put(13,fpark);
        Property p15 = new Property("Kentucky Avenue", 220, false,14, false, "");
        propertyholder.put(14,p15);
        Property p16 = new Property("Indiana Avenue", 220, false,15, false, "");
        propertyholder.put(15,p16);
        Property p17 = new Property("Illinois Avenue", 240, false,16, false, "");
        propertyholder.put(16,p17);
        Property p18 = new Property("Atlantic Avenue", 260, false,17, false, "");
        propertyholder.put(17,p18);
        Property p19 = new Property("Ventnor Avenue", 260, false,18, false, "");
        propertyholder.put(18,p19);
        Property p20 = new Property("Marvin Gardins", 280, false,19, false, "");
        propertyholder.put(19,p20);
        Property goToJail = new Property("Go To Jail", 100, false,20, true, "");
        propertyholder.put(20,goToJail);
        Property p22 = new Property("Pacific Avenue", 300, false,21, false, "");
        propertyholder.put(21,p22);
        Property p23 = new Property("North Carolina Avenue", 300, false,22, false, "");
        propertyholder.put(22,p23);
        Property p24 = new Property("Pennsylvania Avenue", 320, false,23, false, "");
        propertyholder.put(23,p24);
        Property p25 = new Property("Park Place", 350, false,24, false, "");
        propertyholder.put(24,p25);
        Property p26 = new Property("Boardwalk", 400, false,25, false, "");
        propertyholder.put(25,p26);
    }

    public Map<Integer, Property> getProperties(){
        return this.propertyholder;
    }

    public void newOwner(int pos,String n){
        propertyholder.get(pos).setOwner(n);
    }

    public static void main(String[] args) {
        Board board = new Board();
        board.getProperties();
        Property info1 = board.propertyholder.get(24);
        System.out.println(info1.getName() + "," + info1.getCost() + "," + info1.isOwned() + "," + info1.getPosition() +"," + info1.getRent(info1.getCost()));

    }
}
