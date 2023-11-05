package ru.hogwarts.school.model;

import lombok.*;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@Data
@Getter
@Setter
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filePath;
    private long fileSize;
    private String mediaType;
    @Lob
    private byte[] data;
    @OneToOne
    private Student student;

}
