package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMemoryItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;
import java.util.Map;

@Ser
public interface ILabelManager {
   Map getPrimaryLabels();

   String getLabel(long var1, long var3, AutoLabelPolicy var5);

   String getLabel(INativeMemoryItem var1, long var2, AutoLabelPolicy var4);

   Collection getAlternateLabels(long var1);

   boolean setLabel(long var1, String var3, boolean var4, boolean var5, boolean var6);

   boolean removeLabel(long var1);

   Long resolveLabel(String var1);

   default boolean isLegalLabel(String var1) {
      return this.isLegalLabel(var1, null, null);
   }

   boolean isLegalLabel(String var1, Long var2, StringBuilder var3);
}
