# language: de
Funktionalität: AoF-L-13: Das System muss gewährleisten,
  dass jeder Spieler maximal 2 Froschsteine besitzen darf.


 Szenariogrundriss: : Rick überprüft wie viele Froschsteine jeder besitzt
  (Testfall: 13)
    Angenommen das Spiel läuft
    Wenn das Inventar des Spielers <Spieler> abgefragt wird
    Dann hat Spieler <Spieler> maximal zwei Froschsteine

   Beispiele:
     | Spieler |
     | 1       |
     | 2       |
     | 3       |
     | 4       |