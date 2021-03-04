package org.hum.scaffold.splider;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test3 {

	public static void main(String[] args) {
		String ss = "http://asdfasdf.ccom/12faw/123234/a.gif";
		System.out.println(ss.substring(ss.lastIndexOf(".")));
	}
	
	private static class Task implements Runnable {
		int i;
		
		static final Random rand = new Random();
		
		public Task(int i) {
			this.i = i;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(rand.nextInt(5000) + 1000);
				System.out.println(i + " is over");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
