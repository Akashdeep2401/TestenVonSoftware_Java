# language: de
Funktionalität: (24)Das System muss gewährleisten, dass bei der Aktion „Bewegen oder Anlegen“ keine Kette aus vier oder mehr Froschsteinen entsteht.


  Szenario: : Rick überprüft während des Spiels wie ein Zug ausgeführt wird
  (Testfall: 25a)
    Angenommen das Spiel hat angefangen und es wurden 11 Züge gespielt
    Und ein Zug wird gespielt
    Wenn die Aktion "Bewegen" so gespielt wird das eine Kette entstehen würde
    Dann wird der Froschstein nicht bewegt

  Szenario: : Rick überprüft während des Spiels wie ein Zug ausgeführt wird
  (Testfall: 25b)
    Angenommen das Spiel hat angefangen und es wurden 11 Züge gespielt
    Und ein Zug wird gespielt
    Wenn die Aktion "Anlegen" so gespielt wird das eine Kette entstehen würde
    Dann wird der Froschstein nicht angelegt