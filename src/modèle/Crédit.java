package modèle;
import lombok.*;
@Data @AllArgsConstructor @NoArgsConstructor

public class Crédit {
    private Long hamid;
    private Double hamcapitale_Empunté;
    private Integer hamnombre_Mois;
    private Double hamtaux_Mensuel;
    private String hamnom_Demandeur;
    private Double hammensualité;
    public String toString(){


        var créditStr = "==================================================================    \n";
            créditStr+= "=> Crédit n²"+getHamid()+"                                            \n";
            créditStr+= "=> Nom de demandeur de crédit : "+getHamnom_Demandeur()+"             \n";
            créditStr+= "------------------------------------------------------------------    \n";
            créditStr+= "=> Capitale Emprunté          : "+getHamcapitale_Empunté()+"DH        \n";
            créditStr+= "=> Nombre de mois             : "+getHamnombre_Mois()+" mois          \n";
            créditStr+= "=> Taux mensuel               :  "+getHamtaux_Mensuel()+"%            \n";
            créditStr+= "------------------------------------------------------------------    \n";
            créditStr+= "Mensualité                    :"
                   +(getHammensualité()==0.0 ? "NON-CALCULE" : getHammensualité()+" DH/mois")+"\n";
            créditStr += "===================================================================  \n";
        return créditStr;
    }

}
