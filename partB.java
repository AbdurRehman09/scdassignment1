/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asssignment1;

/**
 *
 * @author Abdul Rehman
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

 interface Configuration {
    void display();
   float calculatecost(Item item);
}
class Item implements Configuration{
    private static int nextId = 1;
    protected int id;
    protected String title;
    protected int popularityCount;

     public Item() {
    }
    public Item(String title) {
        this.id = nextId++;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
     public int getp_c()
    {
        return popularityCount;
    }
    public void setp_c(int p_c)
    {
        popularityCount=p_c;
    } 
    void incrementPopularityCount()
    {
        popularityCount++;
    }
   @Override
    public void display() {
        System.out.println("ID: " + id + "\nTitle: " + title);
    }
    @Override
    public final float calculatecost(Item item)
    {
        float x=(float) 0.0;
        if(item instanceof Book)
        {
            x=(float)((Book) item).getprice();
            x=x+(float) (x*0.20)+200;   
        }
        else if(item instanceof Magazine)
        {
            x=(float)((Magazine) item).getprice();
            x=x*((float)((Magazine) item).getp_c());
        }
        else if(item instanceof Newspaper)
        {
            Newspaper New=(Newspaper)item;
            x=(float)15;
        }
        return x;
    }   
}
class Book extends Item {
    private String author;
    private int year;
    private int price;

    public Book(String title, String author, int year, int popularityCount, int price) {
        super(title);
        this.author = author;
        this.year = year;
        this.popularityCount = popularityCount;
        this.price = price;
    }
      @Override
    public int getp_c()
    {
        return popularityCount;
    }
     @Override
    public void setp_c(int p_c)
    {
        popularityCount=p_c;
    }
    public int getprice()
    {
        return price;
    }
    public void setprice(int p)
    {
        price=p;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    @Override
    public void display() {
        super.display();
        System.out.println("Author: " + author);
        System.out.println("Year: " + year);
        System.out.println("Popularity Count: " + popularityCount);
        System.out.println("Price: " + price);
    }
}
class Magazine extends Item {
    private ArrayList<String> authors;
    private String publisherCompany;
    private int price;

    public Magazine(String title, ArrayList<String> authors, String publisherCompany, int p_c, int price) {
        super(title);
        this.authors = authors;
        this.publisherCompany = publisherCompany;
        this.popularityCount = p_c;
        this.price = price;
    }
    public ArrayList<String> getAuthors() {
        return authors;
    }
    public String getpublisherCompany() {
        return publisherCompany;
    }
    public void setpublisherCompany(String pbc) {
        publisherCompany=pbc;
    }
    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }
    @Override
    public int getp_c()
    {
        return popularityCount;
    }
    @Override
    public void setp_c(int p_c)
    {
        popularityCount=p_c;
    }
    public int getprice()
    {
        return price;
    }
    public void setprice(int p)
    {
        price=p;
    }
    @Override
    public void display() {
        super.display();
        System.out.println("Authors: " + String.join(", ", authors));
        System.out.println("Publisher Company: " + publisherCompany);
        System.out.println("Popularity count: " + popularityCount);
        System.out.println("Price: " + price);
    }
}
class Newspaper extends Item {
    private String publisherCompany;
    private String publicationDate;
    public Newspaper(String title, String publisherCompany,int p_c ,String publicationDate) {
        super(title);
        this.publisherCompany = publisherCompany;
        this.publicationDate = publicationDate;
        this.popularityCount=p_c;
    }
     public String getPublisherCompany() {
        return publisherCompany;
    }
    public void setPublisherCompany(String publisherCompany) {
        this.publisherCompany = publisherCompany;
    }
    public String getPublicationDate() {
        return publicationDate;
    }
    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }
    @Override
    public int getp_c()
    {
        return popularityCount;
    }
    @Override
    public void setp_c(int p_c)
    {
        popularityCount=p_c;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Publisher Company: " + publisherCompany);
        System.out.println("PopularityCount: " + popularityCount);
        System.out.println("Publication Date: " + publicationDate);
    }
}
class Borrower {
    private  static int nextid=1;
    private int id;
    private String name;
    private ArrayList<Item> borrowedItems;

    public Borrower( String name) {
        this.id = nextid++;
        this.name = name;
        this.borrowedItems = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Item> getBorrowedItems() {
        return borrowedItems;
    }

    public boolean addborrowItem(Item item) {
        if(!borrowedItems.contains(item))
        {
            item.incrementPopularityCount();
            borrowedItems.add(item);
            return true;
        }
        else
        {
            System.out.println("You have already borrowed it");
        }
        return false;
    }
}

class Library {
    protected ArrayList<Item> items;//available;
    private ArrayList<Borrower> libborrowers;

    public Library() {
        items = new ArrayList<>();
        libborrowers=new ArrayList<>();
    }
    public void addItem(Item newItem) {
        items.add(newItem);
    }
     public Item getitembyid(int id)
    {
        Item item=null;
        for(int i=0;i<items.size();i++)
        {
            if(items.get(i).getId()==id)
            {
                item=items.get(i);
            }
        }
        return item;
    }
    public void edititem(int id, Item it1)
    {
        Iterator<Item>it=items.iterator();
        while(it.hasNext())
        {
            Item item=it.next();
            if(item.getId()==id)
            {
                if(item instanceof Book && it1 instanceof Book)
                {
                    Book curr=(Book)item;
                    Book New=(Book)it1;
                    curr.setTitle(New.getTitle());
                    curr.setAuthor(New.getAuthor());
                    curr.setYear(New.getYear());
                    curr.setp_c(New.getp_c());
                    curr.setprice(New.getprice());
                }
                else if(item instanceof Magazine && it1 instanceof Magazine )
                {
                    Magazine currM = (Magazine) item;
                Magazine NewM = (Magazine) it1;
                currM.setTitle(NewM.getTitle());
                currM.setAuthors(NewM.getAuthors());
                currM.setpublisherCompany(NewM.getpublisherCompany());
                currM.setp_c(NewM.getp_c());
                currM.setprice(NewM.getprice());
                }
                else if(item instanceof Newspaper && it1 instanceof Newspaper)
                {
                     Newspaper currNp = (Newspaper) item;
                    Newspaper newnp = (Newspaper) it1;
                currNp.setTitle(newnp.getTitle());
                currNp.setPublisherCompany(newnp.getPublisherCompany());
                currNp.setp_c(newnp.getp_c());
                currNp.setPublicationDate(newnp.getPublicationDate());
                }
                else
                {
                System.out.println("Invalid item type.");
                return;
                }
            }
            else
            {
                 System.out.println("Invalid item type.");
                return;
            }
             System.out.println("Item with ID " + id + " has been edited.");
            return;
        }
        System.out.println("notfound!");
    }
    public void displayAllItems() {
        
        Iterator<Item>it=items.iterator();
        while(it.hasNext())
        {
            Item it1=it.next();
            it1.display();
        }
    }
     public void deleteitem(int id)
    {
        for(int i=0;i<items.size();i++)
        {  
           if(items.get(i).getId()==id)
           {
                
                items.remove(items.get(i));
                return;
           }
           else
           {
               System.out.println("Wrong id! entered");
           }
        }
    }
      public void displayitemById(int id) {
        for (int i=0;i<items.size();i++) {
            if (items.get(i).getId() == id) {
                 
                items.get(i).display();
                return;
            }
        }
        System.out.println("Book with ID " + id + " not found.");
    }
      public void displayitem(Item item)
      {
          item.display();
      }
      public boolean borrowItem(Borrower newborrower, Item item)
      {
          boolean found=false;
          
              for (int i = 0; i < libborrowers.size(); i++) {
                  if (libborrowers.get(i).getBorrowedItems().contains(item)) {
                      found = true;
                  }
              }
             if (!found && items.contains(item)) {
                  
                  if(newborrower.addborrowItem(item))
                  {    
                    items.remove(item);
                    libborrowers.add(newborrower);
                    System.out.println(newborrower.getName() + " has borrowed: " + item.getTitle());
                    return true;
                  }
              }
              else {
                 System.out.println("Item is not available for borrowing.");
               }
             return false;
        }
    public void readingfile() {
    try {
        BufferedReader reader = new BufferedReader(new FileReader("items.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            int itemType = Integer.parseInt(parts[0]);
            String title = parts[1];

            switch (itemType) {
                case 1:
                    String author = parts[2];
                    int year = Integer.parseInt(parts[3]);
                    int p_c=Integer.parseInt(parts[4]);
                    int cost=Integer.parseInt(parts[5]);
                    Item book = new Book(title, author, year,p_c,cost);
                    addItem(book);
                    break;
                case 2:
                    ArrayList<String> authors = new ArrayList<>();
                    for (int i = 2; i < parts.length - 3; i++) {
                        authors.add(parts[i]);
                    }
                    String publisherCompany = parts[parts.length - 3];
                    int p_cm = Integer.parseInt(parts[parts.length - 2]);
                    int costm=Integer.parseInt(parts[parts.length - 1]);
                    
                    Item magazine = new Magazine(title, authors, publisherCompany,p_cm,costm);
                    addItem(magazine);
                    break;
                case 3:
                    String publisherCompanyN = parts[2];
                    int p_cn=Integer.parseInt(parts[3]);
                    String publicationDate = parts[4];
                    Item newspaper = new Newspaper(title, publisherCompanyN,p_cn,publicationDate);
                    addItem(newspaper);
                    break;
                default:
                    System.out.println("Invalid item type in the file.");
            }
        }
        reader.close();
    } catch (IOException e) {
        System.out.println("Error reading data from file: " + e.getMessage());
    }
}
    public void displayHotPicks() {
    ArrayList<Item> hotPicks = new ArrayList<>(items);
    hotPicks.sort(Comparator.comparingInt(Item::getp_c).reversed());

    System.out.println("Hot Picks:");
    Iterator<Item>it=hotPicks.iterator();
    while(it.hasNext())
    {
        Item temp=it.next();
        System.out.println("Title: " + temp.getTitle() + ", Popularity Count: " + temp.getp_c());
    }

    }
     public void displayborrowers() {
    System.out.println("libborrowers:");
    Iterator<Borrower>it=libborrowers.iterator();
    while(it.hasNext())
    {
        Borrower temp=it.next();
        System.out.println("Borrowername: " + temp.getName() + "   id: " + temp.getId());
        System.out.println("Borroweditems are:" );
        Iterator<Item>it1=temp.getBorrowedItems().iterator();
        while(it1.hasNext())
        {
            Item temp1=it1.next();
            temp1.display();
        }
    }
}
     public void viewcost(int id)
     {
        Iterator<Item>it=items.iterator();
        boolean found=false;
        while(it.hasNext())
        {
            
            Item it1=it.next();
            if(it1.getId()==id)
            {
                found=true;
                float x=it1.calculatecost(it1);
                System.out.println("Cost of item is: "+x);
                break;
            }
        }
        if(!found)
        {
           System.out.println("your given id doesnot exist!");   
        }
     }

}
public class partB {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        library.readingfile();
        while (true) {
            System.out.println("Library Menu:");
            System.out.println("1. Add an item");
            System.out.println("2. Edit an item by ID");
            System.out.println("3. Delete an item by ID");
            System.out.println("4. View all items");
            System.out.println("5. View an item by ID");
            System.out.println("6. Display Hot Picks");
            System.out.println("7. Borrow an item");
            System.out.println("8. View borrowers list");
             System.out.println("9. View Cost of item");
             System.out.println("10.Quit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter the title of the item: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter the type of the item (1 for Book, 2 for Magazine, 3 for Newspaper): ");
                    int itemType = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    switch (itemType) {
                        case 1:
                            System.out.print("Enter the author of the book: ");
                            String author = scanner.nextLine();
                            System.out.print("Enter the year of publication of the book: ");
                            int year = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            System.out.print("Enter the popularity count of the book: ");
                            int popularityCount = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter the price of the book: ");
                            int price = scanner.nextInt();
                            scanner.nextLine();
                            Item newBook = new Book(title, author, year, popularityCount, price);
                            library.addItem(newBook);
                            break;
                        case 2:
                            ArrayList<String> authors = new ArrayList<>();
                            while (true) {
                                System.out.print("Enter an author of the magazine (or '.' to stop): ");
                                String authorName = scanner.nextLine();
                                if (authorName.equals(".")) {
                                    break;
                                }
                                authors.add(authorName);
                            }
                            System.out.print("Enter the publisher company of the magazine: ");
                            String publisherCompany = scanner.nextLine();
                            System.out.print("Enter the popularity count of the magazine: ");
                            int issueNumber = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter the price of the magazine: ");
                            int magPrice = scanner.nextInt();
                            scanner.nextLine();
                            Item newMagazine = new Magazine(title, authors, publisherCompany, issueNumber, magPrice);
                            library.addItem(newMagazine);
                            break;
                        case 3:
                            System.out.print("Enter the publisher company of the newspaper: ");
                            String newspaperPublisherCompany = scanner.nextLine();
                            System.out.print("Enter the popularity count of the magazine: ");
                            int pc = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter the publication date of the newspaper (DD-MM-YYYY): ");
                            String publicationDate = scanner.nextLine();
                            Item newNewspaper = new Newspaper(title, newspaperPublisherCompany,pc, publicationDate);
                            library.addItem(newNewspaper);
                            break;
                        default:
                            System.out.println("Invalid item type.");
                    }
                    System.out.println("Item added successfully.");
                    break;
                case 2:
                    // Implement edit item by ID
                    System.out.print("Enter the id of item: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                   
                    System.out.print("Enter the type of the item (1 for Book, 2 for Magazine, 3 for Newspaper): ");
                    int IT = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                     System.out.print("Enter the new title of the item: ");
                    String newtitle = scanner.nextLine();
                    switch (IT) {
                        case 1:
                            System.out.print("Enter the new author of the book: ");
                            String author = scanner.nextLine();
                            System.out.print("Enter the new year of publication of the book: ");
                            int year = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            System.out.print("Enter the new popularity count of the book: ");
                            int popularityCount = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter the new price of the book: ");
                            int price = scanner.nextInt();
                            scanner.nextLine();
                            Item newBook = new Book(newtitle, author, year, popularityCount, price);
                            library.edititem(id,newBook);
                            break;
                        case 2:
                            ArrayList<String> authors = new ArrayList<>();
                            while (true) {
                                System.out.print("Enter new author of the magazine (or '.' to stop): ");
                                String authorName = scanner.nextLine();
                                if (authorName.equals(".")) {
                                    break;
                                }
                                authors.add(authorName);
                            }
                            System.out.print("Enter the new  publisher company of the magazine: ");
                            String publisherCompany = scanner.nextLine();
                            System.out.print("Enter the new popularitycount of the magazine: ");
                            int popularitycount = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter the new price of the magazine: ");
                            int magPrice = scanner.nextInt();
                            scanner.nextLine();
                            Item newMagazine = new Magazine(newtitle, authors, publisherCompany, popularitycount, magPrice);
                            library.edititem(id,newMagazine);
                            break;
                        case 3:
                            System.out.print("Enter the new publisher company of the newspaper: ");
                            String newspaperPublisherCompany = scanner.nextLine();
                            System.out.print("Enter the new popularity count of the magazine: ");
                            int pc = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter the new publication date of the newspaper (DD-MM-YYYY): ");
                            String publicationDate = scanner.nextLine();
                            Item newNewspaper = new Newspaper(newtitle, newspaperPublisherCompany,pc, publicationDate);
                            library.edititem(id,newNewspaper);
                            break;
                        default:
                            System.out.println("Invalid item type.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the id of item: ");
                    int id1 = scanner.nextInt();
                    scanner.nextLine();
                    library.deleteitem(id1);
                    break;
                case 4:
                   library.displayAllItems();
                    break;
                case 5:
                    System.out.print("Enter the id of item: ");
                    int id2 = scanner.nextInt();
                    scanner.nextLine();
                    library.displayitemById(id2);
                    break;
                case 6:
                    library.displayHotPicks();
                    break;
                case 7:
                    System.out.print("Enter our name: ");
                    String name = scanner.nextLine();
                    Borrower B=new Borrower(name);
                    System.out.print("Enter how many items do u want:");
                    int n = scanner.nextInt();
                    scanner.nextLine();
                    int i=0;
                    int temp=0;
                    while(i<n)
                    {
         
                         System.out.print("Enter id of item u want:");
                         int idi=scanner.nextInt();
                         Item item=library.getitembyid(idi);
                         if(item!=null)
                         {
                            if(idi==temp)
                            {
                                System.out.print("You cannot borrow same item twice");
                                break;
                            }
                            else
                            {
                                if(library.borrowItem(B, item))
                                {
                                    System.out.print("Borrowed successfully");
                                }
                                else
                                {
                                    System.out.print("already ocuupied");
                                    break;
                                }
                            }
                         }else
                         {
                             System.out.print("wrong item id");
                             break;
                         }
                       temp=idi;
                       i++;
                    }
                     break;
                case 8:
                    library.displayborrowers(); 
                    break;
                case 9:
                   System.out.print("Enter the id of item: ");
                    int idcost = scanner.nextInt();
                    scanner.nextLine();
                    library.viewcost(idcost);
                    break;
                case 10:
                    System.out.println("Exiting the library.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
