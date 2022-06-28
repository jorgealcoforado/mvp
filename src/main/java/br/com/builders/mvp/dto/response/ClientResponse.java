package br.com.builders.mvp.dto.response;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

import br.com.builders.mvp.domain.Client;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Schema(name = "Client Response")
public class ClientResponse implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2784869556071927567L;

	private Long id;
	
	private String document;
	
	private String name;
	
	private LocalDate birth;
		
	public int getAge() {
		return Period.between(birth, LocalDate.now()).getYears();
	}

	public static ClientResponse fromEntity(Client entity) {
		return ClientResponse.builder()
				.id(entity.getId())
				.document(entity.getDocument())
				.name(entity.getName())
				.birth(entity.getBirth())
				.build();
	}
}
