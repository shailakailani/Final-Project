/**
 * @author Shaila Lewis
 * @since 04.30.26
 */

package shai.kelv.calofficials.calgov.entity;

public enum OfficialType{
    SENATOR,
    CONGRESSMAN,
    ASSEMBLY_MEMBER;

    public MapType getMapType(){
        switch(this){
            case SENATOR:
                return MapType.SENATE;
            case CONGRESSMAN:
                return MapType.CONGRESS;
            default:
                return MapType.ASSEMBLY;        
        }
    }

    public String toString(){
        switch(this){
            case SENATOR:
                return "Senator";
            case CONGRESSMAN:
                return "Congress Member";
            default:
                return "Assembly Member";        
        }
    }
}

