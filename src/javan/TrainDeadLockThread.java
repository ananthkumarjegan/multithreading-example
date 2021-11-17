package javan;

import java.util.Random;

public class TrainDeadLockThread {

	public static void main(String[] args) {
		Intersection intersection = new Intersection();
		Thread trainA = new TrainATrack(intersection);
		Thread trainB = new TrainBTrack(intersection);
		trainA.setName("Tain A");
		trainA.start();
		trainB.setName("Tain B");
		trainB.start();
	}

	private static class TrainBTrack extends Thread {

		private Intersection intersection;
		private Random random = new Random();

		public TrainBTrack(Intersection intersection) {
			this.intersection = intersection;
		}

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(random.nextInt(5));
					intersection.takeRoadB();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static class TrainATrack extends Thread {

		private Intersection intersection;
		private Random random = new Random();

		public TrainATrack(Intersection intersection) {
			this.intersection = intersection;
		}

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(random.nextInt(5));
					intersection.takeRoadA();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static class Intersection {
		Object roadA = new Object();
		Object roadB = new Object();

		public void takeRoadA() {
			synchronized (roadA) {
				System.out.println("RoadA Blocked by " + Thread.currentThread().getName());

				synchronized (roadB) {
					System.out.println("Train A passing in Track A");
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}

		public void takeRoadB() {
			synchronized (roadA) {
				System.out.println("RoadB Blocked by " + Thread.currentThread().getName());

				synchronized (roadB) {
					System.out.println("Train B passing in Track B");
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
