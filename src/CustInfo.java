/*   
* CustInfo.java  
* This class is used to store basic variables and methods for the frame of this program.   
* Michael, Katherine, Constantine  
*/ 
import java.util.*;



import java.io.*;
import java.net.URL; 
public class CustInfo {     
  public static ArrayList<Customer> customer = new ArrayList<Customer>(); //to store Customer Info (Name, Psw, Rating and 3 answers)
  public static Book[] book = new Book[55]; //to store Book Info (Title, Author, Genre)
  public static int[][] ratingDataBase = new int[2][55]; //to store the average ratings of books     
  public static int countLogIn; //for count the time that the user entered wrong password     
  public static int countAnswer; //for count the time that the user entered wrong answer     
  public static String IDStore; //for temporarily store the ID preventing deleting Customer by mistake     
  public URL url = getClass().getResource("CustomerDatabase.txt");
  public File custData = new File(url.getPath());     
  public static int logInIndex;//to store the index of a current user     
  static int[] random = new int[3]; //for storing 3 random numbers represent 3 safety questions     
  static boolean saved; //to check if the user has saved the user name and password when signing up  
  public URL url2 = getClass().getResource("SafetyQuestionAnswer.txt");
  public File SafetyQuestion = new File(url2.getPath());    
  static String[] safetyQues = {"What is the name of your youngest cousin?",
                  "What is the full name of the best friend in your childhood?","What is your favorite fruit?"};     
  /**      
  * To initialize logInIndex, countLogIn, countAnswer, saved and IDStore;      
  * Pre: none;      
  * Post: Integers above are set to be 0, saved is set to be false and IDStore is set to be null.      
  */     
  public CustInfo() {         
    logInIndex = 0;        
    countLogIn = 0;       
    countAnswer = 0; 
    IDStore = null;         
    saved = false;     
  }
  /**      
   * To generate 3 random numbers represent 3 questions, which is for making random order of safety questions      
   * Pre: null,      * Post: 0, 1, 2 will be added in the array random[] with a random order.      
   */     
   public void geneRan() {         
     Random r = new Random();         
     random[0] = r.nextInt(3);          
     /*To ensure that r2 do not represent the same question as r1*/         
     do {             
       random[1] = r.nextInt(3);          
     } while(random[1] == random[0]);         
     /*To ensure that r3 do not represent the same question as either r1 or r2*/         
     if ((random[0] == 0 && random[1] == 1) || (random[0] == 1 && random[1] == 0)) {             
       random[2] = 2;         
     } else 
       if ((random[0] == 0 && random[1] == 2) || (random[0] == 2 && random[1] == 0)) {             
         random[2] = 1;         
       } else {             
         random[2] = 0;         
         }     
       }     
   /**      
   * To get the information of customers from databases and store them in three arraylists for names, passwords and rankings;      
   * Pre: none;      
   * Post: the names, passwords and the ranking for each books will be reserved in three arraylists.      
   */     
   public void getCust() {         
     customer.clear();
     FileReader out1, out2;
     BufferedReader readFile1, readFile2;         
      
     try {             
       out1 = new FileReader(custData);             
       readFile1 = new BufferedReader(out1); 
       out2 = new FileReader(SafetyQuestion);             
       readFile2 = new BufferedReader(out2); 
       /*To read the information of customers from the database and add it into arraylists*/
       String infoGot = readFile1.readLine();
       String pswGot = readFile1.readLine();
       String rateGot = readFile1.readLine();
       String ansGot1 = readFile2.readLine();
       String ansGot2 = readFile2.readLine();
       String ansGot3 = readFile2.readLine();
       while (infoGot != null)
       {
    	   customer.add(new Customer(infoGot,pswGot, rateGot, ansGot1, ansGot2, ansGot3));
    	   infoGot = readFile1.readLine();
           pswGot = readFile1.readLine();
           rateGot = readFile1.readLine();
           ansGot1 = readFile2.readLine();
           ansGot2 = readFile2.readLine();
           ansGot3 = readFile2.readLine();
       }
       readFile1.close();             
       out1.close();   
       readFile2.close();             
       out2.close();         
     } catch (FileNotFoundException e) {             
       System.out.println("The file does not exist.");             
       System.out.println(e.getMessage());         
     } catch (IOException e) {             
       System.out.println("The file is not possible to be read.");             
       System.out.println(e.getMessage());         
     }     
   }
   
 
   /**      
   * To delete a customer,      
   * Pre: name should not be null and should be the name of customer that needs to be deleted,      
   * Post: the requested customer will be deleted.      
   * @param name is the name of the user.      
   */     
   public void deleteCust(String name) {         
     customer.remove(new Customer(name));
     
     FileWriter in; //Write the information in the ArrayList without the deleted customer into files         
     BufferedWriter writeFile;         
     try {             
       in = new FileWriter(custData, false);             
       writeFile = new BufferedWriter(in);             
       /*Write name, password and rating into the file of custData*/             
       for (int i = 0; i < customer.size(); i++) {                 
         writeFile.write(customer.get(i).name);                 
         writeFile.newLine();                 
         writeFile.write(customer.get(i).psw);                 
         writeFile.newLine();
         writeFile.write(customer.get(i).rating);                 
         writeFile.newLine();
       } 
       writeFile.close();             
       in.close();             
       /*Write answers to safety questions to the file of SafetyQuestion*/             
       in = new FileWriter(SafetyQuestion, false);             
       writeFile = new BufferedWriter(in);             
       for (int i = 0; i < customer.size(); i++) {                
         writeFile.write(customer.get(i).answer1);                
         writeFile.newLine();                
         writeFile.write(customer.get(i).answer2);                
         writeFile.newLine();
         writeFile.write(customer.get(i).answer3);                
         writeFile.newLine();
       } 
       writeFile.close();
       in.close();        
     } catch (IOException e) {            
       System.out.println("The file cannot be written.");             
       System.out.println(e.getMessage());       
     }    
   }    
   /**      
   * To change the password of a customer,      
   * Pre: name and newPass should not be null,      
   * Post: the password will be changed to be newPass.    
   * @param name is the name of user.     
   * @param newPass is the new password.      
   */    
   public void changePass(String name, String newPass) {     
     int index = customer.indexOf(new Customer(name)); //store the index of the name        
     customer.get(index).setPsw(newPass);     
     FileWriter in; //Write the information in the ArrayList without the deleted customer into files         
     BufferedWriter writeFile;        
     try {           
       in = new FileWriter(custData, false);            
       writeFile = new BufferedWriter(in);           
       /*Write name, password and rating into the file of custData*/        
       for (int i = 0; i < customer.size(); i++) {                 
           writeFile.write(customer.get(i).name);                 
           writeFile.newLine();                 
           writeFile.write(customer.get(i).psw);                 
           writeFile.newLine();
           writeFile.write(customer.get(i).rating);                 
           writeFile.newLine();
         } 
       writeFile.close();         
       in.close();       
     } catch (IOException e) {    
       System.out.println("The file cannot be written.");          
       System.out.println(e.getMessage());      
     }    
   }
   
