package javan;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FactorialJoinThreadSample {
	
	
	public static void main(String[] args) throws InterruptedException {
		List<Long> inputList = Arrays.asList(1000L,5L,2000L,30L,210L,90L,420L,620L);
		Collections.sort(inputList);
		List<FacorialCalcuator> threadList= new ArrayList<>();
		
		for (Long input : inputList) {
			FacorialCalcuator thread =new FacorialCalcuator(input);
			threadList.add(thread);
		}
		
		for (Thread thread : threadList) {
			thread.setDaemon(true);
			thread.start();
			thread.join(50);
		}
		
		
		for (int i = 0; i < inputList.size(); i++) {
			FacorialCalcuator calulator= threadList.get(i);
			if(calulator.isFinished()) {
				System.out.println("Factorial calculated for :"+calulator.getNumber() +" is "+calulator.getResult());
			}else {
				System.out.println("Factorial calculation inprogress:"+calulator.number+"..............");
			}
		}
		
	}

	private static class FacorialCalcuator extends Thread {
		private long number;
		private BigInteger result;
		private boolean isFinished = false;

		public FacorialCalcuator(long number) {
			this.number = number;
		}

		@Override
		public void run() {
			this.result = calculateFacorial(number);
			this.isFinished = true;
		}

		public BigInteger calculateFacorial(long number) {
			BigInteger factorialResult = BigInteger.ONE;
			for (long i = 1; i <= number; i++) {
				factorialResult = factorialResult.multiply(new BigInteger(Long.toString(i)));
			}
			return factorialResult;
		}
		
		public BigInteger getResult() {
			return result;
		}
		
		public boolean isFinished() {
			return isFinished;
		}
		
		public long getNumber() {
			return number;
		}
	}
}
