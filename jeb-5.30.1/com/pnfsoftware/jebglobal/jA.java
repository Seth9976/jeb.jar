package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.format.TextBuilder;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.nio.ByteBuffer;

public class jA {
   protected static final ILogger q = GlobalLog.getLogger(jA.class);
   public int RF;
   public int xK;
   public int Dw;
   public int Uv;

   static void q(String var0, Object... var1) {
   }

   protected jA(int var1, int var2) {
      this.RF = -1;
      this.xK = var1;
      this.Dw = var2;
      this.Uv = var2;
   }

   protected jA(uL var1, int var2) throws IOException {
      this.RF = var1.q();
      this.xK = var1.readUnsignedShort();
      if (var2 != 0 && this.xK != var2) {
         throw new IOException("Unexpected chunk type: " + this.xK);
      } else {
         this.Dw = var1.readUnsignedShort();
         this.Uv = var1.readInt();
         if (this.Dw > this.Uv) {
            String var3 = Strings.ff(S.L("Illegal chunk: header size 0x%x is greater than the chunk size 0x%X"), this.Dw, this.Uv);
            var1.q(var3);
         }
      }
   }

   protected jA(jA var1, int var2) throws IOException {
      this.RF = var1.RF;
      this.xK = var1.xK;
      if (var2 != 0 && this.xK != var2) {
         throw new IOException("Unexpected chunk type: " + this.xK);
      } else {
         this.Dw = var1.Dw;
         this.Uv = var1.Uv;
      }
   }

   protected jA(ByteBuffer var1, int var2) throws IOException {
      this.RF = var1.position();
      this.xK = var1.getShort() & '\uffff';
      if (var2 != 0 && this.xK != var2) {
         throw new IOException("Unexpected chunk type: " + this.xK);
      } else {
         this.Dw = var1.getShort();
         this.Uv = var1.getInt();
         if (this.Dw > this.Uv) {
            throw new IOException(Strings.ff(S.L("Illegal chunk: header size 0x%x is greater than the chunk size 0x%X"), this.Dw, this.Uv));
         }
      }
   }

   public int q(uL var1) {
      return var1.q() - this.RF;
   }

   public int RF(uL var1) {
      return var1.q() - (this.RF + this.Dw);
   }

   public int xK(uL var1) throws IOException {
      int var2 = this.RF + this.Dw;
      int var3 = var2 - var1.q();
      return zR.xK(var1, var3);
   }

   public byte[] Dw(uL var1) throws IOException {
      int var2 = this.RF + this.Dw;
      int var3 = var2 - var1.q();
      byte[] var4 = new byte[var3];
      var1.readFully(var4);
      return var4;
   }

   public int Uv(uL var1) throws IOException {
      int var2 = this.RF + this.Uv;
      int var3 = var2 - var1.q();
      return zR.xK(var1, var3);
   }

   public byte[] oW(uL var1) throws IOException {
      int var2 = this.RF + this.Uv;
      int var3 = var2 - var1.q();
      byte[] var4 = new byte[var3];
      var1.readFully(var4);
      return var4;
   }

   public int q(uL var1, int var2) throws IOException {
      var2 += this.RF;
      int var3 = var2 - var1.q();
      return zR.xK(var1, var3);
   }

   public byte[] RF(uL var1, int var2) throws IOException {
      var2 += this.RF;
      int var3 = var2 - var1.q();
      byte[] var4 = new byte[var3];
      var1.readFully(var4);
      return var4;
   }

   public int gO(uL var1) throws IOException {
      return var1.q() + 4 > this.RF + this.Dw ? 0 : var1.readInt();
   }

   public int nf(uL var1) {
      return var1.q() - this.RF;
   }

   public int gP(uL var1) {
      return this.RF + this.Uv - var1.q();
   }

   @Override
   public String toString() {
      return Strings.ff("Chunk@%Xh: type=%s, headerSize=%Xh, chunkSize=%Xh", this.RF, mw.xK(this.xK), this.Dw, this.Uv);
   }

   public void q(TextBuilder var1) {
      var1.appendLine(this.toString());
   }

   public void q(pK var1) {
      var1.q(this.xK, this.Dw);
   }

   public final void RF(pK var1) {
      var1.q();
   }
}
