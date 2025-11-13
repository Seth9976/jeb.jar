package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.dex.DexMethodHandleType;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethodHandle;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class bjq extends com.pnfsoftware.jeb.corei.parsers.dex.CU implements IDexMethodHandle, bjx {
   public static final int RF = 0;
   public static final int xK = 1;
   public static final int Dw = 2;
   public static final int Uv = 3;
   public static final int oW = 4;
   public static final int gO = 5;
   public static final int nf = 6;
   public static final int gP = 7;
   public static final int za = 8;
   @SerId(2)
   private int lm;
   @SerId(3)
   private int zz;
   @SerId(4)
   private int JY;

   public bjq(com.pnfsoftware.jeb.corei.parsers.dex.bK var1, int var2, int var3, int var4) {
      super(var1);
      this.lm = var2;
      this.zz = var3;
      this.JY = var4;
   }

   @Override
   public int getIndex() {
      return this.lm;
   }

   @Override
   public DexMethodHandleType getMethodHandleType() {
      return DexMethodHandleType.get(this.zz);
   }

   @Override
   public int getFieldOrMethodIndex() {
      return this.JY;
   }

   @Override
   public String getAddress(boolean var1) {
      return null;
   }

   @Override
   public String getName(boolean var1) {
      return "method_handle_" + this.lm;
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
      DexMethodHandleType var2 = DexMethodHandleType.get(this.zz);
      String var3;
      if (var2.isFieldAccessor()) {
         var3 = this.q.oW(this.JY).getSignature(var1);
      } else if (var2.isMethodInvoker()) {
         var3 = this.q.gO(this.JY).getSignature(var1);
      } else {
         var3 = "unknown_method_handle_type_" + this.zz;
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
