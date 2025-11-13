package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.util.Arrays;

public class NX {
   private static final ILogger eP = GlobalLog.getLogger(NX.class);
   public short pC;
   public short A;
   public char[] kS;
   public char[] wS;
   public byte UT;
   public byte E;
   public int sY;
   public byte ys;
   public byte ld;
   public byte gp;
   public short oT;
   public short fI;
   public short WR;
   public short NS;
   public byte vP;
   public byte xC;
   public short ED;
   public short Sc;
   public short ah;
   private char[] UO;
   private char[] Ab;
   private byte rl;
   private byte z;
   private int Ek;
   private int hK;
   private boolean Er;
   private String FE;

   public static NX pC(EX var0, int var1) throws IOException {
      int var2 = var0.pC();
      if (var1 < 4) {
         throw new IOException("Configuration block is too small: cannot read size");
      } else {
         int var3 = var0.readInt();
         int var4 = var3;
         boolean var5 = false;
         if (var3 < 16) {
            var5 = true;
         }

         if (var3 > var1) {
            var0.pC(S.L("Configuration entry is truncated: header requests %Xh bytes, but there are only %Xh available"), var3, var1);
            var3 = var1;
         }

         short var6 = 0;
         short var7 = 0;
         if (var3 >= 8) {
            var6 = var0.readShort();
            var7 = var0.readShort();
         }

         char[] var8 = new char[0];
         char[] var9 = new char[0];
         if (var3 >= 12) {
            var8 = pC(var0.readByte(), var0.readByte(), 'a');
            var9 = pC(var0.readByte(), var0.readByte(), '0');
         }

         byte var10 = 0;
         byte var11 = 0;
         int var12 = 0;
         if (var3 >= 16) {
            var10 = var0.readByte();
            var11 = var0.readByte();
            var12 = var0.readUnsignedShort();
         }

         byte var13 = 0;
         byte var14 = 0;
         byte var15 = 0;
         if (var3 >= 20) {
            var13 = var0.readByte();
            var14 = var0.readByte();
            var15 = var0.readByte();
            var0.skipBytes(1);
         }

         short var16 = 0;
         short var17 = 0;
         if (var3 >= 24) {
            var16 = var0.readShort();
            var17 = var0.readShort();
         }

         short var18 = 0;
         if (var3 >= 28) {
            var18 = var0.readShort();
            var0.readShort();
         }

         byte var19 = 0;
         byte var20 = 0;
         short var21 = 0;
         if (var3 >= 32) {
            var19 = var0.readByte();
            var20 = var0.readByte();
            var21 = var0.readShort();
         }

         short var22 = 0;
         short var23 = 0;
         if (var3 >= 36) {
            var22 = var0.readShort();
            var23 = var0.readShort();
         }

         char[] var24 = null;
         char[] var25 = null;
         if (var3 >= 48) {
            var24 = A(var0, 4).toCharArray();
            var25 = A(var0, 8).toCharArray();
         }

         byte var26 = 0;
         byte var27 = 0;
         if (var3 >= 52) {
            var26 = var0.readByte();
            var27 = var0.readByte();
            var0.readShort();
         }

         if (var3 >= 56) {
            var0.skipBytes(4);
         }

         int var28 = var0.pC() - var2;
         int var29 = var3 - var28;
         if (var29 > 0) {
            bM.pC(var0, var3 - var28, true, false);
         }

         return new NX(
            var6,
            var7,
            var8,
            var9,
            var10,
            var11,
            var12,
            var13,
            var14,
            var15,
            var16,
            var17,
            var18,
            var19,
            var20,
            var21,
            var22,
            var23,
            var24,
            var25,
            var26,
            var27,
            var5,
            var4,
            var3
         );
      }
   }

   private static char[] pC(byte var0, byte var1, char var2) {
      if ((var0 >> 7 & 1) == 1) {
         int var3 = var1 & 31;
         int var4 = ((var1 & 224) >> 5) + ((var0 & 3) << 3);
         int var5 = (var0 & 124) >> 2;
         return new char[]{(char)(var3 + var2), (char)(var4 + var2), (char)(var5 + var2)};
      } else {
         return new char[]{(char)var0, (char)var1};
      }
   }

   private static String A(EX var0, int var1) throws IOException {
      StringBuilder var2 = new StringBuilder(8);

      while (var1 != 0) {
         short var3 = var0.readByte();
         var1--;
         if (var3 == 0) {
            break;
         }

         var2.append((char)var3);
      }

      var0.skipBytes(var1);
      return var2.toString();
   }

