package Buscaminas;

import game.Piece;


public class Mine extends Piece {
    private static final String HIDDEN[]={"X","/gamefx/resources/button_blank.png"};
    private static final String BOMB[]={"\u01A0","/gamefx/resources/bomb.png"};
    private static final String MINE[][]={
        {" ","/gamefx/resources/clean.png"},
        {"1","/gamefx/resources/Numbers-1-icon.png"},
        {"2","/gamefx/resources/Numbers-2-icon.png"},
        {"3","/gamefx/resources/Numbers-3-icon.png"},
        {"4","/gamefx/resources/Numbers-4-icon.png"},
        {"5","/gamefx/resources/Numbers-5-icon.png"},
        {"6","/gamefx/resources/Numbers-6-icon.png"},
        {"7","/gamefx/resources/Numbers-7-icon.png"},
        {"8","/gamefx/resources/Numbers-8-icon.png"},
        {"9","/gamefx/resources/Numbers-9-icon.png"}
    };
    
    public int value=0;
    public boolean hidden=true;
    
    
    public static Mine getBomb() {
        Mine mine=new Mine(BOMB[0].charAt(0),BOMB[1],-1);
        return mine;
    }
    
    public static Mine getMine(int value) {
        Mine mine=new Mine(MINE[value][0].charAt(0),MINE[value][1],value);
        return mine;    
    }
    
    private Mine(char name,String image,int value) {
        super(name,image);
        this.value=value;
    }
    
    @Override
    public String getImagePath() {
        if (hidden) return HIDDEN[1];
        return super.getImagePath();
    }
    
    @Override
    public char getId() {
        if (hidden) return HIDDEN[0].charAt(0);
        return super.getId();
    }
    
    public void hide() {
        this.hidden=true;
    }
    
    public void show() {
        this.hidden=false;
    }
    
    public boolean isBomb() {
        return value == -1;
    }
}
