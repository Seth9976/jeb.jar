package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class EStateDumper {
   EState state;

   public EStateDumper(EState var1) {
      this.state = var1;
   }

   public void dumpTo(File var1) throws IOException {
      IVirtualMemory var2 = this.state.getMemory();
      VirtualMemoryUtil.dumpToImageFiles(var2, var1, "img_", "");
      IEGlobalContext var3 = this.state.getGlobalContext();
      ArrayList var4 = new ArrayList();
      ProcessorType var5 = var3.getNativeContext().getProcessor().getType();
      if (var5 != ProcessorType.ARM64) {
         throw new IllegalArgumentException("Limited to aarch64 for now");
      } else {
         var4.add("PC");

         for (int var6 = 0; var6 <= 30; var6++) {
            var4.add("X" + var6);
         }

         var4.add("XSP");

         for (int var11 = 0; var11 <= 31; var11++) {
            var4.add("V" + var11);
         }

         var4.add("TPIDR_EL0");
         StringBuilder var12 = new StringBuilder();

         for (String var8 : var4) {
            IEVar var9 = var3.getVariableByName(var8);
            if (var9 == null) {
               throw new RuntimeException("Cannot find register: " + var8);
            }

            String var10 = this.state.getValue(var9).toHexString();
            Strings.ff(var12, "%s=%s\n", var8, var10);
         }

         IO.writeFile(new File(var1, "registers"), Strings.encodeUTF8(var12.toString()));
      }
   }
}
