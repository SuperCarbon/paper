package com.lab613.zj.paper.classification;

import com.lab613.zj.paper.classification.storage.objects.URLInfo;

public interface ClassificationService {

	URLInfo getClassificationResult(String sourceCode, String ip, String url,
			String title, String description);

}
