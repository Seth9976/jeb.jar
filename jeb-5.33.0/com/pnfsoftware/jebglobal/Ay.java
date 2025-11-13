package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.collect.BytePipe;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.nio.ByteBuffer;

public class Ay extends AR {
   private static final ILogger pC = GlobalLog.getLogger(Ay.class);
   private bA A;
   private BytePipe kS = new BytePipe();

   public Ay(bA var1) {
      super(var1, Ay.class);
      this.A = var1;
   }

   @Override
   protected void pC(byte[] var1) throws IOException {
      this.kS.append(var1);

      while (this.kS.available() >= 11) {
         byte[] var2 = new byte[4];
         this.kS.peek(var2);
         int var3 = ByteBuffer.wrap(var2).getInt();
         if (this.kS.available() < var3) {
            break;
         }

         byte[] var4 = new byte[var3];
         this.kS.get(var4);
         BG var5 = BG.pC(var4);
         this.A.pC(var5);
      }
   }
}
