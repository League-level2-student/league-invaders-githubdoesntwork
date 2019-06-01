import java.io.InputStream;
import java.util.Scanner;

public class Console {
	Scanner scanner;
	String answer;
	int health = 10;
	int healthPotions=0;
	int maxHealthPotions=0;
	int maxHealth=10;

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
			System.out.println();
			System.out.println("----------------");
			answer = scanner.nextLine();
				if (answer == "3") {
					System.out.println("----------------");
					System.out.println();
					System.out.println("HP: "+health+"/"+maxHealth);
					System.out.println("HP potions: "+healthPotions);
					System.out.println("MHP potions: "+maxHealthPotions);
					System.out.println();
					System.out.println("----------------");
				}
		}
	}
}
