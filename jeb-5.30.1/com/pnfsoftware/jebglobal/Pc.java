package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterBankArm;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class Pc extends rR {
   private static final int xW = 4;
   private static final IEncodedMemoryArea KT = DirectEncodedMemoryArea.get(0, 4);
   private static final IEncodedMemoryArea Gf = DirectEncodedMemoryArea.get(4, 4);
   private static final IEncodedMemoryArea Ef = DirectEncodedMemoryArea.get(8, 4);
   private static final IEncodedMemoryArea cC = DirectEncodedMemoryArea.get(12, 4);
   private static final IEncodedMemoryArea sH = DirectEncodedMemoryArea.get(16, 4);
   private static final IEncodedMemoryArea CE = VirtualEncodedMemoryArea.get(13, 4);
   private static final jD.eo wF = jD.eo.oW;
   public static final Pc Dw = new Pc(KT);
   public static final rR Uv = q(0);
   public static final Pc oW = new Pc(Gf);
   public static final Pc gO = new Pc(Ef);
   public static final Pc nf = new Pc(cC);
   public static final rR gP = q(12);
   public static final Ef za = new xT(nf);
   public static final Pc lm = new Pc(sH);
   public static final Pc zz = new Pc(sH, jD.eo.RF);
   public static final Ef JY = new Pc(cC, wF);
   public static final rR HF = RF(0);
   public static final rR LK = RF(8);
   public static final rR io = RF(12);
   public static final rR qa = RF(16);
   public static final rR Hk = new Pc(CE);
   public static final rR Me = new Pc(CE, jD.eo.RF);
   public static final rR PV = new Pc(VirtualEncodedMemoryArea.get(15, 4));
   public static final rR oQ = new Pc(VirtualEncodedMemoryArea.get(14, 4));

   Pc(IEncodedMemoryArea var1) {
      this(0, var1, jD.eo.q);
   }

   private Pc(IEncodedMemoryArea var1, jD.eo var2) {
      this(0, var1, var2);
   }

   public Pc(int var1, IEncodedMemoryArea var2, jD.eo var3) {
      this(var1, var2, var3, var3 == wF ? RegisterBankArm.APSR_nzcv : 15);
   }

   protected Pc(int var1, IEncodedMemoryArea var2, jD.eo var3, int var4) {
      super(var1, var3, var2, var4);
   }

   @Override
   public Integer RF(byte[] var1) {
      Integer var2 = super.RF(var1);
      if (var2 == null) {
         return null;
      } else {
         return this.q(wF) && var2 == 15 ? RegisterBankArm.APSR_nzcv : var2;
      }
   }

   @Override
   public int Uv(byte[] var1) {
      int var2 = super.Uv(var1);
      if (this.q(wF)) {
         Integer var3 = super.RF(var1);
         if (var3 != null && var3 == 15) {
            return 11;
         }
      }

      return var2;
   }

   private static rR q(int var0) {
      return new Pc(0, dX.q(DirectEncodedMemoryArea.get(var0, 4)), jD.eo.q, 15);
   }

   private static Pc RF(int var0) {
      return new Pc(4, DirectEncodedMemoryArea.get(var0, 4), jD.eo.q);
   }

   @Override
   public boolean oW(byte[] var1) {
      return this.Uv(var1) == 0 && this.RF(var1) == 15;
   }
}
