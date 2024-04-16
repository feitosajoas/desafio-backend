package com.example.contacts.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "contato_id")
@Table(name = "contato")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contato_id;

    @Size(min = 5, max = 100, message = "O Nome deve conter entre 5 a 100 caracteres")
    @NotBlank(message = "O nome não pode ser vazio")
    @NotNull
    private String contato_nome;


    @Size(min = 5, max = 255, message = "O Email deve conter entre 5 a 255 caracteres")
    @NotBlank(message = "O Email não pode ser vazio")
    @NotNull
    private String contato_email;

    @Size(min = 5, max = 11, message = "O Nº do celular deve conter entre 5 a 11 caracteres")
    @NotBlank(message = "O Nº do celular não pode ser vazio")
    @Column(name = "contato_celular")
    @NotNull
    private String phoneNumber;

    private String contato_telefone;

    private byte contato_sn_favorito;

    private byte contato_sn_ativo;

    private LocalDateTime contato_dh_cad;

}
