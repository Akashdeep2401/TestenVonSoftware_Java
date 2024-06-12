# language: de
Funktionalität: Das System muss gewährleisten, dass bei der Aktion „Bewegen“
  ein Froschstein der bewegt wird mindestens einen anderen Froschstein überspringt.


  Szenario: : Rick überprüft während des Spiels wie ein Zug ausgeführt wird
  (Testfall: 21)
    Angenommen das Spiel hat angefangen und es wurden 10 Züge gespielt
    Und ein Zug wird gespielt
    Und die Aktion "Bewegen" wird ausgeführt
    Wenn der Froschstein bewegt wird
    Dann darf der Froschstein nicht an der selben Position wie am Anfang des Zuges sein