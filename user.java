import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import Product_classes.books;
class user
{
	books objects=new books();
	static ArrayList<user> users=new ArrayList<>();
	static Scanner sc=new Scanner(System.in);
	private String username,password;
	static String cat="";
	static int push=0;
	static boolean iterate=false;
	static void getlist()
	{
		try
		{
			int i=0;
			BufferedReader read_name=new BufferedReader(new FileReader("Database/name.txt"));
			BufferedReader read_password=new BufferedReader(new FileReader("Database/password.txt"));
			BufferedReader read_yn=new BufferedReader(new FileReader("Database/yn.txt"));
			String line,line1,line2,temp_name="admin",temp_password="admin";
			char temp_yn='n';
			while((line=read_name.readLine())!=null && (line1=read_password.readLine())!=null && (line2=read_yn.readLine())!=null)
			{
				temp_name=line;
				temp_password=line1;
				try
				{
					users.add(new user(temp_name,temp_password));
					for(i=0;i<(line2.length());i++)
					{
						users.get(push).objects.book.get(i).yn=line2.charAt(i);
					}
					push++;
				}
				catch(Exception fm)
				{
					System.out.println("Some users cannot be added because "+fm.getMessage());
				}
			}
			read_name.close();
			read_password.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	static void setlist(int select)
	{
		try
		{
			BufferedWriter write_name=new BufferedWriter(new FileWriter("Database/name.txt",true));
			BufferedWriter write_password=new BufferedWriter(new FileWriter("Database/password.txt",true));
			write_name.write(users.get(select).getusername());
			write_name.append('\n');
			write_password.write(users.get(select).getpassword());
			write_password.append('\n');
			write_name.close();
			write_password.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	static void writeyn()
	{
		int i=0,j=0;
		try
		{
			BufferedWriter write_yn=new BufferedWriter(new FileWriter("Database/yn.txt",false));
			for(i=0;i<users.size();i++)
			{
				for(j=0;j<users.get(i).objects.book.size();j++)
				{
					cat=cat.concat(Character.toString(users.get(i).objects.book.get(j).getyn()));
				}
				write_yn.write(cat);
				write_yn.write('\n');
				cat="";
			}
			write_yn.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	public user(String username,String password)
	{
		setusername(username);
		setpassword(password);
	}
	private void setusername(String username)
	{
		this.username=username;
	}
	private String getusername()
	{
		return username;
	}
	private void setpassword(String password)
	{
		this.password=password;
	}
	private String getpassword()
	{
		return password;
	}
	public static void main(String args[])
	{
		int i=0,count=0,use=-1;
		char set;
		String temp_username,temp_password;
		if(!iterate)
		{
			getlist();
			iterate=true;
		}
		System.out.println("+------------------------+\n|Welcome to E-Learning   |\n+--------------------+---+\n|Register            | a |\n|Login               | b |\n|Exit the program    | c |\n+--------------------+---+\n");
		System.out.print("Enter your choice :- ");
		set=sc.next().charAt(0);
		switch(set)
		{
			case 'a':
			System.out.print("Enter username :- ");
			temp_username=sc.next();
			if(users.size()==0)
			{
				System.out.print("Enter password :- ");
				temp_password=sc.next();
				try
				{
					users.add(new user(temp_username,temp_password));
				}
				catch(Exception fm)
				{
					System.out.println("User cannnot be created because "+fm.getMessage());
				}
			}
			else
			{
				for(user obj:users)
				{
					if(obj.getusername().equalsIgnoreCase(temp_username))
					{
						System.out.println("Username already exists");
						break;
					}
					count++;
				}
				if(count==users.size())
				{
					System.out.print("Enter password :- ");
					temp_password=sc.next();
					try
					{
						users.add(new user(temp_username,temp_password));
					}
					catch(Exception fm)
					{
						System.out.println("User cannot be created because "+fm.getMessage());
					}
				}
			}
			main(args);
			break;
			case 'b':
			System.out.print("Enter username :- ");
			temp_username=sc.next();
			for(i=0;i<users.size();i++)
			{
				if(users.get(i).getusername().equalsIgnoreCase(temp_username))
				{
					use=i;
					break;
				}
			}
			try
			{
				if(use!=-1)
				{
					System.out.print("Enter password :- ");
					temp_password=sc.next();
					if(temp_password.equals(users.get(use).getpassword()))
					{
						System.out.println("Welcome "+users.get(use).getusername());
						users.get(use).objects.homepage();
					}
					else
					{
						throw new Exception("Wrong Password");
					}
				}
				else
				{
					throw new Exception("User not found");
				}
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			main(args);
			break;
			case 'c':
			for(i=push;i<users.size();i++)
			{
				setlist(i);
			}
			writeyn();
			break;
			default:
			System.out.println("Please enter from given choices only");
			main(args);
			break;
		}
	}
}