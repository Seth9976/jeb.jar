package com.pnfsoftware.jebglobal;

import com.google.common.base.Preconditions;
import com.google.common.io.LittleEndianDataOutputStream;
import com.google.common.primitives.UnsignedBytes;
import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import javax.annotation.Nullable;

public class MS extends Ij {
   protected int UT;
   protected int E;
   protected int sY;
   protected int ys;
   protected Qv ld;
   protected Map gp = new TreeMap();

   protected MS(ByteBuffer var1, @Nullable Ij var2) {
      super(var1, var2);
      this.UT = UnsignedBytes.toInt(var1.get());
      this.E = UnsignedBytes.toInt(var1.get());
      var1.position(var1.position() + 2);
      this.sY = var1.getInt();
      this.ys = var1.getInt();
      this.ld = Qv.pC(var1);
   }

   @Override
   protected void pC(ByteBuffer var1) {
      int var2 = this.kS + this.ys;
      if ((this.E & 1) == 0) {
         for (int var3 = 0; var3 < this.sY; var3++) {
            MS.Av var4 = MS.Av.pC(var1, var2, this);
            if (var4 != null) {
               this.gp.put(var3, var4);
            }
         }
      } else {
         for (int var5 = 0; var5 < this.sY; var5++) {
            this.gp.put(Integer.valueOf((char)var1.getShort()), MS.Av.pC(var1, (char)var1.getShort() * 4 + var2, this));
         }
      }
   }

   public int E() {
      return this.UT;
   }

   public String sY() {
      IE var1 = this.ys();
      Preconditions.checkNotNull(var1);
      xx var2 = var1.E();
      Preconditions.checkNotNull(var2);
      return var2.A(this.E() - 1);
   }

   @Nullable
   public IE ys() {
      Ij var1 = this.pC();

      while (var1 != null && !(var1 instanceof IE)) {
         var1 = var1.pC();
      }

      return var1 != null && var1 instanceof IE ? (IE)var1 : null;
   }

   @Override
   protected Ij.Av A() {
      return Ij.Av.fI;
   }

   private int UT() {
      return this.sY * 4;
   }

   private int A(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      int var4 = 0;
      if ((this.E & 1) != 0) {
         for (Entry var6 : this.gp.entrySet()) {
            byte[] var7 = ((MS.Av)var6.getValue()).pC(var3);
            var1.write(var7);
            var2.putShort((short)((char)((Integer)var6.getKey()).intValue()));
            var2.putShort((short)(var4 / 4));
            var4 += var7.length;
            if (var4 % 4 != 0) {
               throw new IllegalStateException();
            }
         }
      } else {
         for (int var8 = 0; var8 < this.sY; var8++) {
            MS.Av var9 = (MS.Av)this.gp.get(var8);
            if (var9 == null) {
               var2.putInt(-1);
            } else {
               byte[] var10 = var9.pC(var3);
               var1.write(var10);
               var2.putInt(var4);
               var4 += var10.length;
            }
         }
      }

      return pC(var1, var4);
   }

   @Override
   protected void kS(ByteBuffer var1) {
      int var2 = this.kS() + this.UT();
      var1.putInt(this.UT);
      var1.putInt(this.sY);
      var1.putInt(var2);
      var1.put(this.ld.pC(false));
   }

   @Override
   protected void pC(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      ByteArrayOutputStream var4 = new ByteArrayOutputStream();
      ByteBuffer var5 = ByteBuffer.allocate(this.UT()).order(ByteOrder.LITTLE_ENDIAN);
      LittleEndianDataOutputStream var6 = new LittleEndianDataOutputStream(var4);

      try {
         this.A(var6, var5, var3);
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

   public abstract static class Av {
      public abstract int pC();

      public abstract int A();

      public abstract int kS();

      @Nullable
      public abstract wR wS();

      public abstract Map UT();

      public abstract int E();

      public final int sY() {
         return this.pC() + (this.ys() ? this.UT().size() * 12 : 8);
      }

      public final boolean ys() {
         return (this.A() & 1) != 0;
      }

      @Nullable
      public static MS.Av pC(ByteBuffer var0, int var1, MS var2) {
         int var3 = var0.getInt();
         if (var3 == -1) {
            return null;
         } else {
            int var4 = var0.position();
            var0.position(var1 + var3);
            MS.Av var5 = pC(var0, var2);
            var0.position(var4);
            return var5;
         }
      }

      @Nullable
      private static MS.Av pC(ByteBuffer var0, MS var1) {
         int var2 = var0.getShort() & '\uffff';
         int var3 = var0.getShort() & '\uffff';
         int var4 = var0.getInt();
         wR var5 = null;
         LinkedHashMap var6 = new LinkedHashMap();
         int var7 = 0;
         if ((var3 & 1) != 0) {
            var7 = var0.getInt();
            int var8 = var0.getInt();

            for (int var9 = 0; var9 < var8; var9++) {
               var6.put(var0.getInt(), wR.pC(var0));
            }
         } else {
            var5 = wR.pC(var0);
         }

         return new MS.Av.Av(var2, var3, var4, var5, var6, var7, var1);
      }

      public static wv ld() {
         return new vy();
      }

      public final byte[] pC(boolean var1) {
         ByteBuffer var2 = ByteBuffer.allocate(this.sY());
         var2.order(ByteOrder.LITTLE_ENDIAN);
         var2.putShort((short)this.pC());
         short var3 = 255;
         var2.putShort((short)(var3 & this.A()));
         var2.putInt(this.kS());
         if (this.ys()) {
            var2.putInt(this.E());
            var2.putInt(this.UT().size());

            for (Entry var5 : this.UT().entrySet()) {
               var2.putInt((Integer)var5.getKey());
               var2.put(((wR)var5.getValue()).pC(var1));
            }
         } else {
            wR var6 = this.wS();
            Preconditions.checkNotNull(var6);
            var2.put(var6.wS());
         }

         return var2.array();
      }

      static class Av extends MS.Av {
         int pC;
         int A;
         int kS;
         wR wS;
         Map UT;
         int E;
         MS sY;

         public Av(int var1, int var2, int var3, wR var4, Map var5, int var6, MS var7) {
            this.pC = var1;
            this.A = var2;
            this.kS = var3;
            this.wS = var4;
            this.UT = var5;
            this.E = var6;
            this.sY = var7;
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
         public wR wS() {
            return this.wS;
         }

         @Override
         public Map UT() {
            return this.UT;
         }

         @Override
         public int E() {
            return this.E;
         }
      }
   }
}
