package com.pnfsoftware.jebglobal;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.io.LittleEndianDataInputStream;
import com.google.common.io.LittleEndianDataOutputStream;
import com.google.common.primitives.Shorts;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;
import javax.annotation.Nullable;

public abstract class oV implements vv {
   private static final ILogger oW = GlobalLog.getLogger();
   public static final int q = 4;
   public static final int RF = 8;
   private static final int gO = 4;
   @Nullable
   private final oV nf;
   protected final int xK;
   protected final int Dw;
   protected final int Uv;

   protected oV(ByteBuffer var1, @Nullable oV var2) {
      this.nf = var2;
      this.Uv = var1.position() - 2;
      this.xK = var1.getShort() & '\uffff';
      this.Dw = var1.getInt();
   }

   protected void q(ByteBuffer var1) {
   }

   @Nullable
   public oV q() {
      return this.nf;
   }

   protected abstract oV.eo RF();

   protected int xK() {
      return this.RF().q();
   }

   public final int Dw() {
      return this.xK;
   }

   public final int Uv() {
      return this.Dw;
   }

   protected final void RF(ByteBuffer var1) {
      var1.position(this.Uv + this.Dw);
   }

   protected final void q(ByteBuffer var1, int var2) {
      int var3 = var1.position();
      var1.putShort(this.RF().q());
      var1.putShort((short)this.xK);
      var1.putInt(var2);
      this.xK(var1);
      int var4 = var1.position() - var3;
      Preconditions.checkState(var4 == this.Dw());
   }

   protected void xK(ByteBuffer var1) {
   }

   protected void q(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
   }

   protected static int q(DataOutput var0, int var1) throws IOException {
      while (var1 % 4 != 0) {
         var0.write(0);
         var1++;
      }

      return var1;
   }

   @Override
   public final byte[] oW() throws IOException {
      return this.q(false);
   }

   @Override
   public final byte[] q(boolean var1) throws IOException {
      ByteBuffer var2 = ByteBuffer.allocate(this.Dw()).order(ByteOrder.LITTLE_ENDIAN);
      this.q(var2, 0);
      ByteArrayOutputStream var3 = new ByteArrayOutputStream();
      LittleEndianDataOutputStream var4 = new LittleEndianDataOutputStream(var3);

      try {
         this.q(var4, var2, var1);
      } catch (Throwable var8) {
         try {
            var4.close();
         } catch (Throwable var7) {
            var8.addSuppressed(var7);
         }

         throw var8;
      }

      var4.close();
      byte[] var9 = var3.toByteArray();
      int var5 = this.Dw() + var9.length;
      var2.putInt(4, var5);
      ByteBuffer var6 = ByteBuffer.allocate(var5).order(ByteOrder.LITTLE_ENDIAN);
      var6.put(var2.array());
      var6.put(var9);
      return var6.array();
   }

   static oV.eo q(int var0) {
      return oV.eo.q((short)(var0 & 4095));
   }

