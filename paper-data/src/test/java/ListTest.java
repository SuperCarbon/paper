import java.util.ArrayList;
import java.util.List;

public class ListTest {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		int i = 0;
		try {
			System.out.println("i =" + i);
			for (;; i++) {
				list.add(i);
			}
		} catch (java.lang.OutOfMemoryError e) {
			System.out.println("i =" + i);
		}

	}

}
