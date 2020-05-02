package cl.multicaja.utils.reflection;

import java.util.Comparator;
import org.apache.commons.collections.Predicate;

/**
 *
 * @author fseguel
 */
public class ComparatorPredicate implements Predicate {

    private Comparator comparator = null;
    private Object original = null;

    /**
     *
     * @param c
     * @param original
     */
    public ComparatorPredicate(Comparator c, Object original) {
        this.comparator = c;
        this.original = original;
    }

    /**
     *
     * @param fields
     * @param original
     */
    public ComparatorPredicate(String[] fields, Object original) {
        this(new ReflectionComparator(fields), original);
    }

    /**
     *
     * @param object
     * @return
     */
    public boolean evaluate(Object object) {
        return (comparator.compare(original, object) == 0);
    }

}
