package ud7.streams;

/**
 * Realmente non ten demasiado sentido empregar un patrón Bridge para algo 
 * tan simple..
 * @author xavi
 */
public class ArtigoBridge extends ArtigoFormatter {
    private Artigo artigo;
    
    public ArtigoBridge(Artigo artigo) {
        this.artigo=artigo;
    }
    
    public String format() {
        return format(artigo);
    }
}
