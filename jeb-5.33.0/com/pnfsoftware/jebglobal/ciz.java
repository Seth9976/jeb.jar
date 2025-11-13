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

public class ciz {
   private static final ILogger wS = GlobalLog.getLogger(ciz.class);
   ByteBuffer pC;
   StringBuilder A;
   int kS;

   public static String pC(int var0) {
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

   public static String A(int var0) {
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

   public static String pC(int var0, int var1) {
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

   public ciz(byte[] var1) {
      this.pC = ByteBuffer.wrap(var1);
      this.pC.order(ByteOrder.LITTLE_ENDIAN);
   }

   public String pC() throws Exception {
      this.pC.position(0);
      this.A = new StringBuilder();
      this.kS = 0;
      this.A();
      return this.A.toString();
   }

   ciz.Av pC(String var1) {
      int var2 = this.pC.position();
      int var3 = this.pC.getShort() & '\uffff';
      int var4 = this.pC.getShort() & '\uffff';
      int var5 = this.pC.getShort() & '\uffff';
      String var6 = this.ys();
      if (var1 != null && !var1.equals(var6)) {
         throw new RuntimeException(Strings.ff("Unexpected key: \"%s\"", var6));
      } else {
         this.ld();
         return new ciz.Av(var2, var3, var4, var5, var6);
      }
   }

   void A() {
      ciz.Av var1 = this.pC("VS_VERSION_INFO");
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
      if (var1.kS >= 4) {
         this.pC.getInt();
      }

      if (var1.kS >= 8) {
         this.pC.getInt();
      }

      if (var1.kS >= 12) {
         var2 = this.pC.getInt();
      }

      if (var1.kS >= 16) {
         var3 = this.pC.getInt();
      }

      Strings.ff(this.A, "FileVersion: %d.%d.%d.%d\n", var2 >>> 16, var2 & 65535, var3 >>> 16, var3 & 65535);
      if (var1.kS >= 20) {
         var4 = this.pC.getInt();
      }

      if (var1.kS >= 24) {
         var5 = this.pC.getInt();
      }

      Strings.ff(this.A, "ProductVersion: %d.%d.%d.%d\n", var4 >>> 16, var4 & 65535, var5 >>> 16, var5 & 65535);
      if (var1.kS >= 28) {
         var6 = this.pC.getInt();
      }

      if (var1.kS >= 32) {
         var7 = this.pC.getInt();
      }

      Strings.ff(this.A, "FileFlags: %s\n", pC(var7 & var6));
      if (var1.kS >= 36) {
         var8 = this.pC.getInt();
      }

      Strings.ff(this.A, "FileOs: %s\n", A(var8));
      if (var1.kS >= 40) {
         var9 = this.pC.getInt();
      }

      if (var1.kS >= 44) {
         var10 = this.pC.getInt();
      }

      Strings.ff(this.A, "FileType: %s\n", pC(var9, var10));
      if (var1.kS >= 48) {
         var11 = this.pC.getInt();
      }

      if (var1.kS >= 52) {
         var12 = this.pC.getInt();
      }

      long var13 = (long)var11 << 32 | var12 & 4294967295L;
      if (var13 != 0L) {
         Date var15 = new Date((var13 - 116444736000000000L) / 10000L);
         Strings.ff(this.A, "FileDate: %s\n", var15);
      }

      if (var1.kS > 52) {
         ByteBufferUtil.skip(this.pC, var1.kS - 52);
      }

      this.ld();
      this.A.append("\n");

      while (this.pC(var1)) {
         ciz.Av var16 = this.pC(null);
         if (var16.UT.equals("StringFileInfo")) {
            this.pC.position(var16.pC);
            this.kS();
         } else {
            if (!var16.UT.equals("VarFileInfo")) {
               throw new RuntimeException();
            }

            this.pC.position(var16.pC);
            this.E();
         }
      }
   }

   void kS() {
      this.pC("Block {\n");
      this.kS++;
      ciz.Av var1 = this.pC("StringFileInfo");

      while (this.pC(var1)) {
         this.wS();
         this.ld();
      }

      this.kS--;
      this.pC("}\n");
   }

   void wS() {
      ciz.Av var1 = this.pC(null);
      int var2 = Integer.parseInt(var1.UT, 16);
      int var3 = var2 >>> 16;
      int var4 = var2 & 65535;
      Object[] var10000 = new Object[]{var3, var4};
      this.pC("Block 0x%X,0x%X {\n", var3, var4);
      this.kS++;

      while (this.pC(var1)) {
         this.UT();
         this.ld();
      }

      this.kS--;
      this.pC("}\n");
   }

   ciz.Sv UT() {
      ciz.Av var1 = this.pC(null);
      String var2 = this.ys();
      if (var2.length() != var1.kS - 1) {
         throw new RuntimeException();
      } else {
         this.pC("\"%s\": \"%s\"\n", var1.UT, var2);
         return new ciz.Sv(var1.wS, var1.UT, var2);
      }
   }

   void E() {
      this.pC("Block {\n");
      this.kS++;
      ciz.Av var1 = this.pC("VarFileInfo");

      while (this.pC(var1)) {
         this.sY();
         this.ld();
      }

      this.kS--;
      this.pC("}\n");
   }

   void sY() {
      this.pC("\"Translation\": ");
      ciz.Av var1 = this.pC("Translation");

      while (this.pC(var1)) {
         int var2 = this.pC.getInt();
         Strings.ff(this.A, "0x%X ", var2);
      }

      this.A.append("\n");
   }

   String ys() {
      BytePipe var1 = new BytePipe(32);

      while (true) {
         char var2 = this.pC.getChar();
         if (var2 == 0) {
            return new String(var1.getAll(), Charset.forName("UTF-16LE"));
         }

         var1.append(var2 & 255);
         var1.append(var2 >> '\b' & 0xFF);
      }
   }

   void ld() {
      int var1 = this.pC.position();
      if (var1 % 4 != 0) {
         int var2 = 4 - var1 % 4;
         this.pC.position(var1 + var2);
      }
   }

   boolean pC(ciz.Av var1) {
      return this.pC.position() < var1.pC + var1.A;
   }

   private void pC(String var1, Object... var2) {
      String var3 = Strings.generate(' ', this.kS * 2) + Strings.ff(var1, var2);
      this.A.append(var3);
   }

   static class Av {
      int pC;
      int A;
      int kS;
      int wS;
      String UT;

      Av(int var1, int var2, int var3, int var4, String var5) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
         this.wS = var4;
         this.UT = var5;
      }

      @Override
      public String toString() {
         return Strings.ff("%s/%d/%d", this.UT, this.A, this.kS);
      }
   }

   static class Sv {
      int pC;
      String A;
      String kS;

      public Sv(int var1, String var2, String var3) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
      }

      @Override
      public String toString() {
         return Strings.ff("%s:%s", this.A, this.kS);
      }
   }
}
