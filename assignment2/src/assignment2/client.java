package assignment2;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class client {
	private static final int SPort = 4444;
	private static final String SHost = "localhost";	// we can use "localhost" as we are working on the same machine, no need to specify the IP Address
	public static String userInput;		// consider using two separate variables to store inputs so that you can keep track of the the login credentials 
	public static int flagUsername = 0;
	public static int flagPwd = 0;
	public static Socket clientSocket;	
	private static Scanner in = new Scanner(System.in);
	private static BufferedReader is;
	private static DataOutputStream os;
	public static ObjectInputStream inObj;
	public static ObjectOutputStream outObj;
	
	public static void connect() {
		try {
			clientSocket = new Socket(SHost, SPort);
		
			is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
			os = new DataOutputStream(clientSocket.getOutputStream());
			
			outObj = new ObjectOutputStream(clientSocket.getOutputStream());
			
			inObj = new ObjectInputStream(clientSocket.getInputStream());
			//os.writeBytes("Hello\n");
			
			Message msg = new Message("Establishing Connection.");
			
			outObj.writeObject(msg);
		
			//System.out.println("Client received " + is.readLine());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(clientSocket.isConnected()) {
			System.out.println("Connection Established");
		}
		else {
			System.out.println("Connection Failed!");
		}
		
	}
	
	public static void login(User user) {
				
			try {
				outObj.writeObject(user);
				flagUsername = inObj.readInt();
				if(flagUsername == 0) {
					System.out.println("Login Failed!\nTry again: ");
				}
				else {
					System.out.println("Login Compelted!");
					flagUsername = 1;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		
	}
	
	
	public static String getInput() {
		//System.out.println("Username: ");
		//Scanner scanUser = new Scanner(System.in);
		//Message
			String addEscape = in.next() + "\n";
		//	in.close();
			return addEscape;
		
	}	
	
	
	public static void sendUsername(String userInput) {
		try {

			os.writeBytes(userInput);
			flagUsername = is.read();
			System.out.println("Client received: username " + flagUsername);
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("End of senduser method\n");
	}

	
	public void sendPwd(String userInput) {
		try {
			//Socket clientSocket = new Socket(SHost, SPort); 	// creates a stream socket and connects it to the specified port and IP address
			
			//BufferedReader is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
// the output stream is here set to that it goes to the Socket and than through the constructor Socket(port, ip) goes to the target server ??? 
			//DataOutputStream os = new DataOutputStream(clientSocket.getOutputStream());
			
// [where does this stream get written?] look up maybe
			os.writeBytes(userInput);
			flagPwd = is.read();
			//System.out.println(is.readLine());
	
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(final String[] v) {
		
		connect();
		
		//------------------------------------------ Login ----------------------------
		while(flagUsername == 0) {
			// initialize a temporary object and ask the user to input credentials
			User usr = new User();
			System.out.println("Username: ");
			String temp = getInput();
			//System.out.println(temp);
			usr.setUsername(temp);
			System.out.println("Password: ");
			temp = getInput();
			//System.out.println(temp);
			usr.setPassword(temp);
			//System.out.println(usr.getUsername());
			//System.out.println(usr.getPassword());
			login(usr);
		}
		
		System.out.println("The End!");
		
		
		// Loop for Username
		/*while(flagUsername == 0) {
			System.out.println("Username: ");
			userInput = getInput();
			
			sendUsername(userInput);			
		}
		
		System.out.print("\nout of loop\n");
		
		// Loop for Password
		while(flagPwd == 0) {
			System.out.println("Password: ");
			userInput = getInput();		// consider using two separate variables to store inputs so that you can keep track of the the login credentials 
			
			new client().sendPwd(userInput);
		}
		

		
		
		
		
		
		
	/*	try {
			FileReader file = new FileReader("C:\\Users\\ibrah\\OneDrive\\Documenti\\UniPr\\III Anno\\Ingegneria Software\\LabJava\\Assignement2\\userBase.txt");
			Scanner myScan = new Scanner(file);
			
			while(myScan.hasNextLine()) {
				myScan.nextLine();
				users++;
			}
			
			System.out.println(users);
			
			// initialize the array of strings holding the usernames and the passwords
			userBase = new String[users+1];
			userPwd = new String[users+1];
			
			myScan.close();
			try {
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	//-------------------------------------------------- write array of strings --------------------------
			FileReader file2 = new FileReader("C:\\Users\\ibrah\\OneDrive\\Documenti\\UniPr\\III Anno\\Ingegneria Software\\LabJava\\Assignement2\\userBase.txt");
			Scanner myScan2 = new Scanner(file2);
			
			String data;
			// userBase = new String[users];
			
			while(myScan2.hasNextLine()) {
				
				data = myScan2.nextLine();
				String[] split = data.split(";");
				// System.out.println(data);
				userBase[users] = split[0];
				userPwd[users] = split[1];
				
				System.out.println(userBase[users] + " " + userPwd[users]);
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
		*/
	}
	
}
