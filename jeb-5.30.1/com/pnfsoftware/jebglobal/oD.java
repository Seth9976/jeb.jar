package com.pnfsoftware.jebglobal;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.primitives.UnsignedBytes;
import java.lang.invoke.StringConcatFactory;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Xv
public abstract class oD implements vv {
   private static final int q = 0;
   private static final int RF = 120;
   private static final int xK = 160;
   private static final int Dw = 213;
   private static final int Uv = 240;
   private static final int oW = 320;
   private static final int gO = 480;
   private static final int nf = 640;
   private static final int gP = 65534;
   private static final int za = 65535;
   private static final ImmutableMap lm = ImmutableMap.builder()
      .put(0, "")
      .put(120, "ldpi")
      .put(160, "mdpi")
      .put(213, "tvdpi")
      .put(240, "hdpi")
      .put(320, "xhdpi")
      .put(480, "xxhdpi")
      .put(640, "xxxhdpi")
      .put(65534, "anydpi")
      .put(65535, "nodpi")
      .build();
   private static final int zz = 1;
   private static final int JY = 2;
   private static final int HF = 3;
   private static final ImmutableMap LK = ImmutableMap.of(1, "nokeys", 2, "qwerty", 3, "12key");
   private static final int io = 3;
   private static final int qa = 1;
   private static final int Hk = 2;
   private static final int Me = 3;
   private static final ImmutableMap PV = ImmutableMap.of(1, "keysexposed", 2, "keyshidden", 3, "keyssoft");
   private static final int oQ = 1;
   private static final int xW = 2;
   private static final int KT = 3;
   private static final int Gf = 4;
   private static final ImmutableMap Ef = ImmutableMap.of(1, "nonav", 2, "dpad", 3, "trackball", 4, "wheel");
   private static final int cC = 12;
   private static final int sH = 4;
   private static final int CE = 8;
   private static final ImmutableMap wF = ImmutableMap.of(4, "navexposed", 8, "navhidden");
   private static final int If = 1;
   private static final int Dz = 2;
   private static final ImmutableMap mI = ImmutableMap.of(1, "port", 2, "land");
   private static final int jq = 192;
   private static final int ui = 64;
   private static final int TX = 128;
   private static final ImmutableMap Rr = ImmutableMap.of(64, "ldltr", 128, "ldrtl");
   private static final int EB = 48;
   private static final int Xo = 16;
   private static final int Bu = 32;
   private static final ImmutableMap IN = ImmutableMap.of(16, "notlong", 32, "long");
   private static final int rL = 768;
   private static final int eJ = 256;
   private static final int YN = 512;
   private static final ImmutableMap Rv = ImmutableMap.of(256, "notround", 512, "round");
   private static final int zx = 15;
   private static final int ZT = 1;
   private static final int Ri = 2;
   private static final int GY = 3;
   private static final int Wx = 4;
   private static final ImmutableMap AB = ImmutableMap.of(1, "small", 2, "normal", 3, "large", 4, "xlarge");
   private static final int CY = 1;
   private static final int WI = 3;
   private static final ImmutableMap Tq = ImmutableMap.of(1, "notouch", 3, "finger");
   private static final int Yp = 48;
   private static final int Gu = 16;
   private static final int nY = 32;
   private static final ImmutableMap lF = ImmutableMap.of(16, "notnight", 32, "night");
   private static final int nq = 15;
   private static final int NX = 2;
   private static final int br = 3;
   private static final int tW = 4;
   private static final int ZA = 5;
   private static final int Ov = 6;
   private static final ImmutableMap Lj = ImmutableMap.of(2, "desk", 3, "car", 4, "television", 5, "appliance", 6, "watch");
   private static final int nv = 32;
   private static final int LL = 36;
   private static final int PQ = 48;
   private static final int fQ = 52;

   public abstract int q();

   public abstract int RF();

   public abstract int xK();

   public abstract byte[] Dw();

   public final String Uv() {
      return this.Xo();
   }

   public abstract byte[] gO();

   public final String nf() {
      return this.Bu();
   }

   public abstract int gP();

   public abstract int za();

   public abstract int lm();

