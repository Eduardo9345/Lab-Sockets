package com.example;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ServidorMensagens {
    private static List<String> mensagens = new ArrayList<>();

    public static void main(String[] args) {
        final int PORTA = 12345;

        try (InputStream is = ServidorMensagens.class.getResourceAsStream("/mensagens.txt")) {
            if (is == null) {
                System.out.println("Arquivo de mensagens não localizado.");
                return;
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    if (!linha.isBlank()) {
                        mensagens.add(linha.trim());
                    }
                }
            }

            System.out.println("Servidor ativo na porta " + PORTA + ". Aguardando conexões...");

            try (ServerSocket servidor = new ServerSocket(PORTA)) {
                while (true) {
                    Socket cliente = servidor.accept();
                    System.out.println("Conexão recebida de " + cliente.getInetAddress());

                    Thread atendimento = new Thread(new AtendimentoCliente(cliente, mensagens));
                    atendimento.start();
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao iniciar servidor: " + e.getMessage());
        }
    }
}
