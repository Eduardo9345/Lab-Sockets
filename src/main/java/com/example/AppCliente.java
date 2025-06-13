package com.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class AppCliente {
    public static void main(String[] args) {
        final String HOST = "localhost";
        final int PORTA = 12345;

        try (
            Socket conexao = new Socket(HOST, PORTA);
            DataOutputStream out = new DataOutputStream(conexao.getOutputStream());
            DataInputStream in = new DataInputStream(conexao.getInputStream());
            Scanner input = new Scanner(System.in)
        ) {
            System.out.println("Conectado ao servidor de frases!");

            boolean rodando = true;
            while (rodando) {
                System.out.print("Informe o número da frase (0 = aleatória): ");
                int escolha = Integer.parseInt(input.nextLine());

                System.out.print("Deseja finalizar após essa? (true/false): ");
                boolean fim = Boolean.parseBoolean(input.nextLine());

                out.writeInt(escolha);
                out.writeBoolean(fim);

                String status = in.readUTF();
                String resposta = in.readUTF();

                if (status.equals("SUCESSO")) {
                    System.out.println("Mensagem recebida: " + resposta);
                } else {
                    System.out.println("Atenção: " + resposta);
                }

                if (fim) rodando = false;
            }

            System.out.println("Finalizando conexão.");
        } catch (IOException e) {
            System.out.println("Não foi possível se conectar: " + e.getMessage());
        }
    }
}
