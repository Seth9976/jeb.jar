package com.pnfsoftware.jeb.corei.parsers.classfile;

import com.android.tools.r8.CompilationMode;
import com.android.tools.r8.D8;
import com.android.tools.r8.D8Command;
import com.android.tools.r8.OutputMode;
import com.android.tools.r8.D8Command.Builder;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class eo implements nI {
   private static final ILogger q = GlobalLog.getLogger(eo.class);
   private int RF;
   private boolean xK;

   public eo(int var1, boolean var2) {
      this.RF = var1;
      this.xK = var2;
   }

   @Override
   public List q(File var1) throws IOException, JebException {
      try {
         File var2 = IO.createTempFolder(null);

         ArrayList var14;
         try {
            D8Command var3 = (D8Command)((Builder)((Builder)((Builder)((Builder)((Builder)D8Command.builder().setMinApiLevel(this.RF))
                           .setDisableDesugaring(!this.xK))
                        .setMode(CompilationMode.RELEASE))
                     .addProgramFiles(new Path[]{var1.toPath()}))
                  .setOutput(var2.toPath(), OutputMode.DexIndexed))
               .build();
            D8.run(var3);
            ArrayList var4 = new ArrayList();

            for (File var6 : IO.listFiles(var2)) {
               var4.add(var6);
            }

            var14 = var4;
         } finally {
            IO.deleteDirectoryOnExit(var2);
         }

         return var14;
      } catch (IOException var12) {
         throw var12;
      } catch (Exception var13) {
         throw new JebException("d8 conversion exception: " + var13.getMessage(), var13);
      }
   }
}
