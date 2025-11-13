package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.util.Arrays;

public class Sp {
   private static final ILogger RL = GlobalLog.getLogger(Sp.class);
   public static final byte q = 1;
   public static final byte RF = 2;
   public static final byte xK = 3;
   public static final byte Dw = 4;
   public static final byte Uv = 5;
   public static final byte oW = 6;
   public static final byte gO = 7;
   public static final byte nf = 8;
   public static final byte gP = 9;
   public static final byte za = 10;
   public static final byte lm = 11;
   public static final byte zz = 12;
   public static final byte JY = 13;
   public static final byte HF = 14;
   public static final byte LK = 15;
   public static final byte io = 16;
   public static final byte qa = 17;
   public static final byte Hk = 18;
   public static final byte Me = 19;
   public static final byte PV = 21;
   public static final byte oQ = 22;
   public static final byte xW = 23;
   public static final byte KT = 24;
   public static final byte Gf = 25;
   public static final byte Ef = 26;
   public static final byte cC = 0;
   public static final byte sH = 1;
   public static final byte CE = 2;
   public static final byte wF = 3;
   public static final byte If = 0;
   public static final byte Dz = 1;
   public static final byte mI = 2;
   public static final byte jq = 3;
   public static final int ui = 0;
   public static final int TX = 120;
   public static final int Rr = 160;
   public static final int EB = 190;
   public static final int Xo = 213;
   public static final int Bu = 240;
   public static final int IN = 320;
   public static final int rL = 480;
   public static final int eJ = 640;
   public static final int YN = 65534;
   public static final int Rv = 65535;
   public static final int zx = -1;
   public static final short ZT = 192;
   public static final short Ri = 0;
   public static final short GY = 64;
   public static final short Wx = 128;
   public static final short AB = 6;
   public static final short CY = 3;
   public static final short WI = 0;
   public static final short Tq = 1;
   public static final short Yp = 2;
   public static final byte Gu = 0;
   public static final byte nY = 1;
   public static final byte lF = 2;
   public static final byte nq = 3;
   public static final byte NX = 0;
   public static final byte br = 1;
   public static final byte tW = 2;
   public static final byte ZA = 3;
   public static final byte Ov = 4;
   public static final byte Lj = 3;
   public static final byte nv = 0;
   public static final byte LL = 1;
   public static final byte PQ = 2;
   public static final byte fQ = 3;
   public static final byte fi = 12;
   public static final byte bl = 0;
   public static final byte jb = 4;
   public static final byte pQ = 8;
   public static final byte kf = 15;
   public static final byte GM = 0;
   public static final byte TQ = 1;
   public static final byte Yw = 2;
   public static final byte IY = 3;
   public static final byte qR = 4;
   public static final byte YA = 48;
   public static final byte fw = 0;
   public static final byte Wp = 16;
   public static final byte cY = 32;
   public static final byte PY = 15;
   public static final byte cR = 0;
   public static final byte eC = 1;
   public static final byte ND = 2;
   public static final byte Qu = 3;
   public static final byte jh = 4;
   public static final byte Jf = 5;
   public static final byte vC = 6;
   public static final byte of = 7;
   public static final byte os = 11;
   public static final byte iu = 12;
   public static final byte fn = 13;
   public static final byte ZU = 14;
   public static final byte Sz = 15;
   public static final byte fq = 48;
   public static final byte mJ = 0;
   public static final byte Bs = 16;
   public static final byte rV = 32;
   public static final byte WX = 12;
   public static final byte CB = 4;
   public static final byte C = 2;
   public static final byte GC = 0;
   public static final byte KF = 8;
   public static final byte rk = 0;
   public static final byte cy = 0;
   public static final byte jk = 1;
   public static final byte Cl = 2;
   public static final byte hM = 3;
   static final int kv = 16;
   static final int oS = 56;
   public short FG;
   public short Al;
   public char[] Kn;
   public char[] vh;
   public byte Rd;
   public byte Eq;
   public int hP;
   public byte wN;
   public byte Uc;
   public byte TB;
   public short dg;
   public short hw;
   public short gm;
   public short uY;
   public byte sc;
   public byte wQ;
   public short Oj;
   public short VW;
   public short ap;
   private char[] hy;
   private char[] Xi;
   private byte Ag;
   private byte rp;
   private int CW;
   private int qm;
   private boolean LR;
   private String Uz;

