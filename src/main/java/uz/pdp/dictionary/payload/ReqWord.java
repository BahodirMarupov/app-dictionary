package uz.pdp.dictionary.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data@AllArgsConstructor@NoArgsConstructor
public class ReqWord {
    private Long id;
    private String wordUz;
    private String wordRu;
}
