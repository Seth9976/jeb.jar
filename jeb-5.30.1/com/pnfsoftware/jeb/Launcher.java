package com.pnfsoftware.jeb;

import com.pnfsoftware.jeb.client.AbstractClientContext;
import com.pnfsoftware.jeb.client.AbstractContext;
import com.pnfsoftware.jeb.client.HeadlessClientContext;
import com.pnfsoftware.jeb.client.JebContextUtil;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.asm.sig.SiglibGen;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypelibGen;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.io.RotatingFileOutputStream;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.logging.StreamSink;
import com.pnfsoftware.jebglobal.ej;
import java.io.File;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashMap;

public class Launcher {
   private static final ILogger q = GlobalLog.getLogger(Launcher.class);
   private static int RF;

   public static void main(String[] original) throws Exception {
      JebContextUtil.autoDetectPreferredLanguage();
      String s = null;
      boolean b = false;
      int n = 2;
      String s2 = null;
      String[] array = null;
      String s3 = null;
      Integer n2 = null;
      Integer n3 = null;
      String s4 = null;
      String s5 = null;
      int n4 = 0;

      for (String s6 : original) {
         if (!s6.equalsIgnoreCase("-c") && !s6.equalsIgnoreCase("-j")) {
            if (s6.equalsIgnoreCase("--license")) {
               System.out.println(AbstractClientContext.generateLicenseInformation());
               System.exit(0);
            } else if (s6.equalsIgnoreCase("--generate-key")) {
               q(2);
            } else if (s6.equalsIgnoreCase("--check-update")) {
               q(3);
            } else if (s6.equals("--srv1")) {
               n = 1;
            } else if (s6.equals("--srv2")) {
               n = 2;
            } else if (s6.startsWith("--script=")) {
               s = s6.substring(9);
               q(4);
            } else if (s6.startsWith("--libdir=")) {
               s2 = s6.substring(9);
            } else if (s6.equals("-v") || s6.equals("--verbose")) {
               b = true;
            } else if (s6.startsWith("--typelibgen=")) {
               s3 = s6.substring(13);
               q(5);
            } else if (s6.startsWith("--siglibgen=")) {
               s4 = s6.substring(12);
               q(8);
            } else if (s6.startsWith("--logfile=")) {
               String[] split = s6.substring(10).split(",");
               if (split.length >= 1) {
                  String pathname = split[0];
                  if (pathname.isEmpty()) {
                     q(S.L("Provide a non-empty file name"));
                  }

                  int logLevel = split.length < 2 ? 30 : GlobalLog.parseLevel(split[1]);
                  if (logLevel < 0) {
                     q(S.L("Illegal log level"));
                  }

                  int n5 = split.length < 3 ? 1 : q(split[2], 1);
                  if (n5 <= 0) {
                     q(S.L("Illegal count"));
                  }

                  int n6 = split.length < 4 ? 1 : q(split[3], 1);
                  if (n6 <= 0) {
                     q(S.L("Illegal size"));
                  }

                  boolean b2 = split.length >= 5 && q(split[4], 0) != 0;
                  File file = new File(pathname);
                  StreamSink addDestinationStream = GlobalLog.addDestinationStream(
                     new RotatingFileOutputStream(file.getParentFile(), file.getName(), n5 - 1, n6 * 1024 * 1024, b2, true)
                  );
                  addDestinationStream.setPrefixWithTimestamp(true);
                  addDestinationStream.setLogLevel(logLevel);
               }
            } else if (s6.equals("--makeapkdebug")) {
               q(7);
            } else {
               if (s6.equals("--")) {
                  array = (String[])Arrays.copyOfRange((Object[])original, n4 + 1, original.length);
                  break;
               }

               if (!s6.equals("-h") && !s6.equals("--help")) {
                  System.out.format(S.L("Disregarding unknown argument \"%s\"\n"), s6);
               } else {
                  q();
                  System.exit(-1);
               }
            }
         }

         n4++;
      }

      if (RF == 1) {
         ej ej = new ej(s3, n2, n3);
         ej.initialize(original);
         ej.start();
         ej.stop();
      } else if (RF == 2) {
         HeadlessClientContext headlessClientContext = new HeadlessClientContext();
         headlessClientContext.initialize(original);
         headlessClientContext.start();
         headlessClientContext.stop();
      } else if (RF == 3) {
         HeadlessClientContext headlessClientContext2 = new HeadlessClientContext();
         headlessClientContext2.initialize(original);
         headlessClientContext2.start();
         headlessClientContext2.checkUpdate();
         headlessClientContext2.stop();
      } else if (RF == 4) {
         if (s != null) {
            if (n == 1) {
               throw new RuntimeException("The legacy script runner is deprecated. Please use the script runner v2.");
            }

            if (n == 2) {
               eo eo = new eo(b, s, s2, array);
               eo.initialize(original);
               eo.start();
               eo.stop();
            }
         }
      } else if (RF == 5) {
         TypelibGen.main(new String[]{s4});
      } else if (RF == 8) {
         SiglibGen.main(new String[]{s5});
      } else if (RF == 7) {
         q(array);
      }
   }

