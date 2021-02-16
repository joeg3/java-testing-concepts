package org.example;

import java.util.Timer;
import java.util.TimerTask;

public class Asynchronous {
  private int count = 0;
  private boolean isTimerStarted = false;
  private boolean isTimerFinished = false;

  TimerTask task = new TimerTask() {
    public void run() {
      isTimerFinished = true;
    }
  };

  /**
   *  A contrived asynchronous method that returns false the first time it's called, and any call
   *  after that until 4 seconds. Any call to this method 4 or more seconds after the first
   *  call will return true.
   */
  boolean asynchronousMethod() throws InterruptedException {
    System.out.println("asynchronousMethod()");
    if (!isTimerStarted) {
      Timer timer = new Timer("Timer");
      timer.schedule(task, 4000L);
      isTimerStarted = true;
    }
    if (isTimerFinished) {
      return true;
    } else {
      return false;
    }


//    Thread.sleep(1000);
//    count ++;
//    System.out.println("Count: " + count);
//    if (count >= 4) {
//      return true;
//    } else {
//      return false;
//    }
  }
}
