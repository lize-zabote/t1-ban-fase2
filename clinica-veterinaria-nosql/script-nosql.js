// Script para rodar no MongoDB Shell (mongosh) ou MongoDB Compass
// Cria a base de dados 'clinica', popula com dados iniciais e reseta contadores

db = db.getSiblingDB('clinica');

// 1. Limpeza: Remove as coleções antigas para começar do zero
db.cliente.drop();
db.animal.drop();
db.funcionario.drop();
db.especie.drop();
db.servico.drop();
db.agendamento.drop();
db.atendimento.drop();
db.counters.drop();

// 2. Inserção de Dados Iniciais (Baseado no dbclinica.txt)

// --- Espécies ---
db.especie.insertMany([
    { id: 1, descricao: "Cachorro" },
    { id: 2, descricao: "Gato" },
    { id: 3, descricao: "Pássaro" },
    { id: 4, descricao: "Hamster" }
]);

// --- Serviços ---
// Nota: Valores numéricos decimais convertidos para Double
db.servico.insertMany([
    { id: 1, valor: 80.00, descricao: "Banho e Tosa Completa" },
    { id: 2, valor: 50.00, descricao: "Banho Simples" },
    { id: 3, valor: 150.00, descricao: "Consulta Veterinária" },
    { id: 4, valor: 90.00, descricao: "Aplicação de Vacina V10" },
    { id: 5, valor: 40.00, descricao: "Corte de Unhas" }
]);

// --- Clientes ---
db.cliente.insertMany([
    { id: 1, nome: "Ana", sobrenome: "Silva", telefone: "(47) 99988-7766", cep: "89220-100", bairro: "Costa e Silva", rua: "Rua das Palmeiras, 123" },
    { id: 2, nome: "Bruno", sobrenome: "Costa", telefone: "(47) 98877-6655", cep: "89204-250", bairro: "América", rua: "Rua XV de Novembro, 456" },
    { id: 3, nome: "Carla", sobrenome: "Oliveira", telefone: "(47) 97766-5544", cep: "89211-500", bairro: "Santo Antônio", rua: "Rua Marquês de Olinda, 789" },
    { id: 4, nome: "Daniel", sobrenome: "Pereira", telefone: "(47) 96655-4433", cep: "89223-005", bairro: "Aventureiro", rua: "Rua Tuiuti, 101" },
    { id: 5, nome: "Eduarda", sobrenome: "Martins", telefone: "(47) 95544-3322", cep: "89202-300", bairro: "Atiradores", rua: "Rua Eusébio de Queiroz, 212" }
]);

// --- Funcionários ---
db.funcionario.insertMany([
    { id: 1, nome: "Fernanda", sobrenome: "Lima", rua: "Rua dos Girassóis, 321", cep: "89221-500", bairro: "Saguaçu", especialidade: "Tosa Japonesa", cargo: "Tosadora" },
    { id: 2, nome: "Gustavo", sobrenome: "Almeida", rua: "Rua das Orquídeas, 654", cep: "89203-400", bairro: "Anita Garibaldi", especialidade: "Clínica Geral de Pequenos Animais", cargo: "Veterinário" },
    { id: 3, nome: "Heloísa", sobrenome: "Ribeiro", rua: "Avenida Central, 987", cep: "89201-001", bairro: "Centro", especialidade: null, cargo: "Recepcionista" },
    { id: 4, nome: "Igor", sobrenome: "Santos", rua: "Rua Blumenau, 741", cep: "89204-251", bairro: "América", especialidade: "Tosa na tesoura", cargo: "Tosador" }
]);

// --- Animais ---
// Datas em formato ISODate
db.animal.insertMany([
    { id: 1, nome: "Thor", dataNascimento: new Date("2022-05-10"), idEspecie: 1, idCliente: 1 },
    { id: 2, nome: "Mia", dataNascimento: new Date("2021-11-20"), idEspecie: 2, idCliente: 1 },
    { id: 3, nome: "Loki", dataNascimento: new Date("2023-01-15"), idEspecie: 1, idCliente: 2 },
    { id: 4, nome: "Zazu", dataNascimento: new Date("2024-03-30"), idEspecie: 3, idCliente: 3 },
    { id: 5, nome: "Bolinha", dataNascimento: new Date("2023-08-01"), idEspecie: 4, idCliente: 4 },
    { id: 6, nome: "Nina", dataNascimento: new Date("2020-07-25"), idEspecie: 1, idCliente: 5 },
    { id: 7, nome: "Simba", dataNascimento: new Date("2022-09-12"), idEspecie: 2, idCliente: 5 }
]);

// --- Agendamentos ---
// Horários armazenados como String ("HH:MM:SS") conforme definido na Modelagem Java
db.agendamento.insertMany([
    { id: 1, data: new Date("2025-10-10"), hora: "10:00:00", idServico: 1, idAnimal: 1 },
    { id: 2, data: new Date("2025-10-11"), hora: "14:30:00", idServico: 3, idAnimal: 2 },
    { id: 3, data: new Date("2025-10-12"), hora: "09:00:00", idServico: 2, idAnimal: 3 },
    { id: 4, data: new Date("2025-10-12"), hora: "11:00:00", idServico: 4, idAnimal: 6 },
    { id: 5, data: new Date("2025-10-15"), hora: "16:00:00", idServico: 5, idAnimal: 7 },
    { id: 6, data: new Date("2025-10-18"), hora: "13:00:00", idServico: 1, idAnimal: 6 }
]);

// --- Atendimentos ---
// Adicionamos um ID artificial (1..6) pois o modelo Java NoSQL espera um ID único para CRUD
db.atendimento.insertMany([
    { id: 1, idAgendamento: 1, idFuncionario: 1 },
    { id: 2, idAgendamento: 2, idFuncionario: 2 },
    { id: 3, idAgendamento: 3, idFuncionario: 4 },
    { id: 4, idAgendamento: 4, idFuncionario: 2 },
    { id: 5, idAgendamento: 5, idFuncionario: 2 },
    { id: 6, idAgendamento: 6, idFuncionario: 4 }
]);

// 3. Configuração dos Contadores (Sequences)
// Define o valor 'seq' com o último ID usado, para que o Java gere o próximo (seq + 1) corretamente.
db.counters.insertMany([
   { _id: "clienteid", seq: 5 },
   { _id: "animalid", seq: 7 },
   { _id: "funcionarioid", seq: 4 },
   { _id: "especieid", seq: 4 },
   { _id: "servicoid", seq: 5 },
   { _id: "agendamentoid", seq: 6 },
   { _id: "atendimentoid", seq: 6 }
]);

print("Banco de dados 'clinica' populado com sucesso e contadores sincronizados!");