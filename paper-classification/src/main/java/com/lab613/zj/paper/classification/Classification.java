package com.lab613.zj.paper.classification;

import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lab613.zj.paper.classification.segmentation.WordSegment;
import com.lab613.zj.paper.classification.storage.objects.URLInfo;
import com.lab613.zj.paper.classification.svm.SVMPredict;
import com.lab613.zj.paper.classification.vector.EigenVector;

public class Classification implements ClassificationService {
	private static final Log LOG = LogFactory.getLog(Classification.class);

	private List<String> featureWordsList;
	private List<Double> weightList;

	public Classification(List<String> featureWordsList, List<Double> weightList) {
		this.featureWordsList = featureWordsList;
		this.weightList = weightList;
	}

	@Override
	public URLInfo getClassificationResult(String sourceCode, String ip,
			String url, String title, String description) {
		/*
		 * 1、分词
		 */
		List<String> wordSegmentList = WordSegment.wordSegment(sourceCode,
				title);

		/*
		 * 2、选取特征词
		 */

		/*
		 * 3、特征向量标准化
		 */

		String vector = EigenVector.eigenVector(featureWordsList, weightList,
				wordSegmentList);
		/*
		 * 4、分类
		 */
		try {
			double categoryNum = SVMPredict.predict(vector);
		} catch (IOException e) {
			LOG.error("分类出现错误");
		}

		return null;
	}
}
