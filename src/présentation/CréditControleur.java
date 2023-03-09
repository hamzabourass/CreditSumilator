package présentation;
import lombok.*;
import métier.CréditMétier;
import métier.IMétier;

@Data @AllArgsConstructor @NoArgsConstructor

public class CréditControleur implements ICréditControleur {
    IMétier creditMetier;
    //CréditMétier creditMetier;
    public void afficher_Mensualité(Long idCrédit) throws Exception {

        var creditAvecMensualité = creditMetier.calculer_Mensualité(idCrédit);
        System.out.println((creditAvecMensualité));
    }

}
