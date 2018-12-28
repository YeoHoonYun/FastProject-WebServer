package my.examples.miniwebserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;

/**
 * Created by cjswo9207u@gmail.com on 2018-12-28
 * Github : https://github.com/YeoHoonYun
 */
public class WebServer {
    private int port;

    public WebServer(int port){
        this.port = port;
    }
    public void run(){
        // 접속을 대기하고 있다.
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(port);
            while(true) {
                System.out.println("접속을 대기합니다.");
                Socket socket = serverSocket.accept(); // 클라이언트가 접속할때까지 대기
                HttpHandler httpHandler = new HttpHandler(socket);
                httpHandler.start(); // 쓰레드를 실행한다.
            }
        }catch(IOException ioe){
            ioe.printStackTrace();
        }finally {
            try{ serverSocket.close(); }catch(Exception ignore){ }
        }
    }
}

class HttpHandler extends Thread{
    private Socket socket;
    public HttpHandler(Socket socket){
        this.socket = socket;
    }

    public void run(){ // 별도로 동작할 코드는 run메소드에서 작성한다.
        final String baseDir = "C:\\Users\\cjswo\\OneDrive\\바탕 화면\\HTMLTest";

        BufferedReader in = null;
        PrintStream out = null;
        FileInputStream fis = null;
        try{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream());

            String requestLine = in.readLine();
            String[] s = requestLine.split(" ");
            System.out.println(s[0] + s[1] + s[2]);
            String httpMethod = s[0];
            String httpPath = s[1];
            if(httpPath.equals("/"))
                httpPath = "/index.html";
            String filePath = baseDir + httpPath;

            File file = new File(filePath);
            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            System.out.println("----"+mimeType);
            if(mimeType == null)
                mimeType = "text/html";

            String line = null;
            while((line = in.readLine()) != null){ // 빈줄까지 읽어들인다.
                // nullPointException
                if("".equals(line)){
                    break;
                }
                System.out.println("헤더정보 : " + line );
            }

            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: " + mimeType);
            out.println("Content-Length: " + file.length());
            out.println();

            byte[] buffer = new byte[1024];
            int readCount = 0;
            fis = new FileInputStream(file);
            while((readCount = fis.read(buffer)) != -1){
                out.write(buffer,0, readCount);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            try{ fis.close(); } catch(Exception ignore){}
            try{ in.close(); }catch(Exception ignore){}
            try{ out.close(); }catch(Exception ignore){}
            try{ socket.close(); }catch(Exception ignore){}
        }
    }
}