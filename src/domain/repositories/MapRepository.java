package domain.repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.baseclass.Map;

import persistance.PersistanceController;

public class MapRepository {
	
	private List<Map> maps;
	
	public MapRepository() throws ClassNotFoundException, SQLException{
		maps = new ArrayList<>();
		maps = PersistanceController.getInstance().getMaps();
	}
	
	public int getNumOfMaps(){
		return maps.size();
	}
	
	public Map getMap(int index){
		return maps.get(index);
	}
	/**
	 * 
	 * @return String array with the names of the instances of Map
	 * @see domain.baseclass.Map
	 */
	public String[] getMapNames(){
		int mapSize = maps.size();
		String[] mapNames = new String[mapSize];
		for(int i = 0; i<mapSize; i++){
			mapNames[i] = maps.get(i).getName();
		}
		return mapNames;
	}
}
