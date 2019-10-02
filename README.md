# ALU Simulator
> <img src="http://home.konkuk.ac.kr/cms/Site/UserFiles/Image/internet/top_logo.gif" height="28px;" />&nbsp;&nbsp;<img src="http://home.konkuk.ac.kr/cms/Site/UserFiles/Image/internet/toptitle.gif" height="24px;" />  
> Konkuk University, Dept. of Software  
> 2019 - "Computer Architechure" group assignment

### 프로젝트 개요
- 컴퓨터 내부에서 10진수를 2진수로 변환하고, 두 2진수로 `덧셈`,`뺄셈`,`곱셈`,`나눗셈`을 수행하는 과정을 시뮬레이션하는 프로그램 개발한다.
- 덧셈과 뺄셈은 `병렬가감산기의 동작원리`에 따라 구현하고, 곱셈 및 나눗셈 연산은 수업 시간에 다룬`Booth 알고리즘` 및 `나눗셈 알고리즘`에 따라서 구현한다.
- 연산에 사용되는 레지스터의 길이는 **32비트**로 가정한다.

<img />

## 프로젝트 폴더 구조
```
ProjectRoot/
├── ALU/
│   ├── Calculator.java			// 추상화된 계산기 클래스
│   ├── Addition.java			// 가산기
│   ├── Subtraction.java		// 감산기
│   ├── Multiplication.java		//	Booth Algorithm
│   ├── Division.java			// 나눗셈 알고리즘
│   ├── BitRegister.java		// boolean 배열로 구성된 비트 레지스터
│   └── LogicalOperator.java		// AND, OR, XOR 과 같은 논리연산 수행
└── Application.java			// ALU Simulator application 실행
```

<img />

## 주요 Component
### Calculator.java
```java
abstract public class Calculator {
	public int a;				// 사용자가 입력한 10진수 a
	public int b;				// 사용자가 입력한 10진수 b
	
	public BitRegister A;			// 레지스터 A
	public BitRegister Q;			// 레지스터 Q
	public BitRegister M;			// 레지스터 M
	
	public boolean C;			// Carry Flag
	public boolean S;			// Sign Flag
	public boolean Z;			// Zero Flag
	public boolean V;			// Overflow Flag
	public boolean[] S_;		// Sign Flag 계산을 위한 임시 비트 배열

	public Calculator(int a, int b) { ... }
	
	public void printHeader() { ... }
	public abstract void printExpression();
	
	// 상속받은 덧셈, 뺄셈, 곱셈, 나눗셈 클래스는 아래 메서드를 override 하여
	// 상세 연산과정을 수행한다.
	public abstract void calculate();
}

public class Addition extends Calculator { ... }
public class Subtraction extends Calculator { ... }
public class Multiplication extends Calculator { ... }
public class Division extends Calculator { ... }
```

### BitRegister.java
```java
public class BitRegister {
	// 10진수를 인자로 입력하면 2진수로 변환하고 BitRegister 인스턴스로 반환
	static public BitRegister toBinaryNumber(int original_number) { ... }
	
	// 2의보수로 비트레지스터를 변환
	public void makeComplement() { ... }
	
	// Shift 연산 수행
	public boolean shiftRight() { ... }
	public boolean shiftRight(boolean prev_carry) { ... }
	public boolean shiftLeft() { ... }
	public boolean shiftLeft(boolean prev_carry) { ... }
	
	// 인스턴스의 boolean[] 배열을 01011.. 과 같은 문자열로 반환
	public String toString() { ... }
	
	// 비트레지스터를 10진수로 변환
	public int toDemicalNumber() { ... }
	
	// 곱셈, 나눗셈 연산 시 내부적으로 사용하는 가(감)산기
	public void calcAdd(BitRegister instance) { ... }
	public void calcSub(BitRegister instance) { ... }
}
```