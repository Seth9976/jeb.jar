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

public abstract class Ij {
   private static final ILogger wS = GlobalLog.getLogger();
   @Nullable
   private final Ij UT;
   protected final int pC;
   protected final int A;
   protected final int kS;

   protected Ij(ByteBuffer var1, @Nullable Ij var2) {
      this.UT = var2;
      this.kS = var1.position() - 2;
      this.pC = var1.getShort() & '\uffff';
      this.A = var1.getInt();
   }

   protected void pC(ByteBuffer var1) {
   }

   @Nullable
   public Ij pC() {
      return this.UT;
   }

   protected abstract Ij.Av A();

   public final int kS() {
      return this.pC;
   }

   public final int wS() {
      return this.A;
   }

   protected final void A(ByteBuffer var1) {
      var1.position(this.kS + this.A);
   }

   protected final void pC(ByteBuffer var1, int var2) {
      int var3 = var1.position();
      var1.putShort(this.A().pC());
      var1.putShort((short)this.pC);
      var1.putInt(var2);
      this.kS(var1);
      int var4 = var1.position() - var3;
      Preconditions.checkState(var4 == this.kS());
   }

   protected void kS(ByteBuffer var1) {
   }

   protected void pC(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
   }

   protected static int pC(DataOutput var0, int var1) throws IOException {
      while (var1 % 4 != 0) {
         var0.write(0);
         var1++;
      }

      return var1;
   }

