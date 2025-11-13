package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.Version;

public class vn {
   private long q;
   private int RF;
   private vn.eo xK;

   public vn(long var1, int var3, vn.eo var4) {
      this.q = var1;
      this.RF = var3;
      this.xK = var4;
   }

   public long q() {
      return this.q;
   }

   public int RF() {
      return this.RF;
   }

   public vn.eo xK() {
      return this.xK;
   }

   public static class eo {
      private Version q;
      private String RF;
      private byte[] xK;
      private String Dw;
      private int Uv;

      public eo(Version var1, String var2, byte[] var3, String var4, int var5) {
         if (var1 != null && var2 != null && var2.length() != 0 && var3 != null && var3.length == 32) {
            this.q = var1;
            this.RF = var2;
            this.xK = var3;
            this.Dw = var4;
            this.Uv = var5;
         } else {
            throw new IllegalArgumentException();
         }
      }

      public Version q() {
         return this.q;
      }

      public int RF() {
         return this.q.getChannel();
      }

      public String xK() {
         return this.RF;
      }

      public byte[] Dw() {
         return this.xK;
      }

      public String Uv() {
         return this.Dw;
      }

      public int oW() {
         return this.Uv;
      }
   }
}
