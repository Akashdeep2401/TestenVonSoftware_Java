# language: de
Funktionalität: AoF-L-19: 19.	Das System muss gewährleisten,
  dass bei der Aktion „Bewegen“ ein Froschstein der Farbe des Spielers,
  der gerade am Zug ist, bewegt werden muss.

Szenario: : Rick überprüft während des Spiels wie ein Zug ausgeführt wird
(Testfall: 19)
  Angenommen das Spiel läuft
  Und ein Zug wird gespielt
  Und die Aktion "Bewegen" wird ausgeführt
  Und der Froschstein der Farbe <Farbe> des Spielers <Spieler> kann bewegt werden
  Wenn der Zug abgefragt wird
  Dann wird der Froschstein bewegt