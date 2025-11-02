# Sobre o Projeto

A Frota Viva é um projeto que ajuda frotas de caminhões a terem maior controle sobre seus veículos, e caminhoneiros a gerirem melhor seu caminhão. O aplicativo, conectado ao veículo, fornece dados técnicos de forma simples e mostra alertas automáticos sobre falhas e manutenções. A empresa também gerencia motoristas, entregas, custos e manutenções de modo mais eficiente.

### Principais Funcionalidades

- **Digital Twin de Veículos** - Representação digital completa dos caminhões
- **Histórico de Modificações** - Registro detalhado de todas as alterações realizadas
- **Controle de Acidentes** - Documentação e rastreamento de ocorrências
- **Gestão de Upgrades** - Acompanhamento de melhorias e atualizações
- **Rastreamento de Peças** - Controle de peças trocadas e manutenções
- **API Documentada** - Swagger/OpenAPI para fácil integração
- **Arquitetura Escalável** - Baseada em MongoDB para alta performance

## Tecnologias Utilizadas

### Backend
- **Java 21** - Linguagem principal
- **Spring Boot 3.5.6** - Framework web
- **Spring Data MongoDB** - Persistência de dados NoSQL
- **Lombok** - Redução de código boilerplate

### Banco de Dados
- **MongoDB Atlas** - Banco de dados NoSQL em nuvem

### Documentação
- **SpringDoc OpenAPI** - Documentação automática da API
- **Swagger UI** - Interface interativa para testes

### DevOps
- **Docker** - Containerização
- **Maven** - Gerenciamento de dependências

## Pré-requisitos

Antes de começar, você precisa ter instalado:

- **Java 21** ou superior
- **Maven 3.6+**
- **Docker** (opcional, para execução com containers)
- **MongoDB** (local) ou acesso ao MongoDB Atlas

## Como Executar

### Opção 1: Execução Local

1. **Clone o repositório**
```bash
git clone <url-do-repositorio>
cd API_MongoDB
```

2. **Configure as variáveis de ambiente**
Crie um arquivo `.env` na raiz do projeto:
```env
MONGO_DB=seu_banco_de_dados
MONGO_URI=mongodb://localhost:27017/api_mongodb
# ou para MongoDB Atlas:
# MONGO_URI=mongodb+srv://usuario:senha@cluster.mongodb.net/api_mongodb?retryWrites=true&w=majority
```

3. **Execute a aplicação**
```bash
./mvnw spring-boot:run
```

### Opção 2: Docker

1. **Build da imagem**
```bash
docker build -t api-mongodb .
```

2. **Execute o container**
```bash
docker run -p 8080:8080 --env-file .env api-mongodb
```

A API estará disponível em: `http://localhost:8080`

## Documentação da API

Após executar a aplicação, acesse a documentação interativa:

- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8080/v3/api-docs`

## Principais Endpoints

### Gestão de Veículos Digital Twin
- `GET /api/veiculos/{id_caminhao}/historico` - Buscar histórico completo do veículo
- `POST /api/veiculos` - Criar novo veículo digital twin

### Histórico de Modificações
- `POST /api/veiculos/{id_caminhao}/historico-modificacao` - Adicionar modificação

### Histórico de Acidentes
- `POST /api/veiculos/{id_caminhao}/historico-acidente` - Registrar acidente

### Upgrades Realizados
- `POST /api/veiculos/{id_caminhao}/upgrade-realizado` - Registrar upgrade

### Exemplo de Uso

**Criar um novo veículo:**
```bash
curl -X POST http://localhost:8080/api/veiculos \
  -H "Content-Type: application/json" \
  -d '{
    "idCaminhao": 1001,
    "placa": "ABC-1234",
    "modelo": "Volvo FH 540",
    "historicoModificacoes": [],
    "historicoAcidentes": [],
    "upgradesRealizados": []
  }'
```

**Buscar histórico do veículo:**
```bash
curl -X GET http://localhost:8080/api/veiculos/1001/historico
```

## Estrutura do Projeto

```
src/main/java/com/example/api_mongodb/
├── controller/          # Controladores REST
├── dto/                # Data Transfer Objects
├── mapper/             # Mapeadores de entidades
├── model/              # Modelos de dados
├── repository/         # Repositórios MongoDB
└── service/            # Lógica de negócio
```

## Modelos de Dados

### VeiculoDigitalTwin
- `id` - Identificador único
- `idCaminhao` - ID do caminhão físico
- `placa` - Placa do veículo
- `modelo` - Modelo do caminhão
- `historicoModificacoes` - Lista de modificações
- `historicoAcidentes` - Lista de acidentes
- `upgradesRealizados` - Lista de upgrades

### HistoricoModificacao
- Registro de modificações realizadas no veículo
- Inclui peças trocadas e detalhes da modificação

### HistoricoAcidente
- Documentação de acidentes ocorridos
- Informações sobre danos e reparos

### UpgradeRealizado
- Registro de melhorias implementadas
- Atualizações de sistema e componentes

## Configuração do MongoDB

### MongoDB Local
```bash
# Instalar MongoDB
# Criar banco de dados
use api_mongodb
```

### MongoDB Atlas
1. **Crie uma conta no MongoDB Atlas**
2. **Configure um cluster**
3. **Obtenha a string de conexão**
4. **Configure no arquivo .env**


## Build para Produção

```bash
./mvnw clean package -DskipTests
```

O arquivo JAR será gerado em `target/API_MongoDB-0.0.1-SNAPSHOT.jar`
