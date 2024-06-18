# language: de
Funktionalität: AoF-L-26: Das System muss gewährleisten,
  dass bei der Aktion „Bewegen“ keine weitere Insel aus Froschsteinen entsteht.



  Szenario: : Rick überprüft während des Spiels wie ein Zug ausgeführt wird
  (Testfall: 26)
    Angenommen das Spiel läuft
    Und ein Zug wird gespielt
    Und die Aktion "Bewegen" ist ausgeführt worden
    Wenn der Zug abgefragt wird
    Dann ist keine weitere Insel aus Froschsteinen gebildet worden