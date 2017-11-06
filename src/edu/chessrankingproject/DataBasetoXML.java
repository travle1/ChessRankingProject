/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chessrankingproject;

/**
 *
 * @author Travis Lowe
 */

import java.io.File;
import java.nio.file.Files;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class DataBasetoXML {
    private ArrayList<Player> dbPlayerList;
    DocumentBuilderFactory PlayerDocFactory;
    DocumentBuilder PlayerDocBuilder;
    Document PlayerDoc;
   
    NodeList nodeList;
    
    File xmlDBfile;
    public DataBasetoXML(){ // default constructor
        
    }
    
    
    
    public DataBasetoXML(String PlayerFirst, String PlayerLast)throws Exception{ // create a brand new database with a basic player as an argument
        PlayerDocFactory = DocumentBuilderFactory.newInstance();
        PlayerDocBuilder = PlayerDocFactory.newDocumentBuilder();
        
        PlayerDoc = PlayerDocBuilder.newDocument();
        
        Element PlayerDatabase = PlayerDoc.createElement("PlayerDatabase");
        PlayerDoc.appendChild(PlayerDatabase);
        
        Element player = PlayerDoc.createElement("Player");
        PlayerDatabase.appendChild(player);
        
        Element firstName = PlayerDoc.createElement("FirstName");
        firstName.appendChild(PlayerDoc.createTextNode(PlayerFirst));
        player.appendChild(firstName);
        
        Element lastName = PlayerDoc.createElement("lastName");
        lastName.appendChild(PlayerDoc.createTextNode(PlayerLast));
        player.appendChild(lastName);
        
        Element gamesPlayed = PlayerDoc.createElement("gamesPlayed");
        gamesPlayed.appendChild(PlayerDoc.createTextNode("0"));
        player.appendChild(gamesPlayed);
        
        Element gamesWon = PlayerDoc.createElement("gamesWon");
        gamesWon.appendChild(PlayerDoc.createTextNode("0"));
        player.appendChild(gamesWon);
        
        Element gamesLost = PlayerDoc.createElement("gamesLost");
        gamesLost.appendChild(PlayerDoc.createTextNode("0"));
        player.appendChild(gamesLost);
        
        Element gamesDrawn = PlayerDoc.createElement("gamesDrawn");
        gamesDrawn.appendChild(PlayerDoc.createTextNode("0"));
        player.appendChild(gamesDrawn);
        
        Element highestRating = PlayerDoc.createElement("highestRating");
        highestRating.appendChild(PlayerDoc.createTextNode("0"));
        player.appendChild(highestRating);
        
        Element lowestRating = PlayerDoc.createElement("lowestRating");
        lowestRating.appendChild(PlayerDoc.createTextNode("0"));
        player.appendChild(lowestRating);
        
        Element currentRank = PlayerDoc.createElement("currentRank");
        currentRank.appendChild(PlayerDoc.createTextNode("0"));
        player.appendChild(currentRank);
        
        Element DatabaseRank = PlayerDoc.createElement("DatabaseRank");
        DatabaseRank.appendChild(PlayerDoc.createTextNode("0"));
        player.appendChild(DatabaseRank);
        
        TransformerFactory TrannyFactory  = TransformerFactory.newInstance();
        Transformer tranny = TrannyFactory.newTransformer();
        tranny.setOutputProperty(OutputKeys.INDENT, "yes");
        tranny.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        DOMSource source  = new DOMSource(PlayerDoc);
        
        
        
        Path root = Paths.get("C://ChessGame");
        //Path root = Paths.get(System.getProperty("user.home"), "ChessGame");
        
        try {
            Files.createDirectory(root);// create chessGame directory in windows or linux (maybe mac??)
        }
        catch(FileAlreadyExistsException e){
            System.out.println("Its ok. The Chess Game File directory already exists. Carry on");
        }
        catch (Exception e) {
            System.out.println("Failed because" + e);
        }
 
        
        
        
        
        StreamResult streamResult = new StreamResult(new File("C://ChessGame//PlayerDataBase.xml"));
        
        tranny.transform(source, streamResult);
        
        
        
    }
    
    
    
   

    public ArrayList<Player> getAllPlayersSorted(ArrayList<Player> inPlayers)throws Exception {
        xmlDBfile = new File("C://ChessGame//PlayerDataBase.xml");
        
        PlayerDocFactory = DocumentBuilderFactory.newInstance();
        PlayerDocBuilder = PlayerDocFactory.newDocumentBuilder();
        
        PlayerDoc = PlayerDocBuilder.parse(xmlDBfile);
        PlayerDoc.getDocumentElement().normalize();
        
        
        nodeList = PlayerDoc.getElementsByTagName("Player");
        dbPlayerList = new ArrayList<>();
        for(int i = 0; i< nodeList.getLength();i++){
         
         Node node = nodeList.item(i);
         Player tempPlayer = new Player(node) ;
         dbPlayerList.add(tempPlayer);
         inPlayers.add(tempPlayer);
        
            System.out.println("Pulled Player " + tempPlayer.getFirstName() + " " + tempPlayer.getLastName() + " from database");
            
        }
           
        Collections.sort(dbPlayerList, Comparator.comparing(Player::getLastName));
        
        return dbPlayerList;
    }

    void recalculatePlayers(ArrayList<Player> newPlayerList, ArrayList<Player> oldPlayerList) {
        
        System.err.println("need to add mee!!!!!!!!!");
        
        // use ELO math to calculate new chess ranking.
        // compare new and old lists. sort by ranking.
        // reassign players rank in database also alphabetically
        //call rewrite database with this new list
    }
    
    private void rewriteDatabase(ArrayList<Player> DatabaseList){
    
    
    }
    
    
     public void AddPlayertoDatabase(Player newPlayer){
        // this function should throw an exception if no database exists
        // this function should throw an exception if player already exists
        
        // add xml code to search through file to check if player exists. if not update database with new player
    
    }
    
    private void removePlayerFromDatabase(Player toRemovePlayer){
    
        // parse through database by id ??
        
    }
}



     