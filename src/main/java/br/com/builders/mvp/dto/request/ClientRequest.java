package br.com.builders.mvp.dto.request;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.builders.mvp.domain.Client;
import br.com.builders.mvp.dto.response.ClientResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Schema(name = "Client Request")
public class ClientRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1866630663897758593L;
	
	@NotEmpty
	@Schema(required = true)
	private String document;
	
	@NotEmpty
	@Schema(required = true)
	private String name;
	
	@NotNull
	@Schema(required = true)
	private LocalDate birth;
	
	public Client toDomain(Long id, ClientRequest request) {
		return Client.builder()
				.id(id)
				.document(request.getDocument())
				.name(request.getName())
				.birth(request.getBirth())
				.build();
	}
}
