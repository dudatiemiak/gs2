package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;

public class ConsultoriaTO {
    private Long id_consultoria;
    @NotBlank
    private String nome_usuario;
    @NotBlank
    private String email_usuario;
    @NotBlank
    private String duvidas;

    public ConsultoriaTO() {
    }

    public ConsultoriaTO(Long id_consultoria, String nome_usuario, String email_usuario, String duvidas) {
        this.id_consultoria = id_consultoria;
        this.nome_usuario = nome_usuario;
        this.email_usuario = email_usuario;
        this.duvidas = duvidas;
    }

    public Long getId_consultoria() {
        return id_consultoria;
    }

    public void setId_consultoria(Long id_consultoria) {
        this.id_consultoria = id_consultoria;
    }

    public @NotBlank String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(@NotBlank String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public @NotBlank String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(@NotBlank String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public @NotBlank String getDuvidas() {
        return duvidas;
    }

    public void setDuvidas(@NotBlank String duvidas) {
        this.duvidas = duvidas;
    }
}
