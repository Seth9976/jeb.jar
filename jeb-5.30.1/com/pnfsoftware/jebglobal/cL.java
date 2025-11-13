package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.BytesBlock;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.nio.ByteOrder;

@SerDisabled
public class cL extends ey {
   private final co q;
   private final YE RF;
   private final LA xK;
   private boolean Dw = true;

   public cL() {
      super(16);
      this.xK = new LA();
      this.q = new co();
      this.RF = new YE();
   }

   public p q(BytesBlock var1) throws ProcessorException {
      p var2 = null;

      p var11;
      try {
         byte[] var3 = var1.getBECode();
         Integer var4 = this.Dw ? this.xK.q() : null;
         Je var5;
         if (var3.length == 2) {
            var5 = this.q.q(var3);
         } else {
            var5 = this.RF.q(var3);
         }

         if (var5 == null || var5.RF(var3)) {
            throw new ProcessorException(getUndefinedMessage(var3));
         }

         if (var5.xK(var3)) {
            throw new ProcessorException(getUnpredictableMessage(var3, var5.q(var3, null).q()));
         }

         mZ var6;
         if (this.Dw && var4 != null) {
            Integer var7 = this.xK.RF();
            var6 = q(var3, var5, var7);
         } else {
            var6 = var5.oW(var3);
         }

         var2 = this.q(var1, var5, var6);
         if (this.Dw && var4 != null && q(var2, !this.xK.xK())) {
            var2 = null;
            throw new ProcessorException("Illegal instruction in the middle of an IT Block");
         }

         var11 = var2;
      } finally {
         if (this.Dw && var2 == null) {
            this.xK.Dw();
         }
      }

      return var11;
   }

   public static boolean q(fA var0, boolean var1) {
      return !var1 && var0.q().isPCUpdated()
         || var0.Dw().q().equals("B") && ((var0.xK()[0] & 240) == 208 || var0.xK().length == 4 && (var0.xK()[0] & 240) == 240 && (var0.xK()[2] & 16) == 0)
         || var0.nf()
         || var0.gP() && !var0.Dw().q().equals("BKPT")
         || var0.zz();
   }

   public static boolean q(byte[] var0, ByteOrder var1) {
      return q(var0, var1 == ByteOrder.BIG_ENDIAN);
   }

   public static boolean q(byte[] var0, boolean var1) {
      byte var2 = var0[0];
      if (!var1) {
         var2 = var0[1];
      }

      return (var2 & 224) == 224 && (var2 & 24) != 0;
   }

   private static mZ q(byte[] var0, Je var1, Integer var2) {
      if (var1.oW()) {
         return mZ.q(65536);
      } else {
         return var2 != null ? mZ.q(262144 | var2) : var1.oW(var0);
      }
   }

   @Override
   protected boolean useCache(BytesBlock var1) {
      return this.Dw && !this.xK.xK();
   }

   public fA RF(BytesBlock var1) throws ProcessorException {
      fA var2 = (fA)super.getInstruction(var1);
      if (this.Dw && Lf.xK(var1.getBECode())) {
         this.xK.q(var1);
      }

      return var2;
   }

   public void q(boolean var1) {
      this.Dw = var1;
   }

   public boolean q() {
      return this.Dw;
   }

   public int q(fA var1) {
      if (this.Dw) {
         return this.xK.Uv();
      } else {
         return Lf.xK(var1.xK()) ? Lf.RF(var1.xK()).size() + 1 : 0;
      }
   }

   public void RF() {
      if (this.Dw) {
         this.xK.Dw();
      }
   }
}
