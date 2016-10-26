package labor2;

/**
 * Aufgabe 2 c)
 *
 * @author Eike Hoffmann <eike.s.hoffmann@student.fh-kiel.de>
 * @author Silas Röber <silas.roeber@student.fh-kiel.de>
 */
class Adresse {
// Attribute
    private final int plz;
    private final String ort;
    private final String str;
    private final int nr;
    private Person[] anwohner = null;

// Konstruktoren
    Adresse(final int plz, final String ort, final String str, final int nr) {
        this.plz = plz;
        this.ort = ort;
        this.str = str;
        this.nr = nr;
    }

// Getter und Setter    
    final int getPlz() {
        return this.plz;
    }

    final String getOrt() {
        return this.ort;
    }

    final String getStr() {
        return this.str;
    }

    final int getNr() {
        return this.nr;
    }
    
    // Einzug
    final protected void setAnwohner(Person anwohner) {   
        if(this.anwohner == null) {
            this.anwohner = new Person[1];
            this.anwohner[0] = anwohner;
        } else {
            Person[] tmp = this.anwohner;
            this.anwohner = new Person[this.anwohner.length + 1];

            for(int i = 0; i < tmp.length; ++i) {
                this.anwohner[i] = tmp[i];
            }
            this.anwohner[tmp.length] = anwohner;
        }
    }
    
    final Person[] getAnwohner() {
        return this.anwohner;
    }

// weitere Funktionen
    //Braucht noch arbeit
    final void auszug(final Person person) {
        Person[] anwohner = this.getAnwohner();
        if(anwohner != null) {
            Person[] tmp = new Person[anwohner.length-1];
            int it = 0;
            for(int i = 0; i < anwohner.length; ++i) {
                if(anwohner[i] != person){
                    tmp[it] = anwohner[i];
                    it++;
                }
            }
            // Darf eigentlich nicht sein müsste der Setter übernehmen; andere Funktion
            this.anwohner = tmp;
        }
    }
    
// Ausgabefunktionen
    @Override
    public String toString() {
        return String.format("%s %d, %d %s", this.getStr(), this.getNr(), this.getPlz(), this.getOrt());
    }
    
   
    public String anwohnerAusgabe() 
    {
        StringBuilder stringBuilder = new StringBuilder();
        Person[] anwohner = this.getAnwohner();
        

        stringBuilder.append(String.format("Adresse: %s%n",
                                           this.toString()));

        if(anwohner != null) {
            stringBuilder.append(String.format("Anwohner: "));
            for(int i = 0; i < anwohner.length; ++i) {
                stringBuilder.append(anwohner[i].ausgabeName());
                stringBuilder.append(System.lineSeparator());
                if(i + 1 < anwohner.length) stringBuilder.append("          ");
            }
        }
        return stringBuilder.toString();
    }

}