   public NX() {
      this.pC = 0;
      this.A = 0;
      this.kS = new char[]{'\u0000', '\u0000'};
      this.wS = new char[]{'\u0000', '\u0000'};
      this.UT = 0;
      this.E = 0;
      this.sY = 0;
      this.ys = 0;
      this.ld = 0;
      this.gp = 0;
      this.oT = 0;
      this.fI = 0;
      this.WR = 0;
      this.vP = 0;
      this.xC = 0;
      this.ED = 0;
      this.Sc = 0;
      this.ah = 0;
      this.UO = null;
      this.Ab = null;
      this.rl = 0;
      this.z = 0;
      this.Er = false;
      this.FE = "";
      this.Ek = 0;
      this.hK = 0;
   }

   public NX(
      short var1,
      short var2,
      char[] var3,
      char[] var4,
      byte var5,
      byte var6,
      int var7,
      byte var8,
      byte var9,
      byte var10,
      short var11,
      short var12,
      short var13,
      byte var14,
      byte var15,
      short var16,
      short var17,
      short var18,
      char[] var19,
      char[] var20,
      byte var21,
      byte var22,
      boolean var23,
      int var24,
      int var25
   ) {
      if (var5 < 0 || var5 > 3) {
         "Invalid orientation value: " + var5;
         Object[] var10000 = new Object[0];
         var5 = 0;
         var23 = true;
      }

      if (var6 < 0 || var6 > 3) {
         "Invalid touchscreen value: " + var6;
         Object[] var26 = new Object[0];
         var6 = 0;
         var23 = true;
      }

      if (var7 < -1) {
         "Invalid density value: " + var7;
         Object[] var27 = new Object[0];
         var7 = 0;
         var23 = true;
      }

      if (var8 < 0 || var8 > 3) {
         "Invalid keyboard value: " + var8;
         Object[] var28 = new Object[0];
         var8 = 0;
         var23 = true;
      }

      if (var9 < 0 || var9 > 4) {
         "Invalid navigation value: " + var9;
         Object[] var29 = new Object[0];
         var9 = 0;
         var23 = true;
      }

      if (var19 == null || var19.length == 0) {
         var19 = null;
      } else if (var19[0] == 0) {
         var19 = null;
      }

      if (var20 == null || var20.length == 0) {
         var20 = null;
      } else if (var20[0] == 0) {
         var20 = null;
      }

      this.pC = var1;
      this.A = var2;
      this.kS = var3;
      this.wS = var4;
      this.UT = var5;
      this.E = var6;
      this.sY = var7;
      this.ys = var8;
      this.ld = var9;
      this.gp = var10;
      this.oT = var11;
      this.fI = var12;
      this.WR = var13;
      this.vP = var14;
      this.xC = var15;
      this.ED = var16;
      this.Sc = var17;
      this.ah = var18;
      this.UO = var19;
      this.Ab = var20;
      this.rl = var21;
      this.z = var22;
      this.Er = var23;
      this.Ek = var24;
      this.hK = var25;
   }

   public String pC() {
      if (this.FE == null) {
         this.FE = this.pC(false);
      }

      return this.FE;
   }

