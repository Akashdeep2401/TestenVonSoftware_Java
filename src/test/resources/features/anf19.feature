# language: de
Funktionalität: (19)Das System muss gewährleisten, dass bei der Aktion „Bewegen“ ein Froschstein der bewegt wird, in grader Linie bewegt wird.


  Szenariogrundriss: : Rick überprüft während des Spiels wie ein Zug ausgeführt wird //    Hier fragen
  (Testfall: 20)
    Angenommen das Spiel hat angefangen und es wurden 11 Züge gespielt
    Und "Blau" ist am Zug
    Und ein Zug wird gespielt
    Wenn die Aktion "Bewegen" ausgeführt wird
    Und der Froschstein <nicht> versucht wird in gerade Linie zu bewegen
    Dann wird der Froschstein <nicht> bewegt

  Beispiele:
    | nicht  |  nicht  |
    | ""     |    ""   |
    | "nicht"| "nicht" |