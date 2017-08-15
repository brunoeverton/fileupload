# Atividade prática: FileUpload
Aplicação web que realiza upload de arquivos em blocos com tamanho máximo de 1Mb, lista os arquivos enviados e realiza o download de um arquivo concluído.

## Configuração inicial
### Banco de dados 
Foi utilizado o banco de dados MySQL e a estrutura está disponível em: db/initialDB.sql
### Aplicação
A configuração da aplicação está disponível em: src/main/resources/application.yml
#### Local temporário para salvar os arquivos
A aplicação salva os arquivos temporariamente em disco, até que o upload seja concluído. Deve ser indicado uma pasta com permissão na propriedade 'file.directory'

## Aplicação
/fileupload/src/main/java/br/com/hotmart/fileupload/App.java

## Para a construção da aplicação foi utilizado:
### SpringBoot
Iniciar e configurar a aplicação
### SpringData
Gerenciamento das entidades em banco
### Swagger
Documentação de API
### DataTables jQuery
Biblioteca para construir tabelas
### toastr
Biblioteca para exibir notificações
### Plupload
Biblioteca para realizar o envio dos arquivos


## Autor
* **Bruno Everton**
