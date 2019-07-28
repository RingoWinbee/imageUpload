package com.example.entity;

import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@MappedSuperclass
public class BaseEntity {

	// 文件的哈希码,每个图片唯一
	@Id
	@Column(name = "hashCode", nullable = false)
	@GeneratedValue(generator = "id") // 注解----JPA通用策略生成器
	@GenericGenerator(name = "id", strategy = "assigned") // 注解----自定义主键生成策略
	protected String hashCode;

	// 文件的路径,其实就是哈希码的前两位后面多了一条杠
	@Column(name = "filePath", nullable = false)
	protected String filePath;

	// 文件的引用次数
	@Column(name = "leadConut", nullable = false)
	protected int leadConut;

	// 文件的引用次数
	@Column(name = "downloadConut", nullable = false)
	protected int downloadConut;

	// 文件的类型
	@Column(name = "fileType", nullable = false)
	protected String fileType;

	// 文件的长度
	@Column(name = "length", nullable = false)
	protected long length;

	public String getHashCode() {
		return hashCode;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getLeadConut() {
		return leadConut;
	}

	public void setLeadConut(int leadConut) {
		this.leadConut = leadConut;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public int getDownloadConut() {
		return downloadConut;
	}

	public void setDownloadConut(int downloadConut) {
		this.downloadConut = downloadConut;
	}
	
}
