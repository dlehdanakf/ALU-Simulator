import java.util.Scanner;
import ALU.*;

public class Application {
	public static void main(String[] args) {
		int a, b;
		Calculator calc = null;
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);

		Application.printHeader();
		
		System.out.println("≫ 숫자(A)를 입력해주세요.");
		while(true) {
			try {
				a = Integer.parseInt(scan.nextLine());
				break;
			} catch(NumberFormatException e) {
				System.out.println("≫ 형식이 맞지 않습니다. 다시 입력해주세요.");
			}
		}
		
		System.out.println("≫ 숫자(B)를 입력해주세요.");
		while(true) {
			try {
				b = Integer.parseInt(scan.nextLine());
				break;
			} catch(NumberFormatException e) {
				System.out.println("≫ 형식이 맞지 않습니다. 다시 입력해주세요.");
			}
		}
		
		int _case  = 1;
		while(true) {
			switch(_case) {
				case 1: calc = new Addition(a, b); break;
				case 2: calc = new Subtraction(a, b); break;
				case 3: calc = new Multiplication(a, b); break;
				case 4: calc = new Division(a, b); break;
			}
			
			System.out.println("\n===============================================\n");
			
			calc.printHeader();
			calc.calculate();
			
			System.out.print("\n");
			if(_case >= 4) {
				System.out.println("ALU Simulator 프로그램을 종료합니다.");
				return;
			} else {
				System.out.println("엔터(Enter)를 입력하면 다음 연산을 수행합니다.");
				scan.nextLine();
				_case += 1;
			}
		}
	}
	
	protected static void printHeader() {
		System.out.println("                   __                         ___  __   __  ");
		System.out.println(" /\\  |    |  |    /__` |  |\\/| |  | |     /\\   |  /  \\ |__) ");
		System.out.println("/~~\\ |___ \\__/    .__/ |  |  | \\__/ |___ /~~\\  |  \\__/ |  \\ ");
		System.out.println("");
	}
}
