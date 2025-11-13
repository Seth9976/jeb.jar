package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class HK {
   public int q;
   public int RF;
   public int xK;

   public HK(int var1, int var2, int var3) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
   }

   public HK(uL var1) throws IOException {
      this.q = var1.readUnsignedShort();
      if (var1.readByte() != 0) {
         var1.q(S.L("Illegal resource value, reserved byte should be 0"));
      }

      this.RF = var1.readUnsignedByte();
      this.xK = var1.readInt();
   }

   @Override
   public String toString() {
      return Strings.ff("t=%d,d=0x%X,s=%d", this.RF, this.xK, this.q);
   }

   public void q(pK var1) {
      var1.q.writeShortLE(this.q);
      var1.q.writeByte(0);
      var1.q.writeByte(this.RF);
      var1.q.writeIntLE(this.xK);
   }

   public String q() {
      return q(this.RF);
   }

   public static String q(int var0) {
      switch (var0) {
         case 0:
         case 1:
         case 2:
         case 4:
         case 5:
         case 6:
         case 7:
         case 8:
         case 9:
         case 10:
         case 11:
         case 12:
         case 13:
         case 14:
         case 15:
         case 16:
         case 17:
         case 19:
         case 20:
         case 21:
         case 22:
         case 23:
         case 24:
         case 25:
         case 26:
         case 27:
         case 28:
         case 29:
         case 30:
         case 31:
         default:
            throw new RuntimeException("Don't know how to format value type: " + var0);
         case 3:
            return "string";
         case 18:
            return "boolean";
      }
   }
}
