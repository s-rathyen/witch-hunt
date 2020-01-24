// importing color and the random utility
import java.awt.Color;
import java.util.Random;



public class P1 {
	// initializing all variables as static variables that can be accessed
	// by any function.
	
	// initializing the various static image variables
	static EZImage witch;
	static EZImage mouse;
	static EZImage left;
	static EZImage right;
	static EZImage up;
	static EZImage down;
	static EZImage tombstone;
	static EZImage cauldrone;
	static EZImage cauldron1;
	static EZImage cauldron2;
	static EZImage cauldron3;
	
	// initializing various static sound variables
	static EZSound cackle;
	static EZSound moor;
	static EZSound bubbles;
	static EZSound suspense;	
	static EZSound swoosh;
	static EZSound catapult;
	
	// initializing various static text variables
	static EZText popUpText;
	static EZText youDied;
	static EZText youDied2;
	
	static EZText text3;
	static EZText text4;
	static EZText text5;
	static EZText text7;
	static EZText text8;
	static EZText text9;
	static EZText text10;
	static EZText text11;
	static EZText text12;
	static EZText text6;
	
	// initializing various static long variables
	// used to keep track of time
	static long ab;
	static long timeSinceCaught;
	static long timeSinceOOB;
	static long timeSinceTT;
	static long currentTime;
	static long timeLevel1;
	
	// initializing various static integer variables
	static int clickX = 0;
	static int clickY = 0;
	static int mouseMove = 0;
	static int randomX;
	static int randomY;
	static int mouseX = 300;
	static int mouseY = 300;
	static int newIngredientX = 0;
	static int newIngredientY = 0;
	static int caught = 0;
	static int tombstoneX = 0;
	static int tombstoneY = 0;
	static int numberTS = 0;
	static int countTS = 0;
	static int caughtTOT = 1;
	static int xr;
	static int phrase;
	static int ar;
	static int cr;
	static int r = 0;
	static int x;
	static int qr;
	int TSX;
	int TSY;
	
	// initializing miscellaneous static variables
	static Random randomGenerator;
	static Color c = new Color(88,69, 56);
	
	
	// below are the functions i used for my program
		
	// function used to set up the background window size, color, and image
	// the image was created on photoshop with downloaded fonts
	public static void setupBg() {
		EZ.initialize(1200, 700);
		EZ.setBackgroundColor(Color.BLACK);
		EZImage background = EZ.addImage("hunt.png", 600, 350);	
	}
	
	// function used to import images onto the screen
	// in various places
	// imported the mouse, witch, four control buttons,
	// tombstone, and cauldron
	// the tombstone was imported outside of the window
	// so it would be out of view
	public static void setupImages() {
		mouse = EZ.addImage("mouse.png", 600, 100);
		witch = EZ.addImage("witch4.png", 100, 100);
		left = EZ.addImage("circle.png", 980, 280);
		right = EZ.addImage("circle.png", 1080, 280);
		up = EZ.addImage("circle.png", 1030, 230);
		down = EZ.addImage("circle.png", 1030, 330);
		tombstone = EZ.addImage("tombstone.png", -500, -500);
		cauldrone = EZ.addImage("cauldrone.png", 1035, 635);
	}

	// function used to import sounds
	// the background music, moor, was played so that it could
	// be on during gameplay
	public static void setupSounds() {
		cackle = EZ.addSound("cackle.wav");
		moor = EZ.addSound("moor.wav");
		bubbles = EZ.addSound("bubbles.wav");
		suspense = EZ.addSound("suspense.wav");
		swoosh = EZ.addSound("swoosh.wav");
		catapult = EZ.addSound("catapult.wav");
		moor.play();
	}

	// function used to set up the various text on the screen
	// mostly on the right panel
	public static void setupText() {
		text5 = EZ.addText(1035, 390, "TIME ELAPSED: ", c, 15);
		text7 = EZ.addText(1035, 420, "LEVEL 1 / 3", c, 15);
		text8 = EZ.addText(1035, 450, "OBJECTIVE:", c, 12);
		text4 = EZ.addText(1035, 470,  "catch 2 miserable mice to fill up your cauldron" , c, 12);
		text9 = EZ.addText(1035, 490,  "and move on to the next level." , c, 12);
		text10 = EZ.addText(1035, 520,  "BEWARE THE TOMBSTONES." , c, 12);
		text3 = EZ.addText(1035, 550,  caught + " / 2 MICE MASSACRED" , c, 15);
		text11 = EZ.addText(400, 100,  "" , c, 50);
		text12 = EZ.addText(400, 400,  "" , c, 50);
		text6 = EZ.addText(1035, 570 , "TOTAL INGREDIENTS COLLECTED: ", c, 12);
		text3.setFont("pixel font-7.ttf");
		text4.setFont("pixel font-7.ttf");
		text5.setFont("pixel font-7.ttf");
		text7.setFont("pixel font-7.ttf");
		text8.setFont("pixel font-7.ttf");
		text9.setFont("pixel font-7.ttf");
		text10.setFont("pixel font-7.ttf");
		text11.setFont("pixel font-7.ttf");
		text12.setFont("pixel font-7.ttf");
		text6.setFont("pixel font-7.ttf");
		randomGenerator = new Random();
	}
	
