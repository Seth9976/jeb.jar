package com.pnfsoftware.jeb.corei.parsers.ihex;

import com.pnfsoftware.jeb.util.collect.ReferenceCounter;
import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;
import java.nio.ByteBuffer;

public class K {
   private static final int[] UT = new int[]{-1, 0, 2, 4, 2, 4};
   ByteBuffer pC;
   boolean A;
   boolean kS;
   ReferenceCounter wS = new ReferenceCounter();

   public K(ByteBuffer var1, boolean var2, boolean var3) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
   }

   public K(ByteBuffer var1) {
      this(var1, false, false);
   }

   public boolean pC() {
      return this.pC.remaining() > 0;
   }

   public int pC(int var1) {
      return this.wS.get(var1);
   }

   public Sv A() throws IOException {
      return this.pC(false);
   }

   public Sv pC(boolean var1) throws IOException {
      if (!var1 && this.pC.get() != 58) {
         throw new IOException("Illegal start of record, expected column");
      } else {
         int var2 = pC(this.pC);
         int var3 = A(this.pC);
         int var4 = pC(this.pC);
         if (this.A || var4 >= 0 && var4 <= 5) {
            if (var4 >= 1 && var4 <= 5 && var2 != UT[var4]) {
               throw new IOException(Strings.ff("Illegal data size for record %d: actual=%d expected=%d", var4, var2, UT[var4]));
            } else {
               this.wS.inc(var4);
               byte[] var5 = new byte[var2];
               pC(this.pC, var5, 0, var2);
               int var6 = pC(this.pC);
               int var7 = pC(var2, var3, var4, var5, 0);
               if (!this.kS && var7 != var6) {
                  throw new IOException(Strings.ff("Illegal checksum: actual=%02X, expected=%02X", var7, var6));
               } else {
                  kS(this.pC);
                  return new Sv(var3, var4, var5, var7 == var6);
               }
            }
         } else {
            throw new IOException("Illegal record type: " + var4);
         }
      }
   }

   public static int pC(ByteBuffer var0) throws IOException {
      if (var0.remaining() < 2) {
         throw new IOException("Not enough input data");
      } else {
         int var1 = 0;

         for (int var2 = 0; var2 < 2; var2++) {
            byte var3 = var0.get();
            int var4;
            if (var3 >= 48 && var3 <= 57) {
               var4 = var3 - 48;
            } else if (var3 >= 97 && var3 <= 102) {
               var4 = var3 - 97 + 10;
            } else {
               if (var3 < 65 || var3 > 70) {
                  throw new IOException();
               }

               var4 = var3 - 65 + 10;
            }

            var1 = var1 << 4 | var4;
         }

         return var1;
      }
   }

   public static int A(ByteBuffer var0) throws IOException {
      if (var0.remaining() < 4) {
         throw new IOException("Not enough input data");
      } else {
         int var1 = 0;

         for (int var2 = 0; var2 < 4; var2++) {
            byte var3 = var0.get();
            int var4;
            if (var3 >= 48 && var3 <= 57) {
               var4 = var3 - 48;
            } else if (var3 >= 97 && var3 <= 102) {
               var4 = var3 - 97 + 10;
            } else {
               if (var3 < 65 || var3 > 70) {
                  throw new IOException();
               }

               var4 = var3 - 65 + 10;
            }

            var1 = var1 << 4 | var4;
         }

         return var1;
      }
   }

   public static void pC(ByteBuffer var0, byte[] var1, int var2, int var3) throws IOException {
      for (int var4 = var2; var4 < var2 + var3; var4++) {
         var1[var4] = (byte)pC(var0);
      }
   }

   public static void kS(ByteBuffer var0) throws IOException {
      if (var0.remaining() < 1) {
         throw new IOException("Not enough input data to read EOL");
      } else {
         byte var1 = var0.get();
         if (var1 == 13) {
            if (var0.remaining() < 1) {
               throw new IOException("Not enough input data to read EOL/LF");
            }

            var1 = var0.get();
         }

         if (var1 != 10) {
            throw new IOException();
         }
      }
   }

   public static int pC(int var0, int var1, int var2, byte[] var3, int var4) {
      int var5 = var0 + (var1 & 0xFF) + (var1 >> 8) + var2;

      for (int var6 = var4; var6 < var4 + var0; var6++) {
         var5 += var3[var6];
      }

      return ~var5 + 1 & 0xFF;
   }
}
