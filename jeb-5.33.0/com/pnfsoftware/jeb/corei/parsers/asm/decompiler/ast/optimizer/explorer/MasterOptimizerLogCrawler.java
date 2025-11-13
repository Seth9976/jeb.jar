package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.ast.optimizer.explorer;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MasterOptimizerLogCrawler {
   private static final ILogger A = GlobalLog.getLogger(MasterOptimizerLogCrawler.class);
   static List pC;

   public static void main(String[] var0) {
      if (var0.length < 3) {
         A.warn("Usage:");
         A.warn("<input_folder_with_logs> <output_folder> --print [optimizationName]|--crawl [optimizationGroup optimizationName]");
      } else {
         pC = pC(var0[0]);
         if (var0[2].equals("--print")) {
            String var1 = Strings.ff("%s_%d", new File(var0[0]).getName(), System.currentTimeMillis());
            String var2 = null;
            if (var0.length == 4) {
               var2 = var0[3];
            }

            pC(var0[1], var1, var2);
            A(var0[1], var1);
            wS(var0[1], var1);
         } else {
            if (!var0[2].equals("--crawl")) {
               A.error("invalid argument");
               return;
            }

            RC var3 = new RC(pC);
            var3.pC(new Sv());
            var3.pC(new yt());
            var3.pC(new KD());
            if (var0.length == 5) {
               var3.pC(new HE(new RC.Av(Integer.parseInt(var0[3]), var0[4])));
            }

            List var4 = var3.pC();
            Object[] var10000 = new Object[]{var4};
         }
      }
   }

   private static List pC(String var0) {
      File var1 = A(var0);
      return pC(var1);
   }

   private static File A(String var0) {
      File var1 = new File(var0);
      if (!var1.exists() || !var1.isDirectory()) {
         A.error("invalid input folder");
      }

      return var1;
   }

   private static List pC(File var0) {
      ArrayList var1 = new ArrayList();

      for (File var3 : IO.listFiles(var0)) {
         var1.add(cq.pC(var3));
      }

      return var1;
   }

   private static void pC(String var0, String var1, String var2) {
      File var3 = pC(var0, var1);
      DH var4 = new DH(pC);
      var4.pC(var3, true, true, var2);
   }

   private static File pC(String var0, String var1) {
      if (!new File(var0).isDirectory()) {
         A.error("invalid output folder");
      }

      return new File(Strings.ff("%s\\%s.html", var0, var1));
   }

   private static void A(String var0, String var1) {
      File var2 = kS(var0, var1);
      zp var3 = new zp(pC);
      var3.pC(var2);
   }

   private static File kS(String var0, String var1) {
      if (!new File(var0).isDirectory()) {
         A.error("invalid output folder");
      }

      return new File(Strings.ff("%s\\%s_timings.txt", var0, var1));
   }

   private static void wS(String var0, String var1) {
      File var2 = UT(var0, var1);
      bO var3 = new bO(pC);
      var3.pC(var2);
   }

   private static File UT(String var0, String var1) {
      if (!new File(var0).isDirectory()) {
         A.error("invalid output folder");
      }

      return new File(Strings.ff("%s\\%s_lengths.txt", var0, var1));
   }

   static {
      GlobalLog.addDestinationStream(System.out);
   }
}
