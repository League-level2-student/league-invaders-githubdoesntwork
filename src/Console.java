import java.io.InputStream;
import java.util.Scanner;

public class Console {
	Scanner scanner;
	String answer;
	int H = 10;
	int HP = 0;
	int MHP = 0;
	int MH = 10;
	int rupees = 0;
	String weapon = "fists";
	int weaponDamage = 1;

	public static void main(String[] args) {
		Console game = new Console(System.in);
	}

	public Console(InputStream e) {
		scanner = new Scanner(e);
		run();
	}

	public void run() {
		while (true) { // MAIN LOOP FOR EVERYTHING
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("----------------");
			System.out.println();
			System.out.println("1. Go on an adventure");
			System.out.println("2. Shop");
			System.out.println("3. Stats");
			System.out.println("4. Item");
			System.out.println();
			System.out.println("----------------");
			answer = scanner.nextLine();
			if (answer.contentEquals("3")) {
				stats();
			} else if (answer.contentEquals("2")) {
				shop();
			}
		}
	}

	public void trans(boolean a) {
		if (a) {
			System.out.println("----------------");
			System.out.println();
			System.out.println("Transaction successful!");
			System.out.println("1. Back");
			System.out.println();
			System.out.println("----------------");
		} else {
			System.out.println("----------------");
			System.out.println();
			System.out.println("You don't have enough rupees!");
			System.out.println("1. Back");
			System.out.println();
			System.out.println("----------------");
		}
	}

	public void shop() {
		while (true) {
			System.out.println("----------------");
			System.out.println();
			System.out.println("Rupees: " + rupees);
			System.out.println("1. HP potions - 10r - 5HP");
			System.out.println("2. MHP potions - 30r - +1MHP");
			System.out.println("3. Iron sword - 50r - 4DMG");
			System.out.println("4. Back");
			System.out.println();
			System.out.println("----------------");
			answer = scanner.nextLine();

			if (answer.contentEquals("1")) {
				while (true) {
					if (rupees > 9) {
						trans(true);
						HP++;
					} else {
						trans(false);
					}
					answer = scanner.nextLine();
					if (answer.contentEquals("1")) {
						break;
					}
				}

			}

			else if (answer.contentEquals("2")) {
				while (true) {
					if (rupees > 29) {
						trans(true);
						MHP++;
					} else {
						trans(false);
					}
					answer = scanner.nextLine();
					if (answer.contentEquals("1")) {
						break;
					}
				}
			}

			else if (answer.contentEquals("3")) {
				while (true) {
					if (rupees > 49) {
						trans(true);
						weapon = "iron sword";
						weaponDamage = 4;
					} else {
						trans(false);
					}
					answer = scanner.nextLine();
					if (answer.contentEquals("1")) {
						break;
					}
				}
			}

			else if (answer.contentEquals("4")) {
				break;
			}
		}
	}
	public void stats() {
		while (true) {
			System.out.println("----------------");
			System.out.println();
			System.out.println("HP: " + H + "/" + MH);
			System.out.println("HP potions: " + HP);
			System.out.println("MHP potions: " + MHP);
			System.out.println("Rupees: " + rupees);
			System.out.println("Weapon: " + weapon);
			System.out.println("Weapon DMG: " + weaponDamage);
			System.out.println();
			System.out.println("1. Back");
			System.out.println();
			System.out.println("----------------");
			answer = scanner.nextLine();
			if (answer.contentEquals("1")) {
				break;
			}
		}
	}
}
