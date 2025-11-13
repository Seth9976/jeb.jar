package com.pnfsoftware.jeb.corei.parsers.asm.nativesig;

import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeSignature;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.Deserializer;
import com.pnfsoftware.jeb.util.serialization.SerializationManager;
import com.pnfsoftware.jebglobal.ckh;
import java.io.File;
import java.io.FileInputStream;

public class SiglibExplorer {
   private static final ILogger pC = GlobalLog.getLogger(SiglibExplorer.class);

   public static void main(String[] var0) {
      if (var0.length != 1) {
         pC.error("> missing package path");
      } else {
         qt var1 = pC(new File(var0[0]));

         for (INativeSignature var3 : var1.getSignatures()) {
            if (var3.getTargetName().startsWith("4865ee6a3409f290")) {
               Object[] var10000 = new Object[]{var3};
            }
         }
      }
   }

   private static qt pC(File var0) {
      try {
         qt var5;
         try (FileInputStream var1 = new FileInputStream(var0)) {
            ckh var2 = ckh.pC();
            SerializationManager var3 = new SerializationManager(var2);
            Deserializer var4 = var3.getDeserializer(var1);
            var4.deserialize();
            var5 = (qt)var4.deserialize();
         }

         return var5;
      } catch (Exception var8) {
         return null;
      }
   }

   static {
      GlobalLog.addDestinationStream(System.out);
   }
}
