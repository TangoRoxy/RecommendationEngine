/* 
* Sign.java 
* This class is used to get information from databases.  
* Katherine, Michael, Constantine
*/ 
import java.io.*;
import java.net.URL; 
public class Sign {   
  /**   
  * To get the information about books from databases;    
  * Pre: null;    
  * Post: the information of books will be got from databases.   
  */   
  public void setArray(){    
    URL url = getClass().getResource("BookDatabaseName.txt");
    File title = new File(url.getPath());
    URL url2 = getClass().getResource("BookAuthor.txt");
    File author = new File(url2.getPath());
    URL url3 = getClass().getResource("BookGenre.txt");
    File genre = new File(url3.getPath());
    URL url4 = getClass().getResource("Rating.txt");
    File rating = new File(url4.getPath());
    FileReader in;
    BufferedReader readFile;    
    String lineOfText; //to store information that read from files   
    try {       
      in = new FileReader(title);           
      readFile = new BufferedReader(in);      
      for (int i = 0; i < 55; i++) {
    	CustInfo.book[i] = new Book();
        lineOfText = readFile.readLine();    
        if (lineOfText != null) {          
          CustInfo.book[i].title = lineOfText;          
        }//store titles of books in an array     
      } 
      readFile.close();        
      in.close();       
      in = new FileReader(author);   
      readFile = new BufferedReader(in);      
      for (int i = 0; i < 55; i++) {      
        lineOfText = readFile.readLine();    
        if (lineOfText != null) {         
        	CustInfo.book[i].author = lineOfText;         
        }//store author names into array      
      } 
      readFile.close();  
      in.close();       
      in = new FileReader(genre);      
      readFile = new BufferedReader(in);      
      for (int i = 0; i < 55; i++) {        
        lineOfText = readFile.readLine();    
        if (lineOfText != null) {           
        	CustInfo.book[i].genre = lineOfText;          
        }//store genres of book into array        
      } 
      readFile.close();         
      in.close();         
      in = new FileReader(rating);        
      readFile = new BufferedReader(in);    
      for (int j = 0; j < 55; j++){               
        CustInfo.ratingDataBase[1][j]=Integer.parseInt(readFile.readLine());      
      }//read and store reader numbers of books      
      for (int i = 0; i < 55; i++){      
        CustInfo.ratingDataBase[0][i] = Integer.parseInt(readFile.readLine());        
      }//read and store average ratings      
    } catch (FileNotFoundException e){      
      System.err.println("FileNotFoundExceotion:" + e.getMessage());       
    } catch (IOException e){           
      System.err.println("IOException: " + e.getMessage()); 
    }    
  } 
} 
