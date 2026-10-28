package projeto_clinica.com.dto.Request;

import projeto_clinica.com.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RoleDTO {

	private Long id;
	@NotBlank
	@Size(max = 80)
	private String authority;
	
	public RoleDTO() {
	}

	public RoleDTO(Long id, String authority) {
		super();
		this.id = id;
		this.authority = authority;
	}

	public RoleDTO(Role role) {
		super();
		id = role.getId();
		authority = role.getAuthority();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
