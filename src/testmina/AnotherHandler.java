package testmina;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 *
 * @author skuarch
 */
public class AnotherHandler extends IoHandlerAdapter {

    private static Logger logger = null;                
            
    //==========================================================================
    public AnotherHandler() {
        super();
        PropertyConfigurator.configure("log.properties");
        logger = Logger.getLogger(getClass());
    }   
    
    //==========================================================================
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        session.close(true);
        logger.error("error", cause);
    }
    
    //==========================================================================
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        /*String str = message.toString();
        
        if (str.trim().equalsIgnoreCase("quit")) {

            session.close(true);
            return;
        }*/
        
        //logger.info("receiving " + message);
        Person p = (Person) message;
        logger.info("no mames"+ p.getMocos());
        
        session.write("que pedo");
        System.out.println("sending response");
        session.close(true);
        session.getCloseFuture().setClosed();
        System.exit(0);
        
    }
    
    

    //==========================================================================
    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        System.out.println("IDLE " + session.getIdleCount(status));
        
        if(session.getIdleCount(status) >= 1){            
            logger.error("session close");
            session.close(true);
            return;
        }
        
    }

    //==========================================================================
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        logger.info("id session " + session.getId());
    }
    
} // class

