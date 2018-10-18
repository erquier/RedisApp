/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis;

import com.redis.entitys.Actor;
import com.redis.entitys.Film;
import com.redis.entitys.Rental;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.json.JSONObject;
import redis.clients.jedis.Jedis;

/**
 *
 * @author Fred Pena -- fantpena@gmail.com
 */
public class AppHib {

    static int count=0;
    private static SessionFactory factory;

    public static void main(String[] args) {

        ArrayList<String> NicholasBarfieldRentalHistory = new ArrayList<String>();

        ArrayList<String> HasheshOrder = new ArrayList<String>();

        ArrayList<String> AnnetteOlsonRentalHistory = new ArrayList<String>();


        //insertJsonFromMySQL();
        Jedis jedis = new Jedis("localhost");
        jedis.select(0);
//        //1
//        System.out.println("descripcion: "+ jedis.hget("BEACH_HEARTBREAKERS", "Description"));
//        //2
//        jedis.keys("*").stream().forEach(obj -> {
//           // System.out.println("No devueltas: " + jedis.hget(obj, "ReturnDate"));
//
//            if(jedis.hget(obj, "ReturnDate") == null){
//                count++;
//
//            }
//
//        });
//        System.out.println("cantidad: "+ count);
//        //3)
//        System.out.println("Dia de renta: " + jedis.hget("TIGHTS_DAWN", "RentalDate"));
//        //4)
//        jedis.keys("*").stream().forEach(obj ->{
//
//            if(Double.parseDouble(jedis.hget(obj,"RentalRate").toString()) >= 4.5){
//                count++;
//
//            }
//        });
//        System.out.println(count);
//
//        5)
//        jedis.keys("*").stream().forEach(obj ->{
//            //System.out.println(jedis.hget("Trailer","SpecialFeatures"));
//            System.out.println(jedis.hget(obj,"Actor"));
//
//            if(jedis.hget(obj,"Actor")!= null
//                    && jedis.hget(obj,"SpecialFeatures").equals("Trailers")
//                    && jedis.hget(obj, "Actor").contains("ED CHASE")){
//                count++;
//
//            }
//        });
//        System.out.println(count);
//
//        6)
//        jedis.keys("*").stream().forEach(obj ->{
//            if(jedis.hget(obj,"Rating").equals("R")){
//                count++;
//            }
//        });
//        System.out.println(count);
//
//        7)
//        jedis.keys("*").stream().forEach(obj ->{
//            if(jedis.hget(obj,"Customer")!= null
//                    && jedis.hget(obj,"Customer").contains("NICHOLAS BARFIELD")){
//
//                NicholasBarfieldRentalHistory.add(jedis.hget(obj,"Title") + "  Rental Date: "+ jedis.hget(obj,"RentalDate"));
//
//                System.out.println(jedis.hget(obj,"Title") + "  Rental Date: "+ jedis.hget(obj,"RentalDate"));
//            }
//        });
//
//
//        8)
//        jedis.keys("*").stream().forEach(obj ->{
//
//            HasheshOrder.add(jedis.hget(obj,"Title") +"  Release Year: "+ jedis.hget(obj,"ReleaseYear"));
//            count++;
//
//            System.out.println(jedis.hget(obj,"Title") +"  Release Year: "+ jedis.hget(obj,"ReleaseYear"));
//            System.out.println(count);
//
//       });



//        9)

//        jedis.keys("*").stream().forEach(obj ->{
//
//            if(  jedis.hget(obj, "Customer") != null &&
//                    jedis.hget(obj, "Customer").contains("ANNETTE OLSON")){
//                AnnetteOlsonRentalHistory.add(jedis.hget(obj,"Title") +
//                        "  Rental Date: "+ jedis.hget(obj,"RentalDate")+
//                        "  Return Date: "+ jedis.hget(obj,"ReturnDate"));
//            }
//
//            System.out.println("El titulo: " + " " + jedis.hget(obj,"Title") +
//                    "Rento en:  "+ jedis.hget(obj, "RentalDate") +
//                    " "+ "Devolvio en:  "+" "+ jedis.hget(obj, "ReturnDate"));
//        });

//        10)

//        jedis.keys("*").stream().forEach(obj ->{
//            if(jedis.hget(obj, "Staff") != null
//                    && jedis.hget(obj, "Staff").contains("Mike Hillyer")
//                    && jedis.hget(obj, "Language").equals("English")){
//
//                count++;
//            }
//        });
//        System.out.println("El staff tiene: " + count + "en ingles!");


//        13)
//        jedis.keys("*").stream().forEach(obj ->{
//            if(jedis.hget(obj, "RentalDate") == null){
//                count++;
//            }
//
//        });
//        System.out.println("Cuantas pelis no se han rentado?: " + count);

//        14)
//        jedis.keys("*").stream().forEach(obj ->{
//
//            jedis.del("JEOPARDY_ENCINO", "MOURNING_PURPLE", "FRONTIER_CABIN", "GONE_TROUBLE");
//
//            if(jedis.hget(obj,"SpecialFeatures").equals("Trailers")){
//                count++;
//            }
//
//        });
//        System.out.println("Peliculas restantes en SpecialFeatures: " + count);

//        15)
//        jedis.keys("*").stream().forEach(obj ->{
//            if(jedis.hget(obj,"SpecialFeatures").equals("Commentaries")){
//                count++;
//            }
//
//        });
//        System.out.println("Special Features consta de: " + count + " comentarios!");




//        insertRedisFromjson();
        System.exit(0);

    }

