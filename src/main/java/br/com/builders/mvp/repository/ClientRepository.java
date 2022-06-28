package br.com.builders.mvp.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.builders.mvp.domain.Client;

@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {

	Optional<Client> findByIdOrDocument(Long id, String document);
	
	boolean existsByDocument(String document);
	
	List<Client> findByIdOrDocumentOrNameOrBirth(Long id, String document, String name, LocalDate birth);
}
