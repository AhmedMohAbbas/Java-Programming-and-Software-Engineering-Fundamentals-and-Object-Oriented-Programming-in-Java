import edu.duke.*;

public class HelloWorld {
	public void runHello () {
		FileResource res = new FileResource("hello_unicode.txt");
		for (String line : res.lines()) {
			System.out.println(line);
		}
	}
}

 class HelloWorld102 {
	public void runHello () {
		FileResource x = new FileResource("hello_unicode.txt");
		for (String y : x.lines()) {
			System.out.println(y);
		}
	}
}
