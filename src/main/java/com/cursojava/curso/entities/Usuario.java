package com.cursojava.curso.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.cursojava.curso.entities.exceptions.LoginAtualizadoException;
import com.cursojava.curso.entities.exceptions.LoginIncorretoException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String senha;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(Long id, String nome, String email, String telefone, String senha) throws IllegalArgumentException {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }

    public void mudarNome(String nome, String email, String senha, String novoNome) throws LoginIncorretoException, LoginAtualizadoException {
        if (!nome.equalsIgnoreCase(this.getNome()) || !email.equalsIgnoreCase(this.getEmail()) || !senha.equals(this.getSenha())) {
            throw new LoginIncorretoException("Nome, e-mail ou senha incorretos!");
        }
        setNome(novoNome);
        throw new LoginAtualizadoException("Nome atualizado com sucesso");
    }

    public void mudarEmail(String nome, String email, String senha, String novoEmail) throws LoginIncorretoException, LoginAtualizadoException {
        if (!nome.equalsIgnoreCase(this.getNome()) || !email.equalsIgnoreCase(this.getEmail()) || !senha.equals(this.getSenha())) {
            throw new LoginIncorretoException("Nome, e-mail ou senha incorretos!");
        }
        setEmail(novoEmail);
        throw new LoginAtualizadoException("E-mail atualizado com sucesso");
    }

    public void mudarSenha(String nome, String email, String senha, String novaSenha) throws LoginIncorretoException, LoginAtualizadoException {
        if (!nome.equalsIgnoreCase(this.getNome()) || !email.equalsIgnoreCase(this.getEmail()) || !senha.equals(this.getSenha())) {
            throw new LoginIncorretoException("Nome, e-mail ou senha incorretos!");
        }
        setSenha(novaSenha);
        throw new LoginAtualizadoException("Senha atualizada com sucesso");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Pedido> getPedidos() {
        return List.copyOf(pedidos);
    }

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}
    
}
