package com.pnfsoftware.jeb;

import com.pnfsoftware.jeb.client.AbstractClientContext;
import com.pnfsoftware.jeb.client.AbstractContext;
import com.pnfsoftware.jeb.client.HeadlessClientContext;
import com.pnfsoftware.jeb.client.JebContextUtil;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.asm.sig.SiglibGen;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypelibGen;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.RotatingFileOutputStream;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.logging.StreamSink;
import com.pnfsoftware.jebglobal.Ws;
import java.io.File;
import java.util.Arrays;

public class Launcher {
   private static final ILogger pC = GlobalLog.getLogger(Launcher.class);
   private static int A;

   public static void main(String[] var0) throws Exception {
      JebContextUtil.autoDetectPreferredLanguage();
      Object var1 = null;
      boolean var2 = false;
      byte var3 = 2;
      Object var4 = null;
      String[] var5 = null;
      Object var6 = null;
      Object var7 = null;
      Object var8 = null;
      Object var9 = null;
      Object var10 = null;
      int var11 = 0;

      for (String var15 : var0) {
         if (!var15.equalsIgnoreCase("-c") && !var15.equalsIgnoreCase("-j")) {
            if (var15.equalsIgnoreCase("--license")) {
               System.out.println(AbstractClientContext.generateLicenseInformation());
               System.exit(0);
            } else if (var15.equalsIgnoreCase("--generate-key")) {
               pC(2);
            } else if (var15.equalsIgnoreCase("--check-update")) {
               pC(3);
            } else if (var15.startsWith("--logfile=")) {
               String[] var16 = var15.substring(10).split(",");
               if (var16.length >= 1) {
                  String var17 = var16[0];
                  if (var17.isEmpty()) {
                     pC(S.L("Provide a non-empty file name"));
                  }

                  int var18 = var16.length < 2 ? 30 : GlobalLog.parseLevel(var16[1]);
                  if (var18 < 0) {
                     pC(S.L("Illegal log level"));
                  }

                  int var19 = var16.length < 3 ? 1 : pC(var16[2], 1);
                  if (var19 <= 0) {
                     pC(S.L("Illegal count"));
                  }

                  int var20 = var16.length < 4 ? 1 : pC(var16[3], 1);
                  if (var20 <= 0) {
                     pC(S.L("Illegal size"));
                  }

                  boolean var21 = var16.length < 5 ? false : pC(var16[4], 0) != 0;
                  File var22 = new File(var17);
                  StreamSink var23 = GlobalLog.addDestinationStream(
                     new RotatingFileOutputStream(var22.getParentFile(), var22.getName(), var19 - 1, var20 * 1024 * 1024, var21, true)
                  );
                  var23.setPrefixWithTimestamp(true);
                  var23.setLogLevel(var18);
               }
            } else if (var15.equals("--makeapkdebug")) {
               pC(7);
            } else {
               if (var15.equals("--")) {
                  var5 = (String[])Arrays.copyOfRange((Object[])var0, var11 + 1, var0.length);
                  break;
               }

               if (!var15.equals("-h") && !var15.equals("--help")) {
                  System.out.format(S.L("Disregarding unknown argument \"%s\"\n"), var15);
               } else {
                  pC();
                  System.exit(-1);
               }
            }
         }

         var11++;
      }

      if (A == 1) {
         Ws var24 = new Ws((String)var6, (Integer)var7, (Integer)var8);
         var24.initialize(var0);
         var24.start();
         var24.stop();
      } else if (A == 2) {
         HeadlessClientContext var25 = new HeadlessClientContext();
         var25.initialize(var0);
         var25.start();
         var25.stop();
      } else if (A == 3) {
         HeadlessClientContext var26 = new HeadlessClientContext();
         var26.initialize(var0);
         var26.start();
         var26.checkUpdate();
         var26.stop();
      } else if (A == 4) {
         if (var1 != null) {
            if (var3 == 1) {
               throw new RuntimeException("The legacy script runner is deprecated. Please use the script runner v2.");
            }

            if (var3 == 2) {
               Av var27 = new Av(var2, (String)var1, (String)var4, var5);
               var27.initialize(var0);
               var27.start();
               var27.stop();
            }
         }
      } else if (A == 5) {
         TypelibGen.main(new String[]{(String)var9});
      } else if (A == 8) {
         SiglibGen.main(new String[]{(String)var10});
      } else if (A == 7) {
         pC(var5);
      }
   }

