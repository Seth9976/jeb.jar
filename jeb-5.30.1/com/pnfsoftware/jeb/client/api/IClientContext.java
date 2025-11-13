package com.pnfsoftware.jeb.client.api;

import com.pnfsoftware.jeb.core.IEnginesContext;
import com.pnfsoftware.jeb.core.IRuntimeProject;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.units.IUnit;
import java.io.IOException;
import java.util.Map;

public interface IClientContext {
   String[] getArguments();

   String getBaseDirectory();

   String getProgramDirectory();

   Version getSoftwareVersion();

   long getUsedMemory();

   long getMaxMemory();

   long getProcessId();

   IEnginesContext getEnginesContext();

   IRuntimeProject getMainProject();

   boolean closeMainProject();

   IUnit open(String var1) throws IOException;

   Map getTransientStore();
}
