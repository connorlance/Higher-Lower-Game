// Programmer: Connor Lance
// Date: 3/20/21
//Description: Higher or lower game that allows user to guess number 1-100. Can quit at any time. Can replay after correct answer. Displays correct number and number of guesses.

package HighLowGame;

import java.util.Scanner;
import java.util.Random;

public class HighLow {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();

		int rand_number = 0;
		int min = 1;
		int max = 100;
		int guess = 0;
		int guess_counter = 0;
		int message_counter = 0;

		String input_play;
		String input_guess;
		String input_replay;
		String[] message = { "Higher or lower game. Type play to start.",
							 "Type play to start the higher or lower game.",
							 "You need to type play to start the higher or lower game.", 
							 "You must type play to start the game",
							 "Seriously, just type play, it is not that hard.",
							 "IF YOU DONT TYPE PLAY IM JUST GOING TO KEEP ASKING YOU TO TYPE PLAY OVER AND OVER AGAIN." };
		
		boolean playMultiple = false;
		boolean play = false;
		boolean first_guess = false;
		boolean playing = false;
		boolean correct = false;
		boolean quit_early = false;

		while (play == false) {
			System.out.println(message[message_counter]);
			input_play = scan.nextLine();
			if (input_play.equals("play")) {
				playMultiple = true;
				play = true;
				first_guess = true;
			} 
			else
				message_counter++;
			if (message_counter == 6) {
				message_counter = 0;
			}
		}
		while (playMultiple) {
			while (play) {
				guess = 0;
				guess_counter = 0;
				rand_number = rand.nextInt((max - min) + 1) + min;

				while (first_guess) {
					System.out.println("Guess a number between 1 and 100. Type quit if you want to stop the game.");
					input_guess = scan.next();
					try {
						guess = Integer.parseInt(input_guess);
							play = false;
							playing = true;
							first_guess = false;
					} catch (NumberFormatException nfe) {
						if (input_guess.equals("quit")) {
							quit_early = true;
							play = false;
							first_guess = false;
						}
					}
				}
			}
			while (playing) {
				if (rand_number < guess) {
					guess_counter++;
					System.out.println("Lower");
					input_guess = scan.next();
					try {
						guess = Integer.parseInt(input_guess);
					} catch (NumberFormatException nfe) {
						if (input_guess.equals("quit")) {
							quit_early = true;
							break;
						}
					}
				} 
				else if (rand_number > guess) {
					guess_counter++;
					System.out.println("Higher");
					input_guess = scan.next();
					try {
						guess = Integer.parseInt(input_guess);

					} catch (NumberFormatException nfe) {
						if (input_guess.equals("quit")) {
							quit_early = true;
							break;
						}
					}
				} 
				else {
					guess_counter++;
					playing = false;
					correct = true;
				}
			}
			if (correct) {
				System.out.println(rand_number + " is the number. It took you " + guess_counter + " guesses.");
				while (correct) {
					System.out.println("Type play if you want to play again. Type quit if you want to quit.");
					input_replay = scan.next();
					if (input_replay.equals("play")) {
						correct = false;
						first_guess = true;
						play = true;
						break;
					} 
					else if (input_replay.equals("quit")) {
						playMultiple = false;
						System.out.println("Thanks for playing.");
						break;
					}
				}
			}
			else if (quit_early) {
				System.out.println(rand_number + " is the number. It took you " + guess_counter + " guesses before you quit.");
				playMultiple = false;
			}
		}
		scan.close();
	}
}