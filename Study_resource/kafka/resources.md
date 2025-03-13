

Confluent palylist** -> KafkaServer {
org.apache.kafka.common.security.oauthbearer.OAuthBearerLoginModule required
oauth.token.endpoint.uri="https://idp.example.com/oauth/token"
oauth.client.id="kafka-broker"
oauth.client.secret="broker-secret"
oauth.jwks.endpoint.uri="https://idp.example.com/oauth/jwks";
};