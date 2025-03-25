/*Descrição do Trabalho: Desenvolvimento de Sistema de Programação Web


Desenvolver um sistema web funcional e seguro, que inclua funcionalidades de cadastro, login, gerenciamento de dados do usuário e 
CRUD (Create, Read, Update, Delete) de cinco entidades. 
O projeto será desenvolvido por uma equipe de 5 pessoas, incentivando a aplicação de boas práticas de desenvolvimento e trabalho em equipe.
Para o sistema vocês têm a liberdade de escolher qual domínio de negócio utilizar, ou seja, 
a ideia do sistema é com o grupo desde que siga os requisitos abaixo.

Requisitos do Sistema
1. Funcionalidades Gerais:
Autenticação e Autorização:


Implementar sistema de cadastro de usuários, incluindo validação de dados:
O usuário terá pelo menos os campos email, nome e senha. Outros campos podem ser colocados.
Usuários cadastrados iniciaram com status inativo.
Implementar funcionalidade de login onde o usuário deverá logar com email e senha.
Só usuários ativos poderão logar.
Permitir ao usuário realizar a alteração de seus dados pessoais, como nome, senha e e-mail.
Proteger o acesso às funcionalidades internas do sistema, de modo que apenas usuários logados possam acessar as telas de CRUD das entidades.
CRUD das Entidades:


O sistema deverá conter 5 entidades principais, cada uma com suas respectivas operações de CRUD:
Entidade 1 (exemplo, Produtos).
Entidade 2 (exemplo, Categorias).
Entidade 3 (exemplo, Pedidos).
Entidade 4 (exemplo, Clientes).
Entidade 5 (exemplo, Fornecedores).
Cada entidade deve ter uma interface gráfica dedicada para as operações de CRUD:
Criação de registros (Create).
Visualização de registros (Read).
Edição de registros (Update) (Pode ser usado o mesma tela da criação).
Exclusão de registros (Delete) (Não precisa de tela, apenas um botão de exclusão já serve).

Regras de Negócio
O acesso às telas de CRUD das entidades será permitido apenas para usuários autenticados.
Cada entidade deve estar relacionada a um modelo de dados no banco de dados, 
incluindo validações para evitar inconsistências (e.g., campos obrigatórios, formatos específicos).
Os dados de autenticação e das entidades devem ser armazenados de forma segura no banco de dados.
 */

package com.cursojava.curso.negocios;

import com.cursojava.curso.entities.Usuario;
import com.cursojava.curso.negocios.exceptions.UsuarioNaoCadastradoException;

public class UsuarioNegocio {

    public Usuario novoUsuario(Long id, String nome, String email, String telefone, String senha) throws UsuarioNaoCadastradoException {
        validarNovoUsuario(id, nome, email, telefone, senha);
        Usuario novoUsuario = new Usuario(null, nome, email, telefone, senha);
        return novoUsuario;
    }

    public void validarNovoUsuario(Long id, String nome, String email, String telefone, String senha) throws UsuarioNaoCadastradoException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("E-mail não pode ser vazio.");
        }
        if (telefone == null || telefone.trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone não pode ser vazio.");
        }
        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("Senha não pode ser vazia.");
        }
    }

    /*public Usuario Login(String nome, String email, String senha) {
        if () {

        }
    }*/
}
