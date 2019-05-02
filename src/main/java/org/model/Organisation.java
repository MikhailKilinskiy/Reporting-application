package org.model;

import org.hibernate.annotations.GenerationTime;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "Organisations")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "OrganisationType",
                    discriminatorType = DiscriminatorType.STRING)
public abstract class Organisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long Id;

    public long getId() {
        return Id;
    }

    @Version
    protected long Version;

    protected String ShortName;

    protected String FullName;

    protected String Region;

    @Enumerated(EnumType.STRING)
    protected OrganisationType Type;

    @Temporal(TemporalType.TIMESTAMP)
    @Column (insertable = false, updatable = false)
    @org.hibernate.annotations.Generated(GenerationTime.ALWAYS)
    protected Date lastModified;

    public Date getLastModified() {
        return lastModified;
    }

    public String getShortName() {
        return ShortName;
    }

    public void setShortName(String shortName) {
        ShortName = shortName;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public OrganisationType getType() {
        return Type;
    }

    public void setType(OrganisationType type) {
        Type = type;
    }

    public Organisation(long id, long version, String shortName, String fullName, String region, OrganisationType type, Date lastModified) {
        Id = id;
        Version = version;
        ShortName = shortName;
        FullName = fullName;
        Region = region;
        Type = type;
        this.lastModified = lastModified;
    }

    public Organisation() {
    }
}
