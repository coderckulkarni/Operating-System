import java.io.*;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;


public class OperatingSystem extends CPU implements Memory {
	
	static FileInputStream fis;
	static FileReader fr;
	static FileWriter wr;
	static BufferedReader br;
	static BufferedWriter bwr;
	static FileOutputStream fos;
	public String[] fourletters = new String[20];
	char word[];
	int address;
	boolean terminate = false;
	static String ir1;
	static int count;
	static int counter = 0;
	static int pgtable = 0;
	static int pageno;
	static int z;
	static char[][] drum = new char[100][4]; 
	static Buffer[] bufferclassarray = new Buffer[10];
	static Queue<Buffer> inputfullbuffer ,emptybuffer,outputfullbufferqueue; 
	static Queue<PCB> loadQ ,readyQ,terminateQ,IOQ;
	static Buffer channel1eb,channel2eb,channel3eb=null;
	
	
	
	public OperatingSystem() {
		super();
		Queue<Buffer> ifb = new ArrayBlockingQueue<>(10);
		Queue<Buffer> eb = new ArrayBlockingQueue<>(10);
		Queue<Buffer> ofb = new ArrayBlockingQueue<>(10);
		Queue<PCB> loadQ = new ArrayBlockingQueue<>(10);
		Queue<PCB> readyQ = new ArrayBlockingQueue<>(10);
		Queue<PCB> terminateQ = new ArrayBlockingQueue<>(10);
		
		/*while(queue size)
        {
        	//enqueue buffer objects in emptybuffer
        	//set choiceofqueueinPCB to 0
        	
        }*/
        //Creating temporary buffers to access same Buffer for Next time to do Remaining Job
       
	}
	public void simulation()
	{
	 
		   if(flag1=="busy")
		   {
			   increment channel_1_timer 
		   }
		   if(flag2==busy)
		   {
			   increment channel_2_timer
		   }
		   if(flag3==busy)
		   {
			   increment channel_3_timer
		   }
		  
		   if(channel_1_timer==5)
		   {
			   IOI=IOI+1;
		   }
		   if(channel_2_timer==5)
		   {
			   IOI=IOI+2;
		   }
		   if(channel_3_timer==2)
		   {
			   IOI=IOI+4;
		   }
	   
	}
	
	
	
	public int Substringsandstore(String s,int pos) throws IOException //gets substring and store to memory
	{
		
		int k = 0;
		int l =pos;
		String sub = null;
		int t = 0;
		int flag = 0;
		
		while(t<37)
		{
			sub = s.substring(t,t+4);
			
			for(int f = 0; f<4 ;++f)
			{
				memory[l][f] = sub.charAt(f);
			}
			t=t+4;
			l++;
		}
		return flag;
				
	}
	
	int Nearestmult10() throws IOException, InterruptedException //To find the nearest multiple of 10
	{
		int op = getOperand();
		int address = (op/10)*10;
		return address;
	}
	

	void read() throws IOException//GD executes this.
, InterruptedException
	{
		
		int pos = Nearestmult10();
		int op = getOperand();
		String line = br.readLine();
		if(line.contains("$END"))
		{
			Terminate(1);
		}
		/*int a = AllocatePage();*/
		//pos = pos/10;
		OperatingSystem.counter = z;
		UpdatePageTable(pageno);
		Substringsandstore(line,pos);
	}
	
	String getwordfrommemory(int location)
	{
		String words = null;
		words = String.valueOf(memory[location]);
		return words;
	}
	
	
	String getlinefrommemory() throws IOException, InterruptedException
	{
		int pos = Nearestmult10();
		
		
		String ch = "";
		for(int i = pos; i<(pos+10); ++i)
		{
			ch = ch + getwordfrommemory(i);
		}
		return ch;
	}
	
