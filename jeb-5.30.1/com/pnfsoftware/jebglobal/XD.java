package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class XD extends rR {
   private static final int lF = 5;
   private static final int nq = 524294;
   private static final int NX = 1048582;
   private static final int br = 2097158;
   private static final int tW = 4194310;
   private static final int ZA = 8388614;
   public static final int Dw = 8388615;
   private static final IEncodedMemoryArea Ov = DirectEncodedMemoryArea.get(0, 5);
   private static final IEncodedMemoryArea Lj = DirectEncodedMemoryArea.get(5, 5);
   private static final IEncodedMemoryArea nv = DirectEncodedMemoryArea.get(10, 5);
   private static final IEncodedMemoryArea LL = DirectEncodedMemoryArea.get(16, 5);
   private final Dm PQ;
   private final dD fQ;
   public static final IEncodedMemoryArea Uv = DirectEncodedMemoryArea.get(22, 2);
   public static final IEncodedMemoryArea oW = DirectEncodedMemoryArea.get(22, 1);
   public static final dD gO = new dD(Uv, 524294, 1048582, 2097158, 4194310);
   private static final dD fi = new dD(Uv, null, null, null, 4194310);
   private static final dD bl = new dD(Uv, 524294, 1048582, 2097158);
   private static final dD jb = new dD(Uv, null, 1048582, 2097158);
   private static final dD pQ = new dD(Uv, 1048582, 2097158, 4194310);
   private static final dD kf = new dD(Uv, 1048582, null, 2097158, 4194310);
   private static final dD GM = new dD(Uv, 2097158, 4194310, null, 1048582);
   private static final dD TQ = new dD(oW, 2097158, 4194310);
   public static final XD nf = new XD(524294, Ov);
   public static final XD gP = new XD(1048582, Ov);
   public static final XD za = new XD(1048582, Lj);
   public static final XD lm = new XD(2097158, Ov);
   public static final XD zz = new XD(2097158, Lj);
   public static final XD JY = new XD(2097158, nv);
   public static final XD HF = new XD(4194310, Ov);
   public static final XD LK = new XD(4194310, Lj);
   public static final XD io = new XD(4194310, nv);
   public static final XD qa = new XD(TQ, Ov);
   public static final XD Hk = new XD(TQ, Lj);
   public static final XD Me = new XD(TQ, nv);
   public static final XD PV = new XD(TQ, LL);
   public static final XD oQ = new XD(gO, Ov);
   public static final XD xW = new XD(gO, Lj);
   public static final XD KT = new XD(gO, LL);
   public static final XD Gf = new XD(bl, Ov);
   public static final XD Ef = new XD(bl, Lj);
   public static final XD cC = new XD(pQ, Ov);
   public static final XD sH = new XD(pQ, Lj);
   public static final XD CE = new XD(fi, Ov);
   public static final XD wF = new XD(fi, Lj);
   public static final XD If = new XD(fi, LL);
   public static final XD Dz = new XD(jb, Ov);
   public static final XD mI = new XD(jb, Lj);
   public static final XD jq = new XD(jb, LL);
   public static final XD ui = new XD(kf, Ov);
   public static final XD TX = new XD(kf, Lj);
   public static final XD Rr = new XD(kf, LL);
   public static final XD EB = new XD(new dD(oW, null, 2097158), Ov);
   public static final XD Xo = new XD(new dD(oW, null, 4194310), Lj);
   public static final XD Bu = new XD(8388614, Ov);
   public static final XD IN = new XD(8388614, Lj);
   public static final XD rL = new XD(8388614, nv);
   public static final XD eJ = new XD(8388615, Ov);
   public static final XD YN = new XD(GM, Ov);
   public static final XD Rv = new XD(GM, Lj);
   private static final IEncodedMemoryArea Yw = new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(21, 1), oW);
   private static final dD IY = new dD(Yw, null, 1048582, 2097158, 4194310);
   public static final XD zx = new XD(IY, Ov);
   public static final XD ZT = new XD(IY, Lj);
   public static final XD Ri = new XD(IY, LL);
   public static final IEncodedMemoryArea GY = new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(19, 1), oW);
   public static final dD Wx = new dD(GY, 2097158, 4194310, 1048582, 1048582);
   public static final XD AB = new XD(Wx, Ov);
   public static final XD CY = new XD(Wx, Lj);
   public static final IEncodedMemoryArea WI = DirectEncodedMemoryArea.get(20, 3);
   private static final dD qR = new dD(WI, null, 1048582, 2097158, 2097158, 4194310, 4194310, 4194310, 4194310);
   public static final XD Tq = new XD(qR, Ov);
   public static final XD Yp = new XD(qR, Lj);
   public static final IEncodedMemoryArea Gu = new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(29, 1), oW);
   private static final dD YA = new dD(Gu, 1048582, null, 2097158, 4194310);
   public static final XD nY = new XD(YA, Ov);

   private XD(int var1, IEncodedMemoryArea var2) {
      this(var1, var2, null);
   }

   public XD(int var1, IEncodedMemoryArea var2, Dm var3) {
      super(var1, jD.eo.q, var2, 31);
      this.fQ = null;
      this.PQ = var3;
   }

   public XD(dD var1, IEncodedMemoryArea var2) {
      this(var1, var2, null);
   }

   private XD(dD var1, IEncodedMemoryArea var2, Dm var3) {
      super(0, jD.eo.q, var2, 31);
      this.fQ = var1;
      this.PQ = var3;
   }

   @Override
   public int Uv(byte[] var1) {
      if (this.fQ != null) {
         Integer var2 = (Integer)this.fQ.RF(var1);
         if (var2 != null && var2 != 0) {
            return var2;
         }
      }

      return super.Uv(var1);
   }

   @Override
   public boolean Dw(byte[] var1) {
      if (!super.Dw(var1)) {
         return false;
      } else {
         Integer var2 = super.RF(var1);
         if (var2 == null) {
            return true;
         } else {
            if (this.fQ != null) {
               Integer var3 = (Integer)this.fQ.RF(var1);
               if (var3 == null) {
                  return false;
               }
            }

            if (this.PQ != null) {
               try {
                  CharSequence var5 = this.PQ.getDataType(var1);
                  if (var5 == null) {
                     return false;
                  }
               } catch (eK var4) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   @Override
   public CW buildOperand(byte[] var1, int var2) throws ProcessorException {
      Integer var3 = this.RF(var1);
      if (var3 == null) {
         return null;
      } else if (this.PQ == null) {
         return GC.q(this.Uv(var1), var3, var2, this.q());
      } else {
         try {
            CharSequence var4 = this.PQ.getDataType(var1);
            if (var4 != null) {
               return new RI(this.Uv(var1), var3, var2, this.q(), var4.toString());
            } else {
               throw new ProcessorException("Illegal datatype");
            }
         } catch (eK var5) {
            throw new ProcessorException(var5);
         }
      }
   }

   @Override
   public boolean oW(byte[] var1) {
      return false;
   }

   public static XD q(int var0, Dm var1) {
      return new XD(8388615, DirectEncodedMemoryArea.get(var0, 5), var1);
   }
}
