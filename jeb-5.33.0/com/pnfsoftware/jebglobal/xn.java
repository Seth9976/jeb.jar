package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.BytesBlock;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.nio.ByteOrder;

@SerDisabled
public class xn extends ch {
   private final Xc pC;
   private final Du A;
   private final sC kS;
   private boolean wS = true;

   public xn() {
      super(16);
      this.kS = new sC();
      this.pC = new Xc();
      this.A = new Du();
   }

   public jR pC(BytesBlock var1) throws ProcessorException {
      jR var2 = null;

      jR var11;
      try {
         byte[] var3 = var1.getBECode();
         Integer var4 = this.wS ? this.kS.pC() : null;
         tz var5;
         if (var3.length == 2) {
            var5 = this.pC.pC(var3);
         } else {
            var5 = this.A.pC(var3);
         }

         if (var5 == null || var5.A(var3)) {
            throw new ProcessorException(getUndefinedMessage(var3));
         }

         if (var5.kS(var3)) {
            throw new ProcessorException(getUnpredictableMessage(var3, var5.pC(var3, null).pC()));
         }

         zj var6;
         if (this.wS && var4 != null) {
            Integer var7 = this.kS.A();
            var6 = pC(var3, var5, var7);
         } else {
            var6 = var5.E(var3);
         }

         var2 = this.pC(var1, var5, var6);
         if (this.wS && var4 != null && pC(var2, !this.kS.kS())) {
            var2 = null;
            throw new ProcessorException("Illegal instruction in the middle of an IT Block");
         }

         var11 = var2;
      } finally {
         if (this.wS && var2 == null) {
            this.kS.wS();
         }
      }

      return var11;
   }

   public static boolean pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var0, boolean var1) {
      return !var1 && var0.pC().isPCUpdated()
         || var0.wS().pC().equals("B") && ((var0.kS()[0] & 240) == 208 || var0.kS().length == 4 && (var0.kS()[0] & 240) == 240 && (var0.kS()[2] & 16) == 0)
         || var0.ys()
         || var0.ld() && !var0.wS().pC().equals("BKPT")
         || var0.fI();
   }

   public static boolean pC(byte[] var0, ByteOrder var1) {
      return pC(var0, var1 == ByteOrder.BIG_ENDIAN);
   }

   public static boolean pC(byte[] var0, boolean var1) {
      byte var2 = var0[0];
      if (!var1) {
         var2 = var0[1];
      }

      return (var2 & 224) == 224 && (var2 & 24) != 0;
   }

   private static zj pC(byte[] var0, tz var1, Integer var2) {
      if (var1.E()) {
         return zj.pC(65536);
      } else {
         return var2 != null ? zj.pC(262144 | var2) : var1.E(var0);
      }
   }

   @Override
   protected boolean useCache(BytesBlock var1) {
      return this.wS && !this.kS.kS();
   }

   public com.pnfsoftware.jeb.corei.parsers.arm.rQ A(BytesBlock var1) throws ProcessorException {
      com.pnfsoftware.jeb.corei.parsers.arm.rQ var2 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)super.getInstruction(var1);
      if (this.wS && uV.kS(var1.getBECode())) {
         this.kS.pC(var1);
      }

      return var2;
   }

   public void pC(boolean var1) {
      this.wS = var1;
   }

   public boolean pC() {
      return this.wS;
   }

   public int pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1) {
      if (this.wS) {
         return this.kS.UT();
      } else {
         return uV.kS(var1.kS()) ? uV.A(var1.kS()).size() + 1 : 0;
      }
   }

   public void A() {
      if (this.wS) {
         this.kS.wS();
      }
   }
}
