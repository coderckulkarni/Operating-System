
public class PCB extends CPU {
	
	protected static int TLL;//Total line limit
	protected static int TTL;//Total time limit
	protected static int TTC;//Total time count
	protected static int TLC;//Total line count
	static int PTR;//Copy of PTR from CPU
	int IC;//Copy of InstructionCounter in CPU. For multiprocessing.
	int programcardcount = 0;
	int firstprogramcard;
	int datacardlocation;
	int datacardcount;
	int timeslice;
	
	
}
