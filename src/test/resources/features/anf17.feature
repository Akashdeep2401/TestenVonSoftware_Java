# language: de
Funktionalität: AoF-L-17: Das System muss gewährleisten, dass die 3 Aktionen in dieser
  Reihenfolge ausgeführt werden müssen: 1. Bewegen, 2. Anlegen , 3. Nachziehen.


  Szenario: : Rick überprüft während des Spiels wie ein Zug ausgeführt wird
  (Testfall: 17)
    Angenommen das Spiel hat angefangen und es wurden 11 Züge gespielt
    Wenn die Aktion "Bewegen" ausgeführt wird
    Und "Grün" ist am Zug
    Wenn der Zug abgefragt wird
    Dann ist die Aktion "Anlegen" nicht gespielt worden