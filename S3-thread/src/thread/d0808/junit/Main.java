package thread.d0808.junit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

	public static void main(String[] args) {

		DemoTest dt = new DemoTest();
		Class<?> cls = dt.getClass();
		
		Method beforeMethod=getMethodByAnnotation(cls,Before.class);
		Method afterMethod=getMethodByAnnotation(cls,After.class);

		// 获取所有方法，并进行遍历
		for (Method m : cls.getMethods()) {
			
			if (m.getAnnotation(Test.class) != null) {
				// 动态执行方法，junit 的测试方法不能定义参数
				try {
					
					if(beforeMethod !=null) {
						beforeMethod.invoke(dt);
					}
					m.invoke(dt);
					
					if(beforeMethod !=null) {
						afterMethod.invoke(dt);
					}
				} catch (IllegalAccessException | IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					System.out.println("测试异常，失败！");
					// 如果是dt.m 方法出现业务异常，将会封装该异常中
					e.printStackTrace();
				}
			}
			
		}

	}

	/**
	 * 根据输入的注解的类名，返回对应的方法
	 * @param testcls 带查找的被测试的类对象
	 * @param annoCls 要查找方法标注的注解类对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static Method getMethodByAnnotation(Class<?> testcls, @SuppressWarnings("rawtypes") Class annoCls) {
		for(Method m: testcls.getMethods()) {
			if(m.getAnnotation(annoCls) != null) {
				return m;
			}
		}
		return null;
	}
}
