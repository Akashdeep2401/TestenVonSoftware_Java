# language: de
Funktionalität: Das System muss gewährleisten, dass die Spieler in der Spielreihenfolge
  ihre Züge machen.


  Szenariogrundriss: : Rick überprüft wer gerade am Zug ist
  (Testfall: 12)
    Angenommen das Spiel läuft
    Wenn Spieler <Spieler1> seinen Zug beendet hat
    Dann ist Spieler <Spieler2> am Zug

    Beispiele:
      | Spieler1 | Spieler2 |
      | 3        | 4        |
      | 2        | 3        |
      | 1        | 2        |
      | 4        | 1        |


  Szenariogrundriss: : Rick überprüft wer gerade nicht am Zug ist
  (Testfall: 12.1)
    Angenommen das Spiel läuft
    Wenn Spieler <Spieler> seinen Zug beendet hat
    Dann ist Spieler <Spieler> nicht am Zug

    Beispiele:
      | Spieler | Spieler |
      | 3       | 1       |
      | 2       | 1       |
      | 1       | 1       |
      | 4       | 2       |
      | 3       | 2       |
      | 2       | 2       |
      | 4       | 3       |
      | 3       | 3       |
      | 1       | 3       |
      | 4       | 4       |
      | 2       | 4       |
      | 1       | 4       |