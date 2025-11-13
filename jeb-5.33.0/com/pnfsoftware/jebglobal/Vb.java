package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Vb extends tH {
   private static Charset UO = Charset.forName("UTF-8");
   private static Charset Ab = Charset.forName("UTF-16LE");
   private String rl;
   int E;
   int sY;
   int ys;
   int ld;
   int gp;
   int[] oT;
   int[] fI;
   byte[] WR;
   byte[] NS;
   List vP;
   List xC;
   byte[] ED;
   byte[] Sc;
   boolean ah;
   boolean eP;

   public Vb(EX var1, String var2) throws IOException {
      this(var1, var2, false);
   }

   public Vb(EX var1, String var2, boolean var3) throws IOException {
      super(var1, 1);
      this.pC(var1, var2, var3);
   }

   public Vb(tH var1, EX var2, String var3, boolean var4) throws IOException {
      super(var1, 1);
      this.pC(var2, var3, var4);
   }

   private void pC(EX var1, String var2, boolean var3) throws IOException {
      this.rl = var2;
      this.ah = var3;
      this.E = var1.readInt();
      this.sY = var1.readInt();
      this.ys = var1.readInt();
      this.ld = var1.readInt();
      this.gp = var1.readInt();
      if (var3) {
         this.ED = this.A(var1);
      } else {
         this.pC(var1);
      }

      int var4 = (this.A + this.ld - var1.pC()) / 4;
      int var5 = var1.pC() + 4 * (this.E + this.sY);
      if (this.ld != 0 && var5 > this.A + this.ld) {
         int var6 = var4 - this.sY;
         if (var6 <= 0) {
            throw new IOException("Malformed string pool, cannot be adjusted");
         }

         var1.pC(S.L("Malformed string pool, string count adjusted to %d (was %d)"), var6, this.E);
         this.E = var6;
      }

      this.oT = bM.pC(var1, this.E);
      this.fI = bM.pC(var1, this.sY);
      if (var3) {
         this.Sc = this.A(var1, this.ld);
      } else if (this.ld != 0) {
         this.pC(var1, this.ld);
      }

      if (this.E == 0) {
         this.WR = new byte[0];
      } else {
         int var8 = (this.sY == 0 ? this.UT : this.gp) - this.ld;
         int var7 = var1.A();
         if (var7 < var8) {
            var1.pC(S.L("Malformed string pool: truncated string data: missing %d bytes"), var8 - var7);
         }

         this.WR = new byte[var8];
         var1.readFully(this.WR);
      }

      this.vP = new ArrayList(this.E);
      this.sY(var1);
      if (this.sY == 0) {
         this.NS = new byte[0];
      } else {
         this.NS = new byte[this.UT - this.gp];
         var1.readFully(this.NS);
      }

      this.xC = new ArrayList(this.sY);
      this.ys(var1);
   }

   public int pC() {
      return this.oT.length;
   }

   public String pC(int var1, String var2) {
      return var1 >= 0 && var1 < this.vP.size() ? this.pC(var1) : var2;
   }

   public String pC(int var1) {
      return (String)this.vP.get(var1);
   }

   private void sY(EX var1) {
      for (int var2 = 0; var2 < this.E; var2++) {
         String var3;
         try {
            int var4 = this.oT[var2];
            if ((this.ys & 256) != 0) {
               int[] var6 = A(this.WR, var4);
               var4 = var6[0];
               int var5 = var6[1];
               var3 = Strings.decodeUTF8Ex(this.WR, var4, var5, true);
            } else {
               int[] var11 = pC(this.WR, var4);
               var4 = var11[0];
               int var10 = var11[1];
               var3 = new String(this.WR, var4, var10, Ab);
            }
         } catch (Exception var7) {
            var1.pC(S.L("Pool '%s': Cannot parse string #%d"), this.rl, var2);
            var3 = "__Illegal_String_" + var2;
         }

         this.vP.add(var3);
      }
   }

   private static int[] pC(byte[] var0, int var1) {
      int var2 = (var0[var1 + 1] & 255) << 8 | var0[var1] & 255;
      if ((var2 & 32768) != 0) {
         int var3 = (var0[var1 + 3] & 255) << 8;
         int var4 = var0[var1 + 2] & 255;
         var2 = ((var2 & 32767) << 16) + var3 + var4;
         return new int[]{var1 + 4, var2 * 2};
      } else {
         return new int[]{var1 + 2, var2 * 2};
      }
   }

   private static int[] A(byte[] var0, int var1) {
      byte var2 = var0[var1];
      if ((var2 & 128) != 0) {
         var1 += 2;
      } else {
         var1++;
      }

      var2 = var0[var1];
      var1++;
      int var3;
      if ((var2 & 128) != 0) {
         int var4 = var0[var1] & 255;
         var3 = ((var2 & 127) << 8) + var4;
         var1++;
      } else {
         var3 = var2;
      }

      return new int[]{var1, var3};
   }

   public String A(int var1) {
      List var2 = this.kS(var1);
      return var2 == null ? null : Strings.join(" ", var2);
   }

   public List kS(int var1) {
      return var1 >= 0 && var1 < this.xC.size() ? (List)this.xC.get(var1) : null;
   }

   private void ys(EX var1) {
      for (int var2 = 0; var2 < this.sY; var2++) {
         Object var3 = new ArrayList();

         try {
            int var4 = this.fI[var2];
            if (var4 >= 0 && var4 < this.WR.length) {
               ByteBuffer var5 = ByteBuffer.wrap(this.NS);
               var5.order(ByteOrder.LITTLE_ENDIAN);
               var5.position(var4);
               var3 = new ArrayList();

               while (var5.remaining() >= 4) {
                  int var6 = var5.getInt();
                  if (var6 == -1) {
                     break;
                  }

                  if (var5.remaining() < 8) {
                     pC.warn(S.L("Not enough data to read a style span"));
                     break;
                  }

                  int var7 = var5.getInt();
                  int var8 = var5.getInt();
                  String var9 = this.pC(var6);
                  var3.add(new Vb.Av(var9, var7, var8));
               }
            }
         } catch (Exception var10) {
            var1.pC(S.L("Pool '%s': Cannot parse style #%d"), this.rl, var2);
            var3 = Collections.emptyList();
         }

         this.xC.add(var3);
      }
   }

   @Override
   public String toString() {
      return Strings.ff("String pool: %d strings, %d styles", this.vP.size(), this.xC.size());
   }

   public void A(int var1, String var2) {
      if (var1 >= 0 && var1 < this.vP.size()) {
         this.vP.set(var1, var2);
         this.eP = true;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public static class Av {
      public String pC;
      public int A;
      public int kS;

      public Av(String var1, int var2, int var3) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
      }

      @Override
      public String toString() {
         return Strings.ff("%s:%d,%d", this.pC, this.A, this.kS);
      }
   }
}
