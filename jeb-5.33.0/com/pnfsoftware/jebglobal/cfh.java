package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.simulator.InsnEmulator;
import java.util.HashMap;
import java.util.Map;

public class cfh extends InsnEmulator {
   public static final cfh pC = pC(0);
   private static Map A;

   public cfh(int var1) {
      super(var1);
   }

   public boolean pC() {
      return (this.getFlags() & 131072) != 0;
   }

   public boolean A() {
      return (this.getFlags() & 262144) != 0;
   }

   public boolean kS() {
      return (this.getFlags() & 524288) != 0;
   }

   public Long pC(byte[] var1, long var2, int var4, cfj[] var5, IMachineContext var6) throws ProcessorException {
      switch (this.getFlags() & 0xFF00) {
         case 256:
            return this.pC(var2, var4, var5, var6);
         case 512:
            return this.A(var2, var4, var5, var6);
         case 768:
            return this.pC(var2, var6);
         default:
            return null;
      }
   }

   private Long pC(long var1, int var3, cfj[] var4, IMachineContext var5) {
      if (var4 != null && var4.length != 0) {
         cfj var6 = var4[var4.length - 1];
         return var6.pC(var1, var3, var5);
      } else {
         return null;
      }
   }

   private Long A(long var1, int var3, cfj[] var4, IMachineContext var5) {
      return var4 != null && var4.length >= 2 && var5 != null ? var4[0].pC(var1, var3, var5) + var4[1].getOperandValue() : null;
   }

   private Long pC(long var1, IMachineContext var3) {
      long var4 = var1 + 4L;
      return var4 + cfi.pC(var3, var4);
   }

   public static cfh pC(int var0) {
      if (A == null) {
         A = new HashMap();
      }

      cfh var1 = (cfh)A.get(var0);
      if (var1 == null) {
         var1 = new cfh(var0);
         A.put(var0, var1);
      }

      return var1;
   }
}
