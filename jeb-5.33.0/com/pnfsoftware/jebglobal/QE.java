package com.pnfsoftware.jebglobal;

import com.google.common.base.Preconditions;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

public final class QE extends Rx {
   private final int wS;
   private final int UT;
   private final int E;
   private final int sY;
   private final int ys;
   private final int ld;
   private final int gp;
   private final List oT = new ArrayList();

   protected QE(ByteBuffer var1, @Nullable Ij var2) {
      super(var1, var2);
      this.wS = var1.getInt();
      this.UT = var1.getInt();
      this.E = var1.getShort() & '\uffff';
      int var3 = var1.getShort() & '\uffff';
      Preconditions.checkState(var3 == 20);
      this.sY = var1.getShort() & '\uffff';
      this.ys = (var1.getShort() & '\uffff') - 1;
      this.ld = (var1.getShort() & '\uffff') - 1;
      this.gp = (var1.getShort() & '\uffff') - 1;
   }

   @Override
   protected void pC(ByteBuffer var1) {
      super.pC(var1);
      this.oT.addAll(this.wS(var1));
   }

   private List wS(ByteBuffer var1) {
      ArrayList var2 = new ArrayList(this.sY);
      int var3 = this.kS + this.kS() + this.E;
      int var4 = var3 + 20 * this.sY;
      var1.mark();
      var1.position(var3);

      while (var3 < var4) {
         var2.add(sA.pC(var1, this));
         var3 += 20;
      }

      var1.reset();
      return var2;
   }

   @Override
   protected Ij.Av A() {
      return Ij.Av.sY;
   }

   @Override
   protected void pC(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      super.pC(var1, var2, var3);
      var1.writeInt(this.wS);
      var1.writeInt(this.UT);
      var1.writeShort(20);
      var1.writeShort(20);
      var1.writeShort((short)this.oT.size());
      var1.writeShort((short)(this.ys + 1));
      var1.writeShort((short)(this.ld + 1));
      var1.writeShort((short)(this.gp + 1));

      for (sA var5 : this.oT) {
         var1.write(var5.pC(var3));
      }
   }
}
