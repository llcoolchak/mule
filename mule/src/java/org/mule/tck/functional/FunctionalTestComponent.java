/*
 * $Header$
 * $Revision$
 * $Date$
 * ------------------------------------------------------------------------------------------------------
 *
 * Copyright (c) Cubis Limited. All rights reserved. http://www.cubis.co.uk
 *
 * The software in this package is published under the terms of the BSD style
 * license a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 *
 */

package org.mule.tck.functional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mule.umo.UMOEventContext;
import org.mule.umo.lifecycle.Callable;
import org.mule.util.StringMessageHelper;

/**
 * <code>FunctionalTestComponent</code> is a component that can be used by
 * functional tests.  This component accepts an EventCallback that can be used to assert the
 * state of the current event.
 *
 * @author <a href="mailto:ross.mason@cubis.co.uk">Ross Mason</a>
 * @version $Revision$
 * @see EventCallback
 */

public class FunctionalTestComponent implements Callable
{
    private static transient Log logger = LogFactory.getLog(FunctionalTestComponent.class);

    private EventCallback eventCallback;

    public Object onCall(UMOEventContext context) throws Exception
    {
        String contents = context.getMessageAsString();
        String msg = null;
        if(contents.length() > 100) {
            msg = StringMessageHelper.getBoilerPlate("Message Received in component: " +
                context.getComponentDescriptor().getName() + ". Content length is: " + contents.length(), '*', 80);
        }else {
            msg = StringMessageHelper.getBoilerPlate("Message Received in component: " +
                context.getComponentDescriptor().getName() + ". Content is: " + contents, '*', 80);
        }
        logger.info(msg);


        contents += " Received";
        if (eventCallback != null)
        {
            eventCallback.eventReceived(context, this);
        }
        return contents;
    }

    public EventCallback getEventCallback()
    {
        return eventCallback;
    }

    public void setEventCallback(EventCallback eventCallback)
    {
        this.eventCallback = eventCallback;
    }
}