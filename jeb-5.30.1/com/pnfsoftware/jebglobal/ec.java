package com.pnfsoftware.jebglobal;

import java.nio.ByteBuffer;
import javax.annotation.Nullable;

public final class ec extends Ex {
   protected ec(ByteBuffer var1, @Nullable oV var2) {
      super(var1, var2);
   }

   @Override
   protected oV.eo RF() {
      return oV.eo.Dw;
   }

   public String RF(int var1) {
      for (oV var3 : this.gO().values()) {
         if (var3 instanceof pV) {
            return ((pV)var3).RF(var1);
         }
      }

      throw new IllegalStateException();
   }
}
