# Contraintes:
- Un groupe a forcément 4 équipes (DONE)
- Un user a forcément un rôle et un nom (DONE)
- On ne peut pas placer un pronostic sur un match déjà joué(DONE)
- On ne peut lancer un match qu'en étant admin (Logic done but not implemented)
- On ne peut changer le score d'un match qu'en étant admin (NOT DONE)
- On ne peut placer un pronostic qu'en étant user (DONE)


## On doit pouvoir (via appel rest):
 - CRUD groupes, équipes… (DONE)
 - Placer des pronostics avec différents users (DONE)
 - Faire jouer les matchs et obtenir une réponse représentant les différents matchs avec leur résultats (idem niveau groupe) (NOT DONE)
- Récupérer les pronostics corrects (Logic should be done but not implemented) 
- changer le score d'un match joué (DONE via put request)
- obtenir les compositions d’équipe (NOT DONE)
- récupérer le classement final de chaque groupe (NOT DONE)
- récupérer une liste classée des meilleurs pronostiqueurs (DONE)
