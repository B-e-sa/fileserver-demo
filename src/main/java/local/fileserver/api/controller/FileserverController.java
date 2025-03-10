package local.fileserver.api.controller;import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import local.fileserver.api.models.File;
import local.fileserver.api.services.FileserverService;

@RestController()
public class FileserverController {
	@Autowired
	FileserverService server;
	
	@GetMapping("{name}")
	public String findFile(@PathVariable String name)
	{
		File foundFile = server.findByName(name);
		if (foundFile == null)
			return "Not found";
		return "Found";
	}
	
	@PostMapping()
	public String pushFile(@RequestBody File file)
	{	
		if (file.getName() == null || 
				file.getContent() == null ||
				file.getExtension() == null)
			return "Bad formated file";
		boolean sucess = server.push(file);
		if (!sucess)
			return "File already exists";
		return "File added";
	}
	
	@DeleteMapping("{name}")
	public String deleteFile(@PathVariable("name") String name)
	{
		boolean sucess = server.delete(name);
		if (!sucess)
			return "File does not exist";
		return "Sucess";
	}
	
	@GetMapping()
	public List<File> listFiles()
	{
		return server.list();
	}
}
