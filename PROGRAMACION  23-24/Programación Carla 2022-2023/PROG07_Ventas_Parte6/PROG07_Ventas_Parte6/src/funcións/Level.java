package funcións;

public interface Level {
    enum Verbosity { INFO, ERR, DEBUG };

    public String[] messages(VerboseException e);
}
