package javaapplication;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Andreas paravoliashs
 */
public class MainClass {
    
    public static Members members;
    
    public static void main(String[] args) {
        CSVParser csvParser = new CSVParser();
        members = new Members();
        members.setMemberslist(csvParser.parseSorted("miles_per_existing_member.csv"));


/*uncomment to redirect System.in to file input*/
//            try {
//                System.setIn(new FileInputStream("get_rank.txt"));
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
//            }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.println("Menu options:");
            System.out.println("1.add_member");
            System.out.println("2.add_points");
            System.out.println("3.get_rank");
            System.out.println("4.save");
            System.out.println("5.exit");
            try {
                String line =br.readLine();
                switch(line){
                    case "add_member":
                        System.out.println("Insert new member's id: ");
                        String member_id =br.readLine();
                        member_id =check_member_id_length(br , member_id);
                        
                        System.out.println("Insert new member's miles(>1000): ");
                        String member_miles =br.readLine();
                        long long_member_id = Long.valueOf(member_id);
                        long long_member_miles =Long.valueOf(member_miles);
                        MainClass.members.addMember(new MemberData(long_member_id ,long_member_miles));
                        
                        System.out.println("MEMBER ADDED["+member_id+","+member_miles+"]");
                        break;
                    case "add_points":
                        System.out.println("Insert existing member's id: ");
                        String existing_member_id =br.readLine();
                        existing_member_id=check_member_id_length(br , existing_member_id);
                        
                        System.out.println("Insert member's miles: ");
                        String more_member_miles =br.readLine();
                        MainClass.members.addPoints(Long.valueOf(existing_member_id),Long.valueOf(more_member_miles) );
                        System.out.println("POINTS ADDED");
                        break;
                    case "get_rank":
                        System.out.println("Insert member's id: ");
                        String member_id2 =br.readLine();
                        member_id2=check_member_id_length(br , member_id2);
                        
                        MenuRankOption menurank = new MenuRankOption(Long.valueOf(member_id2));
                        Thread tobj =new Thread(menurank);  
                        tobj.start(); 
                        System.out.println("get_rank");
                        break;
                    case "save":
                        csvParser.saveCSVFile(members.getMemberslist());    
                        System.out.println("SAVED SUCCESSFULLY");
                    case "exit":
                        return ;
                    default:
                        System.out.println("WRONG INPUT");
                }

            } catch (IOException ex) {
                Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        
        }
    }

    public static String check_member_id_length(BufferedReader br,String member_id){
    
        while((int)(Math.log10(Long.valueOf(member_id)) + 1)!=10){
            try {
                System.out.println("Wrong id length try again: ");
                member_id =br.readLine();
            } catch (IOException ex) {
                Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return member_id;
    }
    
    public static class MenuRankOption implements Runnable{
        private final  long member_id;
        public MenuRankOption(long member_id){
            this.member_id=member_id;
        }
        
        @Override
        public void run() {
            MainClass.members.getMembersRank(this.member_id);
        }
    
    }


}
