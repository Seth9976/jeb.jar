package com.pnfsoftware.jeb.core.units.code.asm;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IClassRebuilder;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.core.util.DecompilerHelper;
import com.pnfsoftware.jebglobal.anx;
import com.pnfsoftware.jebglobal.anz;

public class NativeCodeUtil {
   public static INativeDataItem createDataItem(INativeCodeUnit var0, long var1, String var3, String var4) {
      if (var0.setDataAt(var1, var0.getTypeManager().getType(var3), var4)) {
         INativeContinuousItem var5 = var0.getNativeItemAt(var1);
         if (var5 instanceof INativeDataItem) {
            return (INativeDataItem)var5;
         }
      }

      return null;
   }

   public static final IClassRebuilder createClassRebuilder(INativeCodeUnit var0) {
      INativeDecompilerUnit var1 = (INativeDecompilerUnit)DecompilerHelper.getDecompiler(var0);
      return createClassRebuilder(var0, var1);
   }

   public static final IClassRebuilder createClassRebuilder(INativeCodeUnit var0, INativeDecompilerUnit var1) {
      Object var2;
      if (var0.getDetectedCompiler().isVisualStudio() && var0.getProcessor().getType() == ProcessorType.X86) {
         var2 = new anz(var0, var1);
      } else {
         var2 = new anx(var0, var1);
      }

      return (IClassRebuilder)var2;
   }
}
