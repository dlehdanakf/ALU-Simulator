package ALU;

public class Addition extends Calculator {
	public Addition(int a, int b) { super(a, b); }

	public void printExpression() {
		System.out.println("⑶ 계산식");
		System.out.println("(" + this.a + ") + (" + this.b + ") \n");
	}
	public void calculate() {
		boolean Cn = this.C, Sn;
		int length = this.Q.getLength();
		this.S_ = new boolean[length];
		
		System.out.println("⑷ 계산과정");
		System.out.println("   | Q M C₀ | C S");
		System.out.println("─────────────────");

		for(int i = 0; i < length; i++) {
			boolean q = this.Q.get(i);
			boolean m = this.M.get(i);
			boolean[] full = this.fullAdder(q, m, Cn);
			
			Sn = full[0];
			
			this.C = Cn;
			Cn = full[1];
			
			this.S_[i] = Sn;
			this.Q.set(i, Sn);
			
			System.out.print(this.getIndex(i + 1) + " | ");
			System.out.print(this.toNum(q) + " " + this.toNum(m) + " " + this.toNum(this.C) + "  | ");
			System.out.println(this.toNum(Cn) + " " + this.toNum(Sn));
		}
		
		this.V = LogicalOperator.XOR(this.C, Cn);
		this.Z = LogicalOperator.NOR(this.S_, length);
		this.S = this.Q.getSignBit();
		this.C = Cn;
		
		this.printFooter();
	}
	
	protected boolean[] halfAdder(boolean a, boolean b) {
		boolean S = LogicalOperator.XOR(a, b);
		boolean C = LogicalOperator.AND(a, b);
		
		boolean[] result = new boolean[] { S, C };
		return result;
	}
	protected boolean[] fullAdder(boolean a, boolean b, boolean c0) {
		boolean half[] = this.halfAdder(a, b);
		boolean tmpS = half[0];
		boolean tmpC = half[1];
		
		boolean S = LogicalOperator.XOR(tmpS, c0);
		boolean C = LogicalOperator.OR(LogicalOperator.AND(tmpS, c0), tmpC);
		
		boolean[] result = new boolean[] { S, C };
		return result;
	}
	
	protected void printFooter() {
		super.printFooter();
		System.out.println("바이너리 : " + this.Q.toString());
		System.out.println("10진수  : " + this.Q.toDemicalNumber());
		System.out.println("V Flag : " + this.toNum(this.V));
		System.out.println("Z Flag : " + this.toNum(this.Z));
		System.out.println("S Flag : " + this.toNum(this.S));
		System.out.println("C Flag : " + this.toNum(this.C));
	}
}
