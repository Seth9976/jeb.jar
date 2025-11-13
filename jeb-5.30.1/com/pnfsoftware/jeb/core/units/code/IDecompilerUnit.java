package com.pnfsoftware.jeb.core.units.code;

import com.pnfsoftware.jeb.core.units.IInteractiveUnit;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;

@Ser
public interface IDecompilerUnit extends IInteractiveUnit {
   String propnameListOfDisabledExternalPlugins = "ListOfDisabledExternalPlugins";
   int FLAG_NO_INNER_DECOMPILATION = 1;
   int FLAG_NO_DEFERRED_DECOMPILATION = 2;
   int FLAG_NO_CONCURRENT_DECOMPILATION = 4;
   int FLAG_NO_METHOD_AST_GENERATION = 8;
   int FLAG_TEMP_FORCED_REDECOMPILATIONS = 16;
   int FLAG_BATCH_DECOMPILATION = 32;
   int FLAG_KEEP_IR = 64;
   int FLAG_STANDALONE_IR_CONVERSION = 128;

   ICodeUnit getCodeUnit();

   DecompilerOutputType getOutputType();

   boolean canDecompile(String var1);

   ISourceUnit getDecompiledUnit(String var1);

   ISourceUnit decompileToUnit(String var1);

   ISourceUnit decompileToUnit(String var1, DecompilationContext var2);

   boolean decompileClass(String var1);

   boolean decompileClass(String var1, DecompilationContext var2);

   boolean decompileField(String var1);

   boolean decompileField(String var1, DecompilationContext var2);

   boolean decompileMethod(String var1);

   boolean decompileMethod(String var1, DecompilationContext var2);

   boolean decompileMethods(Collection var1, DecompilationContext var2);

   boolean decompileAllMethods(DecompilationContext var1);

   boolean decompileClasses(Collection var1, DecompilationContext var2);

   boolean decompileAllClasses(DecompilationContext var1);

   boolean decompileAll(DecompilationContext var1);

   boolean decompile(Collection var1, DecompilationContext var2);

   void resetDecompilation(String var1);

   void removeDecompilation(String var1);

   void resetAllDecompilations();

   void removeAllDecompilations();

   void removeFreeElements();

   default void runGarbageCollection() {
      this.removeFreeElements();
   }

   String getDecompiledText(String var1);

   String getDecompiledClassText(String var1);

   String getDecompiledFieldText(String var1);

   String getDecompiledMethodText(String var1);

   boolean canPerformConcurrentDecompilations();

   int getThreadPoolSize();

   void setThreadPoolSize(int var1);

   DecompilerExporter getExporter();
}
