package shai.kelv.calofficials.calgov.entity;
import jakarta.persistence.DiscriminatorValue;

@DiscriminatorValue("ASSEMBLY")
public class AssemblyDistrict extends District{
    private MapType mapType = MapType.ASSEMBLY;
}
