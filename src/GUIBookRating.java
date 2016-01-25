    
    /* 
    * GUIBookRating.java 
    * This class is for giving users a pop-up window for giving ranking to the book they've chosen. 
    * Constantine, Michael, Katherine 
    */ 
    import javax.swing.*; 
    import java.awt.*;
    import java.awt.event.*; 
    public class GUIBookRating {   
      JFrame frame;//Declare JFrame 
      JPanel contentPane;//Declare component panel    
      JLabel label;//Declare component label   
      JComboBox ratingList;//Declare component combobox   
      JButton submit, rankStop;//Declare component buttons  
      Font f = new Font("Times New Roman", Font.BOLD, 20);   
      /**      
      * Constructor to setup GUI components and event handling;    
      * Pre: name, author and theme should not be null, avg and pop should not be 0;    
      * Post: the user will successfully rate the book.  
      * @param bookName is the name of the book.      
      * @param author is the author of the book.    
      * @param genre is the genre of the book.   
      * @param avg is the average score of the book.     
      * @param pop is the population of people that have rated the book.   
      */  
      public GUIBookRating(Book book, int avg, int pop) {   
        frame = new JFrame("Book Rating");      
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        contentPane = new JPanel();      
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS)); //To use BoxLayout to line up items    
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //Create empty borders around items   
        label = new JLabel("Please give rank to the book " + book.title + ":");     
        label.setFont(f); //To keep the font of texts on items the same     
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT); //To line the items up at the centre of the window    
        label.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));     
        submit = new JButton("Submit");      
        submit.setFont(f);     
        submit.setAlignmentX(JLabel.CENTER_ALIGNMENT);   
        submit.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));       
        rankStop = new JButton("Not Now");        
        rankStop.setFont(f);  
        rankStop.setAlignmentX(JLabel.CENTER_ALIGNMENT);       
        rankStop.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));     
        String[] list = {"1 Awful >_<", "2 Bad T_T", "3 So So -_-", "4 Good ^_^", "5 Perfect ^3^"}; 
        //To create a list for user to choose rate from 1 to 5      
        ratingList = new JComboBox(list);   
        ratingList.setAlignmentX(JComboBox.CENTER_ALIGNMENT);       
        ratingList.setSelectedIndex(0); //Set the original rate to be 1    
        submit.addActionListener(new ActionListener() {        
          @Override       
          public void actionPerformed(ActionEvent e) {      
            String rankGet = (String)ratingList.getSelectedItem();       
            /*To transfer the rate user chosen in the combobox to the number of rankings in the database*/         
            if (rankGet.equalsIgnoreCase("1 Awful >_<")) {           
              GUIUserOpen.engine.rankGive(book.title, 1);            
              GUILogOut out = new GUILogOut(String.valueOf(1), String.valueOf(avg),  GUIUserOpen.engine.customerBasedRec (book.title)); 
            } else if (rankGet.equalsIgnoreCase("2 Bad T_T")) {
            	GUIUserOpen.engine.rankGive(book.title, 2);               
              GUILogOut out = new GUILogOut(String.valueOf(2), String.valueOf(avg),  GUIUserOpen.engine.customerBasedRec(book.title));         
            } else if (rankGet.equalsIgnoreCase("3 So So -_-")) {                
            	GUIUserOpen.engine.rankGive(book.title, 3);               
            GUILogOut out = new GUILogOut(String.valueOf(3), String.valueOf(avg),  GUIUserOpen.engine.customerBasedRec(book.title));      
            } else if (rankGet.equalsIgnoreCase("4 Good ^_^")) {              
            	GUIUserOpen.engine.rankGive(book.title, 4);              
            GUILogOut out = new GUILogOut(String.valueOf(4), String.valueOf(avg),  GUIUserOpen.engine.bookBasedRec(book.title));      
          } else {      
        	  GUIUserOpen.engine.rankGive(book.title, 5);      
            GUILogOut out = new GUILogOut(String.valueOf(5), String.valueOf(avg),  GUIUserOpen.engine.bookBasedRec(book.title));       
          } frame.setVisible(false); //Close the window after going to the next step        
        }  
      });     
      rankStop.addActionListener(new ActionListener() {     
        @Override    
        public void actionPerformed(ActionEvent e) {         
          GUIBookInfo info = new GUIBookInfo(book, avg, pop, GUIBookInfo.custrating);  
          //To review the information of the book if the user do not want to rate the book         
          frame.setVisible(false);         
        } 
      });       
      contentPane.add(label);     
      contentPane.add(ratingList);      
      contentPane.add(submit);    
      contentPane.add(rankStop);  
      frame.setContentPane(contentPane);      
      frame.pack();       
      frame.setVisible(true); //Open the pop-up window     
      frame.setLocationRelativeTo(null); //Set the location of the pop-up window at the centre of the window of computer    
    }     
    
  }
  
