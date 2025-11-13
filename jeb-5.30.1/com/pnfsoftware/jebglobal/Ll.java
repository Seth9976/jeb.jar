package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.Arrays;

public abstract class Ll extends Thread {
   private static final ILogger q = GlobalLog.getLogger(Ll.class);
   private tz RF;

   public Ll(tz var1, Class var2) {
      super(var2.getSimpleName());
      this.RF = var1;
   }

   @Override
   public void run() {
      try (InputStream var1 = this.RF.a_().getInputStream()) {
         byte[] var2 = new byte[4096];

         while (true) {
            int var3 = var1.read(var2);
            if (var3 < 0) {
               break;
            }

            this.q(Arrays.copyOf(var2, var3));
            if (Thread.interrupted()) {
               throw new RuntimeException("Interrupted");
            }
         }
      } catch (SocketException var6) {
         if (Strings.equals(var6.getMessage(), "Socket closed")) {
            q.error("The debugger socket was closed (debugger: %s)", this.RF);
         } else {
            q.catchingSilent(var6);
         }
      } catch (IOException var7) {
         q.catchingSilent(var7);
      }

      Object[] var10000 = new Object[]{this.RF};
   }

   protected abstract void q(byte[] var1) throws IOException;
}
