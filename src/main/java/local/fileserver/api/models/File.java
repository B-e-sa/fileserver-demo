package local.fileserver.api.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private LocalDate createdAt;
	
	@Getter
	private int size;
	
	@Getter
	private String content;
	
	@Getter
	private String name;
	
	@Getter
	private Extension extension;
	
	public File() {
		this.createdAt = LocalDate.now(); // needs to be defined at the deserialization,
		                                  // as it doesn't have its value given at the request body
	}
	
	public File(String name, String content, Extension extension) {
		this.name = name;
		this.content = content;
		this.extension = extension;
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
