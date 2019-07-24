package tri.chung.sbjwt.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {

  @NotBlank
  @Size(min = 4, max = 40)
  private String fullname;

  @NotBlank
  @Size(min = 3, max = 15)
  private String username;

  @NotBlank
  @Size(min = 6, max = 20)
  private String password;

}