   public abstract int zz();

   public abstract int JY();

   public abstract int HF();

   public final int LK() {
      return this.HF() & 3;
   }

   public final int io() {
      return this.HF() & 12;
   }

   public abstract int qa();

   public abstract int Hk();

   public abstract int Me();

   public final oD q(int var1) {
      return (oD)(var1 == this.Me()
         ? this
         : new oD.eo(
            this.q(),
            this.RF(),
            this.xK(),
            this.Dw(),
            this.gO(),
            this.gP(),
            this.za(),
            this.lm(),
            this.zz(),
            this.JY(),
            this.HF(),
            this.qa(),
            this.Hk(),
            var1,
            this.PV(),
            this.oQ(),
            this.cC(),
            this.wF(),
            this.If(),
            this.Dz(),
            this.mI(),
            this.jq(),
            this.ui(),
            this.TX()
         ));
   }

   public abstract int PV();

   public abstract int oQ();

   public final int xW() {
      return this.oQ() & 192;
   }

   public final int KT() {
      return this.oQ() & 15;
   }

   public final int Gf() {
      return this.oQ() & 48;
   }

   public final int Ef() {
      return this.oQ() & 768;
   }

   public abstract int cC();

   public final int sH() {
      return this.cC() & 15;
   }

   public final int CE() {
      return this.cC() & 48;
   }

   public abstract int wF();

   public abstract int If();

   public abstract int Dz();

   public abstract byte[] mI();

   public abstract byte[] jq();

   public abstract int ui();

   public abstract byte[] TX();

   static oD q(ByteBuffer var0) {
      int var1 = var0.position();
      int var2 = var0.getInt();
      int var3 = var0.getShort() & '\uffff';
      int var4 = var0.getShort() & '\uffff';
      byte[] var5 = new byte[2];
      var0.get(var5);
      byte[] var6 = new byte[2];
      var0.get(var6);
      int var7 = UnsignedBytes.toInt(var0.get());
      int var8 = UnsignedBytes.toInt(var0.get());
      int var9 = var0.getShort() & '\uffff';
      int var10 = UnsignedBytes.toInt(var0.get());
      int var11 = UnsignedBytes.toInt(var0.get());
      int var12 = UnsignedBytes.toInt(var0.get());
      var0.get();
      int var13 = var0.getShort() & '\uffff';
      int var14 = var0.getShort() & '\uffff';
      int var15 = var0.getShort() & '\uffff';
      int var16 = var0.getShort() & '\uffff';
      int var17 = 0;
      int var18 = 0;
      int var19 = 0;
      int var20 = 0;
      int var21 = 0;
      byte[] var22 = new byte[4];
      byte[] var23 = new byte[8];
      int var24 = 0;
      if (var2 >= 32) {
         var17 = UnsignedBytes.toInt(var0.get());
         var18 = UnsignedBytes.toInt(var0.get());
         var19 = var0.getShort() & '\uffff';
      }

      if (var2 >= 36) {
         var20 = var0.getShort() & '\uffff';
         var21 = var0.getShort() & '\uffff';
      }

      if (var2 >= 48) {
         var0.get(var22);
         var0.get(var23);
      }

      if (var2 >= 52) {
         var24 = UnsignedBytes.toInt(var0.get());
         var0.get();
         var0.getShort();
      }

      int var25 = var0.position() - var1;
      byte[] var26 = new byte[var2 - var25];
      var0.get(var26);
      return new oD.eo(
         var2,
         var3,
         var4,
         var5,
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
         var26
      );
   }

   private String Xo() {
      return this.q(this.Dw(), 97);
   }

   private String Bu() {
      return this.q(this.gO(), 48);
   }

   private String q(byte[] var1, int var2) {
      Preconditions.checkState(var1.length == 2);
      if (var1[0] == 0 && var1[1] == 0) {
         return "";
      } else if ((UnsignedBytes.toInt(var1[0]) & 128) != 0) {
         byte[] var3 = new byte[]{
            (byte)(var2 + (var1[1] & 31)), (byte)(var2 + ((var1[1] & 224) >>> 5) + ((var1[0] & 3) << 3)), (byte)(var2 + ((var1[0] & 124) >>> 2))
         };
         return new String(var3, StandardCharsets.US_ASCII);
      } else {
         return new String(var1, StandardCharsets.US_ASCII);
      }
   }

