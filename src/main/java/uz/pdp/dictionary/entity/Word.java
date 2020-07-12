package uz.pdp.dictionary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Word {

    public Word(String wordUz, String wordRu) {
        this.wordUz = wordUz;
        this.wordRu = wordRu;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String wordUz;
    @Column(unique = true)
    private String wordRu;
    private Long createdAt=(new Date().getTime());
}
