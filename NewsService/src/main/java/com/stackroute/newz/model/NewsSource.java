package com.stackroute.newz.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "news.source")
public class NewsSource {

	@Id
	private int newsSourceId;
	private String newsSourceName;
	private String newsSourceDesc;
	private String newsSourceCreatedBy;
	private Date newsSourceCreationDate;

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

	public Date getNewsSourceCreationDate() {
		return newsSourceCreationDate;
	}

	public void setNewsSourceCreationDate() {
		LocalDateTime dateTime = LocalDateTime.now();
		Instant i = dateTime.atZone(ZoneId.systemDefault()).toInstant();
		Date date = Date.from(i);
		this.newsSourceCreationDate = date;

	}

	public NewsSource(int newsSourceId, String newsSourceName, String newsSourceDesc, String newsSourceCreatedBy,
			Date newsSourceCreationDate) {
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
