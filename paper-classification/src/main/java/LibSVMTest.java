import java.io.IOException;

import com.lab613.zj.paper.classification.svm.SVMPredict;

public class LibSVMTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//String[] trainArgs = { "-v","10","heart_scale", "train_model" };
//		svm_train st = new svm_train();
//		svm_predict sp = new svm_predict();
		//String[] testArgs = { "+1 1:0.708333 2:1 3:1 4:-0.320755 5:-0.105023 6:-1 7:1 8:-0.419847 9:-1 10:-0.225806 12:1 13:-1 ", "train_model", "test_output" };
		try {
			//svm_train.main(trainArgs);
			double a = SVMPredict.predict("+1 1:0.708333 2:1 3:1 4:-0.320755 5:-0.105023 6:-1 7:1 8:-0.419847 9:-1 10:-0.225806 12:1 13:-1");
			System.out.println(a);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
