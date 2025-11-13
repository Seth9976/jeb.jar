package com.pnfsoftware.jebglobal;

import com.google.common.base.Preconditions;
import com.google.common.io.LittleEndianDataInputStream;
import com.google.common.io.LittleEndianDataOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class Po extends pV implements lZ {
   boolean oW = true;

   protected Po(LittleEndianDataInputStream var1, oV var2) throws IOException {
      this(PH.RF(var1), var2);
   }

   protected Po(ByteBuffer var1, oV var2) {
      super(var1, var2);
   }

   @Override
   protected void q(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      if (this.oW) {
         int var4 = 0;
         int var5 = 0;

         while (var4 < this.LK.size()) {
            ByteArrayOutputStream var6 = new ByteArrayOutputStream();
            LittleEndianDataOutputStream var7 = new LittleEndianDataOutputStream(var6);
            int var8 = 0;
            int var9 = var4;

            while (var8 < 2097152 && var9 < this.LK.size()) {
               byte[] var10 = ((String)this.LK.get(var9)).getBytes(this.za() ? Charset.forName("UTF-8") : Charset.forName("UTF-16"));
               boolean var11 = (short)var10.length == var10.length;
               Preconditions.checkArgument(var11);
               var7.writeShort(var10.length);
               var7.write(var10);
               var9++;
               var8 += var10.length + 2;
            }

            var1.writeInt(var8);
            var1.write(var6.toByteArray());
            var5 += var8 + 4;
            var4 = var9;
         }

         var1.writeInt(0);
         int var12 = var5 + 4;
         ByteArrayOutputStream var13 = new ByteArrayOutputStream();
         LittleEndianDataOutputStream var14 = new LittleEndianDataOutputStream(var13);

         for (pV.CU var17 : this.io) {
            var14.write(var17.oW());
         }

         byte[] var16 = var13.toByteArray();
         int var18 = (var16.length + var12) % 4;
         int var19 = var18 == 0 ? var16.length : 4 - var18 + var16.length;
         var1.writeInt(var19);
         var1.write(var16);
         q(var1, var12 + 4 + var16.length);
      } else {
         super.q(var1, var2, var3);
      }
   }

   @Override
   public void RF(boolean var1) {
      this.oW = var1;
   }

   private static int RF(DataOutput var0, int var1) throws IOException {
      if (var1 > 127) {
         var0.write((var1 & 32512) >> 8 | 128);
      }

      var0.write(var1 & 0xFF);
      return var1 > 127 ? 2 : 1;
   }

   @Override
   protected int RF(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      if (!var3 && !this.qa && this.za()) {
         int var4 = 0;

         for (String var6 : this.LK) {
            byte[] var7 = var6.getBytes(Charset.forName("UTF-8"));
            int var8 = RF(var1, var6.length());
            int var9 = var7.length;
            int var10 = RF(var1, var9);
            var1.write(var7);
            var1.write(0);
            var2.putInt(var4);
            var4 += var8 + var10 + var9 + 1;
         }

         return q(var1, var4);
      } else {
         return super.RF(var1, var2, var3);
      }
   }

   @Override
   protected int xK() {
      return oV.eo.RF.q(this.oW);
   }
}