	void write() throws IOException, InterruptedException
	{
		/*Initiate the memory address to the previous multiple of 10; */
		int pos = Nearestmult10();
		
		
		
			String ch = getlinefrommemory();
			ch = ch + "\n";
			fos.write(ch.getBytes());			//Write card.
			//fos.flush();
			PCB.TLC++;
			if(PCB.TLC>=PCB.TLL)
			{
				Terminate(2);
			}
			
	}
	
	void Terminate(int a) throws IOException, InterruptedException
	{
		
		
		System.out.println("--------------------------------------------------");
		String si = String.valueOf(SI);
		String pi = String.valueOf(PI);
		String ti = String.valueOf(TI);
		switch(a)
		{
			case 0:
			{
				System.out.println("No Error");
				fos.write("\nError : No Error\n".getBytes());
				fos.write(("SI = " + si + "\n" + "PI = " + pi + "\n" + "TI = " + ti).getBytes());
				terminate = true;
				break;
			}
			case 1:
			{
				System.out.println("Out of Data");
				fos.write("\nError : Out of Data\n".getBytes());
				fos.write(("SI = " + si + "\n" + "PI = " + pi + "\n" + "TI = " + ti).getBytes());
				break;
			}
			case 2:
			{
				System.out.println("Line Limit Exceeded");
				fos.write("\nError : Line Limit Exceeded\n".getBytes());
				fos.write(("SI = " + si + "\n" + "PI = " + pi + "\n" + "TI = " + ti).getBytes());
				break;
			}
			case 3:
			{
				System.out.println("Time Limit Exceeded");
				fos.write("\nError : Time Limit Exceeded\n".getBytes());
				fos.write(("SI = " + si + "\n" + "PI = " + pi + "\n" + "TI = " + ti).getBytes());
				break;
			}
			case 4:
			{
				System.out.println("Operation Code Error");
				fos.write("\nError : Operation Code Error\n".getBytes());
				fos.write(("SI = " + si + "\n" + "PI = " + pi + "\n" + "TI = " + ti).getBytes());
				terminate = true;
				break;
			}
			case 5:
			{
				System.out.println("Operand Error");
				fos.write("\nError : Operand Error\n".getBytes());
				fos.write(("SI = " + si + "\n" + "PI = " + pi + "\n" + "TI = " + ti).getBytes());
				terminate = true;
				break;
			}
			case 6:
			{
				System.out.println("Invalid Page Fault");
				fos.write("\nError : Invalid Page Fault\n".getBytes());
				fos.write(("SI = " + si + "\n" + "PI = " + pi + "\n" + "TI = " + ti).getBytes());
				terminate = true;
				break;
			}
			
		}
		
	}
	
	/*void Load() throws IOException, InterruptedException
	{
		String line = null;
		int k = 0;
		int i = 1;
		int j = 1;
		int pos=0;
		while((line = br.readLine())!=null)
		{
			
			if(line.startsWith("$AMJ"))
			{
				
				String[] line1 = line.split(" ");
				PCB.TTL = Integer.parseInt(line1[2]);
				PCB.TLL = Integer.parseInt(line1[3]);
				initialise_freespacearray();
				//Get frame for Page Table (Using AllocatePage())
				pgtable = AllocatePage();
				//Memory.freespacearray[pgtable] = true;
				//Memory.freespacearray[pgtable] = false;
				
				//Initialize PTR with the number allocated for Page Table
				if(pgtable>=0 && pgtable<10)
				{
					PTR[0]='0';
					PTR[1]=(char)('0'+pgtable);
				}
				
				else
				{
					PTR[0]=(char)('0'+pgtable/10);
					PTR[1]=(char)('0'+pgtable%10);
				}
				
				PCB.PTR = pgtable;
				//counter = pgtable;
				
				
				
				//Intitalize Page Table
				pgtable = pgtable * 10;
				OperatingSystem.counter = PCB.PTR * 10;
				for(int l=0; l<10; l++)
				{
					memory[pgtable+l][1] = '0';
				}
				
				
				
				
				Thread.sleep(200);
				System.out.print("Program:" + i + "\n\nLoading");
				for(int t = 0;t<2;t++)
				{
					
					Thread.sleep(200);
					System.out.print(".");
				}
				System.out.print("\n");
				Thread.sleep(200);
				i++;
				//pos = 0;
				terminate=false;
				continue;
				
			}
		
			else if(line.startsWith("$DTA"))
			{
				System.out.print("Decoding");
				for(int t = 0;t<2;t++)
				{
					
					Thread.sleep(200);
					System.out.print(".");
				}
				System.out.print("\n");
				StartExecution();
				//terminate = false;
				
				continue;
			}
			
			else if(line.startsWith("$END"))
			{
				String ch = "-----------------------------------------";
				ch = "\n" + ch + "\n\n";
				fos.write(ch.getBytes());
				Thread.sleep(200);
				System.out.println("Program card processed succesfully\n");
				SI = PI = TI = 0;
				
				continue;
			}
			
			
			else
			{
				pageno = AllocatePage(); //Get frame for Program Page
				UpdatePageTable(pageno);
				int x = pageno*10;
				Substringsandstore(line,x);//Send allocated page number  to this function
				
			}
				
		}
		
	}*/
	
	
	
