package labor2;

/**
 * Aufgabe 2
 *
 * @author  Eike Hoffmann   <eike.s.hoffmann@student.fh-kiel.de>
 * @author  Silas Röber     <silas.roeber@student.fh-kiel.de>
 */
class Person {
// Attribute
    private final int personalNr;
    private String anrede;
    private String vorname;
    private String nachname;
    // a)
    private Datum gebDatum;
    private Datum todDatum = null;
    // b)
    private Person ehepartner = null;
    private Person vater = null;
    private Person mutter = null;
    private Person[] kinder = null;
    // c)
    private Adresse adresse = null;     //VEränderung!

//------------------------------------------------------------------------------
// Konstruktoren
    private Person(int nummer) {
        this.personalNr = nummer;
    }

    private Person(Builder builder) {
        this(builder.personalNr);
        this.setAnrede (builder.anrede);
        this.setVorname (builder.vorname);
        this.setNachname (builder.nachname);
        this.setGebDatum(builder.gebDatum);
        this.setMutter(builder.mutter);
        this.setVater(builder.vater);
        this.setAdresse(builder.adresse);
        this.setEhepartner(builder.partner);
    }
    
//------------------------------------------------------------------------------
// Getter und Setter
    final int getPersonalNr() {
        return this.personalNr;
    }
    
    final void setAnrede (final String anrede) {
        this.anrede = anrede;
    }

    final String getAnrede() {
        return this.anrede;
    }
    
    final void setVorname (final String vorname) {
        this.vorname = vorname;
    }

    final String getVorname() {
        return this.vorname;
    }
    
    final void setNachname (final String nachname) {
        this.nachname = nachname;
    }

    final String getNachname() {
        return this.nachname;
    }
    
    // a)
    private void setGebDatum(final Datum datum) {
        if(this.gebDatum != null) System.out.println("Ein Geburtsdatum kann nicht geändert werden!");
        else this.gebDatum = datum;
    }

    final Datum getGebDatum() {
        return this.gebDatum;
    }
    
    final void setTodDatum(final Datum datum) {
        if(this.todDatum != null) System.out.println("Ein Todesdatum kann nicht geändert werden!");
        else this.todDatum = datum;
    }

    final Datum getTodDatum() {
        return this.todDatum;
    }
    
    // b)
    final void setEhepartner(Person ehepartner) {
        if(ehepartner != this && this.getEhepartner() != ehepartner) {
            this.ehepartner = ehepartner;
            if (ehepartner != null) {
                ehepartner.setEhepartner(this);
            }
        }
    }

    final Person getEhepartner() {
        return this.ehepartner;
    }

    final void setVater(Person vater) {
        this.vater = vater;
        if(this.vater != null) {
            this.vater.setKinder(this);
        }
    }
    
    final Person getVater() {
        return this.vater;
    }

    final void setMutter(Person mutter) {
        this.mutter = mutter;
        if(this.mutter != null) {
            this.mutter.setKinder(this);
        }
    }

    final Person getMutter() {
        return this.mutter;
    }

    final void setKinder(Person kind) {
        if(this.kinder == null) {
            this.kinder = new Person[1];
            this.kinder[0] = kind;
        } else {
            Person[] tmp = this.kinder;
            this.kinder = new Person[this.kinder.length + 1];

            for(int i = 0; i < tmp.length; ++i) {
                this.kinder[i] = tmp[i];
            }
            this.kinder[tmp.length] = kind;
        }
    }

    final Person[] getKinder() {
        return this.kinder;
    }
    
    // c)
    // Verändert
    final void setAdresse(final Adresse adresse) {
            if(adresse != null) {
            if(this.adresse != null) {
                this.adresse.auszug(this);
            }
            this.adresse = adresse;
            this.adresse.setAnwohner(this);
        }
    }

    final Adresse getAdresse() {
        return this.adresse;
    }

//------------------------------------------------------------------------------
// weitere Funktionen
    public void heiraten(Person partner) {
        if(this != partner) {
            this.setEhepartner(partner);
            partner.setEhepartner(this);
            this.setNachname(partner.getNachname());
        }
    }
    
//------------------------------------------------------------------------------
// Ausgabe funktionen
    @Override
    public String toString() 
    {
        StringBuilder stringBuilder = new StringBuilder();
        Person[] kinder = this.getKinder();
        Datum todestag = this.getTodDatum();
        Adresse adresse = this.getAdresse();

        stringBuilder.append(String.format("Person %d: %s%n" +
                                           "Geburtstag: %s%n",
                                           this.getPersonalNr(),
                                           this.ausgabeName(),
                                           this.getGebDatum().toString()));

        if(todestag != null) {
            stringBuilder.append(String.format("Todestag: %s%n", todestag.toString()));
        }

        stringBuilder.append(String.format("Mutter: %s%n" +
                                           "Vater: %s%n" +
                                           "Ehepartner: %s",
                                           ausgabePerson(this.getMutter()),
                                           ausgabePerson(this.getVater()),
                                           ausgabePerson(this.getEhepartner())));

        if(adresse != null) {
            stringBuilder.append(String.format("%nAdresse: %s", adresse.toString()));
        }

        if(kinder != null) {
            stringBuilder.append(String.format("%nKinder: "));
            for(int i = 0; i < kinder.length; ++i) {
                stringBuilder.append(kinder[i].ausgabeName());
                stringBuilder.append(System.lineSeparator());
                if(i + 1 < kinder.length) stringBuilder.append("        ");
            }
        }

        return stringBuilder.toString();
    }

    protected String ausgabeName() {
        return String.format("%s %s", this.getVorname(), this.getNachname());
    }
    
    private String ausgabePerson(final Person p) {
        return (p != null ? p.ausgabeName() : "-");
    }

//------------------------------------------------------------------------------    
// interne Klasse Builder
    public static class Builder {
        private final int personalNr;
        private String anrede;
        private String vorname;
        private String nachname;
        private Person mutter;
        private Person vater;
        private Person partner;
        private Datum gebDatum;
        private Adresse adresse;

        public Builder(final int personalNr) {
            this.personalNr = personalNr;
        }

        public Builder anrede(final String anrede) {
            this.anrede = anrede;
            return this;
        }

        public Builder vorname(final String vorname) {
            this.vorname = vorname;
            return this;
        }

        public Builder nachname(final String nachname) {
            this.nachname = nachname;
            return this;
        }

        public Builder mutter(final Person mutter) {
            this.mutter = mutter;
            return this;
        }

        public Builder vater(final Person vater) {
            this.vater = vater;
            return this;
        }

        public Builder gebDatum(final Datum gebDatum) {
            this.gebDatum = gebDatum;
            return this;
        }

        public Builder adresse(final Adresse adresse) {
            this.adresse = adresse;
            return this;
        }

        public Builder ehepartner(final Person partner) {
            this.partner = partner;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}
