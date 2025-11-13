package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.TextBuilder;
import java.io.IOException;

public class ZC extends jA {
   public static final int oW = 1073741824;
   public static final int gO = Integer.MIN_VALUE;
   public static final int nf = 536870912;
   public int gP;
   public int[] za;

   ZC(jA var1, uL var2) throws IOException {
      super(var1, 514);
      this.gP = var2.readUnsignedByte();
      if (this.gP == 0) {
         throw new IOException("Zero id in resource specification");
      } else {
         var2.readByte();
         var2.readShort();
         int var3 = var2.readInt();
         this.xK(var2);
         this.za = zR.q(var2, var3);
         this.Uv(var2);
      }
   }

   @Override
   public void q(TextBuilder var1) {
      if (this.za.length == 0) {
         var1.appendLine("TypeSpec: id:%d (no entry)", this.gP);
      } else {
         var1.appendLine("TypeSpec: id:%d, %d entries", this.gP, this.za.length);
         var1.indent();

         for (int var5 : this.za) {
            String var6 = "";
            if ((var5 & 1073741824) != 0) {
               var5 &= -1073741825;
               var6 = "(PUBLIC)";
            }

            if ((var5 & -2147483648) != 0) {
               var5 &= Integer.MAX_VALUE;
               var6 = "(OVERLAYABLE)";
            }

            var1.append("%Xh%s, ", var5, var6);
         }

         var1.appendLine("");
         var1.unindent();
      }
   }

   public static String q(int var0) {
      StringBuilder var1 = new StringBuilder();
      if ((var0 & 1073741824) != 0) {
         var1.append(" (PUBLIC)");
      }

      if ((var0 & -2147483648) != 0) {
         var1.append(" (OVERLAYABLE)");
      }

      return var1.toString();
   }
}
