# language: de
Funktionalität: (25)Das System muss gewährleisten, dass bei der Aktion „Bewegen“ keine weitere Insel aus Froschsteinen entsteht.


  Szenario: : Rick überprüft während des Spiels wie ein Zug ausgeführt wird
  (Testfall: 26)
    Angenommen das Spiel hat angefangen und es wurden 11 Züge gespielt
    Und "Grün" ist am Zug
    Und ein Zug wird gespielt
    Wenn die Aktion "Bewegen" ausgeführt wird
    Und der Froschstein so bewegt wird, dass eine weitere Insel aus Froschsteinen entsteht
    Dann wird der Froschstein "nicht" bewegt