   public final boolean Rr() {
      return this.RF() == 0
         && this.xK() == 0
         && Arrays.equals(this.Dw(), new byte[2])
         && Arrays.equals(this.gO(), new byte[2])
         && this.gP() == 0
         && this.za() == 0
         && this.lm() == 0
         && this.zz() == 0
         && this.JY() == 0
         && this.HF() == 0
         && this.qa() == 0
         && this.Hk() == 0
         && this.Me() == 0
         && this.PV() == 0
         && this.oQ() == 0
         && this.cC() == 0
         && this.wF() == 0
         && this.If() == 0
         && this.Dz() == 0
         && Arrays.equals(this.mI(), new byte[4])
         && Arrays.equals(this.jq(), new byte[8])
         && this.ui() == 0;
   }

   @Override
   public final byte[] oW() {
      return this.q(false);
   }

   @Override
   public final byte[] q(boolean var1) {
      ByteBuffer var2 = ByteBuffer.allocate(this.q()).order(ByteOrder.LITTLE_ENDIAN);
      var2.putInt(this.q());
      var2.putShort((short)this.RF());
      var2.putShort((short)this.xK());
      var2.put(this.Dw());
      var2.put(this.gO());
      var2.put((byte)this.gP());
      var2.put((byte)this.za());
      var2.putShort((short)this.lm());
      var2.put((byte)this.zz());
      var2.put((byte)this.JY());
      var2.put((byte)this.HF());
      var2.put((byte)0);
      var2.putShort((short)this.qa());
      var2.putShort((short)this.Hk());
      var2.putShort((short)this.Me());
      var2.putShort((short)this.PV());
      if (this.q() >= 32) {
         var2.put((byte)this.oQ());
         var2.put((byte)this.cC());
         var2.putShort((short)this.wF());
      }

      if (this.q() >= 36) {
         var2.putShort((short)this.If());
         var2.putShort((short)this.Dz());
      }

      if (this.q() >= 48) {
         var2.put(this.mI());
         var2.put(this.jq());
      }

      if (this.q() >= 52) {
         var2.putInt(this.ui());
      }

      var2.put(this.TX());
      return var2.array();
   }

   @Override
   public final String toString() {
      if (this.Rr()) {
         return "default";
      } else {
         Collection var1 = this.EB().values();
         var1.removeAll(Collections.singleton(""));
         return Joiner.on('-').join(var1);
      }
   }

   public final Map EB() {
      LinkedHashMap var1 = new LinkedHashMap();
      var1.put(oD.CU.q, this.RF() != 0 ? "mcc" + this.RF() : "");
      var1.put(oD.CU.RF, this.xK() != 0 ? "mnc" + this.xK() : "");
      var1.put(oD.CU.xK, !this.Uv().isEmpty() ? StringConcatFactory.makeConcatWithConstants<"makeConcatWithConstants","\u0001">(this.Uv()) : "");
      var1.put(oD.CU.Dw, !this.nf().isEmpty() ? "r" + this.nf() : "");
      var1.put(oD.CU.Uv, (String)this.q(Rr, this.xW(), ""));
      var1.put(oD.CU.oW, this.wF() != 0 ? "sw" + this.wF() + "dp" : "");
      var1.put(oD.CU.gO, this.If() != 0 ? "w" + this.If() + "dp" : "");
      var1.put(oD.CU.nf, this.Dz() != 0 ? "h" + this.Dz() + "dp" : "");
      var1.put(oD.CU.gP, (String)this.q(AB, this.KT(), ""));
      var1.put(oD.CU.za, (String)this.q(IN, this.Gf(), ""));
      var1.put(oD.CU.lm, (String)this.q(Rv, this.Ef(), ""));
      var1.put(oD.CU.zz, (String)this.q(mI, this.gP(), ""));
      var1.put(oD.CU.JY, (String)this.q(Lj, this.sH(), ""));
      var1.put(oD.CU.HF, (String)this.q(lF, this.CE(), ""));
      var1.put(oD.CU.LK, (String)this.q(lm, this.lm(), this.lm() + "dpi"));
      var1.put(oD.CU.io, (String)this.q(Tq, this.za(), ""));
      var1.put(oD.CU.qa, (String)this.q(PV, this.LK(), ""));
      var1.put(oD.CU.Hk, (String)this.q(LK, this.zz(), ""));
      var1.put(oD.CU.Me, (String)this.q(wF, this.io(), ""));
      var1.put(oD.CU.PV, (String)this.q(Ef, this.JY(), ""));
      var1.put(oD.CU.oQ, this.Me() != 0 ? "v" + this.Me() : "");
      return var1;
   }

