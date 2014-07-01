/*******************************************************************************
 * Copyright (c) 2012, 2014 TH4 SYSTEMS GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     TH4 SYSTEMS GmbH - initial API and implementation
 *     Jens Reimann - additional work
 *     IBH SYSTEMS GmbH - some minor additions
 *******************************************************************************/
package org.eclipse.scada.da.client.dataitem.details.extra.part;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.scada.core.Variant;

public class LocalLevelPresetsOld extends GenericLevelPresets
{
    @Override
    protected boolean isUnsafe ( final String string )
    {
        return getBooleanAttribute ( String.format ( "org.eclipse.scada.da.level.%s.unsafe", string ) ); //$NON-NLS-1$
    }

    @Override
    protected boolean isActive ( final String string )
    {
        return getBooleanAttribute ( String.format ( "org.eclipse.scada.da.level.%s.active", string ) ); //$NON-NLS-1$
    }

    @Override
    protected Number getPreset ( final String string )
    {
        return getNumberAttribute ( String.format ( "org.eclipse.scada.da.level.%s.preset", string ), null ); //$NON-NLS-1$
    }

    @Override
    protected boolean isCapped ()
    {
        return this.hasAttribute ( "org.eclipse.scada.ae.monitor.level.value.original" ); //$NON-NLS-1$
    }

    @Override
    protected Variant getOriginalValue ()
    {
        return this.value.getAttributes ().get ( "org.eclipse.scada.ae.monitor.level.value.original" ); //$NON-NLS-1$
    }

    @Override
    protected boolean isAlarm ( final String string )
    {
        return getBooleanAttribute ( String.format ( "org.eclipse.scada.da.level.%s.alarm", string ) ); //$NON-NLS-1$
    }

    @Override
    protected boolean isWarning ( final String string )
    {
        return getBooleanAttribute ( String.format ( "org.eclipse.scada.da.level.%s.warning", string ) ); //$NON-NLS-1$
    }

    @Override
    protected boolean isError ( final String string )
    {
        return getBooleanAttribute ( String.format ( "org.eclipse.scada.da.level.%s.error", string ) ); //$NON-NLS-1$
    }

    @Override
    protected boolean isAckRequired ( final String string, final String severity )
    {
        return getBooleanAttribute ( String.format ( "org.eclipse.scada.da.level.%s.%s.ackRequired", string, severity ) ); //$NON-NLS-1$
    }

    @Override
    protected void setPreset ( final Variant value, final boolean active, final String tag )
    {
        final Map<String, Variant> attributes = new HashMap<String, Variant> ();

        attributes.put ( String.format ( "org.eclipse.scada.da.level.%s.preset", tag ), value ); //$NON-NLS-1$
        attributes.put ( String.format ( "org.eclipse.scada.da.level.%s.active", tag ), active ? Variant.TRUE : Variant.FALSE ); //$NON-NLS-1$

        this.item.writeAtrtibutes ( attributes, null, makeDisplayCallback () );
    }

    @Override
    protected void setActive ( final boolean state, final String tag )
    {
        final Map<String, Variant> attributes = new HashMap<String, Variant> ();

        attributes.put ( String.format ( "org.eclipse.scada.da.level.%s.active", tag ), state ? Variant.TRUE : Variant.FALSE ); //$NON-NLS-1$

        this.item.writeAtrtibutes ( attributes, null, makeDisplayCallback () );
    }

    private static final String[] TAGS = new String[] { "floor", "lowlow", "low", "high", "highhigh", "ceil" };

    @Override
    protected boolean isAvailable ()
    {
        for ( final String tag : TAGS )
        {
            if ( hasAttribute ( String.format ( "org.eclipse.scada.da.level.%s.active", tag ) ) )//$NON-NLS-1$
            {
                return true;
            }
        }
        return false;
    }

    @Override
    protected String getMinTag ()
    {
        return "floor";
    }

    @Override
    protected String getMaxTag ()
    {
        return "ceil";
    }

    @Override
    protected String getHighHighTag ()
    {
        return "highhigh";
    }

    @Override
    protected String getHighTag ()
    {
        return "high";
    }

    @Override
    protected String getLowTag ()
    {
        return "low";
    }

    @Override
    protected String getLowLowTag ()
    {
        return "lowlow";
    }

}
