package dao;

import modèle.Crédit;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Stream;
import java.util.Set;
import lombok.*;
public class CréditDao implements IDao<Crédit, Long> {
    public static  Set<Crédit> BDCrédits(){
        return new HashSet<Crédit>(
                Arrays.asList(
                        new Crédit(1L , 300000.0, 120, 2.5, "Amine", 0.0),
                        new Crédit(2L , 850000.0, 240, 2.5, "Simo", 0.0),
                        new Crédit(3L , 020000.0, 30, 1.5, "Youssef", 0.0),
                        new Crédit(4L , 065000.0, 120, 2.0, "Hamza", 0.0)
                ));
    }
    public Crédit trouverParId(Long hamid){
        System.out.println("[DAO - DS volatile] le Crédit n : "+hamid);
        return               BDCrédits()
                                       .stream()
                                       .filter(credit-> credit.getHamid() == hamid)
                                       .findFirst()
                                       .orElse(null);


                                          }
                       }
