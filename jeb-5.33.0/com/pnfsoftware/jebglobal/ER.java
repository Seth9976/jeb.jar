package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class ER extends gZ {
   private static final IEncodedMemoryArea Kq = DirectEncodedMemoryArea.get(0, 5);
   private static final IEncodedMemoryArea go = DirectEncodedMemoryArea.get(5, 5);
   private static final IEncodedMemoryArea JF = DirectEncodedMemoryArea.get(10, 5);
   private static final IEncodedMemoryArea Nq = DirectEncodedMemoryArea.get(16, 5);
   private final IX pg;
   private final ZW gj;
   public static final IEncodedMemoryArea pC = DirectEncodedMemoryArea.get(22, 2);
   public static final IEncodedMemoryArea A = DirectEncodedMemoryArea.get(22, 1);
   public static final ZW kS = new ZW(pC, 524294, 1048582, 2097158, 4194310);
   private static final ZW ZD = new ZW(pC, null, null, null, 4194310);
   private static final ZW DL = new ZW(pC, 524294, 1048582, 2097158);
   private static final ZW UH = new ZW(pC, null, 1048582, 2097158);
   private static final ZW VD = new ZW(pC, 1048582, 2097158, 4194310);
   private static final ZW Xs = new ZW(pC, 1048582, null, 2097158, 4194310);
   private static final ZW KV = new ZW(pC, 2097158, 4194310, null, 1048582);
   private static final ZW FK = new ZW(A, 2097158, 4194310);
   public static final ER wS = new ER(524294, Kq);
   public static final ER UT = new ER(1048582, Kq);
   public static final ER E = new ER(1048582, go);
   public static final ER sY = new ER(2097158, Kq);
   public static final ER ys = new ER(2097158, go);
   public static final ER ld = new ER(2097158, JF);
   public static final ER gp = new ER(4194310, Kq);
   public static final ER oT = new ER(4194310, go);
   public static final ER fI = new ER(4194310, JF);
   public static final ER WR = new ER(FK, Kq);
   public static final ER NS = new ER(FK, go);
   public static final ER vP = new ER(FK, JF);
   public static final ER xC = new ER(FK, Nq);
   public static final ER ED = new ER(kS, Kq);
   public static final ER Sc = new ER(kS, go);
   public static final ER ah = new ER(kS, Nq);
   public static final ER eP = new ER(DL, Kq);
   public static final ER UO = new ER(DL, go);
   public static final ER Ab = new ER(VD, Kq);
   public static final ER rl = new ER(VD, go);
   public static final ER z = new ER(ZD, Kq);
   public static final ER Ek = new ER(ZD, go);
   public static final ER hK = new ER(ZD, Nq);
   public static final ER Er = new ER(UH, Kq);
   public static final ER FE = new ER(UH, go);
   public static final ER Aj = new ER(UH, Nq);
   public static final ER EX = new ER(Xs, Kq);
   public static final ER LM = new ER(Xs, go);
   public static final ER mv = new ER(Xs, Nq);
   public static final ER sO = new ER(new ZW(A, null, 2097158), Kq);
   public static final ER os = new ER(new ZW(A, null, 4194310), go);
   public static final ER Cu = new ER(8388614, Kq);
   public static final ER hZ = new ER(8388614, go);
   public static final ER UW = new ER(8388614, JF);
   public static final ER PR = new ER(8388615, Kq);
   public static final ER cX = new ER(KV, Kq);
   public static final ER DQ = new ER(KV, go);
   private static final IEncodedMemoryArea Bi = new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(21, 1), A);
   private static final ZW wQ = new ZW(Bi, null, 1048582, 2097158, 4194310);
   public static final ER ZN = new ER(wQ, Kq);
   public static final ER OB = new ER(wQ, go);
   public static final ER pF = new ER(wQ, Nq);
   public static final IEncodedMemoryArea Bc = new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(19, 1), A);
   public static final ZW OI = new ZW(Bc, 2097158, 4194310, 1048582, 1048582);
   public static final ER Bf = new ER(OI, Kq);
   public static final ER Pe = new ER(OI, go);
   public static final IEncodedMemoryArea ck = DirectEncodedMemoryArea.get(20, 3);
   private static final ZW PZ = new ZW(ck, null, 1048582, 2097158, 2097158, 4194310, 4194310, 4194310, 4194310);
   public static final ER RW = new ER(PZ, Kq);
   public static final ER e = new ER(PZ, go);
   public static final IEncodedMemoryArea xM = new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(29, 1), A);
   private static final ZW Ip = new ZW(xM, 1048582, null, 2097158, 4194310);
   public static final ER kU = new ER(Ip, Kq);

   private ER(int var1, IEncodedMemoryArea var2) {
      this(var1, var2, null);
   }

   public ER(int var1, IEncodedMemoryArea var2, IX var3) {
      super(var1, Ll.Av.pC, var2, 31);
      this.gj = null;
      this.pg = var3;
   }

   public ER(ZW var1, IEncodedMemoryArea var2) {
      this(var1, var2, null);
   }

   private ER(ZW var1, IEncodedMemoryArea var2, IX var3) {
      super(0, Ll.Av.pC, var2, 31);
      this.gj = var1;
      this.pg = var3;
   }

   @Override
   public int UT(byte[] var1) {
      if (this.gj != null) {
         Integer var2 = (Integer)this.gj.A(var1);
         if (var2 != null && var2 != 0) {
            return var2;
         }
      }

      return super.UT(var1);
   }

   @Override
   public boolean wS(byte[] var1) {
      if (!super.wS(var1)) {
         return false;
      } else {
         Integer var2 = super.A(var1);
         if (var2 == null) {
            return true;
         } else {
            if (this.gj != null) {
               Integer var3 = (Integer)this.gj.A(var1);
               if (var3 == null) {
                  return false;
               }
            }

            if (this.pg != null) {
               try {
                  CharSequence var5 = this.pg.getDataType(var1);
                  if (var5 == null) {
                     return false;
                  }
               } catch (oJ var4) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   @Override
   public Yg buildOperand(byte[] var1, int var2) throws ProcessorException {
      Integer var3 = this.A(var1);
      if (var3 == null) {
         return null;
      } else if (this.pg == null) {
         return LC.pC(this.UT(var1), var3, var2, this.pC());
      } else {
         try {
            CharSequence var4 = this.pg.getDataType(var1);
            if (var4 != null) {
               return new lw(this.UT(var1), var3, var2, this.pC(), var4.toString());
            } else {
               throw new ProcessorException("Illegal datatype");
            }
         } catch (oJ var5) {
            throw new ProcessorException(var5);
         }
      }
   }

   @Override
   public boolean E(byte[] var1) {
      return false;
   }

   public static ER pC(int var0, IX var1) {
      return new ER(8388615, DirectEncodedMemoryArea.get(var0, 5), var1);
   }
}
