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

public class cpg implements AutoCloseable {
   private static final ILogger oW = GlobalLog.getLogger(cpg.class, Integer.MAX_VALUE);
   private static final byte[] gO = Strings.encodeASCII("Microsoft C/C++ MSF 7.00\r\n\u001aDS\u0000\u0000\u0000");
   private SeekableByteChannel nf;
   private int gP;
   private int za;
   private ByteBuffer lm;
   List q = new ArrayList();
   int RF;
   int xK;
   int Dw;
   Guid Uv;

   private int q(ByteBuffer var1) {
      int var2 = var1.getInt();
      if (var2 < -1) {
         throw new RuntimeException("Illegal uint: " + Integer.toHexString(var2));
      } else {
         return var2;
      }
   }

   public cpg(IInput var1) throws IOException {
      this.nf = var1.getChannel();
      ByteBuffer var2 = ChannelUtil.read(this.nf, 0L, 32, false);
      if (!ByteBufferUtil.equalsByteArray(var2, 0, gO, 0, 32)) {
         throw new IOException("Not a PDB version 7");
      } else {
         var2 = ChannelUtil.read(this.nf, 32L, 24, false, var2);
         this.gP = this.q(var2);
         this.lm = ByteBuffer.allocate(this.gP);
         this.lm.order(ByteOrder.LITTLE_ENDIAN);
         int var3 = this.q(var2);
         this.za = this.q(var2);
         int var4 = this.q(var2);
         this.q(var2);
         int var5 = this.q(var2);
         Object[] var10000 = new Object[]{this.gP, this.za, var3, var4, var5};
         int var6 = var5 * this.gP;
         cpa var7 = new cpa(var4);
         int var8 = (var7.q + this.gP - 1) / this.gP;
         var2 = ChannelUtil.read(this.nf, var6, var8 * 4, false, var2);
         this.q(var7, var2);
         byte[] var9 = this.q(var7);
         var10000 = new Object[]{var7, Formatter.formatBinaryBlock(var9)};
         var2 = ByteBufferUtil.wrapLE(var9);
         int var10 = this.q(var2);

         for (int var11 = 0; var11 < var10; var11++) {
            int var12 = this.q(var2);
            this.q.add(new cpa(var12));
         }

         int var19 = 0;

         for (cpa var13 : this.q) {
            this.q(var13, var2);
            var10000 = new Object[]{var19, var13};
            var19++;
         }

         cpa var21 = (cpa)this.q.get(1);
         var9 = this.q(var21);
         var2 = ByteBufferUtil.wrapLE(var9);
         this.RF = var2.getInt();
         this.xK = var2.getInt();
         this.Dw = var2.getInt();
         this.Uv = Guid.parse(var2);
         var10000 = new Object[]{this.Uv};
      }
   }

   @Override
   public void close() throws IOException {
      this.nf.close();
   }

   public boolean q(cpd var1) {
      return this.Dw >= var1.RF() && this.Uv.equals(var1.q());
   }

   public int q() {
      return this.q.size();
   }

   public byte[] q(int var1) throws IOException {
      return this.q((cpa)this.q.get(var1));
   }

   public int RF() {
      return this.Dw;
   }

   public Guid xK() {
      return this.Uv;
   }

   public byte[] q(cpa var1) throws IOException {
      byte[] var2 = new byte[var1.q];
      this.q(var1, 0, var1.q, var2, 0);
      return var2;
   }

   public void q(cpa var1, int var2, int var3, byte[] var4, int var5) throws IOException {
      if (var2 <= var1.q && var2 + var3 <= var1.q) {
         int var6 = var3;
         int var7 = 0;
         int var8 = var2 / this.gP;

         for (int var9 = var2 % this.gP; var6 > 0; var8++) {
            int var10 = (Integer)var1.RF.get(var8) * this.gP + var9;
            int var11 = Math.min(var6, this.gP - var9);
            this.lm = ChannelUtil.read(this.nf, var10, var11, false, this.lm);
            var9 = 0;
            this.lm.get(var4, var7, var11);
            var7 += var11;
            var6 -= var11;
         }
      } else {
         throw new IOException();
      }
   }

   private void q(cpa var1, ByteBuffer var2) throws IOException {
      int var3 = (var1.q + this.gP - 1) / this.gP;

      for (int var4 = 0; var4 < var3; var4++) {
         int var5 = this.q(var2);
         var1.RF.add(var5);
      }
   }

   public List Dw() throws IOException {
      return this.q(0, null);
   }

   public List RF(int var1) throws IOException {
      return this.q(var1, null);
   }

   public List q(int var1, int[] var2) throws IOException {
      ArrayList var3 = new ArrayList();
      byte[] var4 = this.q((cpa)this.q.get(3));
      ByteBuffer var5 = ByteBufferUtil.wrapLE(var4);
      coz var6 = coz.q(var5);
      if (var1 != 0 && var1 != var6.xK) {
         throw new IOException("Unexpected age check");
      } else {
         var4 = this.q((cpa)this.q.get(var6.nf));
         cpi var7 = new cpi(new ArraySeekableByteChannel(var4));

         while (var7.hasNext()) {
            cpq var8 = var7.RF();
            cpr var9 = var7.q(var8);
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
