package com.pnfsoftware.jebglobal;

import com.google.common.base.Preconditions;
import com.google.common.io.LittleEndianDataInputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.TreeSet;

public class Mr extends PB implements lZ {
   boolean oW;

   protected Mr(LittleEndianDataInputStream var1, oV var2) throws IOException {
      super(PH.RF(var1), var2);
   }

   protected Mr(ByteBuffer var1, oV var2) {
      super(var1, var2);
      this.oW = true;
   }

   @Override
   protected void q(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      if (this.gO()) {
         throw new RuntimeException("Unsupported/TODO: writing compressed chunks");
      } else {
         super.q(var1, var2, var3);
      }
   }

   @Override
   protected int xK() {
      return oV.eo.zz.q(this.gO());
   }

   @Override
   public void RF(boolean var1) {
      this.oW = var1;
   }

   public boolean gO() {
      if (this.oW && this.gP().equals("string")) {
         TreeSet var1 = new TreeSet();

         for (PB.eo var3 : this.zz.values()) {
            if (var3.zz() || ((Gn)Preconditions.checkNotNull(var3.Dw())).RF() != Gn.CU.Dw) {
               return false;
            }

            var1.add(var3.RF());
         }

         return var1.size() == 1;
      } else {
         return false;
      }
   }
}
