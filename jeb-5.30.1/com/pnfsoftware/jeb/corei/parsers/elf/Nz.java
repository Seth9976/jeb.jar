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
public class Nz {
   @SerId(1)
   int q;
   @SerId(2)
   int RF;
   @SerId(3)
   int xK;
   @SerId(4)
   byte[] Dw;
   @SerId(5)
   byte[] Uv;
   @SerId(6)
   String oW = "";
   @SerId(7)
   String gO;

   public static Nz q(ByteBuffer var0, boolean var1) {
      Nz var2 = new Nz();
      byte var3 = 4;
      var2.q = var0.getInt();
      var2.RF = var0.getInt();
      var2.xK = var0.getInt();
      if (var1 && (var0.order() == ByteOrder.LITTLE_ENDIAN && var2.RF == 0 || var0.order() == ByteOrder.BIG_ENDIAN && var2.q == 0)) {
         var0.position(var0.position() - 12);
         var3 = 8;
         var2.q = (int)var0.getLong();
         var2.RF = (int)var0.getLong();
         var2.xK = (int)var0.getLong();
      }

      if (var2.q != 0) {
         int var4 = (var2.q + var3 - 1) / var3 * var3;
         byte[] var5 = new byte[var4];
         var0.get(var5);
         var2.Dw = Arrays.copyOf(var5, var2.q - 1);

         try {
            var2.oW = new String(var2.Dw, "US-ASCII");
         } catch (Exception var6) {
            var2.oW = "";
         }
      }

      if (var2.RF != 0) {
         int var7 = (var2.RF + var3 - 1) / var3 * var3;
         byte[] var8 = new byte[var7];
         var0.get(var8);
         var2.Uv = Arrays.copyOf(var8, var2.RF);
      } else {
         var2.Uv = new byte[0];
      }

      if (var2.oW.equals("GNU")) {
         if (var2.xK == 3) {
            var2.gO = "Build ID[sha1]=" + Formatter.byteArrayToHex(var2.Uv);
         } else if (var2.xK == 1) {
            var2.gO = ELF.getNoteGnuABIString(var2.Uv, var0.order());
         }
      } else if (var2.oW.equals("Android")) {
         var2.gO = ELF.getNoteAndroidVersionString(var2.Uv, var0.order());
      }

      return var2;
   }

   @Override
   public String toString() {
      return Strings.ff(
         "Owner: %s%n    - type: %s%n    - description: %s\n",
         this.oW,
         ELF.getNoteTypeString(this.oW, this.xK),
         this.gO != null ? this.gO : Formatter.escapeBytes(this.Uv)
      );
   }
}
