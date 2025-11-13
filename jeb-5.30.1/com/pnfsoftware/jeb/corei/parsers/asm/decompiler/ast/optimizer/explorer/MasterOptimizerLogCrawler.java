package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.ast.optimizer.explorer;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MasterOptimizerLogCrawler {
   private static final ILogger RF = GlobalLog.getLogger(MasterOptimizerLogCrawler.class);
   static List q;

   public static void main(String[] var0) {
      if (var0.length < 3) {
         RF.warn("Usage:");
         RF.warn("<input_folder_with_logs> <output_folder> --print [optimizationName]|--crawl [optimizationGroup optimizationName]");
      } else {
         q = q(var0[0]);
         if (var0[2].equals("--print")) {
            String var1 = Strings.ff("%s_%d", new File(var0[0]).getName(), System.currentTimeMillis());
            String var2 = null;
            if (var0.length == 4) {
               var2 = var0[3];
            }

            q(var0[1], var1, var2);
            RF(var0[1], var1);
            Dw(var0[1], var1);
         } else {
            if (!var0[2].equals("--crawl")) {
               RF.error("invalid argument");
               return;
            }

            Bu var3 = new Bu(q);
            var3.q(new oM());
            var3.q(new oL());
            var3.q(new vb());
            if (var0.length == 5) {
               var3.q(new Uz(new Bu.eo(Integer.parseInt(var0[3]), var0[4])));
            }

            List var4 = var3.q();
            Object[] var10000 = new Object[]{var4};
         }
      }
   }

   private static List q(String var0) {
      File var1 = RF(var0);
      return q(var1);
   }

   private static File RF(String var0) {
      File var1 = new File(var0);
      if (!var1.exists() || !var1.isDirectory()) {
         RF.error("invalid input folder");
      }

      return var1;
   }

   private static List q(File var0) {
      ArrayList var1 = new ArrayList();

      for (File var3 : IO.listFiles(var0)) {
         var1.add(EE.q(var3));
      }

      return var1;
   }

   private static void q(String var0, String var1, String var2) {
      File var3 = q(var0, var1);
      qV var4 = new qV(q);
      var4.q(var3, true, true, var2);
   }

   private static File q(String var0, String var1) {
      if (!new File(var0).isDirectory()) {
         RF.error("invalid output folder");
      }

      return new File(Strings.ff("%s\\%s.html", var0, var1));
   }

   private static void RF(String var0, String var1) {
      File var2 = xK(var0, var1);
      PY var3 = new PY(q);
      var3.q(var2);
   }

   private static File xK(String var0, String var1) {
      if (!new File(var0).isDirectory()) {
         RF.error("invalid output folder");
      }

      return new File(Strings.ff("%s\\%s_timings.txt", var0, var1));
   }

   private static void Dw(String var0, String var1) {
      File var2 = Uv(var0, var1);
      tw var3 = new tw(q);
      var3.q(var2);
   }

   private static File Uv(String var0, String var1) {
      if (!new File(var0).isDirectory()) {
         RF.error("invalid output folder");
      }

      return new File(Strings.ff("%s\\%s_lengths.txt", var0, var1));
   }

   static {
      GlobalLog.addDestinationStream(System.out);
   }
}
