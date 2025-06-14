# Sistema de Mensagens via Sockets em Java

## 📋 Descrição

Este projeto é um sistema simples de comunicação entre cliente e servidor, desenvolvido em Java, utilizando sockets TCP. O cliente pode solicitar mensagens específicas por número ou optar por receber uma mensagem aleatória enviada pelo servidor.

O projeto foi elaborado como parte da disciplina **Sistemas Distribuídos** do IFPB - Campus Monteiro, com orientação da professora **Larissa Lucena**.

---

## 📐 Arquitetura

- **Servidor:** Aceita múltiplas conexões simultâneas. Ao receber uma requisição, responde com uma mensagem (aleatória ou pelo número solicitado).
- **Cliente:** Interface via terminal que permite enviar comandos ao servidor e visualizar as mensagens retornadas.
- **Protocolo:** Comunicação binária com envio de `int` e `boolean` na requisição, e resposta com dois campos `UTF`.

---

## 🧪 Funcionalidades

- ✅ Enviar número para solicitar uma mensagem específica.
- 🎲 Enviar 0 para receber uma mensagem aleatória.
- 🔄 Conexão persistente ou encerrada conforme escolha do cliente.
- 👥 Suporte a múltiplos clientes ao mesmo tempo.
- 🗂️ Leitura de mensagens a partir de um arquivo `.txt` externo.

---

## 📁 Estrutura do Projeto

```

src/
├── com/
│   └── example/
│       ├── AppCliente.java         # Código do cliente
│       ├── ServidorMensagens.java  # Código do servidor
│       └── AtendimentoCliente.java # Thread para tratar cada cliente
resources/
└── com/example/mensagens.txt       # Arquivo de mensagens

````

---

## ▶️ Como Executar

### 1. Inicie o Servidor

No terminal, dentro do diretório do projeto, execute:

```bash
java com.example.ServidorMensagens
````

Certifique-se de que o arquivo `mensagens.txt` está no local correto e acessível.

### 2. Inicie o Cliente

Em outro terminal:

```bash
java com.example.AppCliente
```

Siga as instruções do terminal para enviar comandos ao servidor.

---

## 📝 Observações

* O arquivo `mensagens.txt` deve conter uma mensagem por linha.
* As mensagens são indexadas a partir de 1.
* O projeto foi desenvolvido com fins educacionais, utilizando conceitos de redes, paralelismo e fluxo de dados.

---
