/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.transport.rmi;

import org.mule.api.MuleContext;
import org.mule.api.transport.MessageTypeNotSupportedException;
import org.mule.transport.AbstractMuleMessageFactory;

public class RmiMuleMessageFactory extends AbstractMuleMessageFactory
{

    public RmiMuleMessageFactory(MuleContext context)
    {
        super(context);
    }

    @Override
    protected Object extractPayload(Object transportMessage, String encoding) throws Exception
    {
        if (transportMessage == null)
        {
            throw new MessageTypeNotSupportedException(null, getClass());
        }
        else
        {
            return transportMessage;
        }
    }

    @Override
    protected Class<?>[] getSupportedTransportMessageTypes()
    {
        return new Class[]{Object.class};
    }

}