   public static oV q(LittleEndianDataInputStream var0, oV var1, Yy var2) throws IOException {
      short var3 = var0.readShort();
      boolean var4 = (var3 & 4096) != 0;
      short var5 = (short)(var3 & 4095);

      Object var6 = switch (q(var3)) {
         case RF -> {
            Preconditions.checkArgument(var4);
            Po var28 = new Po(var0, var1);
            if (var2 == null) {
               throw new RuntimeException("Unsupported/TODO: compressed String pool without a meta-entry");
            }

            int[] var32 = new int[var28.JY];
            OK var35 = new OK(var0, var28.za() ? Charset.forName("UTF-8") : Charset.forName("UTF-16"));
            TreeSet var39 = new TreeSet();

            for (int var41 = var2.xK.nextSetBit(0); var41 >= 0; var41 = var2.xK.nextSetBit(var41 + 1)) {
               var39.add(var41);
            }

            Iterator var42 = var39.iterator();

            int var44;
            for (var44 = 0; var42.hasNext(); var44++) {
               int var48 = (Integer)var42.next();
               var35.q(var48 - var44);

               while (var44 < var48) {
                  var32[var44] = -1;
                  var44++;
               }

               Preconditions.checkArgument(var44 == var48);
               var28.LK.add(var35.RF());
               var32[var44] = var28.LK.size() - 1;
            }

            var35.q(var28.JY - var44);
            var35.q();

            while (var44 < var28.JY) {
               var32[var44] = -1;
               var44++;
            }

            byte[] var49 = new byte[var0.readInt()];
            var0.readFully(var49);
            ByteBuffer var50 = ByteBuffer.wrap(var49).order(ByteOrder.LITTLE_ENDIAN);
            ArrayList var51 = new ArrayList();
            Iterator var52 = var39.iterator();
            int var54 = 0;

            while (var52.hasNext()) {
               int var53 = (Integer)var52.next();
               if (var53 >= var28.HF) {
                  break;
               }

               for (int var56 = var53 - var54; var56 > 0; var56--) {
                  pV.CU.q(var50, 0, var28);
               }

               var51.add(pV.CU.q(var50, 0, var28));
               var54 = var53 + 1;
            }

            ArrayList var55 = new ArrayList(var51.size());

            for (int var57 = 0; var57 < var51.size(); var57++) {
               pV.CU var58 = (pV.CU)var51.get(var57);
               ArrayList var59 = new ArrayList(var58.q().size());

               for (pV.eo var23 : var58.q()) {
                  int var24 = var32[var23.q()];
                  var59.add(new pV.eo.eo(var24, var23.RF(), var23.xK(), var23.Dw()));
               }

               var55.add(pV.CU.q(Collections.unmodifiableList(var59)));
            }

            var28.io = var55;
            yield var28;
         }
         case xK -> {
            su var27 = new su(var0, var1);
            Map var31 = var27.gO();
            int var34 = var0.readInt();

            for (int var38 = 0; var38 < var34; var38++) {
               var31.put(var27.Uv + var38, q(var0, var27, var2));
            }

            var27.za();
            yield var27;
         }
         case lm -> {
            Preconditions.checkState(var4);
            is var26 = new is(var0, var1);
            int var30 = var0.readInt();
            var26.nf = PH.q(var0, false);
            var26.gO = PH.q(var0, false);
            int var33 = var30 - 2;
            if (var2 == null) {
               for (int var37 = 0; var37 < var33; var37++) {
                  var26.oW.put(var26.Uv + var26.xK + var37, q(var0, var26, var2));
               }
            } else {
               Iterator var36 = var2.RF.iterator();
               int var40 = 0;

               while (var36.hasNext()) {
                  int var43 = (Integer)var36.next();
                  Preconditions.checkState(var43 >= var40);
                  int var46 = var43 - var40;

                  while (var46 > 0) {
                     var46 -= var0.skipBytes(var46);
                  }

                  oV var47 = q(var0, var26, var2);
                  var26.oW.put(var26.Uv + var26.xK + var43, var47);
                  var40 = var47.Dw + var43;
               }
            }

            yield var26;
         }
         case zz -> {
            if (var4) {
               Mr var7 = new Mr(var0, var1);
               byte[] var8 = new byte[var7.Dw - var7.xK];
               var0.readFully(var8);
               ByteBuffer var9 = ByteBuffer.wrap(var8).order(ByteOrder.LITTLE_ENDIAN);
               int var10 = var9.getInt();
               int var11 = TS.q(var9);
               ArrayList var12 = new ArrayList();

               for (int var13 = 0; var13 < var11; var13++) {
                  var12.add(TS.q(var9));
               }

               int var45 = 0;
               int var14 = 0;
               int var17 = 0;

               while (var17 < var12.size()) {
                  int var18 = (Integer)var12.get(var17);
                  int var15;
                  int var16;
                  if (var18 == 0) {
                     var15 = var14;
                     var16 = var17;
                  } else {
                     int var19 = var18 == -2 ? 0 : var18;
                     int var20 = var17 + 1;
                     Preconditions.checkState(var20 < var12.size());
                     int var21 = var14 + (Integer)var12.get(var20);
                     Dg var22 = PB.eo.JY().RF(var10).q(8).xK(var21).q(var7).q(Gn.Dw().RF(var19).q(8).q(Gn.CU.Dw).q()).Dw(0).q(new LinkedHashMap());
                     Preconditions.checkState(!var7.zz.containsKey(var45));
                     var7.zz.put(var45, var22.q());
                     var15 = var21;
                     var16 = var20;
                  }

                  var45++;
                  var14 = var15;
                  var17 = var16 + 1;
               }

               yield var7;
            } else {
               ByteBuffer var25 = PH.q(var0);
               PB var29 = new PB(var25, var1);
               var29.q(var25);
               yield var29;
            }
         }
         case JY -> {
            Preconditions.checkState(!var4);
            yield new cA(var0, var1);
         }
         default -> throw new RuntimeException("Chunk is not handled: " + oV.eo.q(var5));
      };
      Preconditions.checkNotNull(var6);
      return (oV)var6;
   }

   public static oV Dw(ByteBuffer var0) {
      return q(var0, null);
   }

   public static oV q(ByteBuffer var0, oV var1) {
      short var2 = var0.getShort();
      Object var3;
      switch (q(var2)) {
         case RF:
            var3 = new Po(var0, var1);
            ((oV)var3).q(var0);
            break;
         case xK:
            var3 = new su(var0, var1);
            ((oV)var3).q(var0);
            break;
         case lm:
            var3 = new is(var0, var1);
            ((oV)var3).q(var0);
            break;
         case zz:
            var3 = new Mr(var0, var1);
            ((oV)var3).q(var0);
            break;
         case JY:
            var3 = new cA(var0, var1);
            ((oV)var3).q(var0);
            break;
         default:
            throw new RuntimeException("Chunk is not handled: " + Integer.toHexString(var2));
      }

      Preconditions.checkNotNull(var3);
      ((oV)var3).RF(var0);
      return (oV)var3;
   }

   public static enum eo {
      q(0),
      RF(1),
      xK(2),
      Dw(3),
      Uv(256),
      oW(257),
      gO(258),
      nf(259),
      gP(260),
      za(384),
      lm(512),
      zz(513),
      JY(514),
      HF(515);

      private final short LK;
      private static final Map io;

      private eo(int var3) {
         this.LK = Shorts.checkedCast(var3);
      }

      public short q() {
         return this.LK;
      }

      public short q(boolean var1) {
         return var1 ? (short)(this.LK | 4096) : this.LK;
      }

      public static oV.eo q(short var0) {
         return (oV.eo)Preconditions.checkNotNull((oV.eo)io.get(var0), "Unknown chunk type: %s", var0);
      }

      static {
         Builder var0 = ImmutableMap.builder();

         for (oV.eo var4 : values()) {
            var0.put(var4.q(), var4);
         }

         io = var0.build();
      }
   }
}