   private static void q(int rf) {
      if (RF != 0) {
         System.out.println(S.L("A command was already specified"));
      }

      RF = rf;
   }

   static Integer q(String s) {
      try {
         return Integer.parseInt(s);
      } catch (NumberFormatException var2) {
         return null;
      }
   }

   static int q(String s, int n) {
      try {
         return Integer.parseInt(s);
      } catch (NumberFormatException var3) {
         return n;
      }
   }

   private static void q(String s, Object... array) {
      System.err.println(S.L("[ERROR]") + " " + Strings.ff(s, array));
      System.exit(-1);
   }

   private static void q() {
      System.out.format("%s v%s - %s (c) %s\n", "JEB", AbstractContext.app_ver, "PNF Software, Inc.", "2015-2025");
      System.out.println("");
      System.out.println(S.L("Program options:"));
      System.out.println("  --license             : " + S.L("Print out JEB license information"));
      System.out.println("  --generate-key        : " + S.L("Generate a license key in headless environments"));
      System.out.println("  --check-update        : " + S.L("Download the latest update (requires an Internet connection)"));
      System.out.println("");
      System.out.println(S.L("Script runner options:"));
      System.out.println("  --script=path         : " + S.L("JEB Python script to be executed"));
      System.out.println("  --libdir=path         : " + S.L("Library repository (optional))"));
      System.out.println("  -- [ args [...] ]     : " + S.L("Script arguments (retrievable via IClientContext.getArguments)"));
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
      System.out.println(S.L("Typelib generator:"));
      System.out.println("  --typelibgen=cfgfile  : e.g. typelibs/custom/sample-typelib.cfg");
      System.out.println("                          " + S.L("Refer to the typelibs/ folder, manual and blog for additional details"));
      System.out.println("");
      System.out.println(S.L("Siglib generator:"));
      System.out.println("  --siglibgen=cfgfile   : e.g. siglibs/custom/sample-siglib.cfg");
      System.out.println("                          " + S.L("Refer to the siglibs/ folder, manual and blog for additional details"));
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

   public static void q(String[] array) throws Exception {
      if (array == null || array.length == 0 || array[0].equals("-h")) {
         q.error(S.L("Usage: Provide an APK path"));
         System.exit(-1);
      }

      String pathname = null;
      Integer value = null;
      boolean b = false;

      int i;
      for (i = 0; i < array.length - 1; i++) {
         String substring;
         String s = substring = array[i];
         String substring2 = null;
         int index = s.indexOf(61);
         if (index >= 0) {
            substring = s.substring(0, index);
            substring2 = s.substring(index + 1);
         }

         if (substring.equals("--output")) {
            pathname = substring2;
         } else if (substring.equals("--flagInsertionIndex")) {
            try {
               value = Integer.parseInt(substring2);
            } catch (NumberFormatException var14) {
               q.error(S.L("Illegal insertion index: %s"), substring2);
               System.exit(-1);
            }
         } else if (substring.equals("--forceExtractNativeLibs")) {
            b = true;
         } else {
            q.error(S.L("Unknown option: %s"), substring);
            System.exit(-1);
         }
      }

      if (i >= array.length) {
         q.error(S.L("Usage: Provide an APK path"));
         System.exit(-1);
      }

      File file = new File(array[i]);
      if (!file.isFile()) {
         q.error(S.L("%s: not a file"), file);
         System.exit(-2);
      }

      com.pnfsoftware.jeb.corei.parsers.apk.decoder.CU rq = new com.pnfsoftware.jeb.corei.parsers.apk.decoder.CU(file);
      rq.RF(new File(IO.expandPath("~/.jeb-android-frameworks")));
      byte[] tc = rq.q(value, b);
      if (tc == null) {
         q.info(S.L("This APK is already debuggable"));
      } else {
         File file2;
         if (pathname != null) {
            file2 = new File(pathname);
         } else {
            file2 = new File(IO.noExtension(file) + "_debuggable.apk");
         }

         IO.copyFile(file, file2, true);
         HashMap env = new HashMap();
         env.put("create", "true");

         try (FileSystem fileSystem = FileSystems.newFileSystem(URI.create("jar:" + file2.toPath().toUri()), env)) {
            Files.write(fileSystem.getPath("AndroidManifest.xml"), tc, StandardOpenOption.TRUNCATE_EXISTING);
         }

         q.info(S.L("Generated debuggable APK file %s"), file2);
      }
   }

   static {
      GlobalLog.addDestinationStream(System.out);
   }
}
