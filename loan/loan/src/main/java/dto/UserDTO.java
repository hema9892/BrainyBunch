package dto;

import lombok.Builder;

@Builder
public class UserDTO {
	
	public long id;
	public String username;
    public String password;
    public String email;

}
