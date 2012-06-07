package testmina;

import java.net.BindException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 *
 * @author skuarch
 */
public class Main {

    public static Logger logger = null;
    private static final int PORT = 9123;

    //==========================================================================
    public Main() {
        PropertyConfigurator.configure("log.properties");
        logger = Logger.getLogger(getClass());
    }

    //==========================================================================
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        new Main();

        try {

            //IoAcceptor acceptor = new NioSocketAcceptor();
            SocketAcceptor acceptor = new NioSocketAcceptor();
            acceptor.setReuseAddress( true );

            //acceptor.getFilterChain().addLast("logger", new LoggingFilter());
            acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));

            acceptor.setHandler(new AnotherHandler());            
            acceptor.getSessionConfig().setReadBufferSize(2048);
            acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
            acceptor.bind(new InetSocketAddress(PORT));

            acceptor.setCloseOnDeactivation(true);           
                                   

        } catch (BindException be) {
            logger.error("error", be);
        } catch (Exception e) {
            logger.error("error", e);
        }

    } // end main
} // end class
