# language: de
Funktionalität: AoF-L-18: 18.	Das System muss gewährleisten,
  dass die Spieler ihre Züge in der festgelegten Spielreihenfolge ziehen.



  Szenario: : Rick überprüft während des Spiels wie ein Zug ausgeführt wird
  (Testfall: 18)
    Angenommen das Spiel läuft
    Und ein Zug wird gespielt
    Und eine Aktion versucht wurde auszuführen
    Wenn der Zug abgefragt wird
    Dann wird die nächste Aktion in der Spielreihenfolge ausgeführt