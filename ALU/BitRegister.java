package ALU;
import java.lang.Math;
import ALU.LogicalOperator;

public class BitRegister {
	private int _length;
	private boolean[] _register;
	
	static public BitRegister toBinaryNumber(int original_number) {
		BitRegister instance = new BitRegister(32);
		int number = Math.abs(original_number);
		
		int i = 0;
		while(number != 1) {
			instance.set(i, number % 2 == 1 ? true : false);
			number = number / 2;
			
			i++;
		}
		
		instance.set(i, number % 2 == 1 ? true : false);
		if(original_number < 0) {
			instance.makeComplement();
		}
		
		return instance;
	}
	
	public BitRegister(int length) {
		this._length = length;
		this._register = new boolean[length];
	}
	
	public void set(int index, boolean bit) {
		if(index > this._length - 1) {
			return;
		}
		
		this._register[this._length - index - 1] = bit;
	}
	public boolean get(int index) {
		if(index > this._length - 1) {
			return false;
		}
		
		return this._register[this._length - index - 1];
	}
	public boolean[] getAll() {
		return this._register;
	}
	public int getLength() {
		return this._length;
	}
	public boolean getSignBit() {
		return this._register[0];
	}
	public void show() {
		System.out.print("BitRegister::show() => [ ");
		
		for(int i = 0; i < this._length; i++) {
			System.out.print(this._register[i] ? "1" : "0");
		}
		
		System.out.print(" ] \n");
	}
	public void reset() {

		for(int i = 0; i < this._length; i++) {
			this._register[i] = false;
		}
	}
	public BitRegister clone() {
		BitRegister instance = new BitRegister(32);
		for(int i = 0; i < this._length; i++) {
			boolean tmp = this.get(i);
			instance.set(i, tmp);
		}
		
		return instance;
	}
	
	public void makeComplement() {
		for(int i = 0; i < this._length; i++) {
			this.set(i, !this.get(i));
		}
		
		BitRegister one = BitRegister.toBinaryNumber(1);
		this.calcAdd(one);
	}
	public BitRegister getComplement() {
		BitRegister instance = this.clone();
		instance.makeComplement();
		
		return instance;
	}
	
	public boolean shiftRight() {
		boolean first = this.get(0), carry = false;
		for(int i = 0; i < this._length - 1; i++) {
			carry = this.get(i + 1);
			this.set(i, carry);
		}
		
		this.set(this._length - 1, first);
		return carry;
	}
	public boolean shiftRight(boolean prev_carry) {
		boolean carry = this.shiftRight();
		this.set(this._length - 1, prev_carry);
		
		return carry;
	}
	
	public boolean shiftLeft() {
		boolean first = this.get(this._length - 1), carry = false;
		for(int i = this._length - 1; i > 0; i--) {
			carry = this.get(i - 1);
			this.set(i, carry);
		}
		
		this.set(0, first);
		return carry;
	}
	public boolean shiftLeft(boolean prev_carry) {
		boolean carry = this.shiftLeft();
		this.set(0, prev_carry);
		
		return carry;
	}
	
	public String toString() {
		String str = "";
		for(int i = 0; i < this._length; i++) {
			str = (this.get(i) ? 1 : 0) + str;
		}
		
		return str;
	}
	public int toDemicalNumber() {
		if(this.getSignBit() == true) {
			return this.getComplement().toDemicalNumber() * -1;
		}
		
		int result = 0;
		for(int i = 0; i < this._length; i++) {
			boolean bit = this.get(i);
			if(bit == true) {
				result += Math.pow(2, i);
			}
		}
		
		return result;
	}
	

	/**
	 * 2의 보수를 계산하는 과정에서만 실행하는 메소
	 * @param instance
	 */
	protected void calcAdd(BitRegister instance) {
		boolean carry = false;
		for(int i = 0; i < this._length; i++) {
			boolean a = this.get(i);
			boolean b = instance.get(i);
			
			boolean t1 = LogicalOperator.XOR(a, b);
			boolean t2 = LogicalOperator.AND(a, b);
			
			boolean result = LogicalOperator.XOR(t1, carry);
			boolean t3 = LogicalOperator.AND(t1, carry);
			
			carry = LogicalOperator.OR(t2, t3);
			this.set(i, result);
		}
	}
}