package Esercizio2;

import java.util.Random;

public class Main {

	private static int arraySize = 3000;
	private static int partitionSize = arraySize / 3;

	private static int[] array = new int[arraySize];
	private static int[] partialSums = new int[3];

	public static void main(String[] args) {
		generateRandomArray();

		Thread t1 = new Thread(new SumCalculator(0));
		Thread t2 = new Thread(new SumCalculator(1));
		Thread t3 = new Thread(new SumCalculator(2));

		t1.start();
		t2.start();
		t3.start();

		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		int totalSum = 0;
		for (int partialSum : partialSums) {
			totalSum += partialSum;
		}
		System.out.println("Risultati parziali:");
		for (int i = 0; i < partialSums.length; i++) {
			System.out.println("Thread " + (i + 1) + ": " + partialSums[i]);
		}
		System.out.println("Somma totale: " + totalSum);
	}

	private static void generateRandomArray() {
		Random random = new Random();
		for (int i = 0; i < arraySize; i++) {
			array[i] = random.nextInt(3001);
		}
	}

	static class SumCalculator implements Runnable {
		private final int partitionIndex;

		public SumCalculator(int partitionIndex) {
			this.partitionIndex = partitionIndex;
		}

		@Override
		public void run() {
			int startIndex = partitionIndex * partitionSize;
			int endIndex = startIndex + partitionSize;

			int sum = 0;
			for (int i = startIndex; i < endIndex; i++) {
				sum += array[i];
			}

			partialSums[partitionIndex] = sum;
		}
	}
}
