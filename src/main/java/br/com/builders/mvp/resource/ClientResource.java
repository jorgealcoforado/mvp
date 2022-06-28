package br.com.builders.mvp.resource;

import static org.springframework.http.ResponseEntity.ok;

import java.time.LocalDate;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.builders.mvp.dto.request.ClientRequest;
import br.com.builders.mvp.dto.response.ClientResponse;
import br.com.builders.mvp.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/client")
public class ClientResource {

	@Autowired
	private ClientService service;
	
	@Operation(summary = "Insert a Client")
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<ClientResponse> insert(@Valid @RequestBody ClientRequest request){
		return ok(service.insert(request));
	}
	
	@Operation(summary = "List all Clients")
	@GetMapping("/findAll")
	@ResponseStatus(HttpStatus.OK) 
	public @ResponseBody ResponseEntity<Page<ClientResponse>> findAll(Pageable pageable){
		return ok(service.findAll(pageable));
	}
	
	@Operation(summary = "Find one Client")
	@GetMapping("/findOne")
	@ResponseStatus(HttpStatus.OK) 
	public @ResponseBody ResponseEntity<ClientResponse> findByIdOrDocument(
			@Parameter(name = "id", description = "id") @RequestParam(value = "id", defaultValue = "") final Long id
			,@Parameter(name = "document", description = "document") @RequestParam(value = "document", defaultValue = "") final String document
			){
		return ok(service.findByIdOrDocument(id,document));
	}
	
	@Operation(summary = "List Clients")
	@GetMapping("/list")
	@ResponseStatus(HttpStatus.OK) 
	public @ResponseBody ResponseEntity<Collection<ClientResponse>> listByIdOrDocumentOrNameOrBirth(
			@Parameter(name = "id", description = "id") @RequestParam(value = "id", defaultValue = "") final Long id
			,@Parameter(name = "document", description = "document") @RequestParam(value = "document", defaultValue = "") final String document
			,@Parameter(name = "name", description = "name") @RequestParam(value = "name", defaultValue = "") final String name
			,@Parameter(name = "birth", description = "birth") @RequestParam(value = "birth", defaultValue = "") final LocalDate birth
			){
		return ok(service.listByIdOrDocumentOrNameOrBirth(id,document,name,birth));
	}
	
	@Operation(summary = "Update a Client")
	@PatchMapping("/{clientId}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<ClientResponse> update(
			@Parameter(name = "clientId", required = true) @PathVariable(name = "clientId") final Long id,
			@Valid @RequestBody ClientRequest request){
		return ok(service.update(id,request));
	}
	
}
