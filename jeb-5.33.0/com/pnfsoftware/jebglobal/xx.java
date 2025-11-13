package com.pnfsoftware.jebglobal;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.google.common.io.LittleEndianDataOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Nullable;

public class xx extends Ij {
   protected int UT;
   protected int E;
   protected int sY;
   protected int ys;
   protected int ld;
   protected List gp = new ArrayList();
   protected List oT = new ArrayList();
   protected boolean fI = false;

   protected xx(ByteBuffer var1, @Nullable Ij var2) {
      super(var1, var2);
      this.ys = var1.getInt();
      this.ld = var1.getInt();
      this.UT = var1.getInt();
      this.E = var1.getInt();
      this.sY = var1.getInt();
   }

   @Override
   protected void pC(ByteBuffer var1) {
      super.pC(var1);
      this.gp.addAll(this.pC(var1, this.kS + this.E, this.ys));
      this.oT.addAll(this.A(var1, this.kS + this.sY, this.ld));
   }

   public String A(int var1) {
      return (String)this.gp.get(var1);
   }

   public bU.Av UT() {
      return this.E() ? bU.Av.pC : bU.Av.A;
   }

   @Override
   protected Ij.Av A() {
      return Ij.Av.A;
   }

   private int sY() {
      return (this.gp.size() + this.oT.size()) * 4;
   }

   public boolean E() {
      return (this.UT & 256) != 0;
   }

   private List pC(ByteBuffer var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      int var5 = -1;

      for (int var6 = 0; var6 < var3; var6++) {
         int var7 = var2 + var1.getInt();
         var4.add(bU.pC(var1, var7, this.UT()));
         if (var7 <= var5) {
            this.fI = true;
         }

         var5 = var7;
      }

      return var4;
   }

   private List A(ByteBuffer var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();

      for (int var5 = 0; var5 < var3; var5++) {
         int var6 = var2 + var1.getInt();
         var4.add(xx.Sv.pC(var1, var6, this));
      }

      return var4;
   }

   protected int A(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      int var4 = 0;
      HashMap var5 = new HashMap();

      for (String var7 : this.gp) {
         if (var5.containsKey(var7) && (var3 || this.fI)) {
            Integer var9 = (Integer)var5.get(var7);
            var2.putInt(var9 == null ? 0 : var9);
         } else {
            byte[] var8 = bU.pC(var7, this.UT());
            var1.write(var8);
            var5.put(var7, var4);
            var2.putInt(var4);
            var4 += var8.length;
         }
      }

      return pC(var1, var4);
   }

   private int kS(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      int var4 = 0;
      if (this.oT.size() > 0) {
         HashMap var5 = new HashMap();

         for (xx.Sv var7 : this.oT) {
            if (var5.containsKey(var7) && var3) {
               Integer var11 = (Integer)var5.get(var7);
               var2.putInt(var11 == null ? 0 : var11);
            } else {
               byte[] var8 = var7.pC(var3);
               var1.write(var8);
               var5.put(var7, var4);
               var2.putInt(var4);
               var4 += var8.length;
            }
         }

         var1.writeInt(-1);
         var4 += 4;
         var1.writeInt(-1);
         var4 += 4;
         var4 = pC(var1, var4);
      }

      return var4;
   }

   @Override
   protected void kS(ByteBuffer var1) {
      int var2 = this.kS() + this.sY();
      var1.putInt(this.gp.size());
      var1.putInt(this.oT.size());
      var1.putInt(this.UT);
      var1.putInt(this.gp.isEmpty() ? 0 : var2);
      var1.putInt(0);
   }

   @Override
   protected void pC(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      ByteArrayOutputStream var4 = new ByteArrayOutputStream();
      ByteBuffer var6 = ByteBuffer.allocate(this.sY());
      var6.order(ByteOrder.LITTLE_ENDIAN);
      LittleEndianDataOutputStream var7 = new LittleEndianDataOutputStream(var4);

      int var5;
      try {
         var5 = this.A(var7, var6, var3);
         this.kS(var7, var6, var3);
      } catch (Throwable var11) {
         try {
            var7.close();
         } catch (Throwable var10) {
            var11.addSuppressed(var10);
         }

         throw var11;
      }

      var7.close();
      var1.write(var6.array());
      var1.write(var4.toByteArray());
      if (!this.oT.isEmpty()) {
         var2.putInt(24, this.kS() + this.sY() + var5);
      }
   }

   protected abstract static class Av {
      public abstract int pC();

      public abstract int A();

      public abstract int kS();

      public abstract xx wS();

      static xx.Av pC(ByteBuffer var0, int var1, xx var2) {
         int var3 = var0.getInt(var1);
         int var4 = var0.getInt(var1 + 4);
         int var5 = var0.getInt(var1 + 8);
         return new xx.Av.Av(var3, var4, var5, var2);
      }

      public final byte[] pC(boolean var1) {
         ByteBuffer var2 = ByteBuffer.allocate(12).order(ByteOrder.LITTLE_ENDIAN);
         var2.putInt(this.pC());
         var2.putInt(this.A());
         var2.putInt(this.kS());
         return var2.array();
      }

      static class Av extends xx.Av {
         int pC;
         int A;
         int kS;
         xx wS;

         public Av(int var1, int var2, int var3, xx var4) {
            this.pC = var1;
            this.A = var2;
            this.kS = var3;
            this.wS = var4;
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
         public xx wS() {
            return this.wS;
         }
      }
   }

   protected abstract static class Sv {
      public abstract List pC();

      static xx.Sv pC(ByteBuffer var0, int var1, xx var2) {
         Builder var3 = ImmutableList.builder();

         for (int var4 = var0.getInt(var1); var4 != -1; var4 = var0.getInt(var1)) {
            var3.add(xx.Av.pC(var0, var1, var2));
            var1 += 12;
         }

         return new xx.Sv.Av(var3.build());
      }

      public byte[] A() throws IOException {
         return this.pC(false);
      }

      public byte[] pC(boolean var1) throws IOException {
         ByteArrayOutputStream var2 = new ByteArrayOutputStream();
         LittleEndianDataOutputStream var3 = new LittleEndianDataOutputStream(var2);

         try {
            for (xx.Av var5 : this.pC()) {
               byte[] var6 = var5.pC(var1);
               if (var6.length != 12) {
                  throw new IllegalStateException();
               }

               var3.write(var6);
            }

            var3.writeInt(-1);
         } catch (Throwable var8) {
            try {
               var3.close();
            } catch (Throwable var7) {
               var8.addSuppressed(var7);
            }

            throw var8;
         }

         var3.close();
         return var2.toByteArray();
      }

      static xx.Sv pC(List var0) {
         return new xx.Sv.Av(var0);
      }

      static class Av extends xx.Sv {
         List pC;

         public Av(List var1) {
            this.pC = var1;
         }

         @Override
         public List pC() {
            return this.pC;
         }
      }
   }
}
