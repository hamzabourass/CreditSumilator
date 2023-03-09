package métier;
import dao.CréditDao;
import dao.IDao;
import lombok.*;
import modèle.Crédit;
@Data @AllArgsConstructor @NoArgsConstructor

public class CréditMétier implements IMétier {
    IDao<Crédit, Long> creditDao;
    //CréditDao creditDao;
    public Crédit calculer_Mensualité(Long idCrédit) throws Exception {
        var crédit = creditDao.trouverParId(idCrédit);

        if(crédit == null)
            throw new Exception("L'id du credit est incorrecte :: [Crédit non trouvé]");
        else{
             double taux        = crédit.getHamtaux_Mensuel();
                    taux        = taux/1200;
             double capitale    = crédit.getHamcapitale_Empunté();
             int nbr_Mois       = crédit.getHamnombre_Mois();
             double mensualité  = (capitale * taux) / (1 - (Math.pow((1+taux),-1*nbr_Mois)));
                    mensualité  =Math.round((mensualité*100)/100);

                crédit.setHammensualité(mensualité);
          return crédit;
        }
    }
}
