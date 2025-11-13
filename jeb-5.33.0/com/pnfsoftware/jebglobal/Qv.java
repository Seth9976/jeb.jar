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

public abstract class Qv {
   private static final ImmutableMap pC = ImmutableMap.builder()
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
   private static final ImmutableMap A = ImmutableMap.of(1, "nokeys", 2, "qwerty", 3, "12key");
   private static final ImmutableMap kS = ImmutableMap.of(1, "keysexposed", 2, "keyshidden", 3, "keyssoft");
   private static final ImmutableMap wS = ImmutableMap.of(1, "nonav", 2, "dpad", 3, "trackball", 4, "wheel");
   private static final ImmutableMap UT = ImmutableMap.of(4, "navexposed", 8, "navhidden");
   private static final ImmutableMap E = ImmutableMap.of(1, "port", 2, "land");
   private static final ImmutableMap sY = ImmutableMap.of(64, "ldltr", 128, "ldrtl");
   private static final ImmutableMap ys = ImmutableMap.of(16, "notlong", 32, "long");
   private static final ImmutableMap ld = ImmutableMap.of(256, "notround", 512, "round");
   private static final ImmutableMap gp = ImmutableMap.of(1, "small", 2, "normal", 3, "large", 4, "xlarge");
   private static final ImmutableMap oT = ImmutableMap.of(1, "notouch", 3, "finger");
   private static final ImmutableMap fI = ImmutableMap.of(16, "notnight", 32, "night");
   private static final ImmutableMap WR = ImmutableMap.of(2, "desk", 3, "car", 4, "television", 5, "appliance", 6, "watch");

   public abstract int pC();

   public abstract int A();

   public abstract int kS();

   public abstract byte[] wS();

   public final String UT() {
      return this.UW();
   }

   public abstract byte[] E();

   public final String sY() {
      return this.PR();
   }

   public abstract int ys();

   public abstract int ld();

   public abstract int gp();

   public abstract int oT();

   public abstract int fI();

   public abstract int WR();

   public final int NS() {
      return this.WR() & 3;
   }

   public final int vP() {
      return this.WR() & 12;
   }

   public abstract int xC();

   public abstract int ED();

   public abstract int Sc();

   public abstract int ah();

   public abstract int eP();

   public final int UO() {
      return this.eP() & 192;
   }

   public final int Ab() {
      return this.eP() & 15;
   }

   public final int rl() {
      return this.eP() & 48;
   }

   public final int z() {
      return this.eP() & 768;
   }

   public abstract int Ek();

   public final int hK() {
      return this.Ek() & 15;
   }

   public final int Er() {
      return this.Ek() & 48;
   }

   public abstract int FE();

   public abstract int Aj();

   public abstract int EX();

   public abstract byte[] LM();

   public abstract byte[] mv();

   public abstract int sO();

   public abstract byte[] os();

