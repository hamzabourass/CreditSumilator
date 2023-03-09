package métier;

import modèle.Crédit;

public interface IMétier {
    Crédit calculer_Mensualité(Long idCrédit)
                                          throws Exception;
}
