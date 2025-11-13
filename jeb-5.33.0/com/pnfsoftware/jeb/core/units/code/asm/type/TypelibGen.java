package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.corei.parsers.asm.type.tlgen.Av;
import com.pnfsoftware.jeb.corei.parsers.asm.type.tlgen.TLGen;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.ayy;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class TypelibGen {
   private static final ILogger logger;

   public static void main(String[] var0) {
      if (var0.length == 0) {
         String var8 = "JEB Type Library Generator (c) Nicolas Falliere, PNF Software\nProvide a configuration file as argument\n";
         logger.warn(var8);
      } else {
         File var1 = new File(var0[0]);
         logger.info("Configuration: %s", var1);

         try {
            for (Av var3 : Av.pC(var1)) {
               ayy var4 = ayy.pC(var3.UT.getPrimaryProcessorType(), var3.UT.getPrimarySubsystemType(), var3.UT.getCompilerType());
               TLGen var5 = new TLGen(var4, var3.A, var3.kS);
               var5.pC(var3.UT, true, var3.wS);
               logger.info("- Generated: %s", var3.wS);
            }

            logger.info("Copy the generated files to your JEB's typelibs/ folder");
         } catch (TypelibDefinitionException var6) {
            logger.error("ERROR! %s", var6.getMessage());
            System.exit(-1);
         } catch (Exception var7) {
            logger.catching(var7);
         }
      }
   }

   static {
      GlobalLog.addDestinationStream(System.out);

      try {
         String var0 = System.getProperty("java.io.tmpdir");
         File var1 = new File(var0, "jeb-typelib-generator-output.log");
         GlobalLog.addDestinationStream(new PrintStream(var1));
      } catch (IOException var2) {
         throw new RuntimeException(var2);
      }

      logger = GlobalLog.getLogger(TypelibGen.class, 30);
   }
}
