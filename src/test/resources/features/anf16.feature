# language: de
Funktionalität: AoF-L-16: Das System muss gewährleisten,
  dass wenn eine Aktion nicht ausgeführt werden kann, die nächste Aktion ausgeführt wird.


  Szenario: : Rick überprüft während des Spiels wie ein Zug ausgeführt wird
  (Testfall: 16)
    Angenommen das Spiel läuft
    Und ein Zug wird gespielt
    Und die Aktion "Anlegen" ist nicht gespielt worden
    Und die Aktion "Bewegen" ist nicht gespielt worden
    Wenn der Zug abgefragt wird
    Dann hat kein Spieler die Aktion "Nachziehen" gespielt