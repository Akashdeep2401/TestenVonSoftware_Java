# language: de
Funktionalität:



  Szenario: : Rick überprüft während des Spiels wie ein Zug ausgeführt wird
  (Testfall: 17)
    Angenommen das Spiel läuft
    Und ein Zug wird gespielt
    Und die Aktion "Bewegen" ist gespielt worden
    Und die Aktion "Anlegen" ist gespielt worden
    Wenn der Zug abgefragt wird
    Dann ist die Aktion "Nachziehen" nicht gespielt worden