	// function that controls the pop up text on the top of the screen
	// each pop up text lasts for one second
	public static void popText() {
		// when you catch the goblin, toad, or mouse,
		// calls the randomPhrase() function to generate a random phrase
		// that will last for one second and then be removed
		if (System.currentTimeMillis() - timeSinceCaught <= 1000 && (xr != 7)) {
			randomPhrase();
		}
		if ((System.currentTimeMillis() - timeSinceCaught >1000) && (xr == 7)){
			removeRandomPhrase();
		} 
		// when the goblin, toad, or mouse go out of bounds
		// this calls the teleportText() function to have "PREY TELEPORTED!"
		// pop up for one second and then be removed
		if ((System.currentTimeMillis() - timeSinceOOB <= 1000) && (ar != 7)) {
			teleportText();
		}
		if ((System.currentTimeMillis() - timeSinceOOB > 1000) && (ar == 7)) {
			removeTeleportText();
		}
		// when each level is completed, this calls the levelText() function
		// to have the phrase "LEVEL UP!" pop up for one second
		// and then be removed
		if ((System.currentTimeMillis() - timeLevel1 <= 1000) && (qr != 7)) {
			levelText();
		}
		if ((System.currentTimeMillis() - timeLevel1 > 1000) && (qr == 7)) {
			removeLevelText();
		}
	}
	
	// function used by another function, popText()
	// to generate a random number between = 0 and 4
	// each random number is assigned to a specific phrase
	// that will be displayed when the player catches the mouse,
	// toad, or goblin
	public static void randomPhrase() {
		EZ.removeEZElement(popUpText);
		popUpText = EZ.addText(400, 60, "", c, 50);
		Random rng;
		rng = new Random();
		phrase = rng.nextInt(5);
		if (phrase == 0) {
			popUpText.setMsg("FIENDISH!");
		} else if (phrase == 1) {
			popUpText.setMsg("WICKED!");
		} else if (phrase == 2) {
			popUpText.setMsg("OTHERWORLDLY!");
		} else if (phrase == 3) {
			popUpText.setMsg("UNGODLY!");
		} else if (phrase == 4) {
			popUpText.setMsg("VILE!");
		}
		xr = 7;
	}
	// function used by another function, popText()
	// removes the random phrase after one second is reached
	public static void removeRandomPhrase() {
		EZ.removeEZElement(popUpText);
		xr ++;
	}
	// function used by another function, popText()
	// causes "PREY TELEPORTED!" to pop up on the screen
	public static void teleportText() {
		EZ.removeEZElement(popUpText);
		popUpText = EZ.addText(400, 60, "", c, 50);
		popUpText.setMsg("PREY TELEPORTED");
		ar = 7;
	}
	// function used by another function, popText()
	// causes "PREY TELEPORTED!" to be removed from the screen
	public static void removeTeleportText() {
		EZ.removeEZElement(popUpText);
		ar++;
	}
	// function used by another function, popText()
	// causes "LEVEL UP!" to appear on the screen
	public static void levelText() {
		EZ.removeEZElement(popUpText);
		popUpText = EZ.addText(400, 60, "", c, 50);
		popUpText.setMsg("LEVEL UP!");
		qr = 7;
	}
	// function used by another function, popText()
	// causes "LEVEL UP!" to be removed from the screen
	public static void removeLevelText() {
		EZ.removeEZElement(popUpText);
		qr++;
	}
	// this function not currently in use
	public static void conjureText() {
		EZ.removeEZElement(popUpText);
		popUpText = EZ.addText(400, 60, "", c, 50);
		popUpText.setMsg("HOSTILE SPIRIT CONJURED");
		cr = 7;
	}
	// this function not currently in use
	public static void removeConjureText() {
		EZ.removeEZElement(popUpText);
		cr++;
	}
	
