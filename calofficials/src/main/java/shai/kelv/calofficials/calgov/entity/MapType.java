/**
 * Description: Enum for map type to facilitate filtering in database
 * @author Shaila Lewis
 * @since 05.09.2026
 */

package shai.kelv.calofficials.calgov.entity;

public enum MapType {
    CONGRESS,
    SENATE,
    ASSEMBLY;

    public OfficialType getOfficialType(){
        switch(this){
            case SENATE:
                return OfficialType.SENATOR;
            case CONGRESS:
                return OfficialType.CONGRESSMAN;
            default:
                return OfficialType.ASSEMBLY_MEMBER;        
        }
    }
    public static OfficialType getOfficialType(Object str){
        switch(str.toString()){
            case "SENATE":
                return OfficialType.SENATOR;
            case "CONGRESS":
                return OfficialType.CONGRESSMAN;
            case "ASSEMBLY":
                return OfficialType.ASSEMBLY_MEMBER;   
            default:
                return null;     
        }
    }

    public static MapType getMapType(String str){
        switch(str){
            case "SENATE":
                return MapType.SENATE;
            case "CONGRESS":
                return MapType.CONGRESS;
            case "ASSEMBLY":
                return MapType.ASSEMBLY;      
            default:
                return null;      
        }
    }

   
}
