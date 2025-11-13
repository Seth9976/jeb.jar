package com.pnfsoftware.jeb.core.units.code.android;

import com.pnfsoftware.jeb.core.units.code.IDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDGlobalContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.java.IDynamicContentManager;
import com.pnfsoftware.jeb.core.units.code.java.IJavaClass;
import com.pnfsoftware.jeb.core.units.code.java.IJavaField;
import com.pnfsoftware.jeb.core.units.code.java.IJavaGlobalContext;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;

@Ser
public interface IDexDecompilerUnit extends IDecompilerUnit {
   String propnameEmulatorConfigPath = "EmulatorConfigPath";

   IDexUnit getCodeUnit();

   IDGlobalContext getIntermediateContext();

   IJavaGlobalContext getHighLevelContext();

   IDynamicContentManager getDynamicContentManager();

   boolean setIdentifierName(String var1, String var2, String var3);

   boolean setIdentifierName(IJavaIdentifier var1, String var2);

   boolean setIdentifierName(IJavaIdentifier var1, String var2, boolean var3, boolean var4);

   String getIdentifierName(IJavaIdentifier var1);

   IJavaClass getClass(String var1, boolean var2);

   IJavaField getField(String var1, boolean var2);

   IJavaMethod getMethod(String var1, boolean var2);

   @Override
   void resetDecompilation(String var1);

   boolean resetClassElement(IJavaClass var1);

   boolean resetFieldElement(IJavaField var1);

   boolean resetMethodElement(IJavaMethod var1);

   void registerEventQueue(DexDecompilerEventQueue var1);

   void unregisterEventQueue(DexDecompilerEventQueue var1);

   Collection getGlobalDecompilationEvents();

   Collection getGlobalDecompilationEvents(boolean var1);

   void resetGlobalDecompilationEvents();

   void recordDecompilationEvent(DexDecompilerEvent var1);

   void addSpecialComment(String var1, String var2);

   void clearSpecialComments(String var1);

   IEmulatedAndroid createEmulatedAndroid();

   IGenericUnpacker createGenericUnpacker();

   String formatOngoingDecompilations();

   void setCachePolicy(int var1, int var2);

   void clearCachedIRs();

   int getCountOfCachedIRs();

   IDMethodContext retrieveCachedIR(String var1);
}
