package com.pnfsoftware.jebglobal;

import java.nio.ByteBuffer;
import javax.annotation.Nullable;

public abstract class zW extends oV {
   private final int oW;
   private final int gO;

   protected zW(ByteBuffer var1, @Nullable oV var2) {
      super(var1, var2);
      this.oW = var1.getInt();
      this.gO = var1.getInt();
   }

   public boolean gP() {
      return this.gO != -1;
   }

   public int za() {
      return this.oW;
   }

   public String lm() {
      return this.RF(this.gO);
   }

   protected String RF(int var1) {
      if (var1 == -1) {
         return "";
      } else {
         for (oV var2 = this.q(); var2 != null; var2 = var2.q()) {
            if (var2 instanceof ec) {
               return ((ec)var2).RF(var1);
            }
         }

         throw new IllegalStateException();
      }
   }

   @Override
   protected final void xK(ByteBuffer var1) {
      super.xK(var1);
      var1.putInt(this.oW);
      var1.putInt(this.gO);
   }
}