	void setIC(int count)
	{
		if(count>=0 && count<10)
		{
			InstructionCounter[0]='0';
			InstructionCounter[1]=(char)('0'+count);
		}
		
		else
		{
			InstructionCounter[0]=(char)('0'+count/10);
			InstructionCounter[1]=(char)('0'+count%10);
		}
		
	}
	
	void StartExecution() throws IOException, InterruptedException
	{
		
		/*Initialize InstructionCounter to 0;*/
		setIC(0);
		ExecuteUserProgram();
		
	}
	
	
	int getOperand() throws IOException, InterruptedException//To retrieve the last two characters of IR
	{
		int op = 0;
		int opreal = 0;
		String op1=String.valueOf(InstructionRegister);
		op1=op1.substring(2,4);
		try
		{
		op = Integer.parseInt(op1);
		opreal = AddressMap(op);
		}
		catch(NumberFormatException e)
		{
			PI = 2;
		}
		
		return opreal;
		
	}
	
	String getOperator()
	{
		String ir1=String.valueOf(InstructionRegister);
		ir1=ir1.substring(0,2);
		if(ir1.startsWith("H"))
		{
			ir1 = "H";
		}
		
		return ir1;
	}
	
	void setInstructionRegister(int count)
	{
		
			for(int i=0;i<4;i++)
			{
				
				InstructionRegister[i] = memory[count][i];
				
			}
			
	}
	
	int charArrayToInt(char []data)
	{
	    int result = 0;
	    for (int i = 0; i < 2; i++)
	    {
	        int digit = (int)data[i] - (int)'0';
	        if ((digit < 0) || (digit > 9))
	        {
	        result *= 10;
	        }
	        
	        result = (result*10)+ digit;
	    }
	    return result;
	}
	
	int AddressMap(int a) throws IOException, InterruptedException
	{
		int b = a/10;
		int c = a%10;
		z = (PCB.PTR * 10) + b;
		char t = memory[z][1];
		if(t == '0')
		{
			PI = 3;
			
		}
		char s[] = {memory[z][2],memory[z][3]};
		int q = charArrayToInt(s);
		return (q*10 + c);
		
	}
	
