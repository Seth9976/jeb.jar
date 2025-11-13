package com.pnfsoftware.jebglobal;

import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Nullable;

public abstract class Za extends Ij {
   protected final Map wS = new LinkedHashMap();

   protected Za(ByteBuffer var1, @Nullable Ij var2) {
      super(var1, var2);
   }

   @Override
   protected void pC(ByteBuffer var1) {
      super.pC(var1);
      this.wS.clear();
      int var2 = this.kS + this.kS();
      int var3 = var2;
      int var4 = this.kS + this.wS();
      int var5 = var1.position();
      var1.position(var2);

      while (var3 < var4) {
         Ij var6 = this.wS(var1);
         this.wS.put(var3, var6);
         var3 += var6.wS();
      }

      var1.position(var5);
   }

   public final Map UT() {
      return this.wS;
   }

   @Override
   protected void pC(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      for (Ij var5 : this.UT().values()) {
         byte[] var6 = var5.pC(var3);
         var1.write(var6);
         pC(var1, var6.length);
      }
   }

   public Ij wS(ByteBuffer var1) {
      return this.A(var1, this);
   }

   private Ij A(ByteBuffer var1, @Nullable Ij var2) {
      Ij.Av var4 = Ij.Av.pC(var1.getShort());

      Object var3 = switch (var4) {
         case A -> new xx(var1, var2);
         case kS -> new gT(var1, var2);
         case wS -> new ev(var1, var2);
         case UT -> new no(var1, var2);
         case E -> new WO(var1, var2);
         case sY -> new QE(var1, var2);
         case ys -> new o(var1, var2);
         case ld -> new sw(var1, var2);
         case gp -> new tT(var1, var2);
         case oT -> new IE(var1, var2);
         case fI -> new MS(var1, var2);
         case WR -> new Ka(var1, var2);
         case NS -> new aL(var1, var2);
         default -> new XB(var1, var2);
      };
      ((Ij)var3).pC(var1);
      ((Ij)var3).A(var1);
      return (Ij)var3;
   }
}
