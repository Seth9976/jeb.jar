package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.collect.BytePipe;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.ByteBufferUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Date;

public class cqi {
   private static final ILogger rL = GlobalLog.getLogger(cqi.class);
   public static final int q = 1;
   public static final int RF = 2;
   public static final int xK = 4;
   public static final int Dw = 8;
   public static final int Uv = 16;
   public static final int oW = 32;
   public static final int gO = 0;
   public static final int nf = 1;
   public static final int gP = 2;
   public static final int za = 3;
   public static final int lm = 4;
   public static final int zz = 65536;
   public static final int JY = 131072;
   public static final int HF = 196608;
   public static final int LK = 262144;
   public static final int io = 0;
   public static final int qa = 1;
   public static final int Hk = 2;
   public static final int Me = 3;
   public static final int PV = 4;
   public static final int oQ = 5;
   public static final int xW = 7;
   public static final int KT = 0;
   public static final int Gf = 1;
   public static final int Ef = 2;
   public static final int cC = 3;
   public static final int sH = 4;
   public static final int CE = 5;
   public static final int wF = 6;
   public static final int If = 7;
   public static final int Dz = 8;
   public static final int mI = 9;
   public static final int jq = 10;
   public static final int ui = 12;
   public static final int TX = 1;
   public static final int Rr = 2;
   public static final int EB = 3;
   ByteBuffer Xo;
   StringBuilder Bu;
   int IN;

   public static String q(int var0) {
      String var1 = "";
      if ((var0 & 1) != 0) {
         var1 = var1 + "Debug ";
         var0 |= -2;
      }

      if ((var0 & 2) != 0) {
         var1 = var1 + "PreRelease ";
         var0 |= -3;
      }

      if ((var0 & 4) != 0) {
         var1 = var1 + "Patched ";
         var0 |= -5;
      }

      if ((var0 & 8) != 0) {
         var1 = var1 + "PrivateBuild ";
         var0 |= -9;
      }

      if ((var0 & 16) != 0) {
         var1 = var1 + "InfoInferred ";
         var0 |= -17;
      }

      if ((var0 & 32) != 0) {
         var1 = var1 + "SpecialBuild ";
         var0 |= -33;
      }

      if (var1.isEmpty() || var0 != 0) {
         var1 = var1 + Strings.ff("0x%X", var0);
      }

      return var1;
   }

   public static String RF(int var0) {
      String var1 = "";
      switch (var0 & 65535) {
         case 1:
            var1 = var1 + "Win16 ";
            break;
         case 2:
            var1 = var1 + "Pm16 ";
            break;
         case 3:
            var1 = var1 + "Pm32 ";
            break;
         case 4:
            var1 = var1 + "Win32 ";
      }

      switch (var0 & -65536) {
         case 65536:
            var1 = var1 + "Dos ";
            break;
         case 131072:
            var1 = var1 + "OS2 16-bit ";
            break;
         case 196608:
            var1 = var1 + "OS2 32-bit ";
            break;
         case 262144:
            var1 = var1 + "NT ";
      }

      return var1 + Strings.ff("(0x%X)", var0);
   }

   public static String q(int var0, int var1) {
      String var2 = "";

      return switch (var0) {
         case 1 -> var2 + "Application";
         case 2 -> var2 + "DLL";
         case 3 -> {
            var2 = var2 + "Driver (";

            yield switch (var1) {
               case 1 -> var2 + "Printer";
               case 2 -> var2 + "Keyboard";
               case 3 -> var2 + "Language";
               case 4 -> var2 + "Display";
               case 5 -> var2 + "Mouse";
               case 6 -> var2 + "Network";
               case 7 -> var2 + "System";
               case 8 -> var2 + "Installable";
               case 9 -> var2 + "Sound";
               case 10 -> var2 + "Communications";
               default -> var2 + Strings.ff("0x%X", var1);
               case 12 -> var2 + "VersionedPrinter";
            } + ")";
         }
         case 4 -> {
            var2 = var2 + "Font (";

            yield switch (var1) {
               case 1 -> var2 + "Raster";
               case 2 -> var2 + "Vector";
               case 3 -> var2 + "Truetype";
               default -> var2 + Strings.ff("0x%X", var1);
            } + ")";
         }
         case 5 -> var2 + "Vxd";
         default -> var2 + Strings.ff("0x%X (0x%X)", var0, var1);
         case 7 -> var2 + "Library";
      };
   }

   public cqi(byte[] var1) {
      this.Xo = ByteBuffer.wrap(var1);
      this.Xo.order(ByteOrder.LITTLE_ENDIAN);
   }

   public String q() throws Exception {
      this.Xo.position(0);
      this.Bu = new StringBuilder();
      this.IN = 0;
      this.RF();
      return this.Bu.toString();
   }

   cqi.eo q(String var1) {
      int var2 = this.Xo.position();
      int var3 = this.Xo.getShort() & '\uffff';
      int var4 = this.Xo.getShort() & '\uffff';
      int var5 = this.Xo.getShort() & '\uffff';
      String var6 = this.nf();
      if (var1 != null && !var1.equals(var6)) {
         throw new RuntimeException(Strings.ff("Unexpected key: \"%s\"", var6));
      } else {
         this.gP();
         return new cqi.eo(var2, var3, var4, var5, var6);
      }
   }

