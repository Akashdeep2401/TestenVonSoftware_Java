# language: de
Funktionalität:



  Szenario: : Rick überprüft während des Spiels wie ein Zug ausgeführt wird
  (Testfall: 15)
    Angenommen das Spiel läuft
    Und ein Zug wird gespielt
    Und die Aktion "Bewegen" ist nicht gespielt worden
    Wenn der Zug abgefragt wird
    Dann ist die Aktion "Anlegen" nicht gespielt worden
    Und die Aktion "Nachziehen" nicht gespielt worden