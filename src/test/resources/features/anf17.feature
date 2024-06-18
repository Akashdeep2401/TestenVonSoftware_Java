# language: de
Funktionalität: AoF-L-17: Das System muss gewährleisten, dass die 3 Aktionen in dieser
  Reihenfolge ausgeführt werden müssen: 1. Bewegen, 2. Anlegen , 3. Nachziehen.


  Szenario: : Rick überprüft während des Spiels wie ein Zug ausgeführt wird
  (Testfall: 17)
    Angenommen das Spiel läuft
    Und ein Zug wird gespielt
    Und die Aktion "Bewegen" ist gespielt worden
    Und die Aktion "Anlegen" ist gespielt worden
    Wenn der Zug abgefragt wird
    Dann ist die Aktion "Nachziehen" nicht gespielt worden