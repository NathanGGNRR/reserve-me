package groupe.two.diiage.reserveme.models;

import java.io.Serializable;

public class LocationType implements Serializable {
    public Integer id;

    public String name;

    public LocationType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
