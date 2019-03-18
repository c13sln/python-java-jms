import org.jboss.ejb3.annotation.ResourceAdapter;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ResourceAdapter("lokal-mq")
@MessageDriven(activationConfig = {@ActivationConfigProperty(propertyName = "destination", propertyValue = "exempel"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "maxSession", propertyValue = "1")})
public class StompConsumer implements MessageListener {

    private static final Logger LOG = LoggerFactory.getLogger(StompConsumer.class);

    public void onMessage(Message message) {
        LOG.info("Message arrived! :)");

        if (message instanceof TextMessage) {
            TextMessage textmessage = (TextMessage) message;
            try {
                LOG.info("Breaking News: {}", textmessage.getText());
            } catch (JMSException e) {
                LOG.error("Textmeddelande kunde inte utläsas: {}", e);
            }
        } else {
            BytesMessage bm = (BytesMessage) message;
            byte data[] = new byte[0];
            try {
                data = new byte[(int) bm.getBodyLength()];
                bm.readBytes(data);
            } catch (JMSException e) {
                LOG.error("Textmeddelande kunde inte utläsas: {}", e);
            }
            String s = new String(data);
            LOG.info("Breaking News: {}", s);

        }
    }
}

