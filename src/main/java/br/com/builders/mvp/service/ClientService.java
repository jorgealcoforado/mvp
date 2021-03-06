package br.com.builders.mvp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.builders.mvp.domain.Client;
import br.com.builders.mvp.dto.request.ClientRequest;
import br.com.builders.mvp.dto.response.ClientResponse;
import br.com.builders.mvp.exception.BadRequestException;
import br.com.builders.mvp.exception.NotFoundException;
import br.com.builders.mvp.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	public ClientResponse insert(ClientRequest request) {
		if(repository.existsByDocument(request.getDocument()))
			throw new BadRequestException("Client already registered.");
		Client client = ClientRequest.toDomain(request);
		return ClientResponse.fromEntity(repository.save(client));
	}
	
	public Page<ClientResponse> findAll(Pageable pageable){
		return repository.findAll(pageable).map(ClientResponse::fromEntity);
	}
		
	public List<ClientResponse> listByIdOrDocumentOrNameOrBirth(Long id, String document, String name, LocalDate birth){
		if(Objects.isNull(id) && Objects.isNull(document) && Objects.isNull(name) && Objects.isNull(birth))
			throw new BadRequestException("Id, Document, Name or Birth must be filled.");
		return repository.findByIdOrDocumentOrNameOrBirth(id, document, name, birth).stream().map(ClientResponse::fromEntity).toList();
	}
	
	public ClientResponse findByIdOrDocument(Long id, String document){
		if(Objects.isNull(id) && Objects.isNull(document))
			throw new BadRequestException("Id or Document must be filled.");
		Client client = repository.findByIdOrDocument(id,document).orElseThrow(()-> new NotFoundException("Client was not found."));
		return ClientResponse.fromEntity(client);
	}
	
	public ClientResponse update(Long id, ClientRequest request) {
		Client client = repository.findById(id).orElseThrow(()-> new NotFoundException("Client was not found."));
		client = ClientRequest.toDomain(id,request);
		return ClientResponse.fromEntity(repository.save(client));
	}

}
