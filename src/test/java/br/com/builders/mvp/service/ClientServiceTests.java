package br.com.builders.mvp.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.builders.mvp.domain.Client;
import br.com.builders.mvp.dto.request.ClientRequest;
import br.com.builders.mvp.dto.response.ClientResponse;
import br.com.builders.mvp.exception.BadRequestException;
import br.com.builders.mvp.exception.NotFoundException;
import br.com.builders.mvp.repository.ClientRepository;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTests {

	@InjectMocks
	private ClientService service;
	
	@Mock
	private ClientRepository repository;
		
	private ClientRequest request = ClientRequest.builder()
			.document("11111111111")
			.name("João da Silva")
			.birth(LocalDate.of(1990, 7, 21))
			.build();
	
	private ClientRequest requestUpdate = ClientRequest.builder()
			.document("22222222222")
			.name("João da Silva")
			.birth(LocalDate.of(1990, 7, 21))
			.build();
	
	private Client client = Client.builder()
			.id(4L)
			.document("11111111111")
			.name("João da Silva")
			.birth(LocalDate.of(1990, 7, 21))
			.build();
	private Optional<Client> oClient = Optional.of(client);
	
	private Client clientMapper = Client.builder()
			.document("22222222222")
			.name("João da Silva")
			.birth(LocalDate.of(1990, 7, 21))
			.build();
		
	private ClientResponse clientResponse = ClientResponse.builder()
			.id(4L)
			.document("11111111111")
			.name("João da Silva")
			.birth(LocalDate.of(1990, 7, 21))
			.build();
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void shouldInsertClientSuccess() {
		when(repository.existsByDocument(any())).thenReturn(false);
		when(repository.save(any(Client.class))).thenReturn(client);
		assertThat(client.getId(), is(service.insert(request).getId()));
	}
	
	@Test
	public void shouldInsertClientThrowBadRequestExceptionWhenClientAlreadyRegistered() {
		when(repository.existsByDocument(any())).thenReturn(true);
		String message = assertThrows(BadRequestException.class, () -> service.insert(request)).getMessage();
		assertEquals("Client already registered.", message);
	}
	
	@Test
	public void shouldUpdateClientSuccess() {
		when(repository.findById(any())).thenReturn(oClient);
		when(repository.save(any(Client.class))).thenReturn(client);
		service.update(4L,requestUpdate);
	}
	
	@Test
	public void shouldUpdateClientThrowNotFoundExceptionWhenClientWasNotFound() {
		when(repository.findById(any())).thenReturn(Optional.empty());
		assertThrows(NotFoundException.class, () -> service.update(5L,request)).getMessage();
	}
	
}
