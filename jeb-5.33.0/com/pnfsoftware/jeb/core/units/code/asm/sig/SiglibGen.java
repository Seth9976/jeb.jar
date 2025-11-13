package com.pnfsoftware.jeb.core.units.code.asm.sig;

import com.pnfsoftware.jeb.corei.parsers.asm.nativesig.siggen.HE;
import com.pnfsoftware.jeb.corei.parsers.asm.nativesig.siggen.qt;
import com.pnfsoftware.jeb.corei.parsers.asm.nativesig.siggen.sy;
import com.pnfsoftware.jeb.util.base.Throwables;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class SiglibGen {
   private static final ILogger logger;

   public static void main(String[] var0) throws UnsupportedEncodingException {
      String[] var1 = new String[]{
         "com.pnfsoftware.jeb.corei.parsers.asm.analyzer",
         "com.pnfsoftware.jeb.corei.parsers.wincoff",
         "com.pnfsoftware.jeb.corei.parsers.ar",
         "com.pnfsoftware.jeb.corei.parsers.winpe",
         "com.pnfsoftware.jeb.corei.parsers.asm.type",
         "com.pnfsoftware.jeb.corei.parsers.elf",
         "com.pnfsoftware.jeb.corei.parsers.arm"
      };

      for (String var5 : var1) {
         GlobalLog.addGlobalFilter(var5, Integer.MAX_VALUE);
      }

      if (var0.length != 1) {
         logger.warn(
            "JEB Native Signatures Generator (c) -- PNF Software 2019\nPlease provide the following argument:\n\n    <configuration_file>: path to configuration file (see sample-siglib.cfg for an example);\n\n Expected outputs:\n  - signatures will be written in '%s' folder located in <configuration_file>'s folder;\n  - logs and conflicts resolution files will be written in <configuration_file>'s folder.",
            "output"
         );
      } else {
         HE var11;
         try {
            var11 = qt.pC(new File(var0[0]));
         } catch (SiglibException var9) {
            logger.error("cannot parse configuration file");
            return;
         }

         String var12 = new File(var0[0]).getParent();
         boolean var13 = true;

         try {
            sy.pC(var12, var11, var13, true, true);
         } catch (Throwable var10) {
            String var6 = new File(var0[0]).getParent();
            if (var6 != null) {
               File var7 = new File(var6, "errors.txt");
               logger.error("> ERROR: see %s", var7);
               String var8 = Throwables.formatStacktrace(var10);
               IO.writeFileSafe(var7, var8.getBytes("UTF-8"), false);
            }
         }
      }
   }

   static {
      GlobalLog.addDestinationStream(System.out);

      try {
         String var0 = System.getProperty("java.io.tmpdir");
         File var1 = new File(var0, "jeb-siglib-generator-output.log");
         GlobalLog.addDestinationStream(new PrintStream(var1));
      } catch (IOException var2) {
         throw new RuntimeException(var2);
      }

      logger = GlobalLog.getLogger(SiglibGen.class, 30);
   }
}
