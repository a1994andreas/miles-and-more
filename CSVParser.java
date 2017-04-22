package javaapplication;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Andreas paravoliashs
 */
public class CSVParser {
    
    private final ArrayList<MemberData> list;
    private String filepath;
    
    public CSVParser(){
        list = new ArrayList<>();
        this.filepath="";
    }
    
    public CSVParser(String filepath){
        list = new ArrayList<>();
        this.filepath=filepath;
    }
 
    public void setFilepath(String filepath){
        this.filepath=filepath;
    }
    public String getFilepath(){
        return this.filepath;
    }
    
    public void parse(){
        if(!this.filepath.equals(""))
            parse(filepath);
    }
    public ArrayList<MemberData> parse(String filepath) {
        try{
            BufferedReader in = new BufferedReader(new FileReader(filepath));
            //skip the first line which declares the file format
            in.readLine();
            String line;
            while((line = in.readLine()) != null)
            {
                String[] temp = line.split(",");
                if(temp.length>=2){
                    MemberData data = new MemberData(Long.valueOf(temp[0]),Long.valueOf(temp[1]));
                    list.add(data);
                }
                System.out.println(line);
            }
            in.close();
        }catch(IOException ex){
            System.out.println("[IOException]: "+ex.getMessage());
        }
        return this.list;
    }
    
    public ArrayList<MemberData> parseSorted(String filepath){
        parse(filepath);
        Comparator<MemberData> comparator = new Comparator<MemberData>() {
            @Override
            public int compare(MemberData left, MemberData right) {
                return (int)(left.getTotal_miles() - right.getTotal_miles()); 
            }
        };
        Collections.sort(this.list, comparator);
        return this.list;
    }
    
    public ArrayList<MemberData> parseSorted(String filepath,Comparator<MemberData> comparator){
        parse(filepath);
        Collections.sort(this.list, comparator);
        return this.list;
    }
    
    public void saveCSVFile(ArrayList<MemberData> member_list) throws FileNotFoundException{
        try(  PrintWriter out = new PrintWriter("miles_per_existing_member.csv")){
            out.println("memberID,miles");
            for(MemberData  member:  member_list){
                out.println( member.getMember_id()+","+member.getTotal_miles());
            }
            
        }
    
    }
    
}
