package assignment2;

import java.io.*;
import java.net.*;	// contains the classes ServerSocket and Socket
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class server {
	private static final int SPORT = 4444;
	public static int usersnum = 0;		// holds the number of users (and passwords) in the userBase.txt file
	public static int products = 0;		// holds the number of products available in products.txt file
	public static String[] userBase;
	public static String[] userPwd;
	public static int flagUser = 0; 	// handles the loop of asking a correct username to the client
	public static int flagPwd = 0;
	private static ServerSocket dataServer;
	private static Scanner in = new Scanner(System.in);
	private static Socket clientSocket;
	private static BufferedReader is;
	private static DataOutputStream os;
	public static List<Product> productList = new ArrayList<Product>();
	public static int flagProductList = 0;
	private static ObjectInputStream inObj;
	private static ObjectOutputStream outObj;
	public static List<User> userbase = new ArrayList<User>();
	
	
	public static void connection() {
		try {
			dataServer = new ServerSocket(SPORT);
		
			clientSocket = dataServer.accept();
		
			//is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
			//os = new DataOutputStream(clientSocket.getOutputStream());

			outObj = new ObjectOutputStream(clientSocket.getOutputStream());
			
			inObj = new ObjectInputStream(clientSocket.getInputStream());
			
			Object o = inObj.readObject();
			if((o != null) && (o instanceof Message)) {
				Message msg = (Message) o;
				System.out.println("Server received: " + msg.getContent());
			}
			//System.out.println("check");
		
			//os.writeBytes("Connection Established!\n");
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// is this exception catch redundant? as i have previously (line 43ca) handled the situation with an if
			e.printStackTrace();
		}
		
		System.out.println("Connection Established!");
		}
	
	public static void login() {
		int i = 0;
		Object o;
			try {
				o = inObj.readObject();
				
				if(o != null && o instanceof User) {
					User usr = (User) o;
					
					while(i < usersnum) {
																		
						if(usr.getUsername().compareTo(userbase.get(i).getUsername() + "\n") == 0 
								&& usr.getPassword().compareTo(userbase.get(i).getPassword() + "\n") == 0) {
							outObj.writeInt(1);
							flagUser = 1;
						}
						else {
							outObj.writeInt(0);
						}
					}
					
				}
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		
		
		
		
		
	}
	
	//deprecated
	// Scanner class does not allow to reset the pointer of the reader; BufferedReader, on the other hand allows it through mark()
	public static void setUserBase() {
			try {
				FileReader file = new FileReader("C:\\Users\\ibrah\\OneDrive\\Documenti\\UniPr\\III Anno\\Ingegneria Software\\LabJava\\Assignement2\\userBase.txt");
				Scanner myScan = new Scanner(file);
				
				String data;
				
				while(myScan.hasNextLine()) {
					data = myScan.nextLine();
					String[] split = data.split(";");
					User tempUser = new User(split[0], split[1]);
					userbase.add(tempUser);
					usersnum++;
				}
				
				// debug how many lines have been read
				// System.out.println(users);
				
				// initialize the array of strings holding the usernames and the passwords
				//userBase = new String[users+1];
				//userPwd = new String[users+1];
				
				myScan.close();
				try {
					file.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			catch(FileNotFoundException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
		}
		
	//deprecated
	public static void populateUsersAndPwd () {
		try {
			FileReader file2 = new FileReader("C:\\Users\\ibrah\\OneDrive\\Documenti\\UniPr\\III Anno\\Ingegneria Software\\LabJava\\Assignement2\\userBase.txt");
			Scanner myScan2 = new Scanner(file2);
			
			String data;
			// userBase = new String[users];
			
			int i = 0;
			
			while(myScan2.hasNextLine()) {
				
				data = myScan2.nextLine();
				String[] split = data.split(";");
				// System.out.println(data);
				userBase[i] = split[0];
				userPwd[i] = split[1];
				//System.out.println(userBase[i] + " " + userPwd[i]);
				i++;
			}
			
			myScan2.close();
			try {
				file2.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
		
	public static void productInventory() {
		while(flagProductList == 0) {
			String tempName;
			double tempPrice;
			String tempId;
			System.out.println("Insert a product name: ");
			tempName = in.next();
			System.out.println("Insert the product's price: ");
			tempPrice = in.nextDouble();
			System.out.println("Insert the product's id: ");
			tempId = in.next();
			
			Product tempProduct = new Product(tempName, tempPrice, tempId);
			productList.add(tempProduct);
			
			String moreProducts;
			System.out.println("Do you want to add another product? Y/N");
			moreProducts = in.next();
			if(moreProducts.compareTo("Y") == 0 | moreProducts.compareTo("y") == 0) {
				continue;
			}
			if(moreProducts.compareTo("N") == 0 | moreProducts.compareTo("n") == 0) {
				flagProductList = 1;
				break;
			}
			else {
				System.out.println("Anwers not clear: no more products can be added to the list in this session.");
				break;
			}
			
		}
	}
	
	public static void printInventory() {
		int j = 0;

		System.out.println("| ---------------- |");
		System.out.println("| Name\tPrice\tId |");
		System.out.println("| ---------------- |");
		while(j < productList.size()) {
			System.out.println("| " +productList.get(j).name + "\t" + productList.get(j).price + "\t" + productList.get(j).id + " |");
			j++;
		}
		System.out.println("| ---------------- |");
	}
	
	//deprecated
	public static void loginUsername() {
		try {
			// https://docs.oracle.com/javase/8/docs/api/java/net/ServerSocket.html
			//ServerSocket dataServer = new ServerSocket(SPORT);
			
			// https://docs.oracle.com/javase/8/docs/api/java/net/Socket.html - 
			//ServerSocket.accept() waits for a connection to be made and than creates a socket
			//Socket clientSocket = dataServer.accept();
			
			// https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html - Constructor: creates a buffering character-input stream
			// https://docs.oracle.com/javase/8/docs/api/java/io/InputStreamReader.html - Constructor=InputStreamReader: from bytes to char  
			// getInputStream(): bytes from serialization (???)
			//BufferedReader is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			// https://docs.oracle.com/javase/8/docs/api/java/io/DataOutputStream.html - Constructor: data output stream to write on specified underlying stream [what is the underlying ouptputstream
			//getOutputSteam(): returns an output stream
			//DataOutputStream os = new DataOutputStream(clientSocket.getOutputStream());
			
			String username = is.readLine();
			//System.out.println("Server received: " + username);
			int i = 0;
// check userBase and act accordingly - can the o.writeByte() be executed also from another method? i mean to take code from line 112 to 127 and put it into a method
			while(i < usersnum) {
				if(username.compareTo(userbase.get(i).username) == 0) {
					flagUser = 1;
					break;
				}
				i++;
			}
			if(flagUser == 0) {
				//System.out.println("The username is not in the list - line 116");
				os.writeByte(0);
			}
			else if(flagUser == 1) {
				//System.out.println("Input User is in list. - line 120");
				os.writeByte(1);
			}
			
			
			//System.out.println("End of getuser method");
			//clientSocket.close();			
			//dataServer.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//deprecated
	public static void loginPwd() {
		try {
			// https://docs.oracle.com/javase/8/docs/api/java/net/ServerSocket.html
			//ServerSocket dataServer = new ServerSocket(SPORT);
			
			// https://docs.oracle.com/javase/8/docs/api/java/net/Socket.html - 
			// ServerSocket.accept() waits for a connection to be made and than creates a socket
			//Socket clientSocket = dataServer.accept();
			
			// https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html - Constructor: creates a buffering character-input stream
			// https://docs.oracle.com/javase/8/docs/api/java/io/InputStreamReader.html - Constructor=InputStreamReader: from bytes to char  
			// getInputStream(): bytes from serialization (???)
			//BufferedReader is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			// https://docs.oracle.com/javase/8/docs/api/java/io/DataOutputStream.html - Constructor: data output stream to write on specified underlying stream [what is the underlying ouptputstream
			// getOutputSteam(): returns an output stream
			//DataOutputStream os = new DataOutputStream(clientSocket.getOutputStream());
			
			String password = is.readLine();
			//System.out.println("Server received: " + password);
			int i = 0;
			while(i < usersnum) {
				if(password.compareTo(userbase.get(i).password) == 0) {
					flagPwd = 1;
					break;
				}
				i++;
			}
			if(flagPwd == 0) {
				//System.out.println("The password is not in the list - line 165");
				os.writeByte(0);
			}
			else if(flagPwd == 1) {
				//System.out.println("Password is correct. - line 169");
				os.writeByte(1);
				//os.writeBytes("You logged in successfully!");
			}
			
			
			
			//clientSocket.close();			
			//dataServer.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void instructions() {
		
	}
	
	public static void main(final String[] v) {
		
		setUserBase();
		
		System.out.println("Waiting for connection by client");
		
		//------------------------------------------ Set Inventory ----------------------------
				productInventory();
				//printInventory();
		
		//------------------------------------------ Connection ----------------------------
		
		connection();		
		
		// ------------------------------------ Login ------------------------------------
		
		while(flagUser == 0) {
			login();
		}
		
		// ------------------------------------ Instructions ------------------------------------
		
		Message instructions = new Message("Welcome to our online shop!\nPress");
		
		
		System.out.println("The End!");
		
		// ------------------------------------ Login Username ------------------------------------
		/*while(flagUser == 0) {
			loginUsername();
		}

		System.out.print("\nout of loop\n");
		
		// ------------------------------------ Login Password ------------------------------------
		while(flagPwd == 0) {
			loginPwd();
		}*/
		
			
	}
	
}
