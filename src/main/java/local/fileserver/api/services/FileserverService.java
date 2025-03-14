package local.fileserver.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import local.fileserver.api.common.exceptions.ConflictException;
import local.fileserver.api.common.exceptions.NotFoundException;
import local.fileserver.api.models.File;
import local.fileserver.api.models.FileDTO;
import local.fileserver.api.repositories.FileRepository;

@Service
public class FileserverService {
	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	private FileRepository fileRepository;
	
	private File findByNameHelper(String name) {
		File foundFile = fileRepository.findByName(name);
		return foundFile;
	}
	
	/**
	 * Searches for the file by name
	 * 
	 * @param name the file name
	 * @return the file if it exists
	 * @throws NotFoundException, it the file does not exists
	 */
	public File findByName(String name) throws NotFoundException
	{
		File foundFile = this.findByNameHelper(name);
		
		if (foundFile == null)
			throw new NotFoundException();
		
		return foundFile;
	}
	
	/**
	 * Pushes an new file to the file array, if it's name does not exists yet
	 * 
	 * @param name the file name to add 
	 * @return true if the file doesn't exist already, or false if it does.
	 */
	public void push(FileDTO dto)
	{	
		File foundFile = this.findByNameHelper(dto.getName());
		
		if (foundFile != null)
			throw new ConflictException("A file with the same name already exists");
		
		fileRepository.save(dto.ToFile());
	}
	
	/**
	 * Delete the given file by name if it exists
	 * 
	 * @param name the file name to delete 
	 * @return true if the file exists
	 * @throws NotFoundException, if the file does not exists
	 */
	public void delete(String name) throws NotFoundException
	{
        File fileToDelete = this.findByName(name);
        fileRepository.delete(fileToDelete);
	}
	
	/**
	 * List all the current files in the array
	 * 
	 * @return the files array, it can be empty
	 */
	public List<File> list()
	{
		 return fileRepository
				 	.findAll();
		//		 	.stream()
	    //            .map(file -> modelMapper.map(file, FileDTO.class))
	    //            .collect(Collectors.toList());
	}
}
