package javaapplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Andreas paravoliashs
 */
public class Members {
    private ArrayList<MemberData> memberslist;
    
    public Members(){
        this.memberslist = new ArrayList<>();
    }
    
    public ArrayList<MemberData> getMemberslist(){
        return this.memberslist;
    }
    public void setMemberslist(ArrayList<MemberData> memberslist){
        this.memberslist=memberslist;
    }
  
    public void addMember(MemberData new_member){
        this.memberslist.add(new_member);
        sort_list();
    }
    
    public boolean addPoints(long member_id,long points ){
        for(MemberData element : this.memberslist) {
            if(element.getMember_id()== member_id){
                element.setTotal_miles(element.getTotal_miles()+points);
                sort_list();
                return true;
            }
        }
        return false;
    }
    
    public boolean getMembersRank(long member_id){
        for(int i=0; i<this.memberslist.size() ;i++){
            if(this.memberslist.get(i).getMember_id()== member_id){
                System.out.println("Member with ID "+member_id+" belong to the top "+ calculateIndexRank(i)+"% of our members");
                return true;
            }
        }
        return false;
    }
    
    private int calculateIndexRank(int index){
        return ((this.memberslist.size()-index)%100);
    }
    
    private void sort_list(){
        Comparator<MemberData> comparator = new Comparator<MemberData>() {
                @Override
                public int compare(MemberData left, MemberData right) {
                    return (int)(left.getTotal_miles() - right.getTotal_miles()); 
                }
            };
        Collections.sort(this.memberslist, comparator);
    }

}
