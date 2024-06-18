# language: de
Funktionalität: AoF-L-15: Das System muss gewährleisten, dass ein Zug aus 3 Aktionen besteht,
  die in einer festen Reihenfolge ausgeführt werden müssen, wenn diese möglich sind.



  Szenario: : Rick überprüft während des Spiels wie ein Zug ausgeführt wird
  (Testfall: 15)
    Angenommen das Spiel läuft
    Und ein Zug wird gespielt
    Und die Aktion "Bewegen" ist nicht gespielt worden
    Wenn der Zug abgefragt wird
    Dann ist die Aktion "Anlegen" nicht gespielt worden
    Und die Aktion "Nachziehen" nicht gespielt worden