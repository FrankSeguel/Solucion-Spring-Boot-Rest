package cl.multicaja.utils.reflection;

import java.io.Serializable;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Bean base para todos los generados desde la base de datos
 *
 * @author fseguel
 * @version 1.0
 */
public class BaseBean implements Serializable, BeanCloneable {

    /**
     * Representacion del bean como String
     *
     * @return campos del bean representados como un string
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(
                this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * Compara 2 beans
     *
     * @param obj El objeto que se desea comparar con este
     * @return true si son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        try {
            return EqualsBuilder.reflectionEquals(this, obj);
        } catch (Exception e) {
            System.out.println("Solo para poder debugear aca");
            throw new RuntimeException(e);
        }
    }

    /**
     * Genera un hashcode mediante reflection
     *
     * @return el hascode del objeto
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /**
     * Clona un objeto
     *
     * @throws java.lang.CloneNotSupportedException En caso de que no sea
     * posible clonar el objeto
     * @return el objeto clonado
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        Object o = null;
        try {
            o = this.getClass().newInstance();
            // Poblamos los datos
            PropertyUtils.copyProperties(o, this);
        } catch (Exception e) {
            throw new CloneNotSupportedException("Imposible clonar objeto");
        }
        return o;
    }

}
