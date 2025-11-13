package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.ast.optimizer.explorer;

import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.serialization.Deserializer;
import com.pnfsoftware.jeb.util.serialization.SerializationManager;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import com.pnfsoftware.jebglobal.aco;
import com.pnfsoftware.jebglobal.ckh;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Ser
public class cq {
   private static final StructuredLogger wS = aco.pC(cq.class);
   @SerId(1)
   String pC;
   @SerId(2)
   String A;
   @SerId(3)
   List kS;

   public static cq pC(File var0) {
      try {
         cq var6;
         try (FileInputStream var1 = new FileInputStream(var0)) {
            ckh var2 = ckh.pC();
            SerializationManager var3 = new SerializationManager(var2);
            Deserializer var4 = var3.getDeserializer(var1);
            cq var5 = (cq)var4.deserialize();
            var6 = var5;
         }

         return var6;
      } catch (IOException var9) {
         wS.error("ser error");
         return null;
      }
   }

   @Ser
   public static class Av {
      @SerId(1)
      int pC;
      @SerId(2)
      String A;
      @SerId(3)
      int kS;
      @SerId(4)
      long wS;
      @SerTransient
      RC.Av UT;

      public boolean pC() {
         return this.kS > 0;
      }

      public RC.Av A() {
         if (this.UT == null) {
            this.UT = new RC.Av(this.pC, this.A);
         }

         return this.UT;
      }
   }
}
