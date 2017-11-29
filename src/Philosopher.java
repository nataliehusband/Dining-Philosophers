/**
 * Philosopher class that contains the main method that creates multiple instances
 * of the class. A central cup is used as a Semaphore for the chopsticks.
 * @author Natalie and Kyle
 *
 */
public class Philosopher implements Runnable{
	final Thread thread;
	String name;
	Cup cup; 
	int counter = 0; 
	boolean timeToRun = false;

	/**
	 * main method
	 * creates the cup that will hold the chopsticks
	 * creates the philosophers and starts their threads
	 * @param args
	 */
	public static void main(String[] args) {
		Cup c = new Cup();
		Philosopher[] p = new Philosopher[5];
		for(int i = 0; i < p.length; i++) {
			p[i] = new Philosopher("philosopher "+(i+1), c);
			p[i].start();

			//System.out.println("SHOULD BE WORKING");
		}
		//comment to run forever
		try {
			Thread.sleep(5000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//Uncomment to run forever
		//		while(true) {
		//			
		//		}

		for(int i = 0; i< p.length; i++) {
			p[i].stop();
		}
		for(int i= 0; i < p.length; i++) {
			System.out.println("Philosopher " + (i+1) + " ate " + p[i].counter + " times.");
		}
	}

	/**
	 * The constructor philosopher
	 * @param n is the instance in which name is equal to n 
	 * @param c the instance in which cup is equal to c
	 */
	Philosopher(String n, Cup c) {
		this.name = n;
		this.cup = c; 
		thread = new Thread(this, "newThread");

	}

	/**
	 * method think 
	 * is called when the philosopher can't pick up a chopstick and sets a random amount of time for them to think
	 */
	private void think() {
		System.out.println(name + " is philosophising");
		try {
			Thread.sleep((long) (Math.random()*300));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * method start
	 * starts all of the threads for each philosopher and sets the timeToRun = true so that 
	 * the program will either run the length of time that we set or infinitely depending on what is commented out
	 */
	private void start() {
		timeToRun = true;
		thread.start();
	}

	/**
	 * method stop
	 * is called when the time to run is over and it stops each of the threads 
	 */
	private void stop() {
		timeToRun = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * method eat
	 * when a philosopher acquires two chopsticks, this method is called
	 * the philosopher will eat for a random amount of time 
	 */
	private void eat() {
		if(cup.acquired()) {
			System.out.println(name + " is eating"); 
			counter += 1; 
			try {
				Thread.sleep((long) (Math.random()*300));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			cup.release();
		} 
	}

	/**
	 * runs the program when the philosophers in main are started 
	 */
	public  void run() {
		//System.out.println("Beginning to run");
		while(timeToRun) {
			think();
			eat(); 
		}	
	}
}
