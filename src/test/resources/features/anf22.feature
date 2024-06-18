# language: de
Funktionalität: Das System muss gewährleisten, dass bei der Aktion „Bewegen“ ein Froschstein mehrmals springen darf, aber nicht so dass dieser an der Ursprungsposition bleibt.

  Szenariogrundriss: : Rick überprüft während des Spiels wie ein Zug ausgeführt wird
  (Testfall: 22)
    Angenommen das Spiel hat angefangen und es wurden 11 Züge gespielt
    Und "Grün" ist am Zug
    Und ein Zug wird gespielt
    Wenn die Aktion "Bewegen" ausgeführt wird
    Und dabei der Froschstein mehrmals springt
    Und dieser <nicht> wieder an der Ursprungsposition ist
    Dann wird der Froschstein <nicht> bewegt

  Beispiele:
        | nicht | nicht|
        | ""    | "nicht"   |
        | "nicht"  |   ""  |