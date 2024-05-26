# language: de
Funktionalität:



  Szenario: : Rick überprüft am Anfang des Spiels wie viele Froschsteine jeder besitzt
  (Testfall: 14)
    //Angenommen die Spielreihenfolge wurde festgelegt
    Wenn der Beutelinhalt abgefragt wird
    Dann sind <Anzahl> Froschsteine im Beutel
    Und zwei Froschsteine im Inventar von Spieler <Spieler>
