package br.com.fiap.to;

import jakarta.validation.constraints.*;

public class UsuarioTO {
    private Long id_usuario;
    @NotBlank
    private String nome;
    @NotBlank
    @Email
    private String email;
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11}", message = "O CPF deve estar no formato 000.000.000-00 ou conter apenas 11 dígitos numéricos")
    @NotBlank
    private String cpf;
    @NotBlank
    @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres")
    private String senha;

    public UsuarioTO() {
    }

    public UsuarioTO(Long id_usario, String nome, String email, String cpf, String senha) {
        this.id_usuario = id_usario;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usario) {
        this.id_usuario = id_usario;
    }

    public @NotBlank String getNome() {
        return nome;
    }

    public void setNome(@NotBlank String nome) {
        this.nome = nome;
    }

    public @NotBlank @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank @Email String email) {
        this.email = email;
    }

    public @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11}", message = "O CPF deve estar no formato 000.000.000-00 ou conter apenas 11 dígitos numéricos") String getCpf() {
        return cpf;
    }

    public void setCpf(@Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11}", message = "O CPF deve estar no formato 000.000.000-00 ou conter apenas 11 dígitos numéricos") String cpf) {
        this.cpf = cpf;
    }

    public @NotBlank @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres") String getSenha() {
        return senha;
    }

    public void setSenha(@NotBlank @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres") String senha) {
        this.senha = senha;
    }
}
