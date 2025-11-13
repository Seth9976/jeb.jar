package com.pnfsoftware.jeb.core;

import com.pnfsoftware.jeb.core.dao.IDataProvider;
import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.core.units.IUnitIdentifier;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeSignatureDBManager;
import com.pnfsoftware.jeb.core.units.code.asm.sig.codeless.CodelessSignatureManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeLibraryService;
import com.pnfsoftware.jeb.util.base.IProgressCallback;
import com.pnfsoftware.jeb.util.events.IEventSource;
import com.pnfsoftware.jeb.util.net.INet;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public interface IEnginesContext extends IEventSource {
   ICoreContext getCoreContext();

   void close();

   IDataProvider getDataProvider();

   ExecutorService getExecutorService();

   IPropertyManager getPropertyManager();

   INet getNetworkUtility();

   List getEnginesPlugins();

   IRuntimeProject loadProject(String var1) throws IOException;

   IRuntimeProject loadProject(String var1, boolean var2, IProgressCallback var3) throws IOException;

   boolean saveProject(String var1) throws IOException;

   boolean saveProject(String var1, String var2, Map var3, IProgressCallback var4) throws IOException;

   boolean unloadProject(String var1);

   boolean unloadProject(String var1, boolean var2);

   void unloadProjects();

   void unloadProjects(boolean var1);

   void clearUnloadedProject();

   boolean hasProjects();

   IRuntimeProject getMainProject();

   IRuntimeProject getProject(String var1);

   IRuntimeProject getProject(int var1);

   List getProjects();

   File getProjectFile(String var1) throws IOException;

   List getUnitIdentifiers();

   IUnitIdentifier getUnitIdentifier(String var1);

   List getDebuggerUnitIdentifiers();

   List getNativeDisassemblerPlugins();

   List getNativeDecompilerPlugins();

   boolean setIdentifierEnabled(IUnitIdentifier var1, boolean var2);

   boolean isIdentifierEnabled(IUnitIdentifier var1);

   IPluginManager getPluginManager();

   TypeLibraryService getTypeLibraryService();

   NativeSignatureDBManager getNativeSignatureDBManager();

   CodelessSignatureManager getCodelessSignatureManager();

   String unitProperty(String var1, String var2);
}
