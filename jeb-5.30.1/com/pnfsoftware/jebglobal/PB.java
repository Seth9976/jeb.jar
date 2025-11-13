package com.pnfsoftware.jebglobal;

import com.google.common.base.Preconditions;
import com.google.common.io.LittleEndianDataOutputStream;
import com.google.common.primitives.UnsignedBytes;
import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import javax.annotation.Nullable;

public class PB extends oV {
   protected int gO;
   protected int nf;
   protected int gP;
   protected int za;
   protected oD lm;
   protected Map zz = new TreeMap();

   protected PB(ByteBuffer var1, @Nullable oV var2) {
      super(var1, var2);
      this.gO = UnsignedBytes.toInt(var1.get());
      this.nf = UnsignedBytes.toInt(var1.get());
      var1.position(var1.position() + 2);
      this.gP = var1.getInt();
      this.za = var1.getInt();
      this.lm = oD.q(var1);
   }

   @Override
   protected void q(ByteBuffer var1) {
      int var2 = this.Uv + this.za;
      if ((this.nf & 1) == 0) {
         for (int var3 = 0; var3 < this.gP; var3++) {
            PB.eo var4 = PB.eo.q(var1, var2, this);
            if (var4 != null) {
               this.zz.put(var3, var4);
            }
         }
      } else {
         for (int var5 = 0; var5 < this.gP; var5++) {
            this.zz.put(Integer.valueOf((char)var1.getShort()), PB.eo.q(var1, (char)var1.getShort() * 4 + var2, this));
         }
      }
   }

   public int nf() {
      return this.gO;
   }

   public String gP() {
      Wo var1 = this.JY();
      Preconditions.checkNotNull(var1);
      pV var2 = var1.nf();
      Preconditions.checkNotNull(var2);
      return var2.RF(this.nf() - 1);
   }

   public oD za() {
      return this.lm;
   }

   public void q(oD var1) {
      this.lm = var1;
   }

   public int lm() {
      return this.gP;
   }

   public Map zz() {
      return Collections.unmodifiableMap(this.zz);
   }

   public boolean q(rV var1) {
      Wo var2 = (Wo)Preconditions.checkNotNull(this.JY());
      int var3 = var2.za();
      int var4 = this.nf();
      return var1.q() == var3 && var1.RF() == var4 && this.zz.containsKey(var1.xK());
   }

   public void q(Map var1) {
      for (Entry var3 : var1.entrySet()) {
         int var4 = var3.getKey() != null ? (Integer)var3.getKey() : -1;
         this.q(var4, (PB.eo)var3.getValue());
      }
   }

   public void q(int var1, @Nullable PB.eo var2) {
      if (var1 >= 0 && var1 < this.gP) {
         if (var2 != null) {
            this.zz.put(var1, var2);
         } else {
            this.zz.remove(var1);
         }
      }
   }

   protected String RF(int var1) {
      Xm var2 = this.gO();
      Preconditions.checkNotNull(var2);
      return var2.nf().RF(var1);
   }

   protected String xK(int var1) {
      Wo var2 = this.JY();
      Preconditions.checkNotNull(var2);
      pV var3 = var2.gP();
      Preconditions.checkNotNull(var3);
      return var3.RF(var1);
   }

   @Nullable
   private Xm gO() {
      oV var1 = this.q();

      while (var1 != null && !(var1 instanceof Xm)) {
         var1 = var1.q();
      }

      return var1 != null && var1 instanceof Xm ? (Xm)var1 : null;
   }

   @Nullable
   public Wo JY() {
      oV var1 = this.q();

      while (var1 != null && !(var1 instanceof Wo)) {
         var1 = var1.q();
      }

      return var1 != null && var1 instanceof Wo ? (Wo)var1 : null;
   }

   @Override
   protected oV.eo RF() {
      return oV.eo.zz;
   }

   private int HF() {
      return this.gP * 4;
   }

   private int RF(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      int var4 = 0;
      if ((this.nf & 1) != 0) {
         for (Entry var6 : this.zz.entrySet()) {
            byte[] var7 = ((PB.eo)var6.getValue()).q(var3);
            var1.write(var7);
            var2.putShort((short)((char)((Integer)var6.getKey()).intValue()));
            var2.putShort((short)(var4 / 4));
            var4 += var7.length;
            if (var4 % 4 != 0) {
               throw new IllegalStateException();
            }
         }
      } else {
         for (int var8 = 0; var8 < this.gP; var8++) {
            PB.eo var9 = (PB.eo)this.zz.get(var8);
            if (var9 == null) {
               var2.putInt(-1);
            } else {
               byte[] var10 = var9.q(var3);
               var1.write(var10);
               var2.putInt(var4);
               var4 += var10.length;
            }
         }
      }

      return q(var1, var4);
   }

