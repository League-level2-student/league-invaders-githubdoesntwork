import java.io.InputStream;
import java.util.Scanner;

public class Console {
	Scanner scanner;
public static void main(String[] args) {
	Console game = new Console(System.in);
}
public Console(InputStream e){
	scanner = new Scanner(e);
	run();
}
public void run() {
	while(true) {																	//MAIN LOOP FOR EVERYTHING
	System.out.println("----------------");
	System.out.println();
	System.out.println("1. Go on an adventure");
	System.out.println("2. Shop");
	System.out.println();
	System.out.println("----------------");
	}
}
}