   /**    
   * To write the ranking that customers give to a particular book into the database;    
   * Pre: bookName should not be null and rank should be in the range from 1 to 5;    
   * Post: the rank that the customer gives will be add into the database, and true will be returned if the work of this 
   * method can be completed successfully.      
   * @param bookName is the book that the ranking will be given to.      
   * @param rank is the ranking that the customer gives.      
   * @return will be true if the work of this method can be completed successfully, otherwise it will be false.    
   */     
   public boolean rankGive(String bookName, int rank) {     
     int indexBook = -1; //To set an initial index value for the existence checking       
     /*To find the index of the book and record it*/       
     for (int i = 0; i < book.length; i++) {         
       if (book[i].title.equalsIgnoreCase(bookName)) {              
       indexBook = i;       
       break;        
     }      
   }
   char[] ranking = customer.get(logInIndex).rating.toCharArray();//To break string of ranking into chars   
   ranking[indexBook * 2] = Character.forDigit(rank, 10); //To convert the ranking given by the customer into character    
   String newRank = ""; 
   /*To form the new string of ranking*/        
   for (int i = 0; i < ranking.length; i++) {         
     newRank += ranking[i];     
   }
   customer.get(logInIndex).setRating(newRank);//To change string of ranking in the arraylist to the new one      
   FileWriter in;        
   BufferedWriter writeFile;       
   try {         
     in = new FileWriter(custData);        
     writeFile = new BufferedWriter(in);        
     /*To write the new information of customers to the database*/        
     for (int i = 0; i < customer.size(); i++) {                 
         writeFile.write(customer.get(i).name);                 
         writeFile.newLine();                 
         writeFile.write(customer.get(i).psw);                 
         writeFile.newLine();
         writeFile.write(customer.get(i).rating);                 
         writeFile.newLine();
       } 
     writeFile.close();     
     in.close();         
     changeAvg(indexBook, rank);      
   } catch (IOException e) {         
     System.out.println("The file is not possible to be written.");         
     System.out.println(e.getMessage());     
   } 
   return true;     
 }
   
