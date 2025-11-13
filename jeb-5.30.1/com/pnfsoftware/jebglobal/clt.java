package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.simulator.InsnEmulator;
import java.util.HashMap;
import java.util.Map;

public class clt extends InsnEmulator {
   public static final int q = 512;
   public static final int RF = 768;
   public static final int xK = 131072;
   public static final int Dw = 262144;
   public static final int Uv = 524288;
   public static final clt oW = q(0);
   private static Map gO;

   public clt(int var1) {
      super(var1);
   }

   public boolean q() {
      return (this.getFlags() & 131072) != 0;
   }

   public boolean RF() {
      return (this.getFlags() & 262144) != 0;
   }

   public boolean xK() {
      return (this.getFlags() & 524288) != 0;
   }

   public Long q(byte[] var1, long var2, int var4, clv[] var5, IMachineContext var6) throws ProcessorException {
      switch (this.getFlags() & 0xFF00) {
         case 256:
            return this.q(var2, var4, var5, var6);
         case 512:
            return this.RF(var2, var4, var5, var6);
         case 768:
            return this.q(var2, var6);
         default:
            return null;
      }
   }

   private Long q(long var1, int var3, clv[] var4, IMachineContext var5) {
      if (var4 != null && var4.length != 0) {
         clv var6 = var4[var4.length - 1];
         return var6.q(var1, var3, var5);
      } else {
         return null;
      }
   }

   private Long RF(long var1, int var3, clv[] var4, IMachineContext var5) {
      return var4 != null && var4.length >= 2 && var5 != null ? var4[0].q(var1, var3, var5) + var4[1].getOperandValue() : null;
   }

   private Long q(long var1, IMachineContext var3) {
      long var4 = var1 + 4L;
      return var4 + clu.q(var3, var4);
   }

   public static clt q(int var0) {
      if (gO == null) {
         gO = new HashMap();
      }

      clt var1 = (clt)gO.get(var0);
      if (var1 == null) {
         var1 = new clt(var0);
         gO.put(var0, var1);
      }

      return var1;
   }
}
