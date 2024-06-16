# language: de
Funktionalität: Das System muss gewährleisten, dass bei der Aktion „Bewegen“ ein Froschstein mehrmals springen darf, aber nicht so dass dieser an der Ursprungsposition bleibt.

ÄNDERN

  Szenario: : Rick überprüft während des Spiels wie ein Zug ausgeführt wird
  (Testfall: 22)
    Angenommen das Spiel hat angefangen und es wurde ein Zug gespielt
    Und ein Zug wird gespielt
    Wenn die Aktion "Anlegen" wird ausgeführt
    Und der Spieler der am Zug ist hat mindestens einen Froschstein im Inventar
    Und mindestens einer kann gelegt werden
    Dann wird ein Froschstein gelegt