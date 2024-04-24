package framework.interfaces;

/** Interface pour les éléments dans le proto-simulateur.*/
public interface Elements {

     /**
      * Méthode pour initialiser l'élément avec un nombre spécifié d'instances.
      *
      * @param number Le nombre d'instances de cet élément à initialiser.
      */
     void initialisation(int number);

     /** Méthode pour initialiser l'élément. */
     void activation();
}
