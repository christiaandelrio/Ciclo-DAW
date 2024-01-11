package game;

public class Piece {
    private final char id;
    private final String image;
    
    public Piece(char id) {
        this.id=id;
        this.image=null;
    }
    
    public Piece(char id,String image) {
        this.id=id;
        this.image=image;
    }
    
    public char getId() {
        return id;
    }
    
    public String getImagePath() {
        return image;
    } 
    
    @Override
    public String toString() {
        String str=""+id;
        if (image!=null) str+=" ["+image+"]";
        return str;
    }
}
