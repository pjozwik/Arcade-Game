package sample;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * klasa zawierajaca atrybuty oraz metody potrzebne do wczytania zmiennych z plikow parametrycznych
 */
public class Load {

    static String nazwaGry;
    static int liczbaStopniTrudnosci;
    static int liczbaPoziomow;
    static int zmianaStopniaTrudnosci;
    static int poczatkowaSzerokoscPlanszy;
    static int poczatkowaWysokoscPlanszy;
    static float poczatkowaSzerokoscObiektuGryJakoProcentPoczatkowejSzerokosciPlanszy;
    static String tlo;
    static String kolorTla;
    static String obiektyGry;
    static String figuraObiektuGry;
    static int iloscPunktowZaZestrzelenieTarczy;
    static int czasGry;
    static int szerokoscObiektu;
    static int wysokoscObiektu;
    static int iloscOdejmowanychPunktowZaNieTrafienie;
    static String rodzajCelownika;
    static String kolorCelownika;
    static int szybkosc;
    public static String[] kolor = null;
    public static String[] celownik = null;
    public static String[] kolor2 = null;
    public static int[] levels1 = null;
    public static int[] levels2 = null;
    public static List<Score> scores = new ArrayList<>();
    public static String parametry;
    public static String parametry2;
    public static String parametry3;
    public static String parametry4;
    public static String plikTla;
    public static String connection = "disconnected";
    /**
     * metoda słuzaca do wysłania zadania o wyslanie danych z pliku par.text przez serwer oraz wczytaniu ich w przypadku gry online
     * oraz wczytania danych z pliku po stronie klienta w przypadku gry offline
     */
    public static void wczytajZPliku() throws Exception {



        if(connection.equals("connected")) {
            Socket sr = new Socket("localhost", 6777);
            OutputStream os = sr.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);
            pw.println("Get config file");

            InputStream is = sr.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            parametry = br.readLine();
            System.out.println(parametry);
        }


        if(connection.equals("connected")) {
            String split[] = parametry.split("  ");
            for (int i = 0; i < split.length; i++) {
                String split2[] = split[i].split("=");
                if (split2[0].equals("nazwaGry")) nazwaGry = split2[1];
                if (split2[0].equals("liczbaPoziomow")) liczbaPoziomow = Integer.parseInt(split2[1]);
                if (split2[0].equals("poczatkowaSzerokoscPlanszy"))
                    poczatkowaSzerokoscPlanszy = Integer.parseInt(split2[1]);
                if (split2[0].equals("poczatkowaWysokoscPlanszy"))
                    poczatkowaWysokoscPlanszy = Integer.parseInt(split2[1]);
                if (split2[0].equals("liczbaStopniTrudnosci")) liczbaStopniTrudnosci = Integer.parseInt(split2[1]);
                if (split2[0].equals("zmianaStopniaTrudnosci")) zmianaStopniaTrudnosci = Integer.parseInt(split2[1]);
                if (split2[0].equals("poczatkowaSzerokoscObiektuGryJakoProcentPoczatkowejSzerokosciPlanszy"))
                    poczatkowaSzerokoscObiektuGryJakoProcentPoczatkowejSzerokosciPlanszy = Float.parseFloat(split2[1]);
                if (split2[0].equals("tlo")) tlo = split2[1];
                if (split2[0].equals("obiektyGry")) obiektyGry = split2[1];
                if (split2[0].equals("figuraObiektuGry")) figuraObiektuGry = split2[1];
                if (split2[0].equals("plikTla")) plikTla = split2[1];
                if (split2[0].equals("klorTla")) {
                    kolorTla = split2[1];
                    kolor = kolorTla.split(" ");
                }

            }
        }else {
            Properties props = new Properties();


            try (Reader in = new InputStreamReader(new FileInputStream("par.txt"), "UTF-8")) {
                props.load(in);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            nazwaGry = props.getProperty("nazwaGry");
            liczbaStopniTrudnosci = Integer.parseInt(props.getProperty("liczbaStopniTrudnosci"));
            liczbaPoziomow = Integer.parseInt(props.getProperty("liczbaPoziomow"));
            zmianaStopniaTrudnosci = Integer.parseInt(props.getProperty("zmianaStopniaTrudnosci"));
            poczatkowaSzerokoscPlanszy = Integer.parseInt(props.getProperty("poczatkowaSzerokoscPlanszy"));
            poczatkowaWysokoscPlanszy = Integer.parseInt(props.getProperty("poczatkowaWysokoscPlanszy"));
            poczatkowaSzerokoscObiektuGryJakoProcentPoczatkowejSzerokosciPlanszy = Float.parseFloat(props.getProperty("poczatkowaSzerokoscObiektuGryJakoProcentPoczatkowejSzerokosciPlanszy"));
            tlo = props.getProperty("tlo");
            kolorTla = props.getProperty("klorTla");
            obiektyGry = props.getProperty("obiektyGry");
            figuraObiektuGry = props.getProperty("figuraObiektuGry");
            kolor = kolorTla.split(" ");
        }
    }

