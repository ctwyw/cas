/*
 * Copyright 2005 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.uportal.org/license.html
 */
package org.jasig.cas.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.flow.execution.NoSuchFlowExecutionException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * The NoSuchFlowExecutionResolver catches the NoSuchFlowExecutionException
 * thrown by Spring Webflow when the given flow id no longer exists. This can
 * occur if a particular flow has reached an end state (the id is no longer
 * valid)
 * <p>
 * It will redirect back to the requested URI which should start a new workflow.
 * </p>
 * 
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.0
 */
public final class NoSuchFlowExecutionExceptionResolver implements
    HandlerExceptionResolver {
    
    /** Instance of a log. */
    private final Log log = LogFactory.getLog(this.getClass());

    public ModelAndView resolveException(final HttpServletRequest request,
        final HttpServletResponse response, final Object handler,
        final Exception exception) {

        if (!exception.getClass().equals(NoSuchFlowExecutionException.class)) {
            return null;
        }

        log.debug("Error getting flow information for URL:" + request.getRequestURI(), exception);
        
        return new ModelAndView(new RedirectView(request.getRequestURI()));
    }
}