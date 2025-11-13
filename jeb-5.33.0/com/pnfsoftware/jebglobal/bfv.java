package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.dex.DexMethodHandleType;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethodHandle;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class bfv extends com.pnfsoftware.jeb.corei.parsers.dex.Sv implements IDexMethodHandle, bgc {
   @SerId(2)
   private int A;
   @SerId(3)
   private int kS;
   @SerId(4)
   private int wS;

   public bfv(com.pnfsoftware.jeb.corei.parsers.dex.vi var1, int var2, int var3, int var4) {
      super(var1);
      this.A = var2;
      this.kS = var3;
      this.wS = var4;
   }

   @Override
   public int getIndex() {
      return this.A;
   }

   @Override
   public DexMethodHandleType getMethodHandleType() {
      return DexMethodHandleType.get(this.kS);
   }

   @Override
   public int getFieldOrMethodIndex() {
      return this.wS;
   }

   @Override
   public String getAddress(boolean var1) {
      return null;
   }

   @Override
   public String getName(boolean var1) {
      return "method_handle_" + this.A;
   }

   @Override
   public String getSignature(boolean var1) {
      return null;
   }

   @Override
   public String getSignature(boolean var1, boolean var2) {
      return null;
   }

   @Override
   public String generate(boolean var1) {
      DexMethodHandleType var2 = DexMethodHandleType.get(this.kS);
      String var3;
      if (var2.isFieldAccessor()) {
         var3 = this.pC.E(this.wS).getSignature(var1);
      } else if (var2.isMethodInvoker()) {
         var3 = this.pC.sY(this.wS).getSignature(var1);
      } else {
         var3 = "unknown_method_handle_type_" + this.kS;
      }

      return var3;
   }

   @Override
   public boolean isInternal() {
      return true;
   }

   @Override
   public boolean isArtificial() {
      return false;
   }

   @Override
   public int getGenericFlags() {
      return 0;
   }
}