   private Object q(Map var1, Object var2, Object var3) {
      Object var4 = var1.get(var2);
      return var4 != null ? var4 : var3;
   }

   public static enum CU {
      q,
      RF,
      xK,
      Dw,
      Uv,
      oW,
      gO,
      nf,
      gP,
      za,
      lm,
      zz,
      JY,
      HF,
      LK,
      io,
      qa,
      Hk,
      Me,
      PV,
      oQ;
   }

   static class eo extends oD {
      int q;
      int RF;
      int xK;
      byte[] Dw;
      byte[] Uv;
      int oW;
      int gO;
      int nf;
      int gP;
      int za;
      int lm;
      int zz;
      int JY;
      int HF;
      int LK;
      int io;
      int qa;
      int Hk;
      int Me;
      int PV;
      byte[] oQ;
      byte[] xW;
      int KT;
      byte[] Gf;

      public eo(
         int var1,
         int var2,
         int var3,
         byte[] var4,
         byte[] var5,
         int var6,
         int var7,
         int var8,
         int var9,
         int var10,
         int var11,
         int var12,
         int var13,
         int var14,
         int var15,
         int var16,
         int var17,
         int var18,
         int var19,
         int var20,
         byte[] var21,
         byte[] var22,
         int var23,
         byte[] var24
      ) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
         this.Dw = var4;
         this.Uv = var5;
         this.oW = var6;
         this.gO = var7;
         this.nf = var8;
         this.gP = var9;
         this.za = var10;
         this.lm = var11;
         this.zz = var12;
         this.JY = var13;
         this.HF = var14;
         this.LK = var15;
         this.io = var16;
         this.qa = var17;
         this.Hk = var18;
         this.Me = var19;
         this.PV = var20;
         this.oQ = var21;
         this.xW = var22;
         this.KT = var23;
         this.Gf = var24;
      }

      @Override
      public int q() {
         return this.q;
      }

      @Override
      public int RF() {
         return this.RF;
      }

      @Override
      public int xK() {
         return this.xK;
      }

      @Override
      public byte[] Dw() {
         return this.Dw;
      }

      @Override
      public byte[] gO() {
         return this.Uv;
      }

      @Override
      public int gP() {
         return this.oW;
      }

      @Override
      public int za() {
         return this.gO;
      }

      @Override
      public int lm() {
         return this.nf;
      }

      @Override
      public int zz() {
         return this.gP;
      }

      @Override
      public int JY() {
         return this.za;
      }

      @Override
      public int HF() {
         return this.lm;
      }

      @Override
      public int qa() {
         return this.zz;
      }

      @Override
      public int Hk() {
         return this.JY;
      }

      @Override
      public int Me() {
         return this.HF;
      }

      @Override
      public int PV() {
         return this.LK;
      }

      @Override
      public int oQ() {
         return this.io;
      }

      @Override
      public int cC() {
         return this.qa;
      }

      @Override
      public int wF() {
         return this.Hk;
      }

      @Override
      public int If() {
         return this.Me;
      }

      @Override
      public int Dz() {
         return this.PV;
      }

      @Override
      public byte[] mI() {
         return this.oQ;
      }

      @Override
      public byte[] jq() {
         return this.xW;
      }

      @Override
      public int ui() {
         return this.KT;
      }

      @Override
      public byte[] TX() {
         return this.Gf;
      }
   }
}
