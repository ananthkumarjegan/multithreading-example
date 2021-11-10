package javan;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PoliceHackerThreadSample {
	public static final int MAX_PASSWORD = 9999;

	public static void main(String[] args) {
		int password = new Random().nextInt(MAX_PASSWORD);
		System.out.println("Valut Password : " + password);
		Vault vault = new Vault(password);
		List<Thread> threadList = new ArrayList<>();
		threadList.add(new AscendingHacker(vault));
		threadList.add(new DescendingHacker(vault));
		threadList.add(new PoliceHackerThread());

		for (Thread thread : threadList) {
			thread.start();
		}
	}

	private static class Vault {
		private int password;

		public Vault(int password) {
			this.password = password;
		}

		public boolean isCorrectPassword(int toCheckPassword) {
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return this.password == toCheckPassword;
		}
	}

	private static abstract class HackerThread extends Thread {
		protected Vault vault;

		public HackerThread(Vault vault) {
			this.vault = vault;
			this.setName(this.getClass().getSimpleName());
			this.setPriority(this.MAX_PRIORITY);
		}
	}

	private static class AscendingHacker extends HackerThread {

		public AscendingHacker(Vault vault) {
			super(vault);
		}

		@Override
		public void run() {
			for (int i = 0; i < MAX_PASSWORD; i++) {
				if (vault.isCorrectPassword(i)) {
					System.out.println("Password Hacked by :" + this.getName() + " and password is :" + i);
					System.exit(0);
				}
			}
		}

	}

	private static class DescendingHacker extends HackerThread {

		public DescendingHacker(Vault vault) {
			super(vault);
		}

		@Override
		public void run() {
			for (int i = MAX_PASSWORD; i > 0; i--) {
				if (vault.isCorrectPassword(i)) {
					System.out.println("Password Hacked by :" + this.getName() + " and password is :" + i);
					System.exit(0);
				}
			}
		}

	}

	private static class PoliceHackerThread extends Thread {

		@Override
		public void run() {
			for (int i = 0; i <= 9; i++) {
				try {
					Thread.sleep(1000);
					System.out.println("Police will start chase hacker in " + (10 - i) + " seconds");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Hackers your Game over...........");
			System.exit(0);
		}
	}
}
