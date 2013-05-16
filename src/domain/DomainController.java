package domain;

import java.sql.SQLException;
import java.util.List;

import domain.baseclass.Game;
import domain.baseclass.GameSquare;
import domain.baseclass.Land;
import domain.baseclass.Map;
import domain.baseclass.User;
import domain.repositories.GameSquareRepository;
import domain.repositories.MapRepository;
import exceptions.IllegalGameSquareException;
import exceptions.InsufficientArmy;
import exceptions.UserAlreadyExistsException;
/**
 * Delegates everything between the domain layer and the other layers.
 * @author Thijs van der Burgt
 * @author Arno Schutijzer
 *
 */
public class DomainController {
	private Game game;
	private MapRepository mapRepository;
	/**
	 * Default constructor initialises the attribute mapRepository.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public DomainController() throws ClassNotFoundException, SQLException{
		mapRepository = new MapRepository();
	}
	/**
	 * Starts a new game.
	 * @param name
	 */
	public void startGame(String name){
		game = new Game(name);
	}
	
	public String getGameName(){
		return game.getGameName();
	}
	
	public GameSquareRepository getGameSquareRepository(){
		return game.getGameSquareRepository();
	}
	
	public Game getGame(){
		return this.game;
	}
	/**
	 * Creates a new user.
	 * @param string
	 * @throws UserAlreadyExistsException
	 * @see domain.baseclass.Game
	 */
	public void createUser(String string) throws UserAlreadyExistsException {
		game.createUser(string);
	}

	public List<User> getUsers() {
		return game.getUsers();
	}
	
	public User getUser(int index) {
		return game.getUser(index);
	}
	
	public User getUser(int x, int y){
		return game.getGameSquareRepository().getUser(x, y);
	}
	/**
	 * Generates a specified map.
	 * 
	 * @param i
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see domain.baseclass.Game
	 */
	public void generateMap(int i) throws ClassNotFoundException, SQLException {
		game.generateMap(i);
	}
	/**
	 * Calls the attack method for game
	 * 
	 * @see domain.baseclass.Game
	 * @param attX
	 * @param attY
	 * @param defX
	 * @param defY
	 * @param attDice
	 * @param defDice
	 * @return
	 * @throws ClassCastException
	 * @throws IllegalGameSquareException
	 * @throws InsufficientArmy
	 */
	public boolean attack(int attX, int attY, int defX, int defY, int attDice, int defDice) throws ClassCastException, IllegalGameSquareException, InsufficientArmy{
		return game.attack(attX, attY, defX, defY, attDice, defDice);
	}
	
	/**
	 * Calls the method divideMap from Game
	 * @see domain.baseclass.Game
	 */
	public void divideMap() {
		game.divideMap();
	}
	/**
	 * Returns the value from the method displayMap from Game
	 * @see domain.baseclass.Game
	 */
	public String displayMap() {
		return game.displayMap();
	}
	/**
	 * Returns the value from the method gameSquareSymbolSringArray from Game
	 * @see domain.baseclass.Game
	 */
	public String[][] gameSquareSymbolStringArray(){
		return game.gameSquareSymbolStringArray();
	}
	/**
	 * Returns the value from the method gameSquareColorsStringArray from Game
	 * @see domain.baseclass.Game
	 */
	public String[][] gameSquareColorsStringArray(){
		return game.gameSquareColorsStringArray();
	}
	/**
	 * 
	 * @return The amount of maps in mapRepository
	 */
	public int getNumOfMaps(){
		return mapRepository.getNumOfMaps();
	}
	/**
	 * 
	 * @param index
	 * @return A specified map from mapRepostiory
	 */
	public Map getMap(int index){
		return mapRepository.getMap(index);
	}
	/**
	 * Calls the clearUsers method from Game
	 * @see domain.baseclass.Game
	 */
	public void clearUsers(){
		game.clearUsers();
	}
	
	public String[] getMapNames(){
		return mapRepository.getMapNames();
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @return A specified Land object
	 */
	public GameSquare getLand(int x, int y){
		return game.getLand(x, y);
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @return A specified GameSquare obje=ct
	 */
	public GameSquare getGameSquare(int x, int y){
		return game.getGameSquare(x, y);
	}
	
	public int getArmy(int x, int y){
		return ((Land) game.getGameSquare(x, y)).getArmy();
	}
	/**
	 * Calls the method addArmy from Game
	 * @param x
	 * @param y
	 * @see domain.baseclass.Game
	 */
	public void addArmy(int x, int y){
		game.addArmy(x, y);
	}
	/**
	 * 
	 * @param user
	 * @return The amount of armies a user should receive.
	 */
	public int calcArmy(User user){
		return game.calcArmy(user);
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @return The value of the continentNumber attribute from Land
	 */
	public int getContinentNumber(int x, int y){
		if(game.getGameSquare(x, y) instanceof Land)
			return ((Land) game.getGameSquare(x, y)).getContinentNumber();
		else
			return -1;
	}
	/**
	 * 
	 * @return The indexnumber of the User to play
	 */
	public int calcTurn(){
		return game.calcTurn();
	}
}
