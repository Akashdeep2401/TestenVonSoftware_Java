# language: de
Funktionalität: (20)Das System muss gewährleisten, dass bei der Aktion „Bewegen“
  ein Froschstein der bewegt wird mindestens einen anderen Froschstein überspringt.


 Szenariogrundriss: : Rick überprüft während des Spiels wie ein Zug ausgeführt wird
  (Testfall: 21)
    Angenommen das Spiel hat angefangen und es wurden 11 Züge gespielt
    Und "Weiß" ist am Zug
    Und ein Zug wird gespielt
    Wenn die Aktion "Bewegen" ausgeführt wird
    Und der Froschstein <nicht> versucht wird über einen anderen Froschstein springen zu lassen
    Dann wird der Froschstein <nicht> bewegt

  Beispiele:
        | nicht | nicht|
        | ""    | ""   |
        | "nicht"  |   "nicht"  |