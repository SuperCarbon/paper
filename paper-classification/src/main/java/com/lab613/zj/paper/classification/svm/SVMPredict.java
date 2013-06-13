package com.lab613.zj.paper.classification.svm;

import java.io.IOException;
import java.util.StringTokenizer;

import libsvm.svm;
import libsvm.svm_model;
import libsvm.svm_node;
import libsvm.svm_parameter;

public class SVMPredict {
	// svm_type:
	// C_SVC: C-SVM 分类
	// NU_SVC: nu-SVM 分类
	// ONE_CLASS: one-class-SVM
	// EPSILON_SVR: epsilon-SVM回归
	// NU_SVR: nu-SVM回归

	private static double atof(String s) {
		return Double.valueOf(s).doubleValue();
	}

	private static int atoi(String s) {
		return Integer.parseInt(s);
	}

	public static double predict(String line) throws IOException {

		int predict_probability = 0;

		svm_model model = svm.svm_load_model("train_model");

		double v;

		int svm_type = svm.svm_get_svm_type(model);
		// int nr_class = svm.svm_get_nr_class(model);
		double[] prob_estimates = null;
		if (line != null) {

			StringTokenizer st = new StringTokenizer(line, " \t\n\r\f:");

			atof(st.nextToken());
			int m = st.countTokens() / 2;
			svm_node[] x = new svm_node[m];
			for (int j = 0; j < m; j++) {
				x[j] = new svm_node();
				x[j].index = atoi(st.nextToken());
				x[j].value = atof(st.nextToken());
			}

			if (predict_probability == 1
					&& (svm_type == svm_parameter.C_SVC || svm_type == svm_parameter.NU_SVC)) {
				v = svm.svm_predict_probability(model, x, prob_estimates);
				return v;
			} else {
				v = svm.svm_predict(model, x);
				return v;
			}

		} else {
			return 0;
		}

	}

}
