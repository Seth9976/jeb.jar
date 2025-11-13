package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class Dj implements Hu {
   public static final IEncodedMemoryArea pC = VirtualEncodedMemoryArea._00;
   private final Hu oT;
   private final IEncodedMemoryArea fI;
   private final IEncodedMemoryArea WR;
   private final Hu NS;
   private int vP;
   private static final IEncodedMemoryArea xC = new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(12, 3), DirectEncodedMemoryArea.get(6, 2));
   public static final Dj A = new Dj(wT.pC, DirectEncodedMemoryArea.get(5, 2), DirectEncodedMemoryArea.get(7, 5), 512);
   public static final Dj kS = new Dj(wT.pC, DirectEncodedMemoryArea.get(5, 2), wT.wS);
   public static final Dj wS = new Dj(wT.pC, DirectEncodedMemoryArea.get(4, 2), xC, 512);
   public static final Dj UT = new Dj(LY.A, DirectEncodedMemoryArea.get(11, 2), DirectEncodedMemoryArea.get(6, 5));
   public static final Dj E = new Dj(wT.pC, DirectEncodedMemoryArea.get(5, 2), DirectEncodedMemoryArea.get(7, 5));
   public static final Hu sY = new Dj(wT.pC, pC, DirectEncodedMemoryArea.get(4, 1));
   public static final Hu ys = new Dj(wT.pC, VirtualEncodedMemoryArea.get(3, 2), DirectEncodedMemoryArea.get(4, 2).shift(3));
   public static final Hu ld = new Dj(wT.ys, DirectEncodedMemoryArea.getThumb2(4, 1, 2), xC, 512);
   public static final Hu gp = new Dj(sQ.LM, DirectEncodedMemoryArea.get(22, 2), DirectEncodedMemoryArea.get(10, 6), 1280);

   protected static Z.Av pC(int var0) {
      switch (var0) {
         case 0:
            return Z.Av.pC;
         case 1:
            return Z.Av.A;
         case 2:
            return Z.Av.kS;
         case 3:
            return Z.Av.wS;
         default:
            return var0 < Z.Av.values().length ? Z.Av.values()[var0] : null;
      }
   }

   protected static Z.Av A(int var0) {
      switch (var0) {
         case 2:
            return Z.Av.ld;
         case 3:
            return Z.Av.pC;
         case 4:
         case 5:
         default:
            return null;
         case 6:
            return Z.Av.WR;
         case 7:
            return Z.Av.NS;
      }
   }

   public static Yg pC(Yg var0, Z.Av var1, int var2, int var3, int var4) {
      if ((var4 & 512) != 0 && var1 == Z.Av.wS && var2 == 0) {
         var1 = Z.Av.UT;
      }

      if (var2 == 0 && (var4 & 2048) == 0) {
         if (var1 == Z.Av.pC || var1 == Z.Av.wS && (var4 & 1024) == 0) {
            return var0;
         }

         if ((var4 & 256) == 0 && (var1 == Z.Av.A || var1 == Z.Av.kS)) {
            var2 = 32;
         }

         if (var1 == Z.Av.WR || var1 == Z.Av.NS || var1 == Z.Av.ld) {
            return ZV.pC(var0, var1);
         }
      }

      return ZV.pC(var0, var1, var2);
   }

   public Dj(Hu var1, IEncodedMemoryArea var2, IEncodedMemoryArea var3) {
      this(var1, var2, var3, 0);
   }

   public Dj(Hu var1, IEncodedMemoryArea var2, IEncodedMemoryArea var3, int var4) {
      this.oT = var1;
      this.fI = var2;
      this.WR = var3;
      this.NS = null;
      this.vP = var4;
   }

   public Dj(Hu var1, IEncodedMemoryArea var2, Hu var3) {
      this.oT = var1;
      this.fI = var2;
      this.NS = var3;
      this.WR = null;
   }

   @Override
   public Yg buildOperand(byte[] var1, int var2) throws ProcessorException {
      Yg var3 = this.oT.buildOperand(var1, var2);
      Z.Av var4 = pC(this.fI.decodeInt(var1));
      if (this.WR != null) {
         int var6 = Gq.A(var1, this.WR);
         return pC(var3, var4, var6, var2, this.vP);
      } else {
         Yg var5 = this.NS.buildOperand(var1, var2);
         return ZV.pC(var3, var4, var5);
      }
   }
}
