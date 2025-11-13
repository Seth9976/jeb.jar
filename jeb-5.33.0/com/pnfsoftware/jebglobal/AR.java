package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.Arrays;

public abstract class AR extends Thread {
   private static final ILogger pC = GlobalLog.getLogger(AR.class);
   private jJ A;

   public AR(jJ var1, Class var2) {
      super(var2.getSimpleName());
      this.A = var1;
   }

   @Override
   public void run() {
      try (InputStream var1 = this.A.a_().getInputStream()) {
         byte[] var2 = new byte[4096];

         while (true) {
            int var3 = var1.read(var2);
            if (var3 < 0) {
               break;
            }

            this.pC(Arrays.copyOf(var2, var3));
            if (Thread.interrupted()) {
               throw new RuntimeException("Interrupted");
            }
         }
      } catch (SocketException var6) {
         if (Strings.equals(var6.getMessage(), "Socket closed")) {
            pC.error("The debugger socket was closed (debugger: %s)", this.A);
         } else {
            pC.catchingSilent(var6);
         }
      } catch (IOException var7) {
         pC.catchingSilent(var7);
      }

      Object[] var10000 = new Object[]{this.A};
   }

   protected abstract void pC(byte[] var1) throws IOException;
}
