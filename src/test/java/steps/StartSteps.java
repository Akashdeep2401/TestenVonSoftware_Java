package steps;

import de.fhkiel.tsw.Gamelogic;
import de.fhkiel.tsw.armyoffrogs.Color;
import io.cucumber.java.de.Angenommen;
import io.cucumber.java.de.Dann;
import io.cucumber.java.de.Und;
import io.cucumber.java.de.Wenn;
import steps.container.LogicContainer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StartSteps {

    private LogicContainer container;

    public StartSteps(LogicContainer container) {
        this.container = container;
    }

    int frösche;
    int iPlayers;
    private boolean SpielGestartet = false;

    Color[] TestColors = {Color.Blue, Color.Red, Color.White, Color.Green};

    List<Color> TestColorsArrayList = new ArrayList<>();

    @Angenommen("es läuft kein Spiel")
    public void es_läuft_kein_spiel() {
        container.logicUnderTest = new Gamelogic();
    }

    @Angenommen("das Spiel läuft")
    public void das_spiel_läuft() {
        container.logicUnderTest = new Gamelogic();
        TestColorsArrayList.addAll(Arrays.asList(TestColors));
        container.logicUnderTest.startGame(4, TestColorsArrayList);
    }

    @Wenn("der Beutelinhalt abgefragt wird")
    public void der_beutelinhalt_abgefragt_wird() {
        frösche = container.logicUnderTest.frogsInBag();
    }
    /*
    @Dann("sind {int} Frösche im Beutel")
    public void sind_frösche_im_beutel(Integer erwarteteFrösche) {
        assertThat(frösche).isEqualTo(erwarteteFrösche);
    }
    */
    @Angenommen("das Spiel ist mit {int} Spielern gestartet")
    public void das_spiel_ist_mit_spielern_gestartet(Integer spieler) {
        container.logicUnderTest = new Gamelogic();
        List<Color> TestFarben = new ArrayList<>();
        TestFarben.add(Color.Blue);
        TestFarben.add(Color.Red);
        TestFarben.add(Color.Green);
        //container.logicUnderTest.startGame(spieler, TestFarben);
        assertThat(container.logicUnderTest.newGame(spieler)).isTrue();
    }
    @Angenommen("der (erste)(zweite)(dritte)(vierte) {int} Spieler hat {int} Frösche gezogen")
    public void der_erste_spieler_hat_frösche_gezogen(int Spieler, Integer gezogeneFrösche) {
        for (int i = 0; i < gezogeneFrösche; ++i) {
            container.logicUnderTest.takeFrogFromBag(container.logicUnderTest.getReihenfolge()[Spieler]);
        }

    }

    @Angenommen("das Spiel versucht mit {int} Spielern zu starten")
    public void dasSpielVersuchtMitSpielerSpielernZuStarten(int iAnzahlSpieler) {
        container.logicUnderTest = new Gamelogic();
        List<Color> TestFarben = new ArrayList<>();
        if (iAnzahlSpieler == 2) {
            TestFarben.add(Color.Blue);
            TestFarben.add(Color.Red);
        } else if (iAnzahlSpieler == 3) {
            TestFarben.add(Color.Blue);
            TestFarben.add(Color.Red);
            TestFarben.add(Color.Green);
        } else if (iAnzahlSpieler == 4){
            TestFarben.add(Color.Blue);
            TestFarben.add(Color.Red);
            TestFarben.add(Color.Green);
            TestFarben.add(Color.White);
        }
        container.logicUnderTest.startGame(iAnzahlSpieler, TestFarben);
    }

    @Wenn("das System die Spieler zählt")
    public void dasSystemDieSpielerZählt() {
        iPlayers = container.logicUnderTest.getPlayerCount();
    }



    @Dann("wird das Spiel {int} gestartet")
    public void wirdDasSpielNichtGestartet(int SpielGestartet) {
        boolean SpielSollLaufen;
        SpielSollLaufen = SpielGestartet == 1;

        assertThat(container.logicUnderTest.isGameIsRunning()).isEqualTo(SpielSollLaufen);
    }

    @Angenommen("das Spiel ist gestartet")
    public void dasSpielIstGestartet() {
    }

    @Dann("ist das Spiel gestartet")
    public void istDasSpielGestartet() {
    }

    @Angenommen("ein Spieler hat die Farbe {} ausgewählt")
    public void einSpielerHatDieFarbeAusgewahlt(String arg0) {
    }

    @Wenn("die Auswahl überprüft wird")
    public void dieAuswahlUberpruftWird() {
    }

    @Dann("ist die Farbe nicht eine der vorher ausgewählt Farben")
    public void istDieFarbeNichtEineDerVorherAusgewahltFarben() {
    }

    @Dann("sind nur Froschstein im Beutel die Blau, Grün, Rot oder Weiß sind")
    public void sindNurFroschsteinImBeutelDieBlauGrunRotOderWeißSind() {
    }

    @Angenommen("alle {} Spieler haben eine Farbe ausgewählt")
    public void alleSpielerHabenEineFarbeAusgewahlt(String arg0) {
    }


    @Und("es wurde {} als Farbe eines Spielers ausgewählt")
    public void esWurdeAlsFarbeEinesSpielersAusgewahlt(String arg0) {
    }

    @Dann("sind {int} {} Froschsteine im Beutel")
    public void sindFroschsteineImBeutel(int arg0, String arg1) {
    }

    @Angenommen("der Beutelinhalt wurde überprüft")
    public void derBeutelinhaltWurdeUberpruft() {
    }

    @Wenn("die Reihenfolge abgefragt wird")
    public void dieReihenfolgeAbgefragtWird() {
    }

    @Angenommen("es gibt einen Startspieler {}")
    public void esGibtEinenStartspieler(String arg0) {
    }

    @Dann("ist Spieler {} der erste Spieler in der Spielreihenfolge")
    public void istSpielerDerErsteSpielerInDerSpielreihenfolge(String arg0) {
    }

    @Angenommen("die Spielreihenfolge wurde festgelegt")
    public void dieSpielreihenfolgeWurdeFestgelegt() {
    }

    @Und("Spieler {} war am Zug im letzten Zug")
    public void spielerWarAmZugImLetztenZug(String arg0) {
    }

    @Dann("ist Spieler {} am Zug")
    public void istSpielerAmZug(String arg0) {
    }

    @Angenommen("das Programm läuft")
    public void dasProgrammLauft() {
    }

    @Wenn("das Inventar des Spielers {} abgefragt wird")
    public void dasInventarDesSpielersAbgefragtWird(String arg0) {
    }

    @Dann("hat Spieler {} maximal zwei Froschsteine")
    public void hatSpielerMaximalZweiFroschsteine(String arg0) {
    }

    @Dann("sind {} Froschsteine im Beutel")
    public void sindFroschsteineImBeutel(String arg0) {
    }

    @Und("zwei Froschsteine im Inventar von Spieler {}")
    public void zweiFroschsteineImInventarVonSpieler(String arg0) {
    }

    @Dann("hat kein Spieler die Aktion {string} gespielt \\/\\/ Hier Fragen")
    public void hatKeinSpielerDieAktionGespieltHierFragen(String arg0) {
    }

    @Und("der Froschstein der Farbe {} des Spielers {} kann bewegt werden")
    public void derFroschsteinDerFarbeDesSpielersKannBewegtWerden(String arg0, String arg1) {
    }

    @Dann("wird der Froschstein bewegt")
    public void wirdDerFroschsteinBewegt() {
    }

    @Dann("darf der Froschstein nicht an der selben Position wie am Anfang des Zuges sein")
    public void darfDerFroschsteinNichtAnDerSelbenPositionWieAmAnfangDesZugesSein() {
    }

    @Und("der Spieler der am Zug ist hat mindestens einen Froschstein im Inventar")
    public void derSpielerDerAmZugIstHatMindestensEinenFroschsteinImInventar() {
    }

    @Und("der Froschstein gelegt werden kann")
    public void derFroschsteinGelegtWerdenKann() {
    }

    @Dann("wird der Froschstein gelegt")
    public void wirdDerFroschsteinGelegt() {
    }

    @Dann("wird der Froschstein neben einen anderen gelegt")
    public void wirdDerFroschsteinNebenEinenAnderenGelegt() {
    }

    @Und("der Spieler der am Zug ist hat höchstens einen Froschstein im Inventar")
    public void derSpielerDerAmZugIstHatHochstensEinenFroschsteinImInventar() {
    }

    @Dann("wird ein Froschstein zufällig aus dem Beutel genommen")
    public void wirdEinFroschsteinZufalligAusDemBeutelGenommen() {
    }

    @Und("die Aktion {string} oder {string} wurde ausgeführt")
    public void dieAktionOderWurdeAusgefuhrt(String arg0, String arg1) {
    }

    @Dann("ist keine Kette aus Froschsteinen gebildet worden")
    public void istKeineKetteAusFroschsteinenGebildetWorden() {
    }

    @Und("die Aktion {string} ist ausgeführt worden")
    public void dieAktionIstAusgefuhrtWorden(String arg0) {
    }

    @Dann("ist keine weitere Insel aus Froschsteinen gebildet worden")
    public void istKeineWeitereInselAusFroschsteinenGebildetWorden() {
    }

    @Angenommen("das Spiel ist a gestartet b")
    public void das_spiel_ist_a_huhugestartet() {
        container.logicUnderTest = new Gamelogic();
        SpielGestartet = container.logicUnderTest.newGame(2);
    }
    @Dann("ist das Spiel a gestartet")
    public void ist_das_spiel_a_gestartet() {
        assertThat(SpielGestartet).isTrue();
    }
}
