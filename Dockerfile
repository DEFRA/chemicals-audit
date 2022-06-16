FROM defradigital/java:latest-jre

ARG BUILD_VERSION

USER root

RUN mkdir -p /usr/src/reach-audit
WORKDIR /usr/src/reach-audit

COPY ./target/reach-audit-${BUILD_VERSION}.jar /usr/src/reach-audit/reach-audit.jar
COPY ./target/agent/applicationinsights-agent.jar /usr/src/reach-audit/applicationinsights-agent.jar
COPY ./target/classes/applicationinsights.json /usr/src/reach-audit/applicationinsights.json

RUN chown jreuser /usr/src/reach-audit
USER jreuser

EXPOSE 8094

CMD java -javaagent:/usr/src/reach-audit/applicationinsights-agent.jar \
-Xmx${JAVA_MX:-512M} -Xms${JAVA_MS:-256M} -jar reach-audit.jar
