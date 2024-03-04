
Documentação da API
Este repositório contém uma API construída utilizando Java 17 e Spring Boot 3.2.3. Siga as instruções abaixo para disponibilizar o serviço e acessar a documentação da API.

Pré-requisitos
Antes de começar, certifique-se de ter os seguintes itens instalados em sua máquina:

Java 17
Maven
Um IDE (como IntelliJ IDEA ou Eclipse)
Passos para disponibilizar o serviço
Clone este repositório em sua máquina local:

git clone https://github.com/AlanAmanco/cadastro-professor.git

Abra o projeto em seu IDE.

Verifique se todas as dependências foram baixadas e o projeto foi compilado com sucesso.

Execute o aplicativo Spring Boot:

Via linha de comando, vá para o diretório raiz do projeto e execute:

mvn spring-boot:run

Ou execute a classe principal ProfessorCadastroApplication.java diretamente do seu IDE.

O serviço estará disponível em http://localhost:8080.

Acessando a documentação da API
A documentação da API é gerada automaticamente pelo Swagger. Para acessá-la, siga estes passos:

Com o serviço em execução, abra um navegador da web.

Navegue até http://localhost:8080/swagger-ui.html.

Você verá a documentação da API, incluindo todos os endpoints disponíveis, seus parâmetros e respostas.

Explore a documentação para entender como usar a API e teste os endpoints conforme necessário.