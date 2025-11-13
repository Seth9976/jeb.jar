package com.pnfsoftware.jebglobal;

abstract class cjk extends cjh {
   private long[] q;
   private byte[] RF;
   private static final long[] xK = new long[]{
      1L,
      32898L,
      -9223372036854742902L,
      -9223372034707259392L,
      32907L,
      2147483649L,
      -9223372034707259263L,
      -9223372036854743031L,
      138L,
      136L,
      2147516425L,
      2147483658L,
      2147516555L,
      -9223372036854775669L,
      -9223372036854742903L,
      -9223372036854743037L,
      -9223372036854743038L,
      -9223372036854775680L,
      32778L,
      -9223372034707292150L,
      -9223372034707259263L,
      -9223372036854742912L,
      2147483649L,
      -9223372034707259384L
   };

   cjk(String var1) {
      super(var1);
   }

   private static void q(long var0, byte[] var2, int var3) {
      var2[var3 + 0] = (byte)var0;
      var2[var3 + 1] = (byte)(var0 >>> 8);
      var2[var3 + 2] = (byte)(var0 >>> 16);
      var2[var3 + 3] = (byte)(var0 >>> 24);
      var2[var3 + 4] = (byte)(var0 >>> 32);
      var2[var3 + 5] = (byte)(var0 >>> 40);
      var2[var3 + 6] = (byte)(var0 >>> 48);
      var2[var3 + 7] = (byte)(var0 >>> 56);
   }

   private static long RF(byte[] var0, int var1) {
      return var0[var1 + 0] & 255L
         | (var0[var1 + 1] & 255L) << 8
         | (var0[var1 + 2] & 255L) << 16
         | (var0[var1 + 3] & 255L) << 24
         | (var0[var1 + 4] & 255L) << 32
         | (var0[var1 + 5] & 255L) << 40
         | (var0[var1 + 6] & 255L) << 48
         | (var0[var1 + 7] & 255L) << 56;
   }

   @Override
   protected void engineReset() {
      this.nf();
   }

