FROM mysql:latest

ENV MYSQL_DATABASE pep1
ENV MYSQL_ROOT_PASSWORD brayito
ENV MYSQL_ROOT_HOST %

EXPOSE 3306

# Copia tu dump SQL en el directorio que se procesará automáticamente al iniciar
COPY ./dumpPep1.sql /docker-entrypoint-initdb.d/

# Este comando es opcional, dependiendo de si necesitas una configuración específica
# RUN algún_comando_para_configurar_o_modificar_el_servidor_mysql