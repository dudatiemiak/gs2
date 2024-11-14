package br.com.fiap.to;

public class ConsultoriaTO {
    private String id_consultoria;
    private String nome_usuario;
    private String email_usuario;
    private String duvidas;

    public ConsultoriaTO() {
    }

    public ConsultoriaTO(String id_consultoria, String nome_usuario, String email_usuario, String duvidas) {
        this.id_consultoria = id_consultoria;
        this.nome_usuario = nome_usuario;
        this.email_usuario = email_usuario;
        this.duvidas = duvidas;
    }

    public String getId_consultoria() {
        return id_consultoria;
    }

    public void setId_consultoria(String id_consultoria) {
        this.id_consultoria = id_consultoria;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public String getDuvidas() {
        return duvidas;
    }

    public void setDuvidas(String duvidas) {
        this.duvidas = duvidas;
    }
}
