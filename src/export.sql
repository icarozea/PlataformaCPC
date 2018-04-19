
--------------------------------------------------------
--  DDL for Table CITA
--------------------------------------------------------

  CREATE TABLE CITA 
   (	ID_CITA NUMBER(8,0), 
	SALON VARCHAR2(50 BYTE) DEFAULT 0, 
	FECHA_SOLICITUD TIMESTAMP  DEFAULT TO_TIMESTAMP('01/01/00 12:00:00', 'DD/MM/YY HH24:MI:SS'), 
	FECHA_CITA TIMESTAMP  DEFAULT TO_TIMESTAMP('01/01/00 12:00:00' ,'DD/MM/YY HH24:MI:SS'), 
	ID_PRACTICANTE NUMBER(8,0) DEFAULT 0, 
	ID_PACIENTE NUMBER(8,0) DEFAULT 0, 
	ESTADO VARCHAR2(100 BYTE) DEFAULT 'creada', 
	ID_TRATAMIENTO NUMBER(8,0) DEFAULT 0, 
	ES_VALORACION NUMBER DEFAULT 0, 
	NUM_CITA NUMBER DEFAULT 0
   );
--------------------------------------------------------
--  DDL for Table COMENTARIOS_REPORTE
--------------------------------------------------------

  CREATE TABLE COMENTARIOS_REPORTE 
   (	ID_COMENTARIOS NUMBER, 
	COM_OBJETIVO CLOB, 
	COM_DESCRIPCION CLOB, 
	COM_TAREAS CLOB, 
	COM_ACTIVIDADES CLOB
   );
--------------------------------------------------------
--  DDL for Table CONSECUTIVO_HISTORIA
--------------------------------------------------------

  CREATE TABLE CONSECUTIVO_HISTORIA 
   (	ANO NUMBER DEFAULT 2017, 
	CONSECUTIVO NUMBER DEFAULT 1, 
	SEMESTRE VARCHAR2(20 BYTE) DEFAULT 'I'
   );
--------------------------------------------------------
--  DDL for Table CUPOS
--------------------------------------------------------

  CREATE TABLE CUPOS 
   (	NUMERO NUMBER DEFAULT 7
   );
--------------------------------------------------------
--  DDL for Table DETALLE_PERSONA
--------------------------------------------------------

  CREATE TABLE DETALLE_PERSONA 
   (	ID_PERSONA NUMBER(8,0), 
	SEXO VARCHAR2(20 BYTE), 
	EDAD NUMBER, 
	ACUDIENTE VARCHAR2(200 BYTE), 
	PROCESO VARCHAR2(200 BYTE), 
	PERTENECE_U VARCHAR2(20 BYTE), 
	FACULTAD VARCHAR2(200 BYTE), 
	SEMESTRE VARCHAR2(50 BYTE), 
	PROBLEMATICA VARCHAR2(4000 BYTE), 
	OBSERVACIONES VARCHAR2(4000 BYTE), 
	PERSONA_MODIFICA_DATOS VARCHAR2(200 BYTE), 
	ESTADO_CIVIL VARCHAR2(20 BYTE), 
	FECHA_NACIMIENTO VARCHAR2(20 BYTE), 
	LUGAR_NACIMIENTO NUMBER DEFAULT 0, 
	ESCOLARIDAD VARCHAR2(20 BYTE), 
	OCUPACION VARCHAR2(200 BYTE), 
	LOCALIDAD NUMBER DEFAULT 0, 
	BARRIO VARCHAR2(200 BYTE), 
	ESTRATO NUMBER, 
	PERSONA_EMERGENCIA VARCHAR2(200 BYTE), 
	TELEFONO_EMERGENCIA VARCHAR2(200 BYTE), 
	PARENTESCO_EMERGENCIA VARCHAR2(200 BYTE), 
	FORMATO_SOLICITUD VARCHAR2(20 BYTE), 
	INSTITUCION_REMISION VARCHAR2(200 BYTE), 
	PARENTESCO_ACUDIENTE VARCHAR2(200 BYTE), 
	TELEFONO_ACUDIENTE VARCHAR2(200 BYTE), 
	PERSONAS_RESIDE VARCHAR2(4000 BYTE)
   );
