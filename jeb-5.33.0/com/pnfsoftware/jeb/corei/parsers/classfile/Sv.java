package com.pnfsoftware.jeb.corei.parsers.classfile;

import com.android.dx.command.dexer.DxContext;
import com.android.dx.command.dexer.Main;
import com.android.dx.command.dexer.Main.Arguments;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Sv implements K {
   private static final ILogger pC = GlobalLog.getLogger(Sv.class);
   private String A;

   @Override
   public List pC(File var1) throws IOException, JebException {
      try {
         ArrayList var26;
         try (
            ByteArrayOutputStream var2 = new ByteArrayOutputStream();
            ByteArrayOutputStream var3 = new ByteArrayOutputStream();
         ) {
            DxContext var4 = new DxContext(var2, var3);
            File var5 = IO.createTempFolder(null);

            try {
               Sv.Av var6 = new Sv.Av(var4, var5.getAbsolutePath(), false, new String[]{var1.getAbsolutePath()});
               int var7 = new Main(var4).runDx(var6);
               this.A = var3.toString("UTF-8");
               if (var7 != 0) {
                  throw new JebException("dx conversion error: " + var7);
               }

               ArrayList var8 = new ArrayList();

               for (File var10 : IO.listFiles(var5)) {
                  var8.add(var10);
               }

               var26 = var8;
            } finally {
               IO.deleteDirectoryOnExit(var5);
            }
         }

         return var26;
      } catch (IOException var24) {
         throw var24;
      } catch (Exception var25) {
         throw new JebException("dx conversion exception: " + var25.getMessage(), var25);
      }
   }

   private static class Av extends Arguments {
      public Av(DxContext var1, String var2, boolean var3, String[] var4) {
         super(var1);
         this.outName = var2;
         this.fileNames = var4;
         this.jarOutput = var3;
         this.multiDex = true;
         this.optimize = true;
         this.localInfo = true;
         this.coreLibrary = true;
         this.debug = true;
         this.warnings = true;
         this.minSdkVersion = 28;
      }
   }
}
