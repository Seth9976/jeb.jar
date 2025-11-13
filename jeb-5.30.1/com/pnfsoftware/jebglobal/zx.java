package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class zx {
   private int q;
   private int RF;
   private byte xK;
   private byte Dw;
   private byte Uv;
   private short oW;
   private byte[] gO;

   private zx() {
   }

   public boolean q() {
      return (this.xK & 128) == 0;
   }

   public boolean RF() {
      return (this.xK & 128) != 0;
   }

   public int xK() {
      return this.q;
   }

   public int Dw() {
      return this.RF;
   }

   public byte Uv() {
      return this.xK;
   }

   public byte oW() {
      if (!this.q()) {
         throw new si("Need a command packet");
      } else {
         return this.Dw;
      }
   }

   public byte gO() {
      if (!this.q()) {
         throw new si("Need a command packet");
      } else {
         return this.Uv;
      }
   }

   public short nf() {
      if (!this.RF()) {
         throw new si("Need a response packet");
      } else {
         return this.oW;
      }
   }

   public byte[] gP() {
      return this.gO;
   }

   @Override
   public String toString() {
      return this.q()
         ? Strings.ff("[JDWP:id=%d,fl=%02Xh,cc=(%d,%d),dl=%d]", this.RF, this.xK, this.Dw, this.Uv, this.gO.length)
         : Strings.ff("[JDWP:id=%d,fl=%02Xh,err=%d,dl=%d]", this.RF, this.xK, this.oW, this.gO.length);
   }

   public static zx q(byte[] var0) throws IOException {
      zx var1 = new zx();
      ByteBuffer var2 = ByteBuffer.wrap(var0);
      var1.q = var2.getInt();
      if (var0.length != var1.q) {
         throw new IOException(Strings.ff("Unexpected packet length: got %d bytes, but announced packet size is %d", var0.length, var1.q));
      } else {
         var1.RF = var2.getInt();
         var1.xK = var2.get();
         if ((var1.xK & 128) == 0) {
            var1.Dw = var2.get();
            var1.Uv = var2.get();
         } else {
            var1.oW = var2.getShort();
         }

         var1.gO = new byte[var1.q - 11];
         var2.get(var1.gO);
         return var1;
      }
   }

   public static byte[] q(int var0, byte var1, byte var2, byte var3, byte[] var4) {
      if ((var1 & 128) != 0) {
         throw new si("Command packet cannot have the bit #7 set");
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
            throw new si("Cannot build command packet", var11);
         }
      }
   }

   public static byte[] q(int var0, byte var1, short var2, byte[] var3) {
      if ((var1 & 128) == 0) {
         throw new si("Response packet must have the bit #7 set");
      } else {
         ByteArrayOutputStream var4 = new ByteArrayOutputStream();

         try {
            byte[] var7;
            try (DataOutputStream var5 = new DataOutputStream(var4)) {
               int var6 = 11 + (var3 == null ? 0 : var3.length);
               var5.writeInt(var6);
               var5.writeInt(var0);
               var5.write(var1);
               var5.writeShort(var2);
               if (var3 != null) {
                  var5.write(var3);
               }

               var7 = var4.toByteArray();
            }

            return var7;
         } catch (IOException var10) {
            throw new si("Cannot build response packet", var10);
         }
      }
   }
}
