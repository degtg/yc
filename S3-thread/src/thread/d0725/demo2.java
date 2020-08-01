package thread.d0725;

/**
 *join()
 */
public class demo2 {

	public static void main(String[] args) {
		//匿名内部类创建线程
		Thread t1=new Thread("线程1") {
			//类定义，匿名方式
			public void run() {
				for (int i = 0; i < 1000; i++) {
					System.out.println(Thread.currentThread().getName()+":"+i);
				}
			}
		};
		Thread t2=new Thread("---------------线程2") {
			//类定义，匿名方式
			public void run() {
				for (int i = 0; i < 1000; i++) {
					System.out.println(Thread.currentThread().getName()+":"+i);
					try {
						if(i==500) {
							t1.join();//加入join语句，t2执行到500的时候，会等到T1运行完再执行
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		t1.start();
		t2.start();
	}
}
