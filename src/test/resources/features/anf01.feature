# language: de
Funktionalität: Das System muss gewährleisten, dass ein Spiel 2 bis 4 Spieler hat.

  Szenariogrundriss: : Das System zählt die Spieler am Anfang des Spiels
  (Testfall: 1)
    Angenommen das Spiel versucht mit <Spieler> Spielern zu starten
    Wenn das System die Spieler zählt
    Dann wird das Spiel <nicht> gestartet

    Beispiele:
      |Spieler| nicht|
      |1      | 0    |
      |2      | 1    |
      |3      | 1    |
      |4      | 1    |
      |5      | 0    |