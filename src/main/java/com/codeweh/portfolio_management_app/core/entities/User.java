package com.codeweh.portfolio_management_app.core.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;

}