    /**
     metoda słuzaca do wysłania zadania o wyslanie danych z pliku par2.text przez serwer oraz wczytaniu ich w przypadku gry online
     * oraz wczytania danych z pliku po stronie klienta w przypadku gry offline
     */
    public static void wczytajZPliku2() throws Exception {

        if(connection.equals("connected")) {
            Socket sr = new Socket("localhost", 6777);
            OutputStream os1 = sr.getOutputStream();
            PrintWriter pw1 = new PrintWriter(os1, true);
            pw1.println("Get config2");

            InputStream is1 = sr.getInputStream();
            BufferedReader br1 = new BufferedReader(new InputStreamReader(is1));
            parametry2 = br1.readLine();
            System.out.println(parametry2);
        }



        if(connection.equals("connected")) {
            String split1[] = parametry2.split("  ");
            for (int i = 0; i < split1.length; i++) {
                String split2[] = split1[i].split("=");
                if (split2[0].equals("czasGry")) czasGry = Integer.parseInt(split2[1]);
                if (split2[0].equals("rodzajCelownika")) {
                    rodzajCelownika = split2[1];
                    celownik = rodzajCelownika.split(" ");
                }
                if (split2[0].equals("szybkosc")) szybkosc = Integer.parseInt(split2[1]);
                if (split2[0].equals("kolorCelownika")) {
                    kolorCelownika = split2[1];
                    kolor2 = kolorCelownika.split(" ");
                }

            }
        }else {

            Properties props = new Properties();
            try (Reader in = new InputStreamReader(new FileInputStream("par2.txt"), "UTF-8")) {
                props.load(in);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            czasGry = Integer.parseInt(props.getProperty("czasGry"));
            rodzajCelownika = props.getProperty("rodzajCelownika");
            celownik = rodzajCelownika.split(" ");
            kolorCelownika = props.getProperty("kolorCelownika");
            kolor2 = kolorCelownika.split(" ");
            szybkosc = Integer.parseInt(props.getProperty("szybkosc"));
        }
    }

    /**
     * metoda słuzaca do wysłania zadania o wyslanie danych z pliku level.text przez serwer oraz wczytaniu ich w przypadku gry online
     * oraz wczytania danych z pliku po stronie klienta w przypadku gry offline
     */
    public static void wczytajZPlikuZLevelami() throws Exception{

        if(connection.equals("connected")) {
            Socket sr = new Socket("localhost", 6777);
            OutputStream os2 = sr.getOutputStream();
            PrintWriter pw2 = new PrintWriter(os2, true);
            pw2.println("Get lvl config");

            InputStream is2 = sr.getInputStream();
            BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
            parametry3 = br2.readLine();
            System.out.println(parametry3);
        }

        if(connection.equals("connected")) {
            String split3[] = parametry3.split("  ");
            levels1 = new int[liczbaPoziomow];
            levels2 = new int[liczbaPoziomow];

            for (int i = 0; i < split3.length; i++) {
                String split4[] = split3[i].split("=");
                for (int j = 0; j < liczbaPoziomow; j++) {
                    if (split4[0].equals("IloscOdejmowanychPunktowZaNieTrafienie" + j))
                        levels1[j] = Integer.parseInt(split4[1]);
                    if (split4[0].equals("IloscPotrzebnychPunktow" + j)) levels2[j] = Integer.parseInt(split4[1]);
                }
            }
        }else {

            Properties props = new Properties();
            try (Reader in = new InputStreamReader(new FileInputStream("level.text"), "UTF-8")) {
                props.load(in);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            levels1 = new int[liczbaPoziomow];
            levels2 = new int[liczbaPoziomow];
            for (int i = 0; i < liczbaPoziomow; i++) {
                levels1[i] = Integer.parseInt(props.getProperty("IloscOdejmowanychPunktowZaNieTrafienie" + i));
                levels2[i] = Integer.parseInt(props.getProperty("IloscPotrzebnychPunktow" + i));
            }
        }
    }

    /**
     * metoda słuzaca do wysłania zadania o wyslanie danych z pliku wyniki.text przez serwer oraz wczytaniu ich w przypadku gry online
     * oraz wczytania danych z pliku po stronie klienta w przypadku gry offline
     */
    public static void wczytajWyniki() throws Exception {

        if(connection.equals("connected")) {
            Socket sr = new Socket("localhost", 6777);
            OutputStream os2 = sr.getOutputStream();
            PrintWriter pw2 = new PrintWriter(os2, true);
            pw2.println("Get score file");

            InputStream is3 = sr.getInputStream();
            BufferedReader br3 = new BufferedReader(new InputStreamReader(is3));
            parametry4 = br3.readLine();
            System.out.println(parametry4);
        }

        if (connection.equals("connected")) {
            String split4[] = parametry4.split("  ");
            for (int i = 0; i < split4.length; i++) {
                String split2[] = split4[i].split(" ");
                scores.add(new Score(split2[0], Integer.parseInt(split2[1]), Integer.parseInt(split2[2])));
            }
        } else {
            File file = new File("wyniki.txt");
            Scanner sc = null;
            try {
                sc = new Scanner(file);
            } catch (FileNotFoundException e) {
                System.out.println("BLAD PRZY OTWIERANIU PLIKU!");
                System.exit(1);
            }

            while (sc.hasNext()) {
                Score score = new Score(sc.next(), sc.nextInt(), sc.nextInt());
                scores.add(score);
            }


        }
    }
}