package com.example;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Random;

public class AtendimentoCliente implements Runnable {
    private Socket socket;
    private List<String> listaMensagens;
    private Random sorteio = new Random();

    public AtendimentoCliente(Socket socket, List<String> mensagens) {
        this.socket = socket;
        this.listaMensagens = mensagens;
    }

    @Override
    public void run() {
        try (
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            DataOutputStream saida = new DataOutputStream(socket.getOutputStream())
        ) {
            boolean ativo = true;

            while (ativo) {
                int codigo = entrada.readInt();
                boolean encerrar = entrada.readBoolean();

                if (codigo == 0) {
                    int aleatoria = sorteio.nextInt(listaMensagens.size());
                    responder(saida, "SUCESSO", listaMensagens.get(aleatoria));
                } else if (codigo >= 1 && codigo <= listaMensagens.size()) {
                    responder(saida, "SUCESSO", listaMensagens.get(codigo - 1));
                } else {
                    responder(saida, "ERRO", "Código inválido. Use um número de 1 a " + listaMensagens.size() + ", ou 0 para aleatório.");
                }

                if (encerrar) ativo = false;
            }

            System.out.println("Cliente finalizou a conexão.");
            socket.close();

        } catch (IOException e) {
            System.out.println("Falha com cliente: " + e.getMessage());
        }
    }

    private void responder(DataOutputStream saida, String status, String conteudo) throws IOException {
        saida.writeUTF(status);
        saida.writeUTF(conteudo);
    }
}
