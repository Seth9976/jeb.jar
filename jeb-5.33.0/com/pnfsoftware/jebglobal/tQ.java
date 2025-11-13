package com.pnfsoftware.jebglobal;

import com.google.common.base.Preconditions;
import com.google.common.io.LittleEndianDataInputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;

public class tQ extends IE implements sh {
   byte[] UT;
   byte[] E;
   boolean sY = true;

   protected tQ(LittleEndianDataInputStream var1, Ij var2) throws IOException {
      super(Jn.pC(var1, 286), var2);
   }

   protected tQ(ByteBuffer var1, Ij var2) {
      super(var1, var2);
   }

   @Override
   protected void pC(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      if (this.UT != null && this.E != null) {
         var1.write(this.E);
         var2.putInt(268, this.pC);
         var1.write(this.UT);
         var2.putInt(276, this.pC + this.E.length);
         Jn.pC(var1, this.wS.values(), var3 ? 1 : 0, this.sY);
      } else {
         Preconditions.checkState(this.UT == null && this.E == null);
         xx var4 = this.sY();
         xx var5 = this.E();
         Preconditions.checkState(var4 instanceof sh);
         Preconditions.checkState(var5 instanceof sh);
         ((CL)var4).wS = false;
         ((CL)var5).wS = false;
         Map var6 = Jn.pC(var1, this.wS.values(), var3 ? 1 : 0, this.sY);

         for (Ij var8 : this.wS.values()) {
            if (var8 == var4) {
               var2.putInt(276, (Integer)Preconditions.checkNotNull((Integer)var6.get(var8)));
            } else if (var8 == var5) {
               var2.putInt(268, (Integer)Preconditions.checkNotNull((Integer)var6.get(var8)));
            }
         }
      }
   }

   @Override
   public void A(boolean var1) {
      this.sY = var1;

      for (Ij var3 : this.wS.values()) {
         if (var3 instanceof sh) {
            ((sh)var3).A(var1);
         }
      }
   }

   @Override
   public xx E() {
      if (this.E != null) {
         throw new RuntimeException();
      } else {
         return super.E();
      }
   }

   @Override
   public xx sY() {
      if (this.UT != null) {
         throw new RuntimeException();
      } else {
         return super.sY();
      }
   }

   @Override
   public Ij wS(ByteBuffer var1) {
      return Ij.pC(var1, this);
   }
}
