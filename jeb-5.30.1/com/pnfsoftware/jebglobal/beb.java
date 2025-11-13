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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class beb {
   private static final ILogger JY = GlobalLog.getLogger(beb.class);
   public static final int q = -889275714;
   ByteBuffer RF;
   int xK;
   int Dw;
   bdi Uv;
   int oW;
   int gO;
   int nf;
   int[] gP;
   bee[] za;
   beg[] lm;
   bdh[] zz;

   public beb(File var1) throws IOException {
      RandomAccessFile var2 = new RandomAccessFile(var1, "r");
      MappedByteBuffer var3 = var2.getChannel().map(MapMode.READ_ONLY, 0L, var2.length());
      this.RF = var3;
      this.LK();
   }

   public beb(ByteBuffer var1) {
      this.RF = var1;
      this.LK();
   }

   private void LK() {
      if (this.RF.getInt() != -889275714) {
         throw new RuntimeException();
      } else {
         this.xK = this.RF.getShort() & '\uffff';
         this.Dw = this.RF.getShort() & '\uffff';
         int var1 = this.RF.getShort() & '\uffff';
         this.Uv = bdi.q(this.RF, var1 - 1);
         this.oW = this.RF.getShort() & '\uffff';
         this.gO = this.RF.getShort() & '\uffff';
         this.nf = this.RF.getShort() & '\uffff';
         int var2 = this.RF.getShort() & '\uffff';
         this.gP = new int[var2];

         for (int var3 = 0; var3 < var2; var3++) {
            this.gP[var3] = this.RF.getShort() & '\uffff';
         }

         int var7 = this.RF.getShort() & '\uffff';
         this.za = new bee[var7];

         for (int var4 = 0; var4 < var7; var4++) {
            this.za[var4] = bee.q(this.RF);
         }

         int var8 = this.RF.getShort() & '\uffff';
         this.lm = new beg[var8];

         for (int var5 = 0; var5 < var8; var5++) {
            this.lm[var5] = beg.q(this.RF);
         }

         int var9 = this.RF.getShort() & '\uffff';
         this.zz = new bdh[var9];

         for (int var6 = 0; var6 < var9; var6++) {
            this.zz[var6] = bdh.q(this.RF);
         }

         int var10 = this.RF.remaining();
         if (var10 != 0) {
            throw new RuntimeException("Illegal appended data bytes: " + var10);
         }
      }
   }

   public Version q() {
      return Version.create(this.Dw, this.xK);
   }

   public bdi RF() {
      return this.Uv;
   }

   public String q(int var1) {
      return ((bdy)this.Uv.q(var1)).xK();
   }

   public bdk RF(int var1) {
      return this.Uv.q(var1);
   }

   public int xK() {
      return this.oW;
   }

   public int Dw() {
      return this.gO;
   }

   public int Uv() {
      return this.nf;
   }

   public int[] oW() {
      return this.gP;
   }

   public int gO() {
      return this.gP.length;
   }

   public List nf() {
      return Arrays.asList(this.za);
   }

   public bee xK(int var1) {
      return this.za[var1];
   }

   public List gP() {
      return Arrays.asList(this.lm);
   }

   public beg Dw(int var1) {
      return this.lm[var1];
   }

   public List za() {
      return Arrays.asList(this.zz);
   }

   public bdh Uv(int var1) {
      return this.zz[var1];
   }

   @Override
   public String toString() {
      return Strings.ff("Classfile version %s, pool:%d, flags=%s", this.q().formatCompact(), this.RF().q(), bef.q(this.xK()));
   }

   public String lm() {
      return bed.q(this);
   }

   public String zz() {
      return bed.RF(this);
   }

   public List JY() {
      return bed.xK(this);
   }

   public List HF() {
      ArrayList var1 = new ArrayList();
      var1.add(this.zz());
      var1.addAll(this.JY());
      return var1;
   }
}
