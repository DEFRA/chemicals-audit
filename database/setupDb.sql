create database [reach-audit] COLLATE Latin1_General_100_CI_AI_SC
go
exec sp_configure 'contained database authentication', 1
go
create database [reach-audit-unit-test] COLLATE Latin1_General_100_CI_AI_SC
go
reconfigure
go
alter database [reach-audit] set containment = partial
go
alter database [reach-audit-unit-test] set containment = partial
go
