package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.core.units.codeobject.ELF;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

@Ser
public class GK {
   @SerId(1)
   int pC;
   @SerId(2)
   int A;
   @SerId(3)
   int kS;
   @SerId(4)
   byte[] wS;
   @SerId(5)
   byte[] UT;
   @SerId(6)
   String E = "";
   @SerId(7)
   String sY;

   public static GK pC(ByteBuffer var0, boolean var1) {
      GK var2 = new GK();
      byte var3 = 4;
      var2.pC = var0.getInt();
      var2.A = var0.getInt();
      var2.kS = var0.getInt();
      if (var1 && (var0.order() == ByteOrder.LITTLE_ENDIAN && var2.A == 0 || var0.order() == ByteOrder.BIG_ENDIAN && var2.pC == 0)) {
         var0.position(var0.position() - 12);
         var3 = 8;
         var2.pC = (int)var0.getLong();
         var2.A = (int)var0.getLong();
         var2.kS = (int)var0.getLong();
      }

      if (var2.pC != 0) {
         int var4 = (var2.pC + var3 - 1) / var3 * var3;
         byte[] var5 = new byte[var4];
         var0.get(var5);
         var2.wS = Arrays.copyOf(var5, var2.pC - 1);

         try {
            var2.E = new String(var2.wS, "US-ASCII");
         } catch (Exception var6) {
            var2.E = "";
         }
      }

      if (var2.A != 0) {
         int var7 = (var2.A + var3 - 1) / var3 * var3;
         byte[] var8 = new byte[var7];
         var0.get(var8);
         var2.UT = Arrays.copyOf(var8, var2.A);
      } else {
         var2.UT = new byte[0];
      }

      if (var2.E.equals("GNU")) {
         if (var2.kS == 3) {
            var2.sY = "Build ID[sha1]=" + Formatter.byteArrayToHex(var2.UT);
         } else if (var2.kS == 1) {
            var2.sY = ELF.getNoteGnuABIString(var2.UT, var0.order());
         }
      } else if (var2.E.equals("Android")) {
         var2.sY = ELF.getNoteAndroidVersionString(var2.UT, var0.order());
      }

      return var2;
   }

   @Override
   public String toString() {
      return Strings.ff(
         "Owner: %s%n    - type: %s%n    - description: %s\n",
         this.E,
         ELF.getNoteTypeString(this.E, this.kS),
         this.sY != null ? this.sY : Formatter.escapeBytes(this.UT)
      );
   }
}
