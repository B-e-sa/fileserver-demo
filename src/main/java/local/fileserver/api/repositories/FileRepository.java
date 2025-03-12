package local.fileserver.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import local.fileserver.api.models.File;

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {
	 File findByName(String name);
}
