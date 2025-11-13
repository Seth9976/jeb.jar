package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class LF extends Ll {
   private final gZ ld;
   private final IEncodedMemoryArea gp;
   private final IEncodedMemoryArea oT;
   private final IEncodedMemoryArea fI;
   private final gZ WR;
   private final IEncodedMemoryArea NS;
   private final IEncodedMemoryArea vP;
   private final IEncodedMemoryArea xC;
   public static final Ll.Av pC = Ll.Av.E;
   public static final Ll.Av A = Ll.Av.sY;
   public static final Ll.Av kS = Ll.Av.ys;
   public static final Ll.Av wS = Ll.Av.ld;
   public static final LF UT = pC(DirectEncodedMemoryArea.get(0, 8).shift(2));
   public static final LF E = pC(DirectEncodedMemoryArea.get(0, 8).shift(2), pC);
   public static final LF sY = pC(DirectEncodedMemoryArea.get(0, 8).shift(1));
   public static final LF ys = pC(DirectEncodedMemoryArea.get(0, 8).shift(1), pC);

   public LF(
      gZ var1,
      IEncodedMemoryArea var2,
      IEncodedMemoryArea var3,
      IEncodedMemoryArea var4,
      gZ var5,
      IEncodedMemoryArea var6,
      IEncodedMemoryArea var7,
      IEncodedMemoryArea var8,
      Ll.Av... var9
   ) {
      super(var9);
      this.ld = var1;
      this.gp = var2;
      this.oT = var4;
      this.fI = var3;
      this.WR = var5;
      this.NS = var6;
      this.vP = var7;
      this.xC = var8;
   }

   @Override
   public Yg buildOperand(byte[] var1, int var2) throws ProcessorException {
      boolean var3 = this.fI.decode(var1) != 0L;
      boolean var4 = this.oT.decode(var1) != 0L;
      boolean var5 = this.gp.decode(var1) != 0L;
      int var6 = 0;
      if (this.vP != null) {
         if (this.pC(kS)) {
            var6 = Gq.pC(var1, this.vP);
         } else {
            var6 = Gq.A(var1, this.vP);
         }

         if (this.pC(A) && !var5 && !var4 && var3) {
            return this.pC(Yg.pC(this.vP.getLength(), this.pC(var3), var6 >> 2, 2), var1, var4, var5, var2);
         }
      }

      if (this.WR == null || this.WR.A(var1) != null && !this.pC(this.WR.A(var1).intValue())) {
         if (this.WR == null && this.NS == null) {
            return this.pC(pC) && this.ld.E(var1) && var5 && !var4
               ? this.pC(var1, var3, var2, var6)
               : this.pC(this.pC(var3, var6, var4, var5), var1, var4, var5, var2);
         } else if (this.NS == null) {
            return this.pC(this.pC(var1, var2, var3), var1, var4, var5, var2);
         } else {
            int var7 = this.NS.decodeInt(var1);
            short var8 = 512;
            Z.Av var9 = this.pC(wS) ? Dj.A(var7) : Dj.pC(var7);
            if (var9 != Z.Av.pC && this.xC.decode(var1) != 0L) {
               var8 |= 2048;
            }

            return this.pC(Dj.pC(this.pC(var1, var2, var3), var9, var6, var2, var8), var1, var4, var5, var2);
         }
      } else {
         return this.pC(null, var1, var4, var5, var2);
      }
   }

   private Yg pC(boolean var1, int var2, boolean var3, boolean var4) {
      if (var2 == 0 && var1 && var4 && !var3) {
         return null;
      } else {
         return this.vP == null ? null : Yg.pC(this.vP.getLength(), this.pC(var1), var2, 65536);
      }
   }

   private Yg pC(byte[] var1, int var2, boolean var3) throws ProcessorException {
      return var3 ? this.WR.buildOperand(var1, var2) : LC.pC(this.WR.UT(var1), this.WR.A(var1), var2, this.pC(var3) == Yg.Sv.kS ? 262144 : 0);
   }

   private Yg pC(Yg var1, byte[] var2, boolean var3, boolean var4, int var5) throws ProcessorException {
      return new KH(this.A(var2, var5, var4), var1, var3, var4, var5);
   }

   private Yg A(byte[] var1, int var2, boolean var3) throws ProcessorException {
      return var3 ? this.ld.buildOperand(var1, var2) : LC.pC(this.ld.UT(var1), this.ld.A(var1), var2, 1);
   }

   private Yg pC(byte[] var1, boolean var2, int var3, int var4) throws ProcessorException {
      boolean var5 = this.oT.decode(var1) != 0L;
      boolean var6 = this.gp.decode(var1) != 0L;
      return this.pC(this.pC(var2, var4, var5, var6), var1, var5, var6, var3);
   }

   private Yg.Sv pC(boolean var1) {
      return var1 ? Yg.Sv.pC : Yg.Sv.kS;
   }

   public static LF pC(gZ var0, IEncodedMemoryArea var1, IEncodedMemoryArea var2, Ll.Av... var3) {
      return new LF(
         wT.ys,
         DirectEncodedMemoryArea.get(24, 1),
         DirectEncodedMemoryArea.get(23, 1),
         DirectEncodedMemoryArea.get(21, 1),
         var0,
         var1,
         var2,
         VirtualEncodedMemoryArea._0,
         var3
      );
   }

   public static LF pC(IEncodedMemoryArea var0, Ll.Av... var1) {
      return pC(null, null, var0, var1);
   }

   public static LF pC(gZ var0, gZ var1, IEncodedMemoryArea var2, IEncodedMemoryArea var3, Ll.Av... var4) {
      return new LF(
         var0, VirtualEncodedMemoryArea._1, VirtualEncodedMemoryArea._1, VirtualEncodedMemoryArea._0, var1, var2, var3, VirtualEncodedMemoryArea._0, var4
      );
   }

   public static LF pC(gZ var0, gZ var1, IEncodedMemoryArea var2, IEncodedMemoryArea var3, IEncodedMemoryArea var4, Ll.Av... var5) {
      return new LF(var0, VirtualEncodedMemoryArea._1, VirtualEncodedMemoryArea._1, VirtualEncodedMemoryArea._0, var1, var2, var3, var4, var5);
   }

   public static LF pC(gZ var0, gZ var1) {
      return new LF(var0, VirtualEncodedMemoryArea._1, VirtualEncodedMemoryArea._1, VirtualEncodedMemoryArea._0, var1, null, VirtualEncodedMemoryArea._0, null);
   }

   public static LF pC(gZ var0, IEncodedMemoryArea var1, Ll.Av... var2) {
      return new LF(
         var0, VirtualEncodedMemoryArea._1, VirtualEncodedMemoryArea._1, VirtualEncodedMemoryArea._0, null, null, var1, VirtualEncodedMemoryArea._0, var2
      );
   }

   public static LF pC(gZ var0, IEncodedMemoryArea var1, IEncodedMemoryArea var2, IEncodedMemoryArea var3, IEncodedMemoryArea var4, Ll.Av... var5) {
      return new LF(var0, var1, var2, var3, null, null, var4, VirtualEncodedMemoryArea._0, var5);
   }

   public static LF pC(gZ var0) {
      return pC(var0, null);
   }

   public static LF pC(gZ var0, gZ var1, IEncodedMemoryArea var2, IEncodedMemoryArea var3) {
      return new LF(var0, VirtualEncodedMemoryArea._0, VirtualEncodedMemoryArea._1, VirtualEncodedMemoryArea._0, var1, var2, VirtualEncodedMemoryArea._0, var3);
   }

   public static LF A(gZ var0, IEncodedMemoryArea var1, Ll.Av... var2) {
      return new LF(
         var0, VirtualEncodedMemoryArea._0, VirtualEncodedMemoryArea._1, VirtualEncodedMemoryArea._0, null, null, var1, VirtualEncodedMemoryArea._0, var2
      );
   }

   public static LF A(gZ var0, gZ var1) {
      return pC(var0, var1, null, null);
   }

   public static LF kS(gZ var0, IEncodedMemoryArea var1, Ll.Av... var2) {
      return new LF(
         var0, VirtualEncodedMemoryArea._1, VirtualEncodedMemoryArea._1, VirtualEncodedMemoryArea._1, null, null, var1, VirtualEncodedMemoryArea._0, var2
      );
   }

   public static LF A(gZ var0) {
      return kS(var0, null);
   }

   public gZ A() {
      return this.ld;
   }

   public gZ kS() {
      return this.WR;
   }

   public boolean pC(byte[] var1) {
      return this.gp.decodeInt(var1) == 0 || this.oT.decodeInt(var1) != 0;
   }

   public boolean A(byte[] var1) {
      return this.ld != null && this.ld.E(var1) && this.pC(var1);
   }

   @Override
   public String kS(byte[] var1) {
      return this.A(var1) ? "LDR instruction try to write back to PC." : super.kS(var1);
   }
}
