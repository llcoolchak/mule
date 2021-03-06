/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.module.launcher.application;

import java.net.URL;

/**
 * Identifies a classLoader created for a given Mule application
 */
public interface ApplicationClassLoader
{

    String getAppName();

    URL findResource(String s);
}
