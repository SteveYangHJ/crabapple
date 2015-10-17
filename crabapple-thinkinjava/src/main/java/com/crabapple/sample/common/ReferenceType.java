package com.crabapple.sample.common;

public class ReferenceType {

	// 静态初始化块
	static {
		System.out.println("执行静态初始化块!");
	}
	// 非静态初始化快
	{
		System.out.println("执行非静态初始化块!");
	}

	// 构造函数
	public ReferenceType() {
		System.out.println("类ReferenceType的不带参构造函数!");
	}

	// 带参构造函数
	public ReferenceType(String str) {
		System.out.println("类ReferenceType的带参数构造函数,参数为:" + str);
	}

	// 方法,测试String对象
	public static void changeString(String param) {
		param = "change";
	}

	// 改变数组数据
	public static void changeArray(int[] params) {
		for (int i = 0; i < params.length; i++) {
			params[i] = i;
		}
	}

	// 输出数组数据
	public static void showArray(int[] params) {
		for (int i = 0; i < params.length; i++) {
			System.out.print(params[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		String param = "resource";
		String param2 = "resource";

		// 测试
		System.out.println("changeString()方法之前:字符串param=" + param);
		changeString(param);
		System.out.println("changeString()方法之前:字符串param=" + param);

		// 对于String对象param和param2,二者相等-->Java中常量池机制
		System.out.println("param==param2的结果为:" + (param == param2));
		// 但是对象通过new 关键字初始化的String对象,即使字符串内容相等,两个对象也不等(对象的引用不同)
		String resource1 = new String("resource");
		String resource2 = new String("resource");
		System.out.println("resource1==resource2的结果为:"
				+ (resource1 == resource2));

		System.out.println("以下测试数组:");
		int[] iArray = new int[] { 1, 2, 3, 4, 5 };
		System.out.print("原数组数据:");
		showArray(iArray);
		changeArray(iArray);
		System.out.print("改变数组数据后:");
		showArray(iArray);

		System.out.println("----------------------------");
		// 测试静态初始化块和费静态初始化块执行顺序
		// 在Java中，有两种初始化块：静态初始化块和非静态初始化块。
		// 1.静态初始化块：使用static定义，当类装载到系统时执行一次。若在静态初始化
		// 块中想初始化变量，那仅能初始化类变量，即static修饰的数据成员。
		// 2.非静态初始化块：在每个对象生成时都会被执行一次，可以初始化类的实例变量。
		// 非静态初始化块会在构造函数执行时，且在构造函数主体代码执行之前被运行。

		ReferenceType type = new ReferenceType();
		type = new ReferenceType("同一个对象type,两次初始化!");
	}
}