	void ExecuteUserProgram() throws IOException, InterruptedException // Execution starts here //slave mode is this
	{
		PI = 0;
		count= 0;
		int count1 = 0;
		int flag = 0;
		int op = 0;
		//terminate = false;
		Thread.sleep(200);
		System.out.print("Executing");
		for(int t = 0;t<2;t++)
		{
			
			Thread.sleep(200);
			System.out.print(".");
		}
		System.out.print("\n");
		Thread.sleep(200);
		
		while(terminate!=true)
		{
			if(OS.readyQ.peek().TTC ==OS.readyQ.peek().TTL)
			 {
				CPU.time_Interupt=2;
			 }
			OS.readyQ.peek().TSC++; // Incrementing Time Slice Counter
			if(OS.readyQ.peek().timeslice==Time_Slice)
			{
		        CPU.time_Interupt=1;		
			}
			
			 //setInsetInstructionRegister(a);
		
			count=charArrayToInt(InstructionCounter);
			int a = AddressMap(count);
			
			
			setInstructionRegister(a);
			ir1 = getOperator();
			
			op = getOperand();
			if(PI==3)
			{
				MOS(SI,PI,TI);
				continue;
			}
			
			
		
		
		
		
		switch(ir1)
		{
			
			case "LR":
			{
				for(int i=0;i<4;i++)
				{
					register[i] = memory[op][i];
				}
				break;
			}
			
			case "SR":
			{
				for(int i=0;i<4;i++)
				{
					memory[op][i] = (char) register[i];
				}
				break;
			}
			
			case "CR":
			{
				String cmp = new String(register);
				String cmp1 = new String(memory[op]);
				if(cmp.equals(cmp1))
				{
					c = true;
				}
				
				else
					c = false;
					
				break;
			}
				

			case "BT":
			{
				if(c == true)
				{
					count = op;
					count1 = count;
					flag=1;
					
				}
				break;
			}
			
			case "GD":
			{
				SI = 1;
				break;
			}
			
			case "PD":
			{
				SI = 2;
				break;
			}
			
			case "H":
			{
				
				SI = 3;
				break;
			}

			default:
			{
				PI=1;
			}
			
		}
		
		 
		
		MOS(SI, PI, TI);
		SI = 0;
		count++;
		setIC(count);
		if(flag == 1)
		{
			setIC(count1);
			flag = 0;
		}
		}
			
	}

	

		
	

		public void MOS(int SI, int PI, int TI) throws IOException, InterruptedException
		{
			if(TI == 0)
			{
				if(SI == 1)
				{
					// remove PCB from Ready Queue to IO Queue to read
					read();
				}
				if(SI == 2)
				{
					// remove PCB from Ready Queue to IO Queue to write
					write();
				}
				if(SI == 3)
				{
					// remove PCB from Ready Queue to terminate Queue
					//Terminate with error message 0
					Terminate(0);
				}
				if(PI == 1)
				{
					// remove PCB from Ready Queue to terminate Queue
					//terminate with error message 4
					Terminate(4);
				}
				if(PI == 2)
				{
					// remove PCB from Ready Queue to terminate Queue
					//terminate with error message 5
					Terminate(5);
				}
				if(PI == 3)
				{
					//If Page Fault Valid, ALLOCATE, update page Table, Adjust IC if necessary,EXECUTE USER PROGRAM OTHERWISE TERMINATE (6)
					if((ir1.equals("GD")) || (ir1.equals("SR")))
					{
						System.out.println("Valid Page Fault");
						pageno = AllocatePage();
						String num = String.valueOf(InstructionRegister[2]);
						UpdatePageTableforGD(PCB.PTR*10 + Integer.parseInt(num));
						PI=0;
						
						ExecuteUserProgram();
						
						
					}
					else
					{
						System.out.println("Invalid Page fault");
						// remove PCB from Ready Queue to terminate Queue
						Terminate(6);
					}
				}
			}
				else{
					if(PI == 1)
					{
						// remove PCB from Ready Queue to terminate Queue
						//terminate with error message 3
						Terminate(3);
					}
					if(PI == 2)
					{
						// remove PCB from Ready Queue to terminate Queue
						//write and terminate with error message 3
						Terminate(3);
					}
					if(PI == 3)
					{
						// remove PCB from Ready Queue to terminate Queue
						//terminate with error message 0
						Terminate(6);
					}
					if(SI == 1)
					{
						// remove PCB from Ready Queue to terminate Queue
						//terminate with error message 3,4
						Terminate(7);
					}
					if(SI == 2)
					{
						// remove PCB from Ready Queue to terminate Queue
						//terminate with error message 3,5
						write();
						Terminate(8);
					}
					if(SI == 3)
					{
						// remove PCB from Ready Queue to terminate Queue
						//terminate with error message 3
						Terminate(3);
					}		
				}
			
			if(IOI == 1)
			{
				IR_1();
			}
			else if(IOI==2)
			{
				IR_2();
			}
			else if(IOI==3)
			{
				IR_2();
				IR_1();
			}
			else if(IOI==4)
			{
				IR_3();
			}
			else if(IOI==5)
			{
				IR_1();
				IR_3();
			}
			else if(IOI==6)
			{
				IR_3();
				IR_2();
			}
			else if(IOI==7)
			{
				IR_2();
				IR_1();
				IR_3();
			}
			}

			
				
		
		
