package com.pnfsoftware.jeb.core.units.code.asm.decompiler;

import java.util.List;

public interface INativeDecompilerExtensionsManager extends INativeDecompilerExtension {
   boolean registerExtension(INativeDecompilerExtension var1);

   boolean registerExtension(INativeDecompilerExtension var1, INativeDecompilerExtensionsManager.ExtensionPriority var2);

   int registerExtensions(List var1, boolean var2);

   public static enum ExtensionPriority {
      LOW_PRIORITY,
      MEDIUM_PRIORITY,
      HIGH_PRIORITY;
   }
}
