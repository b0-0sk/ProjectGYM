--
-- THIS SCRIPT CREATES THE DATABASE NAMED gym
--

USE master;
GO

IF DB_ID('gym') IS NOT NULL
DROP DATABASE gym;
GO

CREATE DATABASE gym;
GO

USE gym;

CREATE TABLE "Client" (
	"dniClient"	TEXT NOT NULL,
	"user"	TEXT NOT NULL,
	"surname"	TEXT,
	"password"	TEXT NOT NULL,
	"telf"	TEXT NOT NULL,
	"cp"	TEXT NOT NULL,
	"province"	TEXT NOT NULL,
	"email"	TEXT NOT NULL,
	"iban"	TEXT NOT NULL,
	"goodPayer"	TEXT NOT NULL,
	PRIMARY KEY("dniClient")
);

CREATE TABLE "E_S" (
	"movimentsID"	INTEGER NOT NULL,
	"gymID"	TEXT NOT NULL,
	"dniClient"	TEXT NOT NULL,
	"data"	TEXT NOT NULL,
	"e_s"	TEXT NOT NULL,
	PRIMARY KEY("movimentsID","dniClient","gymID")
);

CREATE TABLE "GYMS" (
	"gymID"	TEXT NOT NULL,
	"name"	TEXT NOT NULL,
	"cp"	INTEGER NOT NULL,
	"province"	TEXT NOT NULL,
	PRIMARY KEY("gymID")
);