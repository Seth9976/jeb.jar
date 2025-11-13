package com.pnfsoftware.jeb.core.units.impl;

import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.input.BytesInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.io.File;
import java.io.IOException;

@Ser
public class FileContainerUnit extends ContainerUnit {
   private static final ILogger logger = GlobalLog.getLogger(FileContainerUnit.class);
   @SerTransient
   private File folder;

   public FileContainerUnit(File var1, String var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(var2, var3, var4, var5);
      this.folder = var1;
   }

   @Override
   public boolean process(boolean var1) {
      if (this.isProcessed()) {
         return true;
      } else {
         this.processFiles(this.folder, var1);
         this.setProcessed(true);
         return true;
      }
   }

   private void processFiles(File var1, boolean var2) {
      if (var1.isDirectory()) {
         this.processFolderRecurse(var1, var2);
      } else if (var1.isFile()) {
         this.processFile(var1, var2);
      }
   }

   private void processFolderRecurse(File var1, boolean var2) {
      String[] var3 = var1.list();
      if (var3 != null) {
         for (String var7 : var3) {
            String var8 = var1.getAbsolutePath() + "/" + var7;
            File var9 = new File(var8);
            if (var9.isDirectory()) {
               FileContainerUnit var11 = new FileContainerUnit(var9, var7, this.getUnitProcessor(), this, this.getPropertyDefinitionManager());
               this.addChild(var11);
               var11.process(var2);
            } else {
               IUnit var10 = this.processFile(var9, var2);
               if (var10 != null) {
                  this.addChild(var10);
               }
            }
         }
      }
   }

   private IUnit processFile(File var1, boolean var2) {
      try {
         byte[] var3 = IO.readFile(var1);
         String var4 = var1.getName();
         return this.getUnitProcessor().process(var4, new BytesInput(var3, 0, var3.length, var4), this, null, false, !var2);
      } catch (IOException var5) {
         JebCoreService.notifySilentExceptionToClient(var5);
         logger.catching(var5);
         return null;
      }
   }
}
