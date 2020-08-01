package thread.d0725;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class X2 implements Runnable {
    private int x;
    private int y;
public static void main(String[] args){
	
	
           X2 that=new X2();
           (new Thread(that)).start();
           (new Thread(that)).start();//第6行
//第7行
}
public synchronized void run() {
          for( ; ; ){
               x++;
               y++;//第11行
             System.out.println("x="+x+",y="+y);
          }
}
}