   @Override
   protected void q(byte[] var1) {
      for (byte var2 = 0; var2 < var1.length; var2 += 8) {
         this.q[var2 >>> 3] = this.q[var2 >>> 3] ^ RF(var1, var2);
      }

      for (byte var36 = 0; var36 < 24; var36 += 2) {
         long var12 = this.q[1] ^ this.q[6];
         long var14 = this.q[11] ^ this.q[16];
         var12 ^= this.q[21] ^ var14;
         var12 = var12 << 1 | var12 >>> 63;
         long var16 = this.q[4] ^ this.q[9];
         long var18 = this.q[14] ^ this.q[19];
         var12 ^= this.q[24];
         var16 ^= var18;
         long var37 = var12 ^ var16;
         var12 = this.q[2] ^ this.q[7];
         var14 = this.q[12] ^ this.q[17];
         var12 ^= this.q[22] ^ var14;
         var12 = var12 << 1 | var12 >>> 63;
         var16 = this.q[0] ^ this.q[5];
         var18 = this.q[10] ^ this.q[15];
         var12 ^= this.q[20];
         var16 ^= var18;
         long var4 = var12 ^ var16;
         var12 = this.q[3] ^ this.q[8];
         var14 = this.q[13] ^ this.q[18];
         var12 ^= this.q[23] ^ var14;
         var12 = var12 << 1 | var12 >>> 63;
         var16 = this.q[1] ^ this.q[6];
         var18 = this.q[11] ^ this.q[16];
         var12 ^= this.q[21];
         var16 ^= var18;
         long var6 = var12 ^ var16;
         var12 = this.q[4] ^ this.q[9];
         var14 = this.q[14] ^ this.q[19];
         var12 ^= this.q[24] ^ var14;
         var12 = var12 << 1 | var12 >>> 63;
         var16 = this.q[2] ^ this.q[7];
         var18 = this.q[12] ^ this.q[17];
         var12 ^= this.q[22];
         var16 ^= var18;
         long var8 = var12 ^ var16;
         var12 = this.q[0] ^ this.q[5];
         var14 = this.q[10] ^ this.q[15];
         var12 ^= this.q[20] ^ var14;
         var12 = var12 << 1 | var12 >>> 63;
         var16 = this.q[3] ^ this.q[8];
         var18 = this.q[13] ^ this.q[18];
         var12 ^= this.q[23];
         var16 ^= var18;
         long var10 = var12 ^ var16;
         this.q[0] = this.q[0] ^ var37;
         this.q[5] = this.q[5] ^ var37;
         this.q[10] = this.q[10] ^ var37;
         this.q[15] = this.q[15] ^ var37;
         this.q[20] = this.q[20] ^ var37;
         this.q[1] = this.q[1] ^ var4;
         this.q[6] = this.q[6] ^ var4;
         this.q[11] = this.q[11] ^ var4;
         this.q[16] = this.q[16] ^ var4;
         this.q[21] = this.q[21] ^ var4;
         this.q[2] = this.q[2] ^ var6;
         this.q[7] = this.q[7] ^ var6;
         this.q[12] = this.q[12] ^ var6;
         this.q[17] = this.q[17] ^ var6;
         this.q[22] = this.q[22] ^ var6;
         this.q[3] = this.q[3] ^ var8;
         this.q[8] = this.q[8] ^ var8;
         this.q[13] = this.q[13] ^ var8;
         this.q[18] = this.q[18] ^ var8;
         this.q[23] = this.q[23] ^ var8;
         this.q[4] = this.q[4] ^ var10;
         this.q[9] = this.q[9] ^ var10;
         this.q[14] = this.q[14] ^ var10;
         this.q[19] = this.q[19] ^ var10;
         this.q[24] = this.q[24] ^ var10;
         this.q[5] = this.q[5] << 36 | this.q[5] >>> 28;
         this.q[10] = this.q[10] << 3 | this.q[10] >>> 61;
         this.q[15] = this.q[15] << 41 | this.q[15] >>> 23;
         this.q[20] = this.q[20] << 18 | this.q[20] >>> 46;
         this.q[1] = this.q[1] << 1 | this.q[1] >>> 63;
         this.q[6] = this.q[6] << 44 | this.q[6] >>> 20;
         this.q[11] = this.q[11] << 10 | this.q[11] >>> 54;
         this.q[16] = this.q[16] << 45 | this.q[16] >>> 19;
         this.q[21] = this.q[21] << 2 | this.q[21] >>> 62;
         this.q[2] = this.q[2] << 62 | this.q[2] >>> 2;
         this.q[7] = this.q[7] << 6 | this.q[7] >>> 58;
         this.q[12] = this.q[12] << 43 | this.q[12] >>> 21;
         this.q[17] = this.q[17] << 15 | this.q[17] >>> 49;
         this.q[22] = this.q[22] << 61 | this.q[22] >>> 3;
         this.q[3] = this.q[3] << 28 | this.q[3] >>> 36;
         this.q[8] = this.q[8] << 55 | this.q[8] >>> 9;
         this.q[13] = this.q[13] << 25 | this.q[13] >>> 39;
         this.q[18] = this.q[18] << 21 | this.q[18] >>> 43;
         this.q[23] = this.q[23] << 56 | this.q[23] >>> 8;
         this.q[4] = this.q[4] << 27 | this.q[4] >>> 37;
         this.q[9] = this.q[9] << 20 | this.q[9] >>> 44;
         this.q[14] = this.q[14] << 39 | this.q[14] >>> 25;
         this.q[19] = this.q[19] << 8 | this.q[19] >>> 56;
         this.q[24] = this.q[24] << 14 | this.q[24] >>> 50;
         long var34 = ~this.q[12];
         long var22 = this.q[6] | this.q[12];
         long var24 = this.q[0] ^ var22;
         var22 = var34 | this.q[18];
         long var26 = this.q[6] ^ var22;
         var22 = this.q[18] & this.q[24];
         long var28 = this.q[12] ^ var22;
         var22 = this.q[24] | this.q[0];
         long var30 = this.q[18] ^ var22;
         var22 = this.q[0] & this.q[6];
         long var32 = this.q[24] ^ var22;
         this.q[0] = var24;
         this.q[6] = var26;
         this.q[12] = var28;
         this.q[18] = var30;
         this.q[24] = var32;
         var34 = ~this.q[22];
         var22 = this.q[9] | this.q[10];
         var24 = this.q[3] ^ var22;
         var22 = this.q[10] & this.q[16];
         var26 = this.q[9] ^ var22;
         var22 = this.q[16] | var34;
         var28 = this.q[10] ^ var22;
         var22 = this.q[22] | this.q[3];
         var30 = this.q[16] ^ var22;
         var22 = this.q[3] & this.q[9];
         var32 = this.q[22] ^ var22;
         this.q[3] = var24;
         this.q[9] = var26;
         this.q[10] = var28;
         this.q[16] = var30;
         this.q[22] = var32;
         var34 = ~this.q[19];
         var22 = this.q[7] | this.q[13];
         var24 = this.q[1] ^ var22;
         var22 = this.q[13] & this.q[19];
         var26 = this.q[7] ^ var22;
         var22 = var34 & this.q[20];
         var28 = this.q[13] ^ var22;
         var22 = this.q[20] | this.q[1];
         var30 = var34 ^ var22;
         var22 = this.q[1] & this.q[7];
         var32 = this.q[20] ^ var22;
         this.q[1] = var24;
         this.q[7] = var26;
         this.q[13] = var28;
         this.q[19] = var30;
         this.q[20] = var32;
         var34 = ~this.q[17];
         var22 = this.q[5] & this.q[11];
         var24 = this.q[4] ^ var22;
         var22 = this.q[11] | this.q[17];
         var26 = this.q[5] ^ var22;
         var22 = var34 | this.q[23];
         var28 = this.q[11] ^ var22;
         var22 = this.q[23] & this.q[4];
         var30 = var34 ^ var22;
         var22 = this.q[4] | this.q[5];
         var32 = this.q[23] ^ var22;
         this.q[4] = var24;
         this.q[5] = var26;
         this.q[11] = var28;
         this.q[17] = var30;
         this.q[23] = var32;
         var34 = ~this.q[8];
         var22 = var34 & this.q[14];
         var24 = this.q[2] ^ var22;
         var22 = this.q[14] | this.q[15];
         var26 = var34 ^ var22;
         var22 = this.q[15] & this.q[21];
         var28 = this.q[14] ^ var22;
         var22 = this.q[21] | this.q[2];
         var30 = this.q[15] ^ var22;
         var22 = this.q[2] & this.q[8];
         var32 = this.q[21] ^ var22;
         this.q[2] = var24;
         this.q[8] = var26;
         this.q[14] = var28;
         this.q[15] = var30;
         this.q[21] = var32;
         this.q[0] = this.q[0] ^ xK[var36 + 0];
         var12 = this.q[6] ^ this.q[9];
         var14 = this.q[7] ^ this.q[5];
         var12 ^= this.q[8] ^ var14;
         var12 = var12 << 1 | var12 >>> 63;
         var16 = this.q[24] ^ this.q[22];
         var18 = this.q[20] ^ this.q[23];
         var12 ^= this.q[21];
         var16 ^= var18;
         var37 = var12 ^ var16;
         var12 = this.q[12] ^ this.q[10];
         var14 = this.q[13] ^ this.q[11];
         var12 ^= this.q[14] ^ var14;
         var12 = var12 << 1 | var12 >>> 63;
         var16 = this.q[0] ^ this.q[3];
         var18 = this.q[1] ^ this.q[4];
         var12 ^= this.q[2];
         var16 ^= var18;
         var4 = var12 ^ var16;
         var12 = this.q[18] ^ this.q[16];
         var14 = this.q[19] ^ this.q[17];
         var12 ^= this.q[15] ^ var14;
         var12 = var12 << 1 | var12 >>> 63;
         var16 = this.q[6] ^ this.q[9];
         var18 = this.q[7] ^ this.q[5];
         var12 ^= this.q[8];
         var16 ^= var18;
         var6 = var12 ^ var16;
         var12 = this.q[24] ^ this.q[22];
         var14 = this.q[20] ^ this.q[23];
         var12 ^= this.q[21] ^ var14;
         var12 = var12 << 1 | var12 >>> 63;
         var16 = this.q[12] ^ this.q[10];
         var18 = this.q[13] ^ this.q[11];
         var12 ^= this.q[14];
         var16 ^= var18;
         var8 = var12 ^ var16;
         var12 = this.q[0] ^ this.q[3];
         var14 = this.q[1] ^ this.q[4];
         var12 ^= this.q[2] ^ var14;
         var12 = var12 << 1 | var12 >>> 63;
         var16 = this.q[18] ^ this.q[16];
         var18 = this.q[19] ^ this.q[17];
         var12 ^= this.q[15];
         var16 ^= var18;
         var10 = var12 ^ var16;
         this.q[0] = this.q[0] ^ var37;
         this.q[3] = this.q[3] ^ var37;
         this.q[1] = this.q[1] ^ var37;
         this.q[4] = this.q[4] ^ var37;
         this.q[2] = this.q[2] ^ var37;
         this.q[6] = this.q[6] ^ var4;
         this.q[9] = this.q[9] ^ var4;
         this.q[7] = this.q[7] ^ var4;
         this.q[5] = this.q[5] ^ var4;
         this.q[8] = this.q[8] ^ var4;
         this.q[12] = this.q[12] ^ var6;
         this.q[10] = this.q[10] ^ var6;
         this.q[13] = this.q[13] ^ var6;
         this.q[11] = this.q[11] ^ var6;
         this.q[14] = this.q[14] ^ var6;
         this.q[18] = this.q[18] ^ var8;
         this.q[16] = this.q[16] ^ var8;
         this.q[19] = this.q[19] ^ var8;
         this.q[17] = this.q[17] ^ var8;
         this.q[15] = this.q[15] ^ var8;
         this.q[24] = this.q[24] ^ var10;
         this.q[22] = this.q[22] ^ var10;
         this.q[20] = this.q[20] ^ var10;
         this.q[23] = this.q[23] ^ var10;
         this.q[21] = this.q[21] ^ var10;
         this.q[3] = this.q[3] << 36 | this.q[3] >>> 28;
         this.q[1] = this.q[1] << 3 | this.q[1] >>> 61;
         this.q[4] = this.q[4] << 41 | this.q[4] >>> 23;
         this.q[2] = this.q[2] << 18 | this.q[2] >>> 46;
         this.q[6] = this.q[6] << 1 | this.q[6] >>> 63;
         this.q[9] = this.q[9] << 44 | this.q[9] >>> 20;
         this.q[7] = this.q[7] << 10 | this.q[7] >>> 54;
         this.q[5] = this.q[5] << 45 | this.q[5] >>> 19;
         this.q[8] = this.q[8] << 2 | this.q[8] >>> 62;
         this.q[12] = this.q[12] << 62 | this.q[12] >>> 2;
         this.q[10] = this.q[10] << 6 | this.q[10] >>> 58;
         this.q[13] = this.q[13] << 43 | this.q[13] >>> 21;
         this.q[11] = this.q[11] << 15 | this.q[11] >>> 49;
         this.q[14] = this.q[14] << 61 | this.q[14] >>> 3;
         this.q[18] = this.q[18] << 28 | this.q[18] >>> 36;
         this.q[16] = this.q[16] << 55 | this.q[16] >>> 9;
         this.q[19] = this.q[19] << 25 | this.q[19] >>> 39;
         this.q[17] = this.q[17] << 21 | this.q[17] >>> 43;
         this.q[15] = this.q[15] << 56 | this.q[15] >>> 8;
         this.q[24] = this.q[24] << 27 | this.q[24] >>> 37;
         this.q[22] = this.q[22] << 20 | this.q[22] >>> 44;
         this.q[20] = this.q[20] << 39 | this.q[20] >>> 25;
         this.q[23] = this.q[23] << 8 | this.q[23] >>> 56;
         this.q[21] = this.q[21] << 14 | this.q[21] >>> 50;
         var34 = ~this.q[13];
         var22 = this.q[9] | this.q[13];
         var24 = this.q[0] ^ var22;
         var22 = var34 | this.q[17];
         var26 = this.q[9] ^ var22;
         var22 = this.q[17] & this.q[21];
         var28 = this.q[13] ^ var22;
         var22 = this.q[21] | this.q[0];
         var30 = this.q[17] ^ var22;
         var22 = this.q[0] & this.q[9];
         var32 = this.q[21] ^ var22;
         this.q[0] = var24;
         this.q[9] = var26;
         this.q[13] = var28;
         this.q[17] = var30;
         this.q[21] = var32;
         var34 = ~this.q[14];
         var22 = this.q[22] | this.q[1];
         var24 = this.q[18] ^ var22;
         var22 = this.q[1] & this.q[5];
         var26 = this.q[22] ^ var22;
         var22 = this.q[5] | var34;
         var28 = this.q[1] ^ var22;
         var22 = this.q[14] | this.q[18];
         var30 = this.q[5] ^ var22;
         var22 = this.q[18] & this.q[22];
         var32 = this.q[14] ^ var22;
         this.q[18] = var24;
         this.q[22] = var26;
         this.q[1] = var28;
         this.q[5] = var30;
         this.q[14] = var32;
         var34 = ~this.q[23];
         var22 = this.q[10] | this.q[19];
         var24 = this.q[6] ^ var22;
         var22 = this.q[19] & this.q[23];
         var26 = this.q[10] ^ var22;
         var22 = var34 & this.q[2];
         var28 = this.q[19] ^ var22;
         var22 = this.q[2] | this.q[6];
         var30 = var34 ^ var22;
         var22 = this.q[6] & this.q[10];
         var32 = this.q[2] ^ var22;
         this.q[6] = var24;
         this.q[10] = var26;
         this.q[19] = var28;
         this.q[23] = var30;
         this.q[2] = var32;
         var34 = ~this.q[11];
         var22 = this.q[3] & this.q[7];
         var24 = this.q[24] ^ var22;
         var22 = this.q[7] | this.q[11];
         var26 = this.q[3] ^ var22;
         var22 = var34 | this.q[15];
         var28 = this.q[7] ^ var22;
         var22 = this.q[15] & this.q[24];
         var30 = var34 ^ var22;
         var22 = this.q[24] | this.q[3];
         var32 = this.q[15] ^ var22;
         this.q[24] = var24;
         this.q[3] = var26;
         this.q[7] = var28;
         this.q[11] = var30;
         this.q[15] = var32;
         var34 = ~this.q[16];
         var22 = var34 & this.q[20];
         var24 = this.q[12] ^ var22;
         var22 = this.q[20] | this.q[4];
         var26 = var34 ^ var22;
         var22 = this.q[4] & this.q[8];
         var28 = this.q[20] ^ var22;
         var22 = this.q[8] | this.q[12];
         var30 = this.q[4] ^ var22;
         var22 = this.q[12] & this.q[16];
         var32 = this.q[8] ^ var22;
         this.q[12] = var24;
         this.q[16] = var26;
         this.q[20] = var28;
         this.q[4] = var30;
         this.q[8] = var32;
         this.q[0] = this.q[0] ^ xK[var36 + 1];
         long var20 = this.q[5];
         this.q[5] = this.q[18];
         this.q[18] = this.q[11];
         this.q[11] = this.q[10];
         this.q[10] = this.q[6];
         this.q[6] = this.q[22];
         this.q[22] = this.q[20];
         this.q[20] = this.q[12];
         this.q[12] = this.q[19];
         this.q[19] = this.q[15];
         this.q[15] = this.q[24];
         this.q[24] = this.q[8];
         this.q[8] = var20;
         var20 = this.q[1];
         this.q[1] = this.q[9];
         this.q[9] = this.q[14];
         this.q[14] = this.q[2];
         this.q[2] = this.q[13];
         this.q[13] = this.q[23];
         this.q[23] = this.q[4];
         this.q[4] = this.q[21];
         this.q[21] = this.q[16];
         this.q[16] = this.q[3];
         this.q[3] = this.q[17];
         this.q[17] = this.q[7];
         this.q[7] = var20;
      }
   }

