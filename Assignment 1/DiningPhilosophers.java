public class DiningPhilosophers {
  
  public static void main(String[] args) throws InterruptedException {
    int np = Integer.parseInt(args[0]); // number of philosophers (and chopsticks)
    int nc = Integer.parseInt(args[1]); // number of cycles each philosopher performs
    int tt = Integer.parseInt(args[2]); // max thinking time (ms)
    int et = Integer.parseInt(args[3]); // max eating time (ms)
    int rl = Integer.parseInt(args[4]); // If 0, all philosophers are right-handed. If 1, even-numbered philosophers are right-handed and odd-numbered philosophers are left-handed.
    Philosopher[] philosophers = new Philosopher[np];
    Chopstick[] chopsticks = new Chopstick[np];

    for (int i = 0; i < np; ++i)
      chopsticks[i] = new Chopstick(i);
    for (int i = 0; i < np; ++i) {
      // If rl = 0, all philosophers are right-handed. If rl = 1, only even-numbered philosophers are right handed.
      if (rl == 0 || i % 2 == 0){
        philosophers[i] = new Philosopher(chopsticks[i], chopsticks[(i + 1) % np], i, nc, tt, et, 0);
      }
      else{
        philosophers[i] = new Philosopher(chopsticks[i], chopsticks[(i + 1) % np], i, nc, tt, et, 1);
      }
      philosophers[i].start();
    }
    for (int i = 0; i < np; ++i)
      philosophers[i].join();
  }
}
