package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.FunctionEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class YH extends rR {
   private static final int WI = 5;
   private static final IEncodedMemoryArea Tq = DirectEncodedMemoryArea.get(0, 5);
   private static final IEncodedMemoryArea Yp = DirectEncodedMemoryArea.get(5, 5);
   private static final IEncodedMemoryArea Gu = DirectEncodedMemoryArea.get(10, 5);
   private static final IEncodedMemoryArea nY = DirectEncodedMemoryArea.get(16, 5);
   private static final jD.eo lF = jD.eo.oW;
   private static final jD.eo nq = jD.eo.gO;
   private final IEncodedMemoryArea NX;
   private static final IEncodedMemoryArea br = DirectEncodedMemoryArea.get(31, 1);
   public static final YH Dw = new YH(0, Tq, jD.eo.JY);
   public static final YH Uv = new YH(0, Tq);
   public static final YH oW = new YH(0, Tq, lF);
   public static final YH gO = q(0, Tq);
   public static final YH nf = new YH(0, Yp);
   public static final YH gP = new YH(0, Yp, jD.eo.zz);
   public static final YH za = new YH(0, Yp, jD.eo.RF);
   public static final YH lm = new YH(0, Gu);
   public static final YH zz = new YH(0, nY);
   public static final YH JY = new YH(0, nY, lF);
   public static final YH HF = q(0, nY);
   public static final YH LK = new YH(0, nY, jD.eo.JY);
   public static final YH io = new YH(0, Tq, nq);
   public static final YH qa = new YH(0, Yp, nq);
   public static final YH Hk = new YH(0, nY, nq);
   public static final YH Me = new YH(0, Yp, jD.eo.xK);
   public static final YH PV = new YH(2097152, Tq);
   public static final YH oQ = new YH(2097152, Tq, lF);
   public static final YH xW = q(2097152, Tq);
   public static final YH KT = new YH(2097152, Yp);
   public static final YH Gf = new YH(2097152, Gu);
   public static final YH Ef = new YH(2097152, nY);
   public static final YH cC = new YH(2097152, nY, lF);
   public static final YH sH = q(2097152, nY);
   public static final YH CE = new YH(2097152, Tq, nq);
   public static final YH wF = new YH(2097152, Yp, nq);
   public static final YH If = new YH(2097152, nY, nq);
   public static final YH Dz = new YH(br, Tq);
   public static final YH mI = new YH(br, Yp);
   public static final YH jq = new YH(br, Gu);
   public static final YH ui = new YH(br, nY);
   public static final YH TX = new YH(DirectEncodedMemoryArea.get(30, 1), Tq);
   public static final YH Rr = new YH(DirectEncodedMemoryArea.get(22, 2), Tq);
   public static final YH EB = new YH(DirectEncodedMemoryArea.get(12, 1), Yp);
   public static final YH Xo = new YH(DirectEncodedMemoryArea.get(22, 2), Yp);
   public static final YH Bu = new YH(DirectEncodedMemoryArea.get(12, 1), nY);
   public static final YH IN = new YH(DirectEncodedMemoryArea.get(13, 1), nY);
   public static final YH rL = new YH(DirectEncodedMemoryArea.get(30, 1), nY);
   public static final YH eJ = new YH(DirectEncodedMemoryArea.get(22, 2), nY);
   public static final YH YN = new YH(br, Tq, nq);
   public static final YH Rv = new YH(DirectEncodedMemoryArea.get(22, 2), Tq, nq);
   public static final YH zx = new YH(br, Yp, nq);
   public static final YH ZT = new YH(DirectEncodedMemoryArea.get(22, 2), Yp, nq);
   public static final YH Ri = new YH(br, nY, nq);
   public static final YH GY = new YH(2097152, new FunctionEncodedMemoryArea(5, var0 -> 12L + DirectEncodedMemoryArea.get(13, 2).decode(var0)));
   public static final YH Wx = new YH(2097152, new FunctionEncodedMemoryArea(5, var0 -> 12L + DirectEncodedMemoryArea.get(16, 2).decode(var0)));
   public static final rR AB = new YH(10, VirtualEncodedMemoryArea.get(0, 5));
   public static final rR CY = new YH(0, VirtualEncodedMemoryArea.get(16, 5));

   public YH(int var1, IEncodedMemoryArea var2) {
      this(var1, var2, jD.eo.q);
   }

   public YH(int var1, IEncodedMemoryArea var2, jD.eo var3) {
      this(var1, var2, var3, 33);
   }

   protected YH(int var1, IEncodedMemoryArea var2, jD.eo var3, int var4) {
      super(var1, var3, var2, var4);
      this.NX = null;
   }

   private YH(IEncodedMemoryArea var1, IEncodedMemoryArea var2) {
      this(var1, var2, jD.eo.q);
   }

   public YH(IEncodedMemoryArea var1, IEncodedMemoryArea var2, jD.eo var3) {
      super(0, var3, var2, 33);
      this.NX = var1;
   }

   private static YH q(int var0, IEncodedMemoryArea var1) {
      return new YH(var0, dX.q(var1), jD.eo.q, 33);
   }

   @Override
   public boolean Dw(byte[] var1) {
      if (this.q(lF)) {
         Integer var2 = this.RF(var1);
         if (var2 == null) {
            return super.Dw(var1);
         }

         boolean var3 = (var2 & 1) == 0;
         if (!var3) {
            return false;
         }
      }

      return super.Dw(var1);
   }

   @Override
   public Integer RF(byte[] var1) {
      Integer var2 = super.RF(var1);
      if (var2 == null) {
         return null;
      } else {
         int var3 = this.Uv(var1);
         if (this.q(var3, var2)) {
            var2 = 33;
         }

         return var2;
      }
   }

   private boolean q(int var1, int var2) {
      return (var1 == 2097152 || var1 == 0) && var2 == 31 ? !this.q(nq) && !this.q(jD.eo.xK) : false;
   }

   @Override
   public int Uv(byte[] var1) {
      if (this.NX != null) {
         int var2 = this.NX.decodeInt(var1);
         if (this.NX.getLength() == 1) {
            return var2 == 0 ? 2097152 : 0;
         } else {
            return var2 == 3 ? 0 : 2097152;
         }
      } else {
         return super.Uv(var1);
      }
   }

   @Override
   public boolean oW(byte[] var1) {
      return this.Uv(var1) == 10;
   }
}
