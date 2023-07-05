
public class Main {

	public static void main(String[] args) {
		Thread t1 = new Thread(new StampaSimboli("x"));
		Thread t2 = new Thread(new StampaSimboli("y"));

		t1.start();
		t2.start();
	}

	private static class StampaSimboli implements Runnable {
		private final String symbol;

		public StampaSimboli(String symbol) {
			this.symbol = symbol;
		}

		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				System.out.println(symbol);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
