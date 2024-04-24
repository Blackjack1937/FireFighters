# FireFighters 

## Aperçu
Le projet FireFighters est un simulateur d'automate cellulaire développé en Java. Il simule des scénarios impliquant 
des pompiers combattant des incendies dans divers environnements, en intégrant des obstacles comme les montagnes et 
les routes. Ce projet est une démonstration pratique des principes de programmation orientée objet et de la conception 
SOLID, permettant une simulation flexible et extensible.

## Objectif :
Le but de ce projet est d'implémenter un proto-simulateur d'automate cellulaire. On commencera par une
execution sur une grille où les éléments seront des pompiers et des feux. A chaque tour, chaque pompier
éteindra soit tous les feux autour de lui, soit se dirigera vers le feu le plus proche et éteindra ensuite le feu autour de lui. Le feu de son côté s'étendra autour de lui tous les deux tours. Le but est de voir en combien de tours les pompiers arriveront à éteindre le feu.

Le but principal de ce projet est de rendre du code qui respecte les principes SOLID.

- <strong>Tâche 1 : </strong><br>

#### FireFighters:
Le modèle principal constitué des règles suivantes :

- Des nuages qui se déplacent aléatoirement et qui éteignent les feux.
- Des pompiers motorisés qui peuvent se déplacer de deux cases.
- Des cases montagnes qui sont infranchissables par le feu et les pompiers.
- Des cases routes qui ne sont franchissables que par les pompiers.
- Des cases rocailles sur lesquelles le feu mets quatre tours à se propager.
  <br><br>

- <strong>Tâche 2 : </strong><br>

#### Plague :
Implémentation d'un autre modèle, le Virus-Population-Médecin, avec d'autres règles.

- Des médecins qui se dirigent vers les virus et les éradiquent.
- Des virologistes qui avancent de deux cases et qui éradiquent le virus.
- Des virus qui se propagent chaque double-tour.
- Des personnes infectées qui se déplacent aléatoirement et qui contaminent les personnes vaccinées.
- Des personnes vaccinées qui ne se déplacent pas et qui ne peuvent pas être contaminées par le virus.

## Structure du Projet 

Le diagramme de structure du projet est disponible sous forme de fichier .svg du nom "App_structure"

### Packages et Classes

#### 1. App.java
- Le point d'entrée principal de l'application. Cette classe est responsable de l'initialisation de l'environnement de simulation et de la gestion du flux de contrôle principal.

#### 2. Package : `firefighters`
- Ce package se concentre sur le simulateur de lutte contre les incendies.
 - **entities** : Contient des classes représentant les entités (pompiers, feux, etc.) dans le simulateur. Chaque entité a ses propres caractéristiques et comportements dans la simulation.
 - **obstacles** : Gère les obstacles comme les montagnes, qui sont infranchissables, et les routes, qui sont spécifiques aux déplacements des pompiers.
 - **simulatorcontrol** : Contrôle la logique de la simulation, en gérant les états et les transitions d'état, telles que l'extinction des feux et le déplacement des pompiers.

#### 3. Package : `framework`
- Fournit une base et des interfaces pour le projet.
 - **abstracts** : Contient des classes abstraites qui définissent des modèles de base pour d'autres classes spécifiques du projet.
 - **interfaces** : Définit des interfaces qui établissent des contrats pour diverses parties du simulateur, assurant une cohérence et une standardisation du code.

#### 4. Package : `graphics`
- S'occupe de la représentation graphique de la simulation.
 - **FireFighterPainter.java, GridPainter.java, Painter.java, PlaguePainter.java** : Ces classes sont responsables du rendu graphique des différents éléments du simulateur, comme les pompiers, la grille de simulation, etc.
 - **icons** : Un dossier pour les ressources graphiques comme les icônes et les images, utilisé pour améliorer l'interface utilisateur.

#### 5. Package : `plague`
- Représente un autre modèle de simulateur, le modèle Virus-Population-Médecin, basé sur le modèle passé.
 - **entities** : Gère les entités dans ce modèle, en définissant leurs caractéristiques et comportements.
 - **simulatorcontrol** : Contrôle la simulation pour ce modèle, similaire à la gestion dans le package `firefighters`.

#### 6. Package : `position`
- **Position.java** : Gère les positions des entités sur la grille. Cette classe est cruciale pour le positionnement et le mouvement des entités dans l'espace de la grille.

## Utilisation

Pour exécuter le simulateur, lancez la classe `App.java`. Au démarrage, vous pouvez paramétrer pour choisir entre les scénarios `firefighter` et `plague`, en fonction de la simulation que vous souhaitez exécuter.

### Détails Techniques
- **Version Java** : Java 17
- **Gradle** : Gradle 7.4
- **IDE** : IntelliJ IDEA 2023.2.5
- **Modules** : JavaFX 21

### Principes SOLID
Le projet est conçu en suivant les principes SOLID de la programmation orientée objet pour assurer une bonne maintenance et évolutivité :
- **S - Single Responsibility Principle** : Chaque classe a une seule responsabilité et un seul motif de changement.
- **O - Open/Closed Principle** : Les classes sont ouvertes à l'extension, mais fermées à la modification.
- **L - Liskov Substitution Principle** : Les objets de classes dérivées peuvent remplacer des objets de classes de base sans affecter la correctitude du programme.
- **I - Interface Segregation Principle** : De nombreuses interfaces spécifiques sont meilleures qu'une interface unique et générale.
- **D - Dependency Inversion Principle** : Les modules de haut niveau ne doivent pas dépendre des modules de bas niveau. Les deux devraient dépendre des abstractions.



## Licence

Ce projet est sous licence Apache 2.0. Pour plus de détails, veuillez consulter le fichier LICENSE ou visiter [Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0).



