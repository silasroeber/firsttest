
package labor2;

/**
 * Aufgabe 2
 *
 * @author  Eike Hoffmann   <eike.s.hoffmann@student.fh-kiel.de>
 * @author  Silas Röber     <silas.roeber@student.fh-kiel.de>
 */
public class Labor2 {

    public static void ausgabe(Person[] personen) 
    {
        for( Person person : personen) {
            ausgabe(person);
        }
    }

    public static void ausgabe(Person person) {
        System.out.println(person);
        System.out.println("");
    }

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
    // neue Adresse anlegen
        Adresse adresse1 = new Adresse(24145, "Kiel", "Schoolkamp", 3);
        Adresse adresse2 = new Adresse(24118, "Kiel", "Gutenbergstr", 28);

//------------------------------------------------------------------------------
    // Eltern werden geboren
        Person personFrauTest = new Person.Builder(1)
                .anrede("Frau")
                .vorname("Elisabeth")
                .nachname("Müller")
                .gebDatum(new Datum(13,3,1983))
                //.adresse(adresse1)
                .build();

        Person personMannTest = new Person.Builder(2)
                .anrede("Herr")
                .vorname("Max")
                .nachname("Mustermann")
                .gebDatum(new Datum(24,12,1981))
                //.adresse(adresse1)
                .ehepartner(personFrauTest)
                .build();

        System.out.println("\n"
                + "Eltern werden geboren");
        ausgabe(personFrauTest);
        ausgabe(personMannTest);

//------------------------------------------------------------------------------
    // Sie heiraten und ziehen zusammen
        personFrauTest.heiraten(personMannTest);
        personFrauTest.setAdresse(adresse1);
        personMannTest.setAdresse(adresse1);
        System.out.println("\n"
                + "Sie heiraten und ziehen zusammen");
        ausgabe(personFrauTest);
        ausgabe(personMannTest);
        
//------------------------------------------------------------------------------
    // Sie bekommen Kinder
        Person personKind1Test = new Person.Builder(12)
                .anrede("Frau")
                .vorname("Baby1")
                .nachname("Mustermann")
                .gebDatum(new Datum(21,10,2016))
                .mutter(personFrauTest)
                .vater(personMannTest)
                .adresse(adresse1)
                .build();

        Person personKind2Test = new Person.Builder(13)
                .anrede("Herr")
                .vorname("Baby2")
                .nachname("Mustermann")
                .gebDatum(new Datum(22,10,2016))
                .mutter(personFrauTest)
                .vater(personMannTest)
                .adresse(adresse1)
                .build();

            System.out.println("\n"
                    + "Sie bekomen Kinder");
        ausgabe(new Person[] {personFrauTest, personMannTest, personKind1Test, personKind2Test});
        
//------------------------------------------------------------------------------
    // Kinder ziehen aus
        personKind1Test.setAdresse(adresse2);
        personKind2Test.setAdresse(adresse2);
        System.out.println("\n"
                    + "Kinder ziehen aus:");
        System.out.println(adresse1.anwohnerAusgabe());
        System.out.println(adresse2.anwohnerAusgabe());
        
//------------------------------------------------------------------------------
    // Die Eltern sterben
        personMannTest.setTodDatum(new Datum(22,12,2030));
        personFrauTest.setTodDatum(new Datum(4,7,2032));
            System.out.println("\n"
                    + "eltern Sterben");
        ausgabe(new Person[] {personFrauTest, personMannTest, personKind1Test, personKind2Test});

        
//------------------------------------------------------------------------------
    // Anwohner Ausgabe
        
    }
    
}
