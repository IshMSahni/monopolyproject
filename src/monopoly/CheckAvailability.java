package monopoly;

import monopoly.Model.Board;
import monopoly.Model.Property;

import java.util.ArrayList;

public class CheckAvailability {

    public CheckAvailability(){
    }

    public boolean checkSameOwner(Board board, Property property){
        ArrayList<Property> properties = new ArrayList<Property>();
        if(!property.isSpecial()){
            for(int i = property.getPosition() - 3; i < property.getPosition() + 3; i++){
                if(!board.getProperties().get(i).isSpecial()){
                    if(board.getProperties().get(i).getGroupNum() == property.getGroupNum()){
                        properties.add(board.getProperties().get(i));
                    }
                }
            }
            for(int j = 0; j < properties.size(); j++){
                if(properties.get(j).getOwner() != property.getOwner()){
                    return false;
                }
            }
            return true;
        }else return false;
    }



    public boolean checkMultipleHouses(Board board, Property property){
        ArrayList<Property> properties = new ArrayList<Property>();
        if(!property.isSpecial()){
            if(property.getNumHouses() == 0){
                return false;
            }else{
                properties.add(property);
            }
            for(int i = property.getPosition() - 3; i < property.getPosition() + 3; i++){
                if(!board.getProperties().get(i).isSpecial()){
                    if(board.getProperties().get(i).getGroupNum() == property.getGroupNum()){
                        properties.add(board.getProperties().get(i));
                    }
                }
            }
            for(int j = 0; j < properties.size(); j++){
                if(properties.get(j).getNumHouses() != property.getNumHouses()){
                    return false;
                }
            }
            return true;
        }else{
            return false;
        }
    }

    public boolean checkHotel(Board board, Property property){
        if(property.getNumHouses() == 4){
            return true;
        }else return false;
    }
}
