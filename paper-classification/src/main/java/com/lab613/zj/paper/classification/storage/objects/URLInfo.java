package com.lab613.zj.paper.classification.storage.objects;

import java.util.List;

public class URLInfo {
	private String url;
	private List<String> categories;
	private String ip;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
