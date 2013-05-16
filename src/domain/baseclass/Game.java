package domain.baseclass;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import domain.repositories.*;
import exceptions.IllegalGameSquareException;
import exceptions.InsufficientArmy;
import exceptions.UserAlreadyExistsException;
/**
 * Class delegates everything between the DomainController and the other classes.
 * @author Thijs van der Burgt
 * @author Arno Schutijzer
 *
 */
public class Game {

	private String gameName;
	private UserRepository userRepository;
	private GameSquareRepository map;
	private Random objRandom = new Random();
	private final int DICESIDES = 6;
	private List<Integer> gameSquaresPerUser = new ArrayList<>();
	/**
	 * Default constructor for Game.
	 * @param name
	 */
	public Game(String name) {
		this.gameName = name;
		userRepository = new UserRepository();
		map = new GameSquareRepository();
	}

	public GameSquare getGameSquare(int x, int y) {
		return map.getGameSquare(x, y);
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @return GameSquare of the instance Land or null
	 * @see domain.baseclass.Land
	 */
	public GameSquare getLand(int x, int y) {
		if(map.getGameSquare(x, y) instanceof Land)
			return map.getGameSquare(x, y);
		else
			return null;
	}

	public String getGameName() {
		return this.gameName;
	}

	public List<User> getUsers() {
		return userRepository.getUsers();
	}

	public User getUser(int index) {
		return userRepository.getUser(index);
	}
	/**
	 * Calls the method createUser from the userRepository.
	 * @param username
	 * @throws UserAlreadyExistsException
	 * @see domain.repostories.UserRepository
	 */
	public void createUser(String username) throws UserAlreadyExistsException {
		userRepository.createUser(username);
	}
	/**
	 * Calls the method clearUsers from the userRepository.
	 * @see domain.repositories.UserRepository
	 */
	public void clearUsers() {
		userRepository.clearUsers();
	}
	/**
	 * Calls the method generateMap from GameSquareRepository
	 * @param mapNr
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see domain.repositories.GameSquareRepository
	 */
	public void generateMap(int mapNr) throws ClassNotFoundException,
			SQLException {
		map.generateMap(mapNr);
	}
	/**
	 * 
	 * @return String from the toString function of GameSquareRepository
	 * @see domain.repositories.GameSquareRepository
	 */
	public String displayMap() {
		return map.toString();
	}
	/**
	 * Adds 1 army to a specified GameSquare of the instance Land.
	 * @param x
	 * @param y
	 */
	public void addArmy(int x, int y) {
		map.addArmy(x, y);
	}

	public String[][] gameSquareColorsStringArray() {
		return map.gameSquareColorsStringArray();
	}

	/**
	 * Method assigns the Land objects from the selected map between the created users.
	 */
	public void divideMap() {
		
		Random objRandom = new Random();
		List<User> users = userRepository.getUsers();
		int userAmount = users.size(), landAmount = 0, a = 0;

		for (@SuppressWarnings("unused")
		User u : users) {
			gameSquaresPerUser.add(0);
		}

		for (GameSquare[] gsr : map.getMap()) {
			for (GameSquare gs : gsr) {
				if (gs instanceof Land)
					landAmount++;
			}
		}

		for (int i = 0; i < landAmount; i++) {
			gameSquaresPerUser.set(a, gameSquaresPerUser.get(a) + 1);
			a++;
			if (a == userAmount)
				a = 0;
		}

		int rand;
		boolean succes = false;
		for (int x = 0; x < map.getMap().length; x++) {
			for (int y = 0; y < map.getMap()[x].length; y++) {
				if (map.getGameSquare(x, y) instanceof Land) {
					succes = false;
					while (succes == false) {
						rand = objRandom.nextInt(userAmount);
						if (gameSquaresPerUser.get(rand) != 0) {
							map.setUser(users.get(rand), x, y);
							gameSquaresPerUser.set(rand,
									gameSquaresPerUser.get(rand) - 1);
							succes = true;
						}
					}
				}
			}
		}
	}
	/**
	 * Method simulates a battle between two objects of Land.
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
	public boolean attack(int attX, int attY, int defX, int defY, int attDice,
			int defDice) throws ClassCastException, IllegalGameSquareException,
			InsufficientArmy {
		if (attDice > 3 || defDice > 2 || attDice < 1 || defDice < 1) {
			throw new IllegalArgumentException("codingError");
		}
		if (Math.abs((attX + attY) - (defX + defY)) != 1 && Math.abs((attX+attY) - (defX+defY)) != 9 || Math.abs(attY-defY) == 2 || Math.abs(attX-defX) == 2){
			throw new IllegalGameSquareException("IllegalGameSquareException");
		} else {
			Land attacker, defender;
			attacker = (Land) map.getGameSquare(attX, attY);
			defender = (Land) map.getGameSquare(defX, defY);

			int attArmy = attacker.getArmy();
			int defArmy = defender.getArmy();

			if (attArmy == 0 || attArmy == 1 || attArmy <= attDice) {
				throw new InsufficientArmy("InsufficientAttArmy");
			}else if(defArmy < defDice){
				throw new InsufficientArmy("InsufficientDefArmy");
			} else {
				Integer[] attPoints = new Integer[attDice];
				Integer[] defPoints = new Integer[defDice];

				for (int i = 0; i < attDice; i++) {
					attPoints[i] = objRandom.nextInt(DICESIDES);
				}
				for (int i = 0; i < defDice; i++) {
					defPoints[i] = objRandom.nextInt(DICESIDES);
				}

				Arrays.sort(attPoints, Collections.reverseOrder());
				Arrays.sort(defPoints, Collections.reverseOrder());

				int a = 0;
				if(attDice > defDice){
					a = defDice;
				}else{
					a = attDice;
				}
				
				for(int i = 0 ; i < a; i++){
					if(attPoints[i] > defPoints[i]){
						defArmy--;
					}else{
						attArmy--;
					}
				}
				// pass by reference
				attacker.setArmy(attArmy);
				defender.setArmy(defArmy);

				if (defArmy == 0) {
					defender.setUser(attacker.getUser());

					defender.setArmy(attacker.getArmy() - 1);
					attacker.setArmy(1);
					return true;
				}

				System.out.println(attacker.getName() + " "
						+ ((Land) map.getGameSquare(attX, attY)).getArmy()
						+ " " + defender.getName() + " "
						+ ((Land) map.getGameSquare(defX, defY)).getArmy()
						+ " ");

				return false;
			}
		}
	}
	/**
	 * 
	 * @param cards
	 * @return Amount of armies that can be addittionally placed.
	 */
	public int exchangeCards(List<String> cards) {

		String type = " ";
		int ret = 0;

		if (cards.contains("cannon") && cards.contains("horse")
				&& cards.contains("infantry"))
			return 10;

		if (cards.get(0).equals(cards.get(1))
				&& cards.get(1).equals(cards.get(2)))
			type = cards.get(0);

		switch (type) {
		case "cannon":
			ret = 4;
			break;

		case "infantry":
			ret = 6;
			break;

		case "horse":
			ret = 8;
			break;

		default:
			ret = 0;
			break;
		}

		return ret;
	}
	/**
	 * 
	 * @param user
	 * @return Amount of armies a user should receive based on his conquered Lands.
	 */
	public int calcArmy(User user) {
		int count = 0, army = 0;

		for (int i = 0; i < map.getDIMY(); i++) {
			for (int j = 0; j < map.getDIMX(); j++) {
				if (map.getGameSquare(i, j) instanceof Land
						&& map.getUser(i, j) == user)
					count++;
			}
		}

		army = count/3;
		
		if (army < 3)
			return 3;
		else
			return army;
	}
	/**
	 * 
	 * @return indexnumber of the user that has the first turn.
	 */
	public int calcTurn() {
		int ret = 0, amount = gameSquaresPerUser.get(0);

		for (int i = 0; i < gameSquaresPerUser.size(); i++)
			if (gameSquaresPerUser.get(i) > amount)
				ret = i;

		return ret;
	}

	public GameSquareRepository getGameSquareRepository() {
		return this.map;
	}

	public String[][] gameSquareSymbolStringArray() {
		return map.gameSquareSymbolStringArray();
	}

}