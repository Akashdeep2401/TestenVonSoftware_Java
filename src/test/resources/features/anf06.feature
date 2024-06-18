# language: de
Funktionalität: AoF-L-06: Das System muss gewährleisten, dass der Beutel am Anfang des Spiels
  (10 * Anzahl der Spieler) Froschsteine enthält.


  Szenariogrundriss: : Rick überprüft vor dem Spiel den Beutelinhalt
  (Testfall: 6)
    Angenommen alle <Spieler> Spieler haben eine Farbe ausgewählt
    Wenn der Beutel befüllt wurde
    Dann sind <Anzahl> Frösche im Beutel

    Beispiele:
      | Spieler | Anzahl |
      | 2       | 20     |
      | 3       | 30     |
      | 4       | 40     |