--------------------------------------------------------
--  DDL for Table EPS
--------------------------------------------------------

  CREATE TABLE EPS 
   (	ID_EPS NUMBER(8,0) DEFAULT 0, 
	NOMBRE_EPS VARCHAR2(200 BYTE) DEFAULT ''
   );
--------------------------------------------------------
--  DDL for Table HISTORIA_CLINICA
--------------------------------------------------------

  CREATE TABLE HISTORIA_CLINICA 
   (	ID_HISTORIA NUMBER, 
	ID_PACIENTE NUMBER, 
	CODIGO VARCHAR2(200 BYTE)
   );
--------------------------------------------------------
--  DDL for Table LOCALIDAD
--------------------------------------------------------

  CREATE TABLE LOCALIDAD 
   (	CODIGO NUMBER, 
	NOMBRE VARCHAR2(200 BYTE)
   );
--------------------------------------------------------
--  DDL for Table MUNICIPIO
--------------------------------------------------------

  CREATE TABLE MUNICIPIO 
   (	CODIGO NUMBER, 
	NOMBRE VARCHAR2(200 BYTE)
   );
--------------------------------------------------------
--  DDL for Table PERFIL
--------------------------------------------------------

  CREATE TABLE PERFIL 
   (	ID_PERFIL NUMBER(8,0) DEFAULT 0, 
	NOMBRE_PERFIL VARCHAR2(100 BYTE) DEFAULT '', 
	PERMISO VARCHAR2(100 BYTE) DEFAULT ''
   );
--------------------------------------------------------
--  DDL for Table PERSONA
--------------------------------------------------------

  CREATE TABLE PERSONA 
   (	ID_PERSONA NUMBER(8,0) DEFAULT 0, 
	PRIMER_NOMBRE VARCHAR2(4000 BYTE) DEFAULT '', 
	SEGUNDO_NOMBRE VARCHAR2(4000 BYTE) DEFAULT '', 
	PRIMER_APELLIDO VARCHAR2(4000 BYTE) DEFAULT '', 
	SEGUNDO_APELLIDO VARCHAR2(4000 BYTE) DEFAULT '', 
	NUMERO_DOCUMENTO VARCHAR2(4000 BYTE) DEFAULT '', 
	DIRECCION VARCHAR2(4000 BYTE) DEFAULT '', 
	TELEFONO NUMBER(12,0) DEFAULT 0, 
	TIPO_DOCUMENTO_ID_DOCUMENTO NUMBER(8,0) DEFAULT 0, 
	EPS_ID_EPS NUMBER(8,0) DEFAULT 0, 
	PERSONA_ID_SUPERIOR NUMBER(8,0) DEFAULT NULL, 
	PERFIL_ID_PERFIL NUMBER(8,0) DEFAULT 0, 
	PERSONA_ID_PERSONA NUMBER(8,0) DEFAULT NULL, 
	USUARIO VARCHAR2(30 BYTE) DEFAULT '', 
	PASS VARCHAR2(4000 BYTE) DEFAULT NULL, 
	CORREO VARCHAR2(4000 BYTE) DEFAULT '', 
	OTRO_TEL NUMBER(12,0) DEFAULT 0, 
	CODIGO NUMBER(12,0) DEFAULT 0, 
	JORNADA VARCHAR2(4000 BYTE) DEFAULT 'dia'
   );
--------------------------------------------------------
--  DDL for Table REPORTE_SESION
--------------------------------------------------------

  CREATE TABLE REPORTE_SESION 
   (	ID_SESION NUMBER DEFAULT 0, 
	FECHA TIMESTAMP  DEFAULT TO_TIMESTAMP('01/01/00 12:00:00' ,'DD/MM/YY HH24:MI:SS'), 
	NOMBRE_PROFESIONAL VARCHAR2(200 BYTE) DEFAULT '', 
	RECIBO NUMBER DEFAULT 0, 
	ES_FALLO NUMBER DEFAULT 0, 
	ID_COMENTARIOS NUMBER DEFAULT 0, 
	ID_CITA NUMBER, 
	OBJETIVO_SESION CLOB, 
	DESCRIPCION_SESION CLOB, 
	TAREAS_ASIGNADAS CLOB, 
	ACTIVIDADES_PROX_SESION CLOB
   );