   private static void pC(int var0) {
      if (A != 0) {
         System.out.println(S.L("A command was already specified"));
      }

      A = var0;
   }

   static int pC(String var0, int var1) {
      try {
         return Integer.parseInt(var0);
      } catch (NumberFormatException var2) {
         return var1;
      }
   }

   private static void pC(String var0, Object... var1) {
      String var2 = S.L("[ERROR]") + " " + Strings.ff(var0, var1);
      System.err.println(var2);
      System.exit(-1);
   }

   private static void pC() {
      System.out.format("%s v%s - %s (c) %s\n", "JEB", AbstractContext.app_ver, "PNF Software, Inc.", "2015-2025");
      System.out.println("");
      System.out.println(S.L("Program options:"));
      System.out.println("  --license             : " + S.L("Print out JEB license information"));
      System.out.println("  --generate-key        : " + S.L("Generate a license key in headless environments"));
      System.out.println("  --check-update        : " + S.L("Download the latest update (requires an Internet connection)"));
      System.out.println("");
      System.out.println(S.L("Logging options:"));
      System.out.println("  --logfile=path[,level[,count[,sizeMb[,buffered]]]]");
      System.out.println("                        : " + S.L("Log output to rotating file logs"));
      System.out.println("                          path= " + S.L("base output log file path (e.g., jeb.log)"));
      System.out.println("                          level= " + S.L("minimum (inclusive) threshold for logging"));
      System.out.println("                          count= " + S.L("log files count (will generate files `path`, `path.1`, `path.2`, ..."));
      System.out.println("                          sizeMb= " + S.L("maximum size in Mb of a single log file"));
      System.out.println("                          buffered= " + S.L("buffer file-write operations (should be enabled for high-frequency logging)"));
      System.out.println("                          Defaults values: " + S.L("level=INFO, count=1, sizeMb=1, buffered=0"));
      System.out.println("                          " + S.L("Legal log level values:") + " TRACE, DEBUG, INFO, WARN, ERROR");
      System.out.println("");
      System.out.println(S.L("Debuggable APK Generator:"));
      System.out.println("  --makeapkdebug -- [options] <apkfile>");
      System.out.println("                        : " + S.L("Generate the a debuggable version of an APK."));
      System.out
         .println(
            "                          "
               + S.L(
                  "The resulting APK's digital signatures are invalid. The APK should be resigned using the keys of your choosing before attempting to install it on a production Android device"
               )
         );
      System.out.println("     " + S.L("Options:"));
      System.out.println("     --output=OUTPUT          : " + S.L("Specify the output file"));
      System.out
         .println("                                " + Strings.ff(S.L("If not present, a new file with the '%s' suffix will be created"), "_debuggable.apk"));
      System.out
         .println("     --flagInsertionIndex=IDX : " + S.L("Explicitly specify where the 'debuggable' attribute should be inserted in the Application element"));
      System.out.println("                                " + S.L("A negative index means start from the end (i.e., -1 means append)"));
      System.out.println("                                " + S.L("It is recommended to not set this flag to let JEB pick an appropriate value"));
      System.out
         .println(
            "     --forceExtractNativeLibs : "
               + S.L(
                  "On top of setting the 'debuggable' attribute to true, an attempt will also be made to set the 'extractNativeLibs' attribute to true as well"
               )
         );
      System.out.println("");
   }

   public static void pC(String[] var0) throws Exception {
      pC.error("This option is not available in the demo!");
      System.exit(-1);
   }

   static {
      GlobalLog.addDestinationStream(System.out);
   }
}
