
package com.sdi.ws.admin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para userInfo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="userInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numTareasCompletadas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numTareasCompletadasRetrasadas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numTareasNoPlanificadas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numTareasPlanificadas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="user" type="{http://admin.impl.business.sdi.com/}user" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userInfo", propOrder = {
    "numTareasCompletadas",
    "numTareasCompletadasRetrasadas",
    "numTareasNoPlanificadas",
    "numTareasPlanificadas",
    "user"
})
public class UserInfo {

    protected int numTareasCompletadas;
    protected int numTareasCompletadasRetrasadas;
    protected int numTareasNoPlanificadas;
    protected int numTareasPlanificadas;
    protected User user;

    /**
     * Obtiene el valor de la propiedad numTareasCompletadas.
     * 
     */
    public int getNumTareasCompletadas() {
        return numTareasCompletadas;
    }

    /**
     * Define el valor de la propiedad numTareasCompletadas.
     * 
     */
    public void setNumTareasCompletadas(int value) {
        this.numTareasCompletadas = value;
    }

    /**
     * Obtiene el valor de la propiedad numTareasCompletadasRetrasadas.
     * 
     */
    public int getNumTareasCompletadasRetrasadas() {
        return numTareasCompletadasRetrasadas;
    }

    /**
     * Define el valor de la propiedad numTareasCompletadasRetrasadas.
     * 
     */
    public void setNumTareasCompletadasRetrasadas(int value) {
        this.numTareasCompletadasRetrasadas = value;
    }

    /**
     * Obtiene el valor de la propiedad numTareasNoPlanificadas.
     * 
     */
    public int getNumTareasNoPlanificadas() {
        return numTareasNoPlanificadas;
    }

    /**
     * Define el valor de la propiedad numTareasNoPlanificadas.
     * 
     */
    public void setNumTareasNoPlanificadas(int value) {
        this.numTareasNoPlanificadas = value;
    }

    /**
     * Obtiene el valor de la propiedad numTareasPlanificadas.
     * 
     */
    public int getNumTareasPlanificadas() {
        return numTareasPlanificadas;
    }

    /**
     * Define el valor de la propiedad numTareasPlanificadas.
     * 
     */
    public void setNumTareasPlanificadas(int value) {
        this.numTareasPlanificadas = value;
    }

    /**
     * Obtiene el valor de la propiedad user.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getUser() {
        return user;
    }

    /**
     * Define el valor de la propiedad user.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setUser(User value) {
        this.user = value;
    }

}
