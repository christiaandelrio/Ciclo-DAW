package funcións;

import static funcións.Level.Verbosity.ERR;


public abstract class BuilderAdapter<T> implements Builder<T> {
    private final String msgbase;
    protected VerboseException error;
    
    public BuilderAdapter(String msgbase) {
        this.msgbase=msgbase;
    }
    
    public void reset() {
        error=null;
    }
    
    public void notifyErrors() throws VerboseException {
        if (error!=null) throw error;
    }

    protected int error(String msg,String description) {
        if (error==null) error=new VerboseException(msgbase);
        int erridx=error.addMensaxe(msg);
        error.addMensaxe(ERR,erridx,description);    
        return erridx;
    } 
    
    protected int error(String msg) {
        if (error==null) error=new VerboseException(msgbase);
        int erridx=error.addMensaxe(msg);
        return erridx;
    }
    
    @Override
    public abstract T build() throws Exception;
}
