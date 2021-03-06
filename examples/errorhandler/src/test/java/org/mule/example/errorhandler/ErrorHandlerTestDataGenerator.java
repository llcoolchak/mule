/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.example.errorhandler;

import org.mule.api.DefaultMuleException;
import org.mule.api.lifecycle.FatalException;
import org.mule.api.transformer.TransformerException;
import org.mule.config.i18n.MessageFactory;
import org.mule.example.errorhandler.exceptions.BusinessException;
import org.mule.module.xml.transformer.ObjectToXml;
import org.mule.util.FileUtils;

import java.io.IOException;

public class ErrorHandlerTestDataGenerator
{

    public static void generateTestData(String targetDir) throws IOException, TransformerException
    {
        if (!(targetDir.endsWith("/") || targetDir.endsWith("\\")))
        {
            targetDir += "/";
        }

        ObjectToXml trans = new ObjectToXml();
        DefaultMuleException exception = new DefaultMuleException(MessageFactory.createStaticMessage("Some default exception"));
        FatalException fatal = new FatalException(MessageFactory.createStaticMessage("Some fatal exception"),
            new IOException("Some IO exception"));
        BusinessException business = new BusinessException("Some business exception");

        ExceptionBean bean = new ExceptionBean(exception);
        String xml = (String)trans.transform(bean);
        FileUtils.stringToFile(targetDir + "DefaultMuleException.xml", xml);

        bean = new ExceptionBean(fatal);
        xml = (String)trans.transform(bean);
        FileUtils.stringToFile(targetDir + "FatalException.xml", xml);

        bean = new ExceptionBean(business);
        xml = (String)trans.transform(bean);
        FileUtils.stringToFile(targetDir + "BusinesException.xml", xml);
    }

    public static void main(String[] args)
    {

        if (args.length == 0)
        {
            System.out.println("You must specifiy a target directory for the output files");
            System.exit(1);
        }
        String path = args[0];
        try
        {
            generateTestData(path);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
