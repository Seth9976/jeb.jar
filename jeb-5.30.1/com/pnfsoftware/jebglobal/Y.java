package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class Y extends rR {
   private final IEncodedMemoryArea Wx;
   private final int AB;
   private static final IEncodedMemoryArea CY = DirectEncodedMemoryArea.get(6, 1);
   private static final IEncodedMemoryArea WI = VirtualEncodedMemoryArea._1;
   public static final IEncodedMemoryArea Dw = VirtualEncodedMemoryArea._0;
   public static final IEncodedMemoryArea Uv = null;
   public static final IEncodedMemoryArea oW = DirectEncodedMemoryArea.get(22, 1);
   public static final IEncodedMemoryArea gO = DirectEncodedMemoryArea.get(12, 4);
   public static final IEncodedMemoryArea nf = new EncodedMemoryAreaList(oW, gO);
   public static final IEncodedMemoryArea gP = DirectEncodedMemoryArea.get(7, 1);
   public static final IEncodedMemoryArea za = DirectEncodedMemoryArea.get(16, 4);
   public static final IEncodedMemoryArea lm = new EncodedMemoryAreaList(gP, za);
   public static final IEncodedMemoryArea zz = DirectEncodedMemoryArea.get(5, 1);
   public static final IEncodedMemoryArea JY = DirectEncodedMemoryArea.get(0, 4);
   public static final IEncodedMemoryArea HF = DirectEncodedMemoryArea.get(0, 3);
   public static final IEncodedMemoryArea LK = DirectEncodedMemoryArea.get(3, 1);
   public static final IEncodedMemoryArea io = new EncodedMemoryAreaList(zz, JY);
   public static final Y qa = new Y(nf, CY);
   public static final Y Hk = new Y(lm, CY);
   public static final Y Me = new Y(io, CY);
   public static final Ef[] PV = new Ef[]{qa, Me};
   public static final Ef[] oQ = new Ef[]{qa, Hk, Me};
   public static final Y xW = new Y(nf, WI);
   public static final Y KT = new Y(lm, WI);
   public static final Y Gf = new Y(io, WI);
   public static final Ef[] Ef = new Ef[]{xW, Gf};
   public static final Ef[] cC = new Ef[]{xW, KT, Gf};
   public static final Y sH = new Y(nf, Dw);
   public static final Y CE = new Y(lm, Dw);
   public static final Y wF = new Y(io, Dw);
   public static final Y If = new Y(HF, Dw);
   public static final Y Dz = new Y(JY, Dw);
   public static final Ef[] mI = new Ef[]{sH, wF};
   public static final Ef[] jq = new Ef[]{sH, CE, wF};
   public static final IEncodedMemoryArea ui = new EncodedMemoryAreaList(gO, oW);
   private static final IEncodedMemoryArea Tq = new EncodedMemoryAreaList(za, gP);
   private static final IEncodedMemoryArea Yp = new EncodedMemoryAreaList(JY, zz);
   public static final Ef TX = new Y(ui, Uv);
   public static final Ef Rr = new Y(Tq, Uv);
   public static final Ef EB = new Y(Yp, Uv);
   public static final Ef Xo = new Y(Yp, Uv, 1);
   public static final Ef[] Bu = new Ef[]{TX, EB};
   public static final Ef[] IN = new Ef[]{TX, Rr, EB};
   public static final Ef[] rL = new Ef[]{TX, wF};
   public static final Ef[] eJ = new Ef[]{sH, EB};
   public static final Y YN = new Y(nf, Dw, 1);
   public static final Y Rv = new Y(nf, Dw, 2);
   public static final Y zx = new Y(nf, Dw, 3);
   public static final Y ZT = new Y(nf, Dw, 4);
   private static final Y Gu = new Y(nf, Dw, 5);
   public static final Y Ri = new Y(nf, Dw, 6);
   private static final Y[] nY = new Y[]{sH, YN, Rv, zx, ZT, Gu, Ri};
   private static final Y lF = new Y(lm, Dw, 1);
   private static final Y nq = new Y(lm, Dw, 2);
   private static final Y NX = new Y(lm, Dw, 3);
   private static final Y br = new Y(lm, Dw, 4);
   private static final Y tW = new Y(lm, Dw, 5);
   private static final Y ZA = new Y(lm, Dw, 6);
   private static final Y[] Ov = new Y[]{CE, lF, nq, NX, br, tW, ZA};
   public static final rR GY = new Pc(8, DirectEncodedMemoryArea.get(16, 4), jD.eo.q);

   public Y(IEncodedMemoryArea var1, IEncodedMemoryArea var2) {
      this(var1, var2, 0);
   }

   private Y(IEncodedMemoryArea var1, IEncodedMemoryArea var2, int var3) {
      super(8388614, jD.eo.q, var1, 31);
      this.Wx = var2;
      this.AB = var3;
   }

   @Override
   public Integer RF(byte[] var1) {
      Integer var2 = super.RF(var1);
      if (var2 == null) {
         return null;
      } else {
         if (this.Wx == null) {
            var2 = var2 + this.AB;
         } else {
            int var3 = this.Wx.decodeInt(var1);
            if (var3 == 0) {
               var2 = var2 + this.AB;
            } else {
               var2 = var2 / 2;
               var2 = var2 + this.AB;
            }
         }

         return var2;
      }
   }

   @Override
   public int Uv(byte[] var1) {
      if (this.Wx == null) {
         return 2097158;
      } else {
         int var2 = this.Wx.decodeInt(var1);
         return var2 == 0 ? 4194310 : 8388614;
      }
   }

   @Override
   public boolean Dw(byte[] var1) {
      if (!super.Dw(var1)) {
         return false;
      } else {
         Integer var2 = super.RF(var1);
         if (var2 == null) {
            return true;
         } else if (var2 + this.AB > 31) {
            return false;
         } else {
            if (this.Wx != null) {
               int var3 = this.Wx.decodeInt(var1);
               if (var3 != 0 && var2 % 2 != 0) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public static Y q(int var0) {
      if (var0 >= Ov.length) {
         throw new IllegalArgumentException("Unexpected x value for Dn");
      } else {
         return Ov[var0];
      }
   }

   public static Y RF(int var0) {
      if (var0 >= nY.length) {
         throw new IllegalArgumentException("Unexpected x value for Dd");
      } else {
         return nY[var0];
      }
   }

   @Override
   public boolean oW(byte[] var1) {
      return false;
   }
}
