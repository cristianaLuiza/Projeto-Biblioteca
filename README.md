# 📚 Projeto Biblioteca Comunitária

## 🌱 Sobre o Projeto

O **Biblioteca Comunitária** é um sistema desenvolvido em Java com foco em auxiliar o gerenciamento de doações de livros e incentivar o acesso à leitura através de uma biblioteca comunitária.

O projeto foi criado com o objetivo de organizar informações de:

* 📖 Livros
* 👥 Doadores
* 🙋 Receptores
* ❤️ Interesses em livros
* 📦 Doações
* 🌍 Localização (CEP, Cidade e UF)

A proposta é facilitar o controle das doações e aproximar pessoas interessadas em compartilhar conhecimento através da leitura.

---

# 🚀 Tecnologias Utilizadas

* ☕ Java
* 🗄️ JDBC
* 🐬 MySQL
* 🧩 Maven
* 💻 Programação Orientada a Objetos (POO)
* 🔗 DAO Pattern
* 🛠️ Git e GitHub

---

# 📂 Estrutura do Projeto

```bash
src/
 └── main/
     └── java/
         └── br/com/doacaolivros/
             ├── dao/
             ├── model/
             └── util/
```

---

# 🧠 Funcionalidades

## 📚 Gerenciamento de Livros

* Cadastro de livros
* Consulta de livros
* Controle de disponibilidade

## 👥 Gerenciamento de Pessoas

* Cadastro de doadores
* Cadastro de receptores
* Controle de interesses

## ❤️ Sistema de Doações

* Registro de doações
* Associação entre doador e receptor
* Controle de status da doação

## 🌎 Localização

* Cadastro de cidade
* Cadastro de UF
* Cadastro de CEP

---

# 🏗️ Arquitetura

O projeto utiliza o padrão **DAO (Data Access Object)** para separar a lógica de acesso ao banco de dados da lógica da aplicação.

### 📌 Camadas:

* `model` → Entidades do sistema
* `dao` → Regras de acesso ao banco de dados
* `util` → Utilitários e conexão JDBC

---

# ▶️ Como Executar o Projeto

## 1️⃣ Clone o repositório

```bash
git clone https://github.com/cristianaLuiza/Projeto-Biblioteca.git
```

---

## 2️⃣ Abra o projeto na IDE

Pode ser executado em:

* IntelliJ IDEA
* VS Code
* Eclipse

---

## 3️⃣ Configure o banco de dados

Configure as informações de conexão na classe:

```bash
util/Conexao.java
```

---

## 4️⃣ Execute o projeto

Compile e execute utilizando Maven ou sua IDE.

---

# 🎯 Objetivo do Projeto

Este projeto foi desenvolvido com foco em aprendizado prático de:

* Programação Orientada a Objetos
* Persistência de dados com JDBC
* Estruturação de projetos Java
* Boas práticas de desenvolvimento
* Organização em camadas
* Manipulação de banco de dados

Além disso, o sistema possui um propósito social, incentivando a reutilização de livros e o acesso à leitura.

---

# 👩‍💻 Desenvolvedora

**Cristiana Luiza Martins de Azevedo**

🎓 Estudante de Tecnologia em Informática para Negócios - FATEC

💻 Desenvolvedora focada em Back-End Java

🌱 Apaixonada por tecnologia, educação e projetos com impacto social.

---

# 🔗 GitHub

📎 Repositório:

```bash
https://github.com/cristianaLuiza/Projeto-Biblioteca
```

---

# ⭐ Futuras Melhorias

* Interface gráfica
* API REST com Spring Boot
* Sistema de autenticação
* Relatórios de doações
* Dashboard administrativo
* Deploy em nuvem

---

# 📖 Licença
