package thread;

public class JoinMessages {
    public static void main(String args[]) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                String importantInfo[] = {
                        "Mares eat oats",
                        "Does eat oats",
                        "Little lambs eat ivy",
                        "A kid will eat ivy too"
                };

                for (int i = 0; i < importantInfo.length; i++) {
                    //Pause for 4 seconds
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //Print a message
                    System.out.println(importantInfo[i]);
                }

            }
        };
        Thread thread = new Thread(runnable, "Messenger");
        thread.start();

        thread.join();

        System.out.println("finish!");
    }
}