	// function generates a random number of tombstones
	// from 1-5 and sends them to some random location (tombstoneX, tombstoneY)
	// on the screen
	public static void tsGenerator() {
		numberTS = randomGenerator.nextInt(4) + 1;
		tombstoneX = randomGenerator.nextInt(700);
		tombstoneY = randomGenerator.nextInt(700);
		System.out.println(numberTS);
		
		xr = 0;
		timeSinceCaught = 6000;
	}
	
	// function that makes the game end if the time hits 60 seconds
	// plays a sound and stops the timer
	public static void endTime() {
		if (System.currentTimeMillis() - currentTime >= 60002) {
			suspense.play();
			timeSinceTT = System.currentTimeMillis();
			System.out.println("DELETER!");
			currentTime = 0;
			// places a black rectangle over everything - the illusion of a blank screen
			EZ.addRectangle(EZ.getWindowWidth()/2, EZ.getWindowHeight()/2, EZ.getWindowWidth(), EZ.getWindowWidth(), Color.BLACK, true);
			// red text pops up informing you that time is up
			youDied = EZ.addText(EZ.getWindowWidth()/2, EZ.getWindowHeight()/2, "YOU'RE OUT OF TIME.", Color.RED, 50);
			// total number of ingredients collected displayed
			youDied2 = EZ.addText(EZ.getWindowWidth()/2, EZ.getWindowHeight()/2 + 100, "INGREDIENTS COLLECTED: " + (caughtTOT - 1), Color.RED, 50);
		}
	}
	
	// function that controls the movement of the witch
	public static void controlWitch() {
		// if the left button of the mouse is pressed
		if (EZInteraction.isMouseLeftButtonDown()) {
			// if the cursor is on the 'left' arrow
			if (left.isPointInElement(clickX, clickY)) {
				// witch rotates five degrees left + sound plays
				witch.turnLeft(5);
				swoosh.play();
			}
			// if cursor is on right arrow
			if (right.isPointInElement(clickX, clickY)) {
				// witch rotates five degrees right + sound plays
				witch.turnRight(5);
				swoosh.play();
			}
			// if cursor is on bottom arrow
			if(down.isPointInElement(clickX, clickY)) {
				// witch moves backward 20 px + sound plays
				witch.moveForward(-20);
				swoosh.play();
			}
			// if cursor is on top arrow
			if(up.isPointInElement(clickX, clickY)) {
				// witch moves forward 20 px + sound plays
				witch.moveForward(20);
				swoosh.play();
			}
		}
	}
	
	// function controls the random movement of the mouse, toad, and goblin
	public static void mouseRandomMove() {
		randomX = randomGenerator.nextInt(5 + 1 + 5) - 5;
		randomY = randomGenerator.nextInt(5 + 1 + 5) - 5;
		newIngredientX = randomGenerator.nextInt(700);
		newIngredientY = randomGenerator.nextInt(700);
		mouseX = mouseX + randomX;
		mouseY = mouseY + randomY;
	}
	
	// function controls the mouse, toad, or goblin if they go off the screen
	// where the game is played
	public static void mouseOOB() {
		// sets boundaries for the x and y coordinate
		if(mouseX <= 0 || mouseX >= 800 || mouseY <= 0 || mouseY >= 700) {
			// used to pop up the teleport text
			timeSinceOOB = System.currentTimeMillis();
			// translates the mouse to a random position on the screen
			mouse.translateTo(-100,-100);
			mouseX = newIngredientX;
			mouseY = newIngredientY;
			
		}
	}
	
