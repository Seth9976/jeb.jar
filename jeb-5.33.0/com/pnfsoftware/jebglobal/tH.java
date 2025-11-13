package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;

public class tH {
   protected static final ILogger pC = GlobalLog.getLogger(tH.class);
   public int A;
   public int kS;
   public int wS;
   public int UT;

   protected tH(EX var1, int var2) throws IOException {
      this.A = var1.pC();
      this.kS = var1.readUnsignedShort();
      if (var2 != 0 && this.kS != var2) {
         throw new IOException("Unexpected chunk type: " + this.kS);
      } else {
         this.wS = var1.readUnsignedShort();
         this.UT = var1.readInt();
         if (this.wS > this.UT) {
            String var3 = Strings.ff(S.L("Illegal chunk: header size 0x%x is greater than the chunk size 0x%X"), this.wS, this.UT);
            var1.pC(var3);
         }
      }
   }

   protected tH(tH var1, int var2) throws IOException {
      this.A = var1.A;
      this.kS = var1.kS;
      if (var2 != 0 && this.kS != var2) {
         throw new IOException("Unexpected chunk type: " + this.kS);
      } else {
         this.wS = var1.wS;
         this.UT = var1.UT;
      }
   }

   public int pC(EX var1) throws IOException {
      int var2 = this.A + this.wS;
      int var3 = var2 - var1.pC();
      return bM.A(var1, var3);
   }

   public byte[] A(EX var1) throws IOException {
      int var2 = this.A + this.wS;
      int var3 = var2 - var1.pC();
      byte[] var4 = new byte[var3];
      var1.readFully(var4);
      return var4;
   }

   public int kS(EX var1) throws IOException {
      int var2 = this.A + this.UT;
      int var3 = var2 - var1.pC();
      return bM.A(var1, var3);
   }

   public int pC(EX var1, int var2) throws IOException {
      var2 += this.A;
      int var3 = var2 - var1.pC();
      return bM.A(var1, var3);
   }

   public byte[] A(EX var1, int var2) throws IOException {
      var2 += this.A;
      int var3 = var2 - var1.pC();
      byte[] var4 = new byte[var3];
      var1.readFully(var4);
      return var4;
   }

   public int wS(EX var1) throws IOException {
      return var1.pC() + 4 > this.A + this.wS ? 0 : var1.readInt();
   }

   public int UT(EX var1) {
      return var1.pC() - this.A;
   }

   public int E(EX var1) {
      return this.A + this.UT - var1.pC();
   }

   @Override
   public String toString() {
      return Strings.ff("Chunk@%Xh: type=%s, headerSize=%Xh, chunkSize=%Xh", this.A, Eq.A(this.kS), this.wS, this.UT);
   }
}
