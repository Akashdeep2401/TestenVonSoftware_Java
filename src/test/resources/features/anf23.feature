# language: de
Funktionalität: (23)Das System muss gewährleisten, dass bei der Aktion „Nachziehen“ ein Froschstein zufällig aus dem Beutel genommen werden muss.


  Szenario: : Rick überprüft während des Spiels wie ein Zug ausgeführt wird
  (Testfall: 24)
    Angenommen das Spiel hat angefangen und es wurde ein Zug gespielt
    Und ein Zug wird gespielt
    Wenn die Aktion "Nachziehen" ausgeführt wird
    Und der Spieler der am Zug ist höchstens einen Froschstein im Inventar hat
    Dann wird ein Froschstein zufällig aus dem Beutel genommen