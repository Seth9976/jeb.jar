package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.units.code.wincommon.Guid;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.ArraySeekableByteChannel;
import com.pnfsoftware.jeb.util.io.ByteBufferUtil;
import com.pnfsoftware.jeb.util.io.ChannelUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.List;

public class cia implements AutoCloseable {
   private static final ILogger E = GlobalLog.getLogger(cia.class, Integer.MAX_VALUE);
   private static final byte[] sY = Strings.encodeASCII("Microsoft C/C++ MSF 7.00\r\n\u001aDS\u0000\u0000\u0000");
   private SeekableByteChannel ys;
   private int ld;
   private int gp;
   private ByteBuffer oT;
   List pC = new ArrayList();
   int A;
   int kS;
   int wS;
   Guid UT;

   private int pC(ByteBuffer var1) {
      int var2 = var1.getInt();
      if (var2 < -1) {
         throw new RuntimeException("Illegal uint: " + Integer.toHexString(var2));
      } else {
         return var2;
      }
   }

   public cia(IInput var1) throws IOException {
      this.ys = var1.getChannel();
      ByteBuffer var2 = ChannelUtil.read(this.ys, 0L, 32, false);
      if (!ByteBufferUtil.equalsByteArray(var2, 0, sY, 0, 32)) {
         throw new IOException("Not a PDB version 7");
      } else {
         var2 = ChannelUtil.read(this.ys, 32L, 24, false, var2);
         this.ld = this.pC(var2);
         this.oT = ByteBuffer.allocate(this.ld);
         this.oT.order(ByteOrder.LITTLE_ENDIAN);
         int var3 = this.pC(var2);
         this.gp = this.pC(var2);
         int var4 = this.pC(var2);
         this.pC(var2);
         int var5 = this.pC(var2);
         Object[] var10000 = new Object[]{this.ld, this.gp, var3, var4, var5};
         int var6 = var5 * this.ld;
         chw var7 = new chw(var4);
         int var8 = (var7.pC + this.ld - 1) / this.ld;
         var2 = ChannelUtil.read(this.ys, var6, var8 * 4, false, var2);
         this.pC(var7, var2);
         byte[] var9 = this.pC(var7);
         var10000 = new Object[]{var7, Formatter.formatBinaryBlock(var9)};
         var2 = ByteBufferUtil.wrapLE(var9);
         int var10 = this.pC(var2);

         for (int var11 = 0; var11 < var10; var11++) {
            int var12 = this.pC(var2);
            this.pC.add(new chw(var12));
         }

         int var19 = 0;

         for (chw var13 : this.pC) {
            this.pC(var13, var2);
            var10000 = new Object[]{var19, var13};
            var19++;
         }

         chw var21 = (chw)this.pC.get(1);
         var9 = this.pC(var21);
         var2 = ByteBufferUtil.wrapLE(var9);
         this.A = var2.getInt();
         this.kS = var2.getInt();
         this.wS = var2.getInt();
         this.UT = Guid.parse(var2);
         var10000 = new Object[]{this.UT};
      }
   }

   @Override
   public void close() throws IOException {
      this.ys.close();
   }

   public boolean pC(chx var1) {
      return this.wS >= var1.A() && this.UT.equals(var1.pC());
   }

   public byte[] pC(chw var1) throws IOException {
      byte[] var2 = new byte[var1.pC];
      this.pC(var1, 0, var1.pC, var2, 0);
      return var2;
   }

   public void pC(chw var1, int var2, int var3, byte[] var4, int var5) throws IOException {
      if (var2 <= var1.pC && var2 + var3 <= var1.pC) {
         int var6 = var3;
         int var7 = 0;
         int var8 = var2 / this.ld;

         for (int var9 = var2 % this.ld; var6 > 0; var8++) {
            int var10 = (Integer)var1.A.get(var8) * this.ld + var9;
            int var11 = Math.min(var6, this.ld - var9);
            this.oT = ChannelUtil.read(this.ys, var10, var11, false, this.oT);
            var9 = 0;
            this.oT.get(var4, var7, var11);
            var7 += var11;
            var6 -= var11;
         }
      } else {
         throw new IOException();
      }
   }

   private void pC(chw var1, ByteBuffer var2) throws IOException {
      int var3 = (var1.pC + this.ld - 1) / this.ld;

      for (int var4 = 0; var4 < var3; var4++) {
         int var5 = this.pC(var2);
         var1.A.add(var5);
      }
   }

   public List pC(int var1) throws IOException {
      return this.pC(var1, null);
   }

   public List pC(int var1, int[] var2) throws IOException {
      ArrayList var3 = new ArrayList();
      byte[] var4 = this.pC((chw)this.pC.get(3));
      ByteBuffer var5 = ByteBufferUtil.wrapLE(var4);
      chv var6 = chv.pC(var5);
      if (var1 != 0 && var1 != var6.kS) {
         throw new IOException("Unexpected age check");
      } else {
         var4 = this.pC((chw)this.pC.get(var6.ys));
         cib var7 = new cib(new ArraySeekableByteChannel(var4));

         while (var7.hasNext()) {
            cii var8 = var7.pC();
            cij var9 = var7.pC(var8);
            if (var9 == null) {
               if (var2 != null) {
                  var2[0]++;
               }
            } else {
               var3.add(var9);
            }
         }

         return var3;
      }
   }
}
