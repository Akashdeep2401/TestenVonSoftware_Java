# language: de
Funktionalität: AoF-L-240: Das Plugin muss der GUI die Möglichkeit
  bieten die Anzahl der Frösche im Beutel abzufragen.
  Szenario: Kai überprüft vor einem Spiel den Beutelinhalt.
    (Testfall :24-1)
    Angenommen es läuft kein Spiel
    Wenn der Beutelinhalt abgefragt wird
    Dann sind 40 Frösche im Beutel

  Szenariogrundriss: : Rick überprüft während des Spiels den Beutelinhalt
    (Testfall: 24-2)
    Angenommen das Spiel ist mit <Spieler> Spielern gestartet
    Und der erste Spieler hat <erster Spieler> Frösche gezogen
    Und der zweite Spieler hat <zweiter Spieler> Frösche gezogen
    Und der dritte Spieler hat <dritter Spieler> Frösche gezogen
    Und der vierte Spieler hat <vierter Spieler> Frösche gezogen
    Wenn der Beutelinhalt abgefragt wird
    Dann sind <Anzahl> Frösche im Beutel

   Beispiele:
    | Spieler | erster Spieler | zweiter Spieler | dritter Spieler | vierter Spieler | Anzahl |
    |  2      | 2              | 1               | 0               | 0               | 13     |
    |  3      | 2              | 3               | 5               | 0               | 14     |
    |  4      | 10             | 10              | 10              | 0               | 2      |
    |  4      | 10             | 10              | 10              | 10              | 0      |

  Szenariogrundriss: : Das System zählt die Spieler am Anfang des Spiels
  (Testfall: 1)
    Angenommen das Spiel versucht mit <Spieler> Spielern zu starten
    Wenn das System die Spieler zählt
    Dann wird das Spiel <nicht> gestartet

    Beispiele:
      |Spieler| nicht|
      |1      | 0    |
      |2      |    1 |
      |3      | 1   |
      |4      |   1  |
      |5      |   0  |

  Szenario: : Rick überprüft die ausgewählte Farbe
  (Testfall: 2)
    Angenommen ein Spieler hat die Farbe <Farbe> ausgewählt
    Wenn die Farben überprüft werden
    Dann ist die Farbe Blau, Grün, Rot oder Weiß

  Szenario: : Rick überprüft die ausgewählten Farben
  (Testfall: 3)
    Angenommen ein Spieler hat die Farbe <Farbe> ausgewählt
    Wenn die Auswahl überprüft wird
    Dann ist die Farbe nicht eine der vorher ausgewählt Farben

  Szenario: : Rick überprüft die ausgewählten Farben
  (Testfall: 4)
    Angenommen alle Spieler haben eine Farbe ausgewählt
    Wenn die Farben überprüft werden
    Dann hat jeder Spieler eine ihm zugewiesen Farbe

  Szenario: : Rick überprüft vor dem Spiel den Beutel
  (Testfall: 5)
    Angenommen alle Spieler haben eine Farbe ausgewählt
    Wenn der Beutelinhalt abgefragt wird
    Dann sind nur Froschstein im Beutel die Blau, Grün, Rot oder Weiß sind

  Szenario: : Rick überprüft vor dem Spiel den Beutelinhalt
  (Testfall: 6)
    Angenommen alle <Spieler> Spieler haben eine Farbe ausgewählt
    Wenn der Beutelinhalt abgefragt wird
    Dann sind <Anzahl> Frösche im Beutel

  Szenario: : Rick überprüft vor dem Spiel den Beutelinhalt
  (Testfall: 7)
    Angenommen alle Spieler haben eine Farbe ausgewählt
    Wenn der Beutelinhalt abgefragt wird
    Dann sind nur Froschsteine mit den Farben der Spieler im Beutel

  Szenario: : Rick überprüft vor dem Spiel den Beutelinhalt
  (Testfall: 8)
    Angenommen alle Spieler haben eine Farbe ausgewählt
    Und es wurde <Farbe> als Farbe eines Spielers ausgewählt
    Wenn der Beutelinhalt abgefragt wird
    Dann sind 10 <Farbe> Froschsteine im Beutel

  Szenario: : Rick überprüft die Spielreihenfolge
  (Testfall: 9)
    Angenommen der Beutelinhalt wurde überprüft
    Wenn die Reihenfolge abgefragt wird
    Dann ist einer der Spieler ein Startspieler

  Szenario: : Rick überprüft die Spielreihenfolge
  (Testfall: 10)
    Angenommen es gibt einen Startspieler <Spieler>
    Wenn die Spielreihenfolge abgefragt wird
    Dann ist Spieler <Spieler> der erste Spieler in der Spielreihenfolge

  Szenario: : Rick überprüft die Spielreihenfolge
  (Testfall: 11)
    Angenommen die Spielreihenfolge wurde festgelegt
    Wenn die Spielreihenfolge abgefragt wird
    Dann ist jeder Spieler außer der Startspieler der Spielreihenfolge zugewiesen worden

  Szenario: : Rick überprüft wer gerade am Zug ist
  (Testfall: 12)
    Angenommen das Spiel läuft
    Und Spieler <Spieler> war am Zug im letzten Zug
    Wenn die Spielreihenfolge abgefragt wird
    Dann ist Spieler <Spieler> am Zug

  Szenario: : Rick überprüft wie viele Froschsteine jeder besitzt
  (Testfall: 13)
    Angenommen das Programm läuft
    Wenn das Inventar des Spielers <Spieler> abgefragt wird
    Dann hat Spieler <Spieler> maximal zwei Froschsteine

  Szenario: : Rick überprüft am Anfang des Spiels wie viele Froschsteine jeder besitzt
  (Testfall: 14)
    Angenommen die Spielreihenfolge wurde festgelegt
    Wenn der Beutelinhalt abgefragt wird
    Dann sind <Anzahl> Froschsteine im Beutel
    Und zwei Froschsteine im Inventar von Spieler <Spieler>

  Szenario: : Rick überprüft während des Spiels wie ein Zug ausgeführt wird
  (Testfall: 15)
    Angenommen das Spiel läuft
    Und ein Zug wird gespielt
    Und die Aktion "Bewegen" ist nicht gespielt worden
    Wenn der Zug abgefragt wird
    Dann ist die Aktion "Anlegen" nicht gespielt worden
    Und die Aktion "Nachziehen" nicht gespielt worden

  Szenario: : Rick überprüft während des Spiels wie ein Zug ausgeführt wird
  (Testfall: 16)
    Angenommen das Spiel läuft
    Und ein Zug wird gespielt
    Und die Aktion "Anlegen" ist nicht gespielt worden
    Und die Aktion "Bewegen" ist nicht gespielt worden
    Wenn der Zug abgefragt wird
    Dann hat kein Spieler die Aktion "Nachziehen" gespielt // Hier Fragen

  Szenario: : Rick überprüft während des Spiels wie ein Zug ausgeführt wird
  (Testfall: 17)
    Angenommen das Spiel läuft
    Und ein Zug wird gespielt
    Und die Aktion "Bewegen" ist gespielt worden
    Und die Aktion "Anlegen" ist gespielt worden
    Wenn der Zug abgefragt wird
    Dann ist die Aktion "Nachziehen" nicht gespielt worden


  Szenario: : Rick überprüft während des Spiels wie ein Zug ausgeführt wird
  (Testfall: 18)
    Angenommen das Spiel läuft
    Und ein Zug wird gespielt
    Und eine Aktion versucht wurde auszuführen
    Wenn der Zug abgefragt wird
    Dann wird die nächste Aktion in der Spielreihenfolge ausgeführt


  Szenario: : Rick überprüft während des Spiels wie ein Zug ausgeführt wird //    Hier fragen
  (Testfall: 19)
    Angenommen das Spiel läuft
    Und ein Zug wird gespielt
    Und die Aktion "Bewegen" wird ausgeführt
    Und der Froschstein der Farbe <Farbe> des Spielers <Spieler> kann bewegt werden
    Wenn der Zug abgefragt wird
    Dann wird der Froschstein bewegt

  Szenario: : Rick überprüft während des Spiels wie ein Zug ausgeführt wird //    Hier fragen
  (Testfall: 20)
    Angenommen das Spiel läuft
    Und ein Zug wird gespielt
    Und die Aktion "Bewegen" wird ausgeführt
    Und der Froschstein wurde bewegt
    Wenn der Zug abgefragt wird
    Dann kann der Froschstein bewegt werden

  Szenario: : Rick überprüft während des Spiels wie ein Zug ausgeführt wird //    Hier fragen
  (Testfall: 21)
    Angenommen das Spiel läuft
    Und ein Zug wird gespielt
    Und die Aktion "Bewegen" wird ausgeführt
    Und der Froschstein wurde bewegt
    Wenn der Zug abgefragt wird
    Dann darf der Froschstein nicht an der selben Position wie am Anfang des Zuges sein

  Szenario: : Rick überprüft während des Spiels wie ein Zug ausgeführt wird
  (Testfall: 22)
    Angenommen das Spiel läuft
    Und ein Zug wird gespielt
    Und die Aktion "Anlegen" wird ausgeführt
    Und der Spieler der am Zug ist hat mindestens einen Froschstein im Inventar
    Und der Froschstein gelegt werden kann
    Wenn der Zug abgefragt wird
    Dann wird der Froschstein gelegt

  Szenario: : Rick überprüft während des Spiels wie ein Zug ausgeführt wird
  (Testfall: 23)
    Angenommen das Spiel läuft
    Und ein Zug wird gespielt
    Und die Aktion "Anlegen" wird ausgeführt
    Und der Spieler der am Zug ist hat mindestens einen Froschstein im Inventar
    Und der Froschstein gelegt werden kann
    Wenn der Zug abgefragt wird
    Dann wird der Froschstein neben einen anderen gelegt

  Szenario: : Rick überprüft während des Spiels wie ein Zug ausgeführt wird
  (Testfall: 24)
    Angenommen das Spiel läuft
    Und ein Zug wird gespielt
    Und die Aktion "Nachziehen" wird ausgeführt
    Und der Spieler der am Zug ist hat höchstens einen Froschstein im Inventar
    Wenn der Zug abgefragt wird
    Dann wird ein Froschstein zufällig aus dem Beutel genommen

  Szenario: : Rick überprüft während des Spiels wie ein Zug ausgeführt wird
  (Testfall: 25)
    Angenommen das Spiel läuft
    Und ein Zug wird gespielt
    Und die Aktion "Bewegen" oder "Anlegen" wurde ausgeführt
    Wenn der Zug abgefragt wird
    Dann ist keine Kette aus Froschsteinen gebildet worden

  Szenario: : Rick überprüft während des Spiels wie ein Zug ausgeführt wird
  (Testfall: 26)
    Angenommen das Spiel läuft
    Und ein Zug wird gespielt
    Und die Aktion "Bewegen" ist ausgeführt worden
    Wenn der Zug abgefragt wird
    Dann ist keine weitere Insel aus Froschsteinen gebildet worden