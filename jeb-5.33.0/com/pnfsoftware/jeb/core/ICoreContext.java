package com.pnfsoftware.jeb.core;

import com.pnfsoftware.jeb.core.dao.IDataProvider;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.util.events.IEventSource;
import java.util.List;

public interface ICoreContext extends IEventSource {
   CoreOptions getOptions();

   Version getVersion();

   void close();

   IEnginesContext createEnginesContext(IDataProvider var1, JebClientInformation var2) throws JebException;

   List listEnginesContexts();

   IEnginesContext getDefaultEnginesContexts();

   void closeEnginesContext(IEnginesContext var1);

   Object getValue(int var1, Object... var2);
}
