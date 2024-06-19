# language: de
Funktionalität: (22)Das System muss gewährleisten, dass bei der Aktion „Anlegen“ ein Froschstein im Besitz des Spielers gelegt werden muss.


  Szenario: : Rick überprüft während des Spiels wie ein Zug ausgeführt wird
  (Testfall: 23)
    Angenommen das Spiel hat angefangen und es wurde ein Zug gespielt
    Wenn die Aktion "Anlegen" ausgeführt wird
    Und der Spieler der am Zug ist hat mindestens einen Froschstein im Inventar
    Und mindestens einer kann gelegt werden
    Dann wird ein Froschstein gelegt