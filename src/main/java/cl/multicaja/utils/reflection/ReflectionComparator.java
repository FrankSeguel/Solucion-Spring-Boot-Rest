package cl.multicaja.utils.reflection;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.beanutils.PropertyUtils;

/**
 *
 * @author fseguel
 */
public class ReflectionComparator implements Comparator, Serializable {

    private String[] fields = null;

    /**
     *
     */
    public ReflectionComparator() {
        this.fields = new String[0];
    }

    /**
     *
     * @param fields
     */
    public ReflectionComparator(String[] fields) {
        this.fields = fields;
    }

    private Map getMap(Object o) {
        Map result = new HashMap();
        for (String field : this.fields) {
            try {
                result.put(field, PropertyUtils.getProperty(o, field));
            } catch (Exception e) {
                // Logeamos el error pero seguimos poblando el map
//				logger.debug("Imposible encontrar propiedad: " + field);
            }
        }
        return result;
    }

    public int compare(Object o1, Object o2) {
        // Obtengo los maps de cada objeto
        Map m1 = getMap(o1);
        Map m2 = getMap(o2);
        // Arbitrariamente retorno -1 cuando los datos no son iguales
        return m1.equals(m2) ? 0 : -1;
    }

}