--------------------------------------------------------
--  DDL for Table REPORTE_VALORACION
--------------------------------------------------------

  CREATE TABLE REPORTE_VALORACION 
   (	ID_VALORACION NUMBER, 
	ID_CITA NUMBER, 
	REPORTA VARCHAR2(200 BYTE), 
	SERVICIO_REMITIDO VARCHAR2(4000 BYTE), 
	ENCUESTADOR VARCHAR2(4000 BYTE), 
	MOTIVO CLOB, 
	COMPORTAMIENTO CLOB, 
	HIPOTESIS CLOB
   );
--------------------------------------------------------
--  DDL for Table TIPO_DOCUMENTO
--------------------------------------------------------

  CREATE TABLE TIPO_DOCUMENTO 
   (	ID_DOCUMENTO NUMBER(8,0) DEFAULT 0, 
	SIGLA VARCHAR2(5 BYTE) DEFAULT 'CC', 
	NOMBRE_DOCUMENTO VARCHAR2(100 BYTE) DEFAULT ''
   );
--------------------------------------------------------
--  DDL for Table TRATAMIENTO
--------------------------------------------------------

  CREATE TABLE TRATAMIENTO 
   (	ID_TRATAMIENTO NUMBER(8,0) DEFAULT 0, 
	ID_PACIENTE NUMBER(8,0) DEFAULT 0, 
	ESTADO VARCHAR2(20 BYTE) DEFAULT 'valoracion', 
	FECHA_INICIO TIMESTAMP  DEFAULT TO_TIMESTAMP('01/01/00 12:00:00' ,'DD/MM/YY HH24:MI:SS'), 
	FECHA_CIERRE TIMESTAMP  DEFAULT TO_TIMESTAMP('01/01/00 12:00:00' ,'DD/MM/YY HH24:MI:SS'), 
	TIPO VARCHAR2(100 BYTE) DEFAULT 'individual', 
	NUM_CITA_ACTUAL NUMBER DEFAULT 0, 
	PENDIENTE NUMBER DEFAULT 0,
	DIAGNOSTICO VARCHAR2(200 BYTE) DEFAULT ''
   );
   
--------------------------------------------------------
--  DDL for Table COSTO
--------------------------------------------------------

  CREATE TABLE COSTO 
   (	PRECIO NUMBER DEFAULT 2000
   );
   
--------------------------------------------------------
--  DDL for Sequence CITA_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  CITA_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 ORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence COMENTARIOS_REPORTE_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  COMENTARIOS_REPORTE_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 ORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence EPS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  EPS_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 ORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence HISTORIA_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  HISTORIA_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 ORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence PERSONA_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  PERSONA_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 ORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence REPORTE_CITA_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  REPORTE_CITA_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 ORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence REPORTE_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  REPORTE_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 ORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence REPORTE_SESION_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  REPORTE_SESION_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 ORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence TIPO_DOCUMENTO_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  TIPO_DOCUMENTO_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 5 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence TRATAMIENTO_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  TRATAMIENTO_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 ORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence USUARIO_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  USUARIO_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 ORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence VALORACION_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  VALORACION_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 ORDER  NOCYCLE ;
   

