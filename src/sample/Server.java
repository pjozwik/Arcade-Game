package sample;

import java.io.*;
import java.net.*;

/**
 * klasa umozliwiajaca stworzenie serwera, polaczenie z klientem
 */
public class Server {
    public static String serverRequest = null;
    public static ServerSocket sr;

    /**
     * funkcja tworzaca socket servera oraz interpretujaca zadania pochodzace od serwera
     * @param args
     * @throws Exception
     */
    public static void main(String []args) throws Exception{
         sr = new ServerSocket(6777);
        String request = null;
        for(;;) {
            Socket socket = sr.accept();

            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            request = br.readLine();

            if(request.equals("Update score file")) {
                request = br.readLine();
                try {
                    FileWriter fileWriter = new FileWriter("src\\Serwer\\wyniki.txt",true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.newLine();
                    bufferedWriter.write(request);
                    bufferedWriter.close();
                    fileWriter.close();
                }catch(IOException io){
                    System.out.println("Nie udało sie zaktualizowac wynikow");
                }
            } else if(request.equals("Get config file")){
                serverRequest = getConfig();
                OutputStream os = socket.getOutputStream();
                PrintWriter pw = new PrintWriter(os, true);
                pw.println(serverRequest);
            }else if(request.equals("Get config2")){
                serverRequest = getConfig2();
                OutputStream os = socket.getOutputStream();
                PrintWriter pw1 = new PrintWriter(os, true);
                pw1.println(serverRequest);
            }else if(request.equals("Get lvl config")){
                serverRequest = getLvlConfig();
                OutputStream os = socket.getOutputStream();
                PrintWriter pw2 = new PrintWriter(os, true);
                pw2.println(serverRequest);
            }else if(request.equals("Get score file")){
                serverRequest = getScore();
                OutputStream os = socket.getOutputStream();
                PrintWriter pw3 = new PrintWriter(os, true);
                pw3.println(serverRequest);
            }else if(request.equals("Login")){
                OutputStream os = socket.getOutputStream();
                PrintWriter pw3 = new PrintWriter(os, true);
                pw3.println("Loggedin");
            }
        }
    }

    /**
     * funkcja tworzaca Stringa zawierajacego dane z pliku par
     * @return
     */
    private static String getConfig(){
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("par.txt"))){
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String split[] = currentLine.split(" ");
                if(!"#".equals(split[0]))
                sb.append(currentLine + "  ");
            }
        }
        catch (Exception e){
            System.out.println("Nie można otworzyć pliku");
        }

        return sb.toString();
    }

    /**
     * funkcja tworzaca Stringa zawierajacego dane z pliku par2
     * @return
     */
    private static String getConfig2(){
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("par2.txt"))){
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                    sb.append(currentLine + "  ");
            }
        }
        catch (Exception e){
            System.out.println("Nie można otworzyć pliku");
        }

        return sb.toString();
    }

    /**
     * funkcja tworzaca Stringa zawierajacego dane z pliku level
     * @return
     */
    private static String getLvlConfig(){
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("level.txt"))){
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String split2[] = currentLine.split(" ");
                if(!"#".equals(split2[0]))
                sb.append(currentLine + "  ");
            }
        }
        catch (Exception e){
            System.out.println("Nie można otworzyć pliku");
        }

        return sb.toString();
    }

    /**
     * funkcja tworzaca Stringa zawierajacego dane z pliku wyniki
     * @return
     */
    private static String getScore(){
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("wyniki.txt"))){
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                    sb.append(currentLine + "  ");
            }
        }
        catch (Exception e){
            System.out.println("Nie można otworzyć pliku");
        }

        return sb.toString();
    }
}
