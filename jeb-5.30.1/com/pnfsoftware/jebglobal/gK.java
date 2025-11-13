package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.collect.BytePipe;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.nio.ByteBuffer;

public class gK extends Ll {
   private static final ILogger q = GlobalLog.getLogger(gK.class);
   private Ux RF;
   private BytePipe xK = new BytePipe();

   public gK(Ux var1) {
      super(var1, gK.class);
      this.RF = var1;
   }

   @Override
   protected void q(byte[] var1) throws IOException {
      this.xK.append(var1);

      while (this.xK.available() >= 11) {
         byte[] var2 = new byte[4];
         this.xK.peek(var2);
         int var3 = ByteBuffer.wrap(var2).getInt();
         if (this.xK.available() < var3) {
            break;
         }

         byte[] var4 = new byte[var3];
         this.xK.get(var4);
         zx var5 = zx.q(var4);
         this.RF.q(var5);
      }
   }
}
