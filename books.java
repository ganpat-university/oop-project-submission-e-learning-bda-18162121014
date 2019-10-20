package Product_classes;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.File;
import java.awt.Desktop;
public class books
{
	static Scanner sc=new Scanner(System.in);
	private String name,import_name;
	private int cost;
	public char yn='n';
	static int maximum_size=0;
	public ArrayList<books> book=new ArrayList<>();
	public books()
	{
		try
		{
			book.add(new books("Ansi C by Balaguruswamy",650));
			book.add(new books("Basic Electrical Engineering by UA Bakshi",540));
			book.add(new books("Data structures using C by rema thareja",510));
			book.add(new books("Digital electronics by morris manno (2nd edition)",635));
		}
		catch(Exception fm)
		{
			System.out.println("We are unable to add a book because "+fm.getMessage());
		}
		for(books ob:book)
		{
			maximum_size=ob.name.length()>maximum_size?ob.getname().length():maximum_size;
		}
	}
	public books(String name,int cost)
	{
		setname(name);
		setcost(cost);
		setimport_name(name.concat(".pdf"));
	}
	private void setname(String name)
	{
		this.name=name;
	}
	public String getname()
	{
		return name;
	}
	private void setimport_name(String import_name)
	{
		this.import_name="books/";
		this.import_name=this.import_name.concat(import_name);
	}
	public String getimport_name()
	{
		return import_name;
	}
	private void setcost(int cost)
	{
		this.cost=cost;
	}
	public int getcost()
	{
		return cost;
	}
	private void setyn()
	{
		yn='y';
	}
	public char getyn()
	{
		return yn;
	}
	private void view()
	{
		int set,count=0,j=0;
		char purchase;
		System.out.print("\n+");
		for(j=0;j<maximum_size;j++)
		{
			System.out.print("-");
		}
		System.out.print("+---+");
		for(books i:book)
		{
			if(i.yn=='y')
			{
				System.out.print("\n|"+i.getname());
				for(j=0;j<maximum_size-i.getname().length();j++)
				{
					System.out.print(" ");
				}
				System.out.print("| "+count+" |");
			}
			else
			{
				System.out.print("\n|Not Purchased");
				for(j=0;j<maximum_size-9;j++)
				{
					System.out.print(" ");
				}
				System.out.print("|");
			}
			count++;
		}
		System.out.print("\n+");
		for(j=0;j<maximum_size;j++)
		{
			System.out.print("-");
		}
		System.out.print("+---+");
		System.out.print("\nEnter your choice :- ");
		set=sc.nextInt();
		if(set>=0 && set<book.size())
		{
			if(book.get(set).getyn()=='n')
			{
				System.out.print("Do you want to purchase this book named '"+book.get(set).getname()+"' worth INR "+book.get(set).getcost()+"? [y/n] :- ");
				purchase=sc.next().charAt(0);
				if(purchase==('y'))
				{
					book.get(set).setyn();
					read(book.get(set).getimport_name());
				}
			}
			else
			{
				read(book.get(set).getimport_name());
			}
		}
	}
	private void read(String import_name)
	{
		try
		{
			File read=new File(import_name);
			if(read.exists())
			{
				if(Desktop.isDesktopSupported())
				{
					Desktop.getDesktop().open(read);
				}
				else
				{
					throw new IOException("Awt Desktop is not supported");
				}
			}
			else
			{
				throw new IOException("System cannot load the required file");
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	private void purchase()
	{
		int set,count=0,j=0;
		char purchase;
		System.out.print("\n+");
		for(j=0;j<maximum_size;j++)
		{
			System.out.print("-");
		}
		System.out.print("+---+");
		for(books i:book)
		{
			if(i.yn=='n')
			{
				System.out.print("\n|"+i.getname());
				for(j=0;j<maximum_size-i.getname().length();j++)
				{
					System.out.print(" ");
				}
				System.out.print("| "+count+" |");
			}
			else
			{
				System.out.print("\n|Purchased");
				for(j=0;j<maximum_size-5;j++)
				{
					System.out.print(" ");
				}
				System.out.print("|");
			}
			count++;
		}
		System.out.print("\n+");
		for(j=0;j<maximum_size;j++)
		{
			System.out.print("-");
		}
		System.out.print("+---+");
		System.out.print("\nEnter your choice :- ");
		set=sc.nextInt();
		if(set>=0 && set<book.size())
		{
			if(book.get(set).yn=='n')
			{
				System.out.print("Do you really want to purchase this book worth INR "+book.get(set).cost+"? [y/n] :- ");
				purchase=sc.next().charAt(0);
				if(purchase==('y'))
				{
					book.get(set).setyn();
				}
			}
			else
			{
				System.out.println("You already have the book");
			}
		}
	}
	private void search()
	{
		char set;
		String bookname;
		int count=0;
		int i=0;
		try
		{
			BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter bookname to search :- ");
			bookname=reader.readLine();
			for(books ob:book)
			{
				if(bookname.equalsIgnoreCase(ob.getname()))
				{
					break;
				}
				count++;
			}
			if(count!=book.size())
			{
				if(book.get(count).getyn()=='n')
				{
					System.out.print("Do you want to purchase the book? [y/n] :- ");
					set=sc.next().charAt(0);
					if(set=='y')
					{
						book.get(count).setyn();
					}
				}
				else
				{
					System.out.print("Do you want to view the material? [y/n]? :- ");
					set=sc.next().charAt(0);
					if(set=='y')
					{
						read(book.get(count).getimport_name());
					}
				}
			}
			else
			{
				throw new IOException("Book Not found......");
			}
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
	public void homepage()
	{
		char set;
		System.out.println("+----------------------------------+\n|             Homepage             |\n+------------------------------+---+\n|View the material             | a |\n|Purchase a book               | b |\n|Search book                   | c |\n|Logout                        | d |\n+------------------------------+---+\n");
		System.out.print("Enter choice :- ");
		set=sc.next().charAt(0);
		switch(set)
		{
			case 'a':
			view();
			break;
			case 'b':
			purchase();
			break;
			case 'c':
			search();
			break;
			case 'd':
			break;
		}
		if(set!='d')
		{
			homepage();
		}
	}
}