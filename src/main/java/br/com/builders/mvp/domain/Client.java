package br.com.builders.mvp.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@EqualsAndHashCode(callSuper=false, of={"document","name"})
@Table
public class Client implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6248896456542475135L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "document", nullable = false)
	private String document;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "birth", nullable = false)
	private LocalDate birth;
}