   static Qv pC(ByteBuffer var0) {
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
      return new Qv.Av(
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

   private String UW() {
      return this.pC(this.wS(), 97);
   }

   private String PR() {
      return this.pC(this.E(), 48);
   }

   private String pC(byte[] var1, int var2) {
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

   public final boolean Cu() {
      return this.A() == 0
         && this.kS() == 0
         && Arrays.equals(this.wS(), new byte[2])
         && Arrays.equals(this.E(), new byte[2])
         && this.ys() == 0
         && this.ld() == 0
         && this.gp() == 0
         && this.oT() == 0
         && this.fI() == 0
         && this.WR() == 0
         && this.xC() == 0
         && this.ED() == 0
         && this.Sc() == 0
         && this.ah() == 0
         && this.eP() == 0
         && this.Ek() == 0
         && this.FE() == 0
         && this.Aj() == 0
         && this.EX() == 0
         && Arrays.equals(this.LM(), new byte[4])
         && Arrays.equals(this.mv(), new byte[8])
         && this.sO() == 0;
   }

   public final byte[] pC(boolean var1) {
      ByteBuffer var2 = ByteBuffer.allocate(this.pC()).order(ByteOrder.LITTLE_ENDIAN);
      var2.putInt(this.pC());
      var2.putShort((short)this.A());
      var2.putShort((short)this.kS());
      var2.put(this.wS());
      var2.put(this.E());
      var2.put((byte)this.ys());
      var2.put((byte)this.ld());
      var2.putShort((short)this.gp());
      var2.put((byte)this.oT());
      var2.put((byte)this.fI());
      var2.put((byte)this.WR());
      var2.put((byte)0);
      var2.putShort((short)this.xC());
      var2.putShort((short)this.ED());
      var2.putShort((short)this.Sc());
      var2.putShort((short)this.ah());
      if (this.pC() >= 32) {
         var2.put((byte)this.eP());
         var2.put((byte)this.Ek());
         var2.putShort((short)this.FE());
      }

      if (this.pC() >= 36) {
         var2.putShort((short)this.Aj());
         var2.putShort((short)this.EX());
      }

      if (this.pC() >= 48) {
         var2.put(this.LM());
         var2.put(this.mv());
      }

      if (this.pC() >= 52) {
         var2.putInt(this.sO());
      }

      var2.put(this.os());
      return var2.array();
   }

   @Override
   public final String toString() {
      if (this.Cu()) {
         return "default";
      } else {
         Collection var1 = this.hZ().values();
         var1.removeAll(Collections.singleton(""));
         return Joiner.on('-').join(var1);
      }
   }

   public final Map hZ() {
      LinkedHashMap var1 = new LinkedHashMap();
      var1.put(Qv.Sv.pC, this.A() != 0 ? "mcc" + this.A() : "");
      var1.put(Qv.Sv.A, this.kS() != 0 ? "mnc" + this.kS() : "");
      var1.put(Qv.Sv.kS, !this.UT().isEmpty() ? StringConcatFactory.makeConcatWithConstants<"makeConcatWithConstants","\u0001">(this.UT()) : "");
      var1.put(Qv.Sv.wS, !this.sY().isEmpty() ? "r" + this.sY() : "");
      var1.put(Qv.Sv.UT, (String)this.pC(sY, this.UO(), ""));
      var1.put(Qv.Sv.E, this.FE() != 0 ? "sw" + this.FE() + "dp" : "");
      var1.put(Qv.Sv.sY, this.Aj() != 0 ? "w" + this.Aj() + "dp" : "");
      var1.put(Qv.Sv.ys, this.EX() != 0 ? "h" + this.EX() + "dp" : "");
      var1.put(Qv.Sv.ld, (String)this.pC(gp, this.Ab(), ""));
      var1.put(Qv.Sv.gp, (String)this.pC(ys, this.rl(), ""));
      var1.put(Qv.Sv.oT, (String)this.pC(ld, this.z(), ""));
      var1.put(Qv.Sv.fI, (String)this.pC(E, this.ys(), ""));
      var1.put(Qv.Sv.WR, (String)this.pC(WR, this.hK(), ""));
      var1.put(Qv.Sv.NS, (String)this.pC(fI, this.Er(), ""));
      var1.put(Qv.Sv.vP, (String)this.pC(pC, this.gp(), this.gp() + "dpi"));
      var1.put(Qv.Sv.xC, (String)this.pC(oT, this.ld(), ""));
      var1.put(Qv.Sv.ED, (String)this.pC(kS, this.NS(), ""));
      var1.put(Qv.Sv.Sc, (String)this.pC(A, this.oT(), ""));
      var1.put(Qv.Sv.ah, (String)this.pC(UT, this.vP(), ""));
      var1.put(Qv.Sv.eP, (String)this.pC(wS, this.fI(), ""));
      var1.put(Qv.Sv.UO, this.Sc() != 0 ? "v" + this.Sc() : "");
      return var1;
   }

   private Object pC(Map var1, Object var2, Object var3) {
      Object var4 = var1.get(var2);
      return var4 != null ? var4 : var3;
   }

   static class Av extends Qv {
      int pC;
      int A;
      int kS;
      byte[] wS;
      byte[] UT;
      int E;
      int sY;
      int ys;
      int ld;
      int gp;
      int oT;
      int fI;
      int WR;
      int NS;
      int vP;
      int xC;
      int ED;
      int Sc;
      int ah;
      int eP;
      byte[] UO;
      byte[] Ab;
      int rl;
      byte[] z;

      public Av(
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
         this.NS = var14;
         this.vP = var15;
         this.xC = var16;
         this.ED = var17;
         this.Sc = var18;
         this.ah = var19;
         this.eP = var20;
         this.UO = var21;
         this.Ab = var22;
         this.rl = var23;
         this.z = var24;
      }

      @Override
      public int pC() {
         return this.pC;
      }

      @Override
      public int A() {
         return this.A;
      }

      @Override
      public int kS() {
         return this.kS;
      }

      @Override
      public byte[] wS() {
         return this.wS;
      }

      @Override
      public byte[] E() {
         return this.UT;
      }

      @Override
      public int ys() {
         return this.E;
      }

      @Override
      public int ld() {
         return this.sY;
      }

      @Override
      public int gp() {
         return this.ys;
      }

      @Override
      public int oT() {
         return this.ld;
      }

      @Override
      public int fI() {
         return this.gp;
      }

      @Override
      public int WR() {
         return this.oT;
      }

      @Override
      public int xC() {
         return this.fI;
      }

      @Override
      public int ED() {
         return this.WR;
      }

      @Override
      public int Sc() {
         return this.NS;
      }

      @Override
      public int ah() {
         return this.vP;
      }

      @Override
      public int eP() {
         return this.xC;
      }

      @Override
      public int Ek() {
         return this.ED;
      }

      @Override
      public int FE() {
         return this.Sc;
      }

      @Override
      public int Aj() {
         return this.ah;
      }

      @Override
      public int EX() {
         return this.eP;
      }

      @Override
      public byte[] LM() {
         return this.UO;
      }

      @Override
      public byte[] mv() {
         return this.Ab;
      }

      @Override
      public int sO() {
         return this.rl;
      }

      @Override
      public byte[] os() {
         return this.z;
      }
   }

   public static enum Sv {
      pC,
      A,
      kS,
      wS,
      UT,
      E,
      sY,
      ys,
      ld,
      gp,
      oT,
      fI,
      WR,
      NS,
      vP,
      xC,
      ED,
      Sc,
      ah,
      eP,
      UO;
   }
}
