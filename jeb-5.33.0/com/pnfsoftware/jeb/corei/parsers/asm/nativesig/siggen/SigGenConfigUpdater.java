package com.pnfsoftware.jeb.corei.parsers.asm.nativesig.siggen;

import com.pnfsoftware.jeb.core.units.code.asm.sig.SiglibException;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.Deserializer;
import com.pnfsoftware.jeb.util.serialization.SerializationManager;
import com.pnfsoftware.jebglobal.ckh;
import com.pnfsoftware.jebglobal.ckw;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;

public class SigGenConfigUpdater {
   private static final ILogger pC = GlobalLog.getLogger(SigGenConfigUpdater.class);

   public static void main(String[] var0) throws SiglibException, IOException {
      if (var0.length == 0) {
         pC.info("> BULK MODE");
         ArrayList var1 = new ArrayList();
         File var2 = new File("C:\\work\\PNF\\jeb2\\jeb2-core2-siglibs");
         String var3 = "siglib_config.bin";
         boolean var4 = true;

         for (File var7 : FileUtils.listFiles(var2, null, var4)) {
            if (var7.getName().equals(var3)) {
               var1.add(var7.getAbsolutePath());
            }
         }

         if (!var1.isEmpty()) {
            for (String var9 : var1) {
               pC(var9);
            }
         }
      } else {
         pC(var0[0]);
      }
   }

   private static void pC(String var0) throws IOException, SiglibException {
      boolean var1 = true;
      HE var2 = null;

      try (FileInputStream var3 = new FileInputStream(new File(var0))) {
         ckh var4 = ckh.pC();
         SerializationManager var5 = new SerializationManager(var4);
         Deserializer var6 = var5.getDeserializer(var3);
         var2 = (HE)var6.deserialize();
         pC.warn("> using the found config file");
      } catch (IOException var9) {
         pC.warn("No config file found");
      }

      var2.pC(null);
      ArrayList var10 = new ArrayList();

      for (String var13 : var2.NS()) {
         var10.add(var13.replace(',', ' '));
      }

      var2.kS(var10);
      var2.pC(new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.vi());
      var2.pC(true);
      if (ckw.pC()) {
         if (var1) {
            var2.ys("/home/ubuntu/jeb3-core/typelibs");
         } else {
            var2.ys("C:\\work\\PNF\\jeb2\\jeb3-core\\typelibs");
         }
      }

      String var12 = var1 ? "_linux.txt" : ".txt";
      qt.pC(var2, new File(var0 + var12));
      qt.pC(new File(var0 + var12));
   }

   static {
      GlobalLog.addDestinationStream(System.out);
   }
}
