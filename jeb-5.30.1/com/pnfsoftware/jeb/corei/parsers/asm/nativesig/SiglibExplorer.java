package com.pnfsoftware.jeb.corei.parsers.asm.nativesig;

import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeSignature;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.Deserializer;
import com.pnfsoftware.jeb.util.serialization.SerializationManager;
import com.pnfsoftware.jebglobal.cuu;
import java.io.File;
import java.io.FileInputStream;

public class SiglibExplorer {
   private static final ILogger q = GlobalLog.getLogger(SiglibExplorer.class);

   public static void main(String[] var0) {
      if (var0.length != 1) {
         q.error("> missing package path");
      } else {
         Vj var1 = q(new File(var0[0]));

         for (INativeSignature var3 : var1.getSignatures()) {
            if (var3.getTargetName().startsWith("4865ee6a3409f290")) {
               Object[] var10000 = new Object[]{var3};
            }
         }
      }
   }

   private static Vj q(File var0) {
      try {
         Vj var5;
         try (FileInputStream var1 = new FileInputStream(var0)) {
            cuu var2 = cuu.q();
            SerializationManager var3 = new SerializationManager(var2);
            Deserializer var4 = var3.getDeserializer(var1);
            var4.deserialize();
            var5 = (Vj)var4.deserialize();
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