REM INSERTING into COMENTARIOS_REPORTE
SET DEFINE OFF;
Insert into COMENTARIOS_REPORTE (ID_COMENTARIOS) values ('0');
REM INSERTING into CONSECUTIVO_HISTORIA
SET DEFINE OFF;
Insert into CONSECUTIVO_HISTORIA (ANO,CONSECUTIVO,SEMESTRE) values ('2017','1','II');
REM INSERTING into CUPOS
SET DEFINE OFF;
Insert into CUPOS (NUMERO) values ('4');
REM INSERTING into DETALLE_PERSONA
SET DEFINE OFF;
REM INSERTING into EPS
SET DEFINE OFF;
Insert into EPS (ID_EPS,NOMBRE_EPS) values ('0','No Aplica');
REM INSERTING into HISTORIA_CLINICA
SET DEFINE OFF;
REM INSERTING into LOCALIDAD
SET DEFINE OFF;
Insert into LOCALIDAD (CODIGO,NOMBRE) values ('0','Indefinido');
REM INSERTING into MUNICIPIO
SET DEFINE OFF;
Insert into MUNICIPIO (CODIGO,NOMBRE) values ('0','Indefinido');
REM INSERTING into PERFIL
SET DEFINE OFF;
Insert into PERFIL (ID_PERFIL,NOMBRE_PERFIL,PERMISO) values ('1','Administrador',null);
Insert into PERFIL (ID_PERFIL,NOMBRE_PERFIL,PERMISO) values ('2','Supervisor',null);
Insert into PERFIL (ID_PERFIL,NOMBRE_PERFIL,PERMISO) values ('3','Practicante',null);
Insert into PERFIL (ID_PERFIL,NOMBRE_PERFIL,PERMISO) values ('4','Paciente',null);
REM INSERTING into PERSONA
SET DEFINE OFF;
Insert into PERSONA (ID_PERSONA,PRIMER_NOMBRE,SEGUNDO_NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,NUMERO_DOCUMENTO,DIRECCION,TELEFONO,TIPO_DOCUMENTO_ID_DOCUMENTO,EPS_ID_EPS,PERSONA_ID_SUPERIOR,PERFIL_ID_PERFIL,PERSONA_ID_PERSONA,USUARIO,PASS,CORREO,OTRO_TEL,CODIGO,JORNADA) values ('0','Admin',null,'Seguro','Seguro','9876543210',null,'0','1','0',null,'1',null,null,'oNJ4PYQCY++kIwYlHBSvN+uPx0VFLcaX79UCzSHxrRz86UcOuXsv0wmhyS0Di/pS','a@b.com','0','0','manana');

REM INSERTING into TIPO_DOCUMENTO
SET DEFINE OFF;
Insert into TIPO_DOCUMENTO (ID_DOCUMENTO,SIGLA,NOMBRE_DOCUMENTO) values ('1','CC','Cedula de Ciudadania');
Insert into TIPO_DOCUMENTO (ID_DOCUMENTO,SIGLA,NOMBRE_DOCUMENTO) values ('2','TI','Tarjeta de Identidad');
Insert into TIPO_DOCUMENTO (ID_DOCUMENTO,SIGLA,NOMBRE_DOCUMENTO) values ('3','CE','Cedula de Extranjeria');
Insert into TIPO_DOCUMENTO (ID_DOCUMENTO,SIGLA,NOMBRE_DOCUMENTO) values ('4','RC','Registro Civill');
REM INSERTING into TRATAMIENTO
SET DEFINE OFF;
REM INSERTING into COSTO
SET DEFINE OFF;
Insert into COSTO (PRECIO) values ('2000');
--------------------------------------------------------
--  DDL for Index REPORTE_VALORACION_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX REPORTE_VALORACION_PK ON REPORTE_VALORACION (ID_VALORACION);
--------------------------------------------------------
--  DDL for Index PERSONA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX PERSONA_PK ON PERSONA (ID_PERSONA);
--------------------------------------------------------
--  DDL for Index CITA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX CITA_PK ON CITA (ID_CITA);
--------------------------------------------------------
--  DDL for Index TIPO_DOCUMENTO_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX TIPO_DOCUMENTO_PK ON TIPO_DOCUMENTO (ID_DOCUMENTO);
--------------------------------------------------------
--  DDL for Index LOCALIDAD_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX LOCALIDAD_PK ON LOCALIDAD (CODIGO);
--------------------------------------------------------
--  DDL for Index HISTORIA_CLINICA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX HISTORIA_CLINICA_PK ON HISTORIA_CLINICA (ID_HISTORIA);
--------------------------------------------------------
--  DDL for Index COMENTARIOS_REPORTE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX COMENTARIOS_REPORTE_PK ON COMENTARIOS_REPORTE (ID_COMENTARIOS);
--------------------------------------------------------
--  DDL for Index SESION_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX SESION_PK ON REPORTE_SESION (ID_SESION);
--------------------------------------------------------
--  DDL for Index CUPOS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX CUPOS_PK ON CUPOS (NUMERO);
--------------------------------------------------------
--  DDL for Index MUNICIPIO_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX MUNICIPIO_PK ON MUNICIPIO (CODIGO);
--------------------------------------------------------
--  DDL for Index PERFIL_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX PERFIL_PK ON PERFIL (ID_PERFIL);
--------------------------------------------------------
--  DDL for Index TRATAMIENTO_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX TRATAMIENTO_PK ON TRATAMIENTO (ID_TRATAMIENTO);
--------------------------------------------------------
--  DDL for Index EPS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX EPS_PK ON EPS (ID_EPS);