 /**     
 * To recommend three books for a customer that gives higher than 3 score for a book;    
 * Pre: bookName should not be null;   
 * Post: the list of three books recommended is returned.    
 * @param bookName is the name of book that the customer has given ranking to.  
 * @return will be the list of three books that recommended.     
 */    
 public String[] bookBasedRec(String bookName) {   
   String[] recommended = new String[3]; //To reserve the books recommended        
   int indexBook = -1; //To set an initial value for checking the existence of bookName given       
   /*To find the index of book*/       
   for (int i = 0; i < book.length; i++) {        
     if (book[i].title != null) {      
       if (book[i].title.equalsIgnoreCase(bookName)) {       
         indexBook = i;    
         
         break;              
       }           
     }       
   }
   String theme = book[indexBook].genre; //To get the theme of book that the customer has given ranking
   ArrayList<String> name = new ArrayList<String>(); //To reserve the name of books with the same theme       
   ArrayList<Integer> rank = new ArrayList<Integer>();//To store ranking of books with the same theme      
   /*To find the books with the same theme and add the names and rankings into the arraylists above*/    
   for (int i = 0; i < book.length; i++) {      
     if (book[i].genre != null) {
       if (book[i].genre.equalsIgnoreCase(theme)) {   
    	 name.add(book[i].title);   
         rank.add(ratingDataBase[1][i]);              
       }          
     }        
   } 
   int tempRank; //For temporarily reserve an element during sorting      
   String tempName;        
   /*To sort the books in the arraylists by ranking from highest to lowest*/    
   for (int i = 1; i < name.size(); i++) {         
     tempRank = rank.get(i);        
     tempName = name.get(i);     
     int j; 
     /*To move the books has lower ranking than the temporary one with one index added*/           
     for (j = i - 1; j >= 0 && tempRank > rank.get(j); j--) {            
       rank.set(j + 1, rank.get(j));              
       name.set(j + 1, name.get(j));           
     }         
     rank.set(j + 1, tempRank); //Put the temporary element at the first index of arraylists         
     name.set(j + 1, tempName);         
   } 
   int i = 0, j = 0;  //i is the counter for the index of recommended[], j is the counter for the index of name ArrayList  
   /*Pick up three books with the same theme and highest scores and put them into the list of recommendation*/     
   while (i < recommended.length && j < name.size()) {   
     if (! name.get(j).equalsIgnoreCase(bookName)) {     
       recommended[i] = name.get(j);           
       i++;         
       j++;        
     } else {       
       j++; //Jump up to the next book if the book found is the book that the customer has read      
     }       
   } 
   return recommended;   
 }
 
 
 /**    
 * To calculate the new average of a book after rating, 
 * Pre: indexBook should not be larger than 54 and smaller than 0, rank should be 1, 2, 3, 4 or 5,   
 * Post: the new average score will be calculated     
 * @param indexBook is the index of book.    
 * @param rank is the new rank of book.     
 */     
 public void changeAvg(int indexBook,int rank){    
   if (GUIBookInfo.custrating == 0){       
   ratingDataBase[0][indexBook] *= ratingDataBase[1][indexBook];//to get total scores of the book         
   ratingDataBase[0][indexBook] += rank; //add the new rank on        
   ratingDataBase[1][indexBook] += 1; //add one more person of ranking 
   ratingDataBase[0][indexBook]=(int)(ratingDataBase[0][indexBook]/ratingDataBase[1]   
   [indexBook]+0.5); //calculate new avg      
 } else {         
   ratingDataBase[0][indexBook] *= ratingDataBase[1][indexBook];//to get total scores of the book      
   ratingDataBase[0][indexBook] += rank-GUIBookInfo.custrating;//make adjustment 
   ratingDataBase[0][indexBook]=(int)(ratingDataBase[0][indexBook]/ratingDataBase[1]  [indexBook]+0.5); //calculate new avg     
 } 
 refresh();//update the file of rating database     
 }    
 
 
 /**    
 * To change the text file storing the rating data of every book    
 * pre:the file rating exists     
 * post:the file has been updated with new information     
 */    
 public void refresh(){   
 FileWriter out;   
 BufferedWriter writeFile;     
 try{   

	 URL url3 = getClass().getResource("Rating.txt");
	 out  = new FileWriter(url3.getPath());     
   writeFile = new BufferedWriter(out);         
   for (int i = 0; i < 55; i++){           
     writeFile.write(String.valueOf(ratingDataBase[1][i]));              
     writeFile.newLine();         
   }//write number of readers to the file           
   for (int i = 0; i < 55; i++){         
     writeFile.write(String.valueOf(ratingDataBase[0][i]));              
     writeFile.newLine();      
   }//write average ratings to the file         
   writeFile.close();         
   out.close(); 
   } catch (IOException e){      
     System.err.println("IOException: " + e.getMessage());     
   } //catch any IOException the method may throw    
 }   
 
 
 /**    
 * Recommend three/two/one book(s) if the customer rates a book giving 1-3 scores    
 * pre: the book name that the customer rates   
 * post: the array list that contains recommended books      
 */   
 public String[] customerBasedRec(String bookName){   
   int booknumber = 0;//initialize the book index and counter      
   int count = 0;        
   String[] tempBookList = new String[3]; //Creat an array to store the recommended books' names  
   /*Search all the books and find the index number*/       
   for (int i = 0; i < 55; i++){      
     if (bookName.equalsIgnoreCase(book[i].title)){          
       booknumber = i;       
     }    
   }       
   /*Search all the rating*/     
   for (int j = 0; j < customer.size(); j++){    
     /*the system will not recommend the book that customer himself has read*/      
     if (j != logInIndex){             
       for (int k = 0; k < 55; k++){    
       int temp=k*2;//the rating in file presents as num+space+num...so we need two times k       
       String tempString = customer.get(j).rating;            
       String temp2 = tempString.substring(temp, temp+1); //get the rating     
       /*the rating needs to be between 1-3 and it is for the same book that the current customer rates.*/          
       if (k == booknumber && Integer.parseInt(temp2) >= 1 && Integer.parseInt(temp2) <= 3){      
         for (int p = 0; p < 55; p++){                        
           String temp3 = tempString.substring(p * 2,p * 2+1);    
           //After the other customer index has been identified, his rating for books that is within 4-5 /
           //will be searched. The counter is to make sure the recommended books are less than three.       
           if (Integer.parseInt(temp3) >= 4 && count < 3){            
             tempBookList[count] = book[p].title;                      
             count++;             
           }                    
         }                  
       }              
     }           
   }      
 }     
 /*If the book recommended is less than 3, randomly chose 3 books to recommend*/   
 if (tempBookList[2] == null) {          
   Random r = new Random(); 
   int tempIndex, tempIndex1, tempIndex2; //create three integers for store the random number 
   /*To check if redundant recommendation is existed*/        
   do {  
     tempIndex = r.nextInt(55);             
     tempIndex1 = r.nextInt(55);         
     tempIndex2 = r.nextInt(55);     
   } while (tempIndex == booknumber || tempIndex == tempIndex1 || tempIndex == tempIndex2);       
   /*To check how many books are needed and fill the book represented by the random numbers in*/       
   if (tempBookList[0] == null) {     
     tempBookList[0] = book[tempIndex].title;            
     tempBookList[1] = book[tempIndex1].title;         
     tempBookList[2] = book[tempIndex2].title;     
   } else if (tempBookList[1] == null && tempBookList[0] != null) {     
     tempBookList[1] = book[tempIndex1].title; 
     tempBookList[2] = book[tempIndex2].title;  
     } else {
       tempBookList[2] = book[tempIndex2].title;          
     }       
   } 
   return tempBookList;  
 }
 
 
 /**     
 * To add three answers for a customer to the database,   
 * pre: the three answers,   
 * post: the three answers will be stored in the database file.     
 * @param anwser1 is the answer to the first question.  
 * @param anwser2 is the answer to the second question.   
 * @param anwser3 is the answer to the third question.   
 */ 
 public void SafetyQuestion(String anwser1, String anwser2, String anwser3) {       
   FileWriter in;    
   BufferedWriter writeFile;   
   try {         
     in = new FileWriter(SafetyQuestion, true);          
     writeFile = new BufferedWriter(in);         
     writeFile.write(anwser1);        
     writeFile.newLine();         
     writeFile.write(anwser2);     
     writeFile.newLine();        
     writeFile.write(anwser3);       
     writeFile.newLine();         
     writeFile.close();       
     in.close();        
   } catch (IOException e) {       
   System.out.println("The file is not possible to be written.");       
   System.out.println(e.getMessage());      
 }     
 }    
 
 
 /**     
 * To check whether the customer input the correct answer to questions  
 * pre: the index of the question and the customer input    
 * post: true or false   
 * @param name is the name of user  
 * @param index is the index of question.    
 * @param answer is the answer of the question.  
 * @return will be "true" if the answer is correct and be "false" if not.    
 */   
 public boolean CheckQues(String name, int index, String answer) {   
   int index2 = customer.indexOf(new Customer(name));       
   boolean right = false;
   Customer tempCustomer = customer.get(index2);
   /*To check the index of the question*/   
   if (index == 0) {      
     /*To check if the answer is correct*/      
     if (tempCustomer.answer1.equals(answer)) {       
       right = true;         
     }    
   } else if (index == 1) {    
     if (tempCustomer.answer2.equals(answer)) {         
       right = true;    
     }       
   } else {  
     if (tempCustomer.answer3.equals(answer)) {      // maybe ignore case??   // add a static update to write file
       right = true;     
     }      
   }
   return right;  
 }
 } 


