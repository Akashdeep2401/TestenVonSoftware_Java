# language: de
Funktionalität:



  Szenario: : Rick überprüft während des Spiels wie ein Zug ausgeführt wird
  (Testfall: 16)
    Angenommen das Spiel läuft
    Und ein Zug wird gespielt
    Und die Aktion "Anlegen" ist nicht gespielt worden
    Und die Aktion "Bewegen" ist nicht gespielt worden
    Wenn der Zug abgefragt wird
    Dann hat kein Spieler die Aktion "Nachziehen" gespielt // Hier Fragen