package funcións;

import java.util.Collection;

public interface Printer<T> {
    default Object mapNULL(Object obj) {
        if (obj==null) return "";
        return obj;
    }
    
    default String clean(String str) {
        str=str.replace(","," ");
        return str;
    }
    
    String[] toCSV(Collection<T> list) throws Exception;
    String toRow(T object) throws Exception;
    String print(T object) throws Exception;
}
