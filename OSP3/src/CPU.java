import java.util.Random;


public class CPU implements Memory {
	public char register[] = new char[4];
	public boolean c;
	public char[] InstructionCounter = new char[2];
	public char InstructionRegister[] = new char[4];
	public int SI = 0;//Service Interrupt
	public int PI = 0;//Program Interrupt
	public int TI = 0;//Time Interrupt
	public char[] PTR = new char[2];
	public int IOI = 1;//Channel done interrupt
	PCB pcbs[] = new PCB[10];
	
	public int AllocatePage()
	{
		Random rn = new Random();
		int randomnum = rn.nextInt(30) + 0;
		
		
		if(freespacearray[randomnum] == false)
		{
			freespacearray[randomnum] = true;
			
		}
		
		else
		{
			AllocatePage();
		}
		
		return randomnum;
	}
	
	void UpdatePageTableforGD(int a)
	{
		
		char page_no_char[]=new char[2];
	    String new_page_no;
	    new_page_no=String.valueOf(OperatingSystem.pageno);
	    page_no_char=new_page_no.toCharArray();
	    if(OperatingSystem.pageno>=0 && OperatingSystem.pageno<=9)
	    {
	    	Memory.memory[a][1]='1';
	    	Memory.memory[a][2]='0';
	    	Memory.memory[a][3]=page_no_char[0];		
	    }
	    else
	    {
	    Memory.memory[a][1]='1';
		Memory.memory[a][2]=page_no_char[0];
		Memory.memory[a][3]=page_no_char[1];
	    }

	}
	
	void initialise_freespacearray()
	{
		for(int i=0;i<Memory.freespacearray.length;i++)
		{
			Memory.freespacearray[i]= false;
		}
	}
	
	void UpdatePageTable(int a)
		{
		
		
		String no;
		//String no1=String.valueOf(CPU.IR[2]);
		//int IR = Integer.parseInt(no1);
		  
		char arr[]=new char[2];
	    no=String.valueOf(OperatingSystem.pageno);
	    arr=no.toCharArray();
	    if(OperatingSystem.pageno>=0 && OperatingSystem.pageno<=9)
	    {
	    	memory[OperatingSystem.counter][1]='1';
		    memory[OperatingSystem.counter][2]='0';
		    memory[OperatingSystem.counter][3]=arr[0];
		   	
	    }
	    else
	    	{
	    	  memory[OperatingSystem.counter][1]='1';
	    	  memory[OperatingSystem.counter][2]=arr[0];
	  	      memory[OperatingSystem.counter][3]=arr[1];
	  	 
	    	}
	    OperatingSystem.counter++;
		
			
		
		}
	
	}
	

