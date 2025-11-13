package com.pnfsoftware.jebglobal;

import com.google.common.base.Preconditions;
import com.google.common.io.LittleEndianDataInputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;

public class is extends Wo implements lZ {
   byte[] gO;
   byte[] nf;
   boolean gP = true;

   protected is(LittleEndianDataInputStream var1, oV var2) throws IOException {
      super(PH.q(var1, 286), var2);
   }

   protected is(ByteBuffer var1, oV var2) {
      super(var1, var2);
   }

   @Override
   protected void q(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      if (this.gO != null && this.nf != null) {
         var1.write(this.nf);
         var2.putInt(268, this.xK);
         var1.write(this.gO);
         var2.putInt(276, this.xK + this.nf.length);
         PH.q(var1, this.oW.values(), var3 ? 1 : 0, this.gP);
      } else {
         Preconditions.checkState(this.gO == null && this.nf == null);
         pV var4 = this.gP();
         pV var5 = this.nf();
         Preconditions.checkState(var4 instanceof lZ);
         Preconditions.checkState(var5 instanceof lZ);
         ((Po)var4).oW = false;
         ((Po)var5).oW = false;
         Map var6 = PH.q(var1, this.oW.values(), var3 ? 1 : 0, this.gP);

         for (oV var8 : this.oW.values()) {
            if (var8 == var4) {
               var2.putInt(276, (Integer)Preconditions.checkNotNull((Integer)var6.get(var8)));
            } else if (var8 == var5) {
               var2.putInt(268, (Integer)Preconditions.checkNotNull((Integer)var6.get(var8)));
            }
         }
      }
   }

   @Override
   public void RF(boolean var1) {
      this.gP = var1;

      for (oV var3 : this.oW.values()) {
         if (var3 instanceof lZ) {
            ((lZ)var3).RF(var1);
         }
      }
   }

   @Override
   protected int xK() {
      return oV.eo.lm.q(this.gP);
   }

   @Override
   public pV nf() {
      if (this.nf != null) {
         throw new RuntimeException();
      } else {
         return super.nf();
      }
   }

   @Override
   public pV gP() {
      if (this.gO != null) {
         throw new RuntimeException();
      } else {
         return super.gP();
      }
   }

   @Override
   public oV Uv(ByteBuffer var1) {
      return oV.q(var1, this);
   }
}
