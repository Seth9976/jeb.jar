package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.IRuntimeProject;
import com.pnfsoftware.jeb.core.dao.IFileStore;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.io.LEDataInputStream;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class cdy {
   private static final ILogger pC = GlobalLog.getLogger(cdy.class);
   private File A;
   private List kS = new ArrayList();

   static byte[] pC(byte[] var0, int var1, int var2) {
      for (int var3 = var1; var3 < var1 + var2; var3++) {
         var0[var3] = (byte)(var0[var3] ^ 166);
      }

      return var0;
   }

   private cdy() {
   }

   public cdy(File var1) throws IOException {
      if (var1 == null) {
         throw new IllegalArgumentException("No disk file provided");
      } else {
         this.A = var1;

         try (
            FileInputStream var2 = new FileInputStream(var1);
            LEDataInputStream var3 = new LEDataInputStream(var2);
         ) {
            cdy.Av var4 = cdy.Av.pC(var3);
            int var5 = var4.wS;

            for (int var6 = 0; var6 < var5; var6++) {
               cdy.Sv var7 = cdy.Sv.pC(var3);
               this.kS.add(var7);
            }
         }
      }
   }

   public List pC(int var1) {
      return (List)this.kS.stream().filter(var1x -> var1x.pC == var1).collect(Collectors.toList());
   }

   public cdy.Sv A(int var1) {
      Optional var2 = this.kS.stream().filter(var1x -> var1x.pC == var1).findFirst();
      return var2.isPresent() ? (cdy.Sv)var2.get() : null;
   }

   public int pC(cdy.Sv var1, int var2, int var3, byte[] var4, int var5) throws IOException {
      int var10;
      try (RandomAccessFile var6 = new RandomAccessFile(this.A, "r")) {
         long var7 = var1.kS + var2;
         var6.seek(var7);
         int var9 = var6.read(var4, var5, var3);
         if ((var1.A & 1) != 0) {
            pC(var4, var5, var9);
         }

         var10 = var9;
      }

      return var10;
   }

   public byte[] pC(cdy.Sv var1) throws IOException {
      int var2 = var1.wS;
      byte[] var3 = new byte[var2];
      int var4 = this.pC(var1, 0, var2, var3, 0);
      if (var4 != var2) {
         throw new IOException("Unexpected file size");
      } else {
         return var3;
      }
   }

   public byte[] kS(int var1) throws IOException {
      cdy.Sv var2 = this.A(var1);
      if (var2 == null) {
         throw new IOException("File not found");
      } else {
         return this.pC(var2);
      }
   }

   @Override
   public String toString() {
      return Strings.ff("AADJ [%s] [%d entries]", this.A, this.kS.size());
   }

   public static cdy pC(IRuntimeProject var0) {
      IFileStore var1 = var0.getEnginesContext().getDataProvider().getPluginStore();
      if (var1 != null && var1.getStoreLocation() != null) {
         String var2 = IO.abs(var1.getStoreLocation());
         File var3 = new File(var2, "aadi.bin");
         if (var3 != null) {
            try {
               return new cdy(var3);
            } catch (IOException var5) {
               pC.catching(var5);
            }
         }
      }

      return null;
   }

   static {
      Assert.a(true);
   }

   static class Av {
      int pC;
      int A;
      int kS;
      int wS;

      static cdy.Av pC(LEDataInputStream var0) throws IOException {
         cdy.Av var1 = new cdy.Av();
         var1.pC = var0.readInt();
         if (var1.pC != 1229209921) {
            throw new IOException("Unexpected magic");
         } else {
            var1.A = var0.readInt();
            if (var1.A != 1) {
               throw new IOException("Unsupported version: " + var1.A);
            } else {
               var1.kS = var0.readInt();
               if (var1.kS != 32) {
                  throw new IOException("Unsupported header size: " + var1.kS);
               } else {
                  var1.wS = var0.readInt();
                  if (var1.wS < 0) {
                     throw new IOException("Illegal entry count: " + var1.wS);
                  } else {
                     var0.readLong();
                     var0.readLong();
                     return var1;
                  }
               }
            }
         }
      }

      private Av() {
      }
   }

   static class Sv {
      int pC;
      int A;
      int kS;
      int wS;
      long UT;

      static cdy.Sv pC(LEDataInputStream var0) throws IOException {
         cdy.Sv var1 = new cdy.Sv();
         var1.pC = var0.readInt();
         var1.A = var0.readInt();
         var1.kS = var0.readInt();
         var1.wS = var0.readInt();
         var1.UT = var0.readLong();
         var0.readLong();
         return var1;
      }

      private Sv() {
      }
   }
}