   public String pC(boolean var1) {
      StringBuilder var2 = new StringBuilder();
      if (this.pC != 0) {
         var2.append("-mcc");
         Strings.ff(var2, var1 ? "%d" : "%03d", this.pC);
         if (this.A != -1) {
            if (this.A != 0) {
               var2.append("-mnc");
               if (this.Ek <= 32) {
                  if (this.A > 0 && this.A < 10) {
                     Strings.ff(var2, var1 ? "%d" : "%02d", this.A);
                  } else {
                     Strings.ff(var2, var1 ? "%d" : "%03d", this.A);
                  }
               } else {
                  var2.append(this.A);
               }
            }
         } else {
            var2.append("-mnc00");
         }
      } else if (this.A != 0) {
         var2.append("-mnc").append(this.A);
      }

      var2.append(this.kS());
      switch (this.vP & 192) {
         case 64:
            var2.append("-ldltr");
            break;
         case 128:
            var2.append("-ldrtl");
      }

      if (this.ED != 0) {
         var2.append("-sw").append(this.ED).append("dp");
      }

      if (this.Sc != 0) {
         var2.append("-w").append(this.Sc).append("dp");
      }

      if (this.ah != 0) {
         var2.append("-h").append(this.ah).append("dp");
      }

      switch (this.vP & 15) {
         case 1:
            var2.append("-small");
            break;
         case 2:
            var2.append("-normal");
            break;
         case 3:
            var2.append("-large");
            break;
         case 4:
            var2.append("-xlarge");
      }

      switch (this.vP & 48) {
         case 16:
            var2.append("-notlong");
            break;
         case 32:
            var2.append("-long");
      }

      switch (this.rl & 3) {
         case 1:
            var2.append("-notround");
            break;
         case 2:
            var2.append("-round");
      }

      switch (this.z & 12) {
         case 4:
            var2.append("-lowdr");
            break;
         case 8:
            var2.append("-highdr");
      }

      switch (this.z & 3) {
         case 1:
            var2.append("-nowidecg");
            break;
         case 2:
            var2.append("-widecg");
      }

      switch (this.UT) {
         case 1:
            var2.append("-port");
            break;
         case 2:
            var2.append("-land");
            break;
         case 3:
            var2.append("-square");
      }

      switch (this.xC & 15) {
         case 2:
            var2.append("-desk");
            break;
         case 3:
            var2.append("-car");
            break;
         case 4:
            var2.append("-television");
            break;
         case 5:
            var2.append("-appliance");
            break;
         case 6:
            var2.append("-watch");
            break;
         case 7:
            var2.append("-vrheadset");
         case 8:
         case 9:
         case 10:
         default:
            break;
         case 11:
            var2.append("-godzillaui");
            break;
         case 12:
            var2.append("-smallui");
            break;
         case 13:
            var2.append("-mediumui");
            break;
         case 14:
            var2.append("-largeui");
            break;
         case 15:
            var2.append("-hugeui");
      }

      switch (this.xC & 48) {
         case 16:
            var2.append("-notnight");
            break;
         case 32:
            var2.append("-night");
      }

      switch (this.sY) {
         case 0:
            break;
         case 120:
            var2.append("-ldpi");
            break;
         case 160:
            var2.append("-mdpi");
            break;
         case 213:
            var2.append("-tvdpi");
            break;
         case 240:
            var2.append("-hdpi");
            break;
         case 320:
            var2.append("-xhdpi");
            break;
         case 480:
            var2.append("-xxhdpi");
            break;
         case 640:
            var2.append("-xxxhdpi");
            break;
         case 65534:
            var2.append("-anydpi");
            break;
         case 65535:
            var2.append("-nodpi");
            break;
         default:
            var2.append('-').append(this.sY).append("dpi");
      }

      switch (this.E) {
         case 1:
            var2.append("-notouch");
            break;
         case 2:
            var2.append("-stylus");
            break;
         case 3:
            var2.append("-finger");
      }

      switch (this.gp & 3) {
         case 1:
            var2.append("-keysexposed");
            break;
         case 2:
            var2.append("-keyshidden");
            break;
         case 3:
            var2.append("-keyssoft");
      }

      switch (this.ys) {
         case 1:
            var2.append("-nokeys");
            break;
         case 2:
            var2.append("-qwerty");
            break;
         case 3:
            var2.append("-12key");
      }

      switch (this.gp & 12) {
         case 4:
            var2.append("-navexposed");
            break;
         case 8:
            var2.append("-navhidden");
      }

      switch (this.ld) {
         case 1:
            var2.append("-nonav");
            break;
         case 2:
            var2.append("-dpad");
            break;
         case 3:
            var2.append("-trackball");
            break;
         case 4:
            var2.append("-wheel");
      }

      if (this.oT != 0 && this.fI != 0) {
         if (this.oT > this.fI) {
            Strings.ff(var2, "-%dx%d", this.oT, this.fI);
         } else {
            Strings.ff(var2, "-%dx%d", this.fI, this.oT);
         }
      }

      if (this.WR > 0 && this.WR >= this.A()) {
         var2.append("-v").append(this.WR);
      }

      if (this.Er) {
         var2.append("-ERR");
      }

      return var2.length() >= 1 && var2.charAt(0) == '-' ? var2.substring(1) : var2.toString();
   }

   private short A() {
      if ((this.xC & 15) == 7 || (this.z & 3) != 0 || (this.z & 12) != 0) {
         return 26;
      } else if ((this.rl & 3) != 0) {
         return 23;
      } else if (this.sY == 65534) {
         return 21;
      } else if (this.ED != 0 || this.Sc != 0 || this.ah != 0) {
         return 13;
      } else if ((this.xC & 63) != 0) {
         return 8;
      } else {
         return (short)((this.vP & 63) == 0 && this.sY == 0 ? 0 : 4);
      }
   }

