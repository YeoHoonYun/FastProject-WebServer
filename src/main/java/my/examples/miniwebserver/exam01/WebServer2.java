package my.examples.miniwebserver.exam01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.sql.SQLOutput;

public class WebServer2 {
    private int port;

    public WebServer2(int i) {
        this.port = i;
    }

    public void start() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(port);
            while(true) {
                System.out.println("서버 접속을 대기합니다.");
                socket = serverSocket.accept();
                System.out.println("서버에 접속하였습니다.");

                //쓰레드로 연결을 해야한다.
                WebTest webTest = new WebTest(socket);
                webTest.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
class WebTest extends Thread{
    Socket socket;

    public WebTest(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintStream out = null;

        FileInputStream fis = null;

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream());

            String[] strings = in.readLine().split(" ");
            String httpRequest = strings[0];
            String httpReqeustPath = strings[1];
            String basePath = "C:\\Users\\cjswo\\OneDrive\\바탕 화면\\HTMLTest";

            //HTTP 요청 메시지 출력
            String stringLine = null;
            while((stringLine = in.readLine()) != null){
                if("".equals(stringLine)){
                    break;
                }
                System.out.println(stringLine);
            }

            StringBuffer contentPath = new StringBuffer();
            contentPath.append(basePath);

            if(httpRequest.equals("GET")){
                if(httpReqeustPath.equals("/")){
                    contentPath.append("\\index.html");
                }
                else{
                    contentPath.append(httpReqeustPath);
                }
                fis = new FileInputStream(contentPath.toString());
                byte[] buffer = new byte[1024];
                File file = new File(contentPath.toString());

                if(!file.exists()){
                    System.out.println("Requested File Not Found : ");
                    out.println("HTTP/1.1 404 Not Found");
                    out.println("Connection: close\\r\\n");
                    out.println("\\r\\n");
                }
                else {
                    String contentType= URLConnection.guessContentTypeFromName(file.getName());

                    out.println("HTTP/1.1 200 OK");
                    out.println("content-type: "+contentType+"; charset=UTF-8");
                    out.println("Content-Length: " + file.length());
                    out.println();
                    int num = 0;
                    while((num = fis.read(buffer)) != -1){
                        out.write(buffer,0, num);
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.close();
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
