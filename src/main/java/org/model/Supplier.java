package org.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("SUPPLIER")
public class Supplier extends Organisation {

    protected String Person;

    protected String Phone;

    public String getPerson() {
        return Person;
    }

    public void setPerson(String person) {
        Person = person;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public Supplier(long id, long version, String shortName, String fullName, String region, OrganisationType type, Date lastModified, String person, String phone) {
        super(id, version, shortName, fullName, region, type, lastModified);
        Person = person;
        Phone = phone;
    }

    public Supplier(){
    }
}
