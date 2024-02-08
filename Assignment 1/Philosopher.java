import java.util.Random;

class Philosopher extends Thread {
  private Chopstick left, right;
  private Random random;
  private int thinkCount;
  private int philosopherNumber;
  private int numCycles;
  private int maxThinkTime;
  private int maxEatTime;
  private int rightHanded;

  public Philosopher(Chopstick left, Chopstick right, int id, int nc, int tt, int et, int rl) {
    this.left = left; this.right = right;
    random = new Random();
    this.philosopherNumber = id;
    this.numCycles = nc;
    if (tt == 0){ // if tt is  equal to 0, random.nextInt(maxThinkTime) would have an error.
      this.maxThinkTime = 1000;
    }
    else{
      this.maxThinkTime = tt;
    }

    if (et == 0){ // if et is equal to 0, random.nextInt(maxEatTime) would have an error.
      this.maxEatTime = 1000;
    }
    else{
      this.maxEatTime = et;
    }
    this.rightHanded = rl;
  }

  public void run() {
    try {
      while(numCycles == 0 || thinkCount < numCycles) {
        ++thinkCount;
        if (thinkCount % 10 == 0)
          System.out.println("Philosopher " + philosopherNumber + " has thought " + thinkCount + " times.");
        int tempThink = random.nextInt(maxThinkTime);
        // Think for a while
        Thread.sleep(tempThink);
        System.out.println("Philosopher " + philosopherNumber + " thinks for " + tempThink + " milliseconds.");
         // grab dominant hand's chopstick first (right in this case)
        if (rightHanded == 0){        
          System.out.println("Philosopher " + philosopherNumber + " wants right chopstick.");
          synchronized(right) {        
            System.out.println("Philosopher " + philosopherNumber + " has right chopstick.");
            System.out.println("Philosopher " + philosopherNumber + " wants left chopstick.");            
            synchronized(left) {          
              System.out.println("Philosopher " + philosopherNumber + " has left chopstick.");
              int tempEat = random.nextInt(maxEatTime);
              // Eat for a while after grabbing chopsticks
              Thread.sleep(tempEat);
              System.out.println("Philosopher " + philosopherNumber + " eats for " + tempEat + " milliseconds");
            }
            System.out.println("Philosopher " + philosopherNumber + " releases left chopstick.");
          }
          System.out.println("Philosopher " + philosopherNumber + " releases right chopstick.");
        }
        // grab dominant hand's chopstick first (left in this case)
        else{ 
          System.out.println("Philosopher " + philosopherNumber + " wants left chopstick.");
          synchronized(left) {        
            System.out.println("Philosopher " + philosopherNumber + " has left chopstick.");
            System.out.println("Philosopher " + philosopherNumber + " wants right chopstick.");            
            synchronized(right) {          
              System.out.println("Philosopher " + philosopherNumber + " has right chopstick.");
              int tempEat = random.nextInt(maxEatTime);
              // Eat for a while after grabbing chopsticks
              Thread.sleep(tempEat);
              System.out.println("Philosopher " + philosopherNumber + " eats for " + tempEat + " milliseconds");
            }
            System.out.println("Philosopher " + philosopherNumber + " releases right chopstick.");
          }
          System.out.println("Philosopher " + philosopherNumber + " releases left chopstick.");
        }
      }
    } catch(InterruptedException e) {}
  }
}