   @Override
   protected void xK(ByteBuffer var1) {
      int var2 = this.Dw() + this.HF();
      var1.putInt(this.gO);
      var1.putInt(this.gP);
      var1.putInt(var2);
      var1.put(this.lm.q(false));
   }

   @Override
   protected void q(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      ByteArrayOutputStream var4 = new ByteArrayOutputStream();
      ByteBuffer var5 = ByteBuffer.allocate(this.HF()).order(ByteOrder.LITTLE_ENDIAN);
      LittleEndianDataOutputStream var6 = new LittleEndianDataOutputStream(var4);

      try {
         this.RF(var6, var5, var3);
      } catch (Throwable var10) {
         try {
            var6.close();
         } catch (Throwable var9) {
            var10.addSuppressed(var9);
         }

         throw var10;
      }

      var6.close();
      var1.write(var5.array());
      var1.write(var4.toByteArray());
   }

   @Xv
   public abstract static class eo implements vv {
      public static final int q = -1;
      private static final int RF = 1;
      private static final int xK = 12;

      public abstract int q();

      public abstract int RF();

      public abstract int xK();

      @Nullable
      public abstract Gn Dw();

      public abstract Map Uv();

      public abstract int gO();

      public abstract PB nf();

      public final String gP() {
         return this.nf().gP();
      }

      public final int za() {
         return this.q() + (this.zz() ? this.Uv().size() * 12 : 8);
      }

      public final String lm() {
         return this.nf().xK(this.xK());
      }

      public final boolean zz() {
         return (this.RF() & 1) != 0;
      }

      @Nullable
      public static PB.eo q(ByteBuffer var0, int var1, PB var2) {
         int var3 = var0.getInt();
         if (var3 == -1) {
            return null;
         } else {
            int var4 = var0.position();
            var0.position(var1 + var3);
            PB.eo var5 = q(var0, var2);
            var0.position(var4);
            return var5;
         }
      }

      @Nullable
      private static PB.eo q(ByteBuffer var0, PB var1) {
         int var2 = var0.getShort() & '\uffff';
         int var3 = var0.getShort() & '\uffff';
         int var4 = var0.getInt();
         Gn var5 = null;
         LinkedHashMap var6 = new LinkedHashMap();
         int var7 = 0;
         if ((var3 & 1) != 0) {
            var7 = var0.getInt();
            int var8 = var0.getInt();

            for (int var9 = 0; var9 < var8; var9++) {
               var6.put(var0.getInt(), Gn.q(var0));
            }
         } else {
            var5 = Gn.q(var0);
         }

         return new PB.eo.eo(var2, var3, var4, var5, var6, var7, var1);
      }

      public static Dg JY() {
         return new VU();
      }

      @Override
      public final byte[] oW() {
         return this.q(false);
      }

      @Override
      public final byte[] q(boolean var1) {
         ByteBuffer var2 = ByteBuffer.allocate(this.za());
         var2.order(ByteOrder.LITTLE_ENDIAN);
         var2.putShort((short)this.q());
         short var3 = 255;
         var2.putShort((short)(var3 & this.RF()));
         var2.putInt(this.xK());
         if (this.zz()) {
            var2.putInt(this.gO());
            var2.putInt(this.Uv().size());

            for (Entry var5 : this.Uv().entrySet()) {
               var2.putInt((Integer)var5.getKey());
               var2.put(((Gn)var5.getValue()).q(var1));
            }
         } else {
            Gn var6 = this.Dw();
            Preconditions.checkNotNull(var6);
            var2.put(var6.oW());
         }

         return var2.array();
      }

      static class eo extends PB.eo {
         int RF;
         int xK;
         int Dw;
         Gn Uv;
         Map oW;
         int gO;
         PB nf;

         public eo(int var1, int var2, int var3, Gn var4, Map var5, int var6, PB var7) {
            this.RF = var1;
            this.xK = var2;
            this.Dw = var3;
            this.Uv = var4;
            this.oW = var5;
            this.gO = var6;
            this.nf = var7;
         }

         @Override
         public int q() {
            return this.RF;
         }

         @Override
         public int RF() {
            return this.xK;
         }

         @Override
         public int xK() {
            return this.Dw;
         }

         @Override
         public Gn Dw() {
            return this.Uv;
         }

         @Override
         public Map Uv() {
            return this.oW;
         }

         @Override
         public int gO() {
            return this.gO;
         }

         @Override
         public PB nf() {
            return this.nf;
         }
      }
   }
}
