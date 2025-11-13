package com.pnfsoftware.jebglobal;

import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public class Xm extends Ex {
   private pV gO;
   private final Map nf = new HashMap();

   protected Xm(ByteBuffer var1, @Nullable oV var2) {
      super(var1, var2);
      Preconditions.checkState(var1.getInt() >= 1);
   }

   @Override
   protected void q(ByteBuffer var1) {
      super.q(var1);
      this.nf.clear();

      for (oV var3 : this.gO().values()) {
         if (var3 instanceof Wo var4) {
            this.nf.put(var4.JY(), var4);
         } else if (var3 instanceof pV) {
            this.gO = (pV)var3;
         }
      }

      Preconditions.checkNotNull(this.gO);
   }

   public pV nf() {
      return this.gO;
   }

   @Nullable
   public Wo q(String var1) {
      return (Wo)this.nf.get(var1);
   }

   public Collection gP() {
      return Collections.unmodifiableCollection(this.nf.values());
   }

   @Override
   protected oV.eo RF() {
      return oV.eo.xK;
   }

   @Override
   protected void xK(ByteBuffer var1) {
      super.xK(var1);
      var1.putInt(this.nf.size());
   }

   protected final void za() {
      this.nf.clear();

      for (oV var2 : this.oW.values()) {
         if (var2 instanceof Wo var3) {
            this.nf.put(var3.JY(), var3);
         } else if (var2 instanceof pV) {
            this.gO = (pV)var2;
         }
      }

      Preconditions.checkNotNull(this.gO);
   }
}
