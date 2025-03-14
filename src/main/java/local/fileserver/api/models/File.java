package local.fileserver.api.models;

import java.util.Date;

import org.modelmapper.ModelMapper;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;

@Data
@Entity
public class File {	
	public enum Extension {
		mp4,
		jpeg,
		txt
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Getter
	private Date createdAt;
	
	@Getter
	private int size;
	
	@Getter
	@Column(nullable = false)
	private String content;
	
	@Getter
	@Column(nullable = false)
	private String name;
	
	@Getter
	@Column(nullable = false)
	private Extension extension;
	
	public File() {
		this.createdAt = new Date(); // needs to be defined at the deserialization,
		                             // as the value is not given at the request body
	}
	
	public File(String name, String content, Extension extension) {
		this.name = name;
		this.content = content;
		this.extension = extension;
		this.createdAt = new Date(); // if File was instantiated
		setFileSize();
	}
	
	/**
	 * Set the total file size based on the 
	 * content and name length using UTF-8
	 */
	private void setFileSize()
	{
		int contentSize = (this.content != null) ? this.content.getBytes().length : 0;
        int nameSize = (this.name != null) ? this.name.getBytes().length : 0;
        this.size = nameSize + contentSize;
	}
	
	public void setContent(String content)
	{
		this.content = content;
		this.setFileSize();
	}
	
	public void setName(String name)
	{
		this.name = name;
		this.setFileSize();
	}
}
