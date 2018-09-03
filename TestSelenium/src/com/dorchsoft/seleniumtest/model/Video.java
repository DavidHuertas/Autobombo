package com.dorchsoft.seleniumtest.model;

public class Video {

	private String title;
	private String url;
	private Integer time;

	public Video() {
	}

	public Video(String title, String url, Integer time) {
		super();
		this.title = title;
		this.url = url;
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Video =>\ttitle: " + title + "\t url: " + url + "\t time: " + time;
	}
	
}
