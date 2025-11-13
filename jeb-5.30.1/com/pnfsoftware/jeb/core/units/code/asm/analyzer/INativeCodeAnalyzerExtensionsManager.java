package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface INativeCodeAnalyzerExtensionsManager extends INativeCodeAnalyzerExtension {
   void registerExtension(INativeCodeAnalyzerExtension var1);

   void registerExtension(INativeCodeAnalyzerExtension var1, INativeCodeAnalyzerExtensionsManager.ExtensionPriority var2);

   void registerExtensions(List var1, boolean var2);

   public static enum ExtensionPriority {
      LOW_PRIORITY,
      MEDIUM_PRIORITY,
      HIGH_PRIORITY;
   }
}
