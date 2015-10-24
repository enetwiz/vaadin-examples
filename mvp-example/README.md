MVP Example
===========
Ponizszy przyklad stanowi klasyczna implementacje wzorca MVP wdrozona przy uzyciu frameworkow
Vaadin oraz Spring.


Czym jest MVP?
--------------
MVP jest wzorcem projektowym umozliwiajacym kompleksowa organizacje kodu rozumiana przez podzial
aplikacji na warstwy, gdzie kazda z warstw pelni okreslone funkcje.
Tym samym rozwijajac skrot z nazwy wzorca otrzymamy 3 elementy:

 * **M**odel - stanowi klasa dostepu do danych - moze to byc tzw. encja napisana w standardzie
   JPA, ktora jest obiektowym odwzorowaniem rekordu z bazy danych.
   Przykladowo jezeli w naszej bazie danych mamy tabele `osoba` zawierajaca kolumny:
   `imie`, `nazwisko` i `data_urodzenia`. Wowczas obiektowe odwzorowanie pojedynczego rekordu
   modelu moze wygladac w nastepujacy sposob:

   ```java
   @Entity
   @Table(name = "osoba")
   public class Osoba implements Serializable {

        private static final long serialVersionUID = 1L;

        @Id
        @Column(name = "osoba_id")
        private Integer id;

        @Column(name = "imie")
        private String imie = "Jacek";

        @Column(name = "imie")
        private String nazwisko = "Nowak";

        @Column(name = "data_urodzenia")
        private Date dataUrodzenia = new Date();

        // Gettery/settery
        // ...
   }
   ```
 * **V**iew - czyli widok, ktory stanowi klase zawierajaca jedynie elementy graficzne okna
   programu (tzw. kontrolki) jak np. pole tekstowe, formularz, obrazek itp.
   Bardzo wazna cecha widoku jest brak bezposredniego dostepu do danych. Oznacza to, ze w widoku
   nie powinny byc zainicjowane jakiekolwiek inne obiekty zw. z logika biznesowa (np. klasy
   odpowiedzialne za dostep do danych itp.).
   > Widok to po prostu widok i nic poza tym!

   I w tym miejscu rzecz najwazniejsza dotyczaca klasy widoku - otoz widok otrzymuje/aktualizuje
   dane poprzez interfejs. Oznacza to, ze interfejs jest potrzebny do
   calkowitego odseparowania warstwy widoku od logiki biznesowej aplikacji:

   ```java
   public interface Widok {
        void ustawEtykiete(String etykieta);
   }

   public class WidokImpl extends VerticalLayout implements Widok {

        private Label etykieta = new Label("Moja etykieta");

        @PostConstruct
        private void init() {
            setMargin(true);
            addComponent(etykieta);
        }

        @Override
        public void ustawEtykiete(String etykieta) {
            etykieta.setValue(etykieta);
        }

   }
   ```
 * **P**resenter - prezenter - klasa, ktorej zadaniem jest zlaczenie modelu i widoku w ramach
   okreslonego interfejsu uzytkownika (UI). To wlasnie prezenter odpowiedzialny jest za pobranie
   danych modelu i wstrzykniecie ich do widoku poprzez jego interfejs. W prezenterze wykonywane
   sa rowniez operacje zw. z logika biznesowa aplikacji.
   To na co warto zwrocic uwage to fakt, ze jeden prezenter odpowiada za komunikacje z zaledwie
   jednym widokiem. To zupelnie inny mechanizm niz ten znany z popularnego wzorca MVC, gdzie
   kontroler mogl ladowac wiele widokow:

   ```java
   public class MojPrezenter {
        @PostConstruct
        protected void init() {
            Osoba osoba = new Osoba();
            Widok widok = new WidokImpl();
            widok.ustawEtykiete(osoba.getNazwisko());

            // Ostatecznie prezenter jest inicjowany w interfejsie uzytkownika (klasa UI), skad tez
            // ladowany jest jego - zasilony danymi - widok
            // ...
        }
   }
   ```

> **UWAGA**: Powyzsze przyklady kodu maja za zadanie przyblizyc zalozenia wzorca MVP przez co
  zostaly maksymalnie uproszczone


Komunikacje pomiedzy poszczegolnymi warstwami obrazuje ponizszy diagram:

[![Diagram obrazujacy komunikacje w ramach MVP](http://upload.wikimedia.org/wikipedia/commons/thumb/d/dc/Model_View_Presenter_GUI_Design_Pattern.png/220px-Model_View_Presenter_GUI_Design_Pattern.png)](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter)

> [Diagram pochodzi z serwisu Wikipedia.org](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter)

Po co stosowac wzorzec?
-----------------------

Zastosowanie wzorca MVP przy integracji z Vaadinem (lub jakakolwiek biblioteka do budowy GUI) daje
kilka waznych korzysci:

 * pierwsza z nich jest organizacja kodu w ramach pewnej struktury (standardu) - w zwiazku z
   powyzszym takiego standardu moze nauczyc sie kazdy, przez co szybciej mozna zrozumiec mechanike
   dzialania obcej aplikacji
 * kolejna jest odseparowanie warstwy widoku od warstwy logiki biznesowej co pozwala na
   bezinwazyjna zmiane biblioteki odpowiedzialnej za generowanie GUI - innymi slowy, poczatkowo za
   warstwe graficzna moze odpowiedac biblioteka Vaadin, ktory ostatecznie moze zostac zamieniona
   np. na Swinga. Daje to niesamowita elastycznosc, co jest mozliwe dzieki komunikacji z widokiem
   poprzez interfejs widoku.
 * nizsze koszty utrzymania, a takze rozwoju aplikacji - kod ustandardyzowany, napisany w ramach
   znanego schematu (wzorca) automatycznie staje sie latwiejszy w zrozumieniu i modyfikacji.
   Zapoznanie sie z takim projektem zajmie mniej czasu, a znalezienie odpowiednich specialistow nie
   bedzie graniczylo z cudem :)


Szyna zdarzen, czyli EventBus
-----------------------------