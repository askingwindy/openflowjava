/*
 * Copyright (c) 2013 Pantheon Technologies s.r.o. and others. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */


package org.opendaylight.openflowjava.protocol.spi.connection;

import org.opendaylight.openflowjava.protocol.api.connection.ConnectionConfiguration;
import org.opendaylight.openflowjava.protocol.api.connection.SwitchConnectionHandler;

import com.google.common.util.concurrent.ListenableFuture;

/**
 * @author mirehak
 * @author michal.polkorab
 *
 */
public interface SwitchConnectionProvider extends AutoCloseable {

    /**
     * @param configuration [protocol, port, address and supported features]
     */
    void setConfiguration(ConnectionConfiguration configuration);
    
    /**
     * start listening to switches, but please don't forget to do
     * {@link #setSwitchConnectionHandler(SwitchConnectionHandler)} first
     * @return future, triggered to true, when listening channel is up and running
     */
    ListenableFuture<Boolean> startup();
    
    /**
     * stop listening to switches
     * @return future, triggered to true, when all listening channels are down
     */
    ListenableFuture<Boolean> shutdown();
    
    /**
     * @param switchConHandler instance being informed when new switch connects
     */
    void setSwitchConnectionHandler(SwitchConnectionHandler switchConHandler);
    
}
