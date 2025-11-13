package com.pnfsoftware.jeb.core.units.code.asm.decompiler;

import com.pnfsoftware.jeb.core.units.code.DecompilationContext;
import com.pnfsoftware.jeb.core.units.code.asm.INativeContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.ICMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.IEMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;

@Ser
public interface INativeDecompilerContext {
   DecompilerOptions getOptions();

   INativeContext getNativeContext();

   IEGlobalContext getIntermediateContext();

   default IEGlobalContext getGlobalContext() {
      return this.getIntermediateContext();
   }

   ICGlobalContext getHighLevelContext();

   IEConverter getConverter();

   ITypeManager getTypeManager();

   IWildcardTypeManager getWildcardTypeManager();

   INativeObjectTracker getObjectTracker();

   INativeDecompilerExtensionsManager getExtensionsManager();

   IEMasterOptimizer createIROptimizer(IERoutineContext var1);

   ICMasterOptimizer createASTOptimizer(ICMethod var1);

   Collection getDecompiledItems();

   IDecompiledItem getDecompiledItem(String var1);

   IDecompiledItem getDecompiledItem(INativeItem var1);

   default IDecompiledItem decompile(INativeItem var1) {
      return this.decompile(var1, null);
   }

   IDecompiledItem decompile(INativeItem var1, DecompilationContext var2);

   void resetDecompilation(INativeItem var1);

   void removeDecompilation(INativeItem var1);

   Object getEngine();

   void onEngineNotification(Object var1);
}
