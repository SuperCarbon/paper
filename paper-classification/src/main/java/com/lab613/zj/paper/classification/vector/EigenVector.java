package com.lab613.zj.paper.classification.vector;

import java.util.Iterator;
import java.util.List;

public class EigenVector {

	public static void main(String[] args) {

	}

	// 特征向量的格式为:Label 1:value 2:value;例如-15 1:0.708 7:0.1056 9:-0.3333;
	// 需要注意的是，如果特征值为0，特征冒号前面的序号可以不连续,即使不知道label的值，也要任意填一个
	/**
	 * @Title: eigenVector
	 * @Description: 建立标准的特征向量,作为分类模型的输入
	 * @param featureWordsList 有序的特征词典
	 * @param weightList	对应特征词典的权重值
	 * @param wordSegmentList	待分类的文本分词结果
	 * @return	特征向量
	 * @throws
	 */
	public static String eigenVector(List<String> featureWordsList,
			List<Double> weightList, List<String> wordSegmentList) {
		String vector = "0	";

		Iterator<String> iter = wordSegmentList.iterator();
		while (iter.hasNext()) {
			String word = iter.next();
			int index = featureWordsList.indexOf(word);
			double weight = weightList.get(index);
			vector = vector + index + ":" + weight + "	";
		}
		return vector;
	}

}
