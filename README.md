Clínica Veterinária - Sistema de Gerenciamento (Fase 2: NoSQL)

Este projeto consiste na Fase 2 do trabalho da disciplina de Banco de Dados. O objetivo foi migrar uma aplicação Java baseada em Banco de Dados Relacional (PostgreSQL/JDBC) para um Banco de Dados NoSQL (MongoDB), mantendo as funcionalidades de CRUD, regras de negócio e relatórios.

📋 Sobre o Projeto

O sistema permite o gerenciamento completo de uma clínica veterinária, incluindo:

CRUDs: Clientes, Animais, Funcionários, Serviços, Espécies e Agendamentos.

Processos de Negócio: Registro de Atendimentos (vinculando Agendamento e Funcionário).

Relatórios:

Animais atendidos por funcionário.

Agendamentos por período.

Animais por cliente.

Tecnologias Utilizadas:

Linguagem: Java 17+

Banco de Dados: MongoDB

Gerenciamento de Dependências: Maven

Driver: MongoDB Java Driver Sync

🚀 Pré-requisitos

Para executar este projeto, você precisará ter instalado em sua máquina:

Java JDK 17 ou superior.

Apache Maven (para gerenciar as dependências e compilar).

MongoDB rodando na porta padrão (27017).

Pode ser instalado localmente (MongoDB Community Server).

Ou via Docker (docker run -d -p 27017:27017 --name mongo-clinica mongo).

MongoDB Shell (mongosh) ou MongoDB Compass (para rodar o script de população inicial).

📦 Configuração do Banco de Dados (Passo a Passo)

Antes de iniciar a aplicação Java, é necessário criar o banco de dados clinica, as coleções e popular com os dados iniciais. Também é necessário configurar os contadores para simular o auto-incremento dos IDs.

O arquivo necessário para isso é o scripts_banco.js (localizado na raiz do projeto).

Opção 1: Via Terminal (mongosh)

Se você tiver o mongosh instalado e configurado no path:

Abra o terminal na pasta raiz do projeto.

Execute o comando:

mongosh < scripts_banco.js


Você verá a mensagem "Banco de dados 'clinica' populado com sucesso e contadores sincronizados!" ao final.

Opção 2: Via MongoDB Compass (Interface Gráfica)

Abra o MongoDB Compass e conecte-se ao seu banco local (mongodb://localhost:27017).

Na parte inferior da janela, clique em MONGOSH para abrir o terminal integrado.

Copie todo o conteúdo do arquivo scripts_banco.js.

Cole no terminal do Compass e aperte Enter.

🛠️ Como Compilar e Executar

O projeto utiliza Maven. Siga os passos abaixo para rodar a aplicação.

Via Terminal (Recomendado)

Abra o terminal na pasta raiz do projeto (onde está o arquivo pom.xml).

Compile o projeto e baixe as dependências:

mvn clean package


Execute a aplicação:

mvn exec:java -Dexec.mainClass="clinica.Clinica"


Via IDE (NetBeans, IntelliJ, Eclipse)

Abra sua IDE e selecione "Open Project".

Navegue até a pasta do projeto (que contém o pom.xml) e abra. A IDE deve reconhecer automaticamente como um projeto Maven.

Aguarde a IDE baixar as dependências do MongoDB.

Localize a classe principal: src/main/java/clinica/Clinica.java.

Clique com o botão direito e selecione "Run File" (Executar Arquivo).

📂 Estrutura do Projeto

A migração para NoSQL exigiu adaptações na camada de persistência, mantendo os Beans originais:

src/main/java/clinica/

Beans: (ClienteBean, AnimalBean, etc.) - Representam os objetos em memória. Mantidos idênticos à Fase 1.

Models: (ClienteModel, AnimalModel, etc.) - Responsáveis pela comunicação com o MongoDB.

Mudança: Métodos SQL (INSERT, SELECT) substituídos por métodos Mongo (insertOne, find).

Mudança: JOINs agora são feitos via código (Application-side joins).

Controllers: Recebem input do usuário e chamam os Models. Adaptados para receber MongoDatabase em vez de Connection.

Conexao.java: Gerencia a conexão com o MongoDB via MongoClient.

SequenceManager.java: (Novo) Classe utilitária para simular o AUTO_INCREMENT (sequencial) usando uma coleção auxiliar chamada counters.

Clinica.java: Classe principal com os menus do sistema.

📝 Observações sobre a Migração NoSQL

Para atender aos requisitos mantendo a lógica da aplicação Java original:

IDs Sequenciais: O MongoDB usa ObjectId por padrão, mas para compatibilidade com os int id das classes Java existentes, implementamos uma lógica de sequencia manual na coleção counters.

Relacionamentos: Como o MongoDB não possui chaves estrangeiras estritas (FK), a integridade é garantida pela aplicação. As buscas que antes usavam INNER JOIN no SQL agora realizam buscas adicionais nas coleções referenciadas para montar os objetos completos.

✒️ Autores

Lize Ana Zabote
Hellen Figueiredo Ramos

Projeto desenvolvido para a disciplina de Banco de Dados II.
