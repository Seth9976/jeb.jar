package com.pnfsoftware.jebglobal;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public class IE extends Za {
   protected final int ys;
   protected final String ld;
   protected final int gp;
   protected final int oT;
   protected final int fI;
   protected final int WR;
   protected final int NS;
   protected final Map vP = new HashMap();
   protected final Multimap xC = HashMultimap.create();

   protected IE(ByteBuffer var1, @Nullable Ij var2) {
      super(var1, var2);
      this.ys = var1.getInt();
      this.ld = cD.pC(var1, var1.position());
      this.gp = var1.getInt();
      this.oT = var1.getInt();
      this.fI = var1.getInt();
      this.WR = var1.getInt();
      this.NS = var1.getInt();
   }

   @Override
   protected void pC(ByteBuffer var1) {
      super.pC(var1);

      for (Ij var3 : this.UT().values()) {
         if (var3 instanceof MS var4) {
            this.xC.put(var4.E(), var4);
         } else if (var3 instanceof Ka var5) {
            this.vP.put(var5.UT(), var5);
         } else if (!(var3 instanceof xx)) {
            throw new IllegalStateException();
         }
      }
   }

   public xx sY() {
      Ij var1 = (Ij)Preconditions.checkNotNull((Ij)this.UT().get(this.fI + this.kS));
      Preconditions.checkState(var1 instanceof xx);
      return (xx)var1;
   }

   public xx E() {
      Ij var1 = (Ij)Preconditions.checkNotNull((Ij)this.UT().get(this.gp + this.kS));
      Preconditions.checkState(var1 instanceof xx);
      return (xx)var1;
   }

   public String ys() {
      return this.ld;
   }

   @Override
   protected Ij.Av A() {
      return Ij.Av.oT;
   }

   @Override
   protected void kS(ByteBuffer var1) {
      var1.putInt(this.ys);
      cD.pC(var1, this.ld);
      var1.putInt(0);
      var1.putInt(this.oT);
      var1.putInt(0);
      var1.putInt(this.WR);
      var1.putInt(this.NS);
   }

   @Override
   protected void pC(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      int var4 = this.gp;
      int var5 = this.fI;
      int var6 = 0;

      for (Ij var8 : this.UT().values()) {
         if (var8 == this.E()) {
            var4 = var6 + this.kS();
         } else if (var8 == this.sY()) {
            var5 = var6 + this.kS();
         }

         byte[] var9 = var8.pC(var3);
         var1.write(var9);
         var6 = pC(var1, var9.length);
      }

      var2.putInt(268, var4);
      var2.putInt(276, var5);
   }
}
