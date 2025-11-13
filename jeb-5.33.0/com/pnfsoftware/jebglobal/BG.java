package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class BG {
   private int pC;
   private int A;
   private byte kS;
   private byte wS;
   private byte UT;
   private short E;
   private byte[] sY;

   private BG() {
   }

   public boolean pC() {
      return (this.kS & 128) == 0;
   }

   public boolean A() {
      return (this.kS & 128) != 0;
   }

   public int kS() {
      return this.A;
   }

   public byte wS() {
      if (!this.pC()) {
         throw new yb("Need a command packet");
      } else {
         return this.wS;
      }
   }

   public byte UT() {
      if (!this.pC()) {
         throw new yb("Need a command packet");
      } else {
         return this.UT;
      }
   }

   public short E() {
      if (!this.A()) {
         throw new yb("Need a response packet");
      } else {
         return this.E;
      }
   }

   public byte[] sY() {
      return this.sY;
   }

   @Override
   public String toString() {
      return this.pC()
         ? Strings.ff("[JDWP:id=%d,fl=%02Xh,cc=(%d,%d),dl=%d]", this.A, this.kS, this.wS, this.UT, this.sY.length)
         : Strings.ff("[JDWP:id=%d,fl=%02Xh,err=%d,dl=%d]", this.A, this.kS, this.E, this.sY.length);
   }

   public static BG pC(byte[] var0) throws IOException {
      BG var1 = new BG();
      ByteBuffer var2 = ByteBuffer.wrap(var0);
      var1.pC = var2.getInt();
      if (var0.length != var1.pC) {
         throw new IOException(Strings.ff("Unexpected packet length: got %d bytes, but announced packet size is %d", var0.length, var1.pC));
      } else {
         var1.A = var2.getInt();
         var1.kS = var2.get();
         if ((var1.kS & 128) == 0) {
            var1.wS = var2.get();
            var1.UT = var2.get();
         } else {
            var1.E = var2.getShort();
         }

         var1.sY = new byte[var1.pC - 11];
         var2.get(var1.sY);
         return var1;
      }
   }

   public static byte[] pC(int var0, byte var1, byte var2, byte var3, byte[] var4) {
      if ((var1 & 128) != 0) {
         throw new yb("Command packet cannot have the bit #7 set");
      } else {
         ByteArrayOutputStream var5 = new ByteArrayOutputStream();

         try {
            byte[] var8;
            try (DataOutputStream var6 = new DataOutputStream(var5)) {
               int var7 = 11 + (var4 == null ? 0 : var4.length);
               var6.writeInt(var7);
               var6.writeInt(var0);
               var6.write(var1);
               var6.write(var2);
               var6.write(var3);
               if (var4 != null) {
                  var6.write(var4);
               }

               var8 = var5.toByteArray();
            }

            return var8;
         } catch (IOException var11) {
            throw new yb("Cannot build command packet", var11);
         }
      }
   }
}
