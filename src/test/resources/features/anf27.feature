# language: de
Funktionalität:Das System muss gewährleisten, dass eine Kette eine Verbindung von
  Froschsteinen mit drei oder mehr Einzelverbindung und einem offenem Ende ist.

  Szenario: : Rick überprüft was eine Kette ist
    (Testfall: 27)
    Angenommen das Spiel läuft
    Wenn eine Verbindung aus Froschsteinen mit 3 oder mehr Einzelverbindungen
    Und einem offenen Ende gebildet wird
    Dann ist eine Kette entstanden

  Szenario: : Rick überprüft was ein offenes Ende ist
    (Testfall: 27a)
    Angenommen das Spiel läuft
    Wenn ein Froschstein mit nur einer Einzelverbindung gelegt oder bewegt wird
    Dann ist ein offenes Ende entstanden