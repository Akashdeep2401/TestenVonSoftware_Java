# language: de
Funktionalität: AoF-L-14: Das System muss gewährleisten, dass jeder Spieler einmal
  am Anfang nacheinander 2 Froschsteine vom Beutel zufällig herausnehmen muss..


  Szenariogrundriss: : Rick überprüft am Anfang des Spiels wie viele Froschsteine jeder besitzt
  (Testfall: 14)
    Angenommen das Spiel versucht mit <Spieler> Spielern zu starten
    Dann sind <Frösche> Frösche im Beutel
    Und 2 Froschsteine im Inventar von jedem Spieler
    Und 2 Froschsteine von jeden Spieler werden in der GUI angezeigt

    Beispiele:

      | Spieler | Frösche |
      | 2       | 16      |
      | 3       | 24      |
      | 4       | 32      |