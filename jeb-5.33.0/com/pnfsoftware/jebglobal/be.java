package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.Version;

public class be {
   private long pC;
   private int A;
   private be.Av kS;

   public be(long var1, int var3, be.Av var4) {
      this.pC = var1;
      this.A = var3;
      this.kS = var4;
   }

   public long pC() {
      return this.pC;
   }

   public int A() {
      return this.A;
   }

   public be.Av kS() {
      return this.kS;
   }

   public static class Av {
      private Version pC;
      private String A;
      private byte[] kS;
      private String wS;
      private int UT;

      public Av(Version var1, String var2, byte[] var3, String var4, int var5) {
         if (var1 != null && var2 != null && var2.length() != 0 && var3 != null && var3.length == 32) {
            this.pC = var1;
            this.A = var2;
            this.kS = var3;
            this.wS = var4;
            this.UT = var5;
         } else {
            throw new IllegalArgumentException();
         }
      }

      public Version pC() {
         return this.pC;
      }

      public int A() {
         return this.pC.getChannel();
      }

      public String kS() {
         return this.A;
      }

      public byte[] wS() {
         return this.kS;
      }

      public String UT() {
         return this.wS;
      }

      public int E() {
         return this.UT;
      }
   }
}
