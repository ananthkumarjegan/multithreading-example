package javan;

import java.util.Random;

public class MetricsMeasurementThreadSample {

	public static void main(String[] args) {
		MerticsCalculator merticsCalculator = new MerticsCalculator();
		Thread performanceThread=new PerformanceThread(merticsCalculator);
		Thread printThread= new PrintThread(merticsCalculator);
		
		performanceThread.start();
		printThread.start();
	}

	private static class PrintThread extends Thread {
		private MerticsCalculator merticsCalculator;

		public PrintThread(MerticsCalculator merticsCalculator) {
			this.merticsCalculator = merticsCalculator;
		}

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println("Average of samples :" + merticsCalculator.getAverage());

			}
		}

	}

	private static class PerformanceThread extends Thread {
		private MerticsCalculator merticsCalculator;
		Random random = new Random();

		public PerformanceThread(MerticsCalculator merticsCalculator) {
			this.merticsCalculator = merticsCalculator;
		}

		@Override
		public void run() {
			while (true) {
				long start = System.currentTimeMillis();
				try {
					Thread.sleep(random.nextInt(20));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				long end = System.currentTimeMillis();

				merticsCalculator.addSample(end - start);

			}
		}

	}

	private static class MerticsCalculator {
		private volatile double average;
		private volatile long count;

		public void addSample(long time) {
			double sum = average * count;
			count++;
			average = (sum + time) / count;
		}

		public double getAverage() {
			return average;
		}
	}
}
