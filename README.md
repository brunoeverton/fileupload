# Atividade prática: FileUpload
Aplicação web que realiza upload de arquivos em blocos com tamanho máximo de 1Mb, lista os arquivos enviados e realiza o download de um arquivo concluído.

## Configuração inicial
### Banco de dados 
Foi utilizado o banco de dados MySQL e a estrutura está disponível em: db/initialDB.sql
### Aplicação
A configuração da aplicação está disponível em: src/main/resources/application.yml
#### Local temporário para salvar os arquivos
A aplicação salva os arquivos temporariamente em disco, até que o upload seja concluído. Deve ser indicado uma pasta com permissão na propriedade 'file.directory'

## Iniciando pela IDE
A utilização do springboot permite que o projeto seja iniciado como uma aplicação java simples a partir da classe:
  /fileupload/src/main/java/br/com/hotmart/fileupload/App.java

## Acessando a aplicação
A página inicial pode ser acessada em: http://{server}:{port}

## Documentação 
Com a aplicação em execução a documentação pode ser acessada em: http://{server}:{port}/swagger-ui.html

## Para a construção da aplicação foi utilizado:
* [SpringBoot](https://projects.spring.io/spring-boot/) - Iniciar e configurar a aplicação
* [SpringData](http://projects.spring.io/spring-data/) - Gerenciamento das entidades em banco
* [Swagger](https://swagger.io/) - Documentação de API
* [Bootstrap](http://getbootstrap.com/) - Componentes front-end e CSS
* [jQuery](https://jquery.com/) - Biblioteca javascript para manipulação de objetos html
* [Datatable for jQuery](https://www.datatables) - Biblioteca para construir tabelas
* [toastr](https://github.com/CodeSeven/toastr) - Biblioteca para exibir notificações
* [Plupload](http://www.plupload.com/) - Biblioteca para realizar o envio dos arquivos


## Autor
* **Bruno Everton**
