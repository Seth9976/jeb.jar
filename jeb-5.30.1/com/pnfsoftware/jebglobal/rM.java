package com.pnfsoftware.jebglobal;

import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

public class rM extends oV {
   private static final int oW = 4;
   private final List gO = new ArrayList();

   protected rM(ByteBuffer var1, @Nullable oV var2) {
      super(var1, var2);
   }

   @Override
   protected void q(ByteBuffer var1) {
      super.q(var1);
      this.gO.addAll(this.Uv(var1));
   }

   private List Uv(ByteBuffer var1) {
      int var2 = (this.Uv() - this.Dw()) / 4;
      ArrayList var3 = new ArrayList(var2);
      int var4 = this.Uv + this.Dw();
      var1.mark();
      var1.position(var4);

      for (int var5 = 0; var5 < var2; var5++) {
         var3.add(var1.getInt());
      }

      var1.reset();
      return var3;
   }

   public rV RF(int var1) {
      return rV.q((Integer)this.gO.get(var1));
   }

   @Override
   protected oV.eo RF() {
      return oV.eo.za;
   }

   @Override
   protected void q(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      super.q(var1, var2, var3);

      for (Integer var5 : this.gO) {
         var1.writeInt(var5);
      }
   }
}
