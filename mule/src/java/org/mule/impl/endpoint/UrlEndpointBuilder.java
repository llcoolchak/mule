/*
 * $Header$
 * $Revision$
 * $Date$
 * ------------------------------------------------------------------------------------------------------
 *
 * Copyright (c) Cubis Limited. All rights reserved.
 * http://www.cubis.co.uk
 *
 * The software in this package is published under the terms of the BSD
 * style license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 */
package org.mule.impl.endpoint;

import org.mule.umo.endpoint.MalformedEndpointException;

import java.net.URI;
import java.util.Properties;

/**
 * <code>UrlEndpointBuilder</code> is the default endpointUri strategy suitable for most
 * connectors
 *
 * @author <a href="mailto:ross.mason@cubis.co.uk">Ross Mason</a>
 * @version $Revision$
 */

public class UrlEndpointBuilder extends AbstractEndpointBuilder
{

    protected void setEndpoint(URI uri, Properties props) throws MalformedEndpointException
    {
        if (uri.getHost() != null )
        {
            //set the endpointUri to be a proper url if host and port are set
            this.address = uri.getScheme() + "://" + uri.getHost();
            if(uri.getPort() != -1) {
                address += ":" + uri.getPort();
            }
        }
        if(uri.getPath()!=null) {
            address += uri.getPath();
        }
    }
}