		public void IR_1()// input spooling of ch-1 //start from here**
		  {
		      if(channel1eb.type==0)
		      {
		    	  Read next card in channel1eb;
		    	  Put choiceofqueue as 1 //input full
		      }
		      if(file not ended and empty buffer queue not empty)
		       {
		    	  Ochannel1eb=eb.poll();//Removing head of Empty Buffer Queue and Storing in channel1eb Queue
		    	  start_channel(1); // Starting channel 1  
		       }
		      
		      if(line.startsWith("$AMJ"))
				{
					
		    	    pcbs[n].getCounters(line);
		    	    pcbs[n].TLC=0;    //Initialize Counters
		    	    pcbs[n].TTC=0;
					CPU.initialise_freespacearray();
					pgtable=CPU.AllocatePage();
					pcbs[n].PTR = pgtable;
					pgtable=page_table_number*10;
					Memory.freespace[page_table_number]=false;
					//store data in PCB's variables
					for(int l=0; l<10; l++)
					{
						memory[pgtable+l][1] = '0';
					}
					OperatingSystem.counter = PCB.PTR * 10;
					
					channel1eb = null;
					eb.add(channel1eb);
					change choiceofqueue
					//set F = "P";
					continue;
					
				}
				else if (line.startsWith("$DTA")) 
				{
					
					start_Execution();		
					terminate=false;			
					channel1eb = null;
					eb.add(channel1eb);
					change choiceofqueue to empty buffer
					
					
					continue;   		
				}
				else if (line.startsWith("$END"))
				{
					
					
					
				}
				else
				{
					
					pageno = AllocatePage(); //Get frame for Program Page
					UpdatePageTable(pageno);
					int x = pageno*10;
					Substringsandstore(line,x);//Send allocated page number  to this function
				   place ifb to ifbq;
					if((program card)
					{
					pcbobj[n].programcardcount++;
					flag2 = "busy";
					flag3 = "busy";
					continue;
					}
					
				    else
				    {
				    	pcbobj[n].datacardcount++;
				    	flag2 = "busy";
						flag3 = "busy";
					    continue;
				    }
				}
		  
				
			}
		 
		 public void IR_2() //ch-2 finally does this write to file.
		 {
			 write(ofb.peek());
			 change choiceofqueue of output full queue to empty buffer queue
			 add empty buffer to empty buffer queue;
			 
			 if(ofb is not empty)
			 {
				 get next ob;
				 start channel2;
			 }
		 }
		 
		 
		 }

	


	public static void main(String[] args) throws IOException, InterruptedException {
		
		File fobj = new File("M://OSInput//codetry.txt");
		File oobj = new File("M://OSInput//phase2out.txt");
		OperatingSystem.fr = new FileReader(fobj);
		OperatingSystem.br = new BufferedReader(fr);
		OperatingSystem.wr=new FileWriter(oobj);
		OperatingSystem.bwr=new BufferedWriter(wr);
		OperatingSystem.fos = new FileOutputStream(oobj);
		OperatingSystem.fis=new FileInputStream(fobj);
		OperatingSystem OS = new OperatingSystem();
		OS.Load();
		System.out.println("All program processed. Please check the output file");
		
	}


	
}

