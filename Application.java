import java.util.Scanner;
import ALU.*;

public class Application {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int a, b, select;
		Calculator calc = null;

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
		
		System.out.println("≫ 원하시는 연산자를 선택해주세요.");
		Application.printExpressionQuestion(a, b);
		while(true) {
			try {
				select = Integer.parseInt(scan.nextLine());
				if(select < 1 || select > 4) {
					throw new RuntimeException();
				}
				
				break;
			} catch(NumberFormatException e) {
				System.out.println("≫ 형식이 맞지 않습니다. 다시 입력해주세요.");
			} catch(RuntimeException e) {
				System.out.println("≫ 1 부터 4 사이의 숫자만 입력해주세요.");
			}
		}
		
		switch(select) {
			case 1: calc = new Addition(a, b); break;
			case 2: calc = new Subtraction(a, b); break;
			case 3: calc = new Multiplication(a, b); break;
			case 4: calc = new Division(a, b); break;
		}
		
		System.out.println("\n===============================================\n");
		
		calc.printHeader();
		calc.calculate();
	}
	
	protected static void printHeader() {
		System.out.println("                   __                         ___  __   __  ");
		System.out.println(" /\\  |    |  |    /__` |  |\\/| |  | |     /\\   |  /  \\ |__) ");
		System.out.println("/~~\\ |___ \\__/    .__/ |  |  | \\__/ |___ /~~\\  |  \\__/ |  \\ ");
		System.out.println("");
	}
	protected static void printExpressionQuestion(int a, int b) {
		System.out.print("① " + a + " + " + b + "   ");
		System.out.print("② " + a + " - " + b + "   ");
		System.out.print("③ " + a + " × " + b + "   ");
		System.out.println("④ " + a + " / " + b + "   ");
	}
}