   public static Sp q(uL var0, int var1) throws IOException {
      int var2 = var0.q();
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
            var0.q(S.L("Configuration entry is truncated: header requests %Xh bytes, but there are only %Xh available"), var3, var1);
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
            var8 = q(var0.readByte(), var0.readByte(), 'a');
            var9 = q(var0.readByte(), var0.readByte(), '0');
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
            var24 = RF(var0, 4).toCharArray();
            var25 = RF(var0, 8).toCharArray();
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

         int var28 = var0.q() - var2;
         int var29 = var3 - var28;
         if (var29 > 0) {
            zR.q(var0, var3 - var28, true, false);
         }

         return new Sp(
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

   private static char[] q(byte var0, byte var1, char var2) {
      if ((var0 >> 7 & 1) == 1) {
         int var3 = var1 & 31;
         int var4 = ((var1 & 224) >> 5) + ((var0 & 3) << 3);
         int var5 = (var0 & 124) >> 2;
         return new char[]{(char)(var3 + var2), (char)(var4 + var2), (char)(var5 + var2)};
      } else {
         return new char[]{(char)var0, (char)var1};
      }
   }

   private static String RF(uL var0, int var1) throws IOException {
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

   public Sp() {
      this.FG = 0;
      this.Al = 0;
      this.Kn = new char[]{'\u0000', '\u0000'};
      this.vh = new char[]{'\u0000', '\u0000'};
      this.Rd = 0;
      this.Eq = 0;
      this.hP = 0;
      this.wN = 0;
      this.Uc = 0;
      this.TB = 0;
      this.dg = 0;
      this.hw = 0;
      this.gm = 0;
      this.sc = 0;
      this.wQ = 0;
      this.Oj = 0;
      this.VW = 0;
      this.ap = 0;
      this.hy = null;
      this.Xi = null;
      this.Ag = 0;
      this.rp = 0;
      this.LR = false;
      this.Uz = "";
      this.CW = 0;
      this.qm = 0;
   }

   public Sp(
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

      this.FG = var1;
      this.Al = var2;
      this.Kn = var3;
      this.vh = var4;
      this.Rd = var5;
      this.Eq = var6;
      this.hP = var7;
      this.wN = var8;
      this.Uc = var9;
      this.TB = var10;
      this.dg = var11;
      this.hw = var12;
      this.gm = var13;
      this.sc = var14;
      this.wQ = var15;
      this.Oj = var16;
      this.VW = var17;
      this.ap = var18;
      this.hy = var19;
      this.Xi = var20;
      this.Ag = var21;
      this.rp = var22;
      this.LR = var23;
      this.CW = var24;
      this.qm = var25;
   }

   public String q() {
      if (this.Uz == null) {
         this.Uz = this.q(false);
      }

      return this.Uz;
   }

   public String q(boolean var1) {
      StringBuilder var2 = new StringBuilder();
      if (this.FG != 0) {
         var2.append("-mcc");
         Strings.ff(var2, var1 ? "%d" : "%03d", this.FG);
         if (this.Al != -1) {
            if (this.Al != 0) {
               var2.append("-mnc");
               if (this.CW <= 32) {
                  if (this.Al > 0 && this.Al < 10) {
                     Strings.ff(var2, var1 ? "%d" : "%02d", this.Al);
                  } else {
                     Strings.ff(var2, var1 ? "%d" : "%03d", this.Al);
                  }
               } else {
                  var2.append(this.Al);
               }
            }
         } else {
            var2.append("-mnc00");
         }
      } else if (this.Al != 0) {
         var2.append("-mnc").append(this.Al);
      }

      var2.append(this.xK());
      switch (this.sc & 192) {
         case 64:
            var2.append("-ldltr");
            break;
         case 128:
            var2.append("-ldrtl");
      }

      if (this.Oj != 0) {
         var2.append("-sw").append(this.Oj).append("dp");
      }

      if (this.VW != 0) {
         var2.append("-w").append(this.VW).append("dp");
      }

      if (this.ap != 0) {
         var2.append("-h").append(this.ap).append("dp");
      }

      switch (this.sc & 15) {
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

      switch (this.sc & 48) {
         case 16:
            var2.append("-notlong");
            break;
         case 32:
            var2.append("-long");
      }

      switch (this.Ag & 3) {
         case 1:
            var2.append("-notround");
            break;
         case 2:
            var2.append("-round");
      }

      switch (this.rp & 12) {
         case 4:
            var2.append("-lowdr");
            break;
         case 8:
            var2.append("-highdr");
      }

      switch (this.rp & 3) {
         case 1:
            var2.append("-nowidecg");
            break;
         case 2:
            var2.append("-widecg");
      }

      switch (this.Rd) {
         case 1:
            var2.append("-port");
            break;
         case 2:
            var2.append("-land");
            break;
         case 3:
            var2.append("-square");
      }

      switch (this.wQ & 15) {
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

      switch (this.wQ & 48) {
         case 16:
            var2.append("-notnight");
            break;
         case 32:
            var2.append("-night");
      }

      switch (this.hP) {
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
            var2.append('-').append(this.hP).append("dpi");
      }

      switch (this.Eq) {
         case 1:
            var2.append("-notouch");
            break;
         case 2:
            var2.append("-stylus");
            break;
         case 3:
            var2.append("-finger");
      }

      switch (this.TB & 3) {
         case 1:
            var2.append("-keysexposed");
            break;
         case 2:
            var2.append("-keyshidden");
            break;
         case 3:
            var2.append("-keyssoft");
      }

      switch (this.wN) {
         case 1:
            var2.append("-nokeys");
            break;
         case 2:
            var2.append("-qwerty");
            break;
         case 3:
            var2.append("-12key");
      }

      switch (this.TB & 12) {
         case 4:
            var2.append("-navexposed");
            break;
         case 8:
            var2.append("-navhidden");
      }

      switch (this.Uc) {
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

      if (this.dg != 0 && this.hw != 0) {
         if (this.dg > this.hw) {
            Strings.ff(var2, "-%dx%d", this.dg, this.hw);
         } else {
            Strings.ff(var2, "-%dx%d", this.hw, this.dg);
         }
      }

      if (this.gm > 0 && this.gm >= this.RF()) {
         var2.append("-v").append(this.gm);
      }

      if (this.LR) {
         var2.append("-ERR");
      }

      return var2.length() >= 1 && var2.charAt(0) == '-' ? var2.substring(1) : var2.toString();
   }

   private short RF() {
      if ((this.wQ & 15) == 7 || (this.rp & 3) != 0 || (this.rp & 12) != 0) {
         return 26;
      } else if ((this.Ag & 3) != 0) {
         return 23;
      } else if (this.hP == 65534) {
         return 21;
      } else if (this.Oj != 0 || this.VW != 0 || this.ap != 0) {
         return 13;
      } else if ((this.wQ & 63) != 0) {
         return 8;
      } else {
         return (short)((this.sc & 63) == 0 && this.hP == 0 ? 0 : 4);
      }
   }

   private String xK() {
      StringBuilder var1 = new StringBuilder();
      if (this.Xi != null
         || this.hy != null
         || (this.vh == null || this.vh.length != 2 || this.vh[0] == 0) && (this.Kn == null || this.Kn.length <= 0 || this.Kn[0] == 0)) {
         if (this.Kn != null && this.Kn.length != 0 && this.Kn[0] != 0 || this.vh != null && this.vh.length != 0 && this.vh[0] != 0) {
            var1.append("-b+");
            if (this.Kn != null && this.Kn.length > 0 && this.Kn[0] != 0) {
               var1.append(this.Kn);
            }

            if (this.hy != null && this.hy.length == 4) {
               var1.append("+").append(this.hy);
            }

            if (this.vh != null && (this.vh.length == 2 || this.vh.length == 3) && this.vh[0] != 0) {
               var1.append("+").append(this.vh);
            }

            if (this.Xi != null && this.Xi.length >= 5) {
               var1.append("+").append(q(this.Xi));
            }
         }
      } else {
         if (this.Kn != null && this.Kn.length > 0 && this.Kn[0] != 0) {
            var1.append("-").append(this.Kn);
         }

         if (this.vh != null && this.vh.length > 0 && this.vh[0] != 0) {
            var1.append("-r").append(this.vh);
         }
      }

      return var1.toString();
   }

   private static String q(char[] var0) {
      StringBuilder var1 = new StringBuilder();

      for (char var5 : var0) {
         var1.append(Character.toUpperCase(var5));
      }

      return var1.toString();
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.rp;
      var1 = 31 * var1 + Arrays.hashCode(this.vh);
      var1 = 31 * var1 + this.hP;
      var1 = 31 * var1 + this.TB;
      var1 = 31 * var1 + this.wN;
      var1 = 31 * var1 + Arrays.hashCode(this.Kn);
      var1 = 31 * var1 + Arrays.hashCode(this.hy);
      var1 = 31 * var1 + Arrays.hashCode(this.Xi);
      var1 = 31 * var1 + this.FG;
      var1 = 31 * var1 + this.uY;
      var1 = 31 * var1 + this.Al;
      var1 = 31 * var1 + this.Uc;
      var1 = 31 * var1 + this.Rd;
      var1 = 31 * var1 + this.hw;
      var1 = 31 * var1 + this.ap;
      var1 = 31 * var1 + this.sc;
      var1 = 31 * var1 + this.Ag;
      var1 = 31 * var1 + this.dg;
      var1 = 31 * var1 + this.VW;
      var1 = 31 * var1 + this.gm;
      var1 = 31 * var1 + this.Oj;
      var1 = 31 * var1 + this.Eq;
      return 31 * var1 + this.wQ;
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
         Sp var2 = (Sp)var1;
         if (this.rp != var2.rp) {
            return false;
         } else if (!Arrays.equals(this.vh, var2.vh)) {
            return false;
         } else if (this.hP != var2.hP) {
            return false;
         } else if (this.TB != var2.TB) {
            return false;
         } else if (this.wN != var2.wN) {
            return false;
         } else if (!Arrays.equals(this.Kn, var2.Kn)) {
            return false;
         } else if (!Arrays.equals(this.hy, var2.hy)) {
            return false;
         } else if (!Arrays.equals(this.Xi, var2.Xi)) {
            return false;
         } else if (this.FG != var2.FG) {
            return false;
         } else if (this.uY != var2.uY) {
            return false;
         } else if (this.Al != var2.Al) {
            return false;
         } else if (this.Uc != var2.Uc) {
            return false;
         } else if (this.Rd != var2.Rd) {
            return false;
         } else if (this.hw != var2.hw) {
            return false;
         } else if (this.ap != var2.ap) {
            return false;
         } else if (this.sc != var2.sc) {
            return false;
         } else if (this.Ag != var2.Ag) {
            return false;
         } else if (this.dg != var2.dg) {
            return false;
         } else if (this.VW != var2.VW) {
            return false;
         } else if (this.gm != var2.gm) {
            return false;
         } else if (this.Oj != var2.Oj) {
            return false;
         } else {
            return this.Eq != var2.Eq ? false : this.wQ == var2.wQ;
         }
      }
   }

   @Override
   public String toString() {
      return !this.q().equals("") ? this.q() : "[DEFAULT]";
   }
}
