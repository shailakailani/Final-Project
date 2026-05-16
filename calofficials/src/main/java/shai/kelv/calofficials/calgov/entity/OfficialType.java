/**
 * Description: Enum for official type to facilitate filtering in database
 * @author Shaila Lewis
 * @since 05.09.2026
 */

package shai.kelv.calofficials.calgov.entity;

public enum OfficialType{
    SENATOR,
    CONGRESSMAN,
    ASSEMBLY_MEMBER;

    /**
     * Converts map type to officer type
     * @return MapType for district
     */
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

    /**
     * Get official type that corresponds to a string
     * @param district
     * @return
     */
    public static OfficialType getOfficialType(Object off){
        switch(off.toString()){
            case "SENATOR":
                return OfficialType.SENATOR;
            case "CONGRESSMAN":
                return OfficialType.CONGRESSMAN;
            case "ASSEMBLY_MEMBER":
                return OfficialType.ASSEMBLY_MEMBER;      
            default:
                return null;      
        }
    }

   /**
    * Return string representation of official enum
    * @return string of official type representation
    */
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

