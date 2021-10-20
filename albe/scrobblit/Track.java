
package albe.scrobblit;


public class Track {
    private String Nome, Artista, Contatore,DataUltimoAscolto,Album;
    private int Duration,count;
    public Track () {
        
    }
    public Track(String artista, String nome, String album, String dataUltimoAscolto) {
        this.Nome = nome;
        this.Artista = artista;
        this.DataUltimoAscolto = dataUltimoAscolto;
        this.Album = album;
    }
    public Track(String artista, String nome, String album, String dataUltimoAscolto,int count) {
        this.Nome = nome;
        this.Artista = artista;
        this.DataUltimoAscolto = dataUltimoAscolto;
        this.Album = album;
        this.count = count;
    }
    public Track(String artista, String nome, String album, String dataUltimoAscolto,String contatore) {
        this.Nome = nome;
        this.Artista = artista;
        this.DataUltimoAscolto = dataUltimoAscolto;
        this.Album = album;
        this.Contatore = contatore;
    }
    public void setDuration(int Duration) {
        this.Duration = Duration;
    }

    public int getDuration() {
        return Duration;
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String Album) {
        this.Album = Album;
    }
    public void Track (){
        this.Nome="";
        this.Artista="";
        this.Contatore = "";
        this.Album="";
        this.Duration=0;
    }
    public void setNome(String n) {
        this.Nome=n;
    }
    public void setArtista(String n) {
        this.Artista=n;
    }
    public void setContatore(String n) {
        this.Contatore = n;
    }
    public void setDataUltimoAscolto(String n) {
        this.DataUltimoAscolto = n;
    }
    public String getNome(){
        return Nome;
    }
    public String getArtista(){
        return Artista;
    }
    public String getContatore(){
        return Contatore;
    }
    public String getDataUltimoAscolto() {
        return DataUltimoAscolto;
    }

    public int getCount() {
        return count;
    }
}
