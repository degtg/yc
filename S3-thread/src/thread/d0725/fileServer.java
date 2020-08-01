package thread.d0725;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class fileServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss=new ServerSocket(9999);
        Socket s = ss.accept();
        System.out.println("Server Ok");
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(s.getInputStream()));
       // FileWriter fw=new FileWriter("D:\\xkh.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\a.txt"));
        String line;
        while ((line=bufferedReader.readLine())!=null){//等待读取数据
           // if ("886".equals(line))break;
            bufferedWriter.write(line);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }

        //给出反馈
        BufferedWriter bwServer=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        bwServer.write("文件上传成功！！！");
        bwServer.newLine();
        bwServer.flush();

        ss.close();
        bufferedReader.close();
        bufferedWriter.close();
    }
}
