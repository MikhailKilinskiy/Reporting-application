package org.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("CUSTOMER")
public class Customer extends Organisation {

    protected String ContractName;

    @Temporal(TemporalType.DATE)
    protected Date ContractDate;

    public String getContractName() {
        return ContractName;
    }

    public void setContractName(String contractName) {
        ContractName = contractName;
    }

    public Date getContractDate() {
        return ContractDate;
    }

    public void setContractDate(Date contractDate) {
        ContractDate = contractDate;
    }

    public Customer(long id, long version, String shortName, String fullName, String region, OrganisationType type, Date lastModified, String contractName, Date contractDate) {
        super(id, version, shortName, fullName, region, type, lastModified);
        ContractName = contractName;
        ContractDate = contractDate;
    }

    public Customer() {
    }
}
