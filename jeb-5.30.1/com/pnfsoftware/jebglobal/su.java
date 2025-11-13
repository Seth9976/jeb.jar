package com.pnfsoftware.jebglobal;

import com.google.common.io.LittleEndianDataInputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;

public class su extends Xm implements lZ {
   boolean gO = true;

   protected su(LittleEndianDataInputStream var1, oV var2) throws IOException {
      super(PH.q(var1, 10), var2);
   }

   protected su(ByteBuffer var1, oV var2) {
      super(var1, var2);
   }

   @Override
   protected void q(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      if (this.gO) {
         PH.q(var1, this.oW.values(), var3 ? 1 : 0, true);
      } else {
         super.q(var1, var2, var3);
      }
   }

   @Override
   public void RF(boolean var1) {
      this.gO = var1;

      for (oV var3 : this.oW.values()) {
         if (var3 instanceof lZ) {
            ((lZ)var3).RF(var1);
         }
      }
   }

   @Override
   protected int xK() {
      return oV.eo.xK.q(this.gO);
   }

   @Override
   public oV Uv(ByteBuffer var1) {
      return oV.q(var1, this);
   }
}
