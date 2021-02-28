
package com.easidiomas.usersservice.clients.statisticsservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for systemStatistics complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="systemStatistics"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="createdChats" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="createdPosts"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="entry" maxOccurs="unbounded" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="registeredUsers" type="{http://service.statisticsservice.easidiomas.com/}registeredUsersData" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "systemStatistics", propOrder = {
    "createdChats",
    "createdPosts",
    "registeredUsers"
})
public class SystemStatistics {

    protected int createdChats;
    @XmlElement(required = true)
    protected CreatedPosts createdPosts;
    protected RegisteredUsersData registeredUsers;

    /**
     * Gets the value of the createdChats property.
     * 
     */
    public int getCreatedChats() {
        return createdChats;
    }

    /**
     * Sets the value of the createdChats property.
     * 
     */
    public void setCreatedChats(int value) {
        this.createdChats = value;
    }

    /**
     * Gets the value of the createdPosts property.
     * 
     * @return
     *     possible object is
     *     {@link CreatedPosts }
     *     
     */
    public CreatedPosts getCreatedPosts() {
        return createdPosts;
    }

    /**
     * Sets the value of the createdPosts property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreatedPosts }
     *     
     */
    public void setCreatedPosts(CreatedPosts value) {
        this.createdPosts = value;
    }

    /**
     * Gets the value of the registeredUsers property.
     * 
     * @return
     *     possible object is
     *     {@link RegisteredUsersData }
     *     
     */
    public RegisteredUsersData getRegisteredUsers() {
        return registeredUsers;
    }

    /**
     * Sets the value of the registeredUsers property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegisteredUsersData }
     *     
     */
    public void setRegisteredUsers(RegisteredUsersData value) {
        this.registeredUsers = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="entry" maxOccurs="unbounded" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "entry"
    })
    public static class CreatedPosts {

        protected List<Entry> entry;

        /**
         * Gets the value of the entry property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the entry property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEntry().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Entry }
         * 
         * 
         */
        public List<Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<Entry>();
            }
            return this.entry;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "key",
            "value"
        })
        public static class Entry {

            protected String key;
            protected Integer value;

            /**
             * Gets the value of the key property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getKey() {
                return key;
            }

            /**
             * Sets the value of the key property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setKey(String value) {
                this.key = value;
            }

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setValue(Integer value) {
                this.value = value;
            }

        }

    }

}
