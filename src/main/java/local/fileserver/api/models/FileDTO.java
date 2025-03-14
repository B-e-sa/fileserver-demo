package local.fileserver.api.models;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import local.fileserver.api.models.File.Extension;
import lombok.Getter;

public class FileDTO {	
	@Getter
	private int id;
	
	@Getter
	private Date createdAt;
	
	@Getter
	private int size;
	
	@Getter
	@NotNull
	private String content;
	
	@Getter
	@NotNull
	private String name;
	
	@Getter
	@NotNull
	private Extension extension;
	
	public FileDTO() {}
	
	public FileDTO(String content, String name, Extension extension) {
		this.content = content;
		this.name = name;
		this.extension = extension;
	}
	
	public File ToFile() {
		return new File(this.name, this.content, this.extension);
	}
}
