package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class NS extends Ll {
   private final NS.Av ld;
   private final Yg.Sv gp;
   private final int oT;
   public static final IEncodedMemoryArea pC = new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(26, 1), DirectEncodedMemoryArea.get(12, 3));
   public static final IEncodedMemoryArea A = new EncodedMemoryAreaList(
      DirectEncodedMemoryArea.get(26, 1), DirectEncodedMemoryArea.get(12, 3), DirectEncodedMemoryArea.get(0, 8)
   );
   public static final Hu kS = new NS(NS.Av.pC, Ll.Av.wS, 0);
   public static final Hu wS = new NS(NS.Av.pC, Ll.Av.wS, 35651584);
   public static final Hu UT = new NS(NS.Av.pC, Yg.Sv.A, Ll.Av.wS, 0);
   public static final Hu E = new NS(NS.Av.pC, Yg.Sv.kS, Ll.Av.wS, 35651584);
   public static final Hu sY = new NS(NS.Av.A, Ll.Av.wS, 0);
   public static final Hu ys = new NS(NS.Av.A, Yg.Sv.A, Ll.Av.wS, 0);

   private NS(NS.Av var1, Yg.Sv var2, Ll.Av var3, int var4) {
      super(var3);
      this.ld = var1;
      this.gp = var2;
      this.oT = var4;
   }

   private NS(NS.Av var1, Ll.Av var2, int var3) {
      this(var1, Yg.Sv.pC, var2, var3);
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   protected long pC(byte[] var1) {
      long var2 = switch (this.ld) {
         case A -> {
            int var6 = var1[3] & 255;
            int var7 = Gq.A(var1, pC);
            yield Gq.kS(var7, var6);
         }
         case pC -> {
            int var4 = var1[2] & 15;
            int var5 = var1[3] & 255;
            yield Gq.A(var4, var5);
         }
         default -> 0L;
      };
      if (this.gp == Yg.Sv.A) {
         var2 = (int)var2 & 4294967295L;
      }

      return var2;
   }

   @Override
   public Yg buildOperand(byte[] var1, int var2) {
      long var3 = this.pC(var1);
      if ((this.oT & 2097152) != 0) {
         if (this.gp != null && this.gp == Yg.Sv.kS) {
            var3 = -var3;
         }

         return new cv(var3, var2, (this.oT & 33554432) != 0, false);
      } else {
         if (this.ld == NS.Av.pC) {
            int var5 = var1[2] & 15;
            int var6 = var1[3] & 255;
            if (var5 != 0 && var3 == var6 >> 2 * var5) {
               return new Bc((int)var3, 768, this.pC(), this.gp, this.A(), Yg.pC(this.A(), this.gp, var6, 65536), Yg.pC(this.A(), (long)(2 * var5)));
            }
         }

         return Yg.pC(this.A(), this.gp, var3, this.pC());
      }
   }

   protected int A() {
      int var10000 = jd.pC[this.ld.ordinal()];
      return 32;
   }

   public static enum Av {
      pC,
      A;
   }
}
