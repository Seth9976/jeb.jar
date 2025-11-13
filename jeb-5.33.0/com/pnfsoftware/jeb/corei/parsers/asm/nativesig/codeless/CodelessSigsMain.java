package com.pnfsoftware.jeb.corei.parsers.asm.nativesig.codeless;

import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.core.units.code.asm.sig.codeless.CodelessLibraryID;
import com.pnfsoftware.jeb.core.units.code.asm.sig.codeless.CodelessLibraryVersion;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class CodelessSigsMain {
   private static final ILogger pC;

   public static void main(String[] var0) throws JebException, IOException {
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

      if (Strings.equals(var0[0], "--generate")) {
         boolean var17;
         if (Strings.equals(var0[1], "reference")) {
            var17 = true;
         } else {
            if (!Strings.equals(var0[1], "unknown")) {
               pC();
               return;
            }

            var17 = false;
         }

         CodelessLibraryID var22 = null;
         CodelessLibraryVersion var24 = null;
         if (var17) {
            if (var0.length < 5) {
               pC();
               return;
            }

            for (int var26 = 2; var26 < 5; var26++) {
               if (Strings.startsWith(var0[var26], "--library-id=")) {
                  String var28 = var0[var26].substring(13);

                  for (CodelessLibraryID var10 : CodelessLibraryID.values()) {
                     if (Strings.equals(var28, var10.getName())) {
                        var22 = var10;
                     }
                  }
               }

               if (Strings.startsWith(var0[var26], "--library-version=")) {
                  var24 = new CodelessLibraryVersion(var0[var26].substring(18));
               }
            }

            if (var22 == null || var24 == null) {
               pC.error("> couldn't find library id and/or library version");
               return;
            }
         }

         yt var27 = new yt();
         Ws var29 = var27.pC(var0[var17 ? 4 : 2], var17, var22, var24);
         Ws.pC(var29, var0[var17 ? 5 : 3]);
      } else if (Strings.equals(var0[0], "--match")) {
         KD var11 = new KD();
         var11.pC(var0[1], var0[2], true, true, true, 0);
      } else if (Strings.equals(var0[0], "--import")) {
         zp var12 = new zp();
         var12.pC(var0[1], var0[2]);
      } else if (Strings.equals(var0[0], "--print-gephi-callgraph")) {
         byte var13 = 1;
         Long var18 = null;
         Long var23 = null;
         if (var0.length >= 5) {
            if (Strings.equals(var0[1], "--start-address")) {
               var18 = Long.decode(var0[2]);
               var13 += 2;
            }

            if (Strings.equals(var0[3], "--end-address")) {
               var23 = Long.decode(var0[4]);
               var13 += 2;
            }
         }

         Ws var25 = Ws.pC(var0[var13]);
         cq var6 = new cq();
         var6.pC(var25, var0[var13 + 1], var18, var23);
      } else if (Strings.equals(var0[0], "--stats")) {
         Ws var14 = Ws.pC(var0[1]);
         bO var19 = new bO();
         var19.pC(var14, var0[2]);
      } else if (Strings.equals(var0[0], "--print-gephi-reference-callgraph")) {
         Ws var15 = Ws.pC(var0[1]);
         cq var20 = new cq();
         var20.pC(var15, var0[2]);
      } else if (Strings.equals(var0[0], "--print-gephi-reference-features")) {
         Ws var16 = Ws.pC(var0[1]);
         cq var21 = new cq();
         var21.A(var16, var0[2]);
      } else {
         pC.error("> invalid argument (%s)", var0[0]);
      }
   }

   private static void pC() {
      pC.warn(
         "JEB Codeless (c) -- PNF Software 2020\nPlease provide the following arguments:\n\n    --generate reference|unknown --library-id=LIB_ID_STR --library-version=LIB_VER_STR <executable path> <output folder path>\n\n    --matching <reference model file path> <target model file path>\n\n"
      );
   }

   static {
      GlobalLog.addDestinationStream(System.out);

      try {
         String var0 = System.getProperty("java.io.tmpdir");
         File var1 = new File(var0, "jeb-codeless-signatures-log.txt");
         GlobalLog.addDestinationStream(new PrintStream(var1));
      } catch (IOException var2) {
         throw new RuntimeException(var2);
      }

      pC = GlobalLog.getLogger(CodelessSigsMain.class, 30);
   }
}
