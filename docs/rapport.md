# Labo 8 - Jeu d'échecs

- Groupe : L08GrJ
- Etudiants : Calum Quinn, Dylan Ramos

## Diagramme de classes

![img.png](img.png)

## Choix d'imlémentation

- Les classes `Pawn`, `Rook` et `King` ont une méthode `hasMoved` qui permet de savoir si la pièce a déjà bougé. Cela
  permet de savoir si le roque ou la prise en passant sont possibles ou non.
- Nous avons créé une classe `Position` afin de pouvoir manipuler plus facilement les coordonnées des pièces. D'autre
  part cela permet d'utiliser un `HashMap` pour stocker les pièces du jeu.
- La cardinalité de 0..32 sur `Piece` nous permet de créer un `Board` sans pièces et rappelle le nombre de pièces
  maximum dans un jeu d'échecs.
- La cardinalité de 1 entre `Piece` et `Position` indique qu'une pièce à forcément une position et donc que lorsqu'elle
  est prise, elle est supprimée du plateau. Les pièces supprimées n'ont pas besoin d'être stockées car un pion promu
  peut prendre n'importe quel `PieceType`.
- L'attribut `SIZE` de `Board` est constant et permet de définir la taille du plateau de jeu qui est de 8x8. De plus,
  cet attribut est statique car il est commun à toutes les instances de `Board`.
