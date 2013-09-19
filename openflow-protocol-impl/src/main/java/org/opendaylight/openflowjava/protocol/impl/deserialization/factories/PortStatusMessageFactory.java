package org.opendaylight.openflowjava.protocol.impl.deserialization.factories;

import io.netty.buffer.ByteBuf;

import org.opendaylight.openflowjava.protocol.impl.deserialization.OFDeserializer;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.protocol.rev130731.PortStatusMessage;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.protocol.rev130731.PortStatusMessageBuilder;

/**
 * @author michal.polkorab
 * @author timotej.kubas
 */
public class PortStatusMessageFactory implements OFDeserializer<PortStatusMessage> {

    private static PortStatusMessageFactory instance;
    private static final byte PADDING_IN_FEATURES_REPLY_HEADER = 7;
    
    private PortStatusMessageFactory() {
        // Singleton
    }
    
    /**
     * @return singleton factory
     */
    public static PortStatusMessageFactory getInstance(){
        if(instance == null){
            
            instance = new PortStatusMessageFactory();
        }
        
        return instance;
    }
    
    @Override
    public PortStatusMessage bufferToMessage(ByteBuf rawMessage, short version) {
        PortStatusMessageBuilder psmb = new PortStatusMessageBuilder(); 
        psmb.setVersion(version);
        psmb.setXid(rawMessage.readUnsignedInt());
   
//        TODO enum portReason
//        psmb.setReason(PortReason.values()[rawMessage.readInt()]);
        rawMessage.skipBytes(1); //instead of portReason enum
        
        rawMessage.skipBytes(PADDING_IN_FEATURES_REPLY_HEADER);
        return psmb.build();
    }

    
    
}