    private static void insertRedisFromjson() {
        Jedis jedis = new Jedis("localhost");
        String path = System.getProperty("user.dir") + File.separator + "data" + File.separator + "data.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            JSONObject parser = new JSONObject(br.readLine());

            parser.toMap().entrySet().forEach((entry) -> {
                String key = entry.getKey();
                Object value = entry.getValue();

                jedis.hmset(key, (Map<String, String>) value);
            });

        } catch (FileNotFoundException ex) {
            Logger.getLogger(AppHib.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AppHib.class.getName()).log(Level.SEVERE, null, ex);
        }



    }

    /*
    private static void insertJsonFromMySQL() {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Session session = factory.openSession();

        List<Film> films = session.createQuery("FROM Film").list();

        JSONObject obj = new JSONObject();
        films.stream()//.filter((fil) -> fil.getFilmId() < 10)
                .forEach(film -> {

                    Map<String, String> map = new HashMap<>();
                    map.put("Title", film.getTitle().toLowerCase());
                    map.put("Description", film.getDescription());
                    map.put("ReleaseYear", film.getReleaseYear().toString());
                    map.put("Language", film.getLanguageId().getName());
                    map.put("RentalRate", film.getRentalRate().toString());
                    map.put("Length", film.getLength().toString());
                    map.put("Rating", film.getRating());
                    map.put("SpecialFeatures", film.getSpecialFeatures());

                    if (!film.getFilmActorList().isEmpty()) {
                        Actor actor = film.getFilmActorList().stream().findFirst().get().getActor();
                        map.put("Actor", actor.getFirstName() + " " + actor.getLastName());
                    }

                    if (!film.getInventoryList().isEmpty()) {
                        Rental rental = film.getInventoryList().stream().findFirst().get().getRentalList().stream().findFirst().get();
                        map.put("Customer", rental.getCustomerId().getFirstName() + " " + rental.getCustomerId().getLastName());
                        map.put("RentalDate", rental.getRentalDate().toString());
                        map.put("ReturnDate", rental.getReturnDate() != null ? rental.getReturnDate().toString() : "");
                        map.put("Staff", rental.getStaffId().getFirstName() + " " + rental.getStaffId().getLastName());
                    }

                    obj.put(film.getTitle().replace(" ", "_"), map);

                }
                );
        //  System.out.println("obj.toString(): "+obj.toString());
        //try -with - resources statement based on post comment below :)
        String path = System.getProperty("user.dir") + File.separator + "data" + File.separator + "data.txt";
        try (FileWriter file = new FileWriter(path)) {
            file.write(obj.toString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + obj);
        } catch (IOException ex) {
            Logger.getLogger(AppHib.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

}
