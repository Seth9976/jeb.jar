package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class BS extends gZ {
   private final IEncodedMemoryArea Bf;
   private final int Pe;
   private static final IEncodedMemoryArea ck = DirectEncodedMemoryArea.get(6, 1);
   private static final IEncodedMemoryArea RW = VirtualEncodedMemoryArea._1;
   public static final IEncodedMemoryArea pC = VirtualEncodedMemoryArea._0;
   public static final IEncodedMemoryArea A = null;
   public static final IEncodedMemoryArea kS = DirectEncodedMemoryArea.get(22, 1);
   public static final IEncodedMemoryArea wS = DirectEncodedMemoryArea.get(12, 4);
   public static final IEncodedMemoryArea UT = new EncodedMemoryAreaList(kS, wS);
   public static final IEncodedMemoryArea E = DirectEncodedMemoryArea.get(7, 1);
   public static final IEncodedMemoryArea sY = DirectEncodedMemoryArea.get(16, 4);
   public static final IEncodedMemoryArea ys = new EncodedMemoryAreaList(E, sY);
   public static final IEncodedMemoryArea ld = DirectEncodedMemoryArea.get(5, 1);
   public static final IEncodedMemoryArea gp = DirectEncodedMemoryArea.get(0, 4);
   public static final IEncodedMemoryArea oT = DirectEncodedMemoryArea.get(0, 3);
   public static final IEncodedMemoryArea fI = DirectEncodedMemoryArea.get(3, 1);
   public static final IEncodedMemoryArea WR = new EncodedMemoryAreaList(ld, gp);
   public static final BS NS = new BS(UT, ck);
   public static final BS vP = new BS(ys, ck);
   public static final BS xC = new BS(WR, ck);
   public static final Hu[] ED = new Hu[]{NS, xC};
   public static final Hu[] Sc = new Hu[]{NS, vP, xC};
   public static final BS ah = new BS(UT, RW);
   public static final BS eP = new BS(ys, RW);
   public static final BS UO = new BS(WR, RW);
   public static final Hu[] Ab = new Hu[]{ah, UO};
   public static final Hu[] rl = new Hu[]{ah, eP, UO};
   public static final BS z = new BS(UT, pC);
   public static final BS Ek = new BS(ys, pC);
   public static final BS hK = new BS(WR, pC);
   public static final BS Er = new BS(oT, pC);
   public static final BS FE = new BS(gp, pC);
   public static final Hu[] Aj = new Hu[]{z, hK};
   public static final Hu[] EX = new Hu[]{z, Ek, hK};
   public static final IEncodedMemoryArea LM = new EncodedMemoryAreaList(wS, kS);
   private static final IEncodedMemoryArea e = new EncodedMemoryAreaList(sY, E);
   private static final IEncodedMemoryArea xM = new EncodedMemoryAreaList(gp, ld);
   public static final Hu mv = new BS(LM, A);
   public static final Hu sO = new BS(e, A);
   public static final Hu os = new BS(xM, A);
   public static final Hu Cu = new BS(xM, A, 1);
   public static final Hu[] hZ = new Hu[]{mv, os};
   public static final Hu[] UW = new Hu[]{mv, sO, os};
   public static final Hu[] PR = new Hu[]{mv, hK};
   public static final Hu[] cX = new Hu[]{z, os};
   public static final BS DQ = new BS(UT, pC, 1);
   public static final BS ZN = new BS(UT, pC, 2);
   public static final BS OB = new BS(UT, pC, 3);
   public static final BS pF = new BS(UT, pC, 4);
   private static final BS kU = new BS(UT, pC, 5);
   public static final BS Bc = new BS(UT, pC, 6);
   private static final BS[] Kq = new BS[]{z, DQ, ZN, OB, pF, kU, Bc};
   private static final BS go = new BS(ys, pC, 1);
   private static final BS JF = new BS(ys, pC, 2);
   private static final BS Nq = new BS(ys, pC, 3);
   private static final BS pg = new BS(ys, pC, 4);
   private static final BS gj = new BS(ys, pC, 5);
   private static final BS ZD = new BS(ys, pC, 6);
   private static final BS[] DL = new BS[]{Ek, go, JF, Nq, pg, gj, ZD};
   public static final gZ OI = new wT(8, DirectEncodedMemoryArea.get(16, 4), Ll.Av.pC);

   public BS(IEncodedMemoryArea var1, IEncodedMemoryArea var2) {
      this(var1, var2, 0);
   }

   private BS(IEncodedMemoryArea var1, IEncodedMemoryArea var2, int var3) {
      super(8388614, Ll.Av.pC, var1, 31);
      this.Bf = var2;
      this.Pe = var3;
   }

   @Override
   public Integer A(byte[] var1) {
      Integer var2 = super.A(var1);
      if (var2 == null) {
         return null;
      } else {
         if (this.Bf == null) {
            var2 = var2 + this.Pe;
         } else {
            int var3 = this.Bf.decodeInt(var1);
            if (var3 == 0) {
               var2 = var2 + this.Pe;
            } else {
               var2 = var2 / 2;
               var2 = var2 + this.Pe;
            }
         }

         return var2;
      }
   }

   @Override
   public int UT(byte[] var1) {
      if (this.Bf == null) {
         return 2097158;
      } else {
         int var2 = this.Bf.decodeInt(var1);
         return var2 == 0 ? 4194310 : 8388614;
      }
   }

   @Override
   public boolean wS(byte[] var1) {
      if (!super.wS(var1)) {
         return false;
      } else {
         Integer var2 = super.A(var1);
         if (var2 == null) {
            return true;
         } else if (var2 + this.Pe > 31) {
            return false;
         } else {
            if (this.Bf != null) {
               int var3 = this.Bf.decodeInt(var1);
               if (var3 != 0 && var2 % 2 != 0) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public static BS pC(int var0) {
      if (var0 >= DL.length) {
         throw new IllegalArgumentException("Unexpected x value for Dn");
      } else {
         return DL[var0];
      }
   }

   public static BS A(int var0) {
      if (var0 >= Kq.length) {
         throw new IllegalArgumentException("Unexpected x value for Dd");
      } else {
         return Kq[var0];
      }
   }

   @Override
   public boolean E(byte[] var1) {
      return false;
   }
}
