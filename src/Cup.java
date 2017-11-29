/**
 * Cup class that acts as a semaphore for chopsticks. holds 5 chopsticks
 * @author Natalie and Kyle
 *
 */
public class Cup {
	int stickCount;
	
	/**
	 * constructor, sets the chopstick count = 5 
	 */
	Cup(){
		stickCount = 5; 
	}
	
	/**
	 * method release. when philosopher puts their chopsticks back, adds 2 back to the cup 
	 */
	public synchronized void release() {
		stickCount += 2;
	}
	
	/**
	 * when philosopher acquires two chopsticks, two chopsticks are subracted from the cup
	 * @return
	 */
	public synchronized boolean acquired() {
		if(stickCount > 5) {
			System.err.println("You made Sticks");
			System.exit(1);
		}
		else if(stickCount >= 2) {
			//System.out.println("ACQUIRED");
			stickCount -= 2;
			return true;
		}
		return false;
	}
}
