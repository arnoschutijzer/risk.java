*[Forking this repository] (https://github.com/gitloaf/RiskProject/blob/master/README.md#fork)*

Risk patchnotes
====
      NOTE: RiskProject is now fully functioning.
      RiskProject will only be updated for performance enhancements and 
      uncaught bugfixes.


RiskProject 1.1
---
Deleted Status.java Enum

RiskProject 1.0
---
Complete patch of every bug found
Created Javadoc
Added Javadoc to every class

Updated DomainController.java
- deleted getContinentNumbr method

Updated Game.java
- patched attack method

Deleted Status.java
- deprecated

Updated UserRepository.java
- patched clearUsers to now set userCount to zero

Updated GameGUI.java
- patched "attack" actionCommand
- externalized all exceptions

Updated all .properties files
- added InsufficientAttArmy
- added InsufficientDefArmy
- added endTurnUnassignedArmyException + patched
- added between1And3Exception
- added between1And2Exception
- added insufficientDefArmy
- added noValueException
- added noNumericValue
- added attDice
- added defDice
- added chooseCountriesDice
- added winner
- added quit

Deleted GameMapper.java
- deprecated

Deleted TestUI.java
- deprecated

Created image
- created winnar.png

Created WinnerGUI.java
- created WinnerGUI.java + created patches

Added all UML diagrams





RiskProject 0.96
---
Updated db
- changed Frans-Guyana to Kiribati in database

Updated infantry.png
- resized

Updated DomainController.java
- added addArmy method

Updated Game.java
- added addArmy method
- patched exchangeCards method

Updated Land.java
- added addArmy method

Updated User.java
- added symbolCards in constructor for testing
(should be removed before finalizing)

Updated GameSquareRepository.java
- deleted unnecessary whitespace
- added addArmy method

Updated and patched ExchangeGUI.java
- using enhanced for-loop to construct buttons
- deleted gaps
- updated actionPerformed method to function correctly
(now closes when pressed "confirm")

Updated GameGUI.java
- imported Status enum
- changed catch-block surrounding paintMap call to externalize error message
- added method calcCurrentUser
- changed lblUserTurn and lblArmiesToPlace to show information
- changed Seq and Par groups to implement lblUserTurn and lblArmiesToPlace
- patched refreshAttDefPanels method
- updated mouseReleased method to force user to place armies on countries
- updated endTurn in switch to update lblUserTurn

Deleted NewJFrame.java
- deprecated

Updated resource bundles
- all .properties files now include lblUserTurn- and lblArmiesToPlace's translation

Added Status.java (enum)






RiskProject 0.95
---
Updated all files
- added @author

Updated DomainController.java
- changed getUsers method to return a java.util.List containing the Users
- added method gameSquareSymbolStringArray

Updated Game.java
- changed getUsers method  to return a java.util.List containing the Users
- changed parameter of IllegalArgumentException with String
- changed parameter of IllegalGameSquareException with String
- changed parameter of InsufficientArmy
- updated exchangeCards to handle List of Strings
- added gameSquareSymbolStringArray

Updated Symbol.java
- now uses Strings as identifier for symbols

Updated GameSquareRepository.java
- added gameSquareSymbolStringArray method

Updated ExchangeGUI.java
- implemented JFrame
- added ImageIcons
- added button to confirm
- implemented ActionListener
- implemented Try-Catch blocks 

Updated GameGUI.java
- deleted unused imports
- enhanced readability of fields
- added field currentUser
- added calculation to calculate currentUser
- added drawSymbols
- added refreshAttDefPanels to re-init Att- and Def-panels
- updated MouseListener to cleaner code
- updated actionPerformed
- added JOptionPane to notice user he has conquered a country
- implemented call exchangeGUI
- implemented Try-Catch blocks

Updated Resource bundles
- added select to each .properties file
- added confirm to each .properties file
- added IllegalGameSquareException to each .properties file
- added InsufficientArmy to each .properties file
- added noCorrectCombinationException to each .properties file

Added new images
- added aod.png
- added bass.png
- added defqon.png
- added hazardous.png
- added qlimax.png
- added yinyang.png

NewJFrame.Java deprecated?






RiskProject 0.91
---
Updated classpath to include external libraries and jdbc drivers

Updated DB
- Incorrect GameSquare fixed
- Map Changed

Updated ExchangeGUI.java
- Set visible to true

Updated GameGUI.java
- added indicator which country has which owner
- patched mouseClicked method as this was not functioning correctly
- added actionPerformed method

Updated Resource bundles
- deleted Frans-Guyana from each .properties file
- added Kiribati to each .properties file
- added translation for army, exchangeCard, endTurn, attacker, defender, continent






RiskProject 0.88
---
updated GameGUI.java
- implemented ActionListener + appropriate imports as requested
- added btnAttack, btnExchange, btnEndTurn
- added method paintButton(String text) to create new buttons for panel
- changed dimensions to 210*210 to fit panels
- implemented ActionPerformed to open new ExchangeGUI()

created ExchangeGUI.java
- will contain GUI to exchange cards (not implemented yet)

- added cannon.png
- added horse.png
- added infantry.png






RiskProject 0.87
---
updated GameGUI.java
- mouseListener fully functional as of now

updated DomainController
- added method getGame()
- added method getGameSquareRepository()
- added getUser(int index)
- added getGameSquare(int x, int y)
- added getArmy(int x, int y)
- added getContinentNumber(int x, int y)

updated Game.java
- added getGameSquare(int x, int y)
- added GetUser(int index)
- added getGameSquareRepository()

updated Land.java
- added toString()

updated User.java
- added toString()

updated Water.java
- deleted unused import: java.awt.Color

updated GameSquareRepository.java
- added getArmy(int x, int y)
- added getContinentNumber(int x, int y)

updated UserRepository.java
- added getUser(int index)






RiskProject 0.8
---
- GameGUI.java updated
-additional panel added for extra info
-scrolling over a GameSquare is now correctly shown
-code optimization






RiskProject v0.75
---
updated Game.java
-implemented calcArmy method

updated GameSquare.java
-implemented getUser method

updated Symbol.java
-symbols are now pulled out of SQLite db

updated GameSquareRepository
-implemented getDIMX method
-implemented getDIMY method
-implemented getUser method

Updated Land.java
-deleted Color var and changed to String var
conversion to Color obj will happen at later time

Updated User.java
-added var to contain int of unassigned army

Created GameGUI.java
-created code to hold the game






RiskProject v0.5
---
- added another worldmap

- updated MapRepository.java with method getMapNames
- updated UserRepository.java, deleted unused import
- updated Game.java with methods attack and exchangeCards
- updated DomainController.java
- updated InitGUI.java (optimizations)
- updated DB with another worldmap

- TestUI.java changed
- InitialisationJigloo.java deprecated: deleted as of now






RiskProject v0.43
---
- updated User.java to construct new exception with a name instead of
default construct a new exception
- updated messages_en/messages_fr/messages_nl to include translation of
exceptions
- updated InitGUI.java to catch IllegalArgumentException if username's
length is shorter than 2






RiskProject v0.35
---
- updated InitGUI
- updated UserAlreadExistsException to include error message when default
constructor is called
- updated messages_fr/messages_en/messages_nl to include:
- maptranslation, InitGUItranslation, titleTranslation
- updated Startup.java to construct a new LanguageGUI.java which then
- constructs InitGUI.java

- changed params of LanguageGUI.java and InitGUI.java constructors to
include DomainController to call startGame(String) with given String

deprecated:
- InitialisationJigloo.java: temp. included for look-up purposes
- ApplicationController.java: already deleted






RiskProject v0.3 Patch
---
- implemented initGUI fix

- fixed NullExceptionPointer
- fixed prinstacktraces not working well
- dutch countries added to resource bundles






RiskProject v0.3
---
- InitialisationJigloo optimized
- InitGUI implemented

- InitialisationGroupLayouts deprecated
- RiskApp deprecated


---

Fork
=
Feel free to fork this repository and open pull requests.
Be sure to add a comment to your pull request.
Other pull requests will be denied.

Fork the repo and clone it to your local repository. Import the repo into your workspace.
Or download the .zip file, extract it, and import it in your workspace.