   void RF() {
      cqi.eo var1 = this.q("VS_VERSION_INFO");
      int var2 = 0;
      int var3 = 0;
      int var4 = 0;
      int var5 = 0;
      int var6 = 0;
      int var7 = 0;
      int var8 = 0;
      int var9 = 0;
      int var10 = 0;
      int var11 = 0;
      int var12 = 0;
      if (var1.xK >= 4) {
         this.Xo.getInt();
      }

      if (var1.xK >= 8) {
         this.Xo.getInt();
      }

      if (var1.xK >= 12) {
         var2 = this.Xo.getInt();
      }

      if (var1.xK >= 16) {
         var3 = this.Xo.getInt();
      }

      Strings.ff(this.Bu, "FileVersion: %d.%d.%d.%d\n", var2 >>> 16, var2 & 65535, var3 >>> 16, var3 & 65535);
      if (var1.xK >= 20) {
         var4 = this.Xo.getInt();
      }

      if (var1.xK >= 24) {
         var5 = this.Xo.getInt();
      }

      Strings.ff(this.Bu, "ProductVersion: %d.%d.%d.%d\n", var4 >>> 16, var4 & 65535, var5 >>> 16, var5 & 65535);
      if (var1.xK >= 28) {
         var6 = this.Xo.getInt();
      }

      if (var1.xK >= 32) {
         var7 = this.Xo.getInt();
      }

      Strings.ff(this.Bu, "FileFlags: %s\n", q(var7 & var6));
      if (var1.xK >= 36) {
         var8 = this.Xo.getInt();
      }

      Strings.ff(this.Bu, "FileOs: %s\n", RF(var8));
      if (var1.xK >= 40) {
         var9 = this.Xo.getInt();
      }

      if (var1.xK >= 44) {
         var10 = this.Xo.getInt();
      }

      Strings.ff(this.Bu, "FileType: %s\n", q(var9, var10));
      if (var1.xK >= 48) {
         var11 = this.Xo.getInt();
      }

      if (var1.xK >= 52) {
         var12 = this.Xo.getInt();
      }

      long var13 = (long)var11 << 32 | var12 & 4294967295L;
      if (var13 != 0L) {
         Date var15 = new Date((var13 - 116444736000000000L) / 10000L);
         Strings.ff(this.Bu, "FileDate: %s\n", var15);
      }

      if (var1.xK > 52) {
         ByteBufferUtil.skip(this.Xo, var1.xK - 52);
      }

      this.gP();
      this.Bu.append("\n");

      while (this.q(var1)) {
         cqi.eo var16 = this.q(null);
         if (var16.Uv.equals("StringFileInfo")) {
            this.Xo.position(var16.q);
            this.xK();
         } else {
            if (!var16.Uv.equals("VarFileInfo")) {
               throw new RuntimeException();
            }

            this.Xo.position(var16.q);
            this.oW();
         }
      }
   }

   void xK() {
      this.q("Block {\n");
      this.IN++;
      cqi.eo var1 = this.q("StringFileInfo");

      while (this.q(var1)) {
         this.Dw();
         this.gP();
      }

      this.IN--;
      this.q("}\n");
   }

   void Dw() {
      cqi.eo var1 = this.q(null);
      int var2 = Integer.parseInt(var1.Uv, 16);
      int var3 = var2 >>> 16;
      int var4 = var2 & 65535;
      Object[] var10000 = new Object[]{var3, var4};
      this.q("Block 0x%X,0x%X {\n", var3, var4);
      this.IN++;

      while (this.q(var1)) {
         this.Uv();
         this.gP();
      }

      this.IN--;
      this.q("}\n");
   }

   cqi.CU Uv() {
      cqi.eo var1 = this.q(null);
      String var2 = this.nf();
      if (var2.length() != var1.xK - 1) {
         throw new RuntimeException();
      } else {
         this.q("\"%s\": \"%s\"\n", var1.Uv, var2);
         return new cqi.CU(var1.Dw, var1.Uv, var2);
      }
   }

   void oW() {
      this.q("Block {\n");
      this.IN++;
      cqi.eo var1 = this.q("VarFileInfo");

      while (this.q(var1)) {
         this.gO();
         this.gP();
      }

      this.IN--;
      this.q("}\n");
   }

   void gO() {
      this.q("\"Translation\": ");
      cqi.eo var1 = this.q("Translation");

      while (this.q(var1)) {
         int var2 = this.Xo.getInt();
         Strings.ff(this.Bu, "0x%X ", var2);
      }

      this.Bu.append("\n");
   }

   String nf() {
      BytePipe var1 = new BytePipe(32);

      while (true) {
         char var2 = this.Xo.getChar();
         if (var2 == 0) {
            return new String(var1.getAll(), Charset.forName("UTF-16LE"));
         }

         var1.append(var2 & 255);
         var1.append(var2 >> '\b' & 0xFF);
      }
   }

   String xK(int var1) {
      byte[] var2 = new byte[var1];
      this.Xo.get(var2);
      return new String(var2, Charset.forName("UTF-16LE"));
   }

   void gP() {
      int var1 = this.Xo.position();
      if (var1 % 4 != 0) {
         int var2 = 4 - var1 % 4;
         this.Xo.position(var1 + var2);
      }
   }

   boolean q(cqi.eo var1) {
      return this.Xo.position() < var1.q + var1.RF;
   }

   private void q(String var1, Object... var2) {
      String var3 = Strings.generate(' ', this.IN * 2) + Strings.ff(var1, var2);
      this.Bu.append(var3);
   }

   static class CU {
      int q;
      String RF;
      String xK;

      public CU(int var1, String var2, String var3) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
      }

      @Override
      public String toString() {
         return Strings.ff("%s:%s", this.RF, this.xK);
      }
   }

   static class eo {
      int q;
      int RF;
      int xK;
      int Dw;
      String Uv;

      eo(int var1, int var2, int var3, int var4, String var5) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
         this.Dw = var4;
         this.Uv = var5;
      }

      @Override
      public String toString() {
         return Strings.ff("%s/%d/%d", this.Uv, this.RF, this.xK);
      }
   }
}
