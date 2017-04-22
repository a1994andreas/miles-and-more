/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication;

import java.util.Random;

/**
 *
 * @author Andreas
 */
public class TestSpeedMain {
    
    public static void main(String[] args) {
        long[] rand_members_ids= new long[150];
        
        CSVParser csvParser = new CSVParser();
        Members members = new Members();
        members.setMemberslist(csvParser.parseSorted("miles_per_existing_member.csv"));
        
        Random randomGenerator = new Random();
        //pick 150 random ids 
        for(int k=0; k<rand_members_ids.length ;k++){
            rand_members_ids[k] =members.getMemberslist().get(randomGenerator.nextInt(members.getMemberslist().size())).getMember_id();
        }
        
        //call rank function for those ids
        long startTime =System.currentTimeMillis();
        for(int i=0 ; i<rand_members_ids.length ;i++)
            members.getMembersRank(rand_members_ids[i]);
        long endTime =System.currentTimeMillis();
        System.out.println("Time needed: "+(endTime-startTime)+" ms");
    
    }
    
}
