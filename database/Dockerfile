FROM sndeuxfesacr001.azurecr.io/reach-liquibase-base:1.0

ADD src/main/resources /liquibase/changelog
RUN echo "changeLogFile: liquibase/changelogs/db.changelog-master.xml" >> /liquibase/liquibase.docker.properties
