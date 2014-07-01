/*******************************************************************************
 * Copyright (c) 2012, 2014 TH4 SYSTEMS GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     TH4 SYSTEMS GmbH - initial API and implementation
 *     IBH SYSTEMS GmbH - additional work
 *     IBH SYSTEMS GmbH - bug fixes and extensions, enhancements for legends
 *******************************************************************************/
package org.eclipse.scada.ui.chart.viewer;

import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.scada.chart.swt.ChartRenderer;
import org.eclipse.scada.ui.chart.model.Chart;
import org.eclipse.scada.ui.chart.model.XAxis;
import org.eclipse.scada.ui.chart.model.YAxis;

public class ChartContextImpl implements ChartContext
{
    private final AxisLocator<XAxis, XAxisViewer> xAxisLocator;

    private final AxisLocator<YAxis, YAxisViewer> yAxisLocator;

    private final ExtensionSpaceProvider extensionSpaceProvider;

    private final ChartRenderer chartRenderer;

    private final Chart chart;

    private final ResetHandler resetHandler;

    private final IObservableList informations;

    public ChartContextImpl ( final AxisLocator<XAxis, XAxisViewer> xAxisLocator, final AxisLocator<YAxis, YAxisViewer> yAxisLocator, final ExtensionSpaceProvider extensionSpaceProvider, final ChartRenderer chartRenderer, final Chart chart, final ResetHandler resetHandler, final IObservableList informations )
    {
        this.resetHandler = resetHandler;
        this.xAxisLocator = xAxisLocator;
        this.yAxisLocator = yAxisLocator;
        this.extensionSpaceProvider = extensionSpaceProvider;
        this.chartRenderer = chartRenderer;
        this.chart = chart;
        this.informations = informations;
    }

    @Override
    public IObservableList getInformations ()
    {
        return this.informations;
    }

    /* (non-Javadoc)
     * @see org.eclipse.scada.ui.chart.model.viewer.ChartContext#getxAxisLocator()
     */
    @Override
    public AxisLocator<XAxis, XAxisViewer> getxAxisLocator ()
    {
        return this.xAxisLocator;
    }

    /* (non-Javadoc)
     * @see org.eclipse.scada.ui.chart.model.viewer.ChartContext#getyAxisLocator()
     */
    @Override
    public AxisLocator<YAxis, YAxisViewer> getyAxisLocator ()
    {
        return this.yAxisLocator;
    }

    /* (non-Javadoc)
     * @see org.eclipse.scada.ui.chart.model.viewer.ChartContext#getExtensionSpaceProvider()
     */
    @Override
    public ExtensionSpaceProvider getExtensionSpaceProvider ()
    {
        return this.extensionSpaceProvider;
    }

    @Override
    public ChartRenderer getChartRenderer ()
    {
        return this.chartRenderer;
    }

    @Override
    public ResetHandler getResetHandler ()
    {
        return this.resetHandler;
    }

    @Override
    public Chart getChart ()
    {
        return this.chart;
    }

    @Override
    public void relayoutExtensionSpace ()
    {
        if ( this.extensionSpaceProvider != null )
        {
            this.extensionSpaceProvider.relayout ();
        }
    }
}
