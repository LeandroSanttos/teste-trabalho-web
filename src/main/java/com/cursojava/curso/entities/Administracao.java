package com.cursojava.curso.entities;

import java.util.HashSet;
import java.util.Set;

public class Administracao {
    private static Set<String> emailsCadastrados = new HashSet<>();

    public static void addEmailCadastrado(String email) {
        emailsCadastrados.add(email);
    }

    public static boolean emailCadastrado(String email) {
        return emailsCadastrados.contains(email);
    }
}
