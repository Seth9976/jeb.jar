package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.FunctionEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class sQ extends gZ {
   private static final IEncodedMemoryArea RW = DirectEncodedMemoryArea.get(0, 5);
   private static final IEncodedMemoryArea e = DirectEncodedMemoryArea.get(5, 5);
   private static final IEncodedMemoryArea xM = DirectEncodedMemoryArea.get(10, 5);
   private static final IEncodedMemoryArea kU = DirectEncodedMemoryArea.get(16, 5);
   private static final Ll.Av Kq = Ll.Av.E;
   private static final Ll.Av go = Ll.Av.sY;
   private final IEncodedMemoryArea JF;
   private static final IEncodedMemoryArea Nq = DirectEncodedMemoryArea.get(31, 1);
   public static final sQ pC = new sQ(0, RW, Ll.Av.WR);
   public static final sQ A = new sQ(0, RW);
   public static final sQ kS = new sQ(0, RW, Kq);
   public static final sQ wS = pC(0, RW);
   public static final sQ UT = new sQ(0, e);
   public static final sQ E = new sQ(0, e, Ll.Av.fI);
   public static final sQ sY = new sQ(0, e, Ll.Av.A);
   public static final sQ ys = new sQ(0, xM);
   public static final sQ ld = new sQ(0, kU);
   public static final sQ gp = new sQ(0, kU, Kq);
   public static final sQ oT = pC(0, kU);
   public static final sQ fI = new sQ(0, kU, Ll.Av.WR);
   public static final sQ WR = new sQ(0, RW, go);
   public static final sQ NS = new sQ(0, e, go);
   public static final sQ vP = new sQ(0, kU, go);
   public static final sQ xC = new sQ(0, e, Ll.Av.kS);
   public static final sQ ED = new sQ(2097152, RW);
   public static final sQ Sc = new sQ(2097152, RW, Kq);
   public static final sQ ah = pC(2097152, RW);
   public static final sQ eP = new sQ(2097152, e);
   public static final sQ UO = new sQ(2097152, xM);
   public static final sQ Ab = new sQ(2097152, kU);
   public static final sQ rl = new sQ(2097152, kU, Kq);
   public static final sQ z = pC(2097152, kU);
   public static final sQ Ek = new sQ(2097152, RW, go);
   public static final sQ hK = new sQ(2097152, e, go);
   public static final sQ Er = new sQ(2097152, kU, go);
   public static final sQ FE = new sQ(Nq, RW);
   public static final sQ Aj = new sQ(Nq, e);
   public static final sQ EX = new sQ(Nq, xM);
   public static final sQ LM = new sQ(Nq, kU);
   public static final sQ mv = new sQ(DirectEncodedMemoryArea.get(30, 1), RW);
   public static final sQ sO = new sQ(DirectEncodedMemoryArea.get(22, 2), RW);
   public static final sQ os = new sQ(DirectEncodedMemoryArea.get(12, 1), e);
   public static final sQ Cu = new sQ(DirectEncodedMemoryArea.get(22, 2), e);
   public static final sQ hZ = new sQ(DirectEncodedMemoryArea.get(12, 1), kU);
   public static final sQ UW = new sQ(DirectEncodedMemoryArea.get(13, 1), kU);
   public static final sQ PR = new sQ(DirectEncodedMemoryArea.get(30, 1), kU);
   public static final sQ cX = new sQ(DirectEncodedMemoryArea.get(22, 2), kU);
   public static final sQ DQ = new sQ(Nq, RW, go);
   public static final sQ ZN = new sQ(DirectEncodedMemoryArea.get(22, 2), RW, go);
   public static final sQ OB = new sQ(Nq, e, go);
   public static final sQ pF = new sQ(DirectEncodedMemoryArea.get(22, 2), e, go);
   public static final sQ Bc = new sQ(Nq, kU, go);
   public static final sQ OI = new sQ(2097152, new FunctionEncodedMemoryArea(5, var0 -> 12L + DirectEncodedMemoryArea.get(13, 2).decode(var0)));
   public static final sQ Bf = new sQ(2097152, new FunctionEncodedMemoryArea(5, var0 -> 12L + DirectEncodedMemoryArea.get(16, 2).decode(var0)));
   public static final gZ Pe = new sQ(10, VirtualEncodedMemoryArea.get(0, 5));
   public static final gZ ck = new sQ(0, VirtualEncodedMemoryArea.get(16, 5));

   public sQ(int var1, IEncodedMemoryArea var2) {
      this(var1, var2, Ll.Av.pC);
   }

   public sQ(int var1, IEncodedMemoryArea var2, Ll.Av var3) {
      this(var1, var2, var3, 33);
   }

   protected sQ(int var1, IEncodedMemoryArea var2, Ll.Av var3, int var4) {
      super(var1, var3, var2, var4);
      this.JF = null;
   }

   private sQ(IEncodedMemoryArea var1, IEncodedMemoryArea var2) {
      this(var1, var2, Ll.Av.pC);
   }

   public sQ(IEncodedMemoryArea var1, IEncodedMemoryArea var2, Ll.Av var3) {
      super(0, var3, var2, 33);
      this.JF = var1;
   }

   private static sQ pC(int var0, IEncodedMemoryArea var1) {
      return new sQ(var0, TN.pC(var1), Ll.Av.pC, 33);
   }

   @Override
   public boolean wS(byte[] var1) {
      if (this.pC(Kq)) {
         Integer var2 = this.A(var1);
         if (var2 == null) {
            return super.wS(var1);
         }

         boolean var3 = (var2 & 1) == 0;
         if (!var3) {
            return false;
         }
      }

      return super.wS(var1);
   }

   @Override
   public Integer A(byte[] var1) {
      Integer var2 = super.A(var1);
      if (var2 == null) {
         return null;
      } else {
         int var3 = this.UT(var1);
         if (this.pC(var3, var2)) {
            var2 = 33;
         }

         return var2;
      }
   }

   private boolean pC(int var1, int var2) {
      return (var1 == 2097152 || var1 == 0) && var2 == 31 ? !this.pC(go) && !this.pC(Ll.Av.kS) : false;
   }

   @Override
   public int UT(byte[] var1) {
      if (this.JF != null) {
         int var2 = this.JF.decodeInt(var1);
         if (this.JF.getLength() == 1) {
            return var2 == 0 ? 2097152 : 0;
         } else {
            return var2 == 3 ? 0 : 2097152;
         }
      } else {
         return super.UT(var1);
      }
   }

   @Override
   public boolean E(byte[] var1) {
      return this.UT(var1) == 10;
   }
}
