/*
 *   Copyright 2006 The Portico Project
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
package org.portico.impl.hla1516.types;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import hla.rti1516.CouldNotDecode;
import hla.rti1516.FederateNotExecutionMember;
import hla.rti1516.ParameterHandle;
import hla.rti1516.ParameterHandleFactory;

public class HLA1516ParameterHandleFactory implements ParameterHandleFactory
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	private static final long serialVersionUID = 98121116105109L;
	
	public static final HLA1516ParameterHandleFactory INSTANCE = 
		new HLA1516ParameterHandleFactory();
	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
	public ParameterHandle decode( byte[] buffer, int offset ) throws CouldNotDecode,
		FederateNotExecutionMember
	{
		try
		{
			// get the value
			ByteArrayInputStream byteStream =
				new ByteArrayInputStream( buffer, offset, buffer.length-offset );
			DataInputStream data = new DataInputStream( byteStream );
			int handle = data.readInt();
			data.close();
			
			// return it
			return new HLA1516ParameterHandle( handle );
		}
		catch( Exception e )
		{
			throw new CouldNotDecode( "Error decoding ParameterHandle: " + e.getMessage() );
		}
	}
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