   private String kS() {
      StringBuilder var1 = new StringBuilder();
      if (this.Ab != null
         || this.UO != null
         || (this.wS == null || this.wS.length != 2 || this.wS[0] == 0) && (this.kS == null || this.kS.length <= 0 || this.kS[0] == 0)) {
         if (this.kS != null && this.kS.length != 0 && this.kS[0] != 0 || this.wS != null && this.wS.length != 0 && this.wS[0] != 0) {
            var1.append("-b+");
            if (this.kS != null && this.kS.length > 0 && this.kS[0] != 0) {
               var1.append(this.kS);
            }

            if (this.UO != null && this.UO.length == 4) {
               var1.append("+").append(this.UO);
            }

            if (this.wS != null && (this.wS.length == 2 || this.wS.length == 3) && this.wS[0] != 0) {
               var1.append("+").append(this.wS);
            }

            if (this.Ab != null && this.Ab.length >= 5) {
               var1.append("+").append(pC(this.Ab));
            }
         }
      } else {
         if (this.kS != null && this.kS.length > 0 && this.kS[0] != 0) {
            var1.append("-").append(this.kS);
         }

         if (this.wS != null && this.wS.length > 0 && this.wS[0] != 0) {
            var1.append("-r").append(this.wS);
         }
      }

      return var1.toString();
   }

   private static String pC(char[] var0) {
      StringBuilder var1 = new StringBuilder();

      for (char var5 : var0) {
         var1.append(Character.toUpperCase(var5));
      }

      return var1.toString();
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.z;
      var1 = 31 * var1 + Arrays.hashCode(this.wS);
      var1 = 31 * var1 + this.sY;
      var1 = 31 * var1 + this.gp;
      var1 = 31 * var1 + this.ys;
      var1 = 31 * var1 + Arrays.hashCode(this.kS);
      var1 = 31 * var1 + Arrays.hashCode(this.UO);
      var1 = 31 * var1 + Arrays.hashCode(this.Ab);
      var1 = 31 * var1 + this.pC;
      var1 = 31 * var1 + this.NS;
      var1 = 31 * var1 + this.A;
      var1 = 31 * var1 + this.ld;
      var1 = 31 * var1 + this.UT;
      var1 = 31 * var1 + this.fI;
      var1 = 31 * var1 + this.ah;
      var1 = 31 * var1 + this.vP;
      var1 = 31 * var1 + this.rl;
      var1 = 31 * var1 + this.oT;
      var1 = 31 * var1 + this.Sc;
      var1 = 31 * var1 + this.WR;
      var1 = 31 * var1 + this.ED;
      var1 = 31 * var1 + this.E;
      return 31 * var1 + this.xC;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         NX var2 = (NX)var1;
         if (this.z != var2.z) {
            return false;
         } else if (!Arrays.equals(this.wS, var2.wS)) {
            return false;
         } else if (this.sY != var2.sY) {
            return false;
         } else if (this.gp != var2.gp) {
            return false;
         } else if (this.ys != var2.ys) {
            return false;
         } else if (!Arrays.equals(this.kS, var2.kS)) {
            return false;
         } else if (!Arrays.equals(this.UO, var2.UO)) {
            return false;
         } else if (!Arrays.equals(this.Ab, var2.Ab)) {
            return false;
         } else if (this.pC != var2.pC) {
            return false;
         } else if (this.NS != var2.NS) {
            return false;
         } else if (this.A != var2.A) {
            return false;
         } else if (this.ld != var2.ld) {
            return false;
         } else if (this.UT != var2.UT) {
            return false;
         } else if (this.fI != var2.fI) {
            return false;
         } else if (this.ah != var2.ah) {
            return false;
         } else if (this.vP != var2.vP) {
            return false;
         } else if (this.rl != var2.rl) {
            return false;
         } else if (this.oT != var2.oT) {
            return false;
         } else if (this.Sc != var2.Sc) {
            return false;
         } else if (this.WR != var2.WR) {
            return false;
         } else if (this.ED != var2.ED) {
            return false;
         } else {
            return this.E != var2.E ? false : this.xC == var2.xC;
         }
      }
   }

   @Override
   public String toString() {
      return !this.pC().equals("") ? this.pC() : "[DEFAULT]";
   }
}
