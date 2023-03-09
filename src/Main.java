import dao.*;
import modèle.*;
import métier.CréditMétier;
import métier.IMétier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import présentation.*;
import présentation.ICréditControleur;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;
public class Main {
    static ICréditControleur creditControleur;
    static Scanner clavier = new Scanner(System.in);
    private static boolean estUnNombre(String Input){
        try                  { Integer.parseInt(Input); return true; }
        catch (Exception e)  { return false;}
    }
    public static void test1(){
        var dao        = new CréditDao();
        var metier     = new CréditMétier();
        var controleur = new CréditControleur();
        metier      .setCreditDao(dao);
        controleur  .setCreditMetier(metier);
        String rep = "";
        do{
            System.out.println("=> [Test 1 ] Calcule de Mensualité de Crédit <=\n");
            try{
                String input ="";
                while (true){
                    System.out.println("=> Entrer l'id du crédit : ");
                    input = clavier.nextLine();
                    if(estUnNombre(input)) break;
                    System.err.println("Entrée non valide !!!");
                }
                long id = Long.parseLong(input);
                controleur.afficher_Mensualité(id);
            }
            catch (Exception e){
                System.err.println(e.getMessage());
            }
            System.out.print("Voulez vous quittez (oui/non) ?"); rep = clavier.nextLine();
        }while (!rep.equalsIgnoreCase("oui"));
        System.out.println("Au revoir :) !");
    }
    public static void test2() throws Exception{
        String daoClass;
        String serviceClass;
        String controllerClass;

        Properties properties = new Properties();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        //InputStream propertiesFile =  classLoader.getResourceAsStream("config.properties");
        InputStream propertiesFile = new FileInputStream("C:\\Users\\MAJD\\Desktop\\CreditSimulator\\config.properties");

        if (propertiesFile == null) throw new Exception("fichier config introuvable !!");
        else{
            try {
                properties.load(propertiesFile);
                daoClass        = properties.getProperty("DAO");
                serviceClass    = properties.getProperty("SERVICE");
                controllerClass = properties.getProperty("CONTROLLER");
                propertiesFile.close();
            }
            catch (IOException e){
                throw new Exception("Probleme de chargement des propriétés du fichier config");}
            finally {properties.clear();}
        }
        try{
            Class cDao          = Class.forName(daoClass);
            Class cMetier       = Class.forName(serviceClass);
            Class cControleur   = Class.forName(controllerClass);

            var dao             = (IDao<Crédit,Long>)        cDao.getDeclaredConstructor().newInstance();
            var metier          = (IMétier)               cMetier.getDeclaredConstructor().newInstance();
            creditControleur= (ICréditControleur) cControleur.getDeclaredConstructor().newInstance();

            Method setDao       = cMetier.getMethod("setCreditDao", IDao.class);
            setDao.invoke(metier, dao);

            Method setMetier    = cControleur.getMethod("setCreditMetier", IMétier.class);
            setMetier.invoke(creditControleur,metier);

            creditControleur.afficher_Mensualité(2L);
        }
        catch (Exception e){ e.printStackTrace();}
    }
    public static void test3() throws Exception{
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-ioc.xml");
        creditControleur = (ICréditControleur) context.getBean("controleur");
        creditControleur.afficher_Mensualité(1L);
    }

    public static void main(String[] args) throws Exception {
        test2();
    }
}