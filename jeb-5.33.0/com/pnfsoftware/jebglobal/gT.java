package com.pnfsoftware.jebglobal;

import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public class gT extends Za {
   private xx UT;
   private final Map E = new HashMap();

   protected gT(ByteBuffer var1, @Nullable Ij var2) {
      super(var1, var2);
      Preconditions.checkState(var1.getInt() >= 1);
   }

   @Override
   protected void pC(ByteBuffer var1) {
      super.pC(var1);
      this.E.clear();

      for (Ij var3 : this.UT().values()) {
         if (var3 instanceof IE var4) {
            this.E.put(var4.ys(), var4);
         } else if (var3 instanceof xx) {
            this.UT = (xx)var3;
         }
      }

      Preconditions.checkNotNull(this.UT);
   }

   @Override
   protected Ij.Av A() {
      return Ij.Av.kS;
   }

   @Override
   protected void kS(ByteBuffer var1) {
      super.kS(var1);
      var1.putInt(this.E.size());
   }

   protected final void E() {
      this.E.clear();

      for (Ij var2 : this.wS.values()) {
         if (var2 instanceof IE var3) {
            this.E.put(var3.ys(), var3);
         } else if (var2 instanceof xx) {
            this.UT = (xx)var2;
         }
      }

      Preconditions.checkNotNull(this.UT);
   }
}
