package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterBankArm;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class wT extends gZ {
   private static final IEncodedMemoryArea ah = DirectEncodedMemoryArea.get(0, 4);
   private static final IEncodedMemoryArea eP = DirectEncodedMemoryArea.get(4, 4);
   private static final IEncodedMemoryArea UO = DirectEncodedMemoryArea.get(8, 4);
   private static final IEncodedMemoryArea Ab = DirectEncodedMemoryArea.get(12, 4);
   private static final IEncodedMemoryArea rl = DirectEncodedMemoryArea.get(16, 4);
   private static final IEncodedMemoryArea z = VirtualEncodedMemoryArea.get(13, 4);
   private static final Ll.Av Ek = Ll.Av.E;
   public static final wT pC = new wT(ah);
   public static final gZ A = pC(0);
   public static final wT kS = new wT(eP);
   public static final wT wS = new wT(UO);
   public static final wT UT = new wT(Ab);
   public static final gZ E = pC(12);
   public static final Hu sY = new Fw(UT);
   public static final wT ys = new wT(rl);
   public static final wT ld = new wT(rl, Ll.Av.A);
   public static final Hu gp = new wT(Ab, Ek);
   public static final gZ oT = A(0);
   public static final gZ fI = A(8);
   public static final gZ WR = A(12);
   public static final gZ NS = A(16);
   public static final gZ vP = new wT(z);
   public static final gZ xC = new wT(z, Ll.Av.A);
   public static final gZ ED = new wT(VirtualEncodedMemoryArea.get(15, 4));
   public static final gZ Sc = new wT(VirtualEncodedMemoryArea.get(14, 4));

   wT(IEncodedMemoryArea var1) {
      this(0, var1, Ll.Av.pC);
   }

   private wT(IEncodedMemoryArea var1, Ll.Av var2) {
      this(0, var1, var2);
   }

   public wT(int var1, IEncodedMemoryArea var2, Ll.Av var3) {
      this(var1, var2, var3, var3 == Ek ? RegisterBankArm.APSR_nzcv : 15);
   }

   protected wT(int var1, IEncodedMemoryArea var2, Ll.Av var3, int var4) {
      super(var1, var3, var2, var4);
   }

   @Override
   public Integer A(byte[] var1) {
      Integer var2 = super.A(var1);
      if (var2 == null) {
         return null;
      } else {
         return this.pC(Ek) && var2 == 15 ? RegisterBankArm.APSR_nzcv : var2;
      }
   }

   @Override
   public int UT(byte[] var1) {
      int var2 = super.UT(var1);
      if (this.pC(Ek)) {
         Integer var3 = super.A(var1);
         if (var3 != null && var3 == 15) {
            return 11;
         }
      }

      return var2;
   }

   private static gZ pC(int var0) {
      return new wT(0, TN.pC(DirectEncodedMemoryArea.get(var0, 4)), Ll.Av.pC, 15);
   }

   private static wT A(int var0) {
      return new wT(4, DirectEncodedMemoryArea.get(var0, 4), Ll.Av.pC);
   }

   @Override
   public boolean E(byte[] var1) {
      return this.UT(var1) == 0 && this.A(var1) == 15;
   }
}