--------------------------------------------------------
--  Constraints for Table LOCALIDAD
--------------------------------------------------------

  ALTER TABLE LOCALIDAD MODIFY (CODIGO NOT NULL ENABLE);
  ALTER TABLE LOCALIDAD ADD CONSTRAINT LOCALIDAD_PK PRIMARY KEY (CODIGO);
--------------------------------------------------------
--  Constraints for Table TIPO_DOCUMENTO
--------------------------------------------------------

  ALTER TABLE TIPO_DOCUMENTO ADD CONSTRAINT TIPO_DOCUMENTO_PK PRIMARY KEY (ID_DOCUMENTO);
  ALTER TABLE TIPO_DOCUMENTO MODIFY (ID_DOCUMENTO NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table PERFIL
--------------------------------------------------------

  ALTER TABLE PERFIL ADD CONSTRAINT PERFIL_PK PRIMARY KEY (ID_PERFIL);
  ALTER TABLE PERFIL MODIFY (ID_PERFIL NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table PERSONA
--------------------------------------------------------

  ALTER TABLE PERSONA ADD CONSTRAINT PERSONA_PK PRIMARY KEY (ID_PERSONA);
  ALTER TABLE PERSONA MODIFY (ID_PERSONA NOT NULL ENABLE);
  ALTER TABLE PERSONA MODIFY (PERFIL_ID_PERFIL NOT NULL ENABLE);
  ALTER TABLE PERSONA MODIFY (PASS NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table REPORTE_SESION
--------------------------------------------------------

  ALTER TABLE REPORTE_SESION ADD CONSTRAINT SESION_PK PRIMARY KEY (ID_SESION);
  ALTER TABLE REPORTE_SESION MODIFY (ID_SESION NOT NULL ENABLE);
  ALTER TABLE REPORTE_SESION MODIFY (ID_CITA NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table EPS
--------------------------------------------------------

  ALTER TABLE EPS ADD CONSTRAINT EPS_PK PRIMARY KEY (ID_EPS);
  ALTER TABLE EPS MODIFY (ID_EPS NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table COMENTARIOS_REPORTE
--------------------------------------------------------

  ALTER TABLE COMENTARIOS_REPORTE ADD CONSTRAINT COMENTARIOS_REPORTE_PK PRIMARY KEY (ID_COMENTARIOS);
  ALTER TABLE COMENTARIOS_REPORTE MODIFY (ID_COMENTARIOS NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table MUNICIPIO
--------------------------------------------------------

  ALTER TABLE MUNICIPIO MODIFY (CODIGO NOT NULL ENABLE);
  ALTER TABLE MUNICIPIO ADD CONSTRAINT MUNICIPIO_PK PRIMARY KEY (CODIGO);
--------------------------------------------------------
--  Constraints for Table TRATAMIENTO
--------------------------------------------------------

  ALTER TABLE TRATAMIENTO MODIFY (ID_TRATAMIENTO NOT NULL ENABLE);
  ALTER TABLE TRATAMIENTO ADD CONSTRAINT TRATAMIENTO_PK PRIMARY KEY (ID_TRATAMIENTO);
  ALTER TABLE TRATAMIENTO MODIFY (ID_PACIENTE NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table DETALLE_PERSONA
--------------------------------------------------------

  ALTER TABLE DETALLE_PERSONA MODIFY (ID_PERSONA NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table REPORTE_VALORACION
--------------------------------------------------------

  ALTER TABLE REPORTE_VALORACION MODIFY (ID_VALORACION NOT NULL ENABLE);
  ALTER TABLE REPORTE_VALORACION ADD CONSTRAINT REPORTE_VALORACION_PK PRIMARY KEY (ID_VALORACION);
  ALTER TABLE REPORTE_VALORACION MODIFY (ID_CITA NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table CITA
--------------------------------------------------------

  ALTER TABLE CITA ADD CONSTRAINT CITA_PK PRIMARY KEY (ID_CITA);
  ALTER TABLE CITA MODIFY (ID_CITA NOT NULL ENABLE);
  ALTER TABLE CITA MODIFY (ID_PRACTICANTE NOT NULL ENABLE);
  ALTER TABLE CITA MODIFY (ID_PACIENTE NOT NULL ENABLE);
  ALTER TABLE CITA MODIFY (ID_TRATAMIENTO NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HISTORIA_CLINICA
--------------------------------------------------------

  ALTER TABLE HISTORIA_CLINICA ADD CONSTRAINT HISTORIA_CLINICA_PK PRIMARY KEY (ID_HISTORIA);
  ALTER TABLE HISTORIA_CLINICA MODIFY (ID_HISTORIA NOT NULL ENABLE);
  ALTER TABLE HISTORIA_CLINICA MODIFY (ID_PACIENTE NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table CITA
--------------------------------------------------------

  ALTER TABLE CITA ADD CONSTRAINT FK_PACIENTE_CITA FOREIGN KEY (ID_PACIENTE)
	  REFERENCES PERSONA (ID_PERSONA) ENABLE;
  ALTER TABLE CITA ADD CONSTRAINT FK_PRACTICANTE FOREIGN KEY (ID_PRACTICANTE)
	  REFERENCES PERSONA (ID_PERSONA) ENABLE;
  ALTER TABLE CITA ADD CONSTRAINT FK_TRATAMIENTO FOREIGN KEY (ID_TRATAMIENTO)
	  REFERENCES TRATAMIENTO (ID_TRATAMIENTO) ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table DETALLE_PERSONA
--------------------------------------------------------

  ALTER TABLE DETALLE_PERSONA ADD CONSTRAINT DETALLE_PERSONA_FK1 FOREIGN KEY (ID_PERSONA)
	  REFERENCES PERSONA (ID_PERSONA) ENABLE;
  ALTER TABLE DETALLE_PERSONA ADD CONSTRAINT DETALLE_PERSONA_FK2 FOREIGN KEY (LUGAR_NACIMIENTO)
	  REFERENCES MUNICIPIO (CODIGO) ENABLE;
  ALTER TABLE DETALLE_PERSONA ADD CONSTRAINT DETALLE_PERSONA_FK3 FOREIGN KEY (LOCALIDAD)
	  REFERENCES LOCALIDAD (CODIGO) ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table HISTORIA_CLINICA
--------------------------------------------------------

  ALTER TABLE HISTORIA_CLINICA ADD CONSTRAINT HISTORIA_CLINICA_FK1 FOREIGN KEY (ID_PACIENTE)
	  REFERENCES PERSONA (ID_PERSONA) ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table PERSONA
--------------------------------------------------------

  ALTER TABLE PERSONA ADD CONSTRAINT FK_DOCUMENTO FOREIGN KEY (TIPO_DOCUMENTO_ID_DOCUMENTO)
	  REFERENCES TIPO_DOCUMENTO (ID_DOCUMENTO) ENABLE;
  ALTER TABLE PERSONA ADD CONSTRAINT FK_EPS FOREIGN KEY (EPS_ID_EPS)
	  REFERENCES EPS (ID_EPS) ENABLE;
  ALTER TABLE PERSONA ADD CONSTRAINT FK_PERFIL FOREIGN KEY (PERFIL_ID_PERFIL)
	  REFERENCES PERFIL (ID_PERFIL) ENABLE;
  ALTER TABLE PERSONA ADD CONSTRAINT FK_SUPERIOR FOREIGN KEY (PERSONA_ID_SUPERIOR)
	  REFERENCES PERSONA (ID_PERSONA) ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table REPORTE_SESION
--------------------------------------------------------

  ALTER TABLE REPORTE_SESION ADD CONSTRAINT REPORTE_SESION_FK1 FOREIGN KEY (ID_COMENTARIOS)
	  REFERENCES COMENTARIOS_REPORTE (ID_COMENTARIOS) ENABLE;
  ALTER TABLE REPORTE_SESION ADD CONSTRAINT REPORTE_SESION_FK2 FOREIGN KEY (ID_CITA)
	  REFERENCES CITA (ID_CITA) ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table REPORTE_VALORACION
--------------------------------------------------------

  ALTER TABLE REPORTE_VALORACION ADD CONSTRAINT REPORTE_VALORACION_FK1 FOREIGN KEY (ID_CITA)
	  REFERENCES CITA (ID_CITA) ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table TRATAMIENTO
--------------------------------------------------------

  ALTER TABLE TRATAMIENTO ADD CONSTRAINT FK_PACIENTE_TRATAMIENTO FOREIGN KEY (ID_PACIENTE)
	  REFERENCES PERSONA (ID_PERSONA) ENABLE;
