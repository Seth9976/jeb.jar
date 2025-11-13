package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.format.TextBuilder;
import com.pnfsoftware.jeb.util.io.LEDataOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class AX extends jA {
   private static Charset KT = Charset.forName("UTF-8");
   private static Charset Gf = Charset.forName("UTF-16LE");
   private String Ef;
   public static final int oW = 1;
   public static final int gO = 256;
   int nf;
   int gP;
   int za;
   int lm;
   int zz;
   int[] JY;
   int[] HF;
   byte[] LK;
   byte[] io;
   List qa;
   List Hk;
   byte[] Me;
   byte[] PV;
   boolean oQ;
   boolean xW;

   public AX(uL var1, String var2) throws IOException {
      this(var1, var2, false);
   }

   public AX(uL var1, String var2, boolean var3) throws IOException {
      super(var1, 1);
      this.q(var1, var2, var3);
   }

   public AX(jA var1, uL var2, String var3, boolean var4) throws IOException {
      super(var1, 1);
      this.q(var2, var3, var4);
   }

   private void q(uL var1, String var2, boolean var3) throws IOException {
      this.Ef = var2;
      this.oQ = var3;
      this.nf = var1.readInt();
      this.gP = var1.readInt();
      this.za = var1.readInt();
      this.lm = var1.readInt();
      this.zz = var1.readInt();
      if (var3) {
         this.Me = this.Dw(var1);
      } else {
         this.xK(var1);
      }

      int var4 = (this.RF + this.lm - var1.q()) / 4;
      int var5 = var1.q() + 4 * (this.nf + this.gP);
      if (this.lm != 0 && var5 > this.RF + this.lm) {
         int var6 = var4 - this.gP;
         if (var6 <= 0) {
            throw new IOException("Malformed string pool, cannot be adjusted");
         }

         var1.q(S.L("Malformed string pool, string count adjusted to %d (was %d)"), var6, this.nf);
         this.nf = var6;
      }

      this.JY = zR.q(var1, this.nf);
      this.HF = zR.q(var1, this.gP);
      if (var3) {
         this.PV = this.RF(var1, this.lm);
      } else if (this.lm != 0) {
         this.q(var1, this.lm);
      }

      if (this.nf == 0) {
         this.LK = new byte[0];
      } else {
         int var8 = (this.gP == 0 ? this.Uv : this.zz) - this.lm;
         int var7 = var1.RF();
         if (var7 < var8) {
            var1.q(S.L("Malformed string pool: truncated string data: missing %d bytes"), var8 - var7);
         }

         this.LK = new byte[var8];
         var1.readFully(this.LK);
      }

      this.qa = new ArrayList(this.nf);
      this.za(var1);
      if (this.gP == 0) {
         this.io = new byte[0];
      } else {
         this.io = new byte[this.Uv - this.zz];
         var1.readFully(this.io);
      }

      this.Hk = new ArrayList(this.gP);
      this.lm(var1);
   }

   public int q() {
      return this.JY.length;
   }

   public int RF() {
      return this.HF.length;
   }

   public String q(int var1, String var2) {
      return var1 >= 0 && var1 < this.qa.size() ? this.q(var1) : var2;
   }

   public String q(int var1) {
      return (String)this.qa.get(var1);
   }

   public int q(String var1) {
      for (int var2 = 0; var2 < this.qa.size(); var2++) {
         if (var1.equals(this.q(var2))) {
            return var2;
         }
      }

      return -1;
   }

   private void za(uL var1) {
      for (int var2 = 0; var2 < this.nf; var2++) {
         String var3;
         try {
            int var4 = this.JY[var2];
            if ((this.za & 256) != 0) {
               int[] var6 = RF(this.LK, var4);
               var4 = var6[0];
               int var5 = var6[1];
               var3 = Strings.decodeUTF8Ex(this.LK, var4, var5, true);
            } else {
               int[] var11 = q(this.LK, var4);
               var4 = var11[0];
               int var10 = var11[1];
               var3 = new String(this.LK, var4, var10, Gf);
            }
         } catch (Exception var7) {
            var1.q(S.L("Pool '%s': Cannot parse string #%d"), this.Ef, var2);
            var3 = "__Illegal_String_" + var2;
         }

         this.qa.add(var3);
      }
   }

   public int xK() {
      return (this.za & 256) != 0 ? 8 : 16;
   }

   public boolean Dw() {
      return (this.za & 1) != 0;
   }

   private static int[] q(byte[] var0, int var1) {
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

   private static int[] RF(byte[] var0, int var1) {
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

   public String RF(int var1) {
      List var2 = this.xK(var1);
      return var2 == null ? null : Strings.join(" ", var2);
   }

   public List xK(int var1) {
      return var1 >= 0 && var1 < this.Hk.size() ? (List)this.Hk.get(var1) : null;
   }

   private void lm(uL var1) {
      for (int var2 = 0; var2 < this.gP; var2++) {
         Object var3 = new ArrayList();

         try {
            int var4 = this.HF[var2];
            if (var4 >= 0 && var4 < this.LK.length) {
               ByteBuffer var5 = ByteBuffer.wrap(this.io);
               var5.order(ByteOrder.LITTLE_ENDIAN);
               var5.position(var4);
               var3 = new ArrayList();

               while (var5.remaining() >= 4) {
                  int var6 = var5.getInt();
                  if (var6 == -1) {
                     break;
                  }

                  if (var5.remaining() < 8) {
                     q.warn(S.L("Not enough data to read a style span"));
                     break;
                  }

                  int var7 = var5.getInt();
                  int var8 = var5.getInt();
                  String var9 = this.q(var6);
                  var3.add(new AX.eo(var9, var7, var8));
               }
            }
         } catch (Exception var10) {
            var1.q(S.L("Pool '%s': Cannot parse style #%d"), this.Ef, var2);
            var3 = Collections.emptyList();
         }

         this.Hk.add(var3);
      }
   }

   @Override
   public void q(TextBuilder var1) {
      for (int var2 = 0; var2 < this.JY.length; var2++) {
         var1.appendLine("- %d(%Xh) \"%s\"", var2, var2, Formatter.escapeString(this.q(var2)));
      }
   }

   @Override
   public String toString() {
      return Strings.ff("String pool: %d strings, %d styles", this.qa.size(), this.Hk.size());
   }

   public void RF(int var1, String var2) {
      if (var1 >= 0 && var1 < this.qa.size()) {
         this.qa.set(var1, var2);
         this.xW = true;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public int RF(String var1) {
      return this.q(var1, false);
   }

   public int q(String var1, boolean var2) {
      int var3 = var2 ? -1 : this.q(var1);
      if (var3 < 0) {
         this.qa.add(var1);
         var3 = this.qa.size() - 1;
      }

      return var3;
   }

   @Override
   public void q(pK var1) {
      byte[] var2 = this.Uv();
      var1.q.writeBytes(var2);
   }

   public byte[] Uv() {
      if (!this.oQ) {
         throw new IllegalStateException("This object does not support pool bytes generation");
      } else if (this.xW) {
         throw new RuntimeException("Existing pool strings were modified, the pool cannot be generated");
      } else {
         try {
            int var1 = this.qa.size() - this.nf;
            int var2 = this.za;
            if (var1 > 0) {
               var2 = this.za & -2;
            }

            List var3 = ArrayUtil.asList(this.JY);
            int var4 = this.LK.length;
            int var5 = 0;
            ByteArrayOutputStream var6 = new ByteArrayOutputStream();
            LEDataOutputStream var7 = new LEDataOutputStream(var6);

            for (int var8 = this.nf; var8 < this.qa.size(); var8++) {
               String var9 = (String)this.qa.get(var8);
               if (this.xK() == 16) {
                  byte[] var10 = var9.getBytes(Gf);
                  int var11 = var10.length / 2;
                  if (var11 >= 32768) {
                     throw new RuntimeException("Unsupported UTF-16 reencoding, too many chars: " + var11);
                  }

                  var7.writeShort(var11);
                  var7.write(var10);
                  var7.writeShort(0);
                  int var12 = 2 + var10.length + 2;
                  var5 += var12;
                  var3.add(var4);
                  var4 += var12;
               } else {
                  if (this.xK() != 8) {
                     throw new RuntimeException();
                  }

                  byte[] var22 = var9.getBytes(KT);
                  int var24 = var22.length;
                  if (var24 >= 128) {
                     throw new RuntimeException("Unsupported UTF-8 reencoding, too many bytes: " + var24);
                  }

                  var7.writeByte(var24);
                  var7.writeByte(var24);
                  var7.write(var22);
                  var7.writeByte(0);
                  int var26 = 2 + var22.length + 1;
                  var5 += var26;
                  var3.add(var4);
                  var4 += var26;
               }
            }

            if (var5 > 0 && var5 % 4 != 0) {
               int var19 = 4 - var5 % 4;
               var5 += var19;

               while (var19-- > 0) {
                  var7.writeByte(0);
               }
            }

            var7.close();
            byte[] var20 = var6.toByteArray();
            int var21 = this.Dw + var3.size() * 4 + this.HF.length * 4 + this.PV.length + this.LK.length + var20.length + this.io.length;
            int var23 = this.lm + var1 * 4;
            int var25 = this.zz == 0 ? 0 : this.zz + var1 * 4;
            ByteArrayOutputStream var27 = new ByteArrayOutputStream();
            LEDataOutputStream var13 = new LEDataOutputStream(var27);
            var13.writeShort(this.xK);
            var13.writeShort(this.Dw);
            var13.writeInt(var21);
            var13.writeInt(this.qa.size());
            var13.writeInt(this.Hk.size());
            var13.writeInt(var2);
            var13.writeInt(var23);
            var13.writeInt(var25);
            var13.write(this.Me);

            for (int var15 : var3) {
               var13.writeInt(var15);
            }

            for (int var17 : this.HF) {
               var13.writeInt(var17 + var5);
            }

            var13.write(this.PV);
            var13.write(this.LK);
            var13.write(var20);
            var13.write(this.io);
            var13.close();
            return var27.toByteArray();
         } catch (IOException var18) {
            throw new RuntimeException(var18);
         }
      }
   }

   public static class eo {
      public String q;
      public int RF;
      public int xK;

      public eo(String var1, int var2, int var3) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
      }

      @Override
      public String toString() {
         return Strings.ff("%s:%d,%d", this.q, this.RF, this.xK);
      }
   }
}
