package ALU;
import ALU.BitRegister;

abstract public class Calculator {
	public int a;
	public int b;
	
	public BitRegister A;
	public BitRegister Q;
	public BitRegister M;
	
	public boolean C;
	public boolean S;
	public boolean Z;
	public boolean V;
	public boolean[] S_;
	
	public Calculator(int a, int b) {
		this.a = a;
		this.b = b;
		
		this.Q = BitRegister.toBinaryNumber(this.a);
		this.M = BitRegister.toBinaryNumber(this.b);
		this.C = this.S = this.Z = this.V = false;
	}
	public void printHeader() {
		System.out.println("【 Start ALU Simulator 】");
		System.out.println("⑴ 입력된 10진수");
		System.out.print("A. " + this.a);
		System.out.println(", B. " + this.b + "\n");
		
		System.out.println("⑵ 2진수로 변환된 레지스터");
		System.out.println("A(Q 레지스터) ➔ " + this.Q.toString());
		System.out.println("B(M 레지스터) ➔ " + this.M.toString() + "\n");
		
		this.printExpression();
	}
	public abstract void printExpression();
	public abstract void calculate();
	
	protected String getIndex(int i) {
		if(i > 9) {
			return i + ""; 
		}
		
		return "0" + i;
	}
	protected int toNum(boolean a) {
		if(a == true)
			return 1;
		
		return 0;
	}
	
	protected void printFooter() {
		System.out.println("⑸ 계산결과");
	}
}