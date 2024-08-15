package com.example.coletordadoswifi;

public class Cliente {
    private String nome;
    private String cpf;
    private String telefone;
    private String nomeWifi;
    private String senhaWifi;
    private String endereco;

    public Cliente(String nome, String cpf, String telefone, String nomeWifi, String senhaWifi, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.nomeWifi = nomeWifi;
        this.senhaWifi = senhaWifi;
        this.endereco = endereco;
    }

    // Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNomeWifi() {
        return nomeWifi;
    }

    public void setNomeWifi(String nomeWifi) {
        this.nomeWifi = nomeWifi;
    }

    public String getSenhaWifi() {
        return senhaWifi;
    }

    public void setSenhaWifi(String senhaWifi) {
        this.senhaWifi = senhaWifi;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return String.format("NOME: %s\nCPF: %s\nTELEFONE: %s\nNOME DO WIFI: %s\nSENHA DO WIFI: %s\nENDEREÇO: %s",
                nome, cpf, telefone, nomeWifi, senhaWifi, endereco);
    }
}
