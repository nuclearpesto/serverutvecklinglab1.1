package translator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by archer on 2016-11-23.
 */
public abstract class Translator<E, T> {

    public abstract E translateET(T from);

    public List<E> translateListET (List<T> from){
        List<E> list = new ArrayList<>();
        for (T t: from) {
            list.add(translateET(t));
        }
        return list;
    }


    public abstract T translateTE(E from);

    public List<T> translateListTE (List<E> from){
        List<T> list = new ArrayList<>();
        for (E e: from) {
            list.add(translateTE(e));
        }
        return list;
    }




}
