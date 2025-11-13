package com.pnfsoftware.jebglobal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class uD extends Hy {
   int lm;
   int zz;
   int JY;
   int HF;
   final int LK;
   int io;
   int qa;
   int Hk;
   byte[] Me;
   List PV = new ArrayList();

   public uD(jA var1, uL var2, boolean var3) throws IOException {
      super(var1, var2, var3);
      this.lm = var2.readInt();
      this.zz = var2.readInt();
      this.JY = var2.readUnsignedShort();
      this.HF = var2.readUnsignedShort();
      this.LK = var2.readUnsignedShort();
      this.io = var2.readUnsignedShort();
      this.qa = var2.readUnsignedShort();
      this.Hk = var2.readUnsignedShort();
      if (this.LK > 0) {
         if (var3) {
            this.Me = this.RF(var2, this.Dw + this.JY);
         } else {
            this.q(var2, this.Dw + this.JY);
         }

         for (int var4 = 0; var4 < this.LK; var4++) {
            sy var5 = new sy(var2);
            this.PV.add(var5);
         }
      }
   }

   @Override
   public void q(pK var1) {
      super.q(var1);
      var1.q.writeIntLE(this.lm);
      var1.q.writeIntLE(this.zz);
      var1.q.writeShortLE(this.JY);
      var1.q.writeShortLE(this.HF);
      var1.q.writeShortLE(this.PV.size());
      var1.q.writeShortLE(this.io);
      var1.q.writeShortLE(this.qa);
      var1.q.writeShortLE(this.Hk);
      if (this.Me != null) {
         var1.q.writeBytes(this.Me);
      }

      for (sy var3 : this.PV) {
         var3.q(var1);
      }

      this.RF(var1);
   }
}
