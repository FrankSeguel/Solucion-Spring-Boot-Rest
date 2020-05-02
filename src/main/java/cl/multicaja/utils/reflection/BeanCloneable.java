package cl.multicaja.utils.reflection;

/**
 *
 * @author fseguel
 */
public interface BeanCloneable {

    /**
     *
     * @return
     * @throws CloneNotSupportedException
     */
    public Object clone() throws CloneNotSupportedException;

}
