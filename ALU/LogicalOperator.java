package ALU;

import ALU.BitRegister;

public class LogicalOperator {
	static public boolean NOT(boolean a) {
		return !a;
	}
	static public boolean AND(boolean a, boolean b) {
		return a && b;
	}
	static public boolean OR(boolean a, boolean b) {
		return a || b;
	}
	static public boolean XOR(boolean a, boolean b) {
		if(a == b) {
			return false;
		}
		
		return true;
	}
	static public boolean NAND(boolean a, boolean b) {
		return LogicalOperator.NOT(LogicalOperator.AND(a, b));
	}
	static public boolean NOR(boolean a, boolean b) {
		return LogicalOperator.NOT(LogicalOperator.OR(a, b));
	}
	static public boolean XNOR(boolean a, boolean b) {
		return LogicalOperator.NOT(LogicalOperator.XOR(a, b));
	}
	
	static public boolean AND(boolean[] a, int length) {
		boolean tmp = a[0];
		for(int i = 1; i < length; i++) {
			tmp = LogicalOperator.AND(a[i], tmp);
		}
		
		return tmp;
	}
	static public boolean OR(boolean[] a, int length) {
		boolean tmp = a[0];
		for(int i = 1; i < length; i++) {
			tmp = LogicalOperator.OR(a[i], tmp);
		}
		
		return tmp;
	}
	static public boolean XOR(boolean[] a, int length) {
		boolean tmp = a[0];
		for(int i = 1; i < length; i++) {
			tmp = LogicalOperator.XOR(a[i], tmp);
		}
		
		return tmp;
	}
	static public boolean NAND(boolean[] a, int length) {
		return LogicalOperator.NOT(LogicalOperator.AND(a, length));
	}
	static public boolean NOR(boolean[] a, int length) {
		return LogicalOperator.NOT(LogicalOperator.OR(a, length));
	}
	static public boolean XNOR(boolean[] a, int length) {
		return LogicalOperator.NOT(LogicalOperator.XOR(a, length));
	}
	static public BitRegister XOR(BitRegister a, boolean b) {
		BitRegister instance = a.clone();
		for(int i = 0; i < instance.getLength(); i++) {
			boolean tmp = LogicalOperator.XOR(a.get(i), b);
			instance.set(i, tmp);
		}
		
		return instance;
	}
}
