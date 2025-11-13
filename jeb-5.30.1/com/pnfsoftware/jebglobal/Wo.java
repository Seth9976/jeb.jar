package com.pnfsoftware.jebglobal;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public class Wo extends Ex {
   protected static final int za = 268;
   protected static final int lm = 276;
   protected final int zz;
   protected final String JY;
   protected final int HF;
   protected final int LK;
   protected final int io;
   protected final int qa;
   protected final int Hk;
   protected final Map Me = new HashMap();
   protected final Multimap PV = HashMultimap.create();

   protected Wo(ByteBuffer var1, @Nullable oV var2) {
      super(var1, var2);
      this.zz = var1.getInt();
      this.JY = yj.q(var1, var1.position());
      this.HF = var1.getInt();
      this.LK = var1.getInt();
      this.io = var1.getInt();
      this.qa = var1.getInt();
      this.Hk = var1.getInt();
   }

   @Override
   protected void q(ByteBuffer var1) {
      super.q(var1);

      for (oV var3 : this.gO().values()) {
         if (var3 instanceof PB var4) {
            this.PV.put(var4.nf(), var4);
         } else if (var3 instanceof Vz var5) {
            this.Me.put(var5.gO(), var5);
         } else if (!(var3 instanceof pV)) {
            throw new IllegalStateException();
         }
      }
   }

   public int za() {
      return this.zz;
   }

   public pV gP() {
      oV var1 = (oV)Preconditions.checkNotNull((oV)this.gO().get(this.io + this.Uv));
      Preconditions.checkState(var1 instanceof pV);
      return (pV)var1;
   }

   public pV nf() {
      oV var1 = (oV)Preconditions.checkNotNull((oV)this.gO().get(this.HF + this.Uv));
      Preconditions.checkState(var1 instanceof pV);
      return (pV)var1;
   }

   public Collection lm() {
      return this.PV.values();
   }

   public Collection RF(int var1) {
      return this.PV.get(var1);
   }

   public Collection q(String var1) {
      pV var2 = (pV)Preconditions.checkNotNull(this.nf());
      return this.RF(var2.q(var1) + 1);
   }

   public Collection zz() {
      return this.Me.values();
   }

   public Vz xK(int var1) {
      return (Vz)Preconditions.checkNotNull((Vz)this.Me.get(var1));
   }

   public Vz RF(String var1) {
      pV var2 = (pV)Preconditions.checkNotNull(this.nf());
      return this.xK(var2.q(var1) + 1);
   }

   public String JY() {
      return this.JY;
   }

   @Override
   protected oV.eo RF() {
      return oV.eo.lm;
   }

   @Override
   protected void xK(ByteBuffer var1) {
      var1.putInt(this.zz);
      yj.q(var1, this.JY);
      var1.putInt(0);
      var1.putInt(this.LK);
      var1.putInt(0);
      var1.putInt(this.qa);
      var1.putInt(this.Hk);
   }

   @Override
   protected void q(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      int var4 = this.HF;
      int var5 = this.io;
      int var6 = 0;

      for (oV var8 : this.gO().values()) {
         if (var8 == this.nf()) {
            var4 = var6 + this.Dw();
         } else if (var8 == this.gP()) {
            var5 = var6 + this.Dw();
         }

         byte[] var9 = var8.q(var3);
         var1.write(var9);
         var6 = q(var1, var9.length);
      }

      var2.putInt(268, var4);
      var2.putInt(276, var5);
   }
}
