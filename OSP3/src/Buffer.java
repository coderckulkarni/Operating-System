
public class Buffer {
	static PCB[] ReadyQueue = new PCB[10];
	static PCB[] TerminateQueue = new PCB[10];
	static PCB[] IOQueue = new PCB[10];
	static PCB[] Load = new PCB[10];
	
	int choiceofqueue;
	char[] array = new char[40];
	
	//choiceofqueue:
	//0=Empty buffer
	//1=Input Full Buffer
	//2=Output Full Buffer

}
