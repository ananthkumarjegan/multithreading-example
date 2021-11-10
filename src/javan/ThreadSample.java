package javan;

import java.util.Date;

public class ThreadSample {
	public static void main(String[] args) throws InterruptedException {
		long startdate = System.currentTimeMillis();
		Thread newThread = new Thread(new Runnable() {
			@Override
			public void run() {
				forLoopSample();
			}
		});

		Thread newThreadVal = new Thread(new Runnable() {
			@Override
			public void run() {
				forLoopSample();
			}
		});
		newThread.setName("1st worker Thread");
		newThreadVal.setPriority(Thread.MIN_PRIORITY);
		newThreadVal.setName("2nd worker Thread");
		newThread.start();
		newThreadVal.start();
//		forLoopSample();
//		forLoopSample();
//		Thread.sleep(100);
		long endDate = System.currentTimeMillis();
		long totalTime = endDate - startdate;
		System.out.println("Total time : "+ totalTime);
		
	}
	
	private static void forLoopSample() {
		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + " current value :" + i);
		}
	}
}
