package uz.pdp.dictionary.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data@AllArgsConstructor@NoArgsConstructor
public class ReqUser {
    @NotNull
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z1-7]{2,16}$",message = "Wrong entered name")
    private String firstName;
    @NotNull
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z1-7]{2,16}$",message = "Wrong entered name")
    private String lastName;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z1-7]{3,12}$",message = "Wrong entered name")
    private String username;
    @NotBlank
    @Pattern(regexp = "((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20})", message = "Not allowed password")
    private String password;
    @NotBlank
    @Pattern(regexp = "((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20})", message = "Not allowed password")
    private String prePassword;
}