	// function where most of the important code is 
	public static void mainProgram() {
		// if witch is not touching mouse, mouse continues to move
		// to where the mouseRandomMove() function is putting them
		if (witch.isPointInElement(mouseX, mouseY) == false) {
			mouse.translateTo(mouseX, mouseY);
		// if witch does touch mouse
		} else {
			// sound plays, mouse teleports, variables keep track of the mouse
			// being caught
			text6.setMsg("TOTAL INGREDIENTS COLLECTED: " + caughtTOT);
			// used for the randomPhrase() function
			timeSinceCaught = System.currentTimeMillis();
			// witch cackles
			cackle.play();
			mouse.translateTo(-100, -100);
			mouseX = newIngredientX;
			mouseY = newIngredientY;
			caught++;
			caughtTOT ++;
			// CONTROLS LEVEL 1 (catch 2 mice)
			if (caught < 2 && r == 0) {
				text3.setMsg(caught + " / 2 MICE MASSACRED");
			} else if (caught >= 2 && r == 0) {
				// captures time for the popText() function
				timeLevel1 = System.currentTimeMillis();
				// bubbling sound
				bubbles.play();
				text3.setMsg("2 / 2 MICE MASSACRED!");
				// cauldron appears to fill up by replacing the previous picture
				EZ.removeEZElement(cauldrone);
				cauldron1 = EZ.addImage("cauldron1.png", 1035, 635);
				caught = 0;
				countTS = 0;
				r = 50;
				mouse = EZ.addImage("toad.png", 600, 200);
				text7.setMsg("LEVEL 2 / 3");
				text4.setMsg("catch 2 tricky toads to fill up your cauldron");
				text3.setMsg(caught + " / 2 TOADS TRANSFORMED");		
			}
			// CONTROLS LEVEL 2 (catch 2 toads)
			if (caught < 2 && r == 50) {
				text3.setMsg(caught + " / 2 TOADS TRANSFORMED");
			} else if (caught >= 2 && r == 50) {
				timeLevel1 = System.currentTimeMillis();
				bubbles.play();
				text3.setMsg(caught  + " / 2 TOADS TRANSFORMED");
				EZ.removeEZElement(cauldron1);
				cauldron2 = EZ.addImage("cauldron2.png", 1035, 635);
				EZ.removeEZElement(mouse);
				System.out.println("You win!");
				caught = 0;
				countTS = 0;
				r = 75;
				mouse = EZ.addImage("goblin.png", 600, 200);	
				text7.setMsg("LEVEL 3 / 3");
				text4.setMsg("catch 3 goblins to win the game");
				text3.setMsg(caught + " / 3 GOBLINS CAUGHT");
			}
			// CONTROLS LEVEL 3 (catch 3 goblins)
			if (caught < 3 && r == 75) {
				text3.setMsg(caught + " / 3 GOBLINS CAUGHT");
			} else if (caught >= 1 && r == 75) {
				timeLevel1 = System.currentTimeMillis();
				bubbles.play();
				text3.setMsg(caught + " / 3 GOBLINS CAUGHT");
				EZ.removeEZElement(cauldron2);
				System.out.println(r);
				cauldron3 = EZ.addImage("cauldron3.png", 1035, 635);
				r = 100;
				caught =0;
				System.out.println(r);
			} 
			// IF ALL LEVELS ARE PASSED,
			// a screen pops up informing you that the game is won
			// and the timer stops
			if (r == 100) {
				suspense.play();
				timeSinceTT = System.currentTimeMillis();
				System.out.println("DELETER!");
				currentTime = 0;
				EZ.removeAllEZElements();
				youDied = EZ.addText(EZ.getWindowWidth()/2, EZ.getWindowHeight()/2, "YOU WIN! YOU'RE A WICKED WITCH.", Color.RED, 50);
				youDied2 = EZ.addText(EZ.getWindowWidth()/2, EZ.getWindowHeight()/2 + 100, "INGREDIENTS COLLECTED: " + (caughtTOT - 1), Color.RED, 50);
			}	
		}	
	}
	
	
	// MAIN FUNCTION
	public static void main(String[] args) {
		// capture time when window is opened in a variable currentTime
		currentTime = System.currentTimeMillis();
		// calling five functions to set up gameplay
		setupBg();
		setupImages();
		setupSounds();
		setupText();
		tsGenerator();
		
		// an array used to keep track of the x, y coordinates of tombstones generated
		// (i wasn't quite sure how to use arrays so i apologize if the code doesn't make
		// much sense)
		// copied Books.java
		P1[] numberTombstones = new P1[5]; 
		numberTombstones[0] = new P1();
		numberTombstones[1] = new P1();
		numberTombstones[2] = new P1();
		numberTombstones[3] = new P1();
		numberTombstones[4] = new P1();
		numberTombstones[0].TSX = 0;
		numberTombstones[1].TSX = 0;
		numberTombstones[2].TSX = 0;
		numberTombstones[3].TSX = 0;
		numberTombstones[4].TSX = 0;
		numberTombstones[0].TSY = 0;
		numberTombstones[1].TSY = 0;
		numberTombstones[2].TSY = 0;
		numberTombstones[3].TSY = 0;
		numberTombstones[4].TSY = 0;
		
		// while loop that causes the game to run
		while (System.currentTimeMillis() - currentTime < 70002) {
			// get the x and y coordinates of the mouse in clickX and clickY
			clickX = EZInteraction.getXMouse();
			clickY = EZInteraction.getYMouse();	
			// call two functions 
			// endTime() will end the game at 60 seconds
			endTime();
			// popText() will control the text on the top of the screen
			// that pops up for a second at a time
			popText();
			// timer on the right panel 
			// counts down from 60 to 0
			text5.setMsg("TIME REMAINING: " + (60 - (System.currentTimeMillis() - currentTime)/1000));
			x = 0;  
			// generates tombstones at random places on the screen
			// and stores their x, y coordinates in tombstoneX and tombstoneY
			while (countTS < numberTS) {
				System.out.println("Total TS generated: " + numberTS);
				EZ.addImage("tombstone.png", tombstoneX, tombstoneY);
				numberTombstones[x].TSX = tombstoneX;
				numberTombstones[x].TSY = tombstoneY;
				System.out.println("Tombstones generated: " + (countTS + 1) );	
				System.out.println("(" + numberTombstones[x].TSX + " , " + numberTombstones[x].TSY + ")");
				tombstoneX = randomGenerator.nextInt(840);
				tombstoneY = randomGenerator.nextInt(700);
				countTS ++;
				x = x + 1;
			}
			// if witch touches any of those coordinates
			// that is, touches any of the tombstones, the game ends
			// since there are up to five tombstones generated,
			// i used five if loops for numberTombstone[0] - numberTombstone[5]
			if (witch.isPointInElement(numberTombstones[0].TSX, numberTombstones[0].TSY)) {
				suspense.play();
				timeSinceTT = System.currentTimeMillis();
				System.out.println("DELETER!");
				currentTime = 0;
				EZ.removeAllEZElements();
				youDied = EZ.addText(EZ.getWindowWidth()/2, EZ.getWindowHeight()/2, "YOU DIED! WATCH OUT FOR THE TOMBSTONES.", Color.RED, 50);
				youDied2 = EZ.addText(EZ.getWindowWidth()/2, EZ.getWindowHeight()/2 + 100, "INGREDIENTS COLLECTED: " + (caughtTOT - 1), Color.RED, 50);
				
			} 
			if (witch.isPointInElement(numberTombstones[1].TSX, numberTombstones[1].TSY)) {
				suspense.play();
				timeSinceTT = System.currentTimeMillis();
				System.out.println("DELETER!");
				currentTime = 0;
				EZ.removeAllEZElements();
				youDied = EZ.addText(EZ.getWindowWidth()/2, EZ.getWindowHeight()/2, "YOU DIED! YOU'RE ONE UNLUCKY WITCH.", Color.RED, 50);
				youDied2 = EZ.addText(EZ.getWindowWidth()/2, EZ.getWindowHeight()/2 + 100, "INGREDIENTS COLLECTED: " + (caughtTOT - 1), Color.RED, 50);
			}
			if (witch.isPointInElement(numberTombstones[2].TSX, numberTombstones[2].TSY)) {
				suspense.play();
				timeSinceTT = System.currentTimeMillis();
				System.out.println("DELETER!");
				currentTime = 0;
				EZ.removeAllEZElements();
				youDied = EZ.addText(EZ.getWindowWidth()/2, EZ.getWindowHeight()/2, "YOU'RE DEAD. FIDDLESTICKS!", Color.RED, 50);
				youDied2 = EZ.addText(EZ.getWindowWidth()/2, EZ.getWindowHeight()/2 + 100, "INGREDIENTS COLLECTED: " + (caughtTOT - 1), Color.RED, 50);
			}
			if (witch.isPointInElement(numberTombstones[3].TSX, numberTombstones[3].TSY)) {
				suspense.play();
				timeSinceTT = System.currentTimeMillis();
				System.out.println("DELETER!");
				currentTime = 0;
				EZ.removeAllEZElements();
				youDied = EZ.addText(EZ.getWindowWidth()/2, EZ.getWindowHeight()/2, "YOU'RE DEAD. BETTER LUCK NEXT TIME!", Color.RED, 50);
				youDied2 = EZ.addText(EZ.getWindowWidth()/2, EZ.getWindowHeight()/2 + 100, "INGREDIENTS COLLECTED: " + (caughtTOT - 1), Color.RED, 50);
			}
			if (witch.isPointInElement(numberTombstones[4].TSX, numberTombstones[4].TSY)) {
				suspense.play();
				timeSinceTT = System.currentTimeMillis();
				System.out.println("DELETER!");
				currentTime = 0;
				EZ.removeAllEZElements();
				youDied = EZ.addText(EZ.getWindowWidth()/2, EZ.getWindowHeight()/2, "YOU'RE DEAD. HOW DISAPPOINTING.", Color.RED, 50);
				youDied2 = EZ.addText(EZ.getWindowWidth()/2, EZ.getWindowHeight()/2 + 100, "INGREDIENTS COLLECTED: " + (caughtTOT - 1), Color.RED, 50);
			}
			// calls four functions previously explained to run
			controlWitch();
			mouseRandomMove();
			mouseOOB();
			mainProgram();
			// refreshes screen so graphics refresh
			EZ.refreshScreen();
		}
	}
}	