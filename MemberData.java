package javaapplication;

/**
 *
 * @author Andreas paravoliashs
 */
public class MemberData {
    private long member_id;
    private long total_miles;
    
    public MemberData(long member_id){
        this.member_id=member_id;
        this.total_miles=1000;
    }
           
    public MemberData(long member_id, long total_miles){
        this.member_id=member_id;
        if(total_miles>1000)
            this.total_miles=total_miles;
        else
            this.total_miles=1000;
    }
    
    public long getMember_id(){
        return this.member_id;
    }
    public void setMember_id(long member_id){
        this.member_id=member_id;
    }
    
    public long getTotal_miles(){
        return this.total_miles;
    }
    public void setTotal_miles(long total_miles){
        this.total_miles=total_miles;
    }
    
    @Override
    public String toString(){
       return "Member Id: "+this.member_id+" ,Total Miles: "+this.total_miles ;
    }

}
