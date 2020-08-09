package thread.d0808;

import java.lang.reflect.Method;

@Test("测试类")
public class demo2 {

	@Test("测试类")
	public void test() {
		
	}
	
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException {
		demo2 d=new demo2();
		//Java黑科技--反射
		//获取一个类的类对象
		//方式一：使用类名+class 关键字
		Class<?> cls=demo2.class;
		//方式二：对象名+getClass()
		cls=d.getClass();
		//方式三  ：Class类+forName方法(加载时会执行静态块，完成初始化)
		cls=Class.forName("thread.d0808.demo2");
		
		
		//反射操纵
		cls.getFields();//获取公有的属性数组
		cls.getMethods();//获取公有的方法数组
		cls.getConstructors();//获取构造方法数组
		
		//..
		cls.getAnnotations();//获取类上注解数组
		Test test1=cls.getAnnotation(Test.class);//根据注解类的类对象获取注解对象
		//如果获取为空，表示该方法没有找到对应的注解
		System.out.println(test1.value());
		//获取方法对象
		Method m=cls.getMethod("test");
		
		Test test2=m.getAnnotation(Test.class);
		System.out.println(test2.value());
	}
}
