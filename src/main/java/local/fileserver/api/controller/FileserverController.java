package local.fileserver.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import local.fileserver.api.models.File;
import local.fileserver.api.models.FileDTO;
import local.fileserver.api.services.FileserverService;

@RestController()
public class FileserverController {
	@Autowired
	FileserverService server;
	
	@GetMapping("{name}")
	public ResponseEntity<File> findFile(@PathVariable String name) throws NotFoundException
	{
		File foundFile = server.findByName(name);
		return new ResponseEntity<>(foundFile, HttpStatus.FOUND);
	}
	
	@PostMapping()
	public ResponseEntity<HttpStatus> pushFile(@Valid @RequestBody FileDTO file)
	{			
		server.push(file);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("{name}")
	public ResponseEntity<HttpStatus> deleteFile(@PathVariable("name") String name) throws NotFoundException
	{
		server.delete(name);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<List<File>> listFiles()
	{
		return new ResponseEntity<>(server.list(), HttpStatus.OK);
	}
}
