package ch03.day0907.typehier;

public class ConsolePrinter implements Printer {

	@Override
	public void print(String msg) {
		System.out.println(msg);
	}

}
