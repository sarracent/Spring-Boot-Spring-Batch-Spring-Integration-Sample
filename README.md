# Spring-Boot-Spring-Batch-Spring-Integration-Sample
Sample project for Spring Batch Integration module using Spring Boot and Java Config

# Goal
Build a processing flow using the IntegrationFlow configuration builder and general Java Config instead of xml bean configuration to activate batch jobs.

# Spring Batch + Spring Integration + HttpClient + GSON
This sample uses Spring Batch Integration and integration flow to more easily use Spring Batch and Spring Integration together.Showcases transfer of files from one remote SFTP server and request batch jobs to process those files. With ItemProcessor we send http get request consuming an EndPoint from a microservice and with ItemWriter we send http post request to update a database table in an API.

# Usage
Just run it, spring boot maven plugin:

$ mvn spring-boot:run

The below properties need to modified in application.properties

services.topics.url=http://localhost:8000/topic-service/topics/

services.api.url=http://localhost:8443/agency-contact/

Remote host SFTP server properties

sftp.host.ip = 192.168.10.74

sftp.host.port = 22 

sftp.host.user = user

sftp.host.password = password

sftp.host.remote.directory.download = /

sftp.local.directory.download = sftp-inbound

sftp.host.remote.directory.download.filter = .
