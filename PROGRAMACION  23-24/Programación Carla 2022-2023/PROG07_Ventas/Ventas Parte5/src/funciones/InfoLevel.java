/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funciones;

/**
 *
 * @author yo
 */
class InfoLevel implements Level{
    private static InfoLevel levelObject=null;
    
 
    public static InfoLevel getLevel() {
        if (InfoLevel.levelObject==null){
            InfoLevel.levelObject=new InfoLevel();
        }
        return levelObject;
    }
    private InfoLevel() {}
    
    @Override
    public String[] messages(VerboseException e) {
        return e.information.values().stream()
                .map((em)->(em.id.equals("1I"))?em.toString():"\t"+em.toString())
                .toArray(String[]::new);
    }
    
}
   
        
    

