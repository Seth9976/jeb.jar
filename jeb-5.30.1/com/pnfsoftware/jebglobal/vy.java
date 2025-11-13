package com.pnfsoftware.jebglobal;

import com.google.common.base.Preconditions;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

public final class vy extends zW {
   private final int oW;
   private final int gO;
   private final int nf;
   private final int gP;
   private final int za;
   private final int lm;
   private final int zz;
   private final List JY = new ArrayList();

   protected vy(ByteBuffer var1, @Nullable oV var2) {
      super(var1, var2);
      this.oW = var1.getInt();
      this.gO = var1.getInt();
      this.nf = var1.getShort() & '\uffff';
      int var3 = var1.getShort() & '\uffff';
      Preconditions.checkState(var3 == 20);
      this.gP = var1.getShort() & '\uffff';
      this.za = (var1.getShort() & '\uffff') - 1;
      this.lm = (var1.getShort() & '\uffff') - 1;
      this.zz = (var1.getShort() & '\uffff') - 1;
   }

   @Override
   protected void q(ByteBuffer var1) {
      super.q(var1);
      this.JY.addAll(this.Uv(var1));
   }

   private List Uv(ByteBuffer var1) {
      ArrayList var2 = new ArrayList(this.gP);
      int var3 = this.Uv + this.Dw() + this.nf;
      int var4 = var3 + 20 * this.gP;
      var1.mark();
      var1.position(var3);

      while (var3 < var4) {
         var2.add(hZ.q(var1, this));
         var3 += 20;
      }

      var1.reset();
      return var2;
   }

   public String gO() {
      return this.RF(this.oW);
   }

   public String nf() {
      return this.RF(this.gO);
   }

   public List zz() {
      return Collections.unmodifiableList(this.JY);
   }

   @Override
   protected oV.eo RF() {
      return oV.eo.gO;
   }

   @Override
   protected void q(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      super.q(var1, var2, var3);
      var1.writeInt(this.oW);
      var1.writeInt(this.gO);
      var1.writeShort(20);
      var1.writeShort(20);
      var1.writeShort((short)this.JY.size());
      var1.writeShort((short)(this.za + 1));
      var1.writeShort((short)(this.lm + 1));
      var1.writeShort((short)(this.zz + 1));

      for (hZ var5 : this.JY) {
         var1.write(var5.q(var3));
      }
   }
}
