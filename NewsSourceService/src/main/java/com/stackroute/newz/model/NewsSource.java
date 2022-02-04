package com.stackroute.newz.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "news_source")
public class NewsSource {

	@Id
	private int newsSourceId;
	private String newsSourceName;
	private String newsSourceDesc;
	private String newsSourceCreatedBy;
	private LocalDateTime newsSourceCreationDate;

	public int getNewsSourceId() {
		return newsSourceId;
	}

	public void setNewsSourceId(int newsSourceId) {
		this.newsSourceId = newsSourceId;
	}

	public String getNewsSourceName() {
		return newsSourceName;
	}

	public void setNewsSourceName(String newsSourceName) {
		this.newsSourceName = newsSourceName;
	}

	public String getNewsSourceDesc() {
		return newsSourceDesc;
	}

	public void setNewsSourceDesc(String newsSourceDesc) {
		this.newsSourceDesc = newsSourceDesc;
	}

	public String getNewsSourceCreatedBy() {
		return newsSourceCreatedBy;
	}

	public void setNewsSourceCreatedBy(String newsSourceCreatedBy) {
		this.newsSourceCreatedBy = newsSourceCreatedBy;
	}

	public LocalDateTime getNewsSourceCreationDate() {
		return newsSourceCreationDate;
	}

	public void setNewsSourceCreationDate() {
		this.newsSourceCreationDate = LocalDateTime.now();
	}

	public NewsSource(int newsSourceId, String newsSourceName, String newsSourceDesc, String newsSourceCreatedBy,
			LocalDateTime newsSourceCreationDate) {
		super();
		this.newsSourceId = newsSourceId;
		this.newsSourceName = newsSourceName;
		this.newsSourceDesc = newsSourceDesc;
		this.newsSourceCreatedBy = newsSourceCreatedBy;
		this.newsSourceCreationDate = newsSourceCreationDate;
	}

	@Override
	public String toString() {
		return "NewsSource [newsSourceId=" + newsSourceId + ", newsSourceName=" + newsSourceName + ", newsSourceDesc="
				+ newsSourceDesc + ", newsSourceCreatedBy=" + newsSourceCreatedBy + ", newssourceCreationDate="
				+ newsSourceCreationDate + "]";
	}

	public NewsSource() {
		super();
	}

}
