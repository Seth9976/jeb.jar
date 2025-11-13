package com.pnfsoftware.jeb.corei.parsers.llvmbc;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.FileInput;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.output.IUnitFormatter;
import com.pnfsoftware.jeb.core.output.UnitFormatterUtil;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractBinaryUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.code.android.AndroidSdkUtil;
import com.pnfsoftware.jeb.util.base.OSType;
import com.pnfsoftware.jeb.util.concurrent.CommandExec;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

@Ser
public class eo extends AbstractBinaryUnit {
   private static final ILogger q = GlobalLog.getLogger(eo.class);
   @SerId(1)
   private String RF;

   public eo(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(null, var2, "llvmbc", var1, var3, var4, var5);
   }

   @Override
   protected boolean processInternal() {
      File var1 = null;
      boolean var2 = false;
      File var3 = null;

      try {
         File var4 = AndroidSdkUtil.locateBestNDK(true);
         String var5 = null;
         String var6 = null;
         switch (OSType.determine()) {
            case WIN64:
               var5 = "windows-x86_64";
               var6 = "llvm-dis.exe";
               break;
            case LINUX64:
               var5 = "linux-x86_64";
               var6 = "llvm-dis";
               break;
            case OSX64:
               var5 = "darwin-x86_64";
               var6 = "llvm-dis";
         }

         if (var5 != null && var6 != null) {
            String var7 = "toolchains/llvm/prebuilt/" + var5 + "/bin/" + var6;
            File var8 = new File(var4, var7);
            if (var8.exists()) {
               IInput var9 = this.getInput();
               if (var9 instanceof FileInput) {
                  var1 = ((FileInput)var9).getFile();
               } else {
                  var2 = true;

                  try (InputStream var10 = var9.getStream()) {
                     var1 = IO.createTempFile();
                     var1.deleteOnExit();
                     IO.writeFile(var1, IO.readInputStream(var10));
                  }
               }

               if (var1 != null) {
                  var3 = IO.createTempFile(var1.getName(), ".decoded");
                  var3.delete();
                  var3.deleteOnExit();
                  boolean var17 = new CommandExec(3000L).execute(var8.getAbsolutePath(), "-o=" + var3.getAbsolutePath(), var1.getAbsolutePath());
                  if (var17 && var3.isFile()) {
                     List var11 = IO.readLines(var3, Charset.defaultCharset());

                     for (int var12 = 0; var12 < var11.size(); var12++) {
                        String var13 = (String)var11.get(var12);
                        if (var13.startsWith("; ModuleID =")) {
                           var11.set(var12, "; ModuleID = " + this.getName());
                        }

                        if (var13.startsWith("source_filename =")) {
                           var11.set(var12, Strings.ff("source_filename = \"%s\"", this.getName()));
                        }
                     }

                     this.RF = Strings.join("\n", var11);
                  }
               }
            }
         }
      } catch (Exception var16) {
         q.catchingSilent(var16);
         if (var1 != null && var2) {
            var1.delete();
         }

         if (var3 != null) {
            var3.delete();
         }
      }

      this.setProcessed(true);
      return true;
   }

   @Override
   public IUnitFormatter getFormatter() {
      IUnitFormatter var1 = super.getFormatter();
      if (this.RF != null && UnitFormatterUtil.getPresentationByIdentifier(var1, 1L) == null) {
         var1.addPresentation(new CU(this, 1L, S.L("Decoded"), true), false);
      }

      return var1;
   }

   @Override
   public String getDescription() {
      StringBuilder var1 = new StringBuilder(S.L("LLVM bitcode binary file\n"));
      if (this.RF == null) {
         Strings.ff(var1, S.L("Use %s to disassemble this file\n"), "llvm-dis");
      }

      return var1.toString();
   }
}
