package com.pnfsoftware.jebglobal;

import com.google.common.base.Preconditions;
import com.google.common.io.LittleEndianDataInputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.TreeSet;

public class ID extends MS implements sh {
   boolean wS;

   protected ID(LittleEndianDataInputStream var1, Ij var2) throws IOException {
      super(Jn.A(var1), var2);
   }

   protected ID(ByteBuffer var1, Ij var2) {
      super(var1, var2);
      this.wS = true;
   }

   @Override
   protected void pC(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      if (this.UT()) {
         throw new RuntimeException("Unsupported/TODO: writing compressed chunks");
      } else {
         super.pC(var1, var2, var3);
      }
   }

   @Override
   public void A(boolean var1) {
      this.wS = var1;
   }

   public boolean UT() {
      if (this.wS && this.sY().equals("string")) {
         TreeSet var1 = new TreeSet();

         for (MS.Av var3 : this.gp.values()) {
            if (var3.ys() || ((wR)Preconditions.checkNotNull(var3.wS())).A() != wR.Sv.wS) {
               return false;
            }

            var1.add(var3.A());
         }

         return var1.size() == 1;
      } else {
         return false;
      }
   }
}
