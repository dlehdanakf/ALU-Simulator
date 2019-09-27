package ALU;

public class Division extends Calculator {
	public Division(int a, int b) {
		super(a, b);
		if(a < 0) {
			int length = this.A.getLength();
			for(int i = 0; i < length; i++) {
				this.A.set(i, true);
			}
		}
	}

	public void printExpression() {
		System.out.println("⑶ 계산식");
		System.out.println("(" + this.a + ") / (" + this.b + ") \n");
	}

	public void calculate() {
		int length = this.Q.getLength();
		String calc = "  MINUS  ";
		BitRegister M = this.M.clone();
		if(M.getSignBit() == this.A.getSignBit()) {
			calc = "   ADD   ";
			M.makeComplement();
		}
		
		System.out.println("⑷ 계산과정");
		System.out.println("   | Action    | A                                | Q                               ");
		this.printBorder();
		
		for(int i = 0; i < length; i++) {
			System.out.print(this.getIndex(i + 1) + " | ");
			
			System.out.print(" SHIFT L  | ");
			boolean carry_Q = this.Q.shiftLeft(false);
			this.A.shiftLeft(carry_Q);
			System.out.print(this.A.toString() + " | ");
			System.out.println(this.Q.toStringDivision());
			
			
			System.out.print("   | " + calc + " | ");
			BitRegister A_M = this.A.clone();
			boolean signA0 = A_M.getSignBit();
			A_M.calcAdd(M);
			System.out.println(A_M.toString() + " | ");
			boolean signA1 = A_M.getSignBit();
			if(signA0 == signA1) {
				this.A.calcAdd(M);
				
				this.Q.set(0, true);
				System.out.print("   |  Q₀ = 1   | ");
			} else {
				System.out.print("   |  RESTORE  | ");
			}
			
			System.out.print(this.A.toString() + " | ");
			System.out.println(this.Q.toString());
			this.printBorder();
		}
		
		this.printFooter();
	}

	protected void printBorder() {
		for(int i = 0; i < 84; i++) {
			System.out.print("─");			
		}
		
		System.out.print("\n");
	}
	protected void printFooter() {
		super.printFooter();
		
		System.out.println("A 레지스터 : " + this.A.toString());
		System.out.println("Q 레지스터 : " + this.Q.toString());
		System.out.println("M 레지스터 : " + this.M.toString() + "\n");
		
		BitRegister original_Q = BitRegister.toBinaryNumber(this.a);
		if(original_Q.getSignBit() != this.M.getSignBit()) {
			BitRegister comp = this.Q.getComplement();
			System.out.print("계산 몫 : " + comp.toString());
			System.out.println(", 10진수 : " + comp.toDemicalNumber());
		} else {
			System.out.print("계산 몫 : " + this.Q.toString());
			System.out.println(", 10진수 : " + this.Q.toDemicalNumber());
		}
		System.out.print("나머지  : " + this.A.toString());
		System.out.println(", 10진수 : " + this.A.toDemicalNumber());
	}
}
