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

public class pV extends oV {
   protected static final int gO = 1;
   protected static final int nf = 256;
   protected static final int gP = 24;
   protected int za;
   protected int lm;
   protected int zz;
   protected int JY;
   protected int HF;
   protected List LK = new ArrayList();
   protected List io = new ArrayList();
   protected boolean qa = false;

   protected pV(ByteBuffer var1, @Nullable oV var2) {
      super(var1, var2);
      this.JY = var1.getInt();
      this.HF = var1.getInt();
      this.za = var1.getInt();
      this.lm = var1.getInt();
      this.zz = var1.getInt();
   }

   @Override
   protected void q(ByteBuffer var1) {
      super.q(var1);
      this.LK.addAll(this.q(var1, this.Uv + this.lm, this.JY));
      this.io.addAll(this.RF(var1, this.Uv + this.zz, this.HF));
   }

   public int q(String var1) {
      return this.LK.indexOf(var1);
   }

   public String RF(int var1) {
      return (String)this.LK.get(var1);
   }

   public int gO() {
      return this.LK.size();
   }

   public pV.CU xK(int var1) {
      return (pV.CU)this.io.get(var1);
   }

   public int nf() {
      return this.io.size();
   }

   public Ot.eo gP() {
      return this.za() ? Ot.eo.q : Ot.eo.RF;
   }

   @Override
   protected oV.eo RF() {
      return oV.eo.RF;
   }

   private int zz() {
      return (this.LK.size() + this.io.size()) * 4;
   }

   public boolean za() {
      return (this.za & 256) != 0;
   }

   public boolean lm() {
      return (this.za & 1) != 0;
   }

   private List q(ByteBuffer var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      int var5 = -1;

      for (int var6 = 0; var6 < var3; var6++) {
         int var7 = var2 + var1.getInt();
         var4.add(Ot.q(var1, var7, this.gP()));
         if (var7 <= var5) {
            this.qa = true;
         }

         var5 = var7;
      }

      return var4;
   }

   private List RF(ByteBuffer var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();

      for (int var5 = 0; var5 < var3; var5++) {
         int var6 = var2 + var1.getInt();
         var4.add(pV.CU.q(var1, var6, this));
      }

      return var4;
   }

   protected int RF(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      int var4 = 0;
      HashMap var5 = new HashMap();

      for (String var7 : this.LK) {
         if (var5.containsKey(var7) && (var3 || this.qa)) {
            Integer var9 = (Integer)var5.get(var7);
            var2.putInt(var9 == null ? 0 : var9);
         } else {
            byte[] var8 = Ot.q(var7, this.gP());
            var1.write(var8);
            var5.put(var7, var4);
            var2.putInt(var4);
            var4 += var8.length;
         }
      }

      return q(var1, var4);
   }

   private int xK(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      int var4 = 0;
      if (this.io.size() > 0) {
         HashMap var5 = new HashMap();

         for (pV.CU var7 : this.io) {
            if (var5.containsKey(var7) && var3) {
               Integer var11 = (Integer)var5.get(var7);
               var2.putInt(var11 == null ? 0 : var11);
            } else {
               byte[] var8 = var7.q(var3);
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
         var4 = q(var1, var4);
      }

      return var4;
   }

   @Override
   protected void xK(ByteBuffer var1) {
      int var2 = this.Dw() + this.zz();
      var1.putInt(this.LK.size());
      var1.putInt(this.io.size());
      var1.putInt(this.za);
      var1.putInt(this.LK.isEmpty() ? 0 : var2);
      var1.putInt(0);
   }

   @Override
   protected void q(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      ByteArrayOutputStream var4 = new ByteArrayOutputStream();
      ByteBuffer var6 = ByteBuffer.allocate(this.zz());
      var6.order(ByteOrder.LITTLE_ENDIAN);
      LittleEndianDataOutputStream var7 = new LittleEndianDataOutputStream(var4);

      int var5;
      try {
         var5 = this.RF(var7, var6, var3);
         this.xK(var7, var6, var3);
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
      if (!this.io.isEmpty()) {
         var2.putInt(24, this.Dw() + this.zz() + var5);
      }
   }

   @Xv
   protected abstract static class CU implements vv {
      static final int q = -1;

      public abstract List q();

      static pV.CU q(ByteBuffer var0, int var1, pV var2) {
         Builder var3 = ImmutableList.builder();

         for (int var4 = var0.getInt(var1); var4 != -1; var4 = var0.getInt(var1)) {
            var3.add(pV.eo.q(var0, var1, var2));
            var1 += 12;
         }

         return new pV.CU.eo(var3.build());
      }

      @Override
      public byte[] oW() throws IOException {
         return this.q(false);
      }

      @Override
      public byte[] q(boolean var1) throws IOException {
         ByteArrayOutputStream var2 = new ByteArrayOutputStream();
         LittleEndianDataOutputStream var3 = new LittleEndianDataOutputStream(var2);

         try {
            for (pV.eo var5 : this.q()) {
               byte[] var6 = var5.q(var1);
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

      static pV.CU q(List var0) {
         return new pV.CU.eo(var0);
      }

      static class eo extends pV.CU {
         List RF;

         public eo(List var1) {
            this.RF = var1;
         }

         @Override
         public List q() {
            return this.RF;
         }
      }
   }

   @Xv
   protected abstract static class eo implements vv {
      static final int q = 12;

      public abstract int q();

      public abstract int RF();

      public abstract int xK();

      public abstract pV Dw();

      static pV.eo q(ByteBuffer var0, int var1, pV var2) {
         int var3 = var0.getInt(var1);
         int var4 = var0.getInt(var1 + 4);
         int var5 = var0.getInt(var1 + 8);
         return new pV.eo.eo(var3, var4, var5, var2);
      }

      @Override
      public final byte[] oW() {
         return this.q(false);
      }

      @Override
      public final byte[] q(boolean var1) {
         ByteBuffer var2 = ByteBuffer.allocate(12).order(ByteOrder.LITTLE_ENDIAN);
         var2.putInt(this.q());
         var2.putInt(this.RF());
         var2.putInt(this.xK());
         return var2.array();
      }

      static class eo extends pV.eo {
         int RF;
         int xK;
         int Dw;
         pV Uv;

         public eo(int var1, int var2, int var3, pV var4) {
            this.RF = var1;
            this.xK = var2;
            this.Dw = var3;
            this.Uv = var4;
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
         public pV Dw() {
            return this.Uv;
         }
      }
   }
}
