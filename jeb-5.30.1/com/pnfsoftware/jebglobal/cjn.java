package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.IRuntimeProject;
import com.pnfsoftware.jeb.core.dao.IFileStore;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.LEDataInputStream;
import com.pnfsoftware.jeb.util.io.LEDataOutputStream;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class cjn {
   private static final ILogger LK = GlobalLog.getLogger(cjn.class);
   public static final int q = 1229209921;
   static final int RF = 1;
   static final int xK = 32;
   public static final int Dw = 1;
   public static final int Uv = 2;
   public static final int oW = 3;
   public static final int gO = 4;
   public static final int nf = 5;
   public static final int gP = 6;
   public static final int za = 7;
   public static final int lm = 8;
   public static final int zz = 9;
   public static final int JY = 10;
   static final int HF = 1;
   private static final int io = 4096;
   private static final int qa = 512;
   private File Hk;
   private List Me = new ArrayList();

   private static void q(String var0, Object... var1) {
      System.out.format(var0 + "\n", var1);
   }

   private static int Dw(int var0) {
      return (var0 + 511) / 512 * 512;
   }

   int q(File var1, int var2, List var3, LEDataOutputStream var4, int var5, int var6, long var7) throws IOException {
      int var9 = (int)var1.length();
      int var10 = Dw(var9);
      cjn.CU var11 = new cjn.CU(var5, var6, var2, var9, var7);
      var3.add(var11);
      byte[] var12 = com.pnfsoftware.jeb.util.io.IO.readFile(var1);
      var4.write((var6 & 1) != 0 ? q(var12) : var12);
      var4.write(new byte[var10 - var9]);
      return var2 + var10;
   }

   void q(File var1, File var2) throws IOException {
      ByteArrayOutputStream var3 = new ByteArrayOutputStream();
      LEDataOutputStream var4 = new LEDataOutputStream(var3);
      ByteArrayOutputStream var5 = new ByteArrayOutputStream();
      LEDataOutputStream var6 = new LEDataOutputStream(var5);
      ArrayList var7 = new ArrayList();
      int var8 = 0;
      boolean var9 = true;
      byte var10 = 0;
      if (var9) {
         var10 |= 1;
      }

      var8 = this.q(new File(var1, "process_maps_filtered"), var8, var7, var6, 2, var10, 0L);
      var8 = this.q(new File(var1, "process_status"), var8, var7, var6, 1, var10, 0L);
      var8 = this.q(new File(var1, "registers"), var8, var7, var6, 4, var10, 0L);
      var8 = this.q(new File(var1, "properties"), var8, var7, var6, 5, var10, 0L);
      var8 = this.q(new File(var1, "system_properties"), var8, var7, var6, 6, var10, 0L);
      var8 = this.q(new File(var1, "app_process64"), var8, var7, var6, 7, var10, 0L);
      var8 = this.q(new File(var1, "cpuinfo"), var8, var7, var6, 9, var10, 0L);
      var8 = this.q(new File(var1, "libc.so"), var8, var7, var6, 8, var10, 0L);
      var8 = this.q(new File(var1, "libc++.so"), var8, var7, var6, 10, var10, 0L);
      File var11 = new File(var1, "memdump");

      for (File var13 : com.pnfsoftware.jeb.util.io.IO.listFiles(var11)) {
         String var14 = var13.getName();
         if (var14.endsWith(".bin")) {
            long var15 = Long.parseLong(var14.substring(0, var14.length() - 4), 16);
            Assert.a(var15 % 4096L == 0L);
            int var17 = (int)var13.length();
            Assert.a(var17 % 4096 == 0);
            var8 = this.q(var13, var8, var7, var6, 3, var10, var15);
         }
      }

      var6.close();
      int var29 = 32 + var7.size() * 32;
      int var30 = Dw(var29);
      var7.forEach(var1x -> var1x.xK += var30);
      cjn.eo var31 = new cjn.eo(var7.size());
      var31.q(var4);

      for (cjn.CU var16 : var7) {
         var16.q(var4);
      }

      var4.write(new byte[var30 - var29]);
      var4.flush();
      var4.close();

      try (FileOutputStream var33 = new FileOutputStream(var2)) {
         var33.write(var3.toByteArray());
         var33.write(var5.toByteArray());
      }
   }

   static byte[] q(byte[] var0, int var1, int var2) {
      for (int var3 = var1; var3 < var1 + var2; var3++) {
         var0[var3] = (byte)(var0[var3] ^ 166);
      }

      return var0;
   }

   static byte[] q(byte[] var0) {
      return q(var0, 0, var0.length);
   }

   private cjn() {
   }

   public cjn(File var1) throws IOException {
      if (var1 == null) {
         throw new IllegalArgumentException("No disk file provided");
      } else {
         this.Hk = var1;

         try (
            FileInputStream var2 = new FileInputStream(var1);
            LEDataInputStream var3 = new LEDataInputStream(var2);
         ) {
            cjn.eo var4 = cjn.eo.q(var3);
            int var5 = var4.Dw;

            for (int var6 = 0; var6 < var5; var6++) {
               cjn.CU var7 = cjn.CU.q(var3);
               this.Me.add(var7);
            }
         }
      }
   }

   public List q() {
      return Collections.unmodifiableList(this.Me);
   }

   public List q(int var1) {
      return (List)this.Me.stream().filter(var1x -> var1x.q == var1).collect(Collectors.toList());
   }

   public cjn.CU RF(int var1) {
      Optional var2 = this.Me.stream().filter(var1x -> var1x.q == var1).findFirst();
      return var2.isPresent() ? (cjn.CU)var2.get() : null;
   }

   public int q(cjn.CU var1, int var2, int var3, byte[] var4, int var5) throws IOException {
      int var10;
      try (RandomAccessFile var6 = new RandomAccessFile(this.Hk, "r")) {
         long var7 = var1.xK + var2;
         var6.seek(var7);
         int var9 = var6.read(var4, var5, var3);
         if ((var1.RF & 1) != 0) {
            q(var4, var5, var9);
         }

         var10 = var9;
      }

      return var10;
   }

   public byte[] q(cjn.CU var1) throws IOException {
      int var2 = var1.Dw;
      byte[] var3 = new byte[var2];
      int var4 = this.q(var1, 0, var2, var3, 0);
      if (var4 != var2) {
         throw new IOException("Unexpected file size");
      } else {
         return var3;
      }
   }

   public byte[] xK(int var1) throws IOException {
      cjn.CU var2 = this.RF(var1);
      if (var2 == null) {
         throw new IOException("File not found");
      } else {
         return this.q(var2);
      }
   }

   @Override
   public String toString() {
      return Strings.ff("AADJ [%s] [%d entries]", this.Hk, this.Me.size());
   }

   public static cjn q(IRuntimeProject var0) {
      IFileStore var1 = var0.getEnginesContext().getDataProvider().getPluginStore();
      if (var1 != null && var1.getStoreLocation() != null) {
         String var2 = com.pnfsoftware.jeb.util.io.IO.abs(var1.getStoreLocation());
         File var3 = new File(var2, "aadi.bin");
         if (var3 != null) {
            try {
               return new cjn(var3);
            } catch (IOException var5) {
               LK.catching(var5);
            }
         }
      }

      return null;
   }

   static {
      Assert.a(true);
   }

   static class CU {
      int q;
      int RF;
      int xK;
      int Dw;
      long Uv;

      static cjn.CU q(LEDataInputStream var0) throws IOException {
         cjn.CU var1 = new cjn.CU();
         var1.q = var0.readInt();
         var1.RF = var0.readInt();
         var1.xK = var0.readInt();
         var1.Dw = var0.readInt();
         var1.Uv = var0.readLong();
         var0.readLong();
         return var1;
      }

      private CU() {
      }

      CU(int var1, int var2, int var3, int var4, long var5) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
         this.Dw = var4;
         this.Uv = var5;
      }

      void q(LEDataOutputStream var1) throws IOException {
         var1.writeInt(this.q);
         var1.writeInt(this.RF);
         var1.writeInt(this.xK);
         var1.writeInt(this.Dw);
         var1.writeLong(this.Uv);
         var1.writeLong(0L);
      }
   }

   static class eo {
      int q;
      int RF;
      int xK;
      int Dw;

      static cjn.eo q(LEDataInputStream var0) throws IOException {
         cjn.eo var1 = new cjn.eo();
         var1.q = var0.readInt();
         if (var1.q != 1229209921) {
            throw new IOException("Unexpected magic");
         } else {
            var1.RF = var0.readInt();
            if (var1.RF != 1) {
               throw new IOException("Unsupported version: " + var1.RF);
            } else {
               var1.xK = var0.readInt();
               if (var1.xK != 32) {
                  throw new IOException("Unsupported header size: " + var1.xK);
               } else {
                  var1.Dw = var0.readInt();
                  if (var1.Dw < 0) {
                     throw new IOException("Illegal entry count: " + var1.Dw);
                  } else {
                     var0.readLong();
                     var0.readLong();
                     return var1;
                  }
               }
            }
         }
      }

      private eo() {
      }

      eo(int var1) {
         this.q = 1229209921;
         this.RF = 1;
         this.xK = 32;
         this.Dw = var1;
      }

      void q(LEDataOutputStream var1) throws IOException {
         var1.writeInt(this.q);
         var1.writeInt(this.RF);
         var1.writeInt(this.xK);
         var1.writeInt(this.Dw);
         var1.writeLong(0L);
         var1.writeLong(0L);
      }
   }
}
