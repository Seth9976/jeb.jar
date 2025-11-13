package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.core.units.code.IDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerUnit;
import java.util.List;

public interface IUnitProcessor {
   IPropertyDefinitionManager getPropertyDefinitionManager();

   IPropertyManager getPropertyManager();

   IUnitIdentifier registerPlugin(IUnitPlugin var1);

   boolean unregisterUnitIdentifier(IUnitIdentifier var1);

   List getUnitIdentifiers();

   List getDebuggerUnitIdentifiers();

   IUnitIdentifier getUnitIdentifier(String var1);

   boolean getAlwaysProcessDuplicateInputs();

   void setUnknownInputResolver(IUnknownInputResolver var1);

   int setProcessingDepth(int var1);

   int getProcessingDepth();

   IUnit process(String var1, IInput var2, IUnitCreator var3, String var4, boolean var5, boolean var6);

   IUnit process(String var1, IInput var2, IUnitCreator var3, String var4, boolean var5);

   IUnit process(String var1, IInput var2, IUnitCreator var3, String var4);

   IUnit process(String var1, IInput var2, IUnitCreator var3);

   IUnit process(String var1, IUnitCreator var2);

   IDecompilerUnit createDecompiler(String var1, IUnit var2);

   IDebuggerUnit createDebugger(String var1, IUnit var2);
}
