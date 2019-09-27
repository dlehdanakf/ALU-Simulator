package ALU;

public class Multiplication extends Calculator {
	public Multiplication(int a, int b) { super(a, b); }

	public void printExpression() {
		System.out.println("⑶ 계산식");
		System.out.println("(" + this.a + ") × (" + this.b + ") \n");
	}

	public void calculate() {
		boolean Q_1 = false;
		int length = this.Q.getLength();
		
		System.out.println("⑷ 계산과정");
		System.out.println("   | Action    | A                                | Q                                | Q₋₁");
		this.printBorder();
		
		for(int i = 0; i < length; i++) {
			System.out.print(this.getIndex(i + 1) + " | ");
			
			boolean Q0 = this.Q.get(0);
			int compare = this.compareQ(Q0, Q_1);
			if(compare > 0) {
				if(compare == 1) {
					this.A.calcSub(this.M);
					System.out.print("A ← A - M | ");
				} else if(compare == 2) {
					this.A.calcAdd(this.M);
					System.out.print("A ← A + M | ");
				}
				
				System.out.print(this.A.toString() + " | ");
				System.out.print(this.Q.toString() + " | ");
				System.out.print(this.toNum(Q_1) + "\n   | ");
			}
			
			boolean carry_A = this.A.shiftRight(this.A.getSignBit());
			boolean carry_Q = this.Q.shiftRight(carry_A);
			Q_1 = carry_Q;
			
			System.out.print(" SHIFT R  | ");
			System.out.print(this.A.toString() + " | ");
			System.out.print(this.Q.toString() + " | ");
			System.out.print(this.toNum(Q_1) + "\n");
			this.printBorder();
		}
		
		this.printFooter();
	}
	
	protected int compareQ(boolean Qn, boolean Q1) {
		if(Qn == true && Q1 == false) {
			return 1; // A ← A - M;
		} else if(Qn == false && Q1 == true) {
			return 2; // A ← A + M;
		}
		
		return 0;
	}
	
	protected void printBorder() {
		for(int i = 0; i < 90; i++) {
			System.out.print("─");			
		}
		
		System.out.print("\n");
	}
	protected void printFooter() {
		super.printFooter();
		
		BitRegister result = BitRegister.mergeRegister(this.A, this.Q);
		System.out.println("바이너리   : " + result.toString());
		System.out.println("10진수    : " + result.toDemicalNumber());
		
		System.out.println("A 레지스터 : " + this.A.toString());
		System.out.println("Q 레지스터 : " + this.Q.toString());
		System.out.println("M 레지스터 : " + this.M.toString());
	}
}
