package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import java.io.File;
import java.io.IOException;

public class EStateDumper {
   EState state;

   public EStateDumper(EState var1) {
      this.state = var1;
   }

   public void dumpTo(File var1) throws IOException {
      IVirtualMemory var2 = this.state.getMemory();
      VirtualMemoryUtil.dumpToImageFiles(var2, var1, "img_", "");
      IEGlobalContext var3 = this.state.getGlobalContext();
      ProcessorType var5 = var3.getNativeContext().getProcessor().getType();
      if (var5 == ProcessorType.ARM64) {
         String[] var4 = new String[]{
            "PC",
            "X0",
            "X1",
            "X2",
            "X3",
            "X4",
            "X5",
            "X6",
            "X7",
            "X8",
            "X9",
            "X10",
            "X11",
            "X12",
            "X13",
            "X14",
            "X15",
            "X16",
            "X17",
            "X18",
            "X19",
            "X20",
            "X21",
            "X22",
            "X23",
            "X24",
            "X25",
            "X26",
            "X27",
            "X28",
            "X29",
            "X30",
            "XSP",
            "V0",
            "V1",
            "V2",
            "V3"
         };
         StringBuilder var6 = new StringBuilder();

         for (String var10 : var4) {
            IEVar var11 = var3.getVariableByName(var10);
            if (var11 == null) {
               throw new RuntimeException("Cannot find register: " + var10);
            }

            String var12 = this.state.getValue(var11).toHexString();
            Strings.ff(var6, "%s=%s\n", var10, var12);
         }

         IO.writeFile(new File(var1, "registers"), Strings.encodeUTF8(var6.toString()));
      } else {
         throw new IllegalArgumentException("Limited to aarch64 for now");
      }
   }
}
