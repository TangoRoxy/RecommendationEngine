/* 
    * GUIBookInfo.java  
    * This class is for displaying the information of the chosen book and let the user to search again or rate the book. 
    * Constantine, Katherine, Michael
    */
    import java.awt.*; 
    import javax.swing.*; 
    import java.awt.event.*;
    public class GUIBookInfo {  
      static int avgRank, popNum, custrating;    
      JFrame frame; //Declare JFrame     
      JPanel contentPane; //Declare component panel     
      JLabel nameLabel, authorLabel, genreLabel, avgLabel, popLabel, custLabel; //Declare component labels  
      JButton search, rank; //Declare component buttons 
      Font f20 = new Font("Times New Roman", Font.BOLD, 25);   
      Font f15 = new Font("Times New Roman", Font.BOLD, 15);  
      /**   
      * Constructor to setup GUI components and event handling;  
      * Pre: name, author and theme should not be null, avg and pop should not be 0;   
      * Post: the information of the book will be displayed.
      * @param book is the Book object.     
      * @param avg is the average score of the book.   
      * @param pop is the number of people that have rated the book.    
      * @param rating is the past rating of this book from the user   
      */    
      public GUIBookInfo(Book book, int avg, int pop, int rating) {    
        avgRank = avg; //the average rank of the book  
        popNum = pop; //the number of people that have rated the book       
        custrating = rating; //the last rating to this book by this user     
        frame = new JFrame("Book Information");     
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        contentPane = new JPanel();     
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));//Use BoxLayout to line up items   
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //Create empty borders around items    
        nameLabel = new JLabel(book.title);    
        nameLabel.setFont(f20); //To keep the font of texts on items the same  
        nameLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT); //To line the items up at the centre of the window      
        nameLabel.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));       
        authorLabel = new JLabel("The author of this book is: " + book.author);     
        authorLabel.setFont(f15);       
        authorLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT); 
        authorLabel.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));     
        genreLabel = new JLabel("The genre of this book is: " + book.genre);   
        genreLabel.setFont(f15);     
        genreLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);      
        genreLabel.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));  
        avgLabel = new JLabel("The average ranking of this book is: " + String.valueOf(avgRank));  
        avgLabel.setFont(f15);       
        avgLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);      
        avgLabel.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));    
        popLabel = new JLabel(String.valueOf(popNum) + " users have read this book.");     
        popLabel.setFont(f15);     
        popLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);      
        popLabel.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));  
        custLabel = new JLabel("Your rating of this book was: "+custrating);    
        custLabel.setFont(f15);      
        custLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);     
        custLabel.setBorder(BorderFactory.createEmptyBorder(5,12,5,12));  
        search = new JButton("Search Again");      
        search.setFont(f15);      
        search.setAlignmentX(JLabel.CENTER_ALIGNMENT);  
        search.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));     
        rank = new JButton("Rate it Now");     
        rank.setFont(f15);      
        rank.setAlignmentX(JLabel.CENTER_ALIGNMENT);        
        rank.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));   
        contentPane.add(nameLabel);      
        contentPane.add(authorLabel);       
        contentPane.add(genreLabel);       
        contentPane.add(avgLabel);    
        contentPane.add(popLabel);    
        contentPane.add(custLabel);    
        contentPane.add(search);     
        contentPane.add(rank);      
        frame.setContentPane(contentPane); 
        frame.pack();       
        frame.setVisible(true); //Open the pop-up window    
        frame.setLocationRelativeTo(null); //Set the location of the pop-up window at the centre of the computer  
        search.addActionListener(new ActionListener() {         
          @Override           
          public void actionPerformed(ActionEvent event) {       
            GUISearch searchGUI = new GUISearch(); //To search book again if user don’t want to rate the book       
            frame.setVisible(false); //Close the window after going to the next step        
          }      
        });      
        rank.addActionListener(new ActionListener() {         
          @Override           
          public void actionPerformed(ActionEvent event) {      
            GUIBookRating ratingGUI = new GUIBookRating(book, avgRank, popNum); 
            //To let the user rate the book        
            frame.setVisible(false);       
          }    
        });  
      }
    }