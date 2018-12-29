package my.examples.miniwebserver.exam01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class WebServer {

    private int port;

    public WebServer(int i) {
        this.port = i;
    }

    public void run() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                System.out.println("대기 중 입니다.");
                socket = serverSocket.accept();
                System.out.println("연결되었습니다.");
                WebTead webTead = new WebTead(socket);
                webTead.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
class WebTead extends Thread{
    Socket socket = new Socket();
    public WebTead(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintStream out = null;
        List<String> stringList = new ArrayList<String>();

        FileInputStream fis = null;
        try {
            String basePath = "C:\\users\\user\\Desktop\\HTMLTest";
            String filePath = "";
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream());
            byte[] buffer = new byte[1024];

            String line = null;
            while((line = in.readLine()) != null){
                // nullPointException
                if("".equals(line)){
                    break;
                }
                System.out.println("헤더정보 : " + line);
                stringList.add(line);
            }
            // 들어온 헤더정보
            String[] strings = stringList.get(0).split( " ");
            String fullPath = "";
            if(strings[0].equals("GET")){
                if(strings[1].equals("/")){
                    filePath = "\\index.html";
                    fullPath = basePath + filePath;
                }
                else{
                    fullPath = basePath+strings[1].replace("/","\\");
                }
                fis = new FileInputStream(fullPath);
                File file = new File(fullPath);
                String mimeType= URLConnection.guessContentTypeFromName(file.getName());

                if(file.exists()){
                    out.println("HTTP/1.1 200 OK");
                    out.println("Content-Type: " + mimeType + "; charset=UTF-8");
                    out.println("Content-Length: " + file.length());
                    out.println();
                    int num = 0;
                    while((num=fis.read(buffer)) != -1){
                        out.write(buffer,0,num);
                    }
                }
                else{
                    out.println("HTTP/1.1 404 Not Found");
                }
            }

        } catch (IOException e) {
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
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}