package ALU;

public class Subtraction extends Addition {
	public Subtraction(int a, int b) { super(a, b); }
	
	public void printExpression() {
		System.out.println("⑶ 계산식");
		System.out.println(     "(" + this.a + ") - (" + this.b + ") \n");
	}
	public void calculate() {		
		System.out.println("* 빼기 연산을 수행하기 위한 레지스터 세팅");
		System.out.println("- Carry 비트를 1로 조정");
		this.C = true;
		
		System.out.println("- M 레지스터에 Carry 와 XOR 연산 수행");
		System.out.println("  前 : " + this.M.toString());
		
		this.M = LogicalOperator.XOR(this.M, this.C);
		
		System.out.println("  後 : " + this.M.toString() + "\n");
		
		super.calculate();
	}
}