   public final byte[] pC(boolean var1) throws IOException {
      ByteBuffer var2 = ByteBuffer.allocate(this.kS()).order(ByteOrder.LITTLE_ENDIAN);
      this.pC(var2, 0);
      ByteArrayOutputStream var3 = new ByteArrayOutputStream();
      LittleEndianDataOutputStream var4 = new LittleEndianDataOutputStream(var3);

      try {
         this.pC(var4, var2, var1);
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
      int var5 = this.kS() + var9.length;
      var2.putInt(4, var5);
      ByteBuffer var6 = ByteBuffer.allocate(var5).order(ByteOrder.LITTLE_ENDIAN);
      var6.put(var2.array());
      var6.put(var9);
      return var6.array();
   }

   static Ij.Av pC(int var0) {
      return Ij.Av.pC((short)(var0 & 4095));
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public static Ij pC(LittleEndianDataInputStream var0, Ij var1, g var2) throws IOException {
      short var3 = var0.readShort();
      boolean var4 = (var3 & 4096) != 0;
      short var5 = (short)(var3 & 4095);

      Object var6 = switch (pC(var3)) {
         case A -> {
            Preconditions.checkArgument(var4);
            CL var28 = new CL(var0, var1);
            if (var2 == null) {
               throw new RuntimeException("Unsupported/TODO: compressed String pool without a meta-entry");
            }

            int[] var32 = new int[var28.ys];
            qh var35 = new qh(var0, var28.E() ? Charset.forName("UTF-8") : Charset.forName("UTF-16"));
            TreeSet var39 = new TreeSet();

            for (int var41 = var2.A.nextSetBit(0); var41 >= 0; var41 = var2.A.nextSetBit(var41 + 1)) {
               var39.add(var41);
            }

            Iterator var42 = var39.iterator();

            int var44;
            for (var44 = 0; var42.hasNext(); var44++) {
               int var48 = (Integer)var42.next();
               var35.pC(var48 - var44);

               while (var44 < var48) {
                  var32[var44] = -1;
                  var44++;
               }

               Preconditions.checkArgument(var44 == var48);
               var28.gp.add(var35.A());
               var32[var44] = var28.gp.size() - 1;
            }

            var35.pC(var28.ys - var44);
            var35.pC();

            while (var44 < var28.ys) {
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
               if (var53 >= var28.ld) {
                  break;
               }

               for (int var56 = var53 - var54; var56 > 0; var56--) {
                  xx.Sv.pC(var50, 0, var28);
               }

               var51.add(xx.Sv.pC(var50, 0, var28));
               var54 = var53 + 1;
            }

            ArrayList var55 = new ArrayList(var51.size());

            for (int var57 = 0; var57 < var51.size(); var57++) {
               xx.Sv var58 = (xx.Sv)var51.get(var57);
               ArrayList var59 = new ArrayList(var58.pC().size());

               for (xx.Av var23 : var58.pC()) {
                  int var24 = var32[var23.pC()];
                  var59.add(new xx.Av.Av(var24, var23.A(), var23.kS(), var23.wS()));
               }

               var55.add(xx.Sv.pC(Collections.unmodifiableList(var59)));
            }

            var28.oT = var55;
            yield var28;
         }
         case kS -> {
            eH var27 = new eH(var0, var1);
            Map var31 = var27.UT();
            int var34 = var0.readInt();

            for (int var38 = 0; var38 < var34; var38++) {
               var31.put(var27.kS + var38, pC(var0, var27, var2));
            }

            var27.E();
            yield var27;
         }
         case oT -> {
            Preconditions.checkState(var4);
            tQ var26 = new tQ(var0, var1);
            int var30 = var0.readInt();
            var26.E = Jn.pC(var0, false);
            var26.UT = Jn.pC(var0, false);
            int var33 = var30 - 2;
            if (var2 == null) {
               for (int var37 = 0; var37 < var33; var37++) {
                  var26.wS.put(var26.kS + var26.pC + var37, pC(var0, var26, var2));
               }
            } else {
               Iterator var36 = var2.pC.iterator();
               int var40 = 0;

               while (var36.hasNext()) {
                  int var43 = (Integer)var36.next();
                  Preconditions.checkState(var43 >= var40);
                  int var46 = var43 - var40;

                  while (var46 > 0) {
                     var46 -= var0.skipBytes(var46);
                  }

                  Ij var47 = pC(var0, var26, var2);
                  var26.wS.put(var26.kS + var26.pC + var43, var47);
                  var40 = var47.A + var43;
               }
            }

            yield var26;
         }
         case fI -> {
            if (var4) {
               ID var7 = new ID(var0, var1);
               byte[] var8 = new byte[var7.A - var7.pC];
               var0.readFully(var8);
               ByteBuffer var9 = ByteBuffer.wrap(var8).order(ByteOrder.LITTLE_ENDIAN);
               int var10 = var9.getInt();
               int var11 = Se.pC(var9);
               ArrayList var12 = new ArrayList();

               for (int var13 = 0; var13 < var11; var13++) {
                  var12.add(Se.pC(var9));
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
                     wv var22 = MS.Av.ld().A(var10).pC(8).kS(var21).pC(var7).pC(wR.UT().A(var19).pC(8).pC(wR.Sv.wS).pC()).wS(0).pC(new LinkedHashMap());
                     Preconditions.checkState(!var7.gp.containsKey(var45));
                     var7.gp.put(var45, var22.pC());
                     var15 = var21;
                     var16 = var20;
                  }

                  var45++;
                  var14 = var15;
                  var17 = var16 + 1;
               }

               yield var7;
            } else {
               ByteBuffer var25 = Jn.pC(var0);
               MS var29 = new MS(var25, var1);
               var29.pC(var25);
               yield var29;
            }
         }
         case WR -> {
            Preconditions.checkState(!var4);
            yield new HN(var0, var1);
         }
         default -> throw new RuntimeException("Chunk is not handled: " + Ij.Av.pC(var5));
      };
      Preconditions.checkNotNull(var6);
      return (Ij)var6;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public static Ij pC(ByteBuffer var0, Ij var1) {
      short var2 = var0.getShort();
      Object var3;
      switch (pC(var2)) {
         case A:
            var3 = new CL(var0, var1);
            ((Ij)var3).pC(var0);
            break;
         case kS:
            var3 = new eH(var0, var1);
            ((Ij)var3).pC(var0);
            break;
         case oT:
            var3 = new tQ(var0, var1);
            ((Ij)var3).pC(var0);
            break;
         case fI:
            var3 = new ID(var0, var1);
            ((Ij)var3).pC(var0);
            break;
         case WR:
            var3 = new HN(var0, var1);
            ((Ij)var3).pC(var0);
            break;
         default:
            throw new RuntimeException("Chunk is not handled: " + Integer.toHexString(var2));
      }

      Preconditions.checkNotNull(var3);
      ((Ij)var3).A(var0);
      return (Ij)var3;
   }

   public static enum Av {
      pC(0),
      A(1),
      kS(2),
      wS(3),
      UT(256),
      E(257),
      sY(258),
      ys(259),
      ld(260),
      gp(384),
      oT(512),
      fI(513),
      WR(514),
      NS(515);

      private final short vP;
      private static final Map xC;

      private Av(int var3) {
         this.vP = Shorts.checkedCast(var3);
      }

      public short pC() {
         return this.vP;
      }

      public static Ij.Av pC(short var0) {
         return (Ij.Av)Preconditions.checkNotNull((Ij.Av)xC.get(var0), "Unknown chunk type: %s", var0);
      }

      static {
         Builder var0 = ImmutableMap.builder();

         for (Ij.Av var4 : values()) {
            var0.put(var4.pC(), var4);
         }

         xC = var0.build();
      }
   }
}
