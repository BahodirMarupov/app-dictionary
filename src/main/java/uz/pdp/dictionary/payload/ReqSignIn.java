package uz.pdp.dictionary.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data@AllArgsConstructor@NoArgsConstructor
public class ReqSignIn {
    @NotBlank
    private String username;
    @Pattern(regexp = "^[A-Z][a-z]{2,16}$",message = "Wrong entered name")
    private String password;
}
