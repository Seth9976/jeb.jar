package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel.MapMode;
import java.util.Arrays;
import java.util.List;

public class bai {
   private static final ILogger fI = GlobalLog.getLogger(bai.class);
   ByteBuffer pC;
   int A;
   int kS;
   azq wS;
   int UT;
   int E;
   int sY;
   int[] ys;
   bal[] ld;
   ban[] gp;
   azp[] oT;

   public bai(File var1) throws IOException {
      RandomAccessFile var2 = new RandomAccessFile(var1, "r");
      MappedByteBuffer var3 = var2.getChannel().map(MapMode.READ_ONLY, 0L, var2.length());
      this.pC = var3;
      this.fI();
   }

   public bai(ByteBuffer var1) {
      this.pC = var1;
      this.fI();
   }

   private void fI() {
      if (this.pC.getInt() != -889275714) {
         throw new RuntimeException();
      } else {
         this.A = this.pC.getShort() & '\uffff';
         this.kS = this.pC.getShort() & '\uffff';
         int var1 = this.pC.getShort() & '\uffff';
         this.wS = azq.pC(this.pC, var1 - 1);
         this.UT = this.pC.getShort() & '\uffff';
         this.E = this.pC.getShort() & '\uffff';
         this.sY = this.pC.getShort() & '\uffff';
         int var2 = this.pC.getShort() & '\uffff';
         this.ys = new int[var2];

         for (int var3 = 0; var3 < var2; var3++) {
            this.ys[var3] = this.pC.getShort() & '\uffff';
         }

         int var7 = this.pC.getShort() & '\uffff';
         this.ld = new bal[var7];

         for (int var4 = 0; var4 < var7; var4++) {
            this.ld[var4] = bal.pC(this.pC);
         }

         int var8 = this.pC.getShort() & '\uffff';
         this.gp = new ban[var8];

         for (int var5 = 0; var5 < var8; var5++) {
            this.gp[var5] = ban.pC(this.pC);
         }

         int var9 = this.pC.getShort() & '\uffff';
         this.oT = new azp[var9];

         for (int var6 = 0; var6 < var9; var6++) {
            this.oT[var6] = azp.pC(this.pC);
         }

         int var10 = this.pC.remaining();
         if (var10 != 0) {
            throw new RuntimeException("Illegal appended data bytes: " + var10);
         }
      }
   }

   public Version pC() {
      return Version.create(this.kS, this.A);
   }

   public azq A() {
      return this.wS;
   }

   public azs pC(int var1) {
      return this.wS.pC(var1);
   }

   public int kS() {
      return this.UT;
   }

   public int wS() {
      return this.E;
   }

   public int UT() {
      return this.sY;
   }

   public int[] E() {
      return this.ys;
   }

   public List sY() {
      return Arrays.asList(this.ld);
   }

   public List ys() {
      return Arrays.asList(this.gp);
   }

   @Override
   public String toString() {
      return Strings.ff("Classfile version %s, pool:%d, flags=%s", this.pC().formatCompact(), this.A().pC(), bam.pC(this.kS()));
   }

   public String ld() {
      return bak.pC(this);
   }

   public String gp() {
      return bak.A(this);
   }

   public List oT() {
      return bak.kS(this);
   }
}
