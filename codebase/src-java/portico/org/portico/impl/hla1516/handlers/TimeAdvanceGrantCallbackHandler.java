/*
 *   Copyright 2008 The Portico Project
 *
 *   This file is part of portico.
 *
 *   portico is free software; you can redistribute it and/or modify
 *   it under the terms of the Common Developer and Distribution License (CDDL) 
 *   as published by Sun Microsystems. For more information see the LICENSE file.
 *   
 *   Use of this software is strictly AT YOUR OWN RISK!!!
 *   If something bad happens you do not have permission to come crying to me.
 *   (that goes for your lawyer as well)
 *
 */
package org.portico.impl.hla1516.handlers;

import java.util.Map;

import org.portico.impl.hla1516.Impl1516Helper;
import org.portico.impl.hla1516.types.DoubleTime;
import org.portico.lrc.LRCMessageHandler;
import org.portico.lrc.services.time.msg.TimeAdvanceGrant;
import org.portico.utils.messaging.MessageContext;
import org.portico.utils.messaging.MessageHandler;

@MessageHandler(modules="lrc1516-callback",
                keywords="lrc1516",
                sinks="incoming",
                priority=3,
                messages=TimeAdvanceGrant.class)
public class TimeAdvanceGrantCallbackHandler extends LRCMessageHandler
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private Impl1516Helper helper;

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------

	public void initialize( Map<String,Object> properties )
	{
		super.initialize( properties );
		this.helper = (Impl1516Helper)lrc.getSpecHelper();
	}
	
	public void process( MessageContext context ) throws Exception
	{
		TimeAdvanceGrant grant = context.getRequest( TimeAdvanceGrant.class, this );
		if( logger.isTraceEnabled() )
			logger.trace( "CALLBACK timeAdvanceGrant(time="+grant.getTime()+")" );
		helper.getFederateAmbassador().timeAdvanceGrant( new DoubleTime(grant.getTime()) );
		context.success();
	}
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
