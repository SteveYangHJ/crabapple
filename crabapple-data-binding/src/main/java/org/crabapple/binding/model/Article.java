package org.crabapple.binding.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Model Class
 * 
 * @author Steve Yang, 2012-10-18 PM 07:03:24
 */
@XmlRootElement
public class Article implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String title;
	private Author author;
	private Date publishDate;
	private String summary;
	private String body;
	private String[] comments;

	// Constructor
	public Article() {
	}

	public Article(int id, String title, Author author, Date publishDate,
			String summary, String body) {
		this.id = id;
		this.title = title;
		this.setAuthor(author);
		this.publishDate = publishDate;
		this.summary = summary;
		this.body = body;
	}

	public Article(int id, String title, Author author, Date publishDate,
			String summary) {
		this.id = id;
		this.title = title;
		this.setAuthor(author);
		this.publishDate = publishDate;
		this.summary = summary;
	}

	// Getter/Setter
	@XmlElement(name = "articleId")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String[] getComments() {
		return comments;
	}

	public void setComments(String[] comments) {
		this.comments = comments;
	}
	
	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
}