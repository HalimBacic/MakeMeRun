package Osoba;

public class ClanKluba extends Osoba{

        public static Integer number=100;
        private String uloga;

        public ClanKluba()
        {}

        public ClanKluba(Integer id, String ime, String prezime, String uloga)
        {
            super(id,ime,prezime);
            number++;
            this.uloga = uloga;
        }

        public String getUloga() {
        return uloga;
    }

        public static void setNumber (Integer number) {
            ClanKluba.number = number;
        }

    public static Integer getNumber () {
            return number;
        }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }
}
