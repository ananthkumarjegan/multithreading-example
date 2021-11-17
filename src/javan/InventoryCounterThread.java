package javan;

public class InventoryCounterThread {

	protected static int MAX = 10000;

	public static void main(String[] args) throws InterruptedException {
		InventoryRegister inventoryRegister=new InventoryRegister();
		Thread incrementThread = new IncrementThread(inventoryRegister);
		Thread decrementThread = new DecrementThread(inventoryRegister);
		
		incrementThread.start();
		decrementThread.start();
		
		incrementThread.join();
		decrementThread.join();
		
		System.out.println("Current Items :"+inventoryRegister.getItem());
		
	}

	private static class IncrementThread extends Thread {

		private InventoryRegister inventoryRegister;

		public IncrementThread(InventoryRegister inventoryRegister) {
			this.inventoryRegister = inventoryRegister;
		}

		@Override
		public void run() {
			for (int i = 0; i < MAX; i++) {
				inventoryRegister.incrementItem();
			}
		}
	}

	

	private static class DecrementThread extends Thread {

		private InventoryRegister inventoryRegister;

		public DecrementThread(InventoryRegister inventoryRegister) {
			this.inventoryRegister = inventoryRegister;
		}

		@Override
		public void run() {
			for (int i = 0; i < MAX; i++) {
				inventoryRegister.decrementItem();;
			}
		}
	}

	private static class InventoryRegister {
		private int  item;

		public synchronized void incrementItem() {
			item++;
		}

		public synchronized void decrementItem() {
			item--;
		}

		public  int getItem() {
			return item;
		}
	}
}
