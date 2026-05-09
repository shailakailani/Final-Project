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
}
