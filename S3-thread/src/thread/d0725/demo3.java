package thread.d0725;

import java.util.ArrayList;
import java.util.List;

public class demo3 {

	//存放数字的集合
	private List<String> list=new ArrayList<>();
	//加入了synchronized 关键字的叫 同步方法 那么这些方法将会在同一个时刻被一个线程所执行11
	public synchronized void add(String i) {
		list.add(i);
	}
	
	//根据索引来移除
	/*
	 * public String del(int index) { return list.remove(index); }
	 */
	
	//弹出元素
	/**
	 * synchronized 同步关键字，
	 * 修饰为同步的方法 n个 
	 * @return
	 */
	public String pop() {
		
		//同步代码块,()中写同步的资源对象，也就是要锁定的对象
		synchronized(this) { //this表示d3对象
			return list.remove(0);
		}
		
	}
	
	public synchronized int size() {
		return list.size();
	}
	
	public static void main(String[] args) {
		demo3 d3=new demo3();
		
		Thread t1=new Thread("线程1") {
			public void run() {
				while (true) {
					if(d3.size()<10) {
						for (int i = 0; i < 10; i++) {
							System.out.println(Thread.currentThread()+":"+d3.pop());
							d3.add(i+"");
						}
					}else {
						try {
							Thread.sleep(10*1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		};
		Thread t2=new Thread("=========线程2") {
			public void run() {
				while (true) {
					if(d3.size()>0) {
						System.out.println(Thread.currentThread()+":"+d3.pop());
					}
				}
			}
		};
		
		Thread t3=new Thread("=========线程3") {
			public void run() {
				while (true) {
					if(d3.size()>0) {
						System.out.println(Thread.currentThread()+":"+d3.pop());
					}
				}
			}
		};
		t1.start();
		t2.start();
		t3.start();
	}
}