   @Override
   protected void q(byte[] var1, int var2) {
      int var3 = this.Uv();
      byte[] var4 = this.oW();
      if (var3 + 1 == var4.length) {
         var4[var3] = -127;
      } else {
         var4[var3] = 1;

         for (int var5 = var3 + 1; var5 < var4.length - 1; var5++) {
            var4[var5] = 0;
         }

         var4[var4.length - 1] = -128;
      }

      this.q(var4);
      this.q[1] = ~this.q[1];
      this.q[2] = ~this.q[2];
      this.q[8] = ~this.q[8];
      this.q[12] = ~this.q[12];
      this.q[17] = ~this.q[17];
      this.q[20] = ~this.q[20];
      int var7 = this.engineGetDigestLength();

      for (byte var6 = 0; var6 < var7; var6 += 8) {
         q(this.q[var6 >>> 3], this.RF, var6);
      }

      System.arraycopy(this.RF, 0, var1, var2, var7);
   }

   @Override
   protected void xK() {
      this.q = new long[25];
      this.RF = new byte[this.engineGetDigestLength() + 7 & -8];
      this.nf();
   }

   @Override
   public int RF() {
      return 200 - 2 * this.engineGetDigestLength();
   }

   private final void nf() {
      for (int var1 = 0; var1 < 25; var1++) {
         this.q[var1] = 0L;
      }

      this.q[1] = -1L;
      this.q[2] = -1L;
      this.q[8] = -1L;
      this.q[12] = -1L;
      this.q[17] = -1L;
      this.q[20] = -1L;
   }

   protected cjg q(cjk var1) {
      System.arraycopy(this.q, 0, var1.q, 0, 25);
      return super.q(var1);
   }

   @Override
   public String toString() {
      return "Keccak-" + (this.engineGetDigestLength() << 3);